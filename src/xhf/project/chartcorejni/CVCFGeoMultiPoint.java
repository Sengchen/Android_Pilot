/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class CVCFGeoMultiPoint extends CMultiVCFGeometry {
	private long swigCPtr;

	protected CVCFGeoMultiPoint(long cPtr, boolean cMemoryOwn) {
		super(elecchartcoreJNI.CVCFGeoMultiPoint_SWIGUpcast(cPtr), cMemoryOwn);
		swigCPtr = cPtr;
	}

	protected static long getCPtr(CVCFGeoMultiPoint obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_CVCFGeoMultiPoint(swigCPtr);
			}
			swigCPtr = 0;
		}
		super.delete();
	}

	public CVCFGeoMultiPoint() {
		this(elecchartcoreJNI.new_CVCFGeoMultiPoint(), true);
	}

	public xhfGeometryType getGeometryType() {
		return xhfGeometryType.swigToEnum(elecchartcoreJNI
				.CVCFGeoMultiPoint_getGeometryType(swigCPtr, this));
	}

	public int addGeometryDirectly(CVCFGeometry poNewGeom) {
		return elecchartcoreJNI.CVCFGeoMultiPoint_addGeometryDirectly(swigCPtr,
				this, CVCFGeometry.getCPtr(poNewGeom), poNewGeom);
	}

	public void UpdateGeometry(CVCFGeoPoint pGeome, int nIndexPos) {
		elecchartcoreJNI.CVCFGeoMultiPoint_UpdateGeometry__SWIG_0(swigCPtr,
				this, CVCFGeoPoint.getCPtr(pGeome), pGeome, nIndexPos);
	}

	public void UpdateGeometry(double x, double y, double z, int nIndexPos) {
		elecchartcoreJNI.CVCFGeoMultiPoint_UpdateGeometry__SWIG_1(swigCPtr,
				this, x, y, z, nIndexPos);
	}

}
