package ch12_Generic_enum_annotation.example;


class DeckTest {
public static void main(String args[]) {
        Deck d = new Deck(); // (Deck) . 카드 한 벌 을 만든다
        Card c = d.pick(0); // . 섞기 전에 제일 위의 카드를 뽑는다 [CLOVER,ACE] 일정
        System.out.println(c); // System.out.println(c.toString()); . 과 같다
        d.shuffle(); // . 카드를 섞는다
        c = d.pick(0); // . 섞은 후에 제일 위의 카드를 뽑는다.
        System.out.println(c);
        }
        }
class Deck {
    final int CARD_NUM = Card.Kind.values().length
            * Card.Number.values().length; // 카드의 개수
    Card[] cardArr = new Card[CARD_NUM]; // Card객체 배열을 포함

    // Deck Card를 초기화한다.
    Deck () {
      Card.Kind[]  kinds = Card.Kind.values();
      Card.Number[] numbers = Card.Number.values();
//    Kind 와 Number가 들어있는 cardArr를 만들어야 함.
        int index = 0;
        for(int i = 0; i < kinds.length; i++) {
            for(int j =0; j < numbers.length; j++){
                cardArr[index++] = new Card(kinds[i],numbers[j]);       // 하나 넣을때마다 index를 증가시킨다.
            }
        }
        /**해답 향상된 for문
         * int i=0;
         * for(Card.Kind kind : Card.Kind.values()) {
         * for(Card.Number num : Card.Number.values()) {
         * cardArr[i++] = new Card(kind, num);
         * }
         * }
         */
    }

    Card pick(int index) { // (index) 지정된 위치 에 있는 카드 하나를 꺼내서 반환
        return cardArr[index];
    }
    Card pick() { // Deck . 에서 카드 하나를 선택한다
        int index = (int)(Math.random() * CARD_NUM);
        return pick(index);
    }
    void shuffle() { // . 카드의 순서를 섞는다
        for(int i=0; i < cardArr.length; i++) {
            int r = (int)(Math.random() * CARD_NUM);
            Card temp = cardArr[i];
            cardArr[i] = cardArr[r];
            cardArr[r] = temp;
        }
    }
} // Deck클래스의 끝
// Card클래스
class Card {
    enum Kind { CLOVER, HEART, DIAMOND, SPADE }
    enum Number {
        ACE, TWO, THREE, FOUR, FIVE,
        SIX, SEVEN, EIGHT, NINE, TEN,
        JACK, QUEEN, KING
    }
    Kind kind;
    Number num;

    Card() {
        this(Kind.SPADE, Number.ACE);
    }

    Card(Kind kind, Number num) {
        this.kind = kind;
        this.num = num;
    }

    public String toString() {
        return "[" + kind.name() + "," + num.name() + "]";
    } // toString()의 끝
} // Card클래스의 끝
