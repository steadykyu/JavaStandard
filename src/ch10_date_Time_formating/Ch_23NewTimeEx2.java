package ch10_date_Time_formating;

import java.time.*;
        import java.time.temporal.*;

class Ch_23NewTimeEx2 {
    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2015, 12, 31); // 2015년 12월 31일
        LocalTime time = LocalTime.of(12,34,56);     // 12시 23분 56초

        // 2015년 12월 31일 12시 23분 56초
        LocalDateTime dt  = LocalDateTime.of(date, time);

        ZoneId zid = ZoneId.of("Asia/Seoul");
        ZonedDateTime zdt = dt.atZone(zid);
//		String strZid = zdt.getZone().getId();

        ZonedDateTime seoulTime = ZonedDateTime.now();
        ZoneId nyId = ZoneId.of("America/New_York");
        ZonedDateTime nyTime = ZonedDateTime.now().withZoneSameInstant(nyId);

        // ZonedDatetime -> OffsetDateTime
        OffsetDateTime odt = zdt.toOffsetDateTime();

        System.out.println(dt);         // 2015-12-31T12:34:56
        System.out.println(zid);        // Asia/Seoul
        System.out.println(zdt);        // 2015-12-31T12:34:56+09:00[Asia/Seoul]
        System.out.println(seoulTime);  // 2022-03-03T04:35:47.490800600+09:00[Asia/Seoul]
        System.out.println(nyTime);     // 2022-03-02T14:35:47.491800400-05:00[America/New_York]
        System.out.println(odt);        // 2015-12-31T12:34:56+09:00
    }
}
