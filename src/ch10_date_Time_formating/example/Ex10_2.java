package ch10_date_Time_formating.example;

import java.util.*;
        import java.text.*;
class Ex10_2 {
    static int paycheckCount(Calendar from, Calendar to) {
        if (from ==null || to == null) return 0;
        if(from.equals(to) && from.get(Calendar.DATE) == 21) return 1;

        int fromYear = from.get(Calendar.YEAR);
        int fromMon = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMon = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);

        int mondiff = (toYear*12 + toMon) - (fromYear*12 + fromMon);
        if(mondiff <= 0) return 0;
        //두번 있는 경우( 한번은 월에서 더해짐)
        if(fromDay <= 21 && toDay >= 21) mondiff++;
        //두번 빼야하는 경우
        if(fromDay > 21 && toDay < 21) mondiff--;

        return mondiff;//
    }
    static void printResult(Calendar from, Calendar to) {
        Date fromDate = from.getTime();
        Date toDate = to.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print(sdf.format(fromDate)+" ~ "
                        +sdf.format(toDate)+":");
        System.out.println(paycheckCount(from, to));
    }
    public static void main(String[] args) {
        Calendar fromCal = Calendar.getInstance();
        Calendar toCal = Calendar.getInstance();
        fromCal.set(2010,1,20);
        toCal.set(2010,2,1);
        printResult(fromCal, toCal);
        fromCal.set(2010,0,21);
        toCal.set(2010,0,21);
        printResult(fromCal, toCal);
        fromCal.set(2010,0,1);
        toCal.set(2010,2,1);
        printResult(fromCal, toCal);
        fromCal.set(2010,0,1);
        toCal.set(2010,2,23);
        printResult(fromCal, toCal);
        fromCal.set(2010,0,23);
        toCal.set(2010,2,21);
        printResult(fromCal, toCal);
        fromCal.set(2011,0,22);
        toCal.set(2010,2,21);
        printResult(fromCal, toCal);
    }
}
