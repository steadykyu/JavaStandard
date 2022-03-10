package ch14_lamda_stream.example;

import java.util.Arrays;
import java.util.stream.Stream;

public class Ex14_5 {
    public static void main(String[] args) {
        String[] strArr = { "aaa","bb","c", "dddd" };
        Stream<String> stream = Arrays.stream(strArr);
        int total = stream.mapToInt(s->s.length()).sum();
        //int sum = strStream.mapToInt(String::length).sum();
        System.out.println("sum = "+ total);
    }
}
