package ch6_object1;

public class ch6_5_CardTest {
    public static void main(String[] args) {
        System.out.println("Card.width = "+ Card.width);
        System.out.println("Card.heigth = " + Card.height);

        Card c1 = new Card();
        c1.kind = "Heart";
        c1.number = 7;

        Card c2 = new Card();
        c2.kind = "Spade";
        c2.number = 4;

        System.out.println("c1은 " + c1.kind + "," + c1.number + "이며, 크기는 (" + c1.width + "," + Card.height+ ")");
        System.out.println("c2은 " + c2.kind + "," + c2.number + "이며, 크기는 (" + c1.width + "," + Card.height+ ")");

        System.out.println("c1의 width와 heigth를 각각 50, 80으로 변경합니다");
        c1.width = 50; //Card.width
        c1.height = 80; //Card.height
        c2.number = 5;
        System.out.println("c1은 " + c1.kind + "," + c1.number + "이며, 크기는 (" + c1.width + "," + Card.height+ ")");
        System.out.println("c2은 " + c2.kind + "," + c2.number + "이며, 크기는 (" + c1.width + "," + Card.height+ ")");

    }
}

class Card{
    String kind ;
    int number;
    static int width = 100;
    static int height = 250;
}
