/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class Longs {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected Longs(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(Longs obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_Longs(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public Longs() {
		this(elecchartcoreJNI.new_Longs__SWIG_0(), true);
	}

	public Longs(int n) {
		this(elecchartcoreJNI.new_Longs__SWIG_1(n), true);
	}

	public void recount(int n) {
		elecchartcoreJNI.Longs_recount(swigCPtr, this, n);
	}

	public void clear() {
		elecchartcoreJNI.Longs_clear(swigCPtr, this);
	}

	public int size() {
		return elecchartcoreJNI.Longs_size(swigCPtr, this);
	}

	public int count() {
		return elecchartcoreJNI.Longs_count(swigCPtr, this);
	}

	public int get(int index) {
		return elecchartcoreJNI.Longs_get(swigCPtr, this, index);
	}

	public int gethead() {
		return elecchartcoreJNI.Longs_gethead(swigCPtr, this);
	}

	public int gettail() {
		return elecchartcoreJNI.Longs_gettail(swigCPtr, this);
	}

	public void set(int index, int value) {
		elecchartcoreJNI.Longs_set(swigCPtr, this, index, value);
	}

	public void setNoSize(int index, int value) {
		elecchartcoreJNI.Longs_setNoSize(swigCPtr, this, index, value);
	}

	public void add(int value) {
		elecchartcoreJNI.Longs_add(swigCPtr, this, value);
	}

}
