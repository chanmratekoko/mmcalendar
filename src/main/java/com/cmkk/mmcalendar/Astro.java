package com.cmkk.mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class Astro {

	double sabbath;
	double sabbatheve;
	double yatyaza;
	double pyathada;
	double thamanyo;
	double amyeittasote;
	double warameittugyi;
	double warameittunge;
	double yatpote;
	double thamaphyu;
	double nagapor;
	double yatyotema;
	double mahayatkyan;
	double shanyat;
	/* 0=west, 1=north, 2=east, 3=south */
	double nagahle;
	/* 0=Binga, 1=Atun, 2=Yaza, 3=Adipati, 4= Marana, 5=Thike, 6=Puti */
	double mahabote;
	/* 0=orc, 1=elf, 2=human */
	double nakhat;

	protected Astro() {
	}

	public String getAstroligicalDay() {
		return getAstroligicalDay(LanguageCatalog.getInstance());
	}

	public String getAstroligicalDay(LanguageCatalog languageCatalog) {
		StringBuilder stringBuilder = new StringBuilder();
		if (yatyaza > 0) {
			stringBuilder.append(languageCatalog.translate("Yatyaza"));
		}
		if (pyathada == 1) {
			stringBuilder.append(languageCatalog.translate("Pyathada"));
		} else if (pyathada == 2) {
			stringBuilder.append(languageCatalog.translate("Afternoon Pyathada"));
		}
		return stringBuilder.toString();
	}

	public boolean isSabbath() {
		return (sabbath > 0) || (sabbatheve > 0);
	}

	public String getSabbath() {
		return getSabbath(LanguageCatalog.getInstance());
	}

	public String getSabbath(LanguageCatalog languageCatalog) {
		StringBuilder stringBuilder = new StringBuilder();
		if (sabbath > 0) {
			stringBuilder.append(languageCatalog.translate("Sabbath"));
		} else if (sabbatheve > 0) {
			stringBuilder.append(languageCatalog.translate("Sabbath Eve"));
		}
		return stringBuilder.toString();
	}

	public boolean isThamanyo() {
		return (thamanyo > 0);
	}

	public String getThamanyo() {
		return getThamanyo(LanguageCatalog.getInstance());
	}

	public String getThamanyo(LanguageCatalog languageCatalog) {
		return (thamanyo > 0) ? languageCatalog.translate("Thamanyo") : "";
	}

	public boolean isAmyeittasote() {
		return (amyeittasote > 0);
	}

	public String getAmyeittasote() {
		return getAmyeittasote(LanguageCatalog.getInstance());
	}

	public String getAmyeittasote(LanguageCatalog languageCatalog) {
		return (amyeittasote > 0) ? languageCatalog.translate("Amyeittasote") : "";
	}

	public boolean isWarameittugyi() {
		return (warameittugyi > 0);
	}

	public String getWarameittugyi() {
		return getWarameittugyi(LanguageCatalog.getInstance());
	}

	public String getWarameittugyi(LanguageCatalog languageCatalog) {
		return (warameittugyi > 0) ? languageCatalog.translate("Warameittugyi") : "";
	}

	public boolean isWarameittunge() {
		return (warameittunge > 0);
	}

	public String getWarameittunge() {
		return getWarameittunge(LanguageCatalog.getInstance());
	}

	public String getWarameittunge(LanguageCatalog languageCatalog) {
		return (warameittunge > 0) ? languageCatalog.translate("Warameittunge") : "";
	}

	public boolean isYatpote() {
		return (yatpote > 0);
	}

	public String getYatpote() {
		return getYatpote(LanguageCatalog.getInstance());
	}

	public String getYatpote(LanguageCatalog languageCatalog) {
		return (yatpote > 0) ? languageCatalog.translate("Yatpote") : "";
	}

	public boolean isThamaphyu() {
		return (thamaphyu > 0);
	}

	public String getThamaphyu() {
		return getThamaphyu(LanguageCatalog.getInstance());
	}

	public String getThamaphyu(LanguageCatalog languageCatalog) {
		return (thamaphyu > 0) ? languageCatalog.translate("Thamaphyu") : "";
	}

	public boolean isNagapor() {
		return (nagapor > 0);
	}

	public String getNagapor() {
		return getNagapor(LanguageCatalog.getInstance());
	}

	public String getNagapor(LanguageCatalog languageCatalog) {
		return (nagapor > 0) ? languageCatalog.translate("Nagapor") : "";
	}

	public boolean isYatyotema() {
		return (yatyotema > 0);
	}

	public String getYatyotema() {
		return getYatyotema(LanguageCatalog.getInstance());
	}

	public String getYatyotema(LanguageCatalog languageCatalog) {
		return (yatyotema > 0) ? languageCatalog.translate("Yatyotema") : "";
	}

	public boolean isMahayatkyan() {
		return (mahayatkyan > 0);
	}

	public String getMahayatkyan() {
		return getMahayatkyan(LanguageCatalog.getInstance());
	}

	public String getMahayatkyan(LanguageCatalog languageCatalog) {
		return (mahayatkyan > 0) ? languageCatalog.translate("Mahayatkyan") : "";
	}

	public boolean isShanyat() {
		return (shanyat > 0);
	}

	public String getShanyat() {
		return getShanyat(LanguageCatalog.getInstance());
	}

	public String getShanyat(LanguageCatalog languageCatalog) {
		return (shanyat > 0) ? languageCatalog.translate("Shanyat") : "";
	}

	public String getNagahle() {
		return getNagahle(LanguageCatalog.getInstance());
	}

	/**
	 * 
	 * @return 0=west, 1=north, 2=east, 3=south
	 */
	public int getNagahleInt() {
		return (int) nagahle;
	}

	public String getNagahle(LanguageCatalog languageCatalog) {
		String[] na = new String[] { "West", "North", "East", "South" };
		return languageCatalog.translate(na[(int) nagahle]);
	}

	public String getMahabote() {
		return getMahabote(LanguageCatalog.getInstance());
	}

	/**
	 * 
	 * @return 0=Binga, 1=Atun, 2=Yaza, 3=Adipati, 4= Marana, 5=Thike, 6=Puti
	 */
	public int getMahaboteInt() {
		return (int) mahabote;
	}

	public String getMahabote(LanguageCatalog languageCatalog) {
		String[] pa = new String[] { "Binga", "Atun", "Yaza", "Adipati", "Marana", "Thike", "Puti" };
		return languageCatalog.translate(pa[(int) mahabote]);
	}

	public String getNakhat() {
		return getNakhat(LanguageCatalog.getInstance());
	}

	/**
	 * 
	 * @return 0=orc, 1= elf, 2=human
	 */
	public int getNakhatInt() {
		return (int) nakhat;
	}

	public String getNakhat(LanguageCatalog languageCatalog) {
		String[] nk = new String[] { "Orc", "Elf", "Human" };
		return languageCatalog.translate(nk[(int) nakhat]);
	}

	@Override
	public String toString() {
		return toString(LanguageCatalog.getInstance());
	}

	public String toString(LanguageCatalog languageCatalog) {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(getAstroligicalDay());

		if (isSabbath()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getSabbath(languageCatalog));
		}

		if (isThamanyo()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getThamanyo(languageCatalog));
		}

		if (isThamaphyu()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getThamaphyu(languageCatalog));
		}

		if (isAmyeittasote()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getAmyeittasote(languageCatalog));
		}

		if (isWarameittugyi()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getWarameittugyi(languageCatalog));
		}

		if (isWarameittunge()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getWarameittunge(languageCatalog));
		}

		if (isYatpote()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getYatpote(languageCatalog));
		}

		if (isNagapor()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getNagapor());
		}

		if (isYatyotema()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getYatyotema(languageCatalog));
		}

		if (isMahayatkyan()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getMahayatkyan(languageCatalog));
		}

		if (isShanyat()) {
			stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
			stringBuilder.append(getShanyat(languageCatalog));
		}

		stringBuilder.append("\u1014\u1002\u102B\u1038\u1001\u1031\u102B\u1004\u103A\u1038" + getNagahle(languageCatalog) + "\u101E\u102D\u102F\u1037\u101C\u103E\u100A\u103A\u1037\u101E\u100A\u103A\u104B");
		stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
		stringBuilder.append(getMahabote(languageCatalog) + "\u1016\u103D\u102C\u1038");
		stringBuilder.append(" " + languageCatalog.getLanguage().getComma() + " ");
		stringBuilder.append(getNakhat(languageCatalog) + "\u1014\u1000\u1039\u1001\u1010\u103A");

		return stringBuilder.toString();
	}

}
