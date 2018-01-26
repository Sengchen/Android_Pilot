package xhf.project.sensorctl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.apache.http.util.EncodingUtils;

import xhf.project.chartcore.ChartCoreView;
import xhf.project.dynamictarget.CDynamicTargetControl;
import xhf.project.utilitytool.CUtilityTool;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;

public class CPilotSensorControl {

	private static CSensorDataFlowParase m_sensorDataFlow;

	private boolean m_bGpsFaultAlarm = true;
	private static int m_nConnStateTout = 10;
	private int m_nGpsConnToutSet = 10;

	private InetAddress m_ServerAddress;
	private Socket m_TcpSocket;

	private CSocketCommsThread m_SocketCommsThread;

	private CSockCommConfig m_sockCommConfig = new CSockCommConfig();

	private Activity m_parentActivity = null;

	// ////////////////////////////////////////
	private BluetoothAdapter m_mBluetoothAdapter = null;
	// Member object for the chat services
	private BluetoothChatService m_mChatService = null;

	private String m_strBlueaddress = "00:0A:3A:2E:1C:B0";

	// Message types sent from the BluetoothChatService Handler
	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	// Intent request codes
	public static final int REQUEST_CONNECT_DEVICE = 1;
	public static final int REQUEST_ENABLE_BT = 2;
	public static final String BLUETOOTH_DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	private String m_strConnectedDeviceName = null;

	private String m_strRcvBltData = "";

	public CPilotSensorControl(Activity activity) {

		m_parentActivity = activity;

	}

	public void Initialize() {

		if (CDynamicTargetControl.g_nSystemRunMode == 1)
			return;

		ReadSocketConfigFromFile();

		ReadBluetoothConfig();

		// socket
		m_sensorDataFlow = new CSensorDataFlowParase();

		m_sensorDataFlow.InitSensorDataFlow(4);

		try {
			m_ServerAddress = InetAddress
					.getByName(m_sockCommConfig.m_strServerIP);

		} catch (UnknownHostException e) {
			Log.d("Sockets", e.getLocalizedMessage());
		}

		// bluetooth
		m_mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

	}

	public static Handler tcpSockHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			int numOfBytesReceived = msg.arg1;
			byte[] buffer = (byte[]) msg.obj;

			String strReceived = new String(buffer);
			// ---extract only the actual string received---
			strReceived = strReceived.substring(0, numOfBytesReceived);

			// Log.v("BLTRCV",strReceived);

			if (m_sensorDataFlow != null)
				m_sensorDataFlow.SensorRecevDataFlowMsg(strReceived,
						numOfBytesReceived);
			m_nConnStateTout = 0;
		}
	};

	private class CreateSocketCommThreadTask extends
			AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// ---create a socket---
				m_TcpSocket = new Socket(m_ServerAddress,
						m_sockCommConfig.m_nServerPort);
				m_SocketCommsThread = new CSocketCommsThread(m_TcpSocket);
				m_SocketCommsThread.start();

			} catch (UnknownHostException e) {
				Log.d("Sockets", e.getLocalizedMessage());
			} catch (IOException e) {
				Log.d("Sockets", e.getLocalizedMessage());
			}
			return null;
		}

	}

	private class WriteToSocketServerTask extends AsyncTask<byte[], Void, Void> {
		protected Void doInBackground(byte[]... data) {
			m_SocketCommsThread.write(data[0]);
			return null;
		}
	}

	private class CloseSocketTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			try {

				if (m_TcpSocket != null)
					m_TcpSocket.close();

			} catch (IOException e) {
				Log.d("Sockets", e.getLocalizedMessage());
			}
			return null;
		}
	}

	public void StartupSocketCommThread() {
		new CreateSocketCommThreadTask().execute((Void) null);
	}

	public void CloseSocketCommThread() {

		new CloseSocketTask().execute((Void) null);
	}

	/**
	 * 判断网络功能是否可用 需要权限< uses-permission
	 * android:name="android.permission.ACCESS_NETWORK_STATE">
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isNetworkAvailable(Context ctx) {

		ConnectivityManager cm = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}

	public void hfSensorStateRTimeMonitor() {
		if (CDynamicTargetControl.g_nSystemRunMode == 1)
			return;

		m_nConnStateTout++;
		if (m_nConnStateTout >= m_nGpsConnToutSet) {
			m_nConnStateTout = m_nGpsConnToutSet;
			if (!m_bGpsFaultAlarm) {
				// g_pkSystemAlarmManage.xhfSensorFaultAlarm(PLSHIP_LOS_ALARM);

				m_bGpsFaultAlarm = true;

			}
		} else {
			if (m_bGpsFaultAlarm) {
				// g_pkSystemAlarmManage.xhfSensorFaultReset(PLSHIP_LOS_ALARM);

				m_bGpsFaultAlarm = false;
			}
		}
	}

	public void SetSocketConfig(CSockCommConfig sockCommConfig) {

		m_sockCommConfig = sockCommConfig;

		try {
			m_ServerAddress = InetAddress
					.getByName(m_sockCommConfig.m_strServerIP);
		} catch (UnknownHostException e) {
		}

		SaveSocketConfigToFile();

		if (m_sockCommConfig.m_nActive) {
			// CloseSocketCommThread();
			StartupSocketCommThread();
		} else if (!m_sockCommConfig.m_nActive)
			CloseSocketCommThread();
	}

	// /
	public CSockCommConfig GetSocketConfig() {
		return m_sockCommConfig;
	}

	private void SaveSocketConfigToFile() {

		// "/data/data/xhf.project.ardshippilotsys/
		String strSystemDir = ChartCoreView.GetSystemDataFileDirectory();
		strSystemDir += "zhsystemconfig/socketconfig.bin";

		File file = new File(strSystemDir);

		try {

			FileOutputStream fos = new FileOutputStream(file);

			byte[] bytesip = new byte[21];

			int iplen = m_sockCommConfig.m_strServerIP.length();
			System.arraycopy(m_sockCommConfig.m_strServerIP.getBytes(), 0,
					bytesip, 0, iplen);

			byte[] bytessport = new byte[10];
			String strSvrport = Integer
					.toString(m_sockCommConfig.m_nServerPort);
			System.arraycopy(strSvrport.getBytes(), 0, bytessport, 0,
					strSvrport.length());

			byte[] byteslport = new byte[10];
			String strlocport = Integer.toString(m_sockCommConfig.m_nLocalPort);
			System.arraycopy(strlocport.getBytes(), 0, byteslport, 0,
					strlocport.length());

			int nOnoff = 1;
			if (m_sockCommConfig.m_nActive)
				nOnoff = 1;
			else
				nOnoff = 0;
			byte[] bytesActiv = new byte[10];
			String strOnOff = Integer.toString(nOnoff);
			System.arraycopy(strOnOff.getBytes(), 0, bytesActiv, 0,
					strOnOff.length());

			try {

				fos.write(bytesip, 0, 20);
				fos.write(bytessport, 0, 4);
				fos.write(byteslport, 0, 4);

				fos.write(bytesActiv, 0, 4);
				fos.flush();
				fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ReadSocketConfigFromFile() {
		String strSystemDir = ChartCoreView.GetSystemDataFileDirectory();
		strSystemDir += "zhsystemconfig/socketconfig.bin";

		File file = new File(strSystemDir);

		try {

			FileInputStream fis = new FileInputStream(file);

			try {

				int length = fis.available();

				if (length < 32) {
					fis.close();
					return;
				}
				byte[] ipbuff = new byte[20];

				fis.read(ipbuff, 0, 20);

				String strIP = EncodingUtils.getString(ipbuff, "UTF-8");

				int nIndex = strIP.indexOf(0);

				if (nIndex > 0)
					m_sockCommConfig.m_strServerIP = strIP.substring(0, nIndex);
				else
					m_sockCommConfig.m_strServerIP = strIP;

				// Log.v("111",m_sockCommConfig.m_strServerIP);

				Arrays.fill(ipbuff, (byte) 0);
				fis.read(ipbuff, 0, 4);
				String strsrport = EncodingUtils.getString(ipbuff, "UTF-8");
				nIndex = strsrport.indexOf(0);
				if (nIndex > 0) {
					strsrport = strsrport.substring(0, nIndex);
					m_sockCommConfig.m_nServerPort = Integer
							.parseInt(strsrport);
				} else {
					m_sockCommConfig.m_nServerPort = Integer
							.parseInt(strsrport);
				}

				// Log.v("111",strsrport);

				Arrays.fill(ipbuff, (byte) 0);
				fis.read(ipbuff, 0, 4);
				String strlcport = EncodingUtils.getString(ipbuff, "UTF-8");
				nIndex = strlcport.indexOf(0);
				if (nIndex > 0) {
					strlcport = strlcport.substring(0, nIndex);
					m_sockCommConfig.m_nLocalPort = Integer.parseInt(strlcport);
				} else {
					m_sockCommConfig.m_nLocalPort = Integer.parseInt(strlcport);
				}

				// Log.v("111",strlcport);

				int nOnoff = 1;

				Arrays.fill(ipbuff, (byte) 0);
				fis.read(ipbuff, 0, 4);

				String stronoff = EncodingUtils.getString(ipbuff, "UTF-8");
				nIndex = stronoff.indexOf(0);
				if (nIndex > 0) {
					stronoff = stronoff.substring(0, nIndex);
					nOnoff = Integer.parseInt(stronoff);
				} else {
					nOnoff = Integer.parseInt(stronoff);
				}

				// Log.v("111",stronoff);

				if (nOnoff == 0)
					m_sockCommConfig.m_nActive = false;
				else
					m_sockCommConfig.m_nActive = true;

				fis.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SaveBluetoothConfig() {

		String strSystemDir = ChartCoreView.GetSystemDataFileDirectory();
		strSystemDir += "zhsystemconfig/bluetoothconfig.bin";
		File file = new File(strSystemDir);

		try {

			FileOutputStream fos = new FileOutputStream(file);

			byte[] bytes = m_strBlueaddress.getBytes();

			try {

				fos.write(bytes);
				fos.flush();
				fos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void ReadBluetoothConfig() {

		String strSystemDir = ChartCoreView.GetSystemDataFileDirectory();
		strSystemDir += "zhsystemconfig/bluetoothconfig.bin";

		File file = new File(strSystemDir);

		try {

			FileInputStream fis = new FileInputStream(file);

			try {

				int length = fis.available();

				byte[] buff = new byte[length];

				fis.read(buff);

				m_strBlueaddress = EncodingUtils.getString(buff, "UTF-8");

				fis.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean GetGpsFaultAlarm() {

		return m_bGpsFaultAlarm;
	}

	/*
	 * bluetooth
	 */
	//
	public boolean BluetoothIsEnabled() {

		if (m_mBluetoothAdapter == null) {
			m_mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			if (m_mBluetoothAdapter == null)
				return false;
		}
		if (!m_mBluetoothAdapter.isEnabled())
			return false;
		else
			return true;
	}

	//
	public boolean StartupBluetooth(Activity ativity) {

		m_parentActivity = ativity;
		if (m_mBluetoothAdapter == null) {
			m_mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			if (m_mBluetoothAdapter == null)
				return false;
		}

		if (!m_mBluetoothAdapter.isEnabled()) {
			Intent enableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_ENABLE);
			m_parentActivity.startActivityForResult(enableIntent,
					REQUEST_ENABLE_BT);
			return false;
		}
		if (m_mChatService != null)
			return true;

		// Initialize the BluetoothChatService to perform bluetooth connections
		m_mChatService = new BluetoothChatService(m_parentActivity,
				mBluetoohHandler);
		BluetoothDevice device = m_mBluetoothAdapter
				.getRemoteDevice(m_strBlueaddress);
		// Attempt to connect to the device
		m_mChatService.connect(device);

		return true;

	}

	//
	private final Handler mBluetoohHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_STATE_CHANGE:

				switch (msg.arg1) {
				case BluetoothChatService.STATE_CONNECTED:
					break;
				case BluetoothChatService.STATE_CONNECTING:

					break;
				case BluetoothChatService.STATE_LISTEN:
				case BluetoothChatService.STATE_NONE:

					break;
				}
				break;
			case MESSAGE_WRITE:
				/*
				 * byte[] writeBuf = (byte[]) msg.obj; // construct a string
				 * from the buffer String writeMessage = new String(writeBuf);
				 */
				break;
			case MESSAGE_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// construct a string from the valid bytes in the buffer
				String readMessage = new String(readBuf, 0, msg.arg1);

				m_strRcvBltData += readMessage;

				m_nConnStateTout = 0;

				if (m_strRcvBltData.length() >= 256) {
					Log.v("BLTRCV", m_strRcvBltData);

					if (m_sensorDataFlow != null)
						m_sensorDataFlow.SensorRecevDataFlowMsg(
								m_strRcvBltData, m_strRcvBltData.length());

					m_strRcvBltData = "";
				}

				break;
			case MESSAGE_DEVICE_NAME:
				// save the connected device's name
				// mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
				// Toast.makeText(getApplicationContext(), "Connected to "
				// + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
				break;
			case MESSAGE_TOAST:
				/*
				 * Toast.makeText(getApplicationContext(),
				 * msg.getData().getString(TOAST), Toast.LENGTH_SHORT).show();
				 */
				break;
			}
		}

	};

	//
	public void bluetoothResume() {

		if (m_mChatService != null) {
			// Only if the state is STATE_NONE, do we know that we haven't
			// started already
			if (m_mChatService.getState() == BluetoothChatService.STATE_NONE) {
				// Start the Bluetooth chat services
				m_mChatService.start();
			}
		}
	}

	//
	public void bluetoothStop() {

		if (m_mChatService != null)
			m_mChatService.stop();
	}

	public boolean ensureDiscoverable(Activity ativity) {

		m_parentActivity = ativity;
		if (m_mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {

			Intent discoverableIntent = new Intent(
					BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			discoverableIntent.putExtra(
					BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
			ativity.startActivity(discoverableIntent);
			return true;
		} else
			return false;

	}

	public void requestConntectDevice(String straddress) {

		m_strBlueaddress = straddress;
		// Get the BLuetoothDevice object
		BluetoothDevice device = m_mBluetoothAdapter
				.getRemoteDevice(straddress);
		// Attempt to connect to the device
		m_mChatService.connect(device);

		SaveBluetoothConfig();
	}

	public void requestEnableBluetooth(Activity ativity) {

		m_parentActivity = ativity;

		m_mChatService = new BluetoothChatService(ativity, mBluetoohHandler);

		BluetoothDevice device = m_mBluetoothAdapter
				.getRemoteDevice(m_strBlueaddress);
		// Attempt to connect to the device
		m_mChatService.connect(device);
	}
	//

}
