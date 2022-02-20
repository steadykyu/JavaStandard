package ch5_array;

public class ch5Ex5 {
    public static void main(String[] args) {
        int[] ballArr = {1,2,3,4,5,6,7,8,9};
        int[] ball3 = new int[3];
// ballArr . 배열 의 임의의 요소를 골라서 위치를 바꾼다
        for(int i=0; i< ballArr.length;i++) {
            int j = (int)(Math.random() * ballArr.length);
            int tmp = 0;
            tmp = ballArr[j];
            ball3[i] = ballArr[j];
            ballArr[j] = ballArr[i];
            ballArr[i] = tmp;
            if (i==2) break;
        }
// ballArr 3 ball3 . 배열 의 앞에서 개의 수를 배열 로 복사한다
        /* (2) */
        for(int i=0;i<ball3.length;i++) {
            System.out.print(ball3[i]);
        }
    }
}
// 아예 ballArr를 다 정렬시켜놓고 뺀다음에 복사시키기.로 하면 더 간단하다.

//for(int i=0; i< ballArr.length;i++) {
//        int j = (int)(Math.random() * ballArr.length);
//        int tmp = 0;
//        tmp = ballArr[i];
//        ballArr[i] = ballArr[j];
//        ballArr[j] = tmp;
//        }
//// ballArr 3 ball3 . 배열 의 앞에서 개의 수를 배열 로 복사한다
//        System.arraycopy(ballArr,0, ball3,0,3);

