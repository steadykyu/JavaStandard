package ch11_collectionframework;

import java.util.*;

class Ch11_22Bingo {
    public static void main(String[] args) {
        Set set = new HashSet();
//          Set set = new LinkedHashSet();
        int[][] board = new int[5][5];

        // 25개의 숫자를 난수로 뽑아서 넣는다.
        for(int i=0; set.size() < 25; i++) {
            set.add((int)(Math.random()*50)+1+"");
        }

        Iterator it = set.iterator();

        for(int i=0; i < board.length; i++) {
            for(int j=0; j < board[i].length; j++) {
                board[i][j] = Integer.parseInt((String)it.next());
                System.out.print((board[i][j] < 10 ? "  " : " ") + board[i][j]);
            }
            System.out.println();
        }
    } // main
}
