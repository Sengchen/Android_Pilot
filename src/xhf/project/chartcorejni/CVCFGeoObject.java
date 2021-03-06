/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class CVCFGeoObject {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected CVCFGeoObject(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(CVCFGeoObject obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_CVCFGeoObject(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public CVCFGeoObject() {
		this(elecchartcoreJNI.new_CVCFGeoObject(), true);
	}

	public void SetLayerClassCode(int nLayerClsCode) {
		elecchartcoreJNI.CVCFGeoObject_SetLayerClassCode(swigCPtr, this,
				nLayerClsCode);
	}

	public int GetLayerClassCode() {
		return elecchartcoreJNI.CVCFGeoObject_GetLayerClassCode(swigCPtr, this);
	}

	public void SetObjectClassCode(int lObjectClsCode) {
		elecchartcoreJNI.CVCFGeoObject_SetObjectClassCode(swigCPtr, this,
				lObjectClsCode);
	}

	public int GetObjectClassCode() {
		return elecchartcoreJNI
				.CVCFGeoObject_GetObjectClassCode(swigCPtr, this);
	}

	public void SetFeatureId(int nFeatureId) {
		elecchartcoreJNI.CVCFGeoObject_SetFeatureId(swigCPtr, this, nFeatureId);
	}

	public int GetFeatureId() {
		return elecchartcoreJNI.CVCFGeoObject_GetFeatureId(swigCPtr, this);
	}

	public void SetGeometryDirectly(CVCFGeometry arg0) {
		elecchartcoreJNI.CVCFGeoObject_SetGeometryDirectly(swigCPtr, this,
				CVCFGeometry.getCPtr(arg0), arg0);
	}

	public CVCFGeometry GetGeometryRef() {
		long cPtr = elecchartcoreJNI.CVCFGeoObject_GetGeometryRef(swigCPtr,
				this);
		return (cPtr == 0) ? null : new CVCFGeometry(cPtr, false);
	}

	public void AddAttriField(int nAttriCode, int nValue) {
		elecchartcoreJNI.CVCFGeoObject_AddAttriField__SWIG_0(swigCPtr, this,
				nAttriCode, nValue);
	}

	public void AddAttriField(int nAttriCode, double dValue) {
		elecchartcoreJNI.CVCFGeoObject_AddAttriField__SWIG_1(swigCPtr, this,
				nAttriCode, dValue);
	}

	public void AddAttriField(int nAttriCode, String pszString) {
		elecchartcoreJNI.CVCFGeoObject_AddAttriField__SWIG_2(swigCPtr, this,
				nAttriCode, pszString);
	}

	public int GetAttriFieldNums() {
		return elecchartcoreJNI.CVCFGeoObject_GetAttriFieldNums(swigCPtr, this);
	}

	public CVCFAttrField GetAttriField(int i) {
		long cPtr = elecchartcoreJNI.CVCFGeoObject_GetAttriField(swigCPtr,
				this, i);
		return (cPtr == 0) ? null : new CVCFAttrField(cPtr, false);
	}

	public CVCFAttrField GetAttriFieldByCode(int nCode) {
		long cPtr = elecchartcoreJNI.CVCFGeoObject_GetAttriFieldByCode(
				swigCPtr, this, nCode);
		return (cPtr == 0) ? null : new CVCFAttrField(cPtr, false);
	}

	public int GetAttriFieldIntergerByCode(int nCode) {
		return elecchartcoreJNI.CVCFGeoObject_GetAttriFieldIntergerByCode(
				swigCPtr, this, nCode);
	}

	public double GetAttriFieldDoubleByCode(int nCode) {
		return elecchartcoreJNI.CVCFGeoObject_GetAttriFieldDoubleByCode(
				swigCPtr, this, nCode);
	}

	public String GetAttriFieldStringByCode(int nCode) {
		return elecchartcoreJNI.CVCFGeoObject_GetAttriFieldStringByCode(
				swigCPtr, this, nCode);
	}

	public void GetEnvelope(stGEOENVELOPE stGeoEnvelope) {
		elecchartcoreJNI.CVCFGeoObject_GetEnvelope(swigCPtr, this,
				stGEOENVELOPE.getCPtr(stGeoEnvelope), stGeoEnvelope);
	}

	public void SetUpdateFlag(int nFlag) {
		elecchartcoreJNI.CVCFGeoObject_SetUpdateFlag(swigCPtr, this, nFlag);
	}

	public int GetUpdateFlag() {
		return elecchartcoreJNI.CVCFGeoObject_GetUpdateFlag(swigCPtr, this);
	}

	public void SetUpdateIndex(int nIndex) {
		elecchartcoreJNI.CVCFGeoObject_SetUpdateIndex(swigCPtr, this, nIndex);
	}

	public int GetUpdateIndex() {
		return elecchartcoreJNI.CVCFGeoObject_GetUpdateIndex(swigCPtr, this);
	}

	public boolean ScrPointSelectAt(CPoint pt, stGEOOBJPICKINDEX pkIndex) {
		return elecchartcoreJNI.CVCFGeoObject_ScrPointSelectAt(swigCPtr, this,
				CPoint.getCPtr(pt), pt, stGEOOBJPICKINDEX.getCPtr(pkIndex),
				pkIndex);
	}

	public void setSelect(boolean bSel) {
		elecchartcoreJNI.CVCFGeoObject_setSelect(swigCPtr, this, bSel);
	}

	public boolean getSelect() {
		return elecchartcoreJNI.CVCFGeoObject_getSelect(swigCPtr, this);
	}

}
