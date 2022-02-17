package ch3_operator;

public class OperatorEx2 {
    public static void main(String[] args) {
        int i = 5, j = 0;

        j = i++;
        System.out.println("i = " + i);
        System.out.println("j = " + j);

        i = 5;
        j = 0;
        j = ++i;
        System.out.println("----------------비교선----------");
        System.out.println("i = " + i);
        System.out.println("j = " + j);


    }
}
