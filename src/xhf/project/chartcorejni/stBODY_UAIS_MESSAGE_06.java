/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stBODY_UAIS_MESSAGE_06 {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stBODY_UAIS_MESSAGE_06(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stBODY_UAIS_MESSAGE_06 obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stBODY_UAIS_MESSAGE_06(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setId(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_id_set(swigCPtr, this, value);
	}

	public int getId() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_id_get(swigCPtr, this);
	}

	public void setRepeat(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_repeat_set(swigCPtr, this,
				value);
	}

	public int getRepeat() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_repeat_get(swigCPtr,
				this);
	}

	public void setSmmsi(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_06_smmsi_set(swigCPtr, this, value);
	}

	public int getSmmsi() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_06_smmsi_get(swigCPtr, this);
	}

	public void setSerialNo(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_serialNo_set(swigCPtr, this,
				value);
	}

	public int getSerialNo() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_serialNo_get(swigCPtr,
				this);
	}

	public void setDmmsi(int value) {
		elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_06_dmmsi_set(swigCPtr, this, value);
	}

	public int getDmmsi() {
		return elecchartcoreJNI
				.stBODY_UAIS_MESSAGE_06_dmmsi_get(swigCPtr, this);
	}

	public void setRepeatFlag(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_repeatFlag_set(swigCPtr, this,
				value);
	}

	public int getRepeatFlag() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_repeatFlag_get(swigCPtr,
				this);
	}

	public void setBack(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_back_set(swigCPtr, this, value);
	}

	public int getBack() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_back_get(swigCPtr, this);
	}

	public void setDac(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_dac_set(swigCPtr, this, value);
	}

	public int getDac() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_dac_get(swigCPtr, this);
	}

	public void setFic(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_fic_set(swigCPtr, this, value);
	}

	public int getFic() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_fic_get(swigCPtr, this);
	}

	public void setMsg0(stBODY_UAIS_MESSAGE_6_SUB0 value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg0_set(swigCPtr, this,
				stBODY_UAIS_MESSAGE_6_SUB0.getCPtr(value), value);
	}

	public stBODY_UAIS_MESSAGE_6_SUB0 getMsg0() {
		long cPtr = elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg0_get(swigCPtr,
				this);
		return (cPtr == 0) ? null : new stBODY_UAIS_MESSAGE_6_SUB0(cPtr, false);
	}

	public void setMsg1(stBODY_UAIS_MESSAGE_6_SUB1 value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg1_set(swigCPtr, this,
				stBODY_UAIS_MESSAGE_6_SUB1.getCPtr(value), value);
	}

	public stBODY_UAIS_MESSAGE_6_SUB1 getMsg1() {
		long cPtr = elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg1_get(swigCPtr,
				this);
		return (cPtr == 0) ? null : new stBODY_UAIS_MESSAGE_6_SUB1(cPtr, false);
	}

	public void setMsg2(stBODY_UAIS_MESSAGE_6_SUB2 value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg2_set(swigCPtr, this,
				stBODY_UAIS_MESSAGE_6_SUB2.getCPtr(value), value);
	}

	public stBODY_UAIS_MESSAGE_6_SUB2 getMsg2() {
		long cPtr = elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg2_get(swigCPtr,
				this);
		return (cPtr == 0) ? null : new stBODY_UAIS_MESSAGE_6_SUB2(cPtr, false);
	}

	public void setMsg3(stBODY_UAIS_MESSAGE_6_SUB3 value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg3_set(swigCPtr, this,
				stBODY_UAIS_MESSAGE_6_SUB3.getCPtr(value), value);
	}

	public stBODY_UAIS_MESSAGE_6_SUB3 getMsg3() {
		long cPtr = elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg3_get(swigCPtr,
				this);
		return (cPtr == 0) ? null : new stBODY_UAIS_MESSAGE_6_SUB3(cPtr, false);
	}

	public void setMsg4(stBODY_UAIS_MESSAGE_6_SUB4 value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg4_set(swigCPtr, this,
				stBODY_UAIS_MESSAGE_6_SUB4.getCPtr(value), value);
	}

	public stBODY_UAIS_MESSAGE_6_SUB4 getMsg4() {
		long cPtr = elecchartcoreJNI.stBODY_UAIS_MESSAGE_06_msg4_get(swigCPtr,
				this);
		return (cPtr == 0) ? null : new stBODY_UAIS_MESSAGE_6_SUB4(cPtr, false);
	}

	public stBODY_UAIS_MESSAGE_06() {
		this(elecchartcoreJNI.new_stBODY_UAIS_MESSAGE_06(), true);
	}

}
