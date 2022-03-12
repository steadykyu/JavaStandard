# 1. 람다식
+ 람다식의 도입으로 인해 자바는 객체지향언어인 동시에 함수형 언어가 되었다.

## 1.1 람다식
+ 메서드를 하나의 **식**으로 표현한 것이며, 익명 함수라고 부른다.

### 메서드와 함수의 차이
```
객체지향개념에서는 함수 대신 객체의 행위나 동작을 의미하는 "메서드"라는 용어를 사용합니다. 
메서드는 함수와 같은 의미이지만, 특정 클래스에 속해야한 다는 제약이 있습니다.
그러나 이제 람다식을 통해 메서드가 하나의 독립적인 기능을 할수 있는데 이를 "함수"라는 용어로 사용합니다.
```

## 1.2 람다식 작성하기
+ 메서드에서 이름과 반환타입을 제거하고, 매개변수 선언부와 몸통{} 사이에 "->"를 추가한다.
```
메서드

반환 타입 메서드이름(매개변수 선언) {
      문장들
}
===============================================
람다식

(매개변수 선언) -> {
      문장들
}
```
+ 반환 값이 있는 메서드의 경우 return문 대신 **식(expression)**으로 대신할 수 있다. 식의 연산결과가 자동적으로 반환값이 된다.
+ 이때는 **식**이므로 ";"를 붙이지 않는다.
```java
(int a, int b) -> {return a > b ? a : b; }
===========================================
(int a, int b) -> a > b ? a : b
```
+ 람다식에 선언된 매개변수의 타입은 추론이 가능한 경우 생략할 수 있다. 대부분의 경우에는 생략이 가능하다.
```
(int a, int b) -> a > b ? a : b
====================================
(a, b) -> a > b ? a : b
```
+ 매개변수가 하나인 경우에는 괄호()를 생략할 수 있다. 단 매개변수 타입이 존재하면 괄호()를 생략할 수 없다.
```
(a)     -> a * a
(int a )-> a * a
===========================
a     -> a * a
(int a)-> a * a  // 생략 불가능
```
+ 괄호{} 안의 문장이 하나일때는 괄호{}를 생략 할 수 있다. 이때는 ";" 를 붙이지 않는다.
```
(String name, int i) -> { System.out.pringln(name+"="+i); }
==============================================================
(String name, int i) -> System.out.pringln(name+"="+i)
```

### 1.3 함수형 인터페이스(Functional Interface)
+ 지금까지 람다식이 메서드와 동등한것 처럼 설명했지만, 사실 람다식은 익명클래스의 객체와 동등하다.
```
(int a, int b) -> a > b ? a : b
======================================
익명 클래스

new Object(){
      int max(int a, int b){
          return a > b ? a : b;
      }
}
```
+ 익명 클래스를 사용하기 위해서는 클래스나 인터페이스와 같은 타입이 있어야 한다. 
```
interface MyFunction{
    public abstract int max(int a, int b);
}
```
+ 임의의 이름으로 max라고 이름을 짓고, 이 인터페이스를 익명 클래스로 구현해보자.
```
MyFunction f = new MyFunction(){
                    public int max(int a, int b){
                    return a > b ? a : b;
                    }
                };
```
+ 이 익명클래스에서 반환 타입 메서드이름을 제거하면 람다식과 메서드 선언부가 같다. 그러므로 위 코드의 익명 객체를 람다식으로 아래와 같이 대체할 수 있다.
```
MyFunction f = (int a, int b) -> a > b ? a : b
int big = f.max(5, 3);      
```
+ 람다식이 실제로 익명객체이고, 매개변수의 타입과 개수 그리고 반환값이 같기때문에 대체가 가능하다.
+ 결국 인터페이스를 구현한 익명구현객체(구현체)가 된 것이다. 
```
@FunctionalInterface
interface MyFunction{
    public abstract int max(int a, int b);
}
```
+ 이렇게 인터페이스와 람다식이 자연스럽기 때문에, 람다식을 다루기 위한 인터페이스를 **함수형 인터페이스**로 부르기로 했다.
+ 일반적인 인터페이스와 다르게 함수형 인터페이스는 오직 **하나**의 추상메서드만 가져야한다.

### 함수형 인터페이스의 타입의 매개변수와 반환타입
> 람다식을 매개변수로 지정하기
```
@FunctionalInterface
interface MyFunction{
    void myMethod();    // 추상메서드가 된다.
}
```
```
void aMethod(MyFunction f){
      f.myMethod();
}
    ....
MyFunction f = () -> System.out.println("myMethod()")
aMethod(f)
```
+ 매개변수 f에 람다식(익명객체)를 넣어주어 사용이 가능하다.

> 반환타입이 함수형 인터페이스타입인경우
```
MyFunction myMethod() {
      MyFunction f = () -> {};
      return f;
}
```
+ 위 코드로 추상메서드와 동등한 람다식을 가리키는 참조변수(f)를 반환하거나, 람다식을 직접 반환 할 수 있다.
+ 정확히는 익명객체를 주고받는 것이지만, 이제 변수처럼 메서드를 주고받는 코드형식으로 코드 작성이 가능해졌다.

### 람다식의 형변환
+ 람다식은 컴파일러가 임의의 이름을 정해놓은 익명객체이므로 아래처럼 형변환해주어야한다. 
```
MyFunction f = (MyFunction)(()->{});
```
+ 인터페이스를 구현한 객체(new MyFunction)와 동일하므로 형변환이 가능하다. 이 형변환은 생략이 가능하다.
+ 람다식은 익명객체이지만 Object 타입으로 형변환 할수 없고, 오직 함수형 인터페이스로만 형변환이 가능하다.
+ 만약 Object 타입으로 사용하고 싶다면 아래처럼 작업해야한다.
```
Object obj = (Object)(MyFunction)(()->{});
```

### 외부 변수를 참조하는 람다식
+ 람다식도 익명 클래스의 인스턴스이므로, 람다식의 외부에 선언된 변수에 접근하는 규칙은 이전에 배운 익명클래스의 규칙과 동일하다.
+ (내부 클래스, 지역클래스에 선언된 익명클래스들의 규칙)

## 1.4 java.util.function 패키지
+ 대부분의 메서드는 매개변수가 없거나, 한개 두개, 반환값은 한개,두개 등 타입이 비슷하다.
+ 게다가 지네릭 메서드를 이용하면 매개변수나 반환 타입이 달라도 문제가 되지 않는다.
+ 그러므로 java.util.function 패키지에는 일반적으로 자주 쓰이는 형식의 메서드를 함수형 인터페이스로 정의해 놓았다.
+ 이 인터페이스를 활용함으로써, 정의된 메서드 이름도 통일되고, 재사용성이나 유지보수 측면에도 좋다.
+ 기본적인 함수형 인터페이스는 책의 그림을 참고하자.

### 조건식의 표현에 사용되는 Predicate
### 매개변수가 두개인 함수형 인터페이서 
+ 접두사 Bi 가 붙는다.
### UnaryOperator와 BinaryOperator
+ Function의 또다른 변형으로, 매개변수의 타입과 반환타입의 타입이 모두 일치한다는 점만 제외하면 Function과 같다.
```java
class LambdaEx5 {
    public static void main(String[] args) {
        Supplier<Integer>  s = ()-> (int)(Math.random()*100)+1;       // 함수형 인터페이스 자료형에 알맞은 람다식(구현체인스턴스값)을 넣어준다.
        Consumer<Integer>  c = i -> System.out.print(i+", ");
        Predicate<Integer> p = i -> i%2==0;
        Function<Integer, Integer> f = i -> i/10*10; // i의 일의 자리를 없앤다.

        List<Integer> list = new ArrayList<>();
        makeRandomList(s, list);
        System.out.println(list);               // [15, 13, 88, 78, 18, 28, 48, 36, 83, 80]
        printEvenNum(p, c, list);               // [88, 78, 18, 28, 48, 36, 80, ]
        List<Integer> newList = doSomething(f, list);   //[10, 10, 80, 70, 10, 20, 40, 30, 80, 80]
        System.out.println(newList);
    }

    static <T> List<T> doSomething(Function<T, T> f, List<T> list) {
        List<T> newList = new ArrayList<T>(list.size());

        for(T i : list) {
            newList.add(f.apply(i));                        // Funcion 인터페이스의 추상메서드 apply
        }

        return newList;
    }

    static <T> void printEvenNum(Predicate<T> p, Consumer<T> c, List<T> list) {
        System.out.print("[");
        for(T i : list) {
            if(p.test(i))
                c.accept(i);
        }
        System.out.println("]");
    }

    static <T> void makeRandomList(Supplier<T> s, List<T> list) {
        for(int i=0;i<10;i++) {
            list.add(s.get());
        }
    }
}
```
+ 각 함수형 인터페이스와 관련메서드는 책의 표를 참고하자.

### 컬렉션 프레임워크와 함수형 인터페이스
+ 컬렉션 프레임워크중 일부는 매개변수로 함수형 인터페이스를 사용한다.
+ 책과 코드를 참고하자.
```java
class LambdaEx4 {
    public static void main(String[] args) 	{
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++)
            list.add(i);

        // list의 모든 요소를 출력
        list.forEach(i->System.out.print(i+","));
        System.out.println();

        // list에서 2 또는 3의 배수를 제거한다.
        list.removeIf(x-> x%2==0 || x%3==0);
        System.out.println(list);

        list.replaceAll(i->i*10); // list의 각 요소에 10을 곱한다.
        System.out.println(list);

        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");

        // map의 모든 요소를 {k,v}의 형식으로 출력한다.
        map.forEach((k,v)-> System.out.print("{"+k+","+v+"},"));
        System.out.println();
    }
}
```
### 기본형을 사용하는 함수형 인터페이스
+ 책참고
+ 이전까지 함수형인터페이스는 매개변수와 반환값의 타입이 모드 지네릭 타입이었는데, 기본값 타입을 처리할때도 래퍼 클래스를 사용한다.
+ 그러나 이는 비효율적이므로, 기본형을 사용하는 함수형인터페이스들을 제공하여 효율적으로 처리할 수 있도록 한다.
```java
class LambdaEx6 {
    public static void main(String[] args) {
        IntSupplier  s = ()-> (int)(Math.random()*100)+1;
        IntConsumer  c = i -> System.out.print(i+", ");
        IntPredicate p = i -> i%2==0;
        IntUnaryOperator op = i -> i/10*10; // i의 일의 자리를 없앤다.

        int[] arr = new int[10];

        makeRandomList(s, arr);
        System.out.println(Arrays.toString(arr));
        printEvenNum(p, c, arr);
        int[] newArr = doSomething(op, arr);
        System.out.println(Arrays.toString(newArr));
    }

    static void makeRandomList(IntSupplier s, int[] arr) {
        for(int i=0;i<arr.length;i++) {
            arr[i] = s.getAsInt();  // get()이 아니라 getAsInt()임에 주의
        }
    }

    static void printEvenNum(IntPredicate p, IntConsumer c, int[] arr) {
        System.out.print("[");
        for(int i : arr) {
            if(p.test(i))           // 짝수만 오도록 검증
                c.accept(i);        // 출력
        }
        System.out.println("]");
    }

    static int[] doSomething(IntUnaryOperator op, int[] arr) {
        int[] newArr = new int[arr.length];

        for(int i=0; i<newArr.length;i++) {
            newArr[i] = op.applyAsInt(arr[i]); // apply()가 아님에 주의, 입력타입과 반환타입이 일치, 기본형을 사용하는
        }

        return newArr;
    }
}
```

## 1.5 Function의 합성과 Predicate의 결합
+ 함수형 인터페이스에는 추상메서드 외에도 디폴트메서드와 static 메서드가 정의되어있다.
+ 종류는 책, 코드 참고

### Function의 합성
+ 수학에서 두 함수를 합성하여 새로운 함수를 만들어내는 것 처럼, 두 람다식을 합성해서 새로운 람다식을 만들어 낼수 있다.
+ 새로운 람다식에서 중요한건 **어느 함수를 먼저 적용하는가** 이다.

### 항등함수
+ 함수를 적용하기 이전과 이후가 동일한 항등함수가 존재한다.
```
Function<String,String> f = x -> x;
Function<String,String> f = x -> Function.identitiy(); // 위와 동일
```

### Predicate의 결합
+ 코드참고
+ 여러 조건식을 "."을 통해 연결하여 하나로 합칠 수 있다.
+ 입력값이 있고 boolean을 return하는 static 메서드 isEqual()은 Predicate를 만들때 사용할 수 있다.
```java
class LambdaEx7 {
    public static void main(String[] args) {
        Function<String, Integer>	f  = (s) -> Integer.parseInt(s, 16);
        Function<Integer, String>	g  = (i) -> Integer.toBinaryString(i);

        Function<String, String>	h  = f.andThen(g);
        Function<Integer, Integer>  h2 = f.compose(g);

        System.out.println(h.apply("FF")); // "FF" → 255 → "11111111"
        System.out.println(h2.apply(2));   // 2 → "10" → 16


        Function<String, String> f2 = x -> x; // 항등 함수(identity function)
        System.out.println(f2.apply("AAA"));  // AAA가 그대로 출력됨

        Predicate<Integer> p = i -> i < 100;
        Predicate<Integer> q = i -> i < 200;
        Predicate<Integer> r = i -> i%2 == 0;
        Predicate<Integer> notP = p.negate(); // i >= 100

        Predicate<Integer> all = notP.and(q).or(r);
        System.out.println(all.test(150));       // true

        String str1 = "abc";
        String str2 = "abc";

        // str1과 str2가 같은지 비교한 결과를 반환
        Predicate<String> p2 = Predicate.isEqual(str1);
        boolean result = p2.test(str2);
        System.out.println(result);     //true
    }
}
```

## 1.6 메서드 참조
+ 람다식이 하나의 메서드만 호출하는 경우에는 **메서드 참조**라는 방법으로 람다식을 더 간략하게 만들 수 있다.
```
Function<String, Integer>  f = (String s) -> Integer.parseInt(s);
Function<String, Integer>  f = Integer::parseInt; // 메서드 참조
```
+ 함수형 인터페이스의 지네릭을 통해 여러 정보를 추측할수 있으므로, 이에 해당하는 람다식의 일부를 생략할 수 있다.
+ 지금은 parseInt 메서드 이지만 , 만약 equals 메서드라고 생각해보자. equals는 다른 클래스에도 존재하며 재정의되어있을것이다.
+ 그러므로 구별을 위해 클래스이름은 적어주어야한다.

### 이미 생성된 객체의 메서드를 람다식에서 사용한 경우
+ 이때는 클래스 이름 대신 그 객체의 참조변수를 적어주자.
```java
MyClass obj = new MyClass();
Function<String, Boolean> f = (x) -> obj.equals(x);  // 람다식에서 사용함!
Function<String, Boolean> f = obj::equals;
```

### 생성자의 메서드 참조
> 매개변수가 없는 생성자
```
Supplier<MyClass> s = () -> new MyClass();
Supplier<MyClass> s = MyClass::new;
```      
> 매개변수가 있는 생성자
```
Function<Integer, MyClass> f = (i) -> new MyClass(i);
Function<Integer, MyClass> f = MyClass::new;
=============================================================
BiFunction<Integer, MyClass> f = (i, s) -> new MyClass(i, s);
BiFunction<Integer, MyClass> f = MyClass::new;
```
> 배열 생성자
```
Funcion<Integer, int[]> f = x -> new int[x];
Funcion<Integer, int[]> f2 = int[]::new;
```
+ 코드들을 보면 함수형 인터페이스를 적절하게 변경해주면 된다.
+ 메서드 참조는 람다식을 마치 static 변수처럼 다룰 수 있게 해준다.(인터페이스에 구현시켜놓고 변수처럼 사용하는 모습)

# 2. 스트림
## 2.1 스트림이란
+ 지금까지 우리는 많은 수의 데이터를 다룰때, 컬렉션이나 배열에 데이터를 담고, 결과를 위해 for문 또는 iterator를 이용해왔다.
+ 그러나 이러한 방식으로 작성된 코드는 너무 길고 재사용성도 떨어진다.
+ 스트림은 데이터 소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서드들을 정의해놓았다.
+ 데이터 소스 추상화란 데이터 소스가 무엇이든 간에 **같은 방식으로** 다룰 수 있게되었다는 것을 의미한다.
+ 이전에는 List 정렬은 Collections.sort(), 배열 정렬은 Arrays.sort()를 사용했는데 스트림으로 작성하면 아래와 같다.
```java
String[]          strArr      = { "aaa", "ddd", "ccc"};
List<String>      strList     = Arrays.asList(strArr);
========================스트림생성=============================
Stream<String> strStream1 = strList.stream();         // Collection 클래스의 자손인 List에는 stream()메서드가 내장되어 있음
Stream<String> strStream2 = Arrays.stream(strArr)     // Arrays의 stream() // static 메서드
========================정렬하기===============================
strStream1.sorted().forEach(System.out::println);
strStream2.sorted().forEach(System.out::println); // 위와동일하다!!!
```
+ 스트림을 사용하면 코드가 간결하고 이해하기 쉬우며 재사용성도 높아진다.

### 스트림은 데이터 소스를 변경하지 않는다.
+ 데이터 소스로 부터 데이터를 읽기만 할뿐, 데이터 소스를 변경하지 않는다.

### 스트림은 일회용이다.
+ 스트림은 Iterator처럼 일회용이다. 한번 사용하면 닫혀서 다시 사용할 수 없다. 굳이 다시 사용하려면 다시 생성해야한다.

### 스트림 작업을 내부 반복으로 처리한다.
+ 내부 반복이란 반복문을 메서드의 내부에 숨길수 있다는 것을 의미한다.
+ 스트림의 forEach()는 안에 내장된 for문을 통해 매개변수에 대입된 람다식을 데이터 소스의 **모든 요소에 적용**한다.
+(코드참고)
### 스트림의 연산
+ 스트림은 중간연산 최종연산으로 분류하여 복잡한 작업들을 간단히 처리할 수 있다.
```
중간 연산 : 연산 결과가 스트림인 연산. 스트림에 연속해서 중간 연산할수 있음.
최종 연산 : 연산 결과가 스트림이 아닌 연산. 스트림의 요소를 소모하므로 단 한번만 가능하다.
```
+ 책의 중간연산 목록, 최종연산 목록을 확인하자.

### 지연된 연산
+ 스트림연산에서 최종 연산이 수행되기 전까지는 중간 연산이 수행되지 않는다. 즉 **최종 연산이 수행되어야** 비로소 스트림의 요소들이 중간 연산을 거쳐 최종 연산에서 소모된다.

### Stream\<Integer\> 와 IntStream
+ 요소의 타입이 T인 스트림은 기본적으로 Stream\<T\>이다.
+ 이때 오토박싱&언박싱으로 인한 비효율을 줄이기 위해 데이터 소스의 요소를 **기본형으로 다루는 스트림** IntStream,LongStream,DoubleStream 이 제공된다.

### 병렬 스트림
+ 이전의 fork&join 프레임워크의 병렬처리와 같이 병렬 스트림은 내부적으로 이 프레임워크를 이용해서 자동적으로 연산을 병렬로 수행한다.
+ 스트림에 parallel() 메서드를 호출해주기만 하면 된다.
+ sequential() 메서그가 존재하지만, 기본적으로 병렬이 아닌 스트림이므로 parallel()을 취소할때 말고는 사용하지 않는다.

## 2.2 스트림 만들기
+ 이제 각 과정을 세부적 으로 봐보자.
### 컬렉션
+ 컬렉션의 최고 조상인 Collection에 stream()이 정의되어있다.
+ 그러므로 자손인 List와 Set은 이 메서드를 사용하여 Stream()을 생성한다.
```
List<Integer> list = Arrays.asList(1,2,3,4,5);  // 가변인자
Stream<Integer> intStream = list.stream();      // list를 소스로 하는 컬렉션 생성
```

### 배열
+ 배열을 소스로 하는 스트림을 메서드는 Stream과 Arrays 에 static 메서드로 정의되어 있다.
```java
Stream<String> strstream = Stream.of("a","b","c");    // 가변인자
Stream<String> strstream = Stream.of(new String[]{"a","b","c"});
Stream<String> strstream = Arrays.stream(new String[]{"a","b","c"});
Stream<String> strstream = Arrays.stream(new String[]{"a","b","c"}, 0 , 3);
```
+ 기본형 배열을 소스로 하는 스트림 메서드도 존재한다.
```
IntStream IntStream.of(int ...values)
IntStream IntStream.of(int[])
IntStream Arrays.stream(int[])
IntStream Arrays.stream(int[] array, int startInclusive, int endInclusive)
```

### 특정 범위의 정수
+ 기본형 스트림 IntStream, LongStream은 지정된 범위의 연속된 정수를 스트림으로 생성해서 반환하는 range()와 rangeClosed()를 가지고 있다.
```
IntStream intStream = IntStream.range(1, 5);          //1,2,3,4  (end 포함X)
IntStream intStream = IntStream.rangeClosed(1, 5);    //1,2,3,4,5(end 포함)
```
### 임의의 수
+ 난수를 생성하는 Random클래스에는 **무한스트림**을 생성하는 아래 메서드들이 존재한다.
```
IntStream   ints()
LongStream  longs()
DoubleStream doubles()
```
+ 한개의 매개변수를 지정해주면 무한스트림에서 유한 스트림을 생성해서 반환해준다.ex) ints( long StreamSize)
+ 두개의 매개변수를 지정해주면 지정된 범위의 난수를 발생시키는 스트림을 얻는다.ex) ints(int start, int end)

### 람다식 - iterate(), generate()
+ iterate(), generate()는 람다식을 매개변수로 받아서, 이 람다식에 의해 계산되는 값들을 요소로 하는 **무한 스트림**을 생성한다.
```
Stream<Integer>    eventStream = Stream.iterage(0, n->n+2); // 0, 2 ,4, 6

Stream<Double>    randomStream = Stream.generate(Math::random); 
```
+ iterate()는 람다식에 계산된 결과를 다시 seed값으로 해서 계산을 반복한다.,
+ generate()는 람다식에 의해 계산되는 값을 요소로 무한스트림을 생성하여 반환하지만, 이전 결과를 이용해서 다음요소를 계산하지는 않는다.
+ 이둘은 기본형 스트림 타입(IntStream)의 자료형으로 참조변수를 **생성 할수 없다.**
+ 굳이하려면 mapToInt() 와 같은 메서드로 변환을 해주어야한다.(스트림요소의 Integer -> int로)
### 파일
+ java.nio.file.Files는 파일을 다루는데 필요한 유용한 메서드들을 제공한다.
+ 그중 list(Path path)는 지정된 디렉토리(dir)에 있는 파일의 목록을 소스로 하는 스트림을 생성해서 반환해준다.
+ 파일의 한행을 요소로 하는 스트림을 생성하는 메서드 lines(Path path)도 존재한다.

### 빈 스트림
+ 요소가 하나도 없는 비어있는 스트림을 생성하여 반환 할수도 있다.
```
Stream emptyStream = Stream.empty();
long count = emptyStream.count(); // 0
```

### 두 스트림의 연결
+ Stream의 static 메서드인 concat()을 사용하면, 두 스트림을 하나로 연결 할 수 있다. 물론 연결하려는 두 스트림의 요소는 같은 타입이여야한다.
```java
String[] str1 = {"123","456","789"};
String[] str2 = {"ABC","abc","DEF"};

Stream<String> strs1 = Stream.of(strs1);
Stream<String> strs2 = Stream.of(strs2);
Stream<String> strs3 = Stream.concat(strs1, strs2);
```

## 2.3 스트림의 중간연산
### 스트림 자르기 - skip(), limit()
+ 스트림의 일부를 잘라낼 때 사용한다.
### 스트림의 요소 걸러내기 - filter(), distinct()
+ distinct는 스트림에서 중복된 요소들을 제거하고, filter()는 주어진 조건(Predicate)에 맞지 않는 요소를 걸러낸다.
+ filter의 매개변수로는 Predicate를 필요로하는데, 반환형이 boolean인 람다식을 사용해도 된다.(사실 같은말)
+ 필요하다면 filter() 여러번 사용할수 있다.

### 정렬 - sorted()
+ 지정된 Comparator로 스트림을 정렬한다.
+ Comparator를 지정하지 않으면 **스트림 요소**의 기본 정렬 기준(Comparable)으로 정렬한다. 단 스트림의 요소가 Comparable를 구현하지 않으면 예외가 발생한다.
+ Comparator 인터페이스에 static 메서드와 default 메서드가 많이 추가되었는데, 이 메서드를 사용하여 정렬을 하면 훨씬 쉽다.(책 참고, thenComparing)
+ 가장 기본적인 메서드는 comparing()이다.
```
스트림의 요소가 Comparable을 구현한 경우
 - 매개변수 하나짜리를 사용하면되고 Comparable의 기본 정렬을 기준으로한다.
 - 추가적인 매개변수로 정렬기준(Comparator)을 따로 지정해줄 수 있다.
```
+ 정렬 비교대상이 기본형인 경우, comparing() 대신 comparingInt()등을 사용하면 오토박싱, 언박싱 과정이 없어 더욱 효율적이다.
+ 정렬 조건을 추가할 때는 thenComparing()을 사용한다.
```java
class StreamEx1 {
    public static void main(String[] args) {
        Stream<Student> studentStream = Stream.of(
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
        );

        studentStream.sorted(Comparator.comparing(Student::getBan) // 반별 정렬(Comparator인터페이스의 default 값으로 정해진 오름차순)
                        .thenComparing(Comparator.naturalOrder()))    // 기본 정렬(우리가 설정한 Comparable, 즉 totalScore별 내림차순)
                .forEach(System.out::println);
    }
}
// 스트림에 들어갈 요소에 Comparable interface를 구현해 두어야 한다.
class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;

    Student(String name, int ban, int totalScore) {
        this.name =name;
        this.ban =ban;
        this.totalScore =totalScore;
    }

    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore).toString();
    }

    String getName()     { return name;}
    int getBan()         { return ban;}
    int getTotalScore()  { return totalScore;}

    // 총점 내림차순을 기본 정렬로 한다.( 추상메서드 compareTo를 구현 )
    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }
}
```
### 변환 - map()
+ 스트림의 요소에 저장된 값중에서 원하는 필드만 뽑아내거나 특정 형태로 변환해야할때 사용한다.
+ map() 역시 중간 연산이므로 연산결과는 스트림이 나오며, filter()처럼 하나의 스트림에 여러번 적용 할 수 있다.
+ 이전 최종연산, 중간연산 과정에서 말했던 것처럼 요소 하나하나를 변환해서 최종연산으로 보낸다.
### 조회 - peek()
+ 연산과 연산 사이에 올바르게 처리되었는지를 확인할 수 있다. 주로 filter(), map()의 결과를 확인할때 사용된다.
```java
class StreamEx2 {
    public static void main(String[] args) {
        File[] fileArr = { new File("Ex1.java"), new File("Ex1.bak"),
                new File("Ex2.java"), new File("Ex1"), new File("Ex1.txt")
        };

        Stream<File> fileStream = Stream.of(fileArr);

        // map()으로 Stream<File>을 Stream<String>으로 변환
        Stream<String> filenameStream = fileStream.map(File::getName);
        filenameStream.forEach(System.out::println); // 모든 파일의 이름을 출력
        System.out.println("+=======================================================");

        fileStream = Stream.of(fileArr);  // 스트림을 다시 생성

        fileStream.map(File::getName)			 // Stream<File> → Stream<String>
                .filter(s -> s.indexOf('.')!=-1)   // 확장자가 없는 것은 제외
                //.peek(s -> System.out.printf("1.filename=%s%n", s))
                .map(s -> s.substring(s.indexOf('.')+1)) // 확장자만 추출
                //.peek(s -> System.out.printf("2.filename=%s%n", s))
                .map(String::toUpperCase)    // 모두 대문자로 변환
                .distinct()			       //  중복 제거
                .forEach(System.out::println); // JAVABAKTXT
                // peek를 써보면 중간연산과 최종연산이 '값이 하나씩 들어가서 처리되는 방식'인 점을 확인 할 수 있다.
        System.out.println();
    }
}
```

### mapToInt(), mapToLong(), mapToDouble()
+ map()은 연산결과로 Stream\<T\> 타입의 스트림을 반환하는데, 
스트림의 요소를 **숫자(기본형)**로 변환하는 경우는 위의 메서드를 통해 IntStream과 같은 기본형 스트림으로 변환하는 것이 더 유용할수 있다.
+ IntStream은 count()만 지원하는 Stream\<T\> 와 달리 편리한 메서드들을 제공한다.
```
int               sum()
OptionalDouble    average()
OptionalInt       max()
OptionalInt       min()
```
+ sum()은 0을 반환하면 그만이지만, 다른 메서드들은 계산에 의해 실제 0이 나올수 있으므로 단순히 0을 반환 할수 없다.
+ 이를 위해 int 타입 값을 가지고 있는 OptionalInt을 반환하는 것이다. (추후 나옴)
+ 이 메서드들은 최종 연산이므로 호출 후에 스트림이 닫힌다.
+ 만약 이 메서드들을 모두 호출하고 싶다면, summaryStatistics() 라는 메서드를 따로 제공한다.
```
IntStream scoreStream = strudentStream.maptoInt(student::getTotalScore);
IntSummaryStatistics stat = scoreStream.summaryStatistics();
```
+ 반대로 IntStream을 Stream\<T\> 로 변환할 때는, mapToObj()를 Stream\<Integer\>로 변환할때는 boxed()를 사용한다.

### flatMap() - Stream\<T[]\>를 Stream\<T\>로 변환
+ 스트림의 요소가 배열이거나, map()의 연산결과가 배열인 경우, 즉 스트림의 타입이  Stream\<T[]\>인 경우, Stream\<T\>로 다루어야할때가 있다.
+ 이럴때는 map() 대신 flatMap()을 사용하면 된다.
+ 책의그림과 코드를 참고하자.
```java
class StreamEx4 {
    public static void main(String[] args) {

        Stream<String[]> strArrStrm = Stream.of(
                new String[]{"abc", "def", "jkl"},
                new String[]{"ABC", "GHI", "JKL"}
        );

//		Stream<Stream<String>> strStrmStrm = strArrStrm.map(Arrays::stream);
        Stream<String> strStrm = strArrStrm.flatMap(Arrays::stream);

        strStrm.map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        String[] lineArr = {
                "Believe or not It is true",
                "Do or do not There is no try",
        };

        Stream<String> lineStream = Arrays.stream(lineArr);
        lineStream.flatMap(line -> Stream.of(line.split(" +")))
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        Stream<String> strStrm1 = Stream.of("AAA", "ABC", "bBb", "Dd");
        Stream<String> strStrm2 = Stream.of("bbb", "aaa", "ccc", "dd");

        Stream<Stream<String>> strStrmStrm = Stream.of(strStrm1, strStrm2);

        Stream<String> strStream = strStrmStrm
                .map(s -> s.toArray(String[]::new))     // Stream<Stream<String>> => Stream<String[]>
                .flatMap(Arrays::stream);               // Stream<String[]> => Stream<String>
        strStream.map(String::toLowerCase)
                .distinct()
                .forEach(System.out::println);
    }
}
```
## 2.4 Optional\<T\>와 OptionalInt
+ 이전 중간연산에 반환값이 Optional인 경우들이 있는데, Optional에 대해 알아보자.
+ 사용법은 아래 코드를 참고
```
public final class Optional<T> {
      private final T value;
      ....
}
```
+ Optional 타입의 객체에는 모든 타입의 참조변수를 넣을 수 있다.
+ 이렇게 Optional 객체에 담아서 반환을 하면, 반환된 결과가 null인지 매번 if문으로 체크하는 대신 Optional에 정의된 메서드를 통해서 간단하게 처리할 수 있다.

### Optional 객체 생성하기
+ of() 또는 ofNullable() 사용한다. 만일 참조변수의 값이 null일 가능성이 있으면 of() 대신 ofNullable()을 사용해야한다.
+ Optional\<T\> 타입의 참조변수를 기본값으로 초기화할때는 null로 초기화하기 보다는 empty()를 사용한다.
```
Optional<String> optVal = Optional.<String>empty(); // <String>은 추정가능하므로 생략가능   
```
### Optional 객체의 값 가져오기.
+ get()을 사용하여 저장된 값을 가져온다.
+ null 일때는 NoSuchElementException이 발생하는데 orElse()로 대체할 값을 지정할 수 있다. ( 추가: orElseGet(), orElseThrow())
+ Stream처럼 Optional객체에도 filter(), map(), flatMap()을 사용할 수 있다.
+ isPresent()는 Oprional객체의 값이 null이면 false를, 아니면 true로 반환한다. Optional\<T\>를 반환하는 findAny나 findFirst() 같은 최종 연산과 잘 어울린다.
+ ifPresent(Consumer\<T\> block)은 값이 있으면 주어진 람다식을 수행하고, 없으면 아무일도 하지 않는다.

### OptionalInt, OptionalLong, OptionalDouble
+ IntStream과 같은 기본형 스트림에는 Optional도 기본형을 값으로 하는 OptionalInt, OptionalLong, OptionalDouble을 반환한다.
+ 이전에 값호출에는 get을 사용했지만, getAsInt()...등으로 바뀐 메서드를 사용한다.
```java
class OptionalEx1 {
    public static void main(String[] args) {
        Optional<String>  optStr = Optional.of("abcde");
        Optional<Integer> optInt = optStr.map(String::length);
        System.out.println("optStr="+optStr.get());
        System.out.println("optInt="+optInt.get());

        int result1 = Optional.of("123")
                .filter(x->x.length() >0)
                .map(Integer::parseInt).get();

        int result2 = Optional.of("")       // null로 인식하여 -1로 대체함.
                .filter(x->x.length() >0)
                .map(Integer::parseInt).orElse(-1);

        System.out.println("result1="+result1); // 123
        System.out.println("result2="+result2); // -1

        Optional.of("456").map(Integer::parseInt)
                .ifPresent(x->System.out.printf("result3=%d%n",x)); //456

        OptionalInt optInt1  = OptionalInt.of(0);   // 0을 저장
        OptionalInt optInt2  = OptionalInt.empty(); // 빈 객체를 생성(기본형이기때문에 empty()를 통해 빈객체를 만든다.)

        System.out.println(optInt1.isPresent());   // true
        System.out.println(optInt2.isPresent());   // false

        System.out.println(optInt1.getAsInt());   // 0
//		System.out.println(optInt2.getAsInt());   // NoSuchElementException
        System.out.println("optInt1 ="+optInt1);    // OptionalInt[0]
        System.out.println("optInt2="+optInt2);     // OptionalInt.empty
        System.out.println("optInt1.equals(optInt2)?"+optInt1.equals(optInt2)); //false

        Optional<String> opt  = Optional.ofNullable(null); // null을 저장
        Optional<String> opt2 = Optional.empty();          // 빈 객체를 생성
        System.out.println("opt ="+opt);        //Optional.empty
        System.out.println("opt2="+opt2);       //Optional.empty
        System.out.println("opt.equals(opt2)?"+opt.equals(opt2)); // true

        int result3 = optStrToInt(Optional.of("123"), 0);
        int result4 = optStrToInt(Optional.of(""), 0);

        System.out.println("result3="+result3);     //123
        System.out.println("result4="+result4);     // 0 ,예외의 defaultValue값이 출력
    }

    static int optStrToInt(Optional<String> optStr, int defaultValue) {
        try {
            return optStr.map(Integer::parseInt).get();
        } catch (Exception e){
            return defaultValue;
        }
    }
}
```
## 2.5 스트림의 최종 연산
+ 최종 연산은 스트림을 소모해서 결과를 만들어내므로, 연산후에는 스트림이 닫히게되거나 더이상 사용할 수가 없다.
### forEach()
```
void forEach(Consumer<? super T> action)
```
+ 반환 타입이 void이므로 요소를 출력하는 용도로 자주 사용된다.

### 조건검사 - allMatch(), anyMatch(), noneMatch(),findFirst(), findAny()
+ 모두 매개변수로 Predicate를 요구하며, 연산결과로 boolean을 반환한다.
+ findFirst()와 findAny()는 조건에 맞는 스트림의 요소가 있는지 확인하는데 사용된다. 반환타입은 Optional\<T\>이며, 스트림의 요소가 없을때에는 비어있는 Optional객체를 반환한다.

### 통계 - count(), sum(), average(), max(), min()
+ 이전에 본것처럼 IntStream에는 여러 통계메서드가 있지만, 기본형 스트림이 아닌 경우에는 count(), max(), min()만 존재한다.

### 리듀싱 - reduce()
+ 스트림의 요소를 줄여나가면서 연산을 수행하고 최종결과를 반환한다. 그래서 매개변수 타입이 BinaryOperatior\<T\>이다. 처음 두요소를 가지고 연산한 결과를 가지고 그 다음 요소와 연산한다.(StringBuffer의 개념과 비슷하다.)
```java
Optional<T> reduce(BinaryOperator<T> accumulator)
====================초기값이 있을때============================
T reduce (T identitiy, BinaryOperator<T> accumulator)
U reduce (U identitiy, BiFunction<U,T,U> accumulator, BinaryOperator<T> combiner) // 처리된 결과를 합치는 combiner
```
+ BinaryOperator\<T\>는 BiFunction의 자손이며, BiFunction\<T,T,T\>와 동등하다.
+ 반환타입으로 Optional\<T\> 를 가진다. 추가로 초기값을 가지는 reduce()는 T를 반환타입으로 가진다.
+ 기본형 스트림에서 reduce()를 사용하면 반환타입으로 OptionalInt를 가진다.
+ reduce()는 초기값과 어떤 연산으로 스트림의 요소를 줄여나갈지만 잘 판단하면 된다.
> 활용 코드
```java
class StreamEx5 {
    public static void main(String[] args) {
        String[] strArr = {
                "Inheritance", "Java", "Lambda", "stream",
                "OptionalDouble", "IntStream", "count", "sum"
        };

        Stream.of(strArr).forEach(System.out::println);
        System.out.println("==============================");
        boolean noEmptyStr = Stream.of(strArr).noneMatch(s->s.length()==0);

        Optional<String> sWord = Stream.of(strArr)
                .filter(s->s.charAt(0)=='s').findFirst();

        System.out.println("noEmptyStr="+noEmptyStr);   //true
        System.out.println("sWord="+ sWord.get());      //stream

        // Stream<String[]>을 IntStream으로 변환
        IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream3 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream4 = Stream.of(strArr).mapToInt(String::length);

        int count = intStream1.reduce(0, (a,b) -> a + 1);   // 반환타입이 int(즉 T)
        int sum   = intStream2.reduce(0, (a,b) -> a + b);

        OptionalInt max = intStream3.reduce(Integer::max);  // 반환타입이 OptionalInt
        OptionalInt min = intStream4.reduce(Integer::min);  // (Integer a, Integer b) -> Integer.max(a,b); / Integer 방식으로 재정의된 min

        System.out.println("count="+count);     // 8
        System.out.println("sum="+sum);         // 58
        System.out.println("max="+ max.getAsInt()); // 14
        System.out.println("min="+ min.getAsInt()); // 3
    }
}
```
## 2.6 collect()
+ 스트림의 요소를 수집하는 최종 연산으로 reduce()와 유사하다. 
+ 문법적인 부분이 복잡하지만, 이전의 메서드들로는 작업하기 힘든 그룹화와 분할 연산(groupingBy(), partitioningBy())이 가능하므로 알고있어야한다.
```
collect()         : 스트림의 최종연산, 매개변수로 Collector를 구현한 객체를 필요로 한다.
Collector         : 인터페이스, 컬렉터는 이 인터페이스를 구현해야 한다.(어떻게 수집할 것인지 정하는 인터페이스)
Collectors        : 클래스, static 메서드로 미리 작성된 Collector 구현체를 제공한다.
```
> collect 연산
```
Object collect(Collector collector) 
Object collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)      // 그룹화때 참고
```
### 스트림을 컬렉션과 배열로 변환
+ toList(), toSet(), toMap(), toCollection(), toArray()
+ 스트림의 모든요소를 컬렉션에 수집하려면, Collectors클래스의 toList()같은 메서드를 사용하면 된다.
+ List나 Set아닌 특정 컬렉션을 지정하려면, toCollection()에 해당 컬렉션의 생성자 참조를 매개변수로 넣어주면 된다.
> ArrayList, Map으로 변환
```java
ArrayList<String> list = names.stream()
                              .collect(Collectors.toCollection(ArrayList::new));
Map<String, Person> map = personstream
                              .collect(Collectors.toMap(p->p.getRegId(), p->p));
```
> 배열로 변환
```
Student[] stuNames = studentStream.toArray(Student[]::new);
Object[]  stuNames = studentStream.toArray();
```
+ toArray()로 스트림을 배열로 반환할때, 매개변수를 지정하지 않으면 반환되는 배열의 타입이 Object[] 이다.

### 통계 
+ counting(), summingInt(), averagingInt(), maxBy(), minBy(), summarizingInt
+ collect() 안에서 위의 메서드들을 사용하여 통계값들을 얻을 수 있다.
+ Collectors 클래스의 static 메서드로써, 제각각 반환타입이 다르므로 참고하면서 사용하자.

### reducing()
+ 이전의 reduce와 같이 작업이 가능하다. 그런데 IntStream에는 매개변수 3개짜리 collect만 저으이되어 있으므로 boxed()를 통해 Stream\<Integer\>로 변환해서 사용한다.

### 문자열 결합 - joining()
+ 문자열 스트림의 모든 요소를 하나의 문자열로 연결해서 반환한다. 구분자를 지정해줄 수 있고, 접두사와 접미사도 지정가능하다.
+ 스트림의 요소가 String이나 StringBuffer처럼 CharSequence의 자손인 경우에만 결합이 가능하다.
> 활용 코드
```java
class StreamEx6 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
        };

        // 학생 이름만 뽑아서 List<String>에 저장
        List<String> names = Stream.of(stuArr).map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(names);
        System.out.println("==================================");
        // 스트림을 배열로 변환
        Student[] stuArr2 = Stream.of(stuArr).toArray(Student[]::new);

        for(Student s : stuArr2)
            System.out.println(s);
        System.out.println("==================================");

        // 스트림을 Map<String, Student>로 변환. 학생 이름이 key
        Map<String,Student> stuMap = Stream.of(stuArr)
                .collect(Collectors.toMap(s->s.getName(), p->p));
        for(String name : stuMap.keySet())
            System.out.println(name +"-"+stuMap.get(name));
        System.out.println("==================================");

        long count = Stream.of(stuArr).collect(counting());
        long totalScore = Stream.of(stuArr)
                .collect(summingInt(Student::getTotalScore));
        System.out.println("count="+count);
        System.out.println("totalScore="+totalScore);       // 1420
        System.out.println("==================================");

        totalScore = Stream.of(stuArr)
                .collect(reducing(0, Student::getTotalScore, Integer::sum)); //반환자료형이 long
        System.out.println("totalScore="+totalScore);       // 1420
        System.out.println("==================================");

        Optional<Student> topStudent = Stream.of(stuArr)
                .collect(maxBy(Comparator.comparingInt(Student::getTotalScore)));
        System.out.println("topStudent="+topStudent.get()); // [이자바, 3, 300]
        // 반환자료형이 Optional<Student>

        IntSummaryStatistics stat = Stream.of(stuArr)
                .collect(summarizingInt(Student::getTotalScore));
        System.out.println(stat);   // {count=7, sum=1420, min=100, average=202.857143, max=300}

        String stuNames = Stream.of(stuArr) // Stream<Student>
                .map(Student::getName)      // Stream<String>
                .collect(joining(",", "{", "}")); // 구분자, 접미사, 접두사
        System.out.println(stuNames);
        //{이자바,김자바,안자바,박자바,소자바,나자바,감자바}
    }
}


class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;

    Student(String name, int ban, int totalScore) {
        this.name =name;
        this.ban =ban;
        this.totalScore =totalScore;
    }

    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore).toString();
    }

    String getName() { return name;}
    int getBan() { return ban;}
    int getTotalScore() { return totalScore;}

    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }
}
```

### 그룹화와 분할
+ 그룹화는 스트림의 요소를 특정 기준으로 그룹화하는 것을 의미하고, 분할은 스트림의 요소를 두가지, 지정된 조건에 일치하는 그룹과 일치하지 않는 그룹으로의 분할을 의미한다.
+ groupingBy()는 스트림의 요소를 Function (함수 인터페이스)로, partitioningBy()는 Predicate로 분류한다.
+ 스트림을 두개의 그룹으로 나눠야한다면 partitioningBy(), 그 외에는 groupingBy()를 사용한다.

### partitioningBy()
```java
class Student {
    String name;
    boolean isMale; // 성별
    int hak;		    // 학년
    int ban;		    // 반
    int score;

    Student(String name, boolean isMale, int hak, int ban, int score) {
        this.name	= name;
        this.isMale	= isMale;
        this.hak	= hak;
        this.ban	= ban;
        this.score  = score;
    }
    String	getName()  { return name;}
    boolean isMale()    { return isMale;}
    int		getHak()   { return hak;}
    int		getBan()   { return ban;}
    int		getScore() { return score;}

    public String toString() {
        return String.format("[%s, %s, %d학년 %d반, %3d점]",
                name, isMale ? "남":"여", hak, ban, score);
    }
    // groupingBy()에서 사용
    enum Level { HIGH, MID, LOW }  // 성적을 상, 중, 하 세 단계로 분류
}

class StreamEx7 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("나자바", true,  1, 1, 300),
                new Student("김지미", false, 1, 1, 250),
                new Student("김자바", true,  1, 1, 200),
                new Student("이지미", false, 1, 2, 150),
                new Student("남자바", true,  1, 2, 100),
                new Student("안지미", false, 1, 2,  50),
                new Student("황지미", false, 1, 3, 100),
                new Student("강지미", false, 1, 3, 150),
                new Student("이자바", true,  1, 3, 200),

                new Student("나자바", true,  2, 1, 300),
                new Student("김지미", false, 2, 1, 250),
                new Student("김자바", true,  2, 1, 200),
                new Student("이지미", false, 2, 2, 150),
                new Student("남자바", true,  2, 2, 100),
                new Student("안지미", false, 2, 2,  50),
                new Student("황지미", false, 2, 3, 100),
                new Student("강지미", false, 2, 3, 150),
                new Student("이자바", true,  2, 3, 200)
        };

        System.out.printf("1. 단순분할(성별로 분할)%n");
        Map<Boolean, List<Student>> stuBySex =  Stream.of(stuArr) // key값에 있는 boolean으로 조회하는 것
                .collect(partitioningBy(Student::isMale));

        List<Student> maleStudent   = stuBySex.get(true);
        List<Student> femaleStudent = stuBySex.get(false);

        for(Student s : maleStudent)   System.out.println(s);
        System.out.println("=======================================");
        for(Student s : femaleStudent) System.out.println(s);

        System.out.printf("%n2. 단순분할 + 통계(성별 학생수)%n");
        Map<Boolean, Long> stuNumBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale, counting()));

        System.out.println("남학생 수 :"+ stuNumBySex.get(true));   // 8
        System.out.println("여학생 수 :"+ stuNumBySex.get(false));  // 10


        System.out.printf("%n3. 단순분할 + 통계(성별 1등)%n");
        Map<Boolean, Optional<Student>> topScoreBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale,
                        maxBy(comparingInt(Student::getScore))
                ));
        System.out.println("남학생 1등 :"+ topScoreBySex.get(true));  //Optional[[나자바, 남, 1학년 1반, 300점]]
        System.out.println("여학생 1등 :"+ topScoreBySex.get(false)); //Optional[[김지미, 여, 1학년 1반, 250점]]

        Map<Boolean, Student> topScoreBySex2 = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale,
                        collectingAndThen(
                                maxBy(comparingInt(Student::getScore)), Optional::get
                        )
                ));
        System.out.println("남학생 1등 :"+ topScoreBySex2.get(true));//[나자바, 남, 1학년 1반, 300점]
        System.out.println("여학생 1등 :"+ topScoreBySex2.get(false));//[김지미, 여, 1학년 1반, 250점]

        System.out.printf("%n4. 다중분할(성별 불합격자, 100점 이하)%n");

        Map<Boolean, Map<Boolean, List<Student>>> failedStuBySex =
                Stream.of(stuArr).collect(partitioningBy(Student::isMale,
                        partitioningBy(s -> s.getScore() <= 100))
                );
        List<Student> failedMaleStu   = failedStuBySex.get(true).get(true); // 분할이 두개이므로 get 호출도 두번해준다.
        List<Student> failedFemaleStu = failedStuBySex.get(false).get(true);

        for(Student s : failedMaleStu)   System.out.println(s);
        //[남자바, 남, 1학년 2반, 100점]
        //[남자바, 남, 2학년 2반, 100점]
        System.out.println("=========================================");

        for(Student s : failedFemaleStu) System.out.println(s);
    }
}
```
+ boolean값을 기준으로 나누고, get(boolean key값)으로 목록을 호출할 수 있다.
+ partitioningBy를 여러번 사용해서 이중 분할 할수도 있다.
+ collectingAndThen()을 통해 반환타입을 수정해줄 수 있다.

### groupingBy() 에 의한 분류
```java
class StreamEx8 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("나자바", true,  1, 1, 300),
                new Student("김지미", false, 1, 1, 250),
                new Student("김자바", true,  1, 1, 200),
                new Student("이지미", false, 1, 2, 150),
                new Student("남자바", true,  1, 2, 100),
                new Student("안지미", false, 1, 2,  50),
                new Student("황지미", false, 1, 3, 100),
                new Student("강지미", false, 1, 3, 150),
                new Student("이자바", true,  1, 3, 200),

                new Student("나자바", true,  2, 1, 300),
                new Student("김지미", false, 2, 1, 250),
                new Student("김자바", true,  2, 1, 200),
                new Student("이지미", false, 2, 2, 150),
                new Student("남자바", true,  2, 2, 100),
                new Student("안지미", false, 2, 2,  50),
                new Student("황지미", false, 2, 3, 100),
                new Student("강지미", false, 2, 3, 150),
                new Student("이자바", true,  2, 3, 200)
        };

        System.out.printf("1. 단순그룹화(반별로 그룹화)%n");
        Map<Integer, List<Student>> stuByBan = Stream.of(stuArr)
                .collect(groupingBy(Student::getBan));

        for(List<Student> ban : stuByBan.values()) {
            for(Student s : ban) {
                System.out.println(s);
            }
        }

        System.out.printf("%n2. 단순그룹화(성적별로 그룹화)%n");
        Map<Student.Level, List<Student>> stuByLevel = Stream.of(stuArr)        // 열거형의 자료형
                .collect(groupingBy(s-> {
                    if(s.getScore() >= 200) return Student.Level.HIGH;      // 열거형
                    else if(s.getScore() >= 100) return Student.Level.MID;
                    else                         return Student.Level.LOW;
                }));

        TreeSet<Student.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for(Student.Level key : keySet) {
            System.out.println("["+key+"]");

            for(Student s : stuByLevel.get(key))
                System.out.println(s);
            System.out.println();
        }

        System.out.printf("%n3. 단순그룹화 + 통계(성적별 학생수)%n");
        Map<Student.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(groupingBy(s-> {
                    if(s.getScore() >= 200) return Student.Level.HIGH;
                    else if(s.getScore() >= 100) return Student.Level.MID;
                    else                         return Student.Level.LOW;
                }, counting()));

        for(Student.Level key : stuCntByLevel.keySet())
            System.out.printf("[%s] - %d명, ", key, stuCntByLevel.get(key));
        System.out.println();
/*
		for(List<Student> level : stuByLevel.values()) {
			System.out.println();
			for(Student s : level) {
				System.out.println(s);
			}
		}
*/
        System.out.printf("%n4. 다중그룹화(학년별, 반별)%n");
        Map<Integer, Map<Integer, List<Student>>> stuByHakAndBan =
                Stream.of(stuArr)
                        .collect(groupingBy(Student::getHak,
                                groupingBy(Student::getBan)
                        ));                                         // 이중 Map이 만들어진다., 또한 기본형인 List<Student>로 생성

        for(Map<Integer, List<Student>> hak : stuByHakAndBan.values()) {
            for(List<Student> ban : hak.values()) {
                System.out.println();
                for(Student s : ban)
                    System.out.println(s);
            }
        }

        System.out.printf("%n5. 다중그룹화 + 통계(학년별, 반별 1등)%n");
        Map<Integer, Map<Integer, Student>> topStuByHakAndBan = Stream.of(stuArr)
                .collect(groupingBy(Student::getHak,
                        groupingBy(Student::getBan,
                                collectingAndThen(
                                        maxBy(comparingInt(Student::getScore)),
                                        Optional::get                   // maxBy의 기본 반환타입 Optional<Student> -> Student
                                )
                        )
                ));

        for(Map<Integer, Student> ban : topStuByHakAndBan.values())
            for(Student s : ban.values())
                System.out.println(s);

        System.out.printf("%n6. 다중그룹화 + 통계(학년별, 반별 성적그룹)%n");
        Map<String, Set<Student.Level>> stuByScoreGroup = Stream.of(stuArr)     // Set<Student.Level> , 알맞은 지네릭 입력
                .collect(groupingBy(s-> s.getHak() + "-" + s.getBan(),
                        mapping(s-> {
                            if(s.getScore() >= 200) return Student.Level.HIGH;
                            else if(s.getScore() >= 100) return Student.Level.MID;
                            else                    return Student.Level.LOW;
                        } , toSet())                                            // mapping 후 Set으로 저장
                ));

        Set<String> keySet2 = stuByScoreGroup.keySet();

        for(String key : keySet2) {
            System.out.println("["+key+"]" + stuByScoreGroup.get(key));
        }
    }  // main의 끝
}
```
+ 기본적으로 그룹화하면 List\<T\>에 담는다. 
> 다른 컬렉션에 담는법 
```
Map<Integer, HashSet<Student>> stuByHak = stuStream
                                          .collect(groupingBy(Student::getHak, toCollection(HashSet::new)));
```
+ collectingAndThen()을 통해 반환타입을 수정해줄 수 있다.

## 2.7 Collector 구현하기
+ 지금까지는 Collectors 클래스가 제공하는 컬렉터를 사용해왔지만, 이제는 우리가 컬렉터를 작성해보자.
+ Collector 인터페이스를 구현하여 컬렉터를 작성할 수 있다.
```
public interface Collector<T, A, R> {
	Supplier<A>		supplier();
	BiConsumer<A, T>	accumulator();
	BinaryOperator<A>	combiner();
	Function<A, R>		finisher();
	
	Set<Characteristics>	characteristics(); // 컬렉터의 특성이 담긴 Set을 반환
	...
}
```
+ supplier() 	: 작업결과를 저장할 공간을 제공
+ accumulator() : 스트림의 요소를 수집할 방법을 제공하고 supplier에 누적한다.
+ combiner()	: 두 저장공간을 병합할 방법을 제공(병렬 스트림)
+ finisher()	: 결과를 최종적으로 변환할 방법을 제공 ( 그대로 변환 : Function.identity();로 항승 함수를 반환한다.)

> characteristics()는 컬렉터가 수행하는 작업의 속성에 대한 정보 제공
```
Characteristics.CONCURRENT	: 병렬로 처리할 수 있는 작업
Characteristics.UNORDERED	: 스트림의 요소가 순서가 유지될 필요가 없는 작업
Characteristics.IDENTITY_FINISH	: finish()가 항등함수인 작업
Characteristics.emptySet();	: 지정할 특성이 없는 경우
```
+ 내부적으로 앞의 reduce()에 나온 연산식들로, Collector도 내부적으로 처리하는 과정이 리듀싱과 같다.
+ 그룹화와 분할, 집계등에 유용하게 쓰이고 병렬화에 있어서는 collect()가 유리하다. 반대로 이외의 간단한 작업들은 reduce가 더 유리하다.

## 2.8 스트림의 변환
+ 스트림을 다른 타입으로 변환할때 어떤 메서드를 사용해야하는지 정리한 파트.
+ 책참고하기.
