/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stHFVCFLINEMETASYM {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stHFVCFLINEMETASYM(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stHFVCFLINEMETASYM obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stHFVCFLINEMETASYM(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setNSymMetaId(int value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_nSymMetaId_set(swigCPtr, this,
				value);
	}

	public int getNSymMetaId() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_nSymMetaId_get(swigCPtr,
				this);
	}

	public void setSzFontName(String value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_szFontName_set(swigCPtr, this,
				value);
	}

	public String getSzFontName() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_szFontName_get(swigCPtr,
				this);
	}

	public void setNSymMeta(int value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_nSymMeta_set(swigCPtr, this, value);
	}

	public int getNSymMeta() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_nSymMeta_get(swigCPtr, this);
	}

	public void setDSymHeight(double value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_dSymHeight_set(swigCPtr, this,
				value);
	}

	public double getDSymHeight() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_dSymHeight_get(swigCPtr,
				this);
	}

	public void setDSymWidth(double value) {
		elecchartcoreJNI
				.stHFVCFLINEMETASYM_dSymWidth_set(swigCPtr, this, value);
	}

	public double getDSymWidth() {
		return elecchartcoreJNI
				.stHFVCFLINEMETASYM_dSymWidth_get(swigCPtr, this);
	}

	public void setDSymOffset(double value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_dSymOffset_set(swigCPtr, this,
				value);
	}

	public double getDSymOffset() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_dSymOffset_get(swigCPtr,
				this);
	}

	public void setNSymAngle(int value) {
		elecchartcoreJNI
				.stHFVCFLINEMETASYM_nSymAngle_set(swigCPtr, this, value);
	}

	public int getNSymAngle() {
		return elecchartcoreJNI
				.stHFVCFLINEMETASYM_nSymAngle_get(swigCPtr, this);
	}

	public void setNSymCloseAngle(int value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_nSymCloseAngle_set(swigCPtr, this,
				value);
	}

	public int getNSymCloseAngle() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_nSymCloseAngle_get(swigCPtr,
				this);
	}

	public void setNSymMiterAngle(int value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_nSymMiterAngle_set(swigCPtr, this,
				value);
	}

	public int getNSymMiterAngle() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_nSymMiterAngle_get(swigCPtr,
				this);
	}

	public void setDSymAdjustMent(double value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_dSymAdjustMent_set(swigCPtr, this,
				value);
	}

	public double getDSymAdjustMent() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_dSymAdjustMent_get(swigCPtr,
				this);
	}

	public void setNSymColor(long value) {
		elecchartcoreJNI
				.stHFVCFLINEMETASYM_nSymColor_set(swigCPtr, this, value);
	}

	public long getNSymColor() {
		return elecchartcoreJNI
				.stHFVCFLINEMETASYM_nSymColor_get(swigCPtr, this);
	}

	public void setNSymLneType(int value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_nSymLneType_set(swigCPtr, this,
				value);
	}

	public int getNSymLneType() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_nSymLneType_get(swigCPtr,
				this);
	}

	public void setDSymSnap(double value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_dSymSnap_set(swigCPtr, this, value);
	}

	public double getDSymSnap() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_dSymSnap_get(swigCPtr, this);
	}

	public void setSzMask(String value) {
		elecchartcoreJNI.stHFVCFLINEMETASYM_szMask_set(swigCPtr, this, value);
	}

	public String getSzMask() {
		return elecchartcoreJNI.stHFVCFLINEMETASYM_szMask_get(swigCPtr, this);
	}

	public stHFVCFLINEMETASYM() {
		this(elecchartcoreJNI.new_stHFVCFLINEMETASYM(), true);
	}

}
