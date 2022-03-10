package ch14_lamda_stream.ch14_12;

import java.util.*;
import java.util.stream.*;

class OptionalEx1 {
    public static void main(String[] args) {
        Optional<String>  optStr = Optional.of("abcde");
        Optional<Integer> optInt = optStr.map(String::length);
        System.out.println("optStr="+optStr.get());
        System.out.println("optInt="+optInt.get());

        int result1 = Optional.of("123")
                .filter(x->x.length() >0)
                .map(Integer::parseInt).get();

        int result2 = Optional.of("")       // null로 인식하여 -1로 대체함.
                .filter(x->x.length() >0)
                .map(Integer::parseInt).orElse(-1);

        System.out.println("result1="+result1); // 123
        System.out.println("result2="+result2); // -1

        Optional.of("456").map(Integer::parseInt)
                .ifPresent(x->System.out.printf("result3=%d%n",x)); //456

        OptionalInt optInt1  = OptionalInt.of(0);   // 0을 저장
        OptionalInt optInt2  = OptionalInt.empty(); // 빈 객체를 생성(기본형이기때문에 empty()를 통해 빈객체를 만든다.)

        System.out.println(optInt1.isPresent());   // true
        System.out.println(optInt2.isPresent());   // false

        System.out.println(optInt1.getAsInt());   // 0
//		System.out.println(optInt2.getAsInt());   // NoSuchElementException
        System.out.println("optInt1 ="+optInt1);    // OptionalInt[0]
        System.out.println("optInt2="+optInt2);     // OptionalInt.empty
        System.out.println("optInt1.equals(optInt2)?"+optInt1.equals(optInt2)); //false

        Optional<String> opt  = Optional.ofNullable(null); // null을 저장
        Optional<String> opt2 = Optional.empty();          // 빈 객체를 생성
        System.out.println("opt ="+opt);        //Optional.empty
        System.out.println("opt2="+opt2);       //Optional.empty
        System.out.println("opt.equals(opt2)?"+opt.equals(opt2)); // true

        int result3 = optStrToInt(Optional.of("123"), 0);
        int result4 = optStrToInt(Optional.of(""), 0);

        System.out.println("result3="+result3);     //123
        System.out.println("result4="+result4);     // 0 예외의 defaultValue값
    }

    static int optStrToInt(Optional<String> optStr, int defaultValue) {
        try {
            return optStr.map(Integer::parseInt).get();
        } catch (Exception e){
            return defaultValue;
        }
    }
}
