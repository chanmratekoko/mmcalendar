# Myanmar Calendar
**Myanmar Calendar** for Android and Java applications.
For more information see [the website][1] and [the wiki][2].


## Download

Download [the latest JAR][3] or grab via Maven:

```xml
<dependency>
  <groupId>com.github.chanmratekoko</groupId>
  <artifactId>myanmar-calendar</artifactId>
  <version>1.0</version>
</dependency>
```

or Gradle:
```groovy
compile 'com.github.chanmratekoko:myanmar-calendar:1.0'
```

Snapshots of the development version are available in [Sonatype's `snapshots` repository][snap].


## Usage

### Java

```java
//Default Language Myanmar Unicode
LanguageCatalog languageCatalog = new LanguageCatalog(Language.ENGLISH);
MyanmarDate myanmarDate = MyanmarDateConverter.convert(2017, 6, 6);		

String sasanaYear = myanmarDate.getBuddhistEra(languageCatalog);
String myanmarYear = myanmarDate.getYear(languageCatalog);
String myanmarMonth = myanmarDate.getMyanmarMonth(languageCatalog);
String fortnightDay = myanmarDate.getFortnightDay(languageCatalog);
String weekDay = myanmarDate.getWeekDay(languageCatalog);

Astro astro = AstroConverter.mmDate2Astro(myanmarDate);

```

### Kotlin

```kotlin
var languageCatalog : LanguageCatalog = LanguageCatalog(Language.ENGLISH)
var myanmarDate : MyanmarDate = MyanmarDateConverter.convert(2017, 6, 8)

var sasanaYear = myanmarDate.getBuddhistEra(languageCatalog)
var myanmarYear = myanmarDate.getYear(languageCatalog)
var myanmarMonth = myanmarDate.getMyanmarMonth(languageCatalog)
var fortnightDay = myanmarDate.getFortnightDay(languageCatalog)
var weekDay = myanmarDate.getWeekDay(languageCatalog)

var astro : Astro = AstroConverter.mmDate2Astro(myanmarDate)
```

### Demo
- [Web](http://mc1500.com/)


### Concept reference resources:
1. [Myanmar Calendar Javascript Project by Ko Yan Naing Aye](https://github.com/yan9a/mcal)
2. [Algorithm, Program and Calculation of Myanmar Calendar](http://cool-emerald.blogspot.sg/2013/06/algorithm-program-and-calculation-of.html)

# License
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

### Contributing to Myanmar Calendar

Just make pull request. You are in!



[1]: https://chanmratekoko.github.io/mmcalendar/
[2]: https://github.com/chanmratekoko/mmcalendar/wiki
[3]: https://search.maven.org/remote_content?g=com.github.chanmratekoko&a=myanmar-calendar&v=LATEST

[snap]: https://oss.sonatype.org/content/repositories/snapshots/
