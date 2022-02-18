package ch3_operator.Ex;

public class Ex3_9 {
    public static void main(String[] args) {
        char ch = '0';
        boolean b = (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch >= '0' && ch <= '9';
        System.out.println(b);
    }
}
