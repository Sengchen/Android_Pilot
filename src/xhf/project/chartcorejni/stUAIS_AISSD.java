/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stUAIS_AISSD {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stUAIS_AISSD(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stUAIS_AISSD obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stUAIS_AISSD(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setSzcallNo(String value) {
		elecchartcoreJNI.stUAIS_AISSD_szcallNo_set(swigCPtr, this, value);
	}

	public String getSzcallNo() {
		return elecchartcoreJNI.stUAIS_AISSD_szcallNo_get(swigCPtr, this);
	}

	public void setSzshipname(String value) {
		elecchartcoreJNI.stUAIS_AISSD_szshipname_set(swigCPtr, this, value);
	}

	public String getSzshipname() {
		return elecchartcoreJNI.stUAIS_AISSD_szshipname_get(swigCPtr, this);
	}

	public void setTxHead(int value) {
		elecchartcoreJNI.stUAIS_AISSD_txHead_set(swigCPtr, this, value);
	}

	public int getTxHead() {
		return elecchartcoreJNI.stUAIS_AISSD_txHead_get(swigCPtr, this);
	}

	public void setTxTail(int value) {
		elecchartcoreJNI.stUAIS_AISSD_txTail_set(swigCPtr, this, value);
	}

	public int getTxTail() {
		return elecchartcoreJNI.stUAIS_AISSD_txTail_get(swigCPtr, this);
	}

	public void setTxLeft(int value) {
		elecchartcoreJNI.stUAIS_AISSD_txLeft_set(swigCPtr, this, value);
	}

	public int getTxLeft() {
		return elecchartcoreJNI.stUAIS_AISSD_txLeft_get(swigCPtr, this);
	}

	public void setTxRight(int value) {
		elecchartcoreJNI.stUAIS_AISSD_txRight_set(swigCPtr, this, value);
	}

	public int getTxRight() {
		return elecchartcoreJNI.stUAIS_AISSD_txRight_get(swigCPtr, this);
	}

	public void setDte(int value) {
		elecchartcoreJNI.stUAIS_AISSD_dte_set(swigCPtr, this, value);
	}

	public int getDte() {
		return elecchartcoreJNI.stUAIS_AISSD_dte_get(swigCPtr, this);
	}

	public stUAIS_AISSD() {
		this(elecchartcoreJNI.new_stUAIS_AISSD(), true);
	}

}
