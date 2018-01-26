/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stBODY_NEMA_GPRMC {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stBODY_NEMA_GPRMC(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stBODY_NEMA_GPRMC obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stBODY_NEMA_GPRMC(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setTime(stUTC_TIME value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_time_set(swigCPtr, this,
				stUTC_TIME.getCPtr(value), value);
	}

	public stUTC_TIME getTime() {
		long cPtr = elecchartcoreJNI.stBODY_NEMA_GPRMC_time_get(swigCPtr, this);
		return (cPtr == 0) ? null : new stUTC_TIME(cPtr, false);
	}

	public void setPosvalid(char value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_posvalid_set(swigCPtr, this, value);
	}

	public char getPosvalid() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_posvalid_get(swigCPtr, this);
	}

	public void setLatitude(double value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_latitude_set(swigCPtr, this, value);
	}

	public double getLatitude() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_latitude_get(swigCPtr, this);
	}

	public void setLatitudeType(char value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_latitudeType_set(swigCPtr, this,
				value);
	}

	public char getLatitudeType() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_latitudeType_get(swigCPtr,
				this);
	}

	public void setLongitude(double value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_longitude_set(swigCPtr, this, value);
	}

	public double getLongitude() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_longitude_get(swigCPtr, this);
	}

	public void setLongitudeType(char value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_longitudeType_set(swigCPtr, this,
				value);
	}

	public char getLongitudeType() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_longitudeType_get(swigCPtr,
				this);
	}

	public void setVolity(double value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_volity_set(swigCPtr, this, value);
	}

	public double getVolity() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_volity_get(swigCPtr, this);
	}

	public void setDirect(double value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_direct_set(swigCPtr, this, value);
	}

	public double getDirect() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_direct_get(swigCPtr, this);
	}

	public void setDate(stUTC_DATE value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_date_set(swigCPtr, this,
				stUTC_DATE.getCPtr(value), value);
	}

	public stUTC_DATE getDate() {
		long cPtr = elecchartcoreJNI.stBODY_NEMA_GPRMC_date_get(swigCPtr, this);
		return (cPtr == 0) ? null : new stUTC_DATE(cPtr, false);
	}

	public void setCpj(double value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_cpj_set(swigCPtr, this, value);
	}

	public double getCpj() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_cpj_get(swigCPtr, this);
	}

	public void setCpjdirect(char value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_cpjdirect_set(swigCPtr, this, value);
	}

	public char getCpjdirect() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_cpjdirect_get(swigCPtr, this);
	}

	public void setMode(char value) {
		elecchartcoreJNI.stBODY_NEMA_GPRMC_mode_set(swigCPtr, this, value);
	}

	public char getMode() {
		return elecchartcoreJNI.stBODY_NEMA_GPRMC_mode_get(swigCPtr, this);
	}

	public stBODY_NEMA_GPRMC() {
		this(elecchartcoreJNI.new_stBODY_NEMA_GPRMC(), true);
	}

}
