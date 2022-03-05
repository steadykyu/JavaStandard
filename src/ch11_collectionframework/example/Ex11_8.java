package ch11_collectionframework.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Ex11_8 {
    public static void calculateSchoolRank(List list) {
        Collections.sort(list); // list . 먼저 를 총점기준 내림차순으로 정렬한다
        int prevRank = -1; // 이전 전교등수
        int prevTotal = -1; // 이전 총점
        int length = list.size();

        for(int i = 0; i < length; i++){
            Student str = (Student)list.get(i);
            //총점이 같으면 student는 prevRank를 등수로 가진다.
            if(str.total == prevTotal){
               str.schoolRank =  prevRank;
            // 이미  총점이 높은 순으로 정렬이 되어있는 List이기 때문에 순위대로 Student가 들어올 것이다.
            }else{
                str.schoolRank = i + 1;
            }
            prevRank = str.schoolRank;
            prevTotal = str.total;
        }

    }
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(new Student("이자바",2,1,70,90,70));
        list.add(new Student("안자바",2,2,60,100,80));
        list.add(new Student("홍길동",1,3,100,100,100));
        list.add(new Student("남궁성",1,1,90,70,80));
        list.add(new Student("감자바",1,2,80,80,90));

        calculateSchoolRank(list);

        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
}

