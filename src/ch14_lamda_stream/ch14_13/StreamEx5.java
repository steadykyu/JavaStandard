package ch14_lamda_stream.ch14_13;

import java.util.*;
import java.util.stream.*;

class StreamEx5 {
    public static void main(String[] args) {
        String[] strArr = {
                "Inheritance", "Java", "Lambda", "stream",
                "OptionalDouble", "IntStream", "count", "sum"
        };

        Stream.of(strArr).forEach(System.out::println);
        System.out.println("==============================");
        boolean noEmptyStr = Stream.of(strArr).noneMatch(s->s.length()==0);

        Optional<String> sWord = Stream.of(strArr)
                .filter(s->s.charAt(0)=='s').findFirst();

        System.out.println("noEmptyStr="+noEmptyStr);   //true
        System.out.println("sWord="+ sWord.get());      //stream

        // Stream<String[]>을 IntStream으로 변환
        IntStream intStream1 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream2 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream3 = Stream.of(strArr).mapToInt(String::length);
        IntStream intStream4 = Stream.of(strArr).mapToInt(String::length);

        int count = intStream1.reduce(0, (a,b) -> a + 1);   // 반환타입이 int( 즉 T)
        int sum   = intStream2.reduce(0, (a,b) -> a + b);

        OptionalInt max = intStream3.reduce(Integer::max);  // 반환타입이 OptionalInt
        OptionalInt min = intStream4.reduce(Integer::min);  // (Integer a, Integer b) -> Integer.max(a,b);, 재정의된 min

        System.out.println("count="+count);     // 8
        System.out.println("sum="+sum);         // 58
        System.out.println("max="+ max.getAsInt()); // 14
        System.out.println("min="+ min.getAsInt()); // 3
    }
}

