package ch9_javalang;

import static java.lang.Math.negateExact;
import static java.lang.System.out;

public class Ch9_21MathEx2 {
    public static void main(String[] args) {
        int i = Integer.MIN_VALUE; // 범위 최소값 즉 -2147483648

        out.println("i = "+i);
        out.println("-i = "+(-i));      // 값이 안바뀜.

        out.printf("negateExact(%d) = %d%n", 10 , negateExact(10));
        out.printf("negateExact(%d) = %d%n", -10 , negateExact(-10));

        try{
            out.printf("negateExact(%d) = %d%n", i , negateExact(i));       // 예외 발생(범위가 안맞음)
        } catch(ArithmeticException e){
            // i를 long타입으로 형변환 후 negateExact(i) 호출
            out.printf("negateExact(%d) = %d%n", (long)i , negateExact((long)i));
            // i는 스택오버플로우 때문에 음수적용을 못하지만
            // negateExact((long)i)는 오버플로우를 알리고, 수정도 가능.
        }
    }
}
