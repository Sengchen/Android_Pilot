/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stBODY_NEMA_GPZDA {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stBODY_NEMA_GPZDA(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stBODY_NEMA_GPZDA obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stBODY_NEMA_GPZDA(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setTime(stUTC_TIME value) {
		elecchartcoreJNI.stBODY_NEMA_GPZDA_time_set(swigCPtr, this,
				stUTC_TIME.getCPtr(value), value);
	}

	public stUTC_TIME getTime() {
		long cPtr = elecchartcoreJNI.stBODY_NEMA_GPZDA_time_get(swigCPtr, this);
		return (cPtr == 0) ? null : new stUTC_TIME(cPtr, false);
	}

	public void setDate(stUTC_DATE value) {
		elecchartcoreJNI.stBODY_NEMA_GPZDA_date_set(swigCPtr, this,
				stUTC_DATE.getCPtr(value), value);
	}

	public stUTC_DATE getDate() {
		long cPtr = elecchartcoreJNI.stBODY_NEMA_GPZDA_date_get(swigCPtr, this);
		return (cPtr == 0) ? null : new stUTC_DATE(cPtr, false);
	}

	public stBODY_NEMA_GPZDA() {
		this(elecchartcoreJNI.new_stBODY_NEMA_GPZDA(), true);
	}

}