package ch11_collectionframework;

import java.util.*;

class Ch11_26TreeSetLotto {
    public static void main(String[] args) {
        Set set = new TreeSet();

        for (int i = 0; set.size() < 6 ; i++) {
            int num = (int)(Math.random()*45) + 1;
            set.add(num);  // set.add(new Integer(num));
        }

        System.out.println(set);
    }
}
// 저장할때 부터 이미 정렬되있는 모습을 확인할 수 있다.
