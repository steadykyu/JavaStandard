package ch12_Generic_enum_annotation.ch12_1;

import java.util.ArrayList;

class Fruit				  { public String toString() { return "Fruit";}}
class Apple extends Fruit { public String toString() { return "Apple";}}
class Grape extends Fruit { public String toString() { return "Grape";}}
class Toy		          { public String toString() { return "Toy"  ;}}

class Ch12_1FruitBoxEx1 {
    public static void main(String[] args) {
        Box<Fruit> fruitBox = new Box<Fruit>();
        Box<Apple> appleBox = new Box<Apple>();
        Box<Toy>   toyBox   = new Box<Toy>();
//		Box<Grape> grapeBox = new Box<Apple>(); // 에러. 타입 불일치
//		Box<Fruit> grapeBox = new Box<Apple>(); // 에러. 타입 불일치(상속case)

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple()); // OK. void add(Fruit item) / 자손 인스턴스가 들어감.

        appleBox.add(new Apple());
        appleBox.add(new Apple());
//		appleBox.add(new Toy()); // 에러. Box<Apple>에는 Apple만 담을 수 있음

        toyBox.add(new Toy());
//		toyBox.add(new Apple()); // 에러. Box<Toy>에는 Apple을 담을 수 없음

        System.out.println(fruitBox);
        System.out.println(appleBox);
        System.out.println(toyBox);
    }  // main의 끝
}

class Box<T> {
    ArrayList<T> list = new ArrayList<T>();

    void add(T item)  { list.add(item); }
    T get(int i)      { return list.get(i); }
    int size() { return list.size(); }
    public String toString() { return list.toString();}
}
