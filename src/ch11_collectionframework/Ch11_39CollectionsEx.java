package ch11_collectionframework;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static java.util.Collections.*;

public class Ch11_39CollectionsEx {
    public static void main(String[] args) {

        List list = new ArrayList();
        System.out.println(list);

        // Collections 클래스를 import하여 정적메서드를 호출한다.
        addAll(list, 1,2,3,4,5);
        System.out.println(list);

        rotate(list, 2);    //오른쪽으로 두칸이동
        System.out.println(list);

        swap(list, 0, 2);       // index 에 있는 요소값 교환
        System.out.println(list);

        shuffle(list);
        System.out.println(list);

        sort(list, reverseOrder());     // 역순 정렬 reverse(list);와 동일
        System.out.println(list);

        sort(list);
        System.out.println(list);

        int idx = binarySearch(list, 3);        // 3이 저장된 위치의 index 반환
        System.out.println("index of 3 = " + idx);

        System.out.println("max="+max(list));
        System.out.println("min="+min(list));
        System.out.println("min="+max(list, reverseOrder()));
        System.out.println(list);

        fill(list, 9);          // list를 9로 채운다.
        System.out.println("list = "+   list);

        // list와 같은크기의 새로운 list를 생성하고 2로 채운다. 단 변경이 불가능하다.
        List newList = nCopies(list.size(), 2);
        System.out.println("newList="+newList);

        System.out.println(disjoint(list, newList));        // 공통 요소가 없으면 true

        copy(list, newList);
        System.out.println("newList="+newList);
        System.out.println("list"+list);

        replaceAll(list, 2, 1);
        System.out.println("list="+list);

        Enumeration e = enumeration(list);          // list 참조변수의 Enumeration을 반환
        ArrayList list2 = list(e);                  // Enumeration (iteration 구버전)을 다시 collection의 형태로 바꾸어줌.
        // 이전까지의 참조변수가 아니라, list 메서드이다.

        System.out.println("list2="+list);
    }
}
