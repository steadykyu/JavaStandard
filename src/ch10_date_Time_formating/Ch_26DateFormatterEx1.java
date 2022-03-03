package ch10_date_Time_formating;

import java.time.*;
        import java.time.format.*;

class Ch_26DateFormatterEx1 {
    public static void main(String[] args) {
        ZonedDateTime zdateTime = ZonedDateTime.now();

        String[] patternArr = {
                "yyyy-MM-dd HH:mm:ss",
                "''yy년 MMM dd일 E요일",
                "yyyy-MM-dd HH:mm:ss.SSS Z VV",
                "yyyy-MM-dd hh:mm:ss a",
                "오늘은 올 해의 D번째 날입니다.",
                "오늘은 이 달의 d번째 날입니다.",
                "오늘은 올 해의 w번째 주입니다.",
                "오늘은 이 달의 W번째 주입니다.",
                "오늘은 이 달의 W번째 E요일입니다."
        };

        for(String p : patternArr) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(p);
            System.out.println(zdateTime.format(formatter));
        }
    } // main의 끝

//    2022-03-03 05:15:32
//    '22년 3월 03일 목요일
//    2022-03-03 05:15:32.457 +0900 Asia/Seoul
//    2022-03-03 05:15:32 오전
//    오늘은 올 해의 62번째 날입니다.
//    오늘은 이 달의 3번째 날입니다.
//    오늘은 올 해의 10번째 주입니다.
//    오늘은 이 달의 1번째 주입니다.
//    오늘은 이 달의 1번째 목요일입니다.
}

