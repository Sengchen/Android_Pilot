/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stBODY_UAIS_MESSAGE_6_SUB3 {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stBODY_UAIS_MESSAGE_6_SUB3(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stBODY_UAIS_MESSAGE_6_SUB3 obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stBODY_UAIS_MESSAGE_6_SUB3(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setDAC(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_6_SUB3_DAC_set(swigCPtr, this,
				value);
	}

	public int getDAC() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_6_SUB3_DAC_get(swigCPtr,
				this);
	}

	public void setSpare(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_6_SUB3_spare_set(swigCPtr, this,
				value);
	}

	public int getSpare() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_6_SUB3_spare_get(swigCPtr,
				this);
	}

	public stBODY_UAIS_MESSAGE_6_SUB3() {
		this(elecchartcoreJNI.new_stBODY_UAIS_MESSAGE_6_SUB3(), true);
	}

}