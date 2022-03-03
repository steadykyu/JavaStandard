package ch10_date_Time_formating;

import java.text.*;

class Ch10_11DecimalFormatEx2 {
    public static void main(String[] args) {
        DecimalFormat df  = new DecimalFormat("#,###.##");
        DecimalFormat df2 = new DecimalFormat("#.###E0");

        try {
            Number num = df.parse("1,234,567.89"); // 문자열값을 double로 전환
            System.out.print("1,234,567.89" + " -> ");

            double d = num.doubleValue();   // Number값을 double로 전환
            System.out.print(d + " -> ");

            System.out.println(df2.format(num));
        } catch(Exception e) {}
    } // main
}
