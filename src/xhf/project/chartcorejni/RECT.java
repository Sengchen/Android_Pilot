/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class RECT {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected RECT(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(RECT obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_RECT(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setLeft(int value) {
		elecchartcoreJNI.RECT_left_set(swigCPtr, this, value);
	}

	public int getLeft() {
		return elecchartcoreJNI.RECT_left_get(swigCPtr, this);
	}

	public void setTop(int value) {
		elecchartcoreJNI.RECT_top_set(swigCPtr, this, value);
	}

	public int getTop() {
		return elecchartcoreJNI.RECT_top_get(swigCPtr, this);
	}

	public void setRight(int value) {
		elecchartcoreJNI.RECT_right_set(swigCPtr, this, value);
	}

	public int getRight() {
		return elecchartcoreJNI.RECT_right_get(swigCPtr, this);
	}

	public void setBottom(int value) {
		elecchartcoreJNI.RECT_bottom_set(swigCPtr, this, value);
	}

	public int getBottom() {
		return elecchartcoreJNI.RECT_bottom_get(swigCPtr, this);
	}

	public RECT() {
		this(elecchartcoreJNI.new_RECT(), true);
	}

}
