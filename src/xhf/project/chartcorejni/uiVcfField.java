/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class uiVcfField {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected uiVcfField(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(uiVcfField obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_uiVcfField(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setNInteger(int value) {
		elecchartcoreJNI.uiVcfField_nInteger_set(swigCPtr, this, value);
	}

	public int getNInteger() {
		return elecchartcoreJNI.uiVcfField_nInteger_get(swigCPtr, this);
	}

	public void setFDouble(double value) {
		elecchartcoreJNI.uiVcfField_fDouble_set(swigCPtr, this, value);
	}

	public double getFDouble() {
		return elecchartcoreJNI.uiVcfField_fDouble_get(swigCPtr, this);
	}

	public void setSzString(String value) {
		elecchartcoreJNI.uiVcfField_szString_set(swigCPtr, this, value);
	}

	public String getSzString() {
		return elecchartcoreJNI.uiVcfField_szString_get(swigCPtr, this);
	}

	public uiVcfField() {
		this(elecchartcoreJNI.new_uiVcfField(), true);
	}

}
