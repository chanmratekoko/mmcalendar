package mmcalendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class MyanmarDateConverter {

	static final String[] EMA = { "First Waso", "Tagu", "Kason", "Nayon", "Waso", "Wagaung", "Tawthalin", "Thadingyut",
			"Tazaungmon", "Nadaw", "Pyatho", "Tabodwe", "Tabaung", "Late Tagu", "Late Kason" };

	public static MyanmarDate convert(int year, int month, int day) {
		return convert(year, month, day, CalendarType.ENGLISH, 0);
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param calendarType
	 * @param SG
	 *            : Beginning of Gregorian calendar in JDN [Optional argument:
	 *            default=2361222)])
	 * @return
	 */
	public static MyanmarDate convert(int year, int month, int day, CalendarType calendarType, double SG) {
		double julianDayNumber = WesternDateConverter.w2j(year, month, day, Config.CALENDARTYPE.getNumber(), SG);
		return convert(julianDayNumber);
	}

	public static MyanmarDate convert(double julianDayNumber) {
		return j2m(julianDayNumber);
	}

	/**
	 * Julian date to Myanmar date dependency: chk_my(my)
	 * 
	 * @param jd
	 *            : julian date
	 * @return MyanmarDate year year type [0=common, 1=little watat, 2=big
	 *         watat], month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
	 *         Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
	 *         Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12 ] month type
	 *         [1=hnaung, 0= Oo] month length [29 or 30 days] month day [1 to
	 *         30] fortnight day [1 to 15] moon phase [0=waxing, 1=full moon,
	 *         2=waning, 3=new moon] week day [0=sat, 1=sun, ..., 6=fri]
	 */
	public static MyanmarDate j2m(double jd) {

		double jdn, dd, myl, mmt, a, b, c, e, f, mm, md, mml, mp, fd, wd;

		double my;
		Map<String, Double> yo;

		// convert jd to jdn
		jdn = Math.round(jd);
		/// Myanmar year
		my = Math.floor((jdn - 0.5 - Constants.MO) / Constants.SY);
		// check year
		yo = chk_my(my);
		// day count
		dd = jdn - yo.get("tg1") + 1;
		b = Math.floor(yo.get("myt") / 2.0);
		// big wa and common yr
		c = Math.floor(1 / (yo.get("myt") + 1));
		// year length
		myl = 354 + (1 - c) * 30 + b;
		// month type: Hnaung =1 or Oo = 0
		mmt = Math.floor((dd - 1) / myl);
		dd -= mmt * myl;
		// adjust day count and threshold
		a = Math.floor((dd + 423) / 512.0);
		// month
		mm = Math.floor((dd - b * a + c * a * 30 + 29.26) / 29.544);
		e = Math.floor((mm + 12) / 16.0);
		f = Math.floor((mm + 11) / 16.0);
		// day
		md = dd - Math.floor(29.544 * mm - 29.26) - b * e + c * f * 30;
		mm += f * 3 - e * 4;
		// adjust month and month length
		mml = 30 - mm % 2;

		if (mm == 3) {
			// adjust if Nayon in big watat
			mml += b;
		}

		mp = Math.floor((md + 1) / 16.0) + Math.floor(md / 16.0) + Math.floor(md / mml);
		// waxing or waning day
		fd = md - 15 * Math.floor(md / 16.0);
		// week day
		wd = (jdn + 2) % 7;

		MyanmarDate myanmarDate = new MyanmarDate();
		myanmarDate.my = my;
		myanmarDate.myt = yo.get("myt");
		myanmarDate.myl = myl;
		myanmarDate.mm = mm;
		myanmarDate.mmt = mmt;
		myanmarDate.mml = mml;
		myanmarDate.md = md;
		myanmarDate.mp = mp;
		myanmarDate.fd = fd;
		myanmarDate.wd = wd;
		myanmarDate.jd = jd;

		return myanmarDate;
	}

	/**
	 * Check Myanmar Year dependency: chk_watat(my)
	 * 
	 * @param my
	 *            myanmar year
	 * @return map myt :year type [0=common, 1=little watat, 2=big watat] tg1 :
	 *         the 1st day of Tagu as Julian Day Number fm : full moon day of
	 *         [2nd] Waso as Julain Day Number) werr: watat error 0 or 1
	 * 
	 */
	static Map<String, Double> chk_my(double my) {
		int yd = 0;
		Map<String, Double> y1;
		double nd = 0;
		double werr = 0;
		double fm = 0;

		Map<String, Double> y2 = chk_watat(my);
		double myt = y2.get("watat");

		do {
			yd++;
			y1 = chk_watat(my - yd);
		} while (y1.get("watat") == 0 && yd < 3);

		if (myt > 0) {
			nd = (y2.get("fm") - y1.get("fm")) % 354;
			myt = Math.floor(nd / 31.0) + 1;
			fm = y2.get("fm");
			if (nd != 30 && nd != 31) {
				werr = 1;
			}
		} else {
			fm = y1.get("fm") + 354 * yd;
		}

		double tg1 = y1.get("fm") + 354 * yd - 102;

		// return {myt:myt,tg1:tg1,fm:fm,werr:werr};

		Map<String, Double> map = new HashMap<String, Double>();
		map.put("myt", myt);
		map.put("tg1", tg1);
		map.put("fm", fm);
		map.put("werr", werr);

		return map;
	}

	/**
	 * Check watat (intercalary month) dependency: chk_exception(my,fm,watat,ei)
	 * 
	 * @param my
	 *            myanmar year
	 * @return map watat - intercalary month [1=watat, 0=common] fm - full moon
	 *         day of 2nd Waso in jdn [valid for watat years only])
	 */
	static Map<String, Double> chk_watat(double my) {

		// for(var i=g_eras.length-1;i>0;i--)
		// if(my>=g_eras[i].begin) break; //get data for respective era

		int i = Era.G_ERAS.size() - 1;

		for (; i > 0; i--) {
			if (my > Era.G_ERAS.get(i).begin) {
				break;
			}
		}

		Era era = Era.G_ERAS.get(i);
		int NM = era.NM;
		double WO = era.WO;

		double TA = (Constants.SY / 12.0 - Constants.LM) * (12 - NM); // threshold
																		// to
																		// adjust
		double ed = (Constants.SY * (my + 3739)) % Constants.LM; // excess day

		if (ed < TA) {
			// adjust excess days
			ed += Constants.LM;
		}

		/* full moon day of 2nd Waso */
		double fm = Math.round(Constants.SY * my + Constants.MO - ed + 4.5 * Constants.LM + WO);

		double TW = 0;
		double watat = 0; // find watat

		if (era.eid >= 2) {
			/* if 2nd era or later find watat based on excess days */
			TW = Constants.LM - (Constants.SY / 12.0 - Constants.LM) * NM;
			if (ed >= TW) {
				watat = 1;
			}
		} else {
			/* if 1st era,find watat by 19 years metonic cycle */
			/* Myanmar year is divided by 19 and there is intercalary month */
			/* if the remainder is 2,5,7,10,13,15,18 */
			/*
			 * https://github.com/kanasimi/CeJS/blob/master/data/date/calendar.
			 * js#L2330
			 */
			watat = (my * 7 + 2) % 19;
			if (watat < 0) {
				watat += 19;
			}
			watat = Math.floor(watat / 12.0);
		}

		i = BinarySearchUtil.search(my, era.wte);
		if (i >= 0) {
			// correct watat exceptions
			watat = era.wte[i][1];
		}

		if (watat > 0) {
			i = BinarySearchUtil.search(my, era.fme);
			if (i >= 0) {
				fm += era.fme[i][1];
			}
		} // correct full moon day exceptions

		Map<String, Double> map = new HashMap<String, Double>();
		map.put("fm", fm);
		map.put("watat", watat);
		// return {fm:fm,watat:watat};

		return map;
	}

	// -------------------------------------------------------------------------
	
	/**
	 * Myanmar date to Julian date
	 * @param myanmarDate
	 * @return
	 */
	public static double m2j(MyanmarDate myanmarDate) {
		return m2j(myanmarDate.my, myanmarDate.mm, myanmarDate.mmt, myanmarDate.mp, myanmarDate.fd);
	}
	
	
	/**
	 * Myanmar date to Julian date dependency: chk_my(my)
	 * 
	 * @param my
	 *            Myanmar Year
	 * @param mm
	 *            Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
	 *            Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
	 *            Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12 ]
	 * @param mmt
	 *            month type [1=hnaung, 0=Oo]
	 * @param mp
	 *            moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon]
	 * @param fd
	 *            fortnight day [1 to 15]
	 * @return julian day number
	 */
	public static double m2j(double my, double mm, double mmt, double mp, double fd) {

		double b, c, mml, m1, m2, md, dd;

		//check year
		Map<String, Double> yo = chk_my(my);

		b = Math.floor(yo.get("myt") / 2);

		//if big watat and common year
		c = (yo.get("myt") == 0) ? 1 : 0;

		//month length
		mml = 30 - mm % 2;

		if (mm == 3) {
			//adjust if Nayon in big watat
			mml += b;
		}

		m1 = mp % 2;
		m2 = Math.floor(mp / 2.0);
		md = m1 * (15 + m2 * (mml - 15)) + (1 - m1) * (fd + 15 * m2);

		// adjust month
		mm += 4 - Math.floor((mm + 15) / 16.0) * 4 + Math.floor((mm + 12) / 16);
		

		dd = md + Math.floor(29.544 * mm - 29.26) - c * Math.floor((mm + 11) / 16.0) * 30
				+ b * Math.floor((mm + 12) / 16.0);
		// year length
		double myl = 354 + (1 - c) * 30 + b;
		// adjust day count
		dd += mmt * myl;

		return dd + yo.get("tg1") - 1;
	}

	/**
	 * Time to Fraction of day starting from 12 noon
	 * 
	 * @param h
	 *            hour
	 * @param n
	 *            minute
	 * @param s
	 *            second
	 * @return fraction of day
	 */
	static double t2d(double h, double n, double s) {
		return ((h - 12) / 24.0) + (n / 1440.0) + (s / 86400.0);
	}

	/**
	 * 
	 * @param my
	 *            Myanmar Year
	 * @param mm
	 *            Myanmar month [Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd)
	 *            Waso=4, Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8,
	 *            Nadaw=9, Pyatho=10, Tabodwe=11, Tabaung=12 ]
	 * @return
	 */
	public static MyanmarMonths getMyanmarMonth(double my, double mm) {

		double j1 = Math.round(Constants.SY * my + Constants.MO) + 1;
		double j2 = Math.round(Constants.SY * (my + 1) + Constants.MO);

		MyanmarDate M1 = j2m(j1);
		MyanmarDate M2 = j2m(j2);
		int si = (int) (M1.mm + 12 * M1.mmt);

		double ei = M2.mm + 12 * M2.mmt;

		if (si == 0) {
			si = 4;
		}
		if (mm == 0 && M1.myt == 0) {
			mm = 4;
		}
		if (mm != 0 && mm < si) {
			mm = si;
		}
		if (mm > ei) {
			mm = ei;
		}
		
		List<Integer> index = new ArrayList<Integer>();
		List<String> monthNameList = new ArrayList<String>();
		int currentIndex = 0;

		for (int i = si; i <= ei; i++) {
			if (i == 4 && M1.myt != 0) {
				index.add(0);
				monthNameList.add(EMA[0]);
				if (mm == 0) {
					currentIndex = 0;
				}
			}
			index.add(i);
			monthNameList.add(((i == 4 && M1.myt != 0) ? "Second " : "") + EMA[i]);

			//if (i == mm)  
			if(Math.abs(i - mm) < 0.0000001 ) {
				currentIndex = i;
			}
		}

		return new MyanmarMonths(index, monthNameList, currentIndex);
	}	

}
