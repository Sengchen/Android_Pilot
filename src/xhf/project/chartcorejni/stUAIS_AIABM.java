/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stUAIS_AIABM {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stUAIS_AIABM(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stUAIS_AIABM obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stUAIS_AIABM(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setNNum(int value) {
		elecchartcoreJNI.stUAIS_AIABM_nNum_set(swigCPtr, this, value);
	}

	public int getNNum() {
		return elecchartcoreJNI.stUAIS_AIABM_nNum_get(swigCPtr, this);
	}

	public void setNNO(int value) {
		elecchartcoreJNI.stUAIS_AIABM_nNO_set(swigCPtr, this, value);
	}

	public int getNNO() {
		return elecchartcoreJNI.stUAIS_AIABM_nNO_get(swigCPtr, this);
	}

	public void setNchannel(int value) {
		elecchartcoreJNI.stUAIS_AIABM_nchannel_set(swigCPtr, this, value);
	}

	public int getNchannel() {
		return elecchartcoreJNI.stUAIS_AIABM_nchannel_get(swigCPtr, this);
	}

	public void setMsgid(int value) {
		elecchartcoreJNI.stUAIS_AIABM_msgid_set(swigCPtr, this, value);
	}

	public int getMsgid() {
		return elecchartcoreJNI.stUAIS_AIABM_msgid_get(swigCPtr, this);
	}

	public void setSerialNO(int value) {
		elecchartcoreJNI.stUAIS_AIABM_serialNO_set(swigCPtr, this, value);
	}

	public int getSerialNO() {
		return elecchartcoreJNI.stUAIS_AIABM_serialNO_get(swigCPtr, this);
	}

	public void setDmmsi(int value) {
		elecchartcoreJNI.stUAIS_AIABM_dmmsi_set(swigCPtr, this, value);
	}

	public int getDmmsi() {
		return elecchartcoreJNI.stUAIS_AIABM_dmmsi_get(swigCPtr, this);
	}

	public void setSzText(String value) {
		elecchartcoreJNI.stUAIS_AIABM_szText_set(swigCPtr, this, value);
	}

	public String getSzText() {
		return elecchartcoreJNI.stUAIS_AIABM_szText_get(swigCPtr, this);
	}

	public void setFillbit(int value) {
		elecchartcoreJNI.stUAIS_AIABM_fillbit_set(swigCPtr, this, value);
	}

	public int getFillbit() {
		return elecchartcoreJNI.stUAIS_AIABM_fillbit_get(swigCPtr, this);
	}

	public void setState(int value) {
		elecchartcoreJNI.stUAIS_AIABM_state_set(swigCPtr, this, value);
	}

	public int getState() {
		return elecchartcoreJNI.stUAIS_AIABM_state_get(swigCPtr, this);
	}

	public stUAIS_AIABM() {
		this(elecchartcoreJNI.new_stUAIS_AIABM(), true);
	}

}
