package ch10_date_Time_formating.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Ex10_6 {
    public static void main(String[] args) {
        LocalDate birthDate = LocalDate.of(1995, 2, 4); // 1999년 12월 31일
        LocalDate today = LocalDate.now();

        long days = birthDate.until(today, ChronoUnit.DAYS);

        System.out.println("birth day="+birthDate);
        System.out.println("today ="+today);
        System.out.println(days +" days");

    }
}
// 날짜에서 값비교를 할껀데 , "일" 을 기준으로 하겠다 해서 ChronoUnit.DAYS를 사용한다.