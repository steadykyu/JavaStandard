package ch9_javalang;

class Card{
    String kind;
    int number;

    Card(){
        this("SPADE",1);
    }

    Card(String kind, int number){
        this.kind = kind;
        this.number =number;
    }

//    public String toString(){
//        return "kind : " + kind + ", number : " + number;
//    }
//    //9-6오버라이딩 한 경우

}

public class ch9_4CardToString {
    public static void main(String[] args) {
        Card c1 = new Card();
        Card c2 = new Card();

        System.out.println(c1.toString());
        System.out.println(c2.toString());
    }
}
