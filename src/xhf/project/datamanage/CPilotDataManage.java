package xhf.project.datamanage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.http.util.EncodingUtils;

import xhf.project.chartcore.ChartCoreView;
import xhf.project.dynamictarget.CDynamicTargetControl;
import xhf.project.utilitytool.COleDateTime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CPilotDataManage {

	final Context m_context;

	// DatabaseHelper DBHelper;
	private SQLiteDatabase m_pilotDB;
	private DatabaseHelper m_DBHelper;
	static final int DATABASE_VERSION = 2;

	private static String m_strDatabaseName = "";

	private static String m_strShipPosTable = "create table ShipPosDataTable "
			+ "(id integer primary key autoincrement,fLong double, fLat double,"
			+ "fSpeed double,fCourse double,fHead double,nStarNum int,nDGPS int,"
			+ "nPos int,nPosType int,oleGpsTime datetime,szUseRoute varchar,"
			+ "fXTE double,fAWP double,oleRcvTime datetime);";
	private static String m_strShipStaticDataTable = "create table ShipStaticDataTable "
			+ "(id integer primary key autoincrement,szName varchar, szCallNo varchar,"
			+ "nTxHead int,nTxTail int,nTxLeft int,nTxRight int,settime dateTime);";
	private static String m_strAisDynaTable = "create table AisDynaDataTable "
			+ "(id integer primary key autoincrement,ulMMSI long,oleTime datetime,"
			+ "unSailState int,fROT double,fSog double,fCog double,fLong double, fLat double,"
			+ "fHead double,fPosPrecise double,cUTCs int,cRaim int,bOwnShipAis int,"
			+ "oleRcvTime datetime,szUseRoute varchar);";
	private static String m_strAisStaticTabel = "create table AisStaticDataTable "
			+ "(id integer primary key autoincrement,ulMMSI long,oleTime datetime,"
			+ "ulIMO long,szCallSign varchar,szName varchar,nShipType int,nTxHead int,"
			+ "nTxTail int,nTxLeft int,nTxRight int,nPosSys int,oleETA datetime,"
			+ "szDestionation varchar,fSeaGauge double,bnDTE int,bOwnShipAis int);";

	private ContentValues contentValues = new ContentValues();

	public CPilotDataManage(Context ctx) {

		m_context = ctx;

		String strDatabasePath = ChartCoreView.GetSystemDataFileDirectory();
		strDatabasePath += "zhpilotdatabase";

		File f = new File(strDatabasePath);
		if (!f.exists())
			f.mkdir();

		COleDateTime curtime = COleDateTime.GetCurrentTime();

		m_strDatabaseName = String.format("PLT%04d%02d%02d%02d%02d%02d",
				curtime.GetYear(), curtime.GetMonth(), curtime.GetDay(),
				curtime.GetHour(), curtime.GetMinute(), curtime.GetSecond());

		strDatabasePath += "/";

		m_strDatabaseName = strDatabasePath + m_strDatabaseName;

		// m_DBHelper = new DatabaseHelper(m_context);

	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, m_strDatabaseName, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {

				// create.
				db.execSQL(m_strShipPosTable);
				//
				db.execSQL(m_strShipStaticDataTable);
				//
				db.execSQL(m_strAisDynaTable);
				//
				db.execSQL(m_strAisStaticTabel);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS contacts");
			onCreate(db);
		}
	}

	// ---opens the database---
	public CPilotDataManage open() throws SQLException {
		m_pilotDB = m_DBHelper.getWritableDatabase();
		return this;
	}

	// ---closes the database---
	public void close() {
		m_DBHelper.close();
	}

	public void SaveShipPosData(CShipPosData stData) {
		if (CDynamicTargetControl.g_nSystemRunMode == 1)
			return; // ZAIXIAN

		contentValues.clear();

		contentValues.put("fLong", stData.fLong);
		contentValues.put("fLat", stData.fLat);
		contentValues.put("fCourse", stData.fCourse);
		contentValues.put("fSpeed", stData.fSpeed);
		contentValues.put("fHead", stData.fHead);
		contentValues.put("nStarNum", stData.bnStarNum);
		contentValues.put("nDGPS", stData.bnDGPS);
		contentValues.put("nPos", stData.bnPos);
		contentValues.put("nPosType", stData.nPosType);
		contentValues.put("oleGpsTime", stData.oleGpsTime.GetTimeString());
		contentValues.put("szUseRoute", stData.strRoute);
		contentValues.put("fXTE", stData.fXTE);
		contentValues.put("fAWP", stData.fAWP);
		contentValues.put("oleRcvTime", stData.oleRcvTime.GetTimeString());

		m_pilotDB.insert(m_strShipPosTable, null, contentValues);

	}

	public void SaveShipStaticData(CShipStaticData stData) {
		if (CDynamicTargetControl.g_nSystemRunMode == 1)
			return; // ZAIXIAN

		contentValues.clear();

		contentValues.put("szName", stData.szName);
		contentValues.put("szCallNo", stData.szCallNo);
		contentValues.put("nTxHead", stData.nTxHead);
		contentValues.put("nTxTail", stData.nTxTail);
		contentValues.put("nTxLeft", stData.nTxLeft);
		contentValues.put("nTxRight", stData.nTxRight);
		contentValues.put("settime", stData.oleSettime.GetTimeString());

		m_pilotDB.insert(m_strShipStaticDataTable, null, contentValues);

	}

	public void SaveAisDynamicData(CAisDynaData stData) {
		if (CDynamicTargetControl.g_nSystemRunMode == 1)
			return; // ZAIXIAN

		contentValues.clear();

		contentValues.put("ulMMSI", stData.ulMMSI);
		contentValues.put("oleTime", stData.oleTime.GetTimeString());
		contentValues.put("unSailState", stData.unSailState);
		contentValues.put("fROT", stData.fROT);
		contentValues.put("fSog", stData.fSog);
		contentValues.put("fCog", stData.fCog);
		contentValues.put("fHead", stData.fHead);

		contentValues.put("fLong", stData.fLong);
		contentValues.put("fLat", stData.fLat);
		contentValues.put("fPosPrecise", stData.fPosPrecise);
		contentValues.put("cUTCs", stData.cUTCs);

		contentValues.put("cRaim", stData.cRAIM);
		contentValues.put("bOwnShipAis", stData.bOwnShipAis);
		contentValues.put("OleRcvTime", stData.oleRcvTime.GetTimeString());
		contentValues.put("szUseRoute", stData.szUseRoute);

		m_pilotDB.insert(m_strAisDynaTable, null, contentValues);

	}

	public void SaveAisStaticData(CAisStaticData stData) {
		if (CDynamicTargetControl.g_nSystemRunMode == 1)
			return; // ZAIXIAN

		contentValues.clear();

		contentValues.put("ulMMSI", stData.ulMMSI);
		contentValues.put("oleTime", stData.oleTime.GetTimeString());
		contentValues.put("szCallSign", stData.szCallSign);
		contentValues.put("szName", stData.szName);
		contentValues.put("nShipType", stData.nShipType);
		contentValues.put("nTxHead", stData.nDisHead);
		contentValues.put("nTxTail", stData.nDisTail);

		contentValues.put("nTxLeft", stData.nDisLeft);
		contentValues.put("nTxRight", stData.nDisRight);
		contentValues.put("nPosSys", stData.nPosSys);
		contentValues.put("oleETA", stData.oleETA.GetTimeString());

		contentValues.put("szDestionation", stData.szDestination);
		contentValues.put("fSeaGauge", stData.fSeaGauge);
		contentValues.put("bnDTE", stData.bnDTE);
		contentValues.put("bOwnShipAis", stData.bOwnShipAis);

		m_pilotDB.insert(m_strAisStaticTabel, null, contentValues);

	}

	//
	public void SaveShipParameter(CShipStaticData stData) {

		// "/data/data/xhf.project.ardshippilotsys/
		String strSystemDir = ChartCoreView.GetSystemDataFileDirectory();
		strSystemDir += "zhsystemconfig/shipparameter.bin";

		File file = new File(strSystemDir);

		try {

			FileOutputStream fos = new FileOutputStream(file);

			byte[] bytesname = new byte[21];

			int nlen = stData.szName.length();
			System.arraycopy(stData.szName.getBytes(), 0, bytesname, 0, nlen);

			byte[] bytescall = new byte[10];
			nlen = stData.szCallNo.length();
			System.arraycopy(stData.szCallNo.getBytes(), 0, bytescall, 0, nlen);

			byte[] bytestxh = new byte[10];
			String strtxh = Integer.toString(stData.nTxHead);
			System.arraycopy(strtxh.getBytes(), 0, bytestxh, 0, strtxh.length());

			byte[] bytestxt = new byte[10];
			String strtxt = Integer.toString(stData.nTxTail);
			System.arraycopy(strtxt.getBytes(), 0, bytestxt, 0, strtxt.length());

			byte[] bytestxl = new byte[10];
			String strtxl = Integer.toString(stData.nTxLeft);
			System.arraycopy(strtxl.getBytes(), 0, bytestxl, 0, strtxl.length());

			byte[] bytestxr = new byte[10];
			String strtxr = Integer.toString(stData.nTxRight);
			System.arraycopy(strtxr.getBytes(), 0, bytestxr, 0, strtxr.length());

			try {

				fos.write(bytesname, 0, 20);
				fos.write(bytescall, 0, 7);
				fos.write(bytestxh, 0, 4);
				fos.write(bytestxt, 0, 4);
				fos.write(bytestxl, 0, 4);
				fos.write(bytestxr, 0, 4);
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

	public boolean LoadShipParameter(CShipStaticData stData) {
		String strSystemDir = ChartCoreView.GetSystemDataFileDirectory();
		strSystemDir += "zhsystemconfig/shipparameter.bin";

		File file = new File(strSystemDir);

		try {

			FileInputStream fis = new FileInputStream(file);

			try {

				int length = fis.available();

				if (length < 32) {
					fis.close();
					return false;
				}
				byte[] buff = new byte[20];

				fis.read(buff, 0, 20);

				String strName = EncodingUtils.getString(buff, "UTF-8");

				int nIndex = strName.indexOf(0);

				if (nIndex > 0)
					stData.szName = strName.substring(0, nIndex);
				else
					stData.szName = strName;

				// Log.v("111",m_sockCommConfig.m_strServerIP);

				Arrays.fill(buff, (byte) 0);
				fis.read(buff, 0, 7);
				String strCall = EncodingUtils.getString(buff, "UTF-8");
				nIndex = strCall.indexOf(0);
				if (nIndex > 0)
					stData.szCallNo = strCall.substring(0, nIndex);
				else
					stData.szCallNo = strCall;

				// Log.v("111",strsrport);
				Arrays.fill(buff, (byte) 0);
				fis.read(buff, 0, 4);

				String strtxh = EncodingUtils.getString(buff, "UTF-8");
				nIndex = strtxh.indexOf(0);
				if (nIndex > 0) {
					strtxh = strtxh.substring(0, nIndex);
					stData.nTxHead = Integer.parseInt(strtxh);
				} else {
					stData.nTxHead = Integer.parseInt(strtxh);
				}

				// Log.v("111",strlcport);
				Arrays.fill(buff, (byte) 0);
				fis.read(buff, 0, 4);

				String strtxt = EncodingUtils.getString(buff, "UTF-8");
				nIndex = strtxt.indexOf(0);
				if (nIndex > 0) {
					strtxt = strtxt.substring(0, nIndex);
					stData.nTxTail = Integer.parseInt(strtxt);
				} else {
					stData.nTxTail = Integer.parseInt(strtxt);
				}

				Arrays.fill(buff, (byte) 0);
				fis.read(buff, 0, 4);

				String strtxl = EncodingUtils.getString(buff, "UTF-8");
				nIndex = strtxl.indexOf(0);
				if (nIndex > 0) {
					strtxl = strtxl.substring(0, nIndex);
					stData.nTxLeft = Integer.parseInt(strtxl);
				} else {
					stData.nTxLeft = Integer.parseInt(strtxl);
				}

				Arrays.fill(buff, (byte) 0);
				fis.read(buff, 0, 4);

				String strtxr = EncodingUtils.getString(buff, "UTF-8");
				nIndex = strtxr.indexOf(0);
				if (nIndex > 0) {
					strtxr = strtxr.substring(0, nIndex);
					stData.nTxRight = Integer.parseInt(strtxr);
				} else {
					stData.nTxRight = Integer.parseInt(strtxr);
				}

				fis.close();

				return true;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
