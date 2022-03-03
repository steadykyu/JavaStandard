package ch10_date_Time_formating;


import java.util.Calendar;

class Ch10_5CalendarEx5 {
    public static void main(String[] args) {
        Calendar date = Calendar.getInstance();
        date.set(2015, 0, 31);	// 2015년 1월 31일
        System.out.println(toString(date));
        date.roll(Calendar.MONTH,1);
        System.out.println(toString(date));
        // roll이 다른 필드(여기서는 일 ) 에 영향을 끼친 경우
    }

    public static String toString(Calendar date) {
        return date.get(Calendar.YEAR)+"년 "+ (date.get(Calendar.MONTH)+1)
                +"월 " + date.get(Calendar.DATE) + "일";
    }
}

