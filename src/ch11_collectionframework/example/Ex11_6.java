package ch11_collectionframework.example;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Ex11_6 {
    static int getGroupCount(TreeSet tset, int from, int to) {
        Student s1 = new Student("",0,0,from,from,from);
        Student s2 = new Student("",0,0,to,to,to);

        return tset.subSet(s1, s2).size();
        // Student의 정렬 기준이 현재 평균값이므로, Student의 평균값을 대상으로 범위 검색을 실행한다.
        // 결과로 나오는 Set의 size를 구해준다.
    }

    public static void main(String[] args) {

        TreeSet set = new TreeSet(new Comparator() {

            public int compare(Object o1, Object o2) {
                if(o1 instanceof Student && o2 instanceof Student){
                    Student s1 = (Student)o1;
                    Student s2 = (Student)o2;
                return (int)(s1.getAverage() - s2.getAverage());
                }
                return -1;
            }
        });
        // 내부에 이제 메서드를 구현하고 있는 모양
        // 리턴값이 양수이면 오름차순, 음수이면 내림차순으로 진행된다.
        // 그러므로 평균값의 차이를 입력하였다.(s2, s1 방향을 바꾸면 내림차순이 된다.(리턴값이 양수라면))
        // instanceof 활용하는 방식을 유의해서 보자.

        set.add(new Student("홍길동",1,1,100,100,100));
        set.add(new Student("남궁성",1,2,90,70,80));
        set.add(new Student("김자바",1,3,80,80,90));
        set.add(new Student("이자바",1,4,70,90,70));
        set.add(new Student("안자바",1,5,60,100,80));

        Iterator it = set.iterator();
        while(it.hasNext())
            System.out.println(it.next());

        System.out.println("[60~69] :"+getGroupCount(set,60,70));
        System.out.println("[70~79] :"+getGroupCount(set,70,80));
        System.out.println("[80~89] :"+getGroupCount(set,80,90));
        System.out.println("[90~100] :"+getGroupCount(set,90,101));
    }
}


