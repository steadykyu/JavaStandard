package ch10_date_Time_formating.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Ex10_7 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2016,12,27);

        System.out.println(date.with(TemporalAdjusters.dayOfWeekInMonth(4,DayOfWeek.TUESDAY)));

    }
}
