/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stBODY_UAIS_MESSAGE_18 {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stBODY_UAIS_MESSAGE_18(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stBODY_UAIS_MESSAGE_18 obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stBODY_UAIS_MESSAGE_18(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setId(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_id_set(swigCPtr, this, value);
	}

	public int getId() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_id_get(swigCPtr, this);
	}

	public void setRepeat(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_repeat_set(swigCPtr, this,
				value);
	}

	public int getRepeat() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_repeat_get(swigCPtr,
				this);
	}

	public void setMmsi(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_mmsi_set(swigCPtr, this, value);
	}

	public int getMmsi() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_mmsi_get(swigCPtr, this);
	}

	public void setReserve(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_reserve_set(swigCPtr, this,
				value);
	}

	public int getReserve() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_reserve_get(swigCPtr,
				this);
	}

	public void setSog(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_sog_set(swigCPtr, this, value);
	}

	public int getSog() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_sog_get(swigCPtr, this);
	}

	public void setPrecise(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_precise_set(swigCPtr, this,
				value);
	}

	public int getPrecise() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_precise_get(swigCPtr,
				this);
	}

	public void setLongitude(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_longitude_set(swigCPtr, this,
				value);
	}

	public int getLongitude() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_longitude_get(swigCPtr,
				this);
	}

	public void setLatitude(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_latitude_set(swigCPtr, this,
				value);
	}

	public int getLatitude() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_latitude_get(swigCPtr,
				this);
	}

	public void setCog(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_cog_set(swigCPtr, this, value);
	}

	public int getCog() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_cog_get(swigCPtr, this);
	}

	public void setCourse(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_course_set(swigCPtr, this,
				value);
	}

	public int getCourse() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_course_get(swigCPtr,
				this);
	}

	public void setUtcSec(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_utcSec_set(swigCPtr, this,
				value);
	}

	public int getUtcSec() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_utcSec_get(swigCPtr,
				this);
	}

	public void setBack(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_back_set(swigCPtr, this, value);
	}

	public int getBack() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_back_get(swigCPtr, this);
	}

	public void setModeFlag(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_modeFlag_set(swigCPtr, this,
				value);
	}

	public int getModeFlag() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_modeFlag_get(swigCPtr,
				this);
	}

	public void setRaim(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_raim_set(swigCPtr, this, value);
	}

	public int getRaim() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_raim_get(swigCPtr, this);
	}

	public void setComFlag(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_comFlag_set(swigCPtr, this,
				value);
	}

	public int getComFlag() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_comFlag_get(swigCPtr,
				this);
	}

	public void setComState(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_comState_set(swigCPtr, this,
				value);
	}

	public int getComState() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_18_comState_get(swigCPtr,
				this);
	}

	public stBODY_UAIS_MESSAGE_18() {
		this(elecchartcoreJNI.new_stBODY_UAIS_MESSAGE_18(), true);
	}

}