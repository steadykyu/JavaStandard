package ch10_date_Time_formating.example;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Ex10_3 {
    public static void main(String[] args) {
        String strNum = "123,456,789.5";
        DecimalFormat df = new DecimalFormat("#,###.##");
        DecimalFormat df2 = new DecimalFormat("#,####");
        try {
            Number num = df.parse(strNum);
            double d = num.doubleValue();

            System.out.println("data : "+ strNum);
            System.out.println(num);
            System.out.println("반올림 : "+ Math.round(d));
            System.out.println("만단위 : " +df2.format(d));
            // 이거 알아서 반올림되는데?
        } catch (ParseException e) {
            e.printStackTrace();
        }

        double d2 = 1.6;
        System.out.println(d2);
        Math.round(d2);
        System.out.println(d2);

    }


}
