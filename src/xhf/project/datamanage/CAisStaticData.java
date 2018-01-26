package xhf.project.datamanage;

import xhf.project.utilitytool.COleDateTime;

public class CAisStaticData {

	public long ulMMSI;
	public COleDateTime oleTime;
	public long ulIMO;
	public String szCallSign;
	public String szName;
	public int nShipType;
	public int nDisHead;
	public int nDisTail;
	public int nDisLeft;
	public int nDisRight;
	public int nPosSys;
	public COleDateTime oleETA;
	public String szDestination;
	public double fSeaGauge;
	public int bnDTE;
	public int bOwnShipAis; // 0-other AIS,1-Own AIS

	public CAisStaticData() {
		// TODO Auto-generated constructor stub
	}

}
