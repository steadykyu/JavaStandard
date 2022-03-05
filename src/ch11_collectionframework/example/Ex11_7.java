package ch11_collectionframework.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

class BanNoAscending implements Comparator {
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Student && o2 instanceof Student){
            Student s1 = (Student) o1;
            Student s2 = (Student) o2;

            if(s1.ban != s2.ban)
                return s1.ban-s2.ban;
            else
                return s1.no-s2.no;

            /** 해답
             * int result = s1.ban - s2.ban;
             * if(result==0) { // , . 반이 같으면 번호를 비교한다
             * return s1.no - s2.no;
             * }
             */
        }
        return -1;
    }
}

class Ex11_7 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(new Student("이자바",2,1,70,90,70));
        list.add(new Student("안자바",2,2,60,100,80));
        list.add(new Student("홍길동",1,3,100,100,100));
        list.add(new Student("남궁성",1,1,90,70,80));
        list.add(new Student("감자바",1,2,80,80,90));

        Collections.sort(list, new BanNoAscending());

        Iterator it = list.iterator();

        while(it.hasNext())
            System.out.println(it.next());
    }
}
