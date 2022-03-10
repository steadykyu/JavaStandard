package ch14_lamda_stream.ch14_8;

import java.util.*;
import java.util.stream.*;

class StreamEx1 {
    public static void main(String[] args) {
        Stream<Student> studentStream = Stream.of(
                new Student("이자바", 3, 300),
                new Student("김자바", 1, 200),
                new Student("안자바", 2, 100),
                new Student("박자바", 2, 150),
                new Student("소자바", 1, 200),
                new Student("나자바", 3, 290),
                new Student("감자바", 3, 180)
        );

        studentStream.sorted(Comparator.comparing(Student::getBan) // 반별 정렬(Comparator인터페이스의 default 값으로 정해진 오름차순)
                        .thenComparing(Comparator.naturalOrder()))    // 기본 정렬(우리가 설정한 totalScore별 내림차순)
                .forEach(System.out::println);
    }
}
// 요소에 Comparable interface를 구현해 두어야 한다.
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

    String getName()     { return name;}
    int getBan()         { return ban;}
    int getTotalScore()  { return totalScore;}

    // 총점 내림차순을 기본 정렬로 한다.( 추상메서드 compareTo를 구현 )
    public int compareTo(Student s) {
        return s.totalScore - this.totalScore;
    }
}

