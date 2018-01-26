package xhf.project.sensorctl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.util.Log;

public class CSocketCommsThread extends Thread {

	private final Socket m_TcpSocket;
	private final InputStream m_InputStream;
	private final OutputStream m_OutputStream;
	private byte[] m_rcvBuffers = new byte[1024];
	private int m_nrcvLength = 0;

	public CSocketCommsThread(Socket sock) {

		m_TcpSocket = sock;
		InputStream tmpIn = null;
		OutputStream tmpOut = null;
		try {

			tmpIn = m_TcpSocket.getInputStream();
			tmpOut = m_TcpSocket.getOutputStream();

		} catch (IOException e) {
			Log.d("SocketChat", e.getLocalizedMessage());
		}
		m_InputStream = tmpIn;
		m_OutputStream = tmpOut;
	}

	public void run() {
		// ---keep listening to the InputStream until an
		// exception occurs---
		while (true) {
			try {
				// ---read from the inputStream---
				m_nrcvLength = m_InputStream.read(m_rcvBuffers);
				CPilotSensorControl.tcpSockHandler.obtainMessage(0,
						m_nrcvLength, -1, m_rcvBuffers).sendToTarget();

			} catch (IOException e) {
				break;
			}
		}
	}

	public void write(byte[] bytes) {
		try {
			m_OutputStream.write(bytes);
		} catch (IOException e) {
		}
	}

	// shutdown the connection---
	public void cancel() {

		try {
			m_TcpSocket.close();
		} catch (IOException e) {
		}
	}

}
