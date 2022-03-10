package ch14_lamda_stream.ch14_14;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

// Collectors static import 되어있는 상황!
class StreamEx6 {
    public static void main(String[] args) {
        Student[] stuArr = {
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
        };

        // 학생 이름만 뽑아서 List<String>에 저장
        List<String> names = Stream.of(stuArr).map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(names);
        System.out.println("==================================");
        // 스트림을 배열로 변환
        Student[] stuArr2 = Stream.of(stuArr).toArray(Student[]::new);

        for(Student s : stuArr2)
            System.out.println(s);
        System.out.println("==================================");

        // 스트림을 Map<String, Student>로 변환. 학생 이름이 key
        Map<String,Student> stuMap = Stream.of(stuArr)
                .collect(Collectors.toMap(s->s.getName(), p->p));
        for(String name : stuMap.keySet())
            System.out.println(name +"-"+stuMap.get(name));
        System.out.println("==================================");

        long count = Stream.of(stuArr).collect(counting());
        long totalScore = Stream.of(stuArr)
                .collect(summingInt(Student::getTotalScore));
        System.out.println("count="+count);
        System.out.println("totalScore="+totalScore);       // 1420
        System.out.println("==================================");

        totalScore = Stream.of(stuArr)
                .collect(reducing(0, Student::getTotalScore, Integer::sum)); //반환자료형이 long
        System.out.println("totalScore="+totalScore);       // 1420
        System.out.println("==================================");

        Optional<Student> topStudent = Stream.of(stuArr)
                .collect(maxBy(Comparator.comparingInt(Student::getTotalScore)));
        System.out.println("topStudent="+topStudent.get()); // [이자바, 3, 300]
        // 반환자료형이 Optional<Student>

        IntSummaryStatistics stat = Stream.of(stuArr)
                .collect(summarizingInt(Student::getTotalScore));
        System.out.println(stat);   // {count=7, sum=1420, min=100, average=202.857143, max=300}

        String stuNames = Stream.of(stuArr) // Stream<Student>
                .map(Student::getName)      // Stream<String>
                .collect(joining(",", "{", "}")); // 구분자, 접미사, 접두사
        System.out.println(stuNames);
        //{이자바,김자바,안자바,박자바,소자바,나자바,감자바}
    }
}


class Student implements Comparable<Student> {
    String name;
    int ban;
    int totalScore;

    Student(String name, int ban, int totalScore) {
        this.name =name;
        this.ban =ban;
        this.totalScore =totalScore;
    }

    public String toString() {
        return String.format("[%s, %d, %d]", name, ban, totalScore).toString();
    }

    String getName() { return name;}
    int getBan() { return ban;}
    int getTotalScore() { return totalScore;}

    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }
}

