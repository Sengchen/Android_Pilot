/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class POINT {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected POINT(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(POINT obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_POINT(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setX(int value) {
		elecchartcoreJNI.POINT_x_set(swigCPtr, this, value);
	}

	public int getX() {
		return elecchartcoreJNI.POINT_x_get(swigCPtr, this);
	}

	public void setY(int value) {
		elecchartcoreJNI.POINT_y_set(swigCPtr, this, value);
	}

	public int getY() {
		return elecchartcoreJNI.POINT_y_get(swigCPtr, this);
	}

	public POINT() {
		this(elecchartcoreJNI.new_POINT(), true);
	}

}
