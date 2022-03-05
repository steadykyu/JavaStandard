package ch11_collectionframework.example;

import java.util.*;
class Ex11_12 {
    public static void main(String args[]) throws Exception {
        SutdaDeck deck = new SutdaDeck();
        deck.shuffle();
        Player p1 = new Player("타짜", deck.pick(), deck.pick());
        Player p2 = new Player("고수", deck.pick(), deck.pick());
        System.out.println(p1+" "+deck.getPoint(p1));
        System.out.println(p2+" "+deck.getPoint(p2));
    }
}
class SutdaDeck
{
    final int CARD_NUM = 20;
    SutdaCard[] cards = new SutdaCard[CARD_NUM];
    int pos = 0;                    // 다음에 가져올 카드의 위치

    HashMap jokbo = new HashMap(); // 족보를 저장할 HashMap

    SutdaDeck() {
        //섯다 카드는 20장이다.
        for(int i=0;i < cards.length;i++) {
            int num = i%10+1;
            boolean isKwang = i < 10 && (num==1 || num==3 || num==8);
            cards[i] = new SutdaCard(num,isKwang);
        }
        registerJokbo(); // . 족보를 등록한다
    }
    void registerJokbo() {
        jokbo.put("KK", 4000);      // int로 등록해도 Integer로 오토박싱

        jokbo.put("1010",3100);
        jokbo.put("99", 3090);
        jokbo.put("88", 3080);
        jokbo.put("77", 3070);
        jokbo.put("66", 3060);
        jokbo.put("55", 3050);
        jokbo.put("44", 3040);
        jokbo.put("33", 3030);
        jokbo.put("22", 3020);
        jokbo.put("11", 3010);
        jokbo.put("12", 2060);
        jokbo.put("21", 2060);
        jokbo.put("14", 2050);
        jokbo.put("41", 2050);
        jokbo.put("19", 2040);
        jokbo.put("91", 2040);
        jokbo.put("110", 2030);
        jokbo.put("101", 2030);
        jokbo.put("104", 2020);
        jokbo.put("410", 2020);
        jokbo.put("46", 2010);
        jokbo.put("64", 2010);

    }

    int getPoint(Player p) {
        if(p==null) return 0;
        SutdaCard c1 = p.c1;
        SutdaCard c2 = p.c2;
        Integer result = 0;
        String strtmp ="";

        if(c1.isKwang == true && c2.isKwang == true) {
            result = (Integer) jokbo.get("KK");
        }
        else {
            strtmp = c1.num + "" + c2.num;          // key값이 숫자두개를 문자로 더한것이라고 했으므로
            result = (Integer) jokbo.get(strtmp);   // Object로 반환되어 형변환 해주었음.

            // 족보가 없을때는 다음의 식을 따른다.
            if (result == null) {
                result = new Integer((c1.num + c2.num) % 10 + 1000);
            }
        }
        p.point = result.intValue();
        return result.intValue();
    }
    SutdaCard pick() throws Exception {
        SutdaCard c = null;
        if(0 <= pos && pos < CARD_NUM) {        // pos 0 이 맨위에 있는 카드라고 생각하자.
            c = cards[pos];                     // 그 카드의 값을 넣고
            cards[pos++] = null;                // 값을 null로 바꾼후 pos 위치를 증사시킨다.(카드뽑는 행동)
        } else {
            throw new Exception("남아있는 카드가 없습니다.");
        }
        return c;
    }
    void shuffle() {
        for(int x=0; x < CARD_NUM * 2; x++) {
            int i = (int)(Math.random() * CARD_NUM);
            int j = (int)(Math.random() * CARD_NUM);
            SutdaCard tmp = cards[i];
            cards[i] = cards[j];
            cards[j] = tmp;
        }
    }
} // SutdaDeck

class Player {
    String name;
    SutdaCard c1;
    SutdaCard c2;
    int point; // - 카드의 등급에 따른 점수 새로 추가

    Player(String name, SutdaCard c1, SutdaCard c2) {
        this.name = name ;
        this.c1 = c1 ;
        this.c2 = c2 ;
    }
    public String toString() {
        return "["+name+"]"+ c1.toString() +","+ c2.toString();
    }
} // class Player

