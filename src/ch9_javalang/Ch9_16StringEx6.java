package ch9_javalang;

public class Ch9_16StringEx6 {
    public static void main(String[] args) {
        int iVal = 100;
        String strVal = String.valueOf(iVal);


        double dVal = 200.0;
        String strVal2 = dVal + ""; // String으로 바꾸는 1번방법

        double sum = Integer.parseInt("+"+strVal) + Double.parseDouble(strVal2);

        double sum2 = Integer.valueOf(strVal) + Double.valueOf(strVal2);

        System.out.println(String.join("",strVal,"+",strVal2,"=") + sum);
        System.out.println(strVal +"+"+strVal2+"="+sum2);
    }
}
