package ch6_object2;

import java.util.Vector;

class Product{
    int price;
    int bonusPoint;

    Product(int price){
        this.price = price;
        bonusPoint = (int)(price/10.0);
    }

    //매개변수 유효성을 위해 만들어만 놓은듯.
    Product(){
        price = 0;
        bonusPoint = 0;
    }
}
class Tv extends Product{
    Tv(){ super(100 );}
    public String toString() { return "Tv";}
}

class Computer extends Product{
    Computer(){ super(200 );}
    public String toString() { return "Computer";}
}

class Audio extends Product{
    Audio(){ super(50 );}
    public String toString() { return "Audio";}
}

class Buyer{
    int money = 1000;
    int bonusPoint = 0;
//    Product[] item = new Product[10];
//    int i = 0  Product 배열의 인덱스
    Vector item = new Vector();


    void buy(Product p){
        if (money < p.price){
            System.out.println("잔액이 부족하여 구매가 불가능합니다.");
            return;
        }
        money -= p.price;
        bonusPoint += p.bonusPoint;
        //item[i++] = p;
        item.add(p);
        // p 즉 참조변수의 주소값을 추가한다. 근데 이 주소의 타입은 조상타입이지만 자손인스턴스를 가리키고 있는것이다.
        System.out.println(p + "을/를 구매하셨습니다.");
    }

    void refund(Product p){
        if (item.remove(p)) {
            money += p.price;
            bonusPoint -= p.bonusPoint;
            System.out.println(p + "을/를 반품하셨습니다.");
        } else {
            System.out.println("구입하신 제품중 해당 물품이 없습니다.");
        }
    }

    void summary(){
        int sum = 0;
        String itemList = "";
        if(item.isEmpty()){
            System.out.println("구입하신 제품이 없습니다.");
            return;
        }

        for(int i = 0; i < item.size(); i++){
            Product p = (Product)item.get(i);
            sum += p.price;
            itemList += (i==0) ? "" + p : ", "+ p;
        }
        System.out.println("구입하신 물품의 총금액은 "+ sum + "만원입니다.");
        System.out.println("구입하신 제품은 " + itemList + "입니다.");
    }
}

public class Ch7_23_PolyArgumentTest3 {
    public static void main(String[] args) {
        Buyer b = new Buyer();
        Tv tv = new Tv();
        Computer com = new Computer();
        Audio audio = new Audio();

        b.buy(tv);
        b.buy(com);
        b.buy(audio);
        b.summary();
        System.out.println();
        b.refund(com);
        b.summary();
    }
}
