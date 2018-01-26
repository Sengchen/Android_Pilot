package xhf.project.datamanage;

import xhf.project.utilitytool.COleDateTime;

public class CShipStaticData {

	String szName = "";
	String szCallNo = "";
	int nTxHead = 0;
	int nTxTail = 0;
	int nTxLeft = 0;
	int nTxRight = 0;
	COleDateTime oleSettime = COleDateTime.GetCurrentTime();

	public CShipStaticData() {
		// TODO Auto-generated constructor stub
	}

	public void init() {

		szName = "";
		szCallNo = "";
		nTxHead = 50;
		nTxTail = 20;
		nTxLeft = 18;
		nTxRight = 20;

	}

	public String GetName() {

		return szName;
	}

	public void SetName(String str) {

		szName = str;
	}

	public String GetCallNo() {

		return szCallNo;
	}

	public void SetCallNo(String str) {
		szCallNo = str;

	}

	public int GetTxHead() {

		return nTxHead;
	}

	public void SetTxHead(int x) {

		nTxHead = x;
	}

	public int GetTxTail() {

		return nTxTail;
	}

	public void SetTxTail(int x) {

		nTxTail = x;
	}

	public int GetTxLeft() {

		return nTxLeft;
	}

	public void SetTxLeft(int x) {

		nTxLeft = x;
	}

	public void SetTxRight(int x) {

		nTxRight = x;
	}

	public int GetTxRight() {

		return nTxRight;
	}

}
