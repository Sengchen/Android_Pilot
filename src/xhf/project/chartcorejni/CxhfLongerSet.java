/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class CxhfLongerSet {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected CxhfLongerSet(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(CxhfLongerSet obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_CxhfLongerSet(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public CxhfLongerSet() {
		this(elecchartcoreJNI.new_CxhfLongerSet(), true);
	}

	public int GetLongerNums() {
		return elecchartcoreJNI.CxhfLongerSet_GetLongerNums(swigCPtr, this);
	}

	public void AddLonger(long nLong) {
		elecchartcoreJNI.CxhfLongerSet_AddLonger(swigCPtr, this, nLong);
	}

	public long GetLonger(int nIndex) {
		return elecchartcoreJNI.CxhfLongerSet_GetLonger(swigCPtr, this, nIndex);
	}

	public void RemoveAt(int nIndex) {
		elecchartcoreJNI.CxhfLongerSet_RemoveAt(swigCPtr, this, nIndex);
	}

	public void RemoveAllLongers() {
		elecchartcoreJNI.CxhfLongerSet_RemoveAllLongers(swigCPtr, this);
	}

}