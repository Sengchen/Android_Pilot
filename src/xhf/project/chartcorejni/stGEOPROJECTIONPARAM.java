/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stGEOPROJECTIONPARAM {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stGEOPROJECTIONPARAM(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stGEOPROJECTIONPARAM obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stGEOPROJECTIONPARAM(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setDMapScale(double value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_dMapScale_set(swigCPtr, this,
				value);
	}

	public double getDMapScale() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_dMapScale_get(swigCPtr,
				this);
	}

	public void setDgpCenLong(double value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_dgpCenLong_set(swigCPtr, this,
				value);
	}

	public double getDgpCenLong() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_dgpCenLong_get(swigCPtr,
				this);
	}

	public void setDgpCenLat(double value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_dgpCenLat_set(swigCPtr, this,
				value);
	}

	public double getDgpCenLat() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_dgpCenLat_get(swigCPtr,
				this);
	}

	public void setDMeridianLat(double value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_dMeridianLat_set(swigCPtr, this,
				value);
	}

	public double getDMeridianLat() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_dMeridianLat_get(swigCPtr,
				this);
	}

	public void setLPlanOrgX(int value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_lPlanOrgX_set(swigCPtr, this,
				value);
	}

	public int getLPlanOrgX() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_lPlanOrgX_get(swigCPtr,
				this);
	}

	public void setLPlanOrgY(int value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_lPlanOrgY_set(swigCPtr, this,
				value);
	}

	public int getLPlanOrgY() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_lPlanOrgY_get(swigCPtr,
				this);
	}

	public void setXSizeMM(int value) {
		elecchartcoreJNI
				.stGEOPROJECTIONPARAM_xSizeMM_set(swigCPtr, this, value);
	}

	public int getXSizeMM() {
		return elecchartcoreJNI
				.stGEOPROJECTIONPARAM_xSizeMM_get(swigCPtr, this);
	}

	public void setYSizeMM(int value) {
		elecchartcoreJNI
				.stGEOPROJECTIONPARAM_ySizeMM_set(swigCPtr, this, value);
	}

	public int getYSizeMM() {
		return elecchartcoreJNI
				.stGEOPROJECTIONPARAM_ySizeMM_get(swigCPtr, this);
	}

	public void setRcViewRect(CRect value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_rcViewRect_set(swigCPtr, this,
				CRect.getCPtr(value), value);
	}

	public CRect getRcViewRect() {
		long cPtr = elecchartcoreJNI.stGEOPROJECTIONPARAM_rcViewRect_get(
				swigCPtr, this);
		return (cPtr == 0) ? null : new CRect(cPtr, false);
	}

	public void setDxResolutionDPM(double value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_dxResolutionDPM_set(swigCPtr,
				this, value);
	}

	public double getDxResolutionDPM() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_dxResolutionDPM_get(
				swigCPtr, this);
	}

	public void setDyResolutionDPM(double value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_dyResolutionDPM_set(swigCPtr,
				this, value);
	}

	public double getDyResolutionDPM() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_dyResolutionDPM_get(
				swigCPtr, this);
	}

	public void setDViewScale(double value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_dViewScale_set(swigCPtr, this,
				value);
	}

	public double getDViewScale() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_dViewScale_get(swigCPtr,
				this);
	}

	public void setDRotateAngle(double value) {
		elecchartcoreJNI.stGEOPROJECTIONPARAM_dRotateAngle_set(swigCPtr, this,
				value);
	}

	public double getDRotateAngle() {
		return elecchartcoreJNI.stGEOPROJECTIONPARAM_dRotateAngle_get(swigCPtr,
				this);
	}

	public stGEOPROJECTIONPARAM() {
		this(elecchartcoreJNI.new_stGEOPROJECTIONPARAM(), true);
	}

}
