package xhf.project.sensorctl;

import android.util.Log;
import xhf.project.chartcore.ChartCoreView;
import xhf.project.chartcorejni.CHfSensorDataFlow;
import xhf.project.chartcorejni.stBODY_LOGO_HCHDT;
import xhf.project.chartcorejni.stBODY_NEMA_GPGGA;
import xhf.project.chartcorejni.stBODY_NEMA_GPRMC;
import xhf.project.chartcorejni.stBODY_UAIS_MESSAGE_01;
import xhf.project.chartcorejni.stBODY_UAIS_MESSAGE_05;
import xhf.project.chartcorejni.stBODY_UAIS_MESSAGE_18;
import xhf.project.chartcorejni.stBODY_UAIS_MESSAGE_19;
import xhf.project.chartcorejni.stBODY_UAIS_MESSAGE_21;
import xhf.project.utilitytool.COleDateTime;

public class CSensorDataFlowParase extends CHfSensorDataFlow {

	public CSensorDataFlowParase() {

	}

	@Override
	public void GetGpsGPGGAInfo(stBODY_NEMA_GPGGA stGGA) {

		// Log.v("GGA","stGGA");
		ChartCoreView.m_dynaTargetControl.SetPilotShipPosStateInfo(
				(byte) stGGA.getSateCount(), (byte) stGGA.getGpsStatus());

	}

	@Override
	public void GetGpsGPRMCInfo(stBODY_NEMA_GPRMC stRMC) {

		double fLong = stRMC.getLongitude();
		double fLat = stRMC.getLatitude();
		double fCourse = stRMC.getDirect();
		double fSpeed = stRMC.getVolity();
		char posValid = stRMC.getPosvalid();

		String strLog = Double.toString(fLong);
		strLog += ",";
		strLog += Double.toString(fLat);
		strLog += ",";
		strLog += Double.toString(fCourse);
		strLog += ",";
		strLog += Double.toString(fSpeed);
		strLog += ",";

		// Log.v("RMC",strLog);

		COleDateTime oleTime = COleDateTime.GetCurrentTime();

		oleTime.SetDateTime(stRMC.getDate().getYear(), stRMC.getDate()
				.getMonth(), stRMC.getDate().getDay(), stRMC.getTime()
				.getHour(), stRMC.getTime().getMinute(), stRMC.getTime()
				.getSecond());

		oleTime.TimeAddHours(8);

		// stRMC.time.hour,stRMC.time.minute,stRMC.time.second);
		ChartCoreView.m_dynaTargetControl.SetPilotShipPositionInfo(fLong, fLat,fCourse, fSpeed, oleTime, (byte) posValid);

	}

	@Override
	public void GetAisVDMMSG01Info(stBODY_UAIS_MESSAGE_01 stMsg01) {

		double fLong = (double) (stMsg01.getLongitude()) / 600000.0;
		double fLat = (double) (stMsg01.getLatitude()) / 600000.0;
		double fCourse = (double) (stMsg01.getCog()) / 10.0;
		double fHead = (double) (stMsg01.getCourse());
		int nRot = stMsg01.getRot();

		double rot = Math.sqrt(Math.abs((double) (nRot))) * 4.733;
		rot *= rot;
		if (nRot >= 127 || nRot <= -127) {
			rot = 999.9;
		} else {
			if (nRot < 0) {
				rot = -rot;
			}
		}

		double fSog = (double) (stMsg01.getSog()) / 10.0;
		if (fSog > 102.2)
			fSog = 999.9;

		COleDateTime obtime = COleDateTime.GetCurrentTime();
		ChartCoreView.m_dynaTargetControl.SetAisDynamicInfo(
				(long) stMsg01.getMmsi(), (byte) stMsg01.getRaim(),
				stMsg01.getPrecise(), fLong, fLat, (float) fSog,
				(float) fCourse, (short) 1, (float) fHead, rot,
				stMsg01.getNavState(), obtime);

	}

	@Override
	public void GetAisVDMMSG05Info(stBODY_UAIS_MESSAGE_05 stMsg05) {

		double fSeaGauge = (double) (stMsg05.getDraught()) / 10.0;

		COleDateTime oleETA = COleDateTime.GetCurrentTime();

		// ETA
		int nuM = stMsg05.getUtcMonth();
		int nuD = stMsg05.getUtcDay();
		int nuH = stMsg05.getUtcHour();
		int nuMi = stMsg05.getUtcMintue();

		if (nuM > 12 || nuM <= 0 || nuD > 31 || nuD <= 0 || nuH > 24
				|| nuMi > 60) {
			oleETA.SetDateTime(2000, 1, 1, 0, 0, 0);
		} else {
			oleETA.SetDateTime(oleETA.GetYear(), nuM, nuD, nuH, nuH, 0);
		}

		ChartCoreView.m_dynaTargetControl.SetAisStaticInfo(
				(long) stMsg05.getMmsi(), (long) stMsg05.getImo(),
				stMsg05.getCallNo(), stMsg05.getName(), stMsg05.getTxHead(),
				stMsg05.getTxTail(), stMsg05.getTxLeft(), stMsg05.getTxRight(),
				stMsg05.getDeviceType(), (float) fSeaGauge,
				stMsg05.getShipType(), stMsg05.getDes(), oleETA,
				(byte) stMsg05.getDte());
	}

	@Override
	public void GetAisVDMMSG18Info(stBODY_UAIS_MESSAGE_18 stMsg18) {

		double fCourse = (double) (stMsg18.getCog()) / 10.0;

		double fSog = (double) (stMsg18.getSog()) / 10.0;

		if (fSog > 102.2) {
			fSog = 999.9;
		}

		double fLat = (double) (stMsg18.getLatitude()) / 600000.0;

		double fLong = (double) (stMsg18.getLongitude()) / 600000.0;

		double fHead = (double) (stMsg18.getCourse());

		COleDateTime obtime = COleDateTime.GetCurrentTime();

		ChartCoreView.m_dynaTargetControl
				.SetBAisPositionInfo((long) stMsg18.getMmsi(),
						(byte) stMsg18.getRaim(), stMsg18.getPrecise(), fLong,
						fLat, fSog, fCourse, fHead, obtime);

	}

	@Override
	public void GetAisVDMMSG19Info(stBODY_UAIS_MESSAGE_19 stMsg19) {

		double fCourse = (double) (stMsg19.getCog()) / 10.0;

		double fSog = (double) (stMsg19.getSog()) / 10.0;

		if (fSog > 102.2) {
			fSog = 999.9;
		}

		double fLat = (double) (stMsg19.getLatitude()) / 600000.0;

		double fLong = (double) (stMsg19.getLongitude()) / 600000.0;

		double fHead = (double) (stMsg19.getCourse());

		COleDateTime obtime = COleDateTime.GetCurrentTime();

		ChartCoreView.m_dynaTargetControl.SetBEAisPositionInfo(
				stMsg19.getMmsi(), (byte) stMsg19.getRaim(),
				stMsg19.getPrecise(), fLong, fLat, fSog, fCourse, fHead,
				stMsg19.getName(), stMsg19.getShipType(), stMsg19.getTxHead(),
				stMsg19.getTxTail(), stMsg19.getTxLeft(), stMsg19.getTxRight(),
				stMsg19.getDeviceType(), (byte) stMsg19.getDte(), obtime);
	}

	@Override
	public void GetAisVDMMSG21Info(stBODY_UAIS_MESSAGE_21 stMsg21) {

	}

	@Override
	public void GetAisVDOMSG01Info(stBODY_UAIS_MESSAGE_01 stMsg01) {

		double fLong = (double) (stMsg01.getLongitude()) / 600000.0;
		double fLat = (double) (stMsg01.getLatitude()) / 600000.0;
		double fCourse = (double) (stMsg01.getCog()) / 10.0;
		double fHead = (double) (stMsg01.getCourse());
		int nRot = stMsg01.getRot();

		double rot = Math.sqrt(Math.abs((double) (nRot))) * 4.733;
		rot *= rot;
		if (nRot >= 127 || nRot <= -127) {
			rot = 999.9;
		} else {
			if (nRot < 0) {
				rot = -rot;
			}
		}

		double fSog = (double) (stMsg01.getSog()) / 10.0;
		if (fSog > 102.2)
			fSog = 999.9;

		COleDateTime obtime = COleDateTime.GetCurrentTime();

		ChartCoreView.m_dynaTargetControl.SetOwnAisDynamicInfo(
				(long) stMsg01.getMmsi(), (byte) stMsg01.getRaim(),
				stMsg01.getPrecise(), fLong, fLat, (float) fSog,
				(float) fCourse, (short) 1, (float) fHead, rot,
				stMsg01.getNavState(), obtime);

	}

	@Override
	public void GetAisVDOMSG05Info(stBODY_UAIS_MESSAGE_05 stMsg05) {

		double fSeaGauge = (double) (stMsg05.getDraught()) / 10.0;

		COleDateTime oleETA = COleDateTime.GetCurrentTime();

		// ETA
		int nuM = stMsg05.getUtcMonth();
		int nuD = stMsg05.getUtcDay();
		int nuH = stMsg05.getUtcHour();
		int nuMi = stMsg05.getUtcMintue();

		if (nuM > 12 || nuM <= 0 || nuD > 31 || nuD <= 0 || nuH > 24
				|| nuMi > 60) {
			oleETA.SetDateTime(2000, 1, 1, 0, 0, 0);
		} else {
			oleETA.SetDateTime(oleETA.GetYear(), nuM, nuD, nuH, nuH, 0);
		}

		ChartCoreView.m_dynaTargetControl.SetOwnAisStaticInfo(
				(long) stMsg05.getMmsi(), (long) stMsg05.getImo(),
				stMsg05.getCallNo(), stMsg05.getName(), stMsg05.getTxHead(),
				stMsg05.getTxTail(), stMsg05.getTxLeft(), stMsg05.getTxRight(),
				stMsg05.getDeviceType(), (float) fSeaGauge,
				stMsg05.getShipType(), stMsg05.getDes(), oleETA,
				(byte) stMsg05.getDte());
	}

	@Override
	public void GetAisVDOMSG18Info(stBODY_UAIS_MESSAGE_18 stMsg18) {

		double fCourse = (double) (stMsg18.getCog()) / 10.0;

		double fSog = (double) (stMsg18.getSog()) / 10.0;

		if (fSog > 102.2) {
			fSog = 999.9;
		}

		double fLat = (double) (stMsg18.getLatitude()) / 600000.0;

		double fLong = (double) (stMsg18.getLongitude()) / 600000.0;

		double fHead = (double) (stMsg18.getCourse());

		COleDateTime obtime = COleDateTime.GetCurrentTime();

		ChartCoreView.m_dynaTargetControl
				.SetOwnBAisPositionInfo((long) stMsg18.getMmsi(),
						(byte) stMsg18.getRaim(), stMsg18.getPrecise(), fLong,
						fLat, fSog, fCourse, fHead, obtime);

	}

	@Override
	public void GetAisVDOMSG19Info(stBODY_UAIS_MESSAGE_19 stMsg19) {

		double fCourse = (double) (stMsg19.getCog()) / 10.0;

		double fSog = (double) (stMsg19.getSog()) / 10.0;

		if (fSog > 102.2) {
			fSog = 999.9;
		}

		double fLat = (double) (stMsg19.getLatitude()) / 600000.0;

		double fLong = (double) (stMsg19.getLongitude()) / 600000.0;

		double fHead = (double) (stMsg19.getCourse());

		COleDateTime obtime = COleDateTime.GetCurrentTime();

		ChartCoreView.m_dynaTargetControl.SetOwnBEAisPositionInfo(
				stMsg19.getMmsi(), (byte) stMsg19.getRaim(),
				stMsg19.getPrecise(), fLong, fLat, fSog, fCourse, fHead,
				stMsg19.getName(), stMsg19.getShipType(), stMsg19.getTxHead(),
				stMsg19.getTxTail(), stMsg19.getTxLeft(), stMsg19.getTxRight(),
				stMsg19.getDeviceType(), (byte) stMsg19.getDte(), obtime);
	}

	@Override
	public void GetHdtHCHDTInfo(stBODY_LOGO_HCHDT stHDT) {

	}

	@Override
	public void GetAISGPRMCInfo(stBODY_NEMA_GPRMC stRMC) {

		double fLong = stRMC.getLongitude();
		double fLat = stRMC.getLatitude();
		double fCourse = stRMC.getDirect();
		double fSpeed = stRMC.getVolity();
		char posValid = stRMC.getPosvalid();

		COleDateTime oleTime = COleDateTime.GetCurrentTime();

		oleTime.SetDateTime(stRMC.getDate().getYear(), stRMC.getDate()
				.getMonth(), stRMC.getDate().getDay(), stRMC.getTime()
				.getHour(), stRMC.getTime().getMinute(), stRMC.getTime()
				.getSecond());

		oleTime.TimeAddHours(8);

		ChartCoreView.m_dynaTargetControl.SetAISPilotShipPositionInfo(fLong,
				fLat, fCourse, fSpeed, oleTime, (byte) posValid);
	}

}
