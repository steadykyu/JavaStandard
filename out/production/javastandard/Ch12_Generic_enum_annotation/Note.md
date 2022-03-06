# 1.지네릭스(Generics)
+ 이제는 지네릭스를 모르고는 API문서도 읽기 힘들정도로 대중화 되었다.
+ 너무 이번장에 머무르지 않고, 다른 장들에서 활용하는 모습을 통해 깊게 이해하는 방향으로 학습하자

## 1.1 지네릭스란?
+ 다양한 타입의 객체들을 다루는 메서드나 컬렉션 클래스에 **컴파일시의 타입체크**를 해주는 기능이다.
### 장점
1. 타입 안정성을 제공한다.
2. 타입체크와 형변환을 생략할수 있으므로 코드가 간결해진다.
    + ArrayList에 한 자료형만 넣고 싶을때, 지네릭스를 통하여 가능하다.(안정성, 타입체크)
    + ex) Object 자료형을 쓰면, ArrayList 안의 객체인스턴스를 사용할때마다 알맞은 자손 클래스로 형변환을 해야하지만 지네릭스를 통해 생략가능하다.
    
## 1.2 지네릭 클래스의 선언
```
class Box<T>
  T item;
  void setItem(T item) { this.item = item; }
  T getItem() { return item; }
```
+ T를 타입변수(Type variable)라고 하며, T대신 다른 글자를 사용해도 된다.
    + ex) AraayList<E>, Map<K,V>
+ **기호만 다를뿐 임의의 참조형 타입을 의미하는 것은 모두 같다.**
+ 기존에는 다양한 종류의 타입을 다루는 메서드의 매개변수나 리턴타입으로 Object 타입의 참조변수를 많이 사용하여 형변환이 불가피 했지만,
이제는 지네릭으로 원하는 타입을 지정하기만 하면 되는 것이다.


### 지네릭스의 용어
```
class Box<T>
```
+ Box<T> : 지네릭 클래스. "T의 Box" 또는 "T Box" 라고 읽는다.
+ T      : 타입변수 또는 타입 매개변수  ( parameterized type : 매개변수화된 타입, 대입된 타입)
+ Box    : 원시 타입(raw Type)
+ 작업방식 : Box<String>, Box<Integer> 는 둘다 같은 클래스이고, 컴파일 후에 원시타입인 Box로 바뀐다.

### 지네릭스의 제한
+ T는 인스턴스변수로 간주된다.
+ 그러므로 static 멤버에 타입변수 T를 사용할 수 없다.(필드 - static T variable; : 에러발생)
+ variable 필드가 T로 인해 String, Integer, 이외의 다른 클래스가 될수 있는데, 이는 지정한 타입과 관계없이 동일해야하는 static멤버에서는 사용할 수 없다.
+ 지네릭 배열을 생성할수 없다.
```
T[] itemArr; // 가능
T[] tempArr = new T[10] //불가능
```
+ new 연산자는 컴파일 시점에 타입 T가 무엇인지 정확히 알아야하기 때문에 지네릭 배열을 생성할 수 없다.
+ instanceof도 같은 이유로 사용할 수 없다.
+ 참고 : newInstance() 나 Object 배열을 생성한 후 복사한 다음에 T[]로 형변환 하는 방법으로 사용 가능하긴 하다.

## 1.3 지네릭 클래스의 객체 생성과 사용
+ Box<T> 는 T 타입의 객체만 저장할 수 있다.
+ 참조 변수와 생성자( 참조변수 자료형과 생성자에 대입된 타입이 일치)
```
Box<Apple> appleBox = new Box<Apple>();
Box<Apple> appleBox = new Box<Grape>();  // 에러
```
+ 상속관계
```
Box<Fruit> appleBox = new Box<Apple>(); // 에러
Box<Apple> appleBox = new FruitBox<Apple>();    // Box를 상속 하는 FruitBox, 이 case는 가능
```
+ 추정이 가능한 경우 생략
```
Box<Apple> appleBox = new Box<>(); 
```
+ 매개변수에서 사용
> Fruit 과 Apple(자식)은 상속관계
```
Box<Fruit> fruitBox = new Box<Fruit>(); 
(void add(Fruit fruit){...})  //
fruitBox.add(new Fruit);
fruitBox.add(new Apple);    // 가능! ( 'Fruit fruit = Apple 인스턴스' 이므로)
fruitBox.add(new Fruit2);   // 불가능(Fruit이 와야함)
```
    
## 1.4 제한된 지네릭 클래스
+ 한 종류의 타입이 오도록 제한하기는 했지만, 이 타입에는 모든 종류의 타입 사용이 가능하다. 이를 제한해보자
```
class FruitBox<T extends Fruit>{
    ArrayList<T> list = new ArrayList<T>();
    
    }
============================================
FruitBox<Apple> appleBox = new FruitBox<Apple>();   // OK    
FruitBox<Toy> appleBox = new FruitBox<Toy>();       // 에러 발생    
```
+ FruitBox는 한종류(T)의 타입만 오도록 설정했지만, 이제는 T 에는 Fruit의 자식 클래스들만 올수 있게 설정하였다.
+ 매개변수화된 타입도 이제 다형성이 가능해 진것이다.
```
interface Eatable {}
class FruitBox<T extends Eatable> {...}
=============================================================
class FruitBox<T extends Fruit & Eatable> {...}
```
+ 인터페이스를 구현해야한하는 클래스가 오돍 제약이 필요하다면, 이때 implements가 아닌 **extends**를 사용한다.
+ 자손이면서 동시에 구현 한다면 "&" 기호를 사용한다.
    
## 1.5 와일드 카드
```java
static Juice makeJuice(FruitBox<Fruit> box) {       // Fruit
        String tmp = "";

        for(Fruit f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
 ============================================================
static Juice makeJuice(FruitBox<Apple> box) {       // Apple
        String tmp = "";

        for(Fruit f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
```    
+ 지네릭 타입이 다른 것만으로는 사실 들어가는 타입변수만 다른 것이지 같은 클래스이므로, **오버로딩이 성립하지 않는다.**
+ 그래서 와일드카드가 고안되었다.
```
<? extends T> : 와일드 카드의 상한 제한. T와 그 자손들만 가능
<? super T>   : 와일드 카드의 하한제한. T와 그 조상들만 가능
<?>           : 제한없음. 모든 타입이 가능함. <? extends Object>와 동일
```
> 와일드 카드 사용
```java
// 출력용
class Juice {
    String name;

    Juice(String name)	     { this.name = name + "Juice"; }
    public String toString() { return name;		 }
}
// 와일드 카드 적용
class Juicer {
    static Juice makeJuice(FruitBox<? extends Fruit> box) {
        String tmp = "";

        for(Fruit f : box.getList())            // appleBox가 오더라도 어차피 Apple은 Fruit의 자손이므로 문제 X
            tmp += f + " ";
        return new Juice(tmp);
    }
}
=========================================================
public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();       // Fruit으로 제한시켜놓은 FruitBox 클래스가 생성
        FruitBox<Apple> appleBox = new FruitBox<Apple>();       // Apple로 제한 시켜놓은 FruitBox 클래스가 생성

        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        System.out.println(Juicer.makeJuice(fruitBox));
        System.out.println(Juicer.makeJuice(appleBox));           // 와일드 카드덕분에 Apple Type Variable임에도 메서드 사용가능 
    } 
```
+ ( Apple extends Fruit , Grape extends Fruit)
+ Fruit과 그 자손인 Apple, Grape 클래스들이 들어올 수 있게 된다.
+ 이부분은 코드를 꼭 참고해서 봐도록 하자.( 추가내용 : Comparator<? super T> )

## 1.6 지네릭 메서드
+ 메서드의 선언부에 반환 타입 앞쪽에 지네릭 타입이 선언된 메서드를 지네릭 메서드라고 한다.
```
class FruitBox<T>{
        ...
        static <T> void sort(List<T> list, Comparator<? super T> c){
        ...
        }
}
```
+ 매개변수의 T와 반환타입 앞의 T는 별개의 것이다.
+ 원래 static 멤버에는 타입 매개변수를 사용할 수 없으나, 메서드에 지네릭 타입을 선언하고 사용하는 것은 가능하다.
+ 메서드에 선언된 지네릭 타입은 **지역 변수**를 선언한 것과 같다고 생각하면 되는데, 이러한 타입의 매개변수는 메서드 내에서만 지역적으로 사용될 것이기 때문에 
static이던 아니던 상관이 없다.
```
static Juice makeJuice(FruitBox<? extends Fruit> box) {
        String tmp = "";

        for(Fruit f : box.getList())            
            tmp += f + " ";
        return new Juice(tmp);
    }
================================================================
static <? extends Fruit> Juice makeJuice(FruitBox<T> box) {
        String tmp = "";

        for(Fruit f : box.getList())            
            tmp += f + " ";
        return new Juice(tmp);
    }
```
+ 이렇게 작업하여, FruitBox 입장에서는 Fruit의 자손 클래스만 가질 수 있었는데, 모든 클래스를 T로 가질 수 있는 상태가 되었다.
+ 그러나 적어도 Juice 메서드에서는 Fruit의 자손 클래스중 하나를 가지게 선언하였음으로, 이 메서드 안에서의 T는 적어도 Fruit의 자손클래스가 올 것이다.
> 호출
```
FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();           // 선언부
FruitBox<Apple> appleBox = new FruitBox<Apple>();      
...
System.out.println(<Fruit>Juicer.makeJuice(fruitBox));      // 이렇게 클래스를 선언 할 수 있다.
System.out.println(<Apple>Juicer.makeJuice(appleBox));
```
+ 컴파일러가 선언부를 통해 추정할 수 있으므로 클래스 선언을 생략할 수 있다.

> 여러 매개변수가 존재할때
```
public static void printAll(ArrayList<? extends Product> list,
                            ArrayList<? extends Product> list2) {...}
========================================================================
public static <? extends Product> void printAll(ArrayList<T> list,
                                                ArrayList<T> list2) {...}
```

## 1.7 지네릭 타입의 형변환
### 지네릭 타입과 원시타입
```
Box         box    = null;
Box<Object> objbox = null;

box         = (Box)objBox;          // 가능 지네릭 타입 -> 원시타입
objBox      = (Box<Object>)box;     // 가능 원시타입 -> 지네릭타입
```
+ 항상 가능하나 경고가 발생한다.

### 대입된 타입이 다른 지네릭 타입끼리 형변환 - 불가능
```
Box<Object> objbox = null;
Box<String> strbox = null;
        
objBox      = (Box<Object>)strbox;     // 불가능
strBox      = (Box<String>)objbox;     // 불가능
```
+ 이전에 ' Box<Object> objbox = new Box<String>();' 도 안되는 걸 기억하면 당연히 될 수 없다.

### 와일드카드와 지네릭 타입의 형변환
```
Box<? extends Object> objbox = new Box<String>(); // 가능
```
+ 이전에 다형성으로 오버로딩했던 기억을 살려보면, 당연히 가능해야 할 것이다.
+ 참고(책참고)
```
Optional 클래스는 Optional<?> 타입의 객체를 만들어 둔다.
그래야만 메서드들에서 Optional<T>로 반환할때, 형변환이 일어날 수 있기 때문이다.
만약 Optional<Object> 이면 형변환이 불가능 했을 것이다.
```
### 지네릭 타입끼리의 형변환
```
FruitBox<? extends Object> objbox = null;
FruitBox<? extends String> strbox = null;
        
objBox      = (Box<? extends Object>)strbox;     // 가능
strBox      = (Box<? extends String>)objbox;     // 가능    
```
+ 와일드 카드의 타입이 확정된 타입이 아니라 컴파일러가 형변환 경고를 알리지만 가능하다.

## 1.8 지네릭 타입의 제거
+ 컴파일러는 지네릭타입을 이용해서 소스파일을 체크하고, **필요한 곳에 형변환**을 넣어준다. 그리고 **지네릭 타입을 제거**한다.
+ 이전 버전과의 호환을 위해 이렇게 이렇게 작업하지만, 추후에는 하위 호환성을 포기하고 원시 타입을 사용하지 않을 지도 모르니 지네릭에 익숙해지자.

# 2.열거형
 
## 2.1 열거형이란
+ 열거형은 서로 관련된 상수를 편리하게 선언하기 위한 것으로, 여러 상수를 정의할 때 사용하면 유용하다.
+ 자바의 열거형은 "타입에 안전한 열거형" 방식이다. 즉 실제 값이 같아도 타입이 다르면 컴파일 에러가 발생한다.
+ 상수의 값이 바뀌면 해당 상수를 참조하는 모든 소스를 다시 컴파일 해야하지만, 열거형 상수를 이용하면 기존의 소스를 다시 컴파일 하지 않아도 된다.

## 2.2 열거형의 정의와 사용
> 정의
```
enum 열거형 이름 { 상수명1, 상수명2, ...}
```
> 사용
+ 열거형이름.상수명 으로 사용한다.
+ 열거형 상수간의 비교는 "=="으로 비교가 가능하여, 빠른 성능을 제공한다.
+ 그러나 '<', '>' 같은 비교연산자는 제공하지 않으므로, compareTo() 를 사용해야 한다.(1,0,-1  - 왼쪽이크다, 같다, 오른쪽이크다) 

### 모든 열거형의 조상 - java.lang.Enum
+ 조상의 메서드를 호출할 수 있다.
```java
enum Direction { EAST, SOUTH, WEST, NORTH }

class EnumEx1 {
    public static void main(String[] args) {
        Direction d1 = Direction.EAST;
        Direction d2 = Direction.valueOf("WEST");
        Direction d3 = Enum.valueOf(Direction.class, "EAST");

        System.out.println("d1="+d1);   
        System.out.println("d2="+d2);
        System.out.println("d3="+d3);

        System.out.println("d1==d2 ? "+ (d1==d2));
        System.out.println("d1==d3 ? "+ (d1==d3));
        System.out.println("d1.equals(d3) ? "+ d1.equals(d3));
//		System.out.println("d2 > d3 ? "+ (d1 > d3)); // 에러
        System.out.println("d1.compareTo(d3) ? "+ (d1.compareTo(d3)));
        System.out.println("d1.compareTo(d2) ? "+ (d1.compareTo(d2)));

        switch(d1) {
            case EAST: // Direction.EAST라고 쓸 수 없다.
                System.out.println("The direction is EAST.");
                break;
            case SOUTH:
                System.out.println("The direction is SOUTH.");
                break;
            case WEST:
                System.out.println("The direction is WEST.");
                break;
            case NORTH:
                System.out.println("The direction is NORTH.");
                break;
            default:
                System.out.println("Invalid direction.");
//				break;
        }

        Direction[] dArr = Direction.values();

        for(Direction d : dArr)  // for(Direction d : Direction.values())
            System.out.printf("%s=%d%n", d.name(), d.ordinal());    // 이름과 인덱스값
    }
}
```
결과
```
d1=EAST
d2=WEST
d3=EAST
d1==d2 ? false
d1==d3 ? true
d1.equals(d3) ? true
d1.compareTo(d3) ? 0
d1.compareTo(d2) ? -2
The direction is EAST.
EAST=0
SOUTH=1
WEST=2
NORTH=3
```

## 2.3 열거형에 멤버 추가하기
+ 열거형이 연속적인 값이라면 ordinal()로 순서를 반환할수 있다.
+ 불연속적인 값이라면 열거형 상수의 이름옆에 ()적고 값을 적어주면 된다.
```
enum Direction {
EAST(1, ">"), SOUTH(2,"V"), WEST(3, "<"), NORTH(4,"^");
(...)
}
```
+ 열거형의 생성자는 제어자가 묵시형으로 private하기 때문에, 열거형 객체를 생성할 수 없다.
+ 코드 참고하기.

### 열거형에 추상메서드 추가하기.
```java
enum Transportation {
    BUS(100)      { int fare(int distance) { return distance*BASIC_FARE;}},
    TRAIN(150)    { int fare(int distance) { return distance*BASIC_FARE;}},
    SHIP(100)     { int fare(int distance) { return distance*BASIC_FARE;}},
    AIRPLANE(300) { int fare(int distance) { return distance*BASIC_FARE;}};

    Transportation(int basicFare) { // private Transportation(int basicFare) {
        BASIC_FARE = basicFare;
    }
(...)
```
+ 익명클래스와 같이, 열거형(Transportation)에 정의 된 추상메서드를 각 상수들이 어떻게 구현하는지 보여준다.

## 2.4 열거형의 이해
```
enum Direction { EAST, SOUTH, WEST, NORTH }
==================================================
class Direction{
    static final Direction EAST = new Direction("EAST");
       ....
}
```
+ 사실 열거형 상수 하나하나가 Direction 객체이다.
+ 그러므로 "==" 비교가 가능한 것이다.
```java
abstract class MyEnum<T extends MyEnum<T>> implements Comparable<T> {
    static int id = 0;

    int ordinal;
    String name = "";

    public int ordinal() { return ordinal; }

    MyEnum(String name) {
        this.name = name;
        ordinal = id++;
    }
```
+ 모든 열거형들은 Enum 추상클래스를 상속받으며, 위 코드는 최대한 비슷하게 만든 것이다.
+ 객체가 생성될 때마다 번호를 붙여서 인스턴스 변수 ordinal에 저장한다.(그래서 인덱스 값으로 ordinal이 쓰임)

```java
class Direction{
    static final Direction EAST = new Direction("EAST"){ //익명클래스, 메서드 구현
    Point move(Point p) { .... } 
    };
      (....)

    abstract Point move(Point p);
}
```
+ 열거형에 추상메서드를 추가하면 각 열거형 상수가 추상 메서드를 구현해야하는지 이해하기 위한 코드이다.

# 3. 애너테이션
## 3.1 애너테이션이란
+ 프로그램의 소스코드 안에 다른 프로그램을 위한 정보를 미리 약속된 형식으로 포함시킨 것
+ 기본적으로 JDK에서 제공하는 것과 다른 프로그램에서 제공하는 것들이 있는데, 그저 약속된 형식으로 정보를 제공하기만 하면 될 뿐이다.

## 3.2 표준 애너테이션
+ 몇개의 기본 애너테이션과 메타 애너테이션이 존재한다.
+ 메타 애너테이션은 에너테이션을 정의하는데 사용되는 애너테이션의 애너테이션이다.
+ 먼저 기본만 봐보자.
### @Override
+ 메서드앞에만 붙이는 애너테이션으로, 조상의 메서드를 오버라이딩하는것이라는걸 **컴파일러**에게 알려주는 역할을 한다.
+ 만약  @Override가 붙은 메서드를 오버라이딩 하지않으면 오류를 발생시켜, 실수를 방지시켜준다.

### @Deprecated
+ 더이상 사용되지 않는 필드나 메서드에 @Deprecated붙여, 다른 것으로 대체되었으니 더이상 사용하지 않을 것을 권한다는 메세지를 우리에게 전한다.

### @Functionallnterface
+ 컴파일러가 함수형 인터페이스를 올바르게 선언했는지 확인하고, 잘못된 경우 에러를 발생시킨다.

### @SuppressWarnings
+ 일반적으로는 컴파일러의 경고메세지를 모두 확인하고 해결하고 넘어갔다.
+ 그러나 여러 에러나 경고가 뜬다면, 더 중요한 경고나 에러 확인을 위하여 어떠한 경고들은 묵인해야 할때가 있을 것이다.
+ 그럴때 경고가 발생하는 곳에  @SuppressWarnings를 사용하여 경고를 억제시킨다.
```
deprecation : @Deprecated 가 붙을 대상을 사용해서 발생하는 경고
unchecked   : 지네릭스로 타입을 지정하지 않았을때 발생하는 경고 
rawtypes    : 지네릭스를 사용하지 않아서 발생하는 경고 
varargs     : 가변인자의 타입이 지네릭 타입일 때 발생하는 경고
========================================================
사용방식
1. @SuppressWarnings({"deprecation","unchecked"...})  // 메서드 위에 사용하여, 메서드 안의 옵션경고들을 전부 억제시킨다.
2. @SuppressWarnings("deprecation")                   // 해당 경고가 발생하는 소스코드위에 사용한다.
```
+ 1번방식은 나중에 추가된 코드에서 발생할 수 있는 경고까지 억제하므로, 귀찮더라도 2번방법으로 하는게 좋다.
+ 코드를 참고하자

### @SafeVarargs
+ 메서드에 선언된 가변인자의 타입이 non-reifiable타입일 경우, 해당 메서드를 선언하는 부분과 호출하는 부분에서 uncheked 경고가 발생한다.
+ static이나 final 이 붙은 메서드와 생성자에만 붙일수 있다.(=오버라이드 될수 있는 메서드에는 사용불가)
+ non-reifiable : 컴파일 후에도 제거 되는 타입( ex) 지네릭스 )
+ @SafeVarargs으로 "unchecked" 경고는 억제할 수 있으나 "varargs" 경로는 억제할수 없다.
+ 그러므로 @SuppressWarnings("varargs") 와 같이 사용하는 편이 좋다.
+ 코드를 참고하자

## 3.3 메타 애너테이션
+ 에너테이션에 붙이는 에너테이션으로 에너테이션을 정의할 때 에너테이션의 적용대상(target)이나 유지기간(retention)등을 지정하는데 사용한다.

### Target
+ 에너테이션이 적용가능한 대상을 지정하는데 사용한다.
+ 대상 타입은 책 or API 참고
```
import static java.lang.annotation.ElementType.*;       // ElementType.TYPE 대신 TYPE 쓸수 있도록 하기위해 선언

@Target({FIELD, TYPE, TYPE_USE})
public @interface MyAnnotation {}                       // @interface : 애너테이션 생성, 정의하기

@MyAnnotation                                           // 적용대상이 TYPE
class MyClass{
    @MyAnnotation                                       // FIELD 는 기본형에
    int i;
    
    @MyAnnotation                                       // TYPE_USE 는 참조형에
    MyClass mc;
```

### @Retention
+ 애너테이션이 유지되는 기간을 지정하는데 사용된다.
```
SOURCE  : 소스파일에만 존재. 클래스파일에는 존재 X
CLASS   : 클래스파일에 존재. 실행시에 사용불가. 기본값
RUNTIME : 클래스파일에 존재. 실행시에 사용가능
```
### @Documented
+ 애너테이션에 대한 정보가 javadoc으로 작성한 문서에 포함되도록한다.

### @Inherited
+ 애너테이션이 자손클래스에 상속되도록한다.
```
@Inherited                          // @SupperAnno가 자손까치 영향을 미치도록
@interface SupperAnno{}     

@SupperAnno
class Parent{}

class Child extends Parent{}        // Child에 애너테이션이 붙은것으로 인식
```

### @Repeatable
+ 보통 하나의 대상에 한종류의 애너테이션을 붙이는데, @Repeatable 이 붙은 애너테이션은 여러벝 붙일 수 있다.
```

@interface ToDos{
    ToDo[] value
}
@Repeatable(ToDos.class)
@interface ToDo{
        String value();
}
==========================
@ToDo("delete test codes")
@ToDo("override inherited methods")     // 중복해서 사용
class MyClass{
    ...
}
```

### @Native
+ 네이티브 메서드에 의해 참조되는 상수필드에 붙이는 애너테이션이다.
```
@Native public static final long Min_VALUE = 0x800000000L;
```
+ 네이티브 메서드는 JVM이 설치된 OS의 메서드를 말한다.
+ Object의 메서드들이 대부분 native 메서드로 존재하는데, OS의 메서드와 연결하여 작동한다.(JNI에 의해)

## 3.4 애너테이션 타입 정의하기
```
@interface 애너테이션이름{
        타입 요소이름();  // 애너테이션의 요소를 선언한다.
        ...
}
```
### 애너테이션의 요소
> 특징
```
- 요소의 타입은 기본형, String, enum, 애너테이션, Class만 허용된다.
- ()안에 매개변수를 선언할 수 없다.(매개변수가 없는 추상메서드)
- 예외를 선언할 수 없다.(XX "throw Exception")
- 요소를 타입 매개변수로 정의할 수 없다. (ex) ArrayList<T> 불가능)
```
+ 적용할때 요소들의 값을 빠짐없이 지정해야한다.
+ 기본값이 있는 요소를 만들수 있다.
```
@interface TestInfo{
        int count() default 1;
}

@TestInfo
public class NewClass{...}
```
+ 요소(안쪽 추상메서드)의 이름이 value()이면 호출시 이름을 생략해도 된다.
```
@interface TestInfo{
        String value();
}

@TestInfo("passed")
public class NewClass{...}
```
+ 요소 타입이 배열인 경우 {}를 사용하여 여러개의 값을 지정한다.
```
@interface TestInfo{
        String[] tools();
}

@TestInfo(toos = {"JUNIT", "AutoTester"})
public class NewClass{...}
```
+ 코드참고하기!(12-13)

### java.lang.annotation.Annotation
+ 모든 애너테이션의 조상은 Annotation이다. 그러나 애너테이션은 상속이 허용되지 않으므로 명식적으로 Annotation을 조상으로 지정할 수 없다.
+ Annotation은 인터페이스로, equals() , hashCode(), toString() 메서드가 존재한다. 그러므로 호출도 가능하다.

### 마커 애너테이션 Marker Annotation
+ 값을 지정할 필요가 없는 경우, 애너테이션의 요소를 하나도 정의하지 않을수 있다.
```
public @interface Override{} // 마커애너테이션 . 정의된 요소가 하나도 없다.
```
+ 참고
```
클래스 객체 : 클래스파일 - 클래스 로더에 의해 메모리에 올라갈때 만들어지는 클래스에 대한 정보가 담긴 객체
- "클래스이름.class" 형식을 사용한다.
- 해당클래스에 대한 모든 정보를 가지고있는데, 애너테이션 정보도 포함되어 있다.
```
