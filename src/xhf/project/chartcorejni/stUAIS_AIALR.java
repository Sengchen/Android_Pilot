/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stUAIS_AIALR {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stUAIS_AIALR(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stUAIS_AIALR obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stUAIS_AIALR(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setSzTime(String value) {
		elecchartcoreJNI.stUAIS_AIALR_szTime_set(swigCPtr, this, value);
	}

	public String getSzTime() {
		return elecchartcoreJNI.stUAIS_AIALR_szTime_get(swigCPtr, this);
	}

	public void setAlrNo(int value) {
		elecchartcoreJNI.stUAIS_AIALR_alrNo_set(swigCPtr, this, value);
	}

	public int getAlrNo() {
		return elecchartcoreJNI.stUAIS_AIALR_alrNo_get(swigCPtr, this);
	}

	public void setState(char value) {
		elecchartcoreJNI.stUAIS_AIALR_state_set(swigCPtr, this, value);
	}

	public char getState() {
		return elecchartcoreJNI.stUAIS_AIALR_state_get(swigCPtr, this);
	}

	public void setConfirm(char value) {
		elecchartcoreJNI.stUAIS_AIALR_confirm_set(swigCPtr, this, value);
	}

	public char getConfirm() {
		return elecchartcoreJNI.stUAIS_AIALR_confirm_get(swigCPtr, this);
	}

	public void setData(String value) {
		elecchartcoreJNI.stUAIS_AIALR_data_set(swigCPtr, this, value);
	}

	public String getData() {
		return elecchartcoreJNI.stUAIS_AIALR_data_get(swigCPtr, this);
	}

	public void setCheaksum(long value) {
		elecchartcoreJNI.stUAIS_AIALR_cheaksum_set(swigCPtr, this, value);
	}

	public long getCheaksum() {
		return elecchartcoreJNI.stUAIS_AIALR_cheaksum_get(swigCPtr, this);
	}

	public stUAIS_AIALR() {
		this(elecchartcoreJNI.new_stUAIS_AIALR(), true);
	}

}