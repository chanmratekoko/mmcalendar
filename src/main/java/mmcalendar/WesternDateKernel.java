package mmcalendar;


/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0.2
 *
 */
public final class WesternDateKernel {
	
	/**
	 * Julian date to Western date Credit4 Gregorian date:
	 * http://pmyers.pcug.org.au/General/JulianDates.htm Credit4 Julian
	 * Calendar: http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html
	 * 
	 * @param juliandate 		julian date
	 * @param calendarType		CalendarType Enum
	 * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
	 * 			{@link WesternDate} object
	 */
	public static WesternDate j2w(double juliandate, CalendarType calendarType) {
		return j2w(juliandate, calendarType.getNumber(), 0);
	}

	/**
	 * Julian date to Western date Credit4 Gregorian date:
	 * http://pmyers.pcug.org.au/General/JulianDates.htm Credit4 Julian
	 * Calendar: http://quasar.as.utexas.edu/BillInfo/JulianDatesG.html
	 * 
	 * @param jd
	 *            julian date
	 * @param calType
	 *            calendar type [Optional argument: 0=english (default),
	 *            1=Gregorian, 2=Julian]
	 * @param SG
	 *            Beginning of Gregorian calendar in JDN [Optional argument:
	 *            (default=2361222)])
	 * @return Western date (y=year, m=month, d=day, h=hour, n=minute, s=second)
	 * 			{@link WesternDate} object
	 */
	public static WesternDate j2w(double jd, int calType, double SG) {

		// ct=ct||0;
		calType = calType < 0 ? 0 : calType;

		// SG= SG || 2361222;
		// Gregorian start in English calendar (1752/Sep/14)
		SG = SG <=  0 ? 2361222 : SG;

		double j, jf, y, m, d, h, n, s;

		if (calType == 2 || (calType == 0 && (jd < SG))) {
			double b, c, f, e;
			j = Math.floor(jd + 0.5);
			jf = jd + 0.5 - j;
			b = j + 1524;
			c = Math.floor((b - 122.1) / 365.25);
			f = Math.floor(365.25 * c);
			e = Math.floor((b - f) / 30.6001);
			m = (e > 13) ? (e - 13) : (e - 1);
			d = b - f - Math.floor(30.6001 * e);
			y = m < 3 ? (c - 4715) : (c - 4716);
		} else {
			j = Math.floor(jd + 0.5);
			jf = jd + 0.5 - j;
			j -= 1721119;
			y = Math.floor((4 * j - 1) / 146097.0);
			j = 4 * j - 1 - 146097 * y;
			d = Math.floor(j / 4);
			j = Math.floor((4 * d + 3) / 1461.0);
			d = 4 * d + 3 - 1461 * j;
			d = Math.floor((d + 4) / 4.0);
			m = Math.floor((5 * d - 3) / 153.0);
			d = 5 * d - 3 - 153 * m;
			d = Math.floor((d + 5) / 5.0);
			y = 100 * y + j;
			if (m < 10) {
				m += 3;
			} else {
				m -= 9;
				y = y + 1;
			}
		}

		jf *= 24;
		h = Math.floor(jf);
		jf = (jf - h) * 60;
		n = Math.floor(jf);
		s = Math.round((jf - n) * 60);	
		
		return new WesternDate((int) y, (int) m, (int) d, (int) h, (int) n, (int) s);
	}

	/**
	 * Western date to Julian day number. Credit4 Gregorian 2 JD:
	 * http://www.cs.utsa.edu/~cs1063/projects/Spring2011/Project1/jdn-
	 * explanation.html
	 * 
	 * @param year 		western year
	 * @param month  	month [Jan=1, ... , Dec=12]
	 * @param day		day [0-31]
	 * @param calType 		[0-English, 1-Gregorian, 2-Julian]
	 *            calendar type [Optional argument: 0=english (default),
	 *            1=Gregorian, 2=Julian]
	 * @param SG
	 *            SG: Beginning of Gregorian calendar in JDN [Optional argument:
	 *            (default=2361222)])
	 * @return Julian day number
	 */
	public static double w2j(int year, int month, int day, int calType , double SG) {
		
		// ct=ct||0;
		calType = calType < 0 ? 0 : calType;

		// SG= SG || 2361222;
		// Gregorian start in English calendar (1752/Sep/14)
		SG = SG <=  0 ? 2361222 : SG;
		

		int a = (int) Math.floor((14 - month) / 12.0);
		year = year + 4800 - a;
		month = month + (12 * a) - 3;

		double jd = day + Math.floor((153 * month + 2) / 5.0) + (365 * year) + Math.floor(year / 4.0);

		if (calType  == 1) {
			jd = jd - Math.floor(year / 100) + Math.floor(year / 400) - 32045;
		} else if (calType  == 2) {
			jd = jd - 32083;
		} else {
			jd = jd - Math.floor(year / 100) + Math.floor(year / 400) - 32045;
			if (jd < SG) {
				jd = day + Math.floor((153 * month + 2) / 5) + (365 * year) + Math.floor(year / 4) - 32083;
				if (jd > SG)
					jd = SG;
			}
		}

		return jd;
	}
		
	/**
	 * 
	 * @param year Year
	 * @param month Month
	 * @param day Day
	 * @param calendarType CalendarType Enum [0-English, 1-Gregorian, 2-Julian]
	 *            calendar type [Optional argument: 0=english (default),
	 *            1=Gregorian, 2=Julian]
	 * @param SG
	 *            SG: Beginning of Gregorian calendar in JDN [Optional argument:
	 *            (default=2361222)])
	 * @return Julian day number
	 */
	public static double w2j(int year, int month, int day, CalendarType calendarType, double SG) {
		return w2j(year, month, day, calendarType.getNumber(), SG);
	}	
	
	/**
	 * 
	 * @param year Year
	 * @param month Month
	 * @param day Day
	 * @param hour
	 *            Hour
	 * @param minute
	 *            Minute
	 * @param second
	 *            Second
	 * @param calendarType CalendarType enum
	 * 			  [0-English, 1-Gregorian, 2-Julian]
	 *            calendar type [Optional argument: 0=english (default),
	 *            1=Gregorian, 2=Julian]
	 * @param SG
	 *            SG: Beginning of Gregorian calendar in JDN [Optional argument:
	 *            (default=2361222)])
	 * @return Julian day number
	 */
	public static double w2j(int year, int month, int day, int hour, int minute, int second , CalendarType calendarType, double SG) {
		double fractionOfDay = t2d(hour, minute, second);
		return w2j(year, month, day, calendarType.getNumber(), SG) + fractionOfDay;
	}
	
	/**
	 * Time to Fraction of day starting from 12 noon
	 * @param h hour
	 * @param n minute
	 * @param s second
	 * @return (d: fraction of day)
	 */
	public static double t2d(double h, double n, double s) { 
		return ((h - 12) / 24 + n / 1440 + s / 86400);
	}

	/**
	 * 
	 * @param westernDate
	 *            WesternDate object
	 * @param calendarType
	 *            CalendarType Enum
	 * @param SG
	 *            SG: Beginning of Gregorian calendar in JDN [Optional argument:
	 *            (default=2361222)])
	 * @return Julian number
	 */
	public static double w2j(WesternDate westernDate, CalendarType calendarType, double SG) {
		return w2j(westernDate.getYear(), westernDate.getMonth(), westernDate.getDay(), westernDate.getHour(),
				westernDate.getMinute(), westernDate.getSecond(), calendarType, SG);
	}

	/**
	 * The month according to calendar type
	 * 
	 * @param year Western Year 
	 * @param month Western Month
	 *            [Jan=1, ... , Dec=12]
	 * @return julian day number of start of month
	 */
	public static int getJulianDayNumbeOstartOfMonth(int year, int month) {
		return (int) w2j(year, month, 1, Config.CALENDARTYPE.getNumber(), 0);
	}

	/**
	 * find the length of a month (emLen)
	 * 
	 * @param year Year
	 * @param month Month
	 *            [Jan=1, ... , Dec=12]
	 * @param calenderType
	 *            [0=English, 1=Gregorian, 2=Julian]
	 * @return the length of a month
	 */
	public static int getLengthOfMonth(int year, int month, int calenderType) {
		int leap = 0;
		// length of the current month
		int mLen = (int) (30 + (month + Math.floor(month / 8.0)) % 2);

		// if february
		if (month == 2) {
			if (calenderType == 1 || (calenderType == 0 && year > 1752)) {
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
					leap = 1;
				}
			} else if (year % 4 == 0)
				leap = 1;
			mLen += leap - 2;
		}

		if (year == 1752 && month == 9 && calenderType == 0) {
			mLen = 19;
		}

		return mLen;
	}

	/**
	 * Julian day number of end of the month
	 * 
	 * @param year Year
	 * @param month Month
	 * @return Julian day number of end of the month
	 */
	public static int getJulianDayNumberOfEndOfMonth(int year, int month) {
		int js = getJulianDayNumbeOstartOfMonth(year, month);
		int eml = getLengthOfMonth(year, month, Config.CALENDARTYPE.getNumber());
		return js + eml - 1;
	}
	
}
