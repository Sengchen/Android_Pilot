/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stBODY_UAIS_MESSAGE_22 {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stBODY_UAIS_MESSAGE_22(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stBODY_UAIS_MESSAGE_22 obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stBODY_UAIS_MESSAGE_22(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setId(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_id_set(swigCPtr, this, value);
	}

	public int getId() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_id_get(swigCPtr, this);
	}

	public void setRepeat(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_repeat_set(swigCPtr, this,
				value);
	}

	public int getRepeat() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_repeat_get(swigCPtr,
				this);
	}

	public void setMmsi(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_mmsi_set(swigCPtr, this, value);
	}

	public int getMmsi() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_mmsi_get(swigCPtr, this);
	}

	public void setBack(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_back_set(swigCPtr, this, value);
	}

	public int getBack() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_back_get(swigCPtr, this);
	}

	public void setChannelA(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_channelA_set(swigCPtr, this,
				value);
	}

	public int getChannelA() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_channelA_get(swigCPtr,
				this);
	}

	public void setChannelB(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_channelB_set(swigCPtr, this,
				value);
	}

	public int getChannelB() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_channelB_get(swigCPtr,
				this);
	}

	public void setTransMode(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_transMode_set(swigCPtr, this,
				value);
	}

	public int getTransMode() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_transMode_get(swigCPtr,
				this);
	}

	public void setPower(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_22_power_set(swigCPtr, this, value);
	}

	public int getPower() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_22_power_get(swigCPtr, this);
	}

	public void setLongitude1(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_longitude1_set(swigCPtr, this,
				value);
	}

	public int getLongitude1() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_longitude1_get(swigCPtr,
				this);
	}

	public void setLatitude1(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_latitude1_set(swigCPtr, this,
				value);
	}

	public int getLatitude1() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_latitude1_get(swigCPtr,
				this);
	}

	public void setLongitude2(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_longitude2_set(swigCPtr, this,
				value);
	}

	public int getLongitude2() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_longitude2_get(swigCPtr,
				this);
	}

	public void setLatitude2(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_latitude2_set(swigCPtr, this,
				value);
	}

	public int getLatitude2() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_latitude2_get(swigCPtr,
				this);
	}

	public void setAddressFlag(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_addressFlag_set(swigCPtr, this,
				value);
	}

	public int getAddressFlag() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_addressFlag_get(
				swigCPtr, this);
	}

	public void setBandA(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_22_bandA_set(swigCPtr, this, value);
	}

	public int getBandA() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_22_bandA_get(swigCPtr, this);
	}

	public void setBandB(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_22_bandB_set(swigCPtr, this, value);
	}

	public int getBandB() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_22_bandB_get(swigCPtr, this);
	}

	public void setAreaSize(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_areaSize_set(swigCPtr, this,
				value);
	}

	public int getAreaSize() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_22_areaSize_get(swigCPtr,
				this);
	}

	public void setLback(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_22_lback_set(swigCPtr, this, value);
	}

	public int getLback() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_22_lback_get(swigCPtr, this);
	}

	public stBODY_UAIS_MESSAGE_22() {
		this(elecchartcoreJNI.new_stBODY_UAIS_MESSAGE_22(), true);
	}

}
