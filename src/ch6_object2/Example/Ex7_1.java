package ch6_object2.Example;


class SutdaDeck {
        final int CARD_NUM = 20;
        SutdaCard[] cards = new SutdaCard[CARD_NUM];

        SutdaDeck() {
            for(int i = 0; i < cards.length; i++){
                if(i+1==1 || i+1==3 || i+1==8){
                    cards[i] = new SutdaCard(i+1,true);
                }else if (i < 9){
                    cards[i] = new SutdaCard(i+1,false);
                }else{
                    cards[i] = new SutdaCard(i-9,false);
                }
           }
        }

        void shuffle(){
            int n = (int)(Math.random() * cards.length) +1;
            for(int i = 0; i < cards.length; i++){
                SutdaCard temp = cards[i];
                cards[i] = cards[n];
                cards[n] = temp;
            }
        }

        SutdaCard pick(int index){
            return cards[index];
        }

        SutdaCard pick(){
            int randomIndex = (int)(Math.random() * cards.length);
            return cards[randomIndex];
        }
}
class SutdaCard {
        int num;
        boolean isKwang;
        SutdaCard() {
            this(1, true);
        }
        SutdaCard(int num, boolean isKwang) {
            this.num = num;
            this.isKwang = isKwang;
        }
        // info() Object toString() . 대신 클래스의 을 오버라이딩했다
        public String toString() {
            return num + ( isKwang ? "K":"");
        }
}
public class Ex7_1 {
        public static void main(String args[]) {
            SutdaDeck deck = new SutdaDeck();
//            for(int i=0; i < deck.cards.length;i++)
//                System.out.print(deck.cards[i]+",");
            System.out.println(deck.pick(0));
            System.out.println(deck.pick());
            deck.shuffle();
            for(int i=0; i < deck.cards.length;i++)
                System.out.print(deck.cards[i]+",");
            System.out.println();
            System.out.println(deck.pick(0));

        }
}



