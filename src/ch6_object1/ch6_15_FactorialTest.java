package ch6_object1;

public class ch6_15_FactorialTest {
    static int factorial(int n){
        int result = 0;
        if(n <= 0 || n > 12) return -1;         // 매개변수의 유효성 검사
        if(n==1) return 1;

        return n*factorial(n-1);
    }
    public static void main(String[] args) {
        int result = factorial(4);
        System.out.println(result);
    }

}
