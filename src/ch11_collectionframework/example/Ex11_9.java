package ch11_collectionframework.example;


import java.util.*;

class ClassTotalComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        if(o1 instanceof Student && o2 instanceof Student){
            Student st1 = (Student)o1;
            Student st2 = (Student)o2;

            int result = st1.ban - st2.ban;

            if (result==0){
                return st2.total - st1.total;   // 내림차순
            }
            return result;
            // compare의 return과 메서드의 return을 잘활용한 예시
        }
        return -1;
    }
}

class Ex11_9 {
    public static void calculateClassRank(List list) {
// . 먼저 반별 총점기준 내림차순으로 정렬한다
        Collections.sort(list, new ClassTotalComparator());
        int prevBan = -1;
        int prevRank = -1;
        int prevTotal = -1;
        int length = list.size();

        for(int i = 0, n=0; i < length; i++, n++){
            Student s = (Student) list.get(i);

            if(s.ban != prevBan){
                prevRank = -1;
                prevTotal = -1;
                n = 0;
                // 여기서 i를 초기화 할수 없기 때문에, n을 추가했다.
            }

            if(s.total==prevTotal){
                s.classRank = prevRank;
            }else{
                s.classRank = n + 1;
            }
            prevBan = s.ban;
            prevRank = s.classRank;
            prevTotal = s.total;
        }

    } // public static void calculateClassRank(List list) {
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
        calculateClassRank(list);

        Iterator it = list.iterator();
        while(it.hasNext())
            System.out.println(it.next());
    }
}
