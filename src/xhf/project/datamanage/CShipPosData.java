package xhf.project.datamanage;

import xhf.project.utilitytool.COleDateTime;

public class CShipPosData {

	public double fLong = 180.0;
	public double fLat = 90.0;
	public double fCourse = 360;
	public double fSpeed = 999;
	public double fHead = 360;
	public int bnStarNum = 0; // star nums
	public int bnDGPS = 1; // is dgps?
	public int bnPos = 'V'; // is pos?
	public int nPosType = 2; //
	public COleDateTime oleGpsTime = COleDateTime.GetCurrentTime();//
	public String strRoute = "";
	public double fXTE = 0;
	public double fAWP = 0;
	public COleDateTime oleRcvTime = COleDateTime.GetCurrentTime();//

	public CShipPosData() {

	}

}
