package ch12_Generic_enum_annotation.ch12_4;

import java.util.*;

class Fruit	{
    String name;
    int weight;

    Fruit(String name, int weight) {
        this.name   = name;
        this.weight = weight;
    }

    public String toString() { return name+"("+weight+")";}

}

class Apple extends Fruit {
    Apple(String name, int weight) {
        super(name, weight);
    }
}

class Grape extends Fruit {
    Grape(String name, int weight) {
        super(name, weight);
    }
}

// weight 내림차순
class AppleComp implements Comparator<Apple> {
    public int compare(Apple t1, Apple t2) {
        return t2.weight - t1.weight;
    }
}
// weight 내림차순
class GrapeComp implements Comparator<Grape> {
    public int compare(Grape t1, Grape t2) {
        return t2.weight - t1.weight;
    }
}
// weight 오름차순
// 예시는 AppleComp, GrapeComp가 존재해서 필요없어보이지만
// sort 메서드 안에 하한 제한이 존재하므로, 어떤 과일을 추가하더라도 FruitComp 만으로 정렬기준을 정해줄 수 있다.
class FruitComp implements Comparator<Fruit> {
    public int compare(Fruit t1, Fruit t2) {
        return t1.weight - t2.weight;
    }
}

class FruitBoxEx4 {
    public static void main(String[] args) {
        FruitBox<Apple> appleBox = new FruitBox<Apple>();
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();

        appleBox.add(new Apple("GreenApple", 300));
        appleBox.add(new Apple("GreenApple", 100));
        appleBox.add(new Apple("GreenApple", 200));

        grapeBox.add(new Grape("GreenGrape", 400));
        grapeBox.add(new Grape("GreenGrape", 300));
        grapeBox.add(new Grape("GreenGrape", 200));

        Collections.sort(appleBox.getList(), new AppleComp());
        Collections.sort(grapeBox.getList(), new GrapeComp());
        System.out.println(appleBox);
        System.out.println(grapeBox);
        System.out.println();
        Collections.sort(appleBox.getList(), new FruitComp());               // sort 안의 하한제한  super를 확인해보자.
        Collections.sort(grapeBox.getList(), new FruitComp());               // 그 덕분에 Fruit의 정렬 기준을 사용할 수 있다.
        System.out.println(appleBox);
        System.out.println(grapeBox);
    }  // main
}

class FruitBox<T extends Fruit> extends Box<T> {}

class Box<T> {
    ArrayList<T> list = new ArrayList<T>();

    void add(T item) {
        list.add(item);
    }

    T get(int i) {
        return list.get(i);
    }

    ArrayList<T> getList() { return list; }

    int size() {
        return list.size();
    }

    public String toString() {
        return list.toString();
    }
}

