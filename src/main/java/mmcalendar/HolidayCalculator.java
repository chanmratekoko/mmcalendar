package mmcalendar;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public final class HolidayCalculator {

	/**
	 * Eid
	 */
	private static final int[] ghEid2 = new int[] { 2456936, 2457290, 2457644 };

	/**
	 * Chinese New Yea
	 */
	private static final int[] ghCNY = new int[] { 2456689, 2456690, 2457073, 2457074, 2457427, 2457428, 2457782,
			2457783 };

	private static final int[] ghDiwali = new int[] { 2456599, 2456953, 2457337, 2457691, 2458045, 2458429 };
	private static final int[] ghEid = new int[] { 2456513, 2456867, 2457221, 2457576, 2457930, 2458285 };

	/**
	 * 
	 * @param gy
	 *            year
	 * @param gm
	 *            month [Jan=1, ... , Dec=12]
	 * @param gd
	 *            day [0-31]
	 * @return Holiday Strings
	 */
	static List<String> ehol(int gy, int gm, int gd) {

		List<String> holiday = new ArrayList<String>();

		if ((gy >= 1948) && (gm == 1) && (gd == 4)) {
			holiday.add("Independence Day");
		} else if ((gy >= 1947) && (gm == 2) && (gd == 12)) {
			holiday.add("Union Day");
		} else if ((gy >= 1958) && (gm == 3) && (gd == 2)) {
			holiday.add("Peasants Day");
		} else if ((gy >= 1945) && (gm == 3) && (gd == 27)) {
			holiday.add("Resistance Day");
		} else if ((gy >= 1923) && (gm == 5) && (gd == 1)) {
			holiday.add("Labour Day");
		} else if ((gy >= 1947) && (gm == 7) && (gd == 19)) {
			holiday.add("Martyrs Day");
		} else if ((gm == 12) && (gd == 25)) {
			holiday.add("Christmas Day");
		} else if ((gy >= 2017) && (gm == 12) && (gd == 30 || gd == 31)) {
			holiday.add("Holiday");
		} else if ((gy > 2017) && (gm == 1) && (gd == 1)) {
			holiday.add("Holiday");
		}

		return holiday;
	}

	/**
	 * 
	 * @param my
	 *            Myanmar Year
	 * @param mm
	 *            Myanmar month [Tagu=1, ... , Tabaung=12]
	 * @param md
	 *            day [0-30]
	 * @param mp
	 *            moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
	 * @return List of holiday String
	 */
	static List<String> mhol(double my, int mm, int md, int mp) {

		List<String> holiday = new ArrayList<String>();

		if ((mm == 2) && (mp == 1)) {
			holiday.add("Buddha Day");
		} // Vesak day
		else if ((mm == 4) && (mp == 1)) {
			holiday.add("Start of Buddhist Lent");
		} // Warso day
		else if ((mm == 7) && (mp == 1)) {
			holiday.add("End of Buddhist Lent");
		} else if ((my >= 1379) && (mm == 7) && (md == 14 || md == 16)) {
			holiday.add("Holiday");
		} else if ((mm == 8) && (mp == 1)) {
			holiday.add("Tazaungdaing");
		} else if ((my >= 1379) && (mm == 8) && (md == 14)) {
			holiday.add("Holiday");
		} else if ((my >= 1282) && (mm == 8) && (md == 25)) {
			holiday.add("National Day");
		} else if ((mm == 10) && (md == 1)) {
			holiday.add("Karen New Year Day");
		} else if ((mm == 12) && (mp == 1)) {
			holiday.add("Tabaung Pwe");
		}

		return holiday;
	}

	/**
	 * 
	 * @param jdn
	 *            Julian Day Number to check
	 * @param my
	 *            Myanmar year
	 * @param mmt
	 *            myanmar month type [oo=0, hnaung=1
	 * @return List of holiday String
	 */
	public static List<String> thingyan(double jdn, double my, int mmt) {

		// start of Thingyan
		int BGNTG = 1100;

		List<String> holiday = new ArrayList<String>();

		// double atat;
		double akn, atn;
		// start of third era
		int SE3 = 1312;

		double ja = Constants.SY * (my + mmt) + Constants.MO;
		double jk;

		if (my >= SE3) {
			jk = ja - 2.169918982;
		} else {
			jk = ja - 2.1675;
		}

		akn = Math.round(jk);
		atn = Math.round(ja);

		// if (jdn == (atn + 1))
		if (Math.abs(jdn - (atn + 1)) < 0.0000001) {
			holiday.add("Myanmar New Year Day");
		}

		if ((my + mmt) >= BGNTG) {
			if (jdn == atn) {
				holiday.add("Thingyan Atat");
			} else if ((jdn > akn) && (jdn < atn)) {
				holiday.add("Thingyan Akyat");
			} else if (jdn == akn) {
				holiday.add("Thingyan Akya");
			} else if (jdn == (akn - 1)) {
				holiday.add("Thingyan Akyo");
			} else if (((my + mmt) >= 1369) && ((my + mmt) < 1379)
					&& ((jdn == (akn - 2)) || ((jdn >= (atn + 2)) && (jdn <= (akn + 7))))) {
				holiday.add("Holiday");
			}
		}

		return holiday;
	}

	/**
	 * Diwali or Eid
	 * 
	 * @param jd
	 *            Julian day number
	 * @return List of holiday String
	 */
	public static List<String> ohol(double jd) {

		List<String> holiday = new ArrayList<String>();

		if (BinarySearchUtil.search(jd, ghDiwali) >= 0) {
			holiday.add("Diwali");
		}
		if (BinarySearchUtil.search(jd, ghEid) >= 0) {
			holiday.add("Eid");
		}

		return holiday;
	}

	/**
	 * 
	 * @param jd
	 *            Julian Day Number,
	 * @param calendarType
	 *            calendar type [Optional argument: 0=english (default),
	 *            1=Gregorian, 2=Julian]
	 * @return dependency: DoE(), j2w()
	 */
	public static List<String> ecd(double jd, CalendarType calendarType) {
		// ct=ct||0;
		if (calendarType == null) {
			calendarType = CalendarType.ENGLISH;
		}

		List<String> holiday = new ArrayList<String>();

		WesternDate wd = WesternDateConverter.j2w(jd, calendarType);
		double doe = DoE(wd.year);

		if ((wd.year >= 1915) && (wd.month == 2) && (wd.day == 13)) {
			holiday.add("G. Aung San BD");
		} else if ((wd.year >= 1969) && (wd.month == 2) && (wd.day == 14)) {
			holiday.add("Valentines Day");
		} else if ((wd.year >= 1970) && (wd.month == 4) && (wd.day == 22)) {
			holiday.add("Earth Day");
		} else if ((wd.year >= 1392) && (wd.month == 4) && (wd.day == 1)) {
			holiday.add("April Fools Day");
		} else if ((wd.year >= 1948) && (wd.month == 5) && (wd.day == 8)) {
			holiday.add("Red Cross Day");
		} else if ((wd.year >= 1994) && (wd.month == 10) && (wd.day == 5)) {
			holiday.add("World Teachers Day");
		} else if ((wd.year >= 1947) && (wd.month == 10) && (wd.day == 24)) {
			holiday.add("United Nations Day");
		} else if ((wd.year >= 1753) && (wd.month == 10) && (wd.day == 31)) {
			holiday.add("Halloween");
		}

		if ((wd.year >= 1876) && (jd == doe)) {
			holiday.add("Easter");
		} else if ((wd.year >= 1876) && (jd == (doe - 2))) {
			holiday.add("Good Friday");
		} else if (BinarySearchUtil.search(jd, ghEid2) >= 0) {
			holiday.add("Eid");
		}
		if (BinarySearchUtil.search(jd, ghCNY) >= 0) {
			holiday.add("Chinese New Year");
		}

		return holiday;
	}

	/**
	 * Date of Easter using "Meeus/Jones/Butcher" algorithm Reference: Peter
	 * Duffett-Smith, Jonathan Zwart',
	 * "Practical Astronomy with your Calculator or Spreadsheet," 4th Etd,
	 * Cambridge university press, 2011. Page-4.
	 * 
	 * @param y
	 *            year
	 * @return julian day number dependency: w2j()
	 */
	private static double DoE(double y) {
		double a = y % 19;
		double b = Math.floor(y / 100);
		double c = y % 100;
		double d = Math.floor(b / 4);
		double e = b % 4;
		double f = Math.floor((b + 8) / 25);
		double g = Math.floor((b - f + 1) / 3);
		double h = (19 * a + b - d - g + 15) % 30;
		double i = Math.floor(c / 4);
		double k = c % 4;
		double l = (32 + 2 * e + 2 * i - h - k) % 7;
		double m = Math.floor((a + 11 * h + 22 * l) / 451);
		double q = h + l - 7 * m + 114;
		double p = (q % 31) + 1;
		double n = Math.floor(q / 31);
		// this is for Gregorian
		return WesternDateConverter.w2j(y, n, p, 1, 0);
	}

	/**
	 * 
	 * @param my
	 *            Myanmar year
	 * @param mm
	 *            Myanmar month [Tagu=1, ... , Tabaung=12]
	 * @param md
	 *            day [0-30]
	 * @param mp
	 *            moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
	 * @return List of holiday String
	 */
	public static List<String> mcd(double my, int mm, int md, int mp) {

		List<String> holiday = new ArrayList<String>();

		if ((my >= 1309) && (mm == 11) && (md == 16)) {
			holiday.add("Mon National Day");
		} // the ancient founding of Hanthawady
		else if ((mm == 9) && (md == 1)) {
			holiday.add("Shan New Year Day");
			if (my >= 1306) {
				holiday.add("Authors Day");
			}
		} // Nadaw waxing moon 1
		else if ((mm == 3) && (mp == 1)) {
			holiday.add("Mahathamaya Day");
		} // Nayon full moon
		else if ((mm == 6) && (mp == 1)) {
			holiday.add("Garudhamma Day");
		} // Tawthalin full moon
		else if ((my >= 1356) && (mm == 10) && (mp == 1)) {
			holiday.add("Mothers Day");
		} // Pyatho full moon
		else if ((my >= 1370) && (mm == 12) && (mp == 1)) {
			holiday.add("Fathers Day");
		} // Tabaung full moon
		else if ((mm == 5) && (mp == 1)) {
			holiday.add("Metta Day");
			// if(my>=1324) {hs[h++]="Mon Revolution Day";}//Mon Revolution day
		} // Waguang full moon
		else if ((mm == 5) && (md == 10)) {
			holiday.add("Taungpyone Pwe");
		} // Taung Pyone Pwe
		else if ((mm == 5) && (md == 23)) {
			holiday.add("Yadanagu Pwe");
		} // Yadanagu Pwe

		// else if((my>=1119) && (mm==2) && (md==23)) {hs[h++]="Mon Fallen
		// Day";}
		// else if((mm==12) && (md==12)) {hs[h++]="Mon Women Day";}

		return holiday;
	}

	/**
	 * 
	 * @param myanmarDate
	 *            MyanmarDate
	 * @return List of holiday String
	 */
	public static List<String> getHoliday(MyanmarDate myanmarDate) {

		WesternDate westernDate = WesternDateConverter.j2w(myanmarDate.jd, Config.CALENDARTYPE);
		// Office Off
		List<String> hde = ehol((int) westernDate.year, (int) westernDate.month, (int) westernDate.day);
		List<String> hdm = mhol(myanmarDate.my, (int) myanmarDate.mm, (int) myanmarDate.md, (int) myanmarDate.mp);
		List<String> hdt = thingyan(myanmarDate.jd, myanmarDate.my, (int) myanmarDate.mmt);
		List<String> hdo = ohol(myanmarDate.jd); // Diwali Eid

		List<String> holiday = new ArrayList<String>();

		holiday.addAll(hde);
		holiday.addAll(hdm);
		holiday.addAll(hdt);
		holiday.addAll(hdo);

		return holiday;
	}

	/**
	 * 
	 * @param myanmarDate
	 * @return boolean
	 */
	public static boolean isHoliday(MyanmarDate myanmarDate) {
		return getHoliday(myanmarDate).size() > 0 ? true : false;
	}

	/**
	 * 
	 * @param holidayList
	 * @return boolean
	 */
	public static boolean isHoliday(List<String> holidayList) {
		return holidayList.size() > 0 ? true : false;
	}

	/**
	 * 
	 * @param myanmarDate
	 * @return List of holiday String
	 */
	public static List<String> getAnniversary(MyanmarDate myanmarDate) {
		List<String> ecd = ecd(myanmarDate.jd, Config.CALENDARTYPE); // anniversary
																		// day
		List<String> mcd = mcd(myanmarDate.my, (int) myanmarDate.mm, (int) myanmarDate.md, (int) myanmarDate.mp);

		List<String> holiday = new ArrayList<String>();

		holiday.addAll(ecd);
		holiday.addAll(mcd);

		return holiday;
	}

}
