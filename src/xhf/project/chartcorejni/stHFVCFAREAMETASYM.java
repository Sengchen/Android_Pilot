/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stHFVCFAREAMETASYM {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stHFVCFAREAMETASYM(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stHFVCFAREAMETASYM obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stHFVCFAREAMETASYM(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setNSymMetaId(int value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_nSymMetaId_set(swigCPtr, this,
				value);
	}

	public int getNSymMetaId() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_nSymMetaId_get(swigCPtr,
				this);
	}

	public void setNFilltype(int value) {
		elecchartcoreJNI
				.stHFVCFAREAMETASYM_nFilltype_set(swigCPtr, this, value);
	}

	public int getNFilltype() {
		return elecchartcoreJNI
				.stHFVCFAREAMETASYM_nFilltype_get(swigCPtr, this);
	}

	public void setDSepaX(double value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_dSepaX_set(swigCPtr, this, value);
	}

	public double getDSepaX() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_dSepaX_get(swigCPtr, this);
	}

	public void setDSepaY(double value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_dSepaY_set(swigCPtr, this, value);
	}

	public double getDSepaY() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_dSepaY_get(swigCPtr, this);
	}

	public void setSzFontName(String value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_szFontName_set(swigCPtr, this,
				value);
	}

	public String getSzFontName() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_szFontName_get(swigCPtr,
				this);
	}

	public void setNSymMeta(int value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_nSymMeta_set(swigCPtr, this, value);
	}

	public int getNSymMeta() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_nSymMeta_get(swigCPtr, this);
	}

	public void setDSymHeight(double value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_dSymHeight_set(swigCPtr, this,
				value);
	}

	public double getDSymHeight() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_dSymHeight_get(swigCPtr,
				this);
	}

	public void setDSymWidth(double value) {
		elecchartcoreJNI
				.stHFVCFAREAMETASYM_dSymWidth_set(swigCPtr, this, value);
	}

	public double getDSymWidth() {
		return elecchartcoreJNI
				.stHFVCFAREAMETASYM_dSymWidth_get(swigCPtr, this);
	}

	public void setDSymOffsetX(double value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_dSymOffsetX_set(swigCPtr, this,
				value);
	}

	public double getDSymOffsetX() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_dSymOffsetX_get(swigCPtr,
				this);
	}

	public void setDSymOffsetY(double value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_dSymOffsetY_set(swigCPtr, this,
				value);
	}

	public double getDSymOffsetY() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_dSymOffsetY_get(swigCPtr,
				this);
	}

	public void setNSymAngle(int value) {
		elecchartcoreJNI
				.stHFVCFAREAMETASYM_nSymAngle_set(swigCPtr, this, value);
	}

	public int getNSymAngle() {
		return elecchartcoreJNI
				.stHFVCFAREAMETASYM_nSymAngle_get(swigCPtr, this);
	}

	public void setNSymLinePater(int value) {
		elecchartcoreJNI.stHFVCFAREAMETASYM_nSymLinePater_set(swigCPtr, this,
				value);
	}

	public int getNSymLinePater() {
		return elecchartcoreJNI.stHFVCFAREAMETASYM_nSymLinePater_get(swigCPtr,
				this);
	}

	public void setNSymColor(long value) {
		elecchartcoreJNI
				.stHFVCFAREAMETASYM_nSymColor_set(swigCPtr, this, value);
	}

	public long getNSymColor() {
		return elecchartcoreJNI
				.stHFVCFAREAMETASYM_nSymColor_get(swigCPtr, this);
	}

	public stHFVCFAREAMETASYM() {
		this(elecchartcoreJNI.new_stHFVCFAREAMETASYM(), true);
	}

}