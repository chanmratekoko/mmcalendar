[![GitHublicense](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/chanmratekoko/mmcalendar/blob/master/LICENSE) [![Maven Central](https://img.shields.io/badge/Maven%20Central-1.0.4.RELEASE-blue.svg)](https://search.maven.org/remote_content?g=com.github.chanmratekoko&a=myanmar-calendar&v=LATEST)

# Myanmar Calendar
**Myanmar Calendar** library for Android and Java applications.
For more information see [the website][1] and [the wiki][2].

Usage
-----

**1. Maven**
- Add the following to the `<repositories>` section of your `pom.xml`:

```xml
<dependency>
  <groupId>com.github.chanmratekoko</groupId>
  <artifactId>myanmar-calendar</artifactId>
  <version>1.0.4.RELEASE</version>
</dependency>
```

**2. Gradle dependency**
  -  Add this to your app `build.gradle`:

```gradle
compile 'com.github.chanmratekoko:myanmar-calendar:1.0.4.RELEASE'
```

**3. jar file only**
- Download the [**latest .jar file**][3] from the
releases section
- Copy the **myanmar-calendar-version.jar** file into the `libs` folder of your application project
- Start using the library


Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].


# Sample

### Configure
It is Optional.

```java
// Default Language MYANMAR Unicode Standard
// Default Calendar Type ENGLISH
// Configure Language and Calendar Type
Config.initDefault(
    new Config.Builder()
      .setCalendarType(CalendarType.ENGLISH)
      .setLanguage(Language.ENGLISH)
      .build());
```

### Myanmar Date Converter

```java
MyanmarDate myanmarDate = MyanmarDateConverter.convert(2017, 6, 6);
// or
MyanmarDate myanmarDate = MyanmarDateConverter.convert(new GregorianCalendar());

myanmarDate.getBuddhistEra();
myanmarDate.getYear();
myanmarDate.getMonthName();
myanmarDate.getMoonPhase();
myanmarDate.getFortnightDay();
myanmarDate.getWeekDay();

// (or)

LanguageCatalog languageCatalog = new LanguageCatalog(Language.ENGLISH);

myanmarDate.getBuddhistEra(languageCatalog);
myanmarDate.getYear(languageCatalog);
myanmarDate.getMonthName(languageCatalog);
myanmarDate.getMoonPhase(languageCatalog);
myanmarDate.getFortnightDay(languageCatalog);
myanmarDate.getWeekDay(languageCatalog);

```

### Myanmar Date format

```java
myanmarDate.format("S s k, B y k, M p f r En");
//returnh သာသနာနှစ် ၂၅၆၁ ခု, မြန်မာနှစ် ၁၃၇၉ ခု, ဝါခေါင် လဆန်း ၁ ရက် တနင်္လာနေ
// (or)

LanguageCatalog languageCatalog = new LanguageCatalog(Language.ENGLISH);
myanmarDate.format("S s k, B y k, M p f r En", languageCatalog);
//return Sasana Year 2561 , Myanmar Year 1379 , Wagaung waxing 1  Monday
```

#### Myanmar Date Patterns

Myanmar Date formats are specified by date pattern strings.
The following pattern letters are defined ('S', 's', 'B', 'y', 'k', 'M', 'p', 'f', 'E', 'n', 'r', are reserved):

| Letter        | Date Component  | Examples Myanmar  | Examples English |
| ------------- |-------------|-------------|-------------|
| S | Sasana year | သာသနာနှစ် | Sasana Year |
| s | Buddhist era | ၂၅၆၁ | 2561 |
| B | Burmese year | မြန်မာနှစ် | Myanmar Year |
| y | Myanmar year | ၁၃၇၉ | 1379 |
| k | Ku | ခု |  |
| M | Month in year | ဝါခေါင် | Wagaung |
| p | Moon phase | လဆန်း | waxing |
| f | Fortnight Day | ၁ | 1 |
| r | Yat | ရက် |  |
| E | Day name in week | တနင်္လာ | Monday |
| n | Nay | နေ့ | |

### Astrological information Converter

```java
Astro astro = AstroConverter.convert(myanmarDate);

if (astro.isSabbath()) {
	astro.getSabbath();
}

if (astro.isThamanyo()) {
	astro.getThamanyo();
}

if (astro.isThamaphyu()) {
	astro.getThamaphyu();
}

if (astro.isAmyeittasote()) {
	astro.getAmyeittasote();
}

if (astro.isWarameittugyi()) {
	astro.getWarameittugyi();
}

if (astro.isWarameittunge()) {
	astro.getWarameittunge();
}

if (astro.isYatpote()) {
	astro.getYatpote();
}

if (astro.isNagapor()) {
	astro.getNagapor();
}

if (astro.isYatyotema()) {
	astro.getYatyotema();
}

if (astro.isMahayatkyan()) {
	astro.getMahayatkyan();
}

if (astro.isShanyat()) {
	astro.getShanyat();
}

astro.getNagahle();
astro.getMahabote();
astro.getNakhat();
astro.getYearName();		
astro.getAstroligicalDay();
```

## Features
* Java Calendar, Julian date, (Custom Day, Month, Year) to Myanmar Date. Calendar Type (English, Gregorian and Julian) also Support.
* Astrological information Converter
* Myanmar Date, Julian date to Western Date
Calendar Type (English, Gregorian and Julian) also Support.
* Multi language support (English, Myanmar (Unicode), Myanmar (Zawgyi), Myanmar (Mon)).

> ***Note:*** Ready For Advanced Users and Developers. Check Kernel!

### Demo
- [Web](http://mc1500.com/)

### Concept reference resources:
1. [Myanmar Calendar Javascript Project by Ko Yan Naing Aye](https://github.com/yan9a/mcal)
2. [Algorithm, Program and Calculation of Myanmar Calendar](http://cool-emerald.blogspot.sg/2013/06/algorithm-program-and-calculation-of.html)

### Contributing

Would you like to contribute? Fork us and send a pull request! Be sure to checkout our issues first.

### Simple issues and bug reports

If you are reporting a bug which can be observed visually, please add to your issue either:
* A working sample project that we can compile, run, and immediately observe the issue

## License
```
MIT License

Copyright (c) 2017 Chan Mrate Ko Ko

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

[1]: https://chanmratekoko.github.io/mmcalendar/
[2]: https://github.com/chanmratekoko/mmcalendar/wiki
[3]: https://search.maven.org/remote_content?g=com.github.chanmratekoko&a=myanmar-calendar&v=LATEST

[snap]: http://maven.aliyun.com/nexus/#nexus-search;quick~myanmar-calendar
