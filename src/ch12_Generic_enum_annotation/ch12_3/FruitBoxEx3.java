package ch12_Generic_enum_annotation.ch12_3;

import java.util.ArrayList;

class Fruit		          { public String toString() { return "Fruit";}}
class Apple extends Fruit { public String toString() { return "Apple";}}
class Grape extends Fruit { public String toString() { return "Grape";}}

class Juice {
    String name;

    Juice(String name)	     { this.name = name + "Juice"; }
    public String toString() { return name;		 }
}

class Juicer {
    static Juice makeJuice(FruitBox<? extends Fruit> box) {
        String tmp = "";

        for(Fruit f : box.getList())
            tmp += f + " ";
        return new Juice(tmp);
    }
}

class FruitBoxEx3 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<Fruit>();       // Fruit으로 제한시켜놓은 Box 클래스가 생성
        FruitBox<Apple> appleBox = new FruitBox<Apple>();       // Apple로 제한 시켜놓은 Box 클래스가 생성


        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        System.out.println(Juicer.makeJuice(fruitBox));
        System.out.println(Juicer.makeJuice(appleBox));
    }  // main
}

// Box의 자손인 FruitBox
class FruitBox<T extends Fruit> extends Box<T> {}

class Box<T> {
    //class FruitBox<T extends Fruit> {
    ArrayList<T> list = new ArrayList<T>();                 // T -> Fruit이 오면 제한만 시킨거지, ArrayList에는 어떤 객체도 들어올 수 있다. 근데 그럴 필요는 없을듯
    void add(T item) { list.add(item);      }
    T get(int i)     { return list.get(i); }
    ArrayList<T> getList() { return list;  }
    int size()       { return list.size(); }
    public String toString() { return list.toString();}
}

