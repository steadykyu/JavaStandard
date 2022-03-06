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
fruitBox.add(new Fruit);
fruitBox.add(new Apple);    // 가능! ( 'Fruit fruit = Apple 인스턴스' 이므로)
fruitBox.add(new Fruit2);   // 불가능(Fruit이 와야함)
```
