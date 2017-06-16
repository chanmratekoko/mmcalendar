package mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * 
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class MyanmarDate {

	static final String[] MMA = { "First Waso", "Tagu", "Kason", "Nayon", "Waso", "Wagaung", "Tawthalin", "Thadingyut",
			"Tazaungmon", "Nadaw", "Pyatho", "Tabodwe", "Tabaung" };

	static final String[] MSA = { "waxing", "full moon", "waning", "new moon" };

	/**
	 * Week Days
	 */
	static final String[] WDA = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

	/**
	 * Myanmar year
	 */
	double my;

	/**
	 * year type [0=common, 1=little watat, 2=big watat]
	 */
	double myt;

	/**
	 * year length [354, 384, or 385 days]
	 */
	double myl;

	/**
	 * month Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5,
	 * Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
	 * Tabaung=12
	 */
	double mm;

	/**
	 * mmt: month type [1=hnaung, 0= Oo]
	 */
	double mmt;

	/**
	 * mml: month length [29 or 30 days]
	 */
	double mml;

	/**
	 * md: month day [1 to 30]
	 */
	double md;

	/**
	 * fd: fortnight day [1 to 15],
	 */
	double fd;

	/**
	 * mp :moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon],
	 */
	double mp;

	/**
	 * wd: week day [0=sat, 1=sun, ..., 6=fri]
	 */
	double wd;

	/**
	 * julian day number
	 */
	double jd;

	protected MyanmarDate() {
	}

	/**
	 * 
	 * @param my
	 *            Myanmar year
	 * @param mm
	 *            month Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4,
	 *            Wagaung=5, Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9,
	 *            Pyatho=10, Tabodwe=11, Tabaung=12
	 * @param mmt
	 *            month type [1=hnaung, 0= Oo]
	 * @param fd
	 *            fortnight day [1 to 15],
	 * @param mp
	 *            moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon],
	 */
	public MyanmarDate(int my, int mm, int mmt, int fd, int mp) {
		
		if (my < 0){
			throw new IllegalArgumentException("Myanmar year must be positive number");
		}
		if (mm < 0 || mm > 13){
			throw new IllegalArgumentException("Month must be 0 to 12");
		}
		if (mmt < 0 || mmt > 1){
			throw new IllegalArgumentException("Month type must be 0 to 1");
		}
		if (fd < 1 || fd > 15){
			throw new IllegalArgumentException("Fortnight day must be 0 to 15");
		}
		if (mp < 0 || mp > 3){
			throw new IllegalArgumentException("Moon phase must be 0 to 3");
		}
		
		this.my = my;
		this.mm = mm;
		this.mmt = mmt;		
		this.fd = fd;
		this.mp = mp;
	}

	public String getBuddhistEra(LanguageCatalog languageCatalog) {
		return NumberToStringUtil.convert(my + 1182, languageCatalog);
	}

	public String getBuddhistEra() {
		return getBuddhistEra(new LanguageCatalog());
	}

	public String getYear(LanguageCatalog languageCatalog) {
		return NumberToStringUtil.convert(my, languageCatalog);
	}

	public String getYear() {
		return getYear(new LanguageCatalog());
	}

	public int getYearInt() {
		return (int) my;
	}

	/**
	 * 
	 * @return 0=common, 1=little watat, 2=big watat
	 */
	public int getYearType() {
		/* 0=common, 1=little watat, 2=big watat */
		return (int) mmt;
	}

	public String getMnt() {
		return getMnt(LanguageCatalog.getInstance());
	}

	public String getMnt(LanguageCatalog languageCatalog) {
		/* 0=common, 1=little watat, 2=big watat */
		StringBuilder stringBuilder = new StringBuilder();
		if (mmt > 0) {
			stringBuilder.append(languageCatalog.translate("Late"));
		}

		if (myt > 0 && mm == 4) {
			stringBuilder.append(languageCatalog.translate("Second"));
		}

		return stringBuilder.toString();
	}

	public int getYearLength() {
		return (int) myl;
	}

	public String getMyanmarMonth() {
		return getMyanmarMonth(LanguageCatalog.getInstance());
	}

	public String getMyanmarMonth(LanguageCatalog languageCatalog) {
		int mmIndex = (int) this.mm;
		return languageCatalog.translate(MMA[mmIndex]);
	}

	/**
	 * Tagu=1, Kason=2, Nayon=3, 1st Waso=0, (2nd) Waso=4, Wagaung=5, *
	 * Tawthalin=6, Thadingyut=7, Tazaungmon=8, Nadaw=9, Pyatho=10, Tabodwe=11,
	 * * Tabaung=12
	 * 
	 * @return
	 */
	public int getMonth() {
		return (int) mm;
	}

	public String getMoonPhase() {
		return getMoonPhase(LanguageCatalog.getInstance());
	}

	public String getMoonPhase(LanguageCatalog languageCatalog) {
		int mpIndex = (int) this.mp;
		return languageCatalog.translate(MSA[mpIndex]);
	}

	/**
	 * 
	 * @return month length [29 or 30 days]
	 */
	public int getMonthLength() {
		return (int) mml;
	}

	/**
	 * 
	 * @return month day [1 to 30]
	 */
	public int getMonthDay() {
		return (int) md;
	}

	/**
	 * 
	 * @return month type [1=hnaung, 0= Oo]
	 */
	public int getMonthType() {
		return (int) mmt;
	}

	/**
	 * 
	 * @return fortnight day [1 to 15],
	 */
	public int getFortnightDayInt() {
		return (int) fd;
	}

	public String getFortnightDay() {
		return getFortnightDay(LanguageCatalog.getInstance());
	}

	public String getFortnightDay(LanguageCatalog languageCatalog) {
		return ((mp % 2) == 0) ? NumberToStringUtil.convert(fd, languageCatalog) : "";
	}

	/**
	 * 
	 * @return week day [0=sat, 1=sun, ..., 6=fri]
	 */
	public int getWeekDayInt() {
		return (int) wd;
	}

	public String getWeekDay() {
		return getWeekDay(LanguageCatalog.getInstance());
	}

	public String getWeekDay(LanguageCatalog languageCatalog) {
		int wdIndex = (int) this.wd;
		return languageCatalog.translate(WDA[wdIndex]);
	}

	/**
	 * 
	 * @return julian day number
	 */
	public int getJulianDayNumber() {
		return (int) jd;
	}

	/**
	 * 
	 * @return moon phase [0=waxing, 1=full moon, 2=waning, 3=new moon],
	 */
	public int getMoonPhrase() {
		return (int) mp;
	}

	public boolean isWeekend() {
		return ((wd == 0) || (wd == 1)) ? true : false;
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
		stringBuilder.append(getMyanmarMonth(languageCatalog));
		stringBuilder.append(getMoonPhase(languageCatalog));

		stringBuilder.append(" ");
		stringBuilder.append(getFortnightDay(languageCatalog));

		stringBuilder.append(" ");
		stringBuilder.append(getWeekDay(languageCatalog));
		stringBuilder.append(languageCatalog.translate("Nay"));

		return stringBuilder.toString();
	}

}
