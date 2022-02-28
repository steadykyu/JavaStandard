# 1. java.lang 패키지
+ 자바프로그래밍에 필요한 기본이 되는 클래스로, import문을 사용하지 않아도 사용 가능하다.(ex)String, System클래스등)

## 1.1 Object 클래스
+ 모든 클래스의 조상인 Object클래스의 멤버들은 모든 클래스에서 사용 가능하다.
> 종류(세부적인 내용은 API나 책 참고)
```
clone()
finalize()
getClass()
hashCode()
toString()
notify()
notifyAll()
wait()
wait(long timeout)
wait(long timeout, int nanos)
```
+ 자주쓰는 메서드들만 분석해보자.
### equals
+ Object 클래스의 equals 메서드는 주소값으로 비교한다.
+ 주소값이 아닌 **값 자체를 비교** 하기위해서는 오버라이딩하여 사용하면 된다.(코드참고)
+ String클래스, Date,File,Wrapper 클래스의 equals 들은 이미 오버라이딩 되어있어 값만 비교한다.
+ StringBuffer클래스는 오버라이딩 되어있지 않으므로, 주소값을 비교한다.

### hashcode()
+ 해싱기법에 사용되는 해시함수를 구현한 것이다.
+ 해시함수는 찾고자하는 값을 입력하면, 그 값이 저장된 위치를 알려주는 해시코드를 반환한다.(11장에서 세부적으로 배움)
+ String클래스에서 hashcode()적용시 문자열내용이 같으면, 항상 동일한 해시코드값을 얻도록 오버라이딩 되어있다.

### toString()
+ Object클래스의 toString()은 클래스이름에 16진수의 해시코드를 얻게 된다.(ex Card@139a55)
+ String클래스는 String인스턴스가 가진 문자열값을, Date클래스의 경우 Date인스턴스가 갖고있는 날짜와 시간을 변환하도록 오버라이딩 되어있다.
+ Object의 toString()은 public 접근제한자를 가지므로, 그 자식들은 무조건 public으로 밖에 설정할 수 없다.
```
부모보다 자식의 접근 범위가 같거나 넓어야하므로
```
### clone()
+ 자신을 복제하여 새로운 인스턴스를 생성한다.
+ clone메서드를 통해 만들어진 인스턴스로 작업하여 원래의 인스턴스는 보존할 수 있다.
+ Object클래스에 정의된 clone()은 단순히 인스턴스의 값만 복사한다. 
+ 그러므로 참조타입의 인스턴스 변수가 있는 클래스(클래스를 인스턴스변수로 가지는 클래스)는 완전한 복제가 일어나지 않는다.(아래 얕은복사내용 참고)
+ clone을 사용하기 위해서는 Cloneable 인터페이스를 구현하고, clone의 접근제어자를 protected에서 public으로 오버라이딩해야한다.
```
class Point implements Cloneable{

(...........)

//    public Object clone(){
public Point clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Point)obj; //공변 반환타입
        //return obj;
        }
}
```
+ Cloneable를 구현한 Point 클래스는 이제 clone()를 통해 복제가 가능해진다.(원래 인스턴스 보호를 위해 위처럼 설계되었다.)

### 공변 반환 타입
+ 오버라이딩할때 조상 메서드의 반환타입을 자손클래스의 타입으로 변경을 허용하는 것
+ 위 코드에서 조상타입의 clone은 Object를 반환하지만, Point로 형변환하고 반환시키면 메서드 호출시 편하게 사용할 수 있다.
+ 기본형 배열안에서는 public으로 오버라이딩하였기 때문에, 직접 호출이 가능하다. 아래코드를 보자.
+ 원본과 같은 타입으로 반환하므로 형변환이 필요 없다.
```java
public class Ch9_8CloneEx2 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int[] arrClone = arr.clone();     // 이미 오버라이딩 되어있음.
        arrClone[0] = 6;

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arrClone));
    }
}
```
+ 배열뿐만아니라, java.util 패키지의 Vector, ArrayList, LinkedList, HashSet, TreeSet, Hashmap, TreeMap, Calendar, Date와 같은 클래스들이
아래의 방식으로 복제가 가능하다.
```
ArrayList list = new ArrayList();
...
ArrayList list2 = (ArrayList)list.clone();
```
+ 배열과는 약간 다르게 반환타입이 Object이므로, 형변환을 시켜주어야 복사한 인스턴스값을 가져올수 있다

### 얕은 복사와 깊은 복사
+ 기본형 배열의 경우, 요소의 값이 기본형 값이라 문제가 되지 않지만,
+ 객체배열을 clone()으로 복제하는 경우에는 원본과 복제본이 같은 객체를 공유하므로 완전한 복제가 아니다. 이를 **얕은 복사**라고한다.
+ 원본과 복사본이 서로 다른 객체를 참조하여 서로 영향을 끼치지 않을때, 즉 참조하는 객체까지 복제하는 것을 **깊은 복사** 라고한다.
```java
class Circle implements Cloneable{
    Point p;                                // 클래스안의 클래스(참조변수)
    double r;
    
   public Circle(Point p, double r) {       //생성자를 통해 주입
        this.p = p;
        this.r = r;
    }

    public Circle shallowCopy(){            //얕은 복사
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Circle) obj;
    }

    public Circle deepCopy(){               //깊은 복사
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Circle c = (Circle)obj;
        c.p = new Point(this.p.x, this.p.y);            // 참조변수에 새로운 객체를 넣어줌.

        return c;
    }
    
    (....)
```
+ 위처럼  깊은 복사가 가능하다.(코드참고)

### getClass()
+ Class객체는 클래스의 모든 정보(클래스에 정의된 멤버의 이름이나, 개수등)를 담고있으며, 클래스당 1개만 존재한다.
+ .class 파일이 **클래스 로더**에 의해서 메모리에 올라갈때 자동으로 생성된다.
+ .class -> classLoader -> Class객체
+ getclass()를 사용한 객체가 메모리에 존재하는지 확인하고 있으면 Class객체를 참조하여 반환하고, 없으면 클래스패스에 경로를 따라 .class을 찾는다.
+ 못찾으면 ClassNotFoundException이 발생하고, 찾으면 .class 찾아서 Class객체로 변환한다.

### Class객체를 얻는 방법
```java
//Card 클래스가 있다고 하자.
Class cObj = new Card().getclass();
Class cObj = Card.class;
Class cObj = Class.forName("Card");
```
> 객체생성법
```
Card c = Card.class.newInstance();
```
+ 동적으로 객체를 생성하고 메서드를 호출하는 방식으로 리플렉션이 있다. API로 알아보자.

## 1.2 String 클래스
### 변경 불가능한(immutable) 클래스
+ String 클래스는 인스턴스 생성시 생성자의 매개변수로 입력받은 문자열을 문자형 배열(char[])로 저장한다.
+ 한번 생성된 String인스턴스는 읽어만 올수 있고 변경할 수 없다. 연산자등으로 문자열을 바꾸면 새로운 String인스턴스가 생성되는 것이다.
+ 메모리가 문제 될수 있으므로, 문자열을 다루는 많은 작업이 필요한 경우 StringBuffer클래스를 사용하자.

### 문자열의 비교
+ 문자열 리터럴을 지정하는 방법과 String클래스의 생성자를 사용하는 방법이 있다.
```java
String str1 = "abc";            //문자열 리터럴
String str3 = new String("abc");
char[] chrArr = new char[]{'a','b','c'};
String str3 = new String(chrArr);

```
> 문자열 리터럴
```
1. 모든 문자열 리터럴은 컴파일시에 클래스 파일에 저장되며, 한번만 저장된다.
2. 이 문자열은 String인스턴스 인데 이 하나의 인스턴스를 공유하는 것이다.
3. 클래스 파일의 리터럴 목록들은 클래스로더에 의해 메모리에 올라갈때 JVM의 상수저장소로 저장된다
```

### 빈 문자열
+ 길이가 0인 배열이 존재할 수 있다.
```
String s = ""; 
new char[0]와 같은 의미이다.
```
> 참고
```
String s = "";    //기본값 null
char c = ' ';     //기본값 \u0000
```
+ 각 타입의 기본값으로 초기화 하기보다는 공백으로 초기화하는것이 더 일반적이다.

### String 클래스의 생성자와 메서드
+ (중요)책 또는 API를 참고해서 익숙해지기.

### join()과 StringJoiner
+ 책 또는 API를 참고해서 익숙해지기.
### 유니코드의 보충문자
+ 책이나 API에서 보면 매개변수의 타입이 char인 것도 있고 int인 것도 있다.
+ 이는 확장된 유니코드가 20비트가 되면서, 하나의 문자를 char로 다루지 못하고 int로 밖에 다룰 수 없기 때문이다.
+ 이를 보충문자라고하며, String 메서드에는 이 보충문자를 지원하는 것도 지원하지 않는 메서드들도 있다.

### 문자 인코딩 변환
+ getBytes를 사용하면, 문자열의 문자 인코딩을 다른 인코딩으로 변경 할 수 있다.
+ 문자열 리터럴에 포함되는 문자들은 os의 인코딩을 기본으로 사용한다.(WINDOWS - CP949사용)
+ 아래 처럼 변경이 가능하다.
```
byte[] utf8_str = "가".getBytes("UTF-8);
String str = new String(utf8_str,"UTF-8");
```

### String.format()
+ printf와 사용법이 같다.

### 기본형 값을 String으로 변환
+ 빈 문자열을 더하거나 valueOf()메서드를 사용하자.
```
String str = ""+100;
String str2 = String.valueOf(100);
```

### String을 기본형 값으로 변환
```
int i = Integer.parseInt("100");
int i2 = Integer.valueOf("100");  

```
+ valueOf은 반환타입만 다르지, parseInt를 포함하고 있는 메서드이다.(즉 같다)
+ valueOf의 반환 타입은 Integer이지만, 곧 배울 오토박싱에 의해 컴파일시 int로 자동 변환된다.
+ 더 다양한 변환 방법을 알고 싶다면 책을 참고하자.(기본형->문자열 / 문자열 -> 기본형)
+ parseInt()는 문자열에 *공백 또는 문자가 포함되어있는경우* 예외가 발생하므로, 매개변수에 .trim()을 붙여주는게 좋다.

> subString에서 주의할점
```
String substring(int begin, int end);
1. index가 0부터 시작한다.
2. start부터 end의 범위중 end위치에 있는 문자는 결과에 포함되지 않는다!
```

## 1.3 StringBuffer 클래스와 StringBuilder
+ 편집할 문자열 길이를 고려하려 버퍼의 길이를 충분히 잡아주자. 그래야 작업효율을 떨어트리지 않는다.

### StringBuffer의 생성자
+ 버퍼의 크기를 지정하지 않으면 버퍼의 크기가 16이 되도록 생성자에 설정되어있다.

### StringBuffer의 변경
+ String과 달리 StringBuffer는 인스턴스의 내용을 변경할 수 있다.
+ StringBuffer의 여러 메서드들은 자기 자신을 반환하는 메서드들이 많은데, 이를 통해 모두 같은 인스턴스를 가리키게 할 수 있다.

### StringBuffer의 비교
+ equals가 오버라이딩 되어 있지 않으므로, 주소값을 가져온다.
+ 값 비교를 위해서는 String 인스턴스를 얻은 다음, String의 equals를 사용해야한다.
```
String s = sb.toSting()  //sb는 StringBuffer의 참조변수
```

### StringBuffer클래스의 생성자와 메서드
+ (중요)책 또는 API를 참고해서 익숙해지기.

### StringBuilder란
+ StringBuffer와 완전히 똑같은 기능이지만, 멀티쓰레드에 안전하도록 동기화 되어있다.
+ 멀티쓰레드로 작성된 프로그램이 아닐 경우, StringBuffer의 동기화는 불필요하게 성능을 떨어트린다.
+ (13장에서 공부)

## 1.4 Math 클래스
+ Math클래스의 생성자는 접근제어자가 private하므로 인스턴스를 생성할 수 없다.(일부로 설계)
+ static 메서드들과 2개의 상수만 존재한다.(E-자연로그의 밑, PI)

### 올림 버림 반올림
> 반올림 방식(소수 둘째자리까지 보고싶을때)
```
1. 원래 값에 100을 곱한다.
2. 위의 결과에 Math.round를 사용한다.(Long형 반환)
3. 위의 결과를 다시 100.0으로 나눈다.(Long
```
+ 소수 첫째자리에서 반올림이 일어난다.
+ 음수에대한 올림과 버림에 주의하자.(ex) ceil(-1.5) = -1) , floot(-1.5) = -2)
+ 비슷한 메서드로 rint()가 있지만, 다른점이 존재한다.
```
1. 반환값이 double이다.
2. 두 정수 가운데 있는 값은 가장 가까운 짝수로 반환한다.(rint(-1.5) = -2.0)
```

### 예외를 발생시키는 메서드
+ int addExact(int x, int y), int negateExace(int a)....etc 
+ 메서드 이름에 Exact가 포함된 메서드들은 오버플로우를 감지하기 위해 만들어 졌다.
+ 일반적인 연산자들은 단지 결과를 반환할뿐, 오버플로우 된 값인지는 알려주지 않는다.
+ 그러나 Exact가 포함된 메서드은 오버플로우가 발생하면 예외(ArithmeticException)을 발생시킨다.
+ 책 참고하기

### 삼각함수와 지수, 로그
+ sqrt(), pow(), sin(), cos(), toRadian(), toDegrees()등
+ 책 참고하기

### StictMath클래스
+ Math클래스는 성능을 위해 JVM이 설치된 OS의 메서드를 호출해서 사용한다.(즉 OS의존적이다.)
+ OS 마다 처리방법이 다른데, 이런 차이를 없애도록 어떤 OS에서 실행해도 같은 결과를 얻는 StrictMath클래스가 존재한다.

### Math 클래스의 메서드
+ 책orAPI참고

## 1.5 래퍼(wrapper) 클래스
+ 기본형 변수도 객체로 다루어져야하는 경우들이 있다. 이럴때 wrapper 클래스를 사용한다.
```
매개변수로 객체를 요구할때
기본형 값이 아닌 객체로 저장해야 할때
객체간의 비교가 필요할떄 
..etc
```
+ wrapper 클래스는 각 자료형의 알맞은 값을 내부적으로 필드형태로 저장하고 있다.

### Number클래스
+ 추상클래스로 내부적으로 숫자를 멤버변수로 갖는 래퍼클래스들의 조상이다.
+ Byte Short Integer Long Float Double BigInteger BigDecimal가 존재한다.

### 문자열을 숫자로 변환하기.
```
int i = new Integer("100").intValue();
int i2 = Integer.parseInt("100");
Interger i3 = Integer.valueOf("100");
```
> 다른 진법 숫자일대도 변환이가능하다.
```
int i5 = Integer.parseInt("100",16); // 100(16) - > 256
int i6 = Integer.parseInt("FF",16); // FF(16) - > 255
- 진법을 생략하면 10진법으로 적용된다.
```

### 오토박싱 언박싱
+ 이전에는 기본형과 참조형 연산이 불가능 했으나, 1.5이후로는 컴파일러가 자동으로 변환하는 코드를 넣어준다.
+ 그러므로 wrapper는 기본형(**언박싱**)으로 기본형은 wrapper(**오토박싱**)로 자동 변환해준다.
```
ArrayList<Integer> list = new ArrayList<Integer>();
list.add(10);             //오토박싱 10 -> new Integer(10)
int value = list.get(0)   //언박싱 new Integer(10) -> 10
```
+ 이전까지의 자바원칙이 바뀌는 것이아니라 그저 변환코드를 추가해준 것일 뿐임을 명심하자.(ex) intValue() or valueOf()등 )

# 2. 유용한 클래스
## 2.1 java.util.Objects 클래스
+ 모든 메서드가 static이고, 객체의 비교나 널 체크에 유용하다.
```
static boolean isNull(Object obj)
static boolean nonNull(Object obj)
static <T> T requireNonNull(T obj, String message)
static <T> T requireNonNull(T obj, String message)              // 매개변수 유효성 검사에 사용
static <T> T requireNonNull(T obj, Supplier<String> messageSupplier)
static int compare(Object a, Object a, Comparator c)            // 같으면 0, 크면 양수, 작으면 음수를 반환한다.
```
+ Objects에도 equals나 toSting(), hashcode()가 존재하는데 **null 검사를 해준다는 것** 외에는 다른게 없다.
+ deepEquals(), hash(Object.... values) 등 존재.
+ static import문을 써서 메서드이름만으로 호출하면, Object의 메서드들과 충돌할 수 있다. 그럴땐 클래스이름.메서드명 으로 해주자.

## 2.2 java.util.Random 클래스
+ Math.random이 바로 이 Random클래스의 인스턴스를 생성하는 것으로 동등한 결과를 가진다.
+ 차이점 : 종자값(seed)을 설정할 수 있다. default 종자값은 현재시간이며, 같은 종자값을 넣으면 같은 난수가 나온다.

### Random클래스의 생성자와 메서드
+ 책 참고하기

## 2.3 정규식(Regular Expression)
+ java.util.regex패키지
+ 정규식은 텍스트데이터 중에서 원하는 조건(패턴,pattern)과 일치하는 문자열을 찾아내기 위해 사용한다.
+ 미리 정의된 기호와 문자가 존재한다.(굉장히 많기때문에 API를 참조해야함)
> 아래의 방식으로 진행된다.
```
1. 정규식을 매개변수로 Pattern 클래스의 statuc 메서드인 Pattern compile(String regex)을 호출하여 Pattern 인스턴스를 얻는다.
  Pattern p = Pattern.compile("c[a-z]*);
  
2. 정규식으로 비교할 대상을 매개변수로 Pattern 클래스의 Matcher matcher(CharSequence input)를 호출하여 Matcher 인스턴스를 얻는다.
  Matcher m = p.matcher(data[i]);  // data[]는 여러 String 배열
  
3. Matcher 인스턴스에 boolean matches()를 호출해서 정규식에 부합하는지 확인한다.
  if(m.matches())
```
+ 정규식의 일부를 괄호로 묵어서 그룹화 할 수 있다.
```
 String pattern = "(0\\d{1,2})-(\\d{3,4})-(\\d{4})";
      (....)
 while(m.find()){
            System.out.println(++i + ": " + m.group() + " -> " + m.group(1)
                                + ", " + m.group(2) + ", " + m.group(3));
       }    
```
+ group()로 문자열 전체를, m.group(1)으로 첫번쨰 그룹을 얻을 수 있다. 이후는 동일 방식이다.
+ find()는 패턴과 일치하는 부분을 찾아내면 true를 반환하고, 없으면 false를 반환한다.
+ find를 호출한후 다시 호출하면 이전에 발견한 패턴과 일치하는 부분 끝의 **다음에서부터** 다시 패턴매칭을 시작한다.
+ 위 예시로 따지면 그룹1 그룹2 그룹3 순서로 매칭한다. 
+ 더 자세한 동작방식은 책을 참고하자.(m.start, m.end, m.appendReplacement(StringBuffer, String), m.appendTail(StingBuffer)

## 2.4 Scanner클래스
+ java.util.Scanner
+ 화면, 파일, 문자열과 같은 입력소스로부터 문자데이터를 읽어오는데 도움을 준다.
+ 정규식 표현을 이용한 라인단위 검색을 지원하며 구분자에도 정규식 표현이 가능하다.
> 관련 메서드
```
Scanner useDelimiter(Pattern pattern)
Scanner useDelimiter(String string)
```
+ nextLine(), hasnextLine()nextInt(), nextLong(), ...etc 메서드들이 존재한다. 책orAPI참고

## 2.5 StringTokenizer 클래스
+ java.util.StringTokenizer
+ 긴 문자열을 지정된 구분자를 기준으로 토큰(token)이라는 여러개의 문자열로 잘라내는데 사용한다.
+ String의 split(String regex)을 써도 되지만, 정규식표현을 써야하는 split보다 좀더 간단하고 명확하게 결과를 얻을 수 있다.
+ 그러나 구분자로 단 **하나의** 문자 밖에 사용하지 못하므로, 복잡하게 문자열을 나눌때는 정규식을 써야한다.
```
구분자 "+-*/" 라고 가정하자.
"+-*/"로 하나의 구분자로 가지는것이 아니라 "+", "-", "*", "/" 각각의 문자가 모두 구분자로 가진다.
```
+ 또한 split과 다르게 빈문자열을 토큰으로 인식하지 않는다.
```
"100,,,200,300"
spilt           "100"/" "/ " "/"200"/"300"
StringTokenizer "100"/"200"/"300"
```

### StringTokenizer 생성자와 메서드
+ 책 API참고

### 2.6 BigInteger 클래스
+ java.math.BigInteger
+ long의 19자리 보다 더 큰 수를 다룰때 사용한다.
+ 내부적으로 int 배열을 사용해서 값을 다룬다. 그러므로 성능이 long보다는 떨어진다.

### BigInteger의 생성
+ 주로 문자열로 숫자를 표현한다. 정수형 리터럴로는 표현할수 있는 값의 한계가 있기 때문이다.
```
BigInteger val = new BigInteger("12345678901234567890)"
```
### 다른 타입으로의 변환
+ 문자열로 변환 : toString()
+ byte 배열로 변환 : tobyteArray()
+ 기본형으로 변환 : intValue(),doubleValue() ..etc(Number클래스로 부터 상속받음)
+ Exact형 메서드 : intValueExact(), longValueExact()

### BigInteger 연산
```
BigInteger add(BigInteger val)
같은방식으로 subtract, multiply, divide, remainder 가 존재한다.
```
+ BigInteger는 불변이므로 위의 연산을 할떄마다 새로운 BigInteger인스턴스가 생성된다.

### 비트연산 메서드
```
bitCount()   / bitLength()     / testBit(int n) 
setBit(int n)/ clearBit(int n) / flipBit(int n)
```
+ 책 API 참고

## 2.7 BigDecimamal 클래스
+ java.math.BigDecimamal
+ 실수형과는 달리 정수와 10의 제곱의 곱으로 실수를 표현한다.
```
정수 x 10^(-scale)
```
> 클래스 내부
```
private final BigInteger intVal; // 정수
private final int scale;         // 지수
private transient int precision; // 정밀도 - 정수의 자릿수
```

### BigDecimal의 생성
+ 기본형 리터럴로는 표현하는데 한계가 존재하므로, **문자열**로 숫자를 표현하는 것이 일반적이다.
+ 문자열과 비교했을때 2진 실수로 저장하는 double 타입을 매개변수로 갖는 생성자를 사용하면 오차가 발생 할 수 있다.

### 다른 타입으로의 변환
> 문자열로 반환
```
String toPlanString()     // 어떤 경우에도 다른 기호없이 숫자로만 표현
String toString()         // 필요하면 지수형태로 표현할 수 있음
```

> 기본형으로 반환, Exact붙은 메서드 반환
+ 기본형으로 변환 : intValue(),doubleValue() ..etc(Number 클래스로부터 상속받음)
+ Exact형 메서드 : intValueExact(), longValueExact()

### BigDecimal의 연산
```
BigDecimal add(BigDecimal val)
같은방식으로 subtract, multiply, divide, remainder 가 존재한다.
```
+ BigDecimal는 불변이므로 위의 연산을 할떄마다 새로운 BigDecimal인스턴스가 생성된다.
+ 곱셉에서는 두 피연산자의 scale을 더하고 나눗셈에서는 뺀다.
+ 덧셈뺼셈에서는 둘중 자리수가 높은쪽이 scale 결과값이 된다.

### 반올림 모드 divide()와 setScale()
```
BigDecimal divide(BigDecimal divisor, int scale, RoundingMode roundingMode, ....)
```
+ scale값에 몇번째 자리에서 반올림 할지를 결정한다.
+ RoundingMode에 ROUND_로 시작하는 상수들 중 하나를 선택하면 된다.(주로 HALF_UP을 많이쓴다.)
+ 더 많은 옵션은 책참고

### java.math.MathContext
+ 반올림모드와 정밀도(precision)을 하나로 묶어놓은 것이다.
+ 위의 divide와 다르게 정수값 매개변수로 precision를 가진다. 이는 정수와 소수점 이하를 모두 포함하는 자리수를 의미한다.
+ MathContext의 결과는 precision을 가지고 반올림을 한다
+ bd1의 123456에서 세번째 자리에서 반올림 하여 12가 되고, 여기 scale 값을 반영시켜 1.2E + 2가 된다.
```java
BigDecimal bd1 = new BigDecimal("123.456");
BigDecimal bd2 = new BigDecimal("1.0");

System.out.println(bd1.divide(bd2,2,HALF_UP)); //123.46
System.out.println(bd1.divide(bd2,
                              new MathContext(2,HALF_UP))); // 1.2E + 2
```

### scale 변경
+ BigDecimal을 10으로 곱하거나 나누는 대신 sclae 값을 변경하므로써 같은 값을 얻을 수 있다.
```
System.out.println(bd1.setScale(2, HALF_UP)); // 123.46
```
+ scale을 변경하는 것은 10의 n제곱으로 나누는 것과 같으므로, divider()를 호출할때 처럼 오차가 발생할 수 있다.
+ 그러므로 반올림모드를 지정해주어야한다.

****
# 추가사항
### valueOf 추가사항 - char[]배열을 String으로 바꾸기
```java
String str1 = new String(result)        // "   가나다"
String str2 = String.valueOf(result)    // "가나다"
//이거 쓰면 내장되어있는 parseChar()메서드를 예외없이 동작시키기 위해 trim()으로 char[] 배열의 공백을 다 제거해버리는 것 같다.
//char[] 의 원본 값을 문자열에 다 가져가고 싶다면 위처럼 String생성 주입으로 해주자.(9_10예제)
```
### String을 char[]배열로 바꾸기.
```java
1. for문과 String.chatAt(index) 이용하기
```java
    public static void main(String[] args) {
        String s1 = "First String";
        char[] charArray = new char[s1.length()];
        for (int i = 0; i < s1.length(); i++) {
            charArray[i] = s1.charAt(i);
            System.out.print(charArray[i]);
        }
    }
```
2.String.toCharArray() 이용하기.
```
public class DeclareCharArray {
    public static void main(String[] args) {
        String s1 = "First String";
        char[] charArray = s1.toCharArray();
        for (char c : charArray) {
            System.out.print(" " + c);
        }
    }
}
```
+ 결과 :  F i r s t   S t r i n g
