/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class CGeo2lPoint {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected CGeo2lPoint(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(CGeo2lPoint obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_CGeo2lPoint(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public CGeo2lPoint() {
		this(elecchartcoreJNI.new_CGeo2lPoint(), true);
	}

	public void setM_lx(int value) {
		elecchartcoreJNI.CGeo2lPoint_m_lx_set(swigCPtr, this, value);
	}

	public int getM_lx() {
		return elecchartcoreJNI.CGeo2lPoint_m_lx_get(swigCPtr, this);
	}

	public void setM_ly(int value) {
		elecchartcoreJNI.CGeo2lPoint_m_ly_set(swigCPtr, this, value);
	}

	public int getM_ly() {
		return elecchartcoreJNI.CGeo2lPoint_m_ly_get(swigCPtr, this);
	}

}
