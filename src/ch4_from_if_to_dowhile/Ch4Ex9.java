package ch4_from_if_to_dowhile;

import static java.lang.Character.getNumericValue;

public class Ch4Ex9 {
    public static void main(String[] args) {
        String str = "12345";
        int sum = 0;

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
//            char값 그대로 -> int
            int num =  Character.getNumericValue(ch);
            sum += num;
        }
        System.out.println(sum);
    }
}

// 0의 유니코드 값을 빼버렸다. char 특성을 이용함.

//for(int i=0; i < str.length(); i++) {
//        sum += str.charAt(i) - '0';
//        }