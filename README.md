
[![GitHublicense](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/chanmratekoko/mmcalendar/blob/master/LICENSE) [![Maven Central](https://img.shields.io/badge/Maven%20Central-1.0.6.RELEASE-blue.svg)](https://search.maven.org/remote_content?g=com.github.chanmratekoko&a=myanmar-calendar&v=LATEST)

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
  <version>1.0.8.RELEASE</version>
</dependency>
```

**2. Gradle dependency**
  -  Add this to your app `build.gradle`:

```gradle
compile 'com.github.chanmratekoko:myanmar-calendar:1.0.8.RELEASE'
```

**3. jar file**
- Download the [**latest .jar file**][3] from the
releases section
- Copy the **myanmar-calendar-version.jar** file into the `libs` folder of your application project
- Start using the library

## Sample

### Configure

Configure default calendar type and language. It is `optional`.

```java
// Default Language MYANMAR Unicode Standard (Burmese)
// Default Calendar Type ENGLISH
// Configure Language and Calendar Type
Config.initDefault(
    new Config.Builder()
      .setCalendarType(CalendarType.ENGLISH)
      .setLanguage(Language.ENGLISH)
      .build());
```

### Myanmar Date Converter

#### Sample Usage:

```java
// Get Myanmar Date by year, month and day
MyanmarDate myanmarDate = MyanmarDate.of(2024, 1, 1);

// Output: ၂၅၆၇
myanmarDate.getBuddhistEra();

// Output: ၁၃၈၅
myanmarDate.getYear();

// Output: နတ်တော်
myanmarDate.getMonthName();

// Output: လဆုတ်
myanmarDate.getMoonPhase();

// Output: ၅
myanmarDate.getFortnightDay();

// Output : တနင်္လာ
myanmarDate.getWeekDay();
```

#### Translate the output into another language.
```java
// Get Myanmar Date by year, month and day
MyanmarDate myanmarDate = MyanmarDate.of(2024, 1, 1);
Language language = Language.ENGLISH;

// Output: 2567
myanmarDate.getBuddhistEra(language);

// Output: 1385
myanmarDate.getYear(language);

// Output: Nadaw
myanmarDate.getMonthName(language);

// Output: Waning
myanmarDate.getMoonPhase(language);

// Output: 5
myanmarDate.getFortnightDay(language);

// Output: Monday
myanmarDate.getWeekDay(language);
```

### Myanmar Date format

```java
MyanmarDate myanmarDate = MyanmarDate.of(2024, 1, 1);
//Output: သာသနာနှစ် ၂၅၆၇ ခု၊ မြန်မာနှစ် ၁၃၈၅ ခု၊ နတ်တော် လဆုတ် ၅ ရက် တနင်္လာနေ့
myanmarDate.format("S s k, B y k, M p f r En");

//Output: Sasana Year 2567 Ku, Myanmar Year 1385 Ku, Nadaw Waning 5 Yat Monday Nay
myanmarDate.format("S s k, B y k, M p f r E n", Language.ENGLISH);
```

#### Myanmar Date Patterns

Myanmar Date formats are specified by date pattern strings.
The following pattern letters are defined ('S', 's', 'B', 'y', 'k', 'M', 'p', 'f', 'E', 'n', 'r', are reserved):

| Letter        | Date Component  | Examples Myanmar  | Examples English |
| ------------- |-------------|-------------|------------------|
| S | Sasana year | သာသနာနှစ် | Sasana Year      |
| s | Buddhist era | ၂၅၆၁ | 2561             |
| B | Burmese year | မြန်မာနှစ် | Myanmar Year     |
| y | Myanmar year | ၁၃၇၉ | 1379             |
| k | Ku | ခု | Ku |
| M | Month in year | ဝါခေါင် | Wagaung          |
| p | Moon phase | လဆန်း | waxing           |
| f | Fortnight Day | ၁ | 1                |
| r | Yat | ရက် |         Yat         |
| E | Day name in week | တနင်္လာ | Monday           |
| n | Nay | နေ့ |         Nay         |

### Calculation of Relevant Myanmar Months For The Year

```java
// နှစ်အလိုက် မြန်မာ လအမည်များ တွက်ချက်ခြင်း
// Output: Month Names (Relevant Myanmar month names for a given Myanmar year.)
MyanmarMonths myanmarMonth = MyanmarMonths.of(1381, 0);
```

### Create the header for the Myanmar Calendar.

#### Myanmar Calendar Style Header
```java
// Output: သာသနာနှစ် ၂၅၆၂ ခု မြန်မာနှစ် ၁၃၈၀ ခု ကဆုန် - နယုန်
// Output: Sasana Year 2561 - 2562 Ku Myanmar Year 1379 - 1380 Ku Late Kason - Kason
String header = MyanmarCalendarKernel.getCalendarHeader(1380, 2);
```

#### Western Calendar Style Header
```java
// Output: သာသနာနှစ် ၂၅၆၇ - ၂၅၆၈ ခု မြန်မာနှစ် ၁၃၈၅ - ၁၃၈၆ ခု တပေါင်း - တန်ခူး
// Output: Sasana Year 2567 - 2568 Ku Myanmar Year 1385 - 1386 Ku Tabaung - Tagu
String header = MyanmarCalendarKernel.getCalendarHeaderForWesternStyle(2024, 4);
```

### Thingyan (Myanmar New Year)

```java
MyanmarThingyanDateTime myanmarThingyanDateTime = MyanmarThingyanDateTime.of(1386);
// Thingyan Akyo day (သင်္ကြန်အကြိုနေ့)
MyanmarDate akyoDay = myanmarThingyanDateTime.getAkyoDay();
// Akya time (သင်္ကြန်ကျချိန်)
LocalTime akyaTime = myanmarThingyanDateTime.getAkyaTime().toMyanmarLocalTime();
// Akya day (အကျနေ့)
MyanmarDate akyaDay = myanmarThingyanDateTime.getAkyaDay();
// Atat Time (သင်္ကြန်တက်ချိန်)
LocalTime atatTime = myanmarThingyanDateTime.getAtatTime().toMyanmarLocalTime();
// Atat day (သင်္ကြန်အတက်နေ့)
MyanmarDate atatDay = myanmarThingyanDateTime.getAtatDay();
// Thingyan Akyat day (အကြတ်နေ့)
MyanmarDate[] akyatDays = myanmarThingyanDateTime.getAkyatDays();
//  Myanmar New Year's Day (နှစ်ဆန်းတစ်ရက်နေ့)
MyanmarDate myanmarNewYearDate = myanmarThingyanDateTime.getMyanmarNewYearDay();
```

### Holiday Calculation

```java
// Checks if the current date is a holiday
boolean isHoliday = HolidayCalculator.isHoliday(MyanmarDate.now());

// Retrieves a list of holiday names for the current date
List<String> holidayNameList = HolidayCalculator.getHoliday(MyanmarDate.now());
```

```java
// Check Anniversary
List<String> anniversary = HolidayCalculator.getAnniversary(MyanmarDate.of(2017,1 ,1));
```

### Astrological information Converter

```java
import mmcalendar.MyanmarDate;
import mmcalendar.Astro;

MyanmarDate myanmarDate = MyanmarDate.now();
Astro astro = Astro.of(myanmarDate);

// ဥပုသ် သို့ အဖိတ်
// Output: Sabbath or Sabbath Eve or Empty
astro.getSabbath();

// သမားညို
// Output: Thamanyo or Empty
astro.getThamanyo();

// သမားဖြူ
// Output: Thamaphyu or Empty
astro.getThamaphyu();

// အမြိတ္တစုတ်
// Output: Amyeittasote or Empty
astro.getAmyeittasote();

// ဝါရမိတ္တုကြီး
// Output: Warameittugyi or Empty
astro.getWarameittugyi();

// ဝါရမိတ္တုငယ်
// Output: Warameittunge or Empty
astro.getWarameittunge();

// ရက်ပုပ်
// Output: Yatpote or Empty
astro.getYatpote();

// နဂါးပေါ်
// Output: Nagapor or Empty
astro.getNagapor();

// ရက်ယုတ်မာ
// Output: Yatyotema or Empty
astro.getYatyotema();

// မဟာရက်ကြမ်း
// Output: Mahayatkyan or Empty
astro.getMahayatkyan();

// ရှမ်းရက်
// Output: Shanyat or empty
astro.getShanyat();

// နဂါးခေါင်း လှည့်ရာအရပ်
// Output: west or north or east or south
astro.getNagahle();

// မဟာဘုတ်၊ ဇာတာခွင်
// Output: Binga or Atun or Yaza or Adipati or Marana or Thike or Puti
astro.getMahabote();

// နက္ခတ်
// Output: Ogre or Elf or Human
astro.getNakhat();

// ခုနှစ်အမည်
// Output: Hpusha or Magha or Phalguni or Chitra or Visakha or Jyeshtha or Ashadha or Sravana or Bhadrapaha or Asvini or Krittika or Mrigasiras
astro.getYearName();

// ရက်ရာဇာ သို့ ပြဿဒါး သို့ မွန်းလွဲ ပြဿဒါး
// Output: "Yatyaza" or "Pyathada" or "Afternoon Pyathada" or Empty ""
astro.getAstrologicalDay();
```
> You can verify these days by invoking the properties prefixed with `is`.


## Features
* The conversion of Myanmar Date can be executed with support for various time zones, integrating features from the Java Date and Time API, Julian Day, and Unix Time.
* Astrological information Converter
* Myanmar Date, Julian date to Western Date
Calendar Type (English, Gregorian and Julian) also Support.
* The algorithm for Myanmar Thingyan Date and Time.

> All the calculations are based on Myanmar Standard Time (UTC+06:30) which is calculated on the basis of 97° 30' longitude.

> ***Note:*** Ready For Advanced Users and Developers. Check Kernel!

### Language Localization Support
* English
* Myanmar (Burmese Unicode) _(default)_
* Myanmar (Burmese Zawgyi)
* Myanmar (Mon)
* Myanmar (Tai)
* Myanmar (Karen)

### Test Coverage

Unit test coverage initiation spans from 1923 through 2023.

The algorithm is capable of calculating from the commencement of the Myanmar Calendar Year 2.


### Demo
- [Web](https://yan9a.github.io/mmcal/index.htm)

### Concept reference resources:
1. [Algorithm, Program and Calculation of Myanmar Calendar](http://cool-emerald.blogspot.sg/2013/06/algorithm-program-and-calculation-of.html) [(Dr Yan Naing Aye)](https://github.com/yan9a/)

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
