/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stBODY_UAIS_MESSAGE_15 {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stBODY_UAIS_MESSAGE_15(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stBODY_UAIS_MESSAGE_15 obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stBODY_UAIS_MESSAGE_15(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setId(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_id_set(swigCPtr, this, value);
	}

	public int getId() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_id_get(swigCPtr, this);
	}

	public void setRepeat(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_repeat_set(swigCPtr, this,
				value);
	}

	public int getRepeat() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_repeat_get(swigCPtr,
				this);
	}

	public void setMmsi(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_mmsi_set(swigCPtr, this, value);
	}

	public int getMmsi() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_mmsi_get(swigCPtr, this);
	}

	public void setBack1(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_15_back1_set(swigCPtr, this, value);
	}

	public int getBack1() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_15_back1_get(swigCPtr, this);
	}

	public void setTmmsi1(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_tmmsi1_set(swigCPtr, this,
				value);
	}

	public int getTmmsi1() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_tmmsi1_get(swigCPtr,
				this);
	}

	public void setT1m1Type(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t1m1Type_set(swigCPtr, this,
				value);
	}

	public int getT1m1Type() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t1m1Type_get(swigCPtr,
				this);
	}

	public void setT1m1offset(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t1m1offset_set(swigCPtr, this,
				value);
	}

	public int getT1m1offset() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t1m1offset_get(swigCPtr,
				this);
	}

	public void setBack2(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_15_back2_set(swigCPtr, this, value);
	}

	public int getBack2() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_15_back2_get(swigCPtr, this);
	}

	public void setT1m2Type(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t1m2Type_set(swigCPtr, this,
				value);
	}

	public int getT1m2Type() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t1m2Type_get(swigCPtr,
				this);
	}

	public void setT1m2offset(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t1m2offset_set(swigCPtr, this,
				value);
	}

	public int getT1m2offset() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t1m2offset_get(swigCPtr,
				this);
	}

	public void setBack3(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_15_back3_set(swigCPtr, this, value);
	}

	public int getBack3() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_15_back3_get(swigCPtr, this);
	}

	public void setTmmsi2(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_tmmsi2_set(swigCPtr, this,
				value);
	}

	public int getTmmsi2() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_tmmsi2_get(swigCPtr,
				this);
	}

	public void setT2Type(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t2Type_set(swigCPtr, this,
				value);
	}

	public int getT2Type() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t2Type_get(swigCPtr,
				this);
	}

	public void setT2offset(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t2offset_set(swigCPtr, this,
				value);
	}

	public int getT2offset() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_15_t2offset_get(swigCPtr,
				this);
	}

	public void setBack4(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_15_back4_set(swigCPtr, this, value);
	}

	public int getBack4() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_15_back4_get(swigCPtr, this);
	}

	public stBODY_UAIS_MESSAGE_15() {
		this(elecchartcoreJNI.new_stBODY_UAIS_MESSAGE_15(), true);
	}

}
