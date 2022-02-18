package ch3_operator.Ex;

public class Ex3_7 {
        public static void main(String[] args) {
            int fahrenheit = 100;
            float celcius = (int)(5.0/9.0 * 1000 + 1)/(int)1/1000.0f * (fahrenheit-32);
            //(int)((5/9f * (fahrenheit - 32))*100 + 0.5) / 100f
            System.out.println("Fahrenheit:"+fahrenheit);
            System.out.println("Celcius:"+celcius);
        }
}
