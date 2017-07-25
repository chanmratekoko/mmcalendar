package mmcalendar;

import java.io.Serializable;

/**
 * Myanmar Date 
 * 
 * @author <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @version 1.0.2
 * 
 * @since 1.0
 *
 */
public class MyanmarDate implements Serializable, Cloneable, Comparable<MyanmarDate> {

	private static final long serialVersionUID = 1L;

	static final String[] MMA = { "First Waso", "Tagu", "Kason", "Nayon", "Waso", "Wagaung", "Tawthalin", "Thadingyut",
			"Tazaungmon", "Nadaw", "Pyatho", "Tabodwe", "Tabaung" };

	/**
	 * New Moon mean Dark moon
	 */
	static final String[] MSA = { "waxing", "full moon", "waning", "new moon" }; 

	/**
	 * Week Days
	 */
	static final String[] WDA = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

	/**
	 * Myanmar year
	 */
	int myear;

	/**
	 * year type [0=common, 1=little watat, 2=big watat]
	 */
	int yearType;

	/**
	 * myl : year length [normal = 354, small watat = 384, or big watat = 385 days]
	 */
	int yearLength;

	/**
	 * mm :
	 * month Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
	 * Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
	 * Tabaung=12
	 */
	int mmonth;

	/**
	 * mmt: month type [1=hnaung, 0= Oo]
	 */
	int monthType;

	/**
	 * mml: month length [29 or 30 days]
	 */
	int monthLength;

	/**
	 * md: month day [1 to 30]
	 */
	int monthDay;

	/**
	 * fd: fortnight day [1 to 15],
	 */
	int fortnightDay;

	/**
	 * 
	 * mp :moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon],
	 */
	int moonPhase;

	/**
	 * wd: week day [0=sat, 1=sun, ..., 6=fri]
	 */
	int weekDay;

	/**
	 * julian day number
	 */
	double jd;

	protected MyanmarDate() {
	}

	/**
	 * 
	 * @param myear
	 *            Myanmar year
	 * @param mmonth
	 *            month Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4,
	 *            Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9,
	 *            Pyatho=10, Tabodwe=11, Tabaung=12
	 * @param mmt
	 *            month type [1=hnaung, 0= Oo]
	 * @param fd
	 *            fortnight day [1 to 15],
	 * @param moonPhase
	 *            moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon],
	 */
	public MyanmarDate(int myear, int mmonth, int mmt, int fd, int moonPhase) {
		
		if (myear < 0){
			throw new IllegalArgumentException("Myanmar year must be positive number");
		}
		if (mmonth < 0 || mmonth > 13){
			throw new IllegalArgumentException("Month must be 0 to 12");
		}
		if (mmt < 0 || mmt > 1){
			throw new IllegalArgumentException("Month type must be 0 to 1");
		}
		if (fd < 1 || fd > 15){
			throw new IllegalArgumentException("Fortnight day must be 0 to 15");
		}
		if (moonPhase < 0 || moonPhase > 3){
			throw new IllegalArgumentException("Moon phase must be 0 to 3");
		}
		
		this.myear = myear;
		this.mmonth = mmonth;
		this.monthType = mmt;		
		this.fortnightDay = fd;
		this.moonPhase = moonPhase;
	}

	public String getBuddhistEra(LanguageCatalog languageCatalog) {
		return NumberToStringUtil.convert(myear + 1182, languageCatalog);
	}

	public String getBuddhistEra() {
		return getBuddhistEra(new LanguageCatalog());
	}

	public String getYear(LanguageCatalog languageCatalog) {
		return NumberToStringUtil.convert(myear, languageCatalog);
	}

	public String getYear() {
		return getYear(new LanguageCatalog());
	}

	public int getYearInt() {
		return myear;
	}

	/**
	 * 
	 * @return 0=common, 1=little watat, 2=big watat
	 */
	public int getYearType() {
		/* 0=common, 1=little watat, 2=big watat */
		return yearType;
	}

	public String getMnt() {
		return getMnt(LanguageCatalog.getInstance());
	}

	public String getMnt(LanguageCatalog languageCatalog) {
		/* 0=common, 1=little watat, 2=big watat */
		StringBuilder stringBuilder = new StringBuilder();
		if (monthType > 0) {
			stringBuilder.append(languageCatalog.translate("Late"));
		}

		if (yearType > 0 && mmonth == 4) {
			stringBuilder.append(languageCatalog.translate("Second"));
		}

		return stringBuilder.toString();
	}

	/**
	 * Myanmar Year Length
	 * @return [normal=354, small watat=384, big watat=385]
	 */
	public int getYearLength() {
		return yearLength;
	}

	public String getMonthName() {
		return getMonthName(LanguageCatalog.getInstance());
	}

	public String getMonthName(LanguageCatalog languageCatalog) {
		return getMnt() + languageCatalog.translate(MMA[this.mmonth]);
	}

	/**
	 * @return int Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4,
	 *         Wagaung=5, * Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9,
	 *         Pyatho=10, Tabodwe=11, * Tabaung=12
	 */
	public int getMonth() {
		return mmonth;
	}
	
	/**
	 * moon phase 
	 * @return [0=waxing, 1=full moon, 2=waning, 3=new moon]
	 */
	public int getMoonPhraseInt(){
		return moonPhase;
	}

	public String getMoonPhase() {
		return getMoonPhase(LanguageCatalog.getInstance());
	}

	public String getMoonPhase(LanguageCatalog languageCatalog) {
		return languageCatalog.translate(MSA[this.moonPhase]);
	}

	/**
	 * 
	 * @return month length [29 or 30 days]
	 */
	public int getMonthLength() {
		return monthLength;
	}

	/**
	 * 
	 * @return month day [1 to 30]
	 */
	public int getMonthDay() {
		return monthDay;
	}

	/**
	 * 
	 * @return month type [1 = hnaung, 0 = Oo]
	 */
	public int getMonthType() {
		return monthType;
	}

	/**
	 * 
	 * @return fortnight day [1 to 15],
	 */
	public int getFortnightDayInt() {
		return fortnightDay;
	}

	public String getFortnightDay() {
		return getFortnightDay(LanguageCatalog.getInstance());
	}

	public String getFortnightDay(LanguageCatalog languageCatalog) {
		return ((moonPhase % 2) == 0) ? NumberToStringUtil.convert(fortnightDay, languageCatalog) : "";
	}

	/**
	 * 
	 * @return week day [0=sat, 1=sun, ..., 6=fri]
	 */
	public int getWeekDayInt() {
		return weekDay;
	}

	public String getWeekDay() {
		return getWeekDay(LanguageCatalog.getInstance());
	}

	public String getWeekDay(LanguageCatalog languageCatalog) {
		return languageCatalog.translate(WDA[this.weekDay]);
	}

	/**
	 * 
	 * @return julian day number
	 */
	public double getJulianDayNumber() {
		return jd;
	}

	/**
	 * 
	 * @return moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon],
	 */
	public int getMoonPhrase() {
		return moonPhase;
	}

	public boolean isWeekend() {
		return ((weekDay == 0) || (weekDay == 1)) ? true : false;
	}

	@Override
	public String toString() {
		LanguageCatalog languageCatalog = LanguageCatalog.getInstance();
		return toString(languageCatalog);
	}

	public String toString(LanguageCatalog languageCatalog) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(languageCatalog.translate("Sasana Year"));
		stringBuilder.append(" ");
		stringBuilder.append(getBuddhistEra(languageCatalog));
		stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
		stringBuilder.append(languageCatalog.translate("Myanmar Year"));
		stringBuilder.append(" ");
		stringBuilder.append(getYear(languageCatalog));
		stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");

		stringBuilder.append(" ");
		stringBuilder.append(getMonthName(languageCatalog));
		stringBuilder.append(" ");
		stringBuilder.append(getMoonPhase(languageCatalog));

		stringBuilder.append(" ");
		stringBuilder.append(getFortnightDay(languageCatalog));

		stringBuilder.append(" ");
		stringBuilder.append(getWeekDay(languageCatalog));
		stringBuilder.append(languageCatalog.translate("Nay"));

		return stringBuilder.toString();
	}

	@Override
	public int compareTo(MyanmarDate anotherMyanmarDate) {		
		return Double.compare(jd, anotherMyanmarDate.jd);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fortnightDay;
		long temp;
		temp = Double.doubleToLongBits(jd);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + mmonth;
		result = prime * result + monthDay;
		result = prime * result + monthLength;
		result = prime * result + monthType;
		result = prime * result + moonPhase;
		result = prime * result + myear;
		result = prime * result + weekDay;
		result = prime * result + yearLength;
		result = prime * result + yearType;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyanmarDate other = (MyanmarDate) obj;
		if (fortnightDay != other.fortnightDay)
			return false;
		if (Double.doubleToLongBits(jd) != Double.doubleToLongBits(other.jd))
			return false;
		if (mmonth != other.mmonth)
			return false;
		if (monthDay != other.monthDay)
			return false;
		if (monthLength != other.monthLength)
			return false;
		if (monthType != other.monthType)
			return false;
		if (moonPhase != other.moonPhase)
			return false;
		if (myear != other.myear)
			return false;
		if (weekDay != other.weekDay)
			return false;
		if (yearLength != other.yearLength)
			return false;
		if (yearType != other.yearType)
			return false;
		return true;
	}
}
