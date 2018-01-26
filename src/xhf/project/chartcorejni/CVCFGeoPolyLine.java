/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class CVCFGeoPolyLine extends CVCFGeometry {
	private long swigCPtr;

	protected CVCFGeoPolyLine(long cPtr, boolean cMemoryOwn) {
		super(elecchartcoreJNI.CVCFGeoPolyLine_SWIGUpcast(cPtr), cMemoryOwn);
		swigCPtr = cPtr;
	}

	protected static long getCPtr(CVCFGeoPolyLine obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_CVCFGeoPolyLine(swigCPtr);
			}
			swigCPtr = 0;
		}
		super.delete();
	}

	public CVCFGeoPolyLine() {
		this(elecchartcoreJNI.new_CVCFGeoPolyLine(), true);
	}

	public xhfGeometryType getGeometryType() {
		return xhfGeometryType.swigToEnum(elecchartcoreJNI
				.CVCFGeoPolyLine_getGeometryType(swigCPtr, this));
	}

	public boolean ScrPointSelectAt(CPoint pt, stGEOOBJPICKINDEX pkIndex) {
		return elecchartcoreJNI.CVCFGeoPolyLine_ScrPointSelectAt(swigCPtr,
				this, CPoint.getCPtr(pt), pt,
				stGEOOBJPICKINDEX.getCPtr(pkIndex), pkIndex);
	}

	public void addEdge(CVCFGeoEdge arg0) {
		elecchartcoreJNI.CVCFGeoPolyLine_addEdge(swigCPtr, this,
				CVCFGeoEdge.getCPtr(arg0), arg0);
	}

	public void addEdgeDirectly(CVCFGeoEdge arg0) {
		elecchartcoreJNI.CVCFGeoPolyLine_addEdgeDirectly(swigCPtr, this,
				CVCFGeoEdge.getCPtr(arg0), arg0);
	}

	public CVCFGeoEdge getExteriorEdge() {
		long cPtr = elecchartcoreJNI.CVCFGeoPolyLine_getExteriorEdge__SWIG_0(
				swigCPtr, this);
		return (cPtr == 0) ? null : new CVCFGeoEdge(cPtr, false);
	}

	public int getNumInteriorEdges() {
		return elecchartcoreJNI.CVCFGeoPolyLine_getNumInteriorEdges(swigCPtr,
				this);
	}

	public CVCFGeoEdge getInteriorEdge(int arg0) {
		long cPtr = elecchartcoreJNI.CVCFGeoPolyLine_getInteriorEdge__SWIG_0(
				swigCPtr, this, arg0);
		return (cPtr == 0) ? null : new CVCFGeoEdge(cPtr, false);
	}

	public int GetTatolPointNums() {
		return elecchartcoreJNI.CVCFGeoPolyLine_GetTatolPointNums(swigCPtr,
				this);
	}

	public int GetTatollPointNums() {
		return elecchartcoreJNI.CVCFGeoPolyLine_GetTatollPointNums(swigCPtr,
				this);
	}

	public int getNumdspPoints() {
		return elecchartcoreJNI.CVCFGeoPolyLine_getNumdspPoints(swigCPtr, this);
	}

	public void setNumdspPoints(int arg0) {
		elecchartcoreJNI.CVCFGeoPolyLine_setNumdspPoints(swigCPtr, this, arg0);
	}

	public void setdspPoint(int arg0, int arg1, int arg2) {
		elecchartcoreJNI.CVCFGeoPolyLine_setdspPoint(swigCPtr, this, arg0,
				arg1, arg2);
	}

	public void setNumdspEdgeLens(int arg0) {
		elecchartcoreJNI
				.CVCFGeoPolyLine_setNumdspEdgeLens(swigCPtr, this, arg0);
	}

	public void setdspEdgeLen(int arg0, int arg1) {
		elecchartcoreJNI.CVCFGeoPolyLine_setdspEdgeLen(swigCPtr, this, arg0,
				arg1);
	}

	public void setM_ndspPointCount(int value) {
		elecchartcoreJNI.CVCFGeoPolyLine_m_ndspPointCount_set(swigCPtr, this,
				value);
	}

	public int getM_ndspPointCount() {
		return elecchartcoreJNI.CVCFGeoPolyLine_m_ndspPointCount_get(swigCPtr,
				this);
	}

	public void setM_nEdgeLens(Ints value) {
		elecchartcoreJNI.CVCFGeoPolyLine_m_nEdgeLens_set(swigCPtr, this,
				Ints.getCPtr(value), value);
	}

	public Ints getM_nEdgeLens() {
		long cPtr = elecchartcoreJNI.CVCFGeoPolyLine_m_nEdgeLens_get(swigCPtr,
				this);
		return (cPtr == 0) ? null : new Ints(cPtr, false);
	}

	public void setM_paoDspPointXs(Ints value) {
		elecchartcoreJNI.CVCFGeoPolyLine_m_paoDspPointXs_set(swigCPtr, this,
				Ints.getCPtr(value), value);
	}

	public Ints getM_paoDspPointXs() {
		long cPtr = elecchartcoreJNI.CVCFGeoPolyLine_m_paoDspPointXs_get(
				swigCPtr, this);
		return (cPtr == 0) ? null : new Ints(cPtr, false);
	}

	public void setM_paoDspPointYs(Ints value) {
		elecchartcoreJNI.CVCFGeoPolyLine_m_paoDspPointYs_set(swigCPtr, this,
				Ints.getCPtr(value), value);
	}

	public Ints getM_paoDspPointYs() {
		long cPtr = elecchartcoreJNI.CVCFGeoPolyLine_m_paoDspPointYs_get(
				swigCPtr, this);
		return (cPtr == 0) ? null : new Ints(cPtr, false);
	}

}
