package com.cmkk.mmcalendar;

/**
 * <a href="mailto:chanmratekoko.dev@gmail.com">Chan Mrate Ko Ko</a>
 * @author Chan Mrate Ko Ko
 * @version 1.0
 *
 */
public class AstroConverter {

	/**
	 * 
	 * @param myanmarDate
	 * @return
	 */
	public static Astro mmDate2Astro(MyanmarDate myanmarDate) {
		return astro((int) myanmarDate.mm, (int) myanmarDate.mml, (int) myanmarDate.md, (int) myanmarDate.wd, (int) myanmarDate.my);
	}
	/**
	 * More Detail
	 * <a href="http://cool-emerald.blogspot.sg/2013/12/myanmar-astrological-calendar-days.html">More details</a>
	 * 
	 * @param mm month
	 * @param mml length of the month
	 * @param md day of the month [0-30]
	 * @param wd weekday [0=sat, 1=sun, ..., 6=fri]
	 * @param my Myanmar year
	 * @return
	 *  
	 */
	private static Astro astro(int mm, int mml, int md, int wd, int my) {

		double d, sabbath, sabbatheve, yatyaza, pyathada, thamanyo, amyeittasote;
		double warameittugyi, warameittunge, yatpote, thamaphyu, nagapor, yatyotema;
		double mahayatkyan, shanyat, nagahle, m1, wd1, wd2, mahabote;
		int[] wda;
		int[] sya;

		if (mm <= 0) {
			mm = 4;// first waso is considered waso
		}

		d = md - 15 * Math.floor(md / 16.0);// waxing or waning day [0-15]
		sabbath = 0;

		if ((md == 8) || (md == 15) || (md == 23) || (md == mml)) {
			sabbath = 1;
		}

		sabbatheve = 0;

		if ((md == 7) || (md == 14) || (md == 22) || (md == (mml - 1))) {
			sabbatheve = 1;
		}

		yatyaza = 0;
		m1 = mm % 4;
		wd1 = Math.floor(m1 / 2.0) + 4;
		wd2 = ((1 - Math.floor(m1 / 2.0)) + m1 % 2) * (1 + 2 * (m1 % 2));

		//if ((wd == wd1) || (wd == wd2))
		if ((Math.abs(wd - wd1) < 0.0000001) || (Math.abs(wd - wd2) < 0.0000001)) {
			yatyaza = 1;
		}

		pyathada = 0;
		wda = new int[] { 1, 3, 3, 0, 2, 1, 2 };

		if (m1 == wda[wd]) {
			pyathada = 1;
		}

		if ((m1 == 0) && (wd == 4)) {
			pyathada = 2;// afternoon pyathada
		}

		thamanyo = 0;
		m1 = mm - 1 - Math.floor(mm / 9.0);
		wd1 = (m1 * 2 - Math.floor(m1 / 8.0)) % 7;

		wd2 = (wd + 7 - wd1) % 7;

		if (wd2 <= 1) {
			thamanyo = 1;
		}

		amyeittasote = 0;
		wda = new int[] { 5, 8, 3, 7, 2, 4, 1 };

		if (d == wda[wd]) {
			amyeittasote = 1;
		}

		warameittugyi = 0;
		wda = new int[] { 7, 1, 4, 8, 9, 6, 3 };

		if (d == wda[wd]) {
			warameittugyi = 1;
		}

		warameittunge = 0;
		double wn = (wd + 6) % 7;

		if ((12 - d) == wn) {
			warameittunge = 1;
		}

		yatpote = 0;
		wda = new int[] { 8, 1, 4, 6, 9, 8, 7 };

		if (d == wda[wd]) {
			yatpote = 1;
		}

		thamaphyu = 0;
		wda = new int[] { 1, 2, 6, 6, 5, 6, 7 };

		if (d == wda[wd]) {
			thamaphyu = 1;
		}

		wda = new int[] { 0, 1, 0, 0, 0, 3, 3 };

		if (d == wda[wd]) {
			thamaphyu = 1;
		}

		if ((d == 4) && (wd == 5)) {
			thamaphyu = 1;
		}

		nagapor = 0;
		wda = new int[] { 26, 21, 2, 10, 18, 2, 21 };

		if (md == wda[wd]) {
			nagapor = 1;
		}

		wda = new int[] { 17, 19, 1, 0, 9, 0, 0 };

		if (md == wda[wd]) {
			nagapor = 1;
		}

		if (((md == 2) && (wd == 1)) || (((md == 12) || (md == 4) || (md == 18)) && (wd == 2))) {
			nagapor = 1;
		}

		yatyotema = 0;

		// m1 = (mm % 2) ? mm : ((mm + 9) % 12);

		m1 = (mm % 2 > 0) ? mm : ((mm + 9) % 12);
		m1 = (m1 + 4) % 12 + 1;

		if (d == m1) {
			yatyotema = 1;
		}

		mahayatkyan = 0;
		m1 = (Math.floor((mm % 12) / 2.0) + 4) % 6 + 1;

		if (d == m1) {
			mahayatkyan = 1;
		}

		shanyat = 0;
		sya = new int[] { 8, 8, 2, 2, 9, 3, 3, 5, 1, 4, 7, 4 };

		if (d == sya[mm - 1]) {
			shanyat = 1;
		}

		nagahle = Math.floor((mm % 12) / 3.0);
		mahabote = (my - wd) % 7;
		double nakhat = my % 3;

		Astro astro = new Astro();
		astro.sabbath = sabbath;
		astro.sabbatheve = sabbatheve;
		astro.yatyaza = yatyaza;
		astro.pyathada = pyathada;
		astro.thamanyo = thamanyo;
		astro.amyeittasote = amyeittasote;
		astro.warameittugyi = warameittugyi;
		astro.warameittunge = warameittunge;
		astro.yatpote = yatpote;
		astro.thamaphyu = thamaphyu;
		astro.nagapor = nagapor;
		astro.yatyotema = yatyotema;
		astro.mahayatkyan = mahayatkyan;
		astro.shanyat = shanyat;
		/* 0=west, 1=north, 2=east, 3=south */
		astro.nagahle = nagahle;
		/* 0=Binga, 1=Atun, 2=Yaza, 3=Adipati, 4=Marana, 5=Thike, 6=Puti */
		astro.mahabote = mahabote;
		astro.nakhat = nakhat;

		return astro;
	}
}
