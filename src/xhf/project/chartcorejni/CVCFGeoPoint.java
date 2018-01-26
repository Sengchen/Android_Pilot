/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class CVCFGeoPoint extends CVCFGeometry {
	private long swigCPtr;

	protected CVCFGeoPoint(long cPtr, boolean cMemoryOwn) {
		super(elecchartcoreJNI.CVCFGeoPoint_SWIGUpcast(cPtr), cMemoryOwn);
		swigCPtr = cPtr;
	}

	protected static long getCPtr(CVCFGeoPoint obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_CVCFGeoPoint(swigCPtr);
			}
			swigCPtr = 0;
		}
		super.delete();
	}

	public CVCFGeoPoint() {
		this(elecchartcoreJNI.new_CVCFGeoPoint__SWIG_0(), true);
	}

	public CVCFGeoPoint(double fx, double fy) {
		this(elecchartcoreJNI.new_CVCFGeoPoint__SWIG_1(fx, fy), true);
	}

	public CVCFGeoPoint(double fx, double fy, double fz) {
		this(elecchartcoreJNI.new_CVCFGeoPoint__SWIG_2(fx, fy, fz), true);
	}

	public CVCFGeoPoint(int lx, int ly) {
		this(elecchartcoreJNI.new_CVCFGeoPoint__SWIG_3(lx, ly), true);
	}

	public CVCFGeoPoint(int lx, int ly, double fz) {
		this(elecchartcoreJNI.new_CVCFGeoPoint__SWIG_4(lx, ly, fz), true);
	}

	public xhfGeometryType getGeometryType() {
		return xhfGeometryType.swigToEnum(elecchartcoreJNI
				.CVCFGeoPoint_getGeometryType(swigCPtr, this));
	}

	public boolean ScrPointSelectAt(CPoint pt, stGEOOBJPICKINDEX pkIndex) {
		return elecchartcoreJNI.CVCFGeoPoint_ScrPointSelectAt(swigCPtr, this,
				CPoint.getCPtr(pt), pt, stGEOOBJPICKINDEX.getCPtr(pkIndex),
				pkIndex);
	}

	public void GetPoint(SWIGTYPE_p_double fx, SWIGTYPE_p_double fy) {
		elecchartcoreJNI.CVCFGeoPoint_GetPoint__SWIG_0(swigCPtr, this,
				SWIGTYPE_p_double.getCPtr(fx), SWIGTYPE_p_double.getCPtr(fy));
	}

	public void GetPoint(SWIGTYPE_p_double fx, SWIGTYPE_p_double fy,
			SWIGTYPE_p_double fz) {
		elecchartcoreJNI.CVCFGeoPoint_GetPoint__SWIG_1(swigCPtr, this,
				SWIGTYPE_p_double.getCPtr(fx), SWIGTYPE_p_double.getCPtr(fy),
				SWIGTYPE_p_double.getCPtr(fz));
	}

	public double getX() {
		return elecchartcoreJNI.CVCFGeoPoint_getX(swigCPtr, this);
	}

	public double getY() {
		return elecchartcoreJNI.CVCFGeoPoint_getY(swigCPtr, this);
	}

	public double getZ() {
		return elecchartcoreJNI.CVCFGeoPoint_getZ(swigCPtr, this);
	}

	public void SetPoint(double fx, double fy) {
		elecchartcoreJNI.CVCFGeoPoint_SetPoint__SWIG_0(swigCPtr, this, fx, fy);
	}

	public void SetPoint(double fx, double fy, double fz) {
		elecchartcoreJNI.CVCFGeoPoint_SetPoint__SWIG_1(swigCPtr, this, fx, fy,
				fz);
	}

	public void setX(double xIn) {
		elecchartcoreJNI.CVCFGeoPoint_setX(swigCPtr, this, xIn);
	}

	public void setY(double yIn) {
		elecchartcoreJNI.CVCFGeoPoint_setY(swigCPtr, this, yIn);
	}

	public void setZ(double zIn) {
		elecchartcoreJNI.CVCFGeoPoint_setZ(swigCPtr, this, zIn);
	}

	public void GetlPoint(SWIGTYPE_p_long lx, SWIGTYPE_p_long ly) {
		elecchartcoreJNI.CVCFGeoPoint_GetlPoint__SWIG_0(swigCPtr, this,
				SWIGTYPE_p_long.getCPtr(lx), SWIGTYPE_p_long.getCPtr(ly));
	}

	public void GetlPoint(SWIGTYPE_p_long lx, SWIGTYPE_p_long ly,
			SWIGTYPE_p_double fz) {
		elecchartcoreJNI.CVCFGeoPoint_GetlPoint__SWIG_1(swigCPtr, this,
				SWIGTYPE_p_long.getCPtr(lx), SWIGTYPE_p_long.getCPtr(ly),
				SWIGTYPE_p_double.getCPtr(fz));
	}

	public int getlX() {
		return elecchartcoreJNI.CVCFGeoPoint_getlX(swigCPtr, this);
	}

	public int getlY() {
		return elecchartcoreJNI.CVCFGeoPoint_getlY(swigCPtr, this);
	}

	public void SetlPoint(int lx, int ly) {
		elecchartcoreJNI.CVCFGeoPoint_SetlPoint__SWIG_0(swigCPtr, this, lx, ly);
	}

	public void SetlPoint(int lx, int ly, double fz) {
		elecchartcoreJNI.CVCFGeoPoint_SetlPoint__SWIG_1(swigCPtr, this, lx, ly,
				fz);
	}

	public void setlX(int xIn) {
		elecchartcoreJNI.CVCFGeoPoint_setlX(swigCPtr, this, xIn);
	}

	public void setlY(int yIn) {
		elecchartcoreJNI.CVCFGeoPoint_setlY(swigCPtr, this, yIn);
	}

}
