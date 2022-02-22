package ch6_object2;

public class Ch7_3_Desktop {
    public static void main(String[] args) {
        Deck d = new Deck();
        Card c = d.pick(0); //첫번째에 위치한 카드 뽑기.
        System.out.println(c);

        d.shuffle();
        c = d.pick(0);
        System.out.println(c);
    }
}

class Deck{
    final int Card_Num = 52; // 카드의 개수
    Card cardArr[] = new Card[Card_Num];    // Card객체를 가진 배열을 만듬.

    Deck() {
        int i = 0;

        //초기화를 통해 모든 숫자, 무늬 종류의 카드를 만듬.
        // 3 개의 변수를 이용한 for문이다.;
        for (int k = Card.KIND_MAX; k > 0; k--) {
            for (int n = 0; n < Card.NUM_MAX; n++) {
                cardArr[i++] = new Card(k, n + 1);      // 이렇게 대입할수 도 있네.
            }
        }
    }

        Card pick(int index){       // 지정한 위치에 있는 카드 뽑기.
            return cardArr[index];
        }

        Card pick(){            // 카드 하나 뽑기
            int index = (int)(Math.random() * Card_Num);
            return pick(index);
        }

        void shuffle(){
            for(int i =0; i < cardArr.length; i++){
                int r = (int)Math.random() * Card_Num;

                Card temp = cardArr[i];
                cardArr[i] = cardArr[r];
                cardArr[r] = temp;
            }
        }
}


class Card{
    static final int KIND_MAX = 4;  //카드 무늬수
    static final int NUM_MAX = 13;  //무늬별 카드수

    static final int SPADE = 4;
    static final int DIAMOND = 3;
    static final int HEART = 2;
    static final int CLOVER = 1;
    int kind;
    int number;

    Card(){
        this(SPADE,1);
    }

    Card(int kind, int number){
        this.kind = kind;
        this.number = number;
    }

    public String toString(){
        String[] kinds = {"", "CLOVER", "HEART", "DIAMOND", "SPADE"};
        String numbers = "0123456789XJQK";
        return "kind : " + kinds[this.kind] + ", number : " + numbers.charAt(this.number);
    }
}