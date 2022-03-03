package ch10_date_Time_formating;

import java.util.*;
        import java.text.*;

class Ch_18MessageFormatEx1 {
    public static void main(String[] args) {
        String msg = "Name: {0} \nTel: {0} \nAge:{2} \nBirthday:{3}";   // 인덱스 번호에 알맞은 결과가 출력된다.

        Object[] arguments = {
                "이자바","02-123-1234", "27", "07-09"
        };

        String result = MessageFormat.format(msg, arguments);
        System.out.println(result);
    }
}
