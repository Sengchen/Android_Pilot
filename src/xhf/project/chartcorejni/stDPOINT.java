/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stDPOINT {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stDPOINT(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stDPOINT obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stDPOINT(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setX(double value) {
		elecchartcoreJNI.stDPOINT_x_set(swigCPtr, this, value);
	}

	public double getX() {
		return elecchartcoreJNI.stDPOINT_x_get(swigCPtr, this);
	}

	public void setY(double value) {
		elecchartcoreJNI.stDPOINT_y_set(swigCPtr, this, value);
	}

	public double getY() {
		return elecchartcoreJNI.stDPOINT_y_get(swigCPtr, this);
	}

	public stDPOINT() {
		this(elecchartcoreJNI.new_stDPOINT(), true);
	}

}
