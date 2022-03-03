package ch10_date_Time_formating.example;

    import java.time.*;
    class Ex10_8 {
        public static void main(String[] args) {
            ZonedDateTime zdt = ZonedDateTime.now();
            ZoneId nyId = ZoneId.of("America/New_York");
            ZonedDateTime zdtNY = ZonedDateTime.now().withZoneSameInstant(nyId);

            System.out.println(zdt);
            System.out.println(zdtNY);
            long sec1 = zdt.getOffset().getTotalSeconds();      //
            long sec2 = zdtNY.getOffset().getTotalSeconds();    //
            long diff = (sec1 - sec2)/3600;
            System.out.println("sec1="+sec1);
            System.out.println("sec2="+sec2);
            System.out.printf("diff=%d hrs%n",diff);
        }
    }
/**
 * ZonedDateTime의  getOffset()은 ZoneOffset을 반환한다.
 * ZoneOffset의 getTotalSeconds()을 호출하면, 날짜와 시간을 초단위로 변환한 결과를 얻을 수 있다.
 */