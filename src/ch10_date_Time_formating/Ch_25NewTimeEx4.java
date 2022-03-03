package ch10_date_Time_formating;

import java.time.*;

import java.time.temporal.*;

    class Ch_25NewTimeEx4 {
        public static void main(String[] args) {
            LocalDate date1 = LocalDate.of(2014,  1,  1);
            LocalDate date2 = LocalDate.of(2015, 12, 31);

            Period pe = Period.between(date1, date2);

            System.out.println("date1="+date1);                                 //2014-01-01
            System.out.println("date2="+date2);                                 //2015-12-31
            System.out.println("pe="+pe);                                       //P1Y11M30D

            System.out.println("YEAR=" +pe.get(ChronoUnit.YEARS));               //YEAR=1
            System.out.println("MONTH="+pe.get(ChronoUnit.MONTHS));              //MONTH=11
            System.out.println("DAY="  +pe.get(ChronoUnit.DAYS));                //DAY=30

            LocalTime time1 = LocalTime.of( 0, 0, 0);
            LocalTime time2 = LocalTime.of(12,34,56); // 12시간 23분 56초

            Duration du = Duration.between(time1, time2);                        //du=PT12H34M56S

            System.out.println("time1="+time1);                            //time1=00:00
            System.out.println("time2="+time2);                            //time2=12:34:56
            System.out.println("du="+du);                               //du=PT12H34M56S

            LocalTime tmpTime = LocalTime.of(0,0).plusSeconds(du.getSeconds());

            System.out.println("HOUR="  +tmpTime.getHour());               // HOUR=12
            System.out.println("MINUTE="+tmpTime.getMinute());             // MINUTE=34
            System.out.println("SECOND="+tmpTime.getSecond());             // SECOND=56
            System.out.println("NANO="  +tmpTime.getNano());               // NANO=0
        }
    }

