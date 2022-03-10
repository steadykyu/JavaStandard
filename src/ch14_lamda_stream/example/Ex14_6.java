package ch14_lamda_stream.example;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public class Ex14_6 {
    public static void main(String[] args) {
        String[] strArr = { "aaa","bb","c", "dddd" };
        OptionalInt max = Arrays.stream(strArr).mapToInt(s->s.length()).max();
        System.out.println(max.getAsInt());
    }
}
/** 해답지
 * String[] strArr = { "aaa","bb","c", "dddd" };
 * Stream<String> strStream = Stream.of(strArr);
 * strStream.map(String::length) // strStream.map(s-> s.length())
 * .sorted(Comparator.reverseOrder()) // 문자열 길이로 역순 정렬
 * .limit(1).forEach(System.out::println); // 제일 긴 문자열의 길이 출력
 */
