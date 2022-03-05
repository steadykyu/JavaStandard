package ch11_collectionframework.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
// 5, 6, 7, 8 번문제
class Student implements Comparable{
    String name;
    int ban;
    int no;
    int kor, eng, math;

    int total; // 총점
    int schoolRank; // 전교등수
    int classRank; // 반등수

    Student(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;

        total = kor+eng+math;
    }
    int getTotal() {
        return kor+eng+math;
    }
    float getAverage() {
        return (int)((getTotal()/ 3f)*10+0.5)/10f;
    }
//    public String toString() {
//        return name +","+ban +","+no +","+kor +","+eng +","+math
//                +","+getTotal() +","+getAverage();
//    }

//    =================================== 5번 해답
//    public int compareTo(Object o) {
//        if(o instanceof Student) {
//            Student tmp = (Student)o;
//            return name.compareTo(tmp.name); //  String클래스의 compareTo()를 호출해서 사용한다.
//        } else {
//            return -1;
//        }
//    }

    //    =================================== 8번 해답
    public int compareTo(Object o) {
        if(o instanceof Student) {
            Student tmp = (Student)o;
            return new Integer(total).compareTo(new Integer(tmp.total)) * -1;
            /** return tmp.total - this.total;
             * 해답
             * 어차피 이쪽도 양수 음수냐가 중요한 것이므로 위처럼 값 두개를 바꿔서 빼주면 된다.
             * 굳이 Integer의 값을 비교하는 compareTo()를 쓸 필요가 없었다.**/

        } else {
            return -1;
        }
    }

    public String toString() {
        return name
                +","+ban
                +","+no
                +","+kor
                +","+eng
                +","+math
                +","+getTotal()
                +","+getAverage()
                +","+schoolRank // 새로추가
                +","+classRank; // 새로추가

    }

}
// 이름이 정렬 기준이 되어야한다.
public class Ex11_5 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(new Student("홍길동",1,1,100,100,100));
        list.add(new Student("남궁성",1,2,90,70,80));
        list.add(new Student("김자바",1,3,80,80,90));
        list.add(new Student("이자바",1,4,70,90,70));
        list.add(new Student("안자바",1,5,60,100,80));

        Collections.sort(list);


        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
}
/**
 * 그러나 한 가지 조건이 있다 ArrayList 에 저장된 요소들은 모두 Comparable인터페이스를 구현한 것이어야 한다는 것이다.
 * 이 인터페이스에는 compareTo 메서드가 정의되어 있는데 .  ,
 * 이 메서드는 Collections.sort(List list) 에 의해 호출되어 정렬기준을 제공하게 된다
 */