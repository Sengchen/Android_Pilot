/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 2.0.9
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package xhf.project.chartcorejni;

public class stGPSDATA_PACK {
	private long swigCPtr;
	protected boolean swigCMemOwn;

	protected stGPSDATA_PACK(long cPtr, boolean cMemoryOwn) {
		swigCMemOwn = cMemoryOwn;
		swigCPtr = cPtr;
	}

	protected static long getCPtr(stGPSDATA_PACK obj) {
		return (obj == null) ? 0 : obj.swigCPtr;
	}

	protected void finalize() {
		delete();
	}

	public synchronized void delete() {
		if (swigCPtr != 0) {
			if (swigCMemOwn) {
				swigCMemOwn = false;
				elecchartcoreJNI.delete_stGPSDATA_PACK(swigCPtr);
			}
			swigCPtr = 0;
		}
	}

	public void setGpGGA(stBODY_NEMA_GPGGA value) {
		elecchartcoreJNI.stGPSDATA_PACK_gpGGA_set(swigCPtr, this,
				stBODY_NEMA_GPGGA.getCPtr(value), value);
	}

	public stBODY_NEMA_GPGGA getGpGGA() {
		long cPtr = elecchartcoreJNI.stGPSDATA_PACK_gpGGA_get(swigCPtr, this);
		return (cPtr == 0) ? null : new stBODY_NEMA_GPGGA(cPtr, false);
	}

	public void setGpRMC(stBODY_NEMA_GPRMC value) {
		elecchartcoreJNI.stGPSDATA_PACK_gpRMC_set(swigCPtr, this,
				stBODY_NEMA_GPRMC.getCPtr(value), value);
	}

	public stBODY_NEMA_GPRMC getGpRMC() {
		long cPtr = elecchartcoreJNI.stGPSDATA_PACK_gpRMC_get(swigCPtr, this);
		return (cPtr == 0) ? null : new stBODY_NEMA_GPRMC(cPtr, false);
	}

	public stGPSDATA_PACK() {
		this(elecchartcoreJNI.new_stGPSDATA_PACK(), true);
	}

}
