package ch3_operator.Ex;

public class Ex3_10 {
    public static void main(String[] args) {
        char ch = 'B';
        char lowerCase = (ch >= 'A' && ch <= 'Z') ? (char)(ch+32) : ch;
        System.out.println("ch:"+ch);
        System.out.println("ch to lowerCase:"+lowerCase);
    }

}
