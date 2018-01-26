package xhf.project.utilitytool;

import java.util.Date;

public class COleDateTime {

	public int m_nYear = 2013;
	public int m_nMonth = 1;
	public int m_nDay = 1;
	public int m_nHour = 1;
	public int m_nMinute = 1;
	public int m_nSecond = 1;

	public COleDateTime() {

	}

	public COleDateTime(int nyear, int nmonth, int nday, int nhour, int nMin,
			int nSecond) {

		m_nYear = nyear;
		m_nMonth = nmonth;
		m_nDay = nday;
		m_nHour = nhour;
		m_nMinute = nMin;
		m_nSecond = nSecond;

	};

	public COleDateTime(COleDateTime oleTime) {

		m_nYear = oleTime.m_nYear;
		m_nMonth = oleTime.m_nMonth;
		m_nDay = oleTime.m_nDay;
		m_nHour = oleTime.m_nHour;
		m_nMinute = oleTime.m_nMinute;
		m_nSecond = oleTime.m_nSecond;

	}

	public int GetYear() {
		return m_nYear;
	}

	public int GetMonth() {
		return m_nMonth;
	}

	public int GetDay() {
		return m_nDay;
	}

	public int GetHour() {
		return m_nHour;
	}

	public int GetMinute() {
		return m_nMinute;
	}

	public int GetSecond() {
		return m_nSecond;
	}

	public void SetDateTime(int nyear, int nmonth, int nday, int nhour,
			int nMin, int nSecond) {

		m_nYear = nyear;
		m_nMonth = nmonth;
		m_nDay = nday;
		m_nHour = nhour;
		m_nMinute = nMin;
		m_nSecond = nSecond;

	}

	public String GetTimeString() {

		String strTime = String.format("%04d-%02d-%02d %02d:%02d:%02d",
				m_nYear, m_nMonth, m_nDay, m_nHour, m_nMinute, m_nSecond);
		/*
		 * strTime+=String.format("-%1$02d", m_nMonth);
		 * strTime+=String.format("-%1$02d", m_nDay);
		 * strTime+=String.format(" %1$02d", m_nHour);
		 * strTime+=String.format(":%1$02d", m_nMinute);
		 * strTime+=String.format(":%1$02d", m_nSecond);
		 */
		return strTime;

	}

	public String GetTimeHHMMSSString() {

		String strTime = String.format("%02d:%02d:%02d", m_nHour, m_nMinute,
				m_nSecond);
		/*
		 * strTime+=String.format("-%1$02d", m_nMonth);
		 * strTime+=String.format("-%1$02d", m_nDay);
		 * strTime+=String.format(" %1$02d", m_nHour);
		 * strTime+=String.format(":%1$02d", m_nMinute);
		 * strTime+=String.format(":%1$02d", m_nSecond);
		 */
		return strTime;

	}

	public static COleDateTime GetCurrentTime() {
		Date now = new Date();

		COleDateTime currtime = new COleDateTime(now.getYear(), now.getMonth(),
				now.getDay(), now.getHours(), now.getMinutes(),
				now.getSeconds());

		return currtime;

	};

	public static long GetDiffTime(COleDateTime stTime, COleDateTime edTime) {

		long lSecd1 = 0;
		long lSecd2 = 0;
		if (stTime.m_nMonth == 1 || stTime.m_nMonth == 3
				|| stTime.m_nMonth == 5 || stTime.m_nMonth == 7
				|| stTime.m_nMonth == 8 || stTime.m_nMonth == 10
				|| stTime.m_nMonth == 12) {
			lSecd1 = stTime.m_nYear * 365 * 24 * 3600 + stTime.m_nMonth * 31
					* 24 * 3600 + stTime.m_nDay * 24 * 3600 + stTime.m_nHour
					* 24 * 3600 + stTime.m_nMinute * 60 + stTime.m_nSecond;
		} else {
			lSecd1 = stTime.m_nYear * 365 * 24 * 3600 + stTime.m_nMonth * 30
					* 24 * 3600 + stTime.m_nDay * 24 * 3600 + stTime.m_nHour
					* 24 * 3600 + stTime.m_nMinute * 60 + stTime.m_nSecond;
		}

		if (edTime.m_nMonth == 1 || edTime.m_nMonth == 3
				|| edTime.m_nMonth == 5 || edTime.m_nMonth == 7
				|| edTime.m_nMonth == 8 || edTime.m_nMonth == 10
				|| edTime.m_nMonth == 12) {
			lSecd2 = edTime.m_nYear * 365 * 24 * 3600 + edTime.m_nMonth * 31
					* 24 * 3600 + edTime.m_nDay * 24 * 3600 + edTime.m_nHour
					* 24 * 3600 + edTime.m_nMinute * 60 + edTime.m_nSecond;
		} else {
			lSecd2 = edTime.m_nYear * 365 * 24 * 3600 + edTime.m_nMonth * 30
					* 24 * 3600 + edTime.m_nDay * 24 * 3600 + edTime.m_nHour
					* 24 * 3600 + edTime.m_nMinute * 60 + edTime.m_nSecond;
		}

		return lSecd2 - lSecd1;
	};

	public long Subduction(COleDateTime oleTime) {
		return (GetTatolSeconds() - oleTime.GetTatolSeconds());
	}

	public static void UTCTimeToLocalTime(COleDateTime utcTime,
			COleDateTime lcTime) {

	};

	public static void SetSystemTime(COleDateTime locTime) {

	};

	public long GetTatolSeconds() {

		long lSecd = 0;
		if (m_nMonth == 1 || m_nMonth == 3 || m_nMonth == 5 || m_nMonth == 7
				|| m_nMonth == 8 || m_nMonth == 10 || m_nMonth == 12) {
			lSecd = m_nYear * 365 * 24 * 3600 + m_nMonth * 31 * 24 * 3600
					+ m_nDay * 24 * 3600 + m_nHour * 24 * 3600 + m_nMinute * 60
					+ m_nSecond;
		} else {
			lSecd = m_nYear * 365 * 24 * 3600 + m_nMonth * 30 * 24 * 3600
					+ m_nDay * 24 * 3600 + m_nHour * 24 * 3600 + m_nMinute * 60
					+ m_nSecond;
		}

		return lSecd;
	};

	public boolean IsGreaterThanTo(COleDateTime oleTime) {
		if (m_nYear > oleTime.m_nYear)
			return true;

		if (m_nYear < oleTime.m_nYear)
			return false;

		if (m_nMonth > oleTime.m_nMonth)
			return true;

		if (m_nMonth < oleTime.m_nMonth)
			return false;

		if (m_nDay > oleTime.m_nDay)
			return true;

		if (m_nDay < oleTime.m_nDay)
			return false;

		if (m_nHour > oleTime.m_nHour)
			return true;

		if (m_nHour < oleTime.m_nHour)
			return false;

		if (m_nMinute > oleTime.m_nMinute)
			return true;

		if (m_nMinute < oleTime.m_nMinute)
			return false;

		if (m_nSecond > oleTime.m_nSecond)
			return true;

		if (m_nSecond < oleTime.m_nSecond)
			return false;

		return false;
	};

	public boolean IsGreaterThanEqualTo(COleDateTime oleTime) {
		if (m_nYear > oleTime.m_nYear)
			return true;

		if (m_nYear < oleTime.m_nYear)
			return false;

		if (m_nMonth > oleTime.m_nMonth)
			return true;

		if (m_nMonth < oleTime.m_nMonth)
			return false;

		if (m_nDay > oleTime.m_nDay)
			return true;

		if (m_nDay < oleTime.m_nDay)
			return false;

		if (m_nHour > oleTime.m_nHour)
			return true;

		if (m_nHour < oleTime.m_nHour)
			return false;

		if (m_nMinute > oleTime.m_nMinute)
			return true;

		if (m_nMinute < oleTime.m_nMinute)
			return false;

		if (m_nSecond > oleTime.m_nSecond)
			return true;

		if (m_nSecond < oleTime.m_nSecond)
			return false;
		else
			return true;

	};

	public boolean IsLessThanTo(COleDateTime oleTime) {

		if (m_nYear < oleTime.m_nYear)
			return true;

		if (m_nYear > oleTime.m_nYear)
			return false;

		if (m_nMonth < oleTime.m_nMonth)
			return true;

		if (m_nMonth > oleTime.m_nMonth)
			return false;

		if (m_nDay < oleTime.m_nDay)
			return true;

		if (m_nDay > oleTime.m_nDay)
			return false;

		if (m_nHour < oleTime.m_nHour)
			return true;

		if (m_nHour > oleTime.m_nHour)
			return false;

		if (m_nMinute < oleTime.m_nMinute)
			return true;

		if (m_nMinute > oleTime.m_nMinute)
			return false;

		if (m_nSecond < oleTime.m_nSecond)
			return true;

		if (m_nSecond > oleTime.m_nSecond)
			return false;

		return false;

	};

	public boolean IsLessThanEqualTo(COleDateTime oleTime) {
		if (m_nYear < oleTime.m_nYear)
			return true;

		if (m_nYear > oleTime.m_nYear)
			return false;

		if (m_nMonth < oleTime.m_nMonth)
			return true;

		if (m_nMonth > oleTime.m_nMonth)
			return false;

		if (m_nDay < oleTime.m_nDay)
			return true;

		if (m_nDay > oleTime.m_nDay)
			return false;

		if (m_nHour < oleTime.m_nHour)
			return true;

		if (m_nHour > oleTime.m_nHour)
			return false;

		if (m_nMinute < oleTime.m_nMinute)
			return true;

		if (m_nMinute > oleTime.m_nMinute)
			return false;

		if (m_nSecond < oleTime.m_nSecond)
			return true;

		if (m_nSecond > oleTime.m_nSecond)
			return false;
		else
			return true;

	};

	public boolean IsEqualsTo(COleDateTime oleTime) {
		if (m_nYear < oleTime.m_nYear)
			return false;

		if (m_nYear > oleTime.m_nYear)
			return false;

		if (m_nMonth < oleTime.m_nMonth)
			return false;

		if (m_nMonth > oleTime.m_nMonth)
			return false;

		if (m_nDay < oleTime.m_nDay)
			return false;

		if (m_nDay > oleTime.m_nDay)
			return false;

		if (m_nHour < oleTime.m_nHour)
			return false;

		if (m_nHour > oleTime.m_nHour)
			return false;

		if (m_nMinute < oleTime.m_nMinute)
			return false;

		if (m_nMinute > oleTime.m_nMinute)
			return false;

		if (m_nSecond < oleTime.m_nSecond)
			return false;

		if (m_nSecond > oleTime.m_nSecond)
			return false;
		else
			return true;

	};

	public void TimeAddHours(int nHours) {
		m_nHour += nHours;
		if (m_nHour >= 24 || m_nHour < 0) {
			m_nDay += (int) (m_nHour / 24);
			m_nHour = m_nHour % 24;

		}

		if (m_nMonth == 1 || m_nMonth == 3 || m_nMonth == 5 || m_nMonth == 7
				|| m_nMonth == 8 || m_nMonth == 10 || m_nMonth == 12) {
			if (m_nDay > 32 || m_nDay < 0) {
				m_nMonth += (int) (m_nDay / 32);
				m_nDay = m_nDay % 32;

			}
		} else {
			if (m_nDay >= 31 || m_nDay < 0) {
				m_nMonth += (int) (m_nDay / 31);
				m_nDay = m_nDay % 31;

			}
		}

		if (m_nMonth >= 13 || m_nMonth < 0) {
			m_nYear += (int) (m_nMonth / 13);
			m_nMonth = m_nMonth % 13;

		}
	};

	public void TimeAddMinutes(int nMinutes) {

	};

	public void TimeAddSeconds(int nSecond) {
		m_nSecond += nSecond;
		if (m_nSecond >= 60) {
			m_nMinute += (int) (m_nSecond / 60);

			m_nSecond = m_nSecond % 60;

		}

		if (m_nMinute >= 60) {
			m_nHour += (int) (m_nMinute / 60);
			m_nMinute = m_nMinute % 60;
		}
		if (m_nHour >= 24 || m_nHour < 0) {
			m_nDay += (int) (m_nHour / 24);
			m_nHour = m_nHour % 24;

		}

		if (m_nMonth == 1 || m_nMonth == 3 || m_nMonth == 5 || m_nMonth == 7
				|| m_nMonth == 8 || m_nMonth == 10 || m_nMonth == 12) {
			if (m_nDay > 32 || m_nDay < 0) {
				m_nMonth += (int) (m_nDay / 32);
				m_nDay = m_nDay % 32;

			}
		} else {
			if (m_nDay >= 31 || m_nDay < 0) {
				m_nMonth += (int) (m_nDay / 31);
				m_nDay = m_nDay % 31;

			}
		}

		if (m_nMonth >= 13 || m_nMonth < 0) {
			m_nYear += (int) (m_nMonth / 13);
			m_nMonth = m_nMonth % 13;
		}
	}

}
