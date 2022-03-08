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
