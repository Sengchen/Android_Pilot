/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stBODY_UAIS_MESSAGE_13 {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stBODY_UAIS_MESSAGE_13(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stBODY_UAIS_MESSAGE_13 obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stBODY_UAIS_MESSAGE_13(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setId(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_id_set(swigCPtr, this, value);
	}

	public int getId() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_id_get(swigCPtr, this);
	}

	public void setRepeat(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_repeat_set(swigCPtr, this,
				value);
	}

	public int getRepeat() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_repeat_get(swigCPtr,
				this);
	}

	public void setMmsi(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_mmsi_set(swigCPtr, this, value);
	}

	public int getMmsi() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_mmsi_get(swigCPtr, this);
	}

	public void setTmmsi1(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_tmmsi1_set(swigCPtr, this,
				value);
	}

	public int getTmmsi1() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_tmmsi1_get(swigCPtr,
				this);
	}

	public void setSerialNo1(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_serialNo1_set(swigCPtr, this,
				value);
	}

	public int getSerialNo1() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_serialNo1_get(swigCPtr,
				this);
	}

	public void setTmmsi2(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_tmmsi2_set(swigCPtr, this,
				value);
	}

	public int getTmmsi2() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_tmmsi2_get(swigCPtr,
				this);
	}

	public void setSerialNo2(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_serialNo2_set(swigCPtr, this,
				value);
	}

	public int getSerialNo2() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_serialNo2_get(swigCPtr,
				this);
	}

	public void setTmmsi3(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_tmmsi3_set(swigCPtr, this,
				value);
	}

	public int getTmmsi3() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_tmmsi3_get(swigCPtr,
				this);
	}

	public void setSerialNo3(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_serialNo3_set(swigCPtr, this,
				value);
	}

	public int getSerialNo3() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_serialNo3_get(swigCPtr,
				this);
	}

	public void setTmmsi4(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_tmmsi4_set(swigCPtr, this,
				value);
	}

	public int getTmmsi4() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_tmmsi4_get(swigCPtr,
				this);
	}

	public void setSerialNo4(int value) {
		elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_serialNo4_set(swigCPtr, this,
				value);
	}

	public int getSerialNo4() {
		return elecchartcoreJNI.stBODY_UAIS_MESSAGE_13_serialNo4_get(swigCPtr,
				this);
	}

	public stBODY_UAIS_MESSAGE_13() {
		this(elecchartcoreJNI.new_stBODY_UAIS_MESSAGE_13(), true);
	}

}
