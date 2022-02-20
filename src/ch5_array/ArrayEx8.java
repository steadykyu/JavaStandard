package ch5_array;

public class ArrayEx8 {
    public static void main(String[] args) {
        int[] ball = new int[45];

        for (int i = 0; i < ball.length; i++)
            ball[i] = i + 1;  //1 ~ 45값 저장


        int temp = 0;
        int j = 0;

        for (int i = 0; i < 6; i++) {
                j = (int)(Math.random() *45);
                temp = ball[i];
                ball[i] = ball[j];
                ball[j] = temp;
        }

        for(int i = 0; i < 6; i++)
            System.out.printf("ball[%d]=%d%n", i, ball[i]);
    }
}
// 앞 6자리가 당첨번호인 로또번호 만들기.
// temp를 통해 배열 요소간 이동하는 모습을 눈여겨두자.