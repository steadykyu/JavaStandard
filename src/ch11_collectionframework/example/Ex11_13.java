package ch11_collectionframework.example;

import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

public class Ex11_13 {
    public static void main(String args[]) throws Exception {
        SutdaDeck deck = new SutdaDeck();
        deck.shuffle();
        Player[] pArr = {
                new Player("타짜", deck.pick(), deck.pick()),
                new Player("고수", deck.pick(), deck.pick()),
                new Player("물주", deck.pick(), deck.pick()),
                new Player("중수", deck.pick(), deck.pick()),
                new Player("하수", deck.pick(), deck.pick())
        };
        TreeMap rank = new TreeMap(new Comparator() {
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Player && o2 instanceof Player){
                   Player player = (Player)o1;
                   Player player2 = (Player)o2;

                   return player2.point - player.point; // 내림차순한다.
                }
                return -1;
            }
        });
        for(int i=0; i < pArr.length;i++) {
            Player p = pArr[i];
            rank.put(p, deck.getPoint(p));  //Player가 key,
            System.out.println(p+" "+deck.getPoint(p));
        }
        System.out.println();
        System.out.println("1위는 "+rank.firstKey()+"입니다.");
    }
}


