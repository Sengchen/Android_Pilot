/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stVCFGEOOBJECTRECHEADER {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stVCFGEOOBJECTRECHEADER(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stVCFGEOOBJECTRECHEADER obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stVCFGEOOBJECTRECHEADER(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setNLayerCode(int value) {
		elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nLayerCode_set(swigCPtr, this,
				value);
	}

	public int getNLayerCode() {
		return elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nLayerCode_get(
				swigCPtr, this);
	}

	public void setNGeoLayer(int value) {
		elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nGeoLayer_set(swigCPtr, this,
				value);
	}

	public int getNGeoLayer() {
		return elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nGeoLayer_get(swigCPtr,
				this);
	}

	public void setNPrim(int value) {
		elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nPrim_set(swigCPtr, this,
				value);
	}

	public int getNPrim() {
		return elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nPrim_get(swigCPtr,
				this);
	}

	public void setLObjectFC(int value) {
		elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_lObjectFC_set(swigCPtr, this,
				value);
	}

	public int getLObjectFC() {
		return elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_lObjectFC_get(swigCPtr,
				this);
	}

	public void setNObjectId(int value) {
		elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nObjectId_set(swigCPtr, this,
				value);
	}

	public int getNObjectId() {
		return elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nObjectId_get(swigCPtr,
				this);
	}

	public void setNShapeType(int value) {
		elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nShapeType_set(swigCPtr, this,
				value);
	}

	public int getNShapeType() {
		return elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nShapeType_get(
				swigCPtr, this);
	}

	public void setNAttrsLen(int value) {
		elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nAttrsLen_set(swigCPtr, this,
				value);
	}

	public int getNAttrsLen() {
		return elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nAttrsLen_get(swigCPtr,
				this);
	}

	public void setNCoordsLen(int value) {
		elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nCoordsLen_set(swigCPtr, this,
				value);
	}

	public int getNCoordsLen() {
		return elecchartcoreJNI.stVCFGEOOBJECTRECHEADER_nCoordsLen_get(
				swigCPtr, this);
	}

	public stVCFGEOOBJECTRECHEADER() {
		this(elecchartcoreJNI.new_stVCFGEOOBJECTRECHEADER(), true);
	}

}