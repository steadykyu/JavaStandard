package ch4_from_if_to_dowhile;

public class Ch4Ex10 {
    public static void main(String[] args) {
        int num = 12345;
        int sum = 0;

        for(int i = 0; i < 5; i++){
            int one_num = num % 10;
            sum += one_num;
            // sum += num % 10;
            num = num/10;
        }
        System.out.println(sum);
    }
}
