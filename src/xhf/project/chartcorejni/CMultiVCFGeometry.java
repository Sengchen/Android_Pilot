/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class CMultiVCFGeometry extends CVCFGeometry {
	private long swigCPtr;

	protected CMultiVCFGeometry(long cPtr, boolean cMemoryOwn) {
		super(elecchartcoreJNI.CMultiVCFGeometry_SWIGUpcast(cPtr), cMemoryOwn);
		swigCPtr = cPtr;
	}

	protected static long getCPtr(CMultiVCFGeometry obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_CMultiVCFGeometry(swigCPtr);
			}
			swigCPtr = 0;
		}
		super.delete();
	}

	public CMultiVCFGeometry() {
		this(elecchartcoreJNI.new_CMultiVCFGeometry(), true);
	}

	public xhfGeometryType getGeometryType() {
		return xhfGeometryType.swigToEnum(elecchartcoreJNI
				.CMultiVCFGeometry_getGeometryType(swigCPtr, this));
	}

	public int getNumGeometries() {
		return elecchartcoreJNI.CMultiVCFGeometry_getNumGeometries(swigCPtr,
				this);
	}

	public CVCFGeometry getGeometryRef(int arg0) {
		long cPtr = elecchartcoreJNI.CMultiVCFGeometry_getGeometryRef(swigCPtr,
				this, arg0);
		return (cPtr == 0) ? null : new CVCFGeometry(cPtr, false);
	}

	public int addGeometryDirectly(CVCFGeometry arg0) {
		return elecchartcoreJNI.CMultiVCFGeometry_addGeometryDirectly(swigCPtr,
				this, CVCFGeometry.getCPtr(arg0), arg0);
	}

	public int removeGeometry(int iIndex, int bDelete) {
		return elecchartcoreJNI.CMultiVCFGeometry_removeGeometry__SWIG_0(
				swigCPtr, this, iIndex, bDelete);
	}

	public int removeGeometry(int iIndex) {
		return elecchartcoreJNI.CMultiVCFGeometry_removeGeometry__SWIG_1(
				swigCPtr, this, iIndex);
	}

	public void closeRings() {
		elecchartcoreJNI.CMultiVCFGeometry_closeRings(swigCPtr, this);
	}

}