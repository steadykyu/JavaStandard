package ch6_object2;

class Card2 {
    final int NUMBER;
    final String KIND;
    static int width = 100;
    static int height = 250;

//    생성자를 통해 상수에 값을 넣어줌
    Card2(String kind, int num){
        KIND = kind;
        NUMBER = num;
    }

    Card2(){
        this("HEART", 1);
    }

    public String toString(){
        return KIND + " " + NUMBER;
    }
}

public class Ch7_12_FinalCardTest {
    public static void main(String[] args) {
        Card2 c = new Card2("HEART", 10);
//        c.NUMBER = 5;         상수 이기때문에 변경불가.
        System.out.println(c.KIND);
        System.out.println(c.NUMBER);
        System.out.println(c);
    }
}
