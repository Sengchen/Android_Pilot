/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class CVCFGeoLayer {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected CVCFGeoLayer(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(CVCFGeoLayer obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_CVCFGeoLayer(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public CVCFGeoLayer() {
		this(elecchartcoreJNI.new_CVCFGeoLayer(), true);
	}

	public void addGeoObjectDirectly(CVCFGeoObject arg0) {
		elecchartcoreJNI.CVCFGeoLayer_addGeoObjectDirectly(swigCPtr, this,
				CVCFGeoObject.getCPtr(arg0), arg0);
	}

	public int getGeoObjectNums() {
		return elecchartcoreJNI.CVCFGeoLayer_getGeoObjectNums(swigCPtr, this);
	}

	public CVCFGeoObject getGeoObject(int i) {
		long cPtr = elecchartcoreJNI.CVCFGeoLayer_getGeoObject__SWIG_0(
				swigCPtr, this, i);
		return (cPtr == 0) ? null : new CVCFGeoObject(cPtr, false);
	}

	public void clearGeoObject() {
		elecchartcoreJNI.CVCFGeoLayer_clearGeoObject(swigCPtr, this);
	}

}
