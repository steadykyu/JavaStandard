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
```
1. Calendar를 Date로 변환
  Calendar cal = Calendar.getInstance();
  ....
  Date d = new Date(cal.getTimeInMillis());
  ----------------------------------------
  Calendar cal = Calendar.getInstance();
  Date d2 = cal.getTime()       // Calendar를 Date로 변환
  
  
2. Date를 Calendar로 변환
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
