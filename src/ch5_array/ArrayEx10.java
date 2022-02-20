package ch5_array;

public class ArrayEx10 {
    public static void main(String[] args) {
        int[] numArr = new int[10];

        for(int i=0; i < numArr.length; i++ ){
            System.out.print(numArr[i] = (int)(Math.random() * 10));
        }
        System.out.println();

        for (int i=0; i < numArr.length-1; i++){
            boolean changed = false;

            for(int j=0; j < numArr.length-1-i; j++){
                if(numArr[j] > numArr[j+1]){
                    int temp = numArr[j];
                    numArr[j] = numArr[j+1];
                    numArr[j+1] = temp;
                    changed = true;
                }
            }
            if(!changed) break;

            for(int k = 0; k < numArr.length; k++){
                System.out.print(numArr[k]);
            }
            System.out.println();
        }
    }
}
// 버블정렬 알고리즘
// 총 length-1번의 경우의 수를 해야한다는 점과
// numArr.length-1-i 한번시행할때마다, 정렬해야하는 개수가 주는 모습을 코드로 잘 표현해야한다.
