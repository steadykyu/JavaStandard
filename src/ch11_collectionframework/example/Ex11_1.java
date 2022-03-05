package ch11_collectionframework.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ex11_1 {
    public static void main(String[] args) {
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        ArrayList kyo = new ArrayList(); // 교집합
        ArrayList cha = new ArrayList(); // 차집합
        ArrayList hap = new ArrayList(); // 합집합
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        // 교집합
        kyo.addAll(list1);
        kyo.retainAll(list2);

        // 차집합
        cha.addAll(list1);
        cha.removeAll(list2);

        // 합집합
        // hap을 set 으로 바꾸자.
        Set tmp = new HashSet((hap));
        tmp.addAll(list1);
        tmp.addAll(list2);
        // hap에 set의 모든 객체들을 넣어주자.
        hap.addAll(tmp);

        /** 해답
         * hap.addAll(list1); // list1 의 모든 요소를  hap 에 저장한다
         * hap.removeAll(kyo); // hap 에서 kyo와 공통된 모든 요소를 삭제한다
         * hap.addAll(list2); //  list2의 모든 요소를  hap에 저장한다.
         */

        System.out.println("list1="+list1);
        System.out.println("list2="+list2);
        System.out.println("kyo="+kyo);
        System.out.println("cha="+cha);
        System.out.println("hap="+hap);
    }

}
