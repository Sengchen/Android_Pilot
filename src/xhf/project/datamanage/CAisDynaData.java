package xhf.project.datamanage;

import xhf.project.utilitytool.COleDateTime;

public class CAisDynaData {

	public long ulMMSI = 0;
	public COleDateTime oleTime = COleDateTime.GetCurrentTime();
	public int unSailState = 0;
	public double fROT = 999;
	public double fSog = 999;
	public double fCog = 360;
	public double fHead = 360;
	public double fLong = 180;
	public double fLat = 90;
	public double fPosPrecise = 0;
	public int cUTCs = 0;
	public int cRAIM = 0;
	public String szShipName = "";
	public String szCallSign = "";
	public int bOwnShipAis = 0; // 0-other AIS,1-Own AIS
	public COleDateTime oleRcvTime = COleDateTime.GetCurrentTime();;
	public String szUseRoute = ""; //

	public CAisDynaData() {
		// TODO Auto-generated constructor stub
	}

}
