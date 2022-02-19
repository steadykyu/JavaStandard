package ch4_from_if_to_dowhile;

public class Ch4Ex3 {
    public static void main(String[] args) {
        int sum1 = 0;
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= i ; j++){
                sum1 += j;
            }
        }

//        for(int i=1; i <=10; i++) {
//            sum += i;
//            totalSum += sum;
//        totalsum으로 더하는구나. 오우;

            System.out.println(sum1);
    }
}
