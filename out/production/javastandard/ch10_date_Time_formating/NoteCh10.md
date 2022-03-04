# 1.날짜와 시간

## 1.1 Calendar와 Date
+ 초창기에 Date 클래스가 만들어지고, 조금 더 개선한 Calendar클래스가 만들어졌다.
+ 그러나 이제는 java.time 패키지가 있어서 굳이 쓰지는 않지만, 이 클래스를 활용한 라이브러리들이 있어서 이해는 필요하다.
+ 태국을 제외한 모든 나라들은 GregorianCalendar 를 사용한다.
+ Calendar는 추상클래스이기때문에, 메서드를 통해서 완전히 구현된 클래스의 인스턴스를 얻어야 한다.
```
Calendar cal = Calendar.getInstance();
````

### Date와 Calendar간의 변환
1. Calendar를 Date로 변환
```
  Calendar cal = Calendar.getInstance();
  ....
  Date d = new Date(cal.getTimeInMillis());
  ----------------------------------------
  Calendar cal = Calendar.getInstance();
  Date d2 = cal.getTime()       // Calendar를 Date로 변환
```
2. Date를 Calendar로 변환
```
  Date d = new Date();
  ...
  Calendar cal = Calendar.getInstance();
  cal.setTime(d)
```

+ Calendar 의 getTimeInMillis()는 1/1000초를 단위로 값이 존재한다. 그러므로 우리기준의 초단위로 맞추려면 1000으로 나누어주어야한다.
+ 책 or 작성한 코드에서 Calendar의 여러메소드와 상수필드값들을 보도록 하자.

# 2. 형식화클래스
+ 날짜들을 형식에 맞춰서 출력하려면 계산할 생각으로 벌써 복잡하다.
+ 그러므로 java.text패키지에는 숫자, 날짜, 텍스트데이터를 일정한 형식에 맞게 표현할 수 있도록 표준화해두었다.
+ 즉 형식화된 데이터의 패턴만 정의해주면 복잡한 문자열에서도 쉽게 원하는 값을 얻어 낼 수 있다.
## 2.1 DecimalFormat
+ 숫자를 형식화 하는데 사용된다.
+ 책 or API를 참고하여 사용법을 알아보자.
> DecimalFormat의 패턴에 사용되는 기호들
```
0 : 10진수
1 : 10진수
. : 소수점
- : 음수부호
. : 단위구분자
E : 지수기호
; : 패턴구분자
% : 퍼센트
\u2030 : 퍼밀(퍼센트 x10)
\u00A4 : 통화
' : escape 문자.
```
+ 예시
```
double number = 1234567.89;
DecimalFormat df = new DecimalFormat("#.#E0");  
String result = df.format(number); // 1.2E6
```

+ 관련 메서드
```
format: 숫자를 패턴에 맞춘 문자열로 반환해준다.
parse : 기호와 문자가 포함된 문자열(ex)1,234,567.89)을 Number 인스턴스값으로 리턴한다.
    - 메서드 호출 이전에 패턴을 넣어주어야한다.
```
## 2.2 SimpleDateFormat
+ 이제는 날짜의 출력형식을 만들어 출력해보자.
+ 원하는 출력형식의 패턴을 작성하여 SimpleDateFormat인스턴스를 생성한 다음, 출력하고자 하는 Date인스턴스를 가지고 format(Date d)를 호출한다.
+ 그 결과로 지정한 출력형식에 맞게 변환된 문자열을 얻게된다.
```
Date today = new Date();
SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
String result = df.format(today);   // 2022-03-03 형식으로 출력
```
+ 추가적인 패턴기호들은 책 aPI참고

+ 관련 메서드
```
format: 날짜를 패턴에 맞춘 문자열로 반환해준다.
parse : 기호와 문자가 포함된 날짜문자열(ex)2015/12/31)을 Date 인스턴스값으로 리턴한다. 
    - 메서드 호출 이전에 패턴을 넣어주어야한다.
      DateFormat df = new SimpleDateFormat(pattern);
      df.parse(문자열);
```
## 2.3 ChoiceFormat
+ 특정 범위에 속하는 값을 문자열로 변환해준다.
+ 책 or 코드참고
+ 경계값들은 double 형식으로 모두 오름차순으로 정렬되있어야한다.
+ 치환된 문자열의 개수는 경계값에 의해 정의된 범위의 개수와 일치해야한다.
> 패턴 구분자
```
# : 경계값을 범위에 포함한다.
< : 경계값을 범위에 포함하지 않는다.
```
```java
class Ch10_16ChoiceFormatEx1 {
    public static void main(String[] args) {
        double[] limits = {60, 70, 80, 90};	// 낮은 값부터 큰 값의 순서로 적어야한다.
        // limits, grades간의 순서와 개수를 맞추어야 한다.
        String[] grades = {"D", "C", "B", "A"};

        int[] scores = { 100, 95, 88, 70, 52, 60, 70};

        ChoiceFormat form = new ChoiceFormat(limits, grades);

        for(int i=0;i<scores.length;i++) {
            System.out.println(scores[i]+":"+form.format(scores[i]));
        }
    } // main
}
```
## 2.4 MessageFormat
+ 데이터가 들어갈 자리를 마련해 놓은 양식을 미리 작성하고, 프로그램을 이용해서 다수의 데이터를 같은 양식으로 출력할 수 있다.
+ {숫자} - 숫자는 인덱스 0부터 시작하며 양식에 들어갈 데이터는 객체배열인 arguments에 지저오디어 있다.(코드확인)
```java
class Ch_18MessageFormatEx1 {
    public static void main(String[] args) {
        String msg = "Name: {0} \nTel: {0} \nAge:{2} \nBirthday:{3}";   // 인덱스 번호에 알맞은 결과가 출력된다.

        Object[] arguments = {
                "이자바","02-123-1234", "27", "07-09"
        };

        String result = MessageFormat.format(msg, arguments);
        System.out.println(result);
    }
}
```
> parse 메소드로 패턴에 있는 값들을 뽑아올 수도 있다.
```java
class Ch_20MessageFormatEx3 {
        public static void main(String[] args) throws Exception {
            String[] data = {
                    "INSERT INTO CUST_INFO VALUES ('이자바','02-123-1234',27,'07-09');",
                    "INSERT INTO CUST_INFO VALUES ('김프로','032-333-1234',33,'10-07');"
            };

            String pattern = "INSERT INTO CUST_INFO VALUES ({0},{1},{2},{3});";
            MessageFormat mf = new MessageFormat(pattern);

            for(int i=0; i < data.length;i++) {
                Object[] objs = mf.parse(data[i]);      // 양식에서 인덱스값에 있는것만 뽑아 객체배열에 넣음.
                for(int j=0; j < objs.length; j++) {
                    System.out.print(objs[j] + ",");
                }
                System.out.println();
            }
        } // main
    }
```

# 3. java.time 패키지
+ 날짜 시간을 다루는데 가장 편한 패키지이다.
+ 패키지안에 서브패키지들이 많은데 핵심적인 클래스들은 그리 많지 않으니 능숙해지면 된다.
+ String 클래스처럼 불변의 특징을 지닌다. 즉 날짜와 시간을 변경할때마다 새로운 객체를 반환한다.

# 3.1 java.time 패키지의 핵심 클래스
+ 어느 정도 개념만 잡자. 아래에서 세부적으로 보고 코드를 보면서 이해해야 잘된다.
+ LocalTime 클래스 : 시간을 표현
+ LocalDate 클래스 : 날짜를 표현
+ LodalDateTime : 날짜 & 시간을 표현
+ ZonedDateTime : LodalDateTime + 시간대로 시간대까지 표현한다.
+ Instant 클래스 : 날짜와 시간을 초 단위로 표현하고 타임스탬프(날짜 시간을 하나의 정수로 표현)값을 사용한다. 그러므로 데이터 베이스에서 많이 사용된다.

### Period와 Duration
+ Period : 두 날짜간의 차이를 표현
+ Duration : 두 시간의 차이를 표현

### now(),of()
+ 객체를 생성하는 now()와 of()

### Temporal과 TemporalAmount
+ LocalTime, LocalDate, LodalDateTime, ZonedDateTime 등 날짜와 시간을 표현하기 위한 클래스들은 **Temporal, Temporal, TemporalAdjuster인터페이스를 구현**한것이다.
+ Period와 Duration은 TemporalAmount를 구현한 것이다.
+ Temporal은 그러므로 날짜와 시간을 표현하는 여러 메소드의 매개변수로 등장한다.

### TemporalUnit 과 TemporalField
+ 날짜와 시간의 **단위**를 정의해놓은 것이 **TemporalUnit인터페이스**이고, 이 인터페이스를 구현한 것이 열거형 ChronoUnit이다.
+ 년, 월, 일 등 날짜와 시간의**필드**를 정의해 놓은것이 **TemporalField인터페이스**이고, 이 인터페이스를 구현한 것이 열거형 ChronoFiled이다.

## 3.2 LocalDate와 LocalTime
```java
class Ch_22NewTimeEx1 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now(); // 오늘의 날짜
        LocalTime now  = LocalTime.now();  // 현재 시간

        LocalDate birthDate = LocalDate.of(1999, 12, 31); // 1999년 12월 31일
        LocalTime birthTime = LocalTime.of(23, 59, 59);   // 23시 59분 59초

        System.out.println("today="+today);
        System.out.println("now="+now);
        System.out.println("birthDate="+birthDate);   // 1999-12-31
        System.out.println("birthTime="+birthTime);   // 23:59:59

        System.out.println(birthDate.withYear(2000)); // 2000-12-31
        System.out.println(birthDate.plusDays(1));    // 2001-01-01
        System.out.println(birthDate.plus(1,ChronoUnit.DAYS)); //2001-01-02

        // 23:59:59 -> 23:00
        System.out.println(birthTime.truncatedTo(ChronoUnit.HOURS));
        // 특정 ChronoField의 범위를 알아내는 방법
        System.out.println(ChronoField.CLOCK_HOUR_OF_DAY.range()); // 1-24
        System.out.println(ChronoField.HOUR_OF_DAY.range());       // 0-23
    }
}
```
+ 가장 기본이 되는 클래스들이며, static메서드인 현재의 시간or날짜- now(), 지정한 시간or날짜 - of() 로 객체를 생성할수 있다.
### get(), getXXX....() 
+ LocalDate와 LocalTime 객체 안의 특정 필드의 값을 가져올 수 있다.
+ 다양한 메서드들로 값을 받을 수 있다. (getYear() , getMonth(), isLeapYear()) 등
+ 책 or API 참고하자.
```
int get       (TemporalField field)
int getLong   (TemporalField field)
```
+ int 범위에 들어가는 필드는 get을, 더 큰범위에 들어가는 필드는 getLong()을 쓴다.
+ 매개변수로 사용할 수 있는 TemporalField 목록은 책 or API를 참고하자.
+ 특정 필드가 가질수 있는 값의 범위를 알고 싶을때는 다음의 코드를 작성하면 된다.
```
        System.out.println(ChronoField.CLOCK_HOUR_OF_DAY.range()); // 1-24
        System.out.println(ChronoField.HOUR_OF_DAY.range());       // 0-23
```

### 필드의 값 변경하기 - with(), plus(), minus()
```
LocalDate with(TemporalField field, long newValue)
```
+ 날짜와 시간에서 특정 **필드값을 변경**하려면, with로 시작하는 메서드를 사용하면 된다.
+ 필드를 변경하는 메서드들은 항상 새로운 객체를 생성해서 반환하므로 아래처럼 대입연산자를 사용하자.
```
data = date.withYear(2000); // 연도를 2000으로 변경
time = time.withHour(12);   // 시간을 12시로 변경
```
+ 이외에도 plus(), minus()로 변경이 가능하다. 방식은 with와 같으므로 책 참고
```
LocalTime plus (TemporalAmount amountToAdd)
LocalTime plus (long amountToAdd, TemporalUnit unit)
```
+ truncatedTo() : 지정된 것보다 작은 단위의 필드를 모두 0으로 만든다.
```
LocalTime time = LocalTime.of(12, 34, 56);  // 12시 34분 56초
time = time.truncatedTo(ChonoUnit.Hours);   // 시 보다 작은 단위를 0으로
System.out.pringln(time);                   // 12:00
```
+ 열거형 TemporalUnit은 책이나 API를 참고하자.

### 날짜와 시간의 비교 - isAfter(), isBefore(), isEqual
```
boolean isAfter(ChronoLocalDate other)
boolean isBefore(ChronoLocalDate other)
boolean isEqual(ChronoLocalDate other)
```
+ equals 와 isEqual()는 연표(Era)가 다른 두 날짜를 비교하는데 있어서 다르다. 
+ ex) isEqual()을 사용하면 같은 2000년 1월 1일 이여도 일본Date와 한국Date를 비교시 true를 반환한다.(equals : false)

## 3.3 Instant
+ 에포크 타임부터 경과된 시간을 나노초 단위로 표현한다.
+ 날짜와 시간을 초 단위로 표현하고 타임스탬프(날짜 시간을 하나의 정수로 표현)값을 사용한다. 그러므로 데이터 베이스에서 많이 사용된다.
+ 아래 메서드를 통해 java.util.date와 변환이 가능해졌다.
```
static Date from(Instant instant)   //Instant -> Date
Instant toInstant()                 //Date -> Instant
```

## 3.4 LocalDateTime과 ZonedDateTime
``` java
class Ch_23NewTimeEx2 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2015, 12, 31); // 2015년 12월 31일
        LocalTime time = LocalTime.of(12,34,56);     // 12시 23분 56초

        // 2015년 12월 31일 12시 23분 56초
        LocalDateTime dt  = LocalDateTime.of(date, time);

        ZoneId zid = ZoneId.of("Asia/Seoul");
        ZonedDateTime zdt = dt.atZone(zid);
//		String strZid = zdt.getZone().getId();

        ZonedDateTime seoulTime = ZonedDateTime.now();
        ZoneId nyId = ZoneId.of("America/New_York");
        ZonedDateTime nyTime = ZonedDateTime.now().withZoneSameInstant(nyId);

        // ZonedDatetime -> OffsetDateTime
        OffsetDateTime odt = zdt.toOffsetDateTime();

        System.out.println(dt);         // 2015-12-31T12:34:56
        System.out.println(zid);        // Asia/Seoul
        System.out.println(zdt);        // 2015-12-31T12:34:56+09:00[Asia/Seoul]
        System.out.println(seoulTime);  // 2022-03-03T04:35:47.490800600+09:00[Asia/Seoul]
        System.out.println(nyTime);     // 2022-03-02T14:35:47.491800400-05:00[America/New_York]
        System.out.println(odt);        // 2015-12-31T12:34:56+09:00
    }
}
```
+ ZonedDateTime : LodalDateTime + 시간대로 시간대까지 표현한다
### LocalDate와 LocalTime으로 LocalDateTime() 만들기
+ LocalDate 에 attime(), LocalTime에 atDate() 메서드를 통해 LocalDateTime를 생성할 수 있다.
+ 다양한 버전의 of(), now()가 존재한다.

### LocalDatetime의 변환
+ toLocalDate(),  toLocalTime() 으로 변환 가능하다.

### LocalDateTime으로 ZonedDateTime 만들기
+ 코드참고(atZone()메서드)
+ ZoneId사용
### ZoneOffset
+ UTC부터 얼마나 떨어져있는지를 ZoneOffset으로 표현한다.
+ 한국은 9시간 더 빠르다.

### OffsetDateTime
+ ZoneId는 구역별 시간대와 관련된 규칙을 포함하고있지만, ZoneOffset은 단지 시간대를 시간의 차이로만 구분한다.
+ 그러므로 서로 다른 시단개에 존재하는 컴퓨터간의 통신에는 OffsetDateTime이 필요하다.
+ ZonedDateTime에 toOffsetDateTime()을 통해 변환이 가능하다.

### ZonedateTime의 변환
+ LocalDateTime 처럼, 날짜와 시간에 관련된 다른 클래스로 변환하는 메서드들을 가지고 있다.
+ ex) toLocalDate(), toLocalTime() 등등 

## 참고 
- LocalDate의 toEpochDay()라는 메서드는 EpochDay기준으로 날짜요일하나하나씩 세기때문에 계산하기 편하다.
## 3.5 TemporalAdjusters
+ 자주 쓰일만한 날짜 계산들을 대신 해주는 메서드들을 정의해놓은 클래스이다.
+ 책참고

### TemporalAdjuster ( s없으면 인터페이스)
```java
class DayAfterTomorrow implements TemporalAdjuster {        // 우리가 직접 구현한 adjustInto
    @Override
    public Temporal adjustInto(Temporal temporal) {
        return temporal.plus(2, ChronoUnit.DAYS);
    }
}

class Ch10_24NewTimeEx3 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate date  = today.with(new DayAfterTomorrow());       // Temporal를 매개변수로 가지는 with()

        p(today); // System.out.println(today); 2022-03-03
        p(date);  //2022-03-05

        p(today.with(firstDayOfNextMonth()));        // 다음 달의 첫 날
        p(today.with(firstDayOfMonth()));            // 이 달의 첫 날
        p(today.with(lastDayOfMonth()));             // 이 달의 마지막 날
        p(today.with(firstInMonth(TUESDAY)));        // 이 달의 첫번째 화요일
        p(today.with(lastInMonth(TUESDAY)));         // 이 달의 마지막 화요일
        p(today.with(previous(TUESDAY)));            // 지난 주 화요일
        p(today.with(previousOrSame(TUESDAY)));      // 지난 주 화요일(오늘 포함)
        p(today.with(next(TUESDAY)));                // 다음 주 화요일
        p(today.with(nextOrSame(TUESDAY)));          // 다음 주 화요일(오늘 포함)
        p(today.with(dayOfWeekInMonth(4, TUESDAY))); // 이 달의 4번째 화요일
    }

    static void p(Object obj) { // 라인의 길이를 줄이기 위해 새로 정의한 메서드
        System.out.println(obj);
    }
}
```
+ 만약 TemporalAdjusters에 필요한게 없다면 adjustInto()로 구현하고 with()를 이용하여 직접 구현해 줄 수도 있다.

## 3.6 Period와 Duration
+ Period : 날짜 차이 , Duration : 시간차이
```java
    class Ch_25NewTimeEx4 {
        public static void main(String[] args) {
            LocalDate date1 = LocalDate.of(2014,  1,  1);
            LocalDate date2 = LocalDate.of(2015, 12, 31);

            Period pe = Period.between(date1, date2);

            System.out.println("date1="+date1);                                 //2014-01-01
            System.out.println("date2="+date2);                                 //2015-12-31
            System.out.println("pe="+pe);                                       //P1Y11M30D

            System.out.println("YEAR=" +pe.get(ChronoUnit.YEARS));               //YEAR=1
            System.out.println("MONTH="+pe.get(ChronoUnit.MONTHS));              //MONTH=11
            System.out.println("DAY="  +pe.get(ChronoUnit.DAYS));                //DAY=30

            LocalTime time1 = LocalTime.of( 0, 0, 0);
            LocalTime time2 = LocalTime.of(12,34,56); // 12시간 23분 56초

            Duration du = Duration.between(time1, time2);                        //du=PT12H34M56S

            System.out.println("time1="+time1);                            //time1=00:00
            System.out.println("time2="+time2);                            //time2=12:34:56
            System.out.println("du="+du);                               //du=PT12H34M56S

            LocalTime tmpTime = LocalTime.of(0,0).plusSeconds(du.getSeconds());

            System.out.println("HOUR="  +tmpTime.getHour());               // HOUR=12
            System.out.println("MINUTE="+tmpTime.getMinute());             // MINUTE=34
            System.out.println("SECOND="+tmpTime.getSecond());             // SECOND=56
            System.out.println("NANO="  +tmpTime.getNano());               // NANO=0
        }
    }
```
+ 차이를 구한후 양수 또는 음수로 저장된다.
+ Duration은 getMinute, getHour가 아쉽게도 없다. 그러므로 필요에따라 LocalTime으로 변환해서 사용하는게 좋다.

### between 과 until
+ between은 static 메서드이고 until은 인스턴스 메서드이다.
+ until 은 년,월,일 으로 분리해서 저장하는 between가 다르게 D-day 구하기에는 더 좋다.

### of() with()
+ LocalDate와 LocalField에서 배운것과 같다.

### 사칙연산, 비교연산, 기타메서드
+ plus(), minus() 외의  mutipliedBy(), divideBy() 메서드도 있다.
+ boolean을 리턴하는 isNegative() , isZero() / negate(), abs()도 존재한다.(단 Period는 negate가 없다)

### 다른 단위로 변환 
- toTotalMonths(), toDays(), toHours(), toMinutes()
- 책 참고

## 3.7 파싱과 포맷
+ 날짜와 시간을 원하는 형식으로 출력하고 parsing 하는방법이다.
```java
class Ch_26DateFormatterEx1 {
    public static void main(String[] args) {
        ZonedDateTime zdateTime = ZonedDateTime.now();

        String[] patternArr = {
                "yyyy-MM-dd HH:mm:ss",
                "''yy년 MMM dd일 E요일",
                "yyyy-MM-dd HH:mm:ss.SSS Z VV",
                "yyyy-MM-dd hh:mm:ss a",
                "오늘은 올 해의 D번째 날입니다.",
                "오늘은 이 달의 d번째 날입니다.",
                "오늘은 올 해의 w번째 주입니다.",
                "오늘은 이 달의 W번째 주입니다.",
                "오늘은 이 달의 W번째 E요일입니다."
        };

        for(String p : patternArr) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(p);
            System.out.println(zdateTime.format(formatter));
        }
    } // main의 끝

//    2022-03-03 05:15:32
//    '22년 3월 03일 목요일
//    2022-03-03 05:15:32.457 +0900 Asia/Seoul
//    2022-03-03 05:15:32 오전
//    오늘은 올 해의 62번째 날입니다.
//    오늘은 이 달의 3번째 날입니다.
//    오늘은 올 해의 10번째 주입니다.
//    오늘은 이 달의 1번째 주입니다.
//    오늘은 이 달의 1번째 목요일입니다.
}
```
+ java.time.format패키지안의 DateTimeFormatter가 핵심이다.
+ 이 클래스에는 자주쓰이는 형식들이 정의되어 있으며, 직접 정의해서 사용 할 수도 있다. 
+ format() 메서드를 통해 날짜와 시간을 형식화 한다.
+ DateTimeFormatter에는 상수로 정의된 (날짜,시간)형식들이 존재한다. 책참고
+ 이 메서드는 DateTimeFormatter뿐만 아니라 LocalDate나 LocalTime클래스에도 존재하니 원하는쪽에서 사용하면 된다.

### 로케일에 종속된 형식화
+ static 메서드 ofLocalizedDate(), ofLocalizedTime(), ofLocalizedDateTime()은 로케일에 종속적인 포맷터를 생성한다.
+ 로케일은 사용자의 언어, 국가뿐 아니라 사용자 인터페이스에서 사용자가 선호하는 사항을 지정한 매개 변수의 모임이다.
+ 로케일 목록은 FormatStyle에 있으며 책을 참고하자!

### 출력형식 직접 정의하기
+ ofPattern()으로 출력형식을 직접 작성할 수 있다.(코드참고)
+ 출력 형식에 사용 되는 기호들은 책과 코드를 참고하자.

### 문자열을 날짜와 시간으로 파싱하기
+ 이전 클래스들처럼 static 메서드 parse()를 사용하면 된다.
```
static LocalDateTime parse(charSequence text)
static LocalDateTime parse(charSequence text, DataTimeFormatter formatter)
```
+ formatter자리에 상수로 정의된형식(ISO_DATE_TIME) 이나 우리가 만든 pattern을 넣으면 된다.
```java
class Ch_27DateFormatterEx2 {
        public static void main(String[] args) {

            LocalDate newYear = LocalDate.parse("2016-01-01", DateTimeFormatter.ISO_LOCAL_DATE);

            LocalDate     date     = LocalDate.parse("2001-01-01");
            LocalTime     time     = LocalTime.parse("23:59:59");
            LocalDateTime dateTime = LocalDateTime.parse("2001-01-01T23:59:59");

            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime endOfYear   = LocalDateTime.parse("2015-12-31 23:59:59", pattern);

            System.out.println(newYear);    //2016-01-01
            System.out.println(date);       //2001-01-01
            System.out.println(time);       //23:59:59
            System.out.println(dateTime);   //2001-01-01T23:59:59
            System.out.println(endOfYear);  //2015-12-31T23:59:59
        } // main의 끝
    }
```

