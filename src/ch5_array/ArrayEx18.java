package ch5_array;

public class ArrayEx18 {
    public static void main(String[] args) {
        int[][] score = {
                {100, 100, 100},
                {20, 20, 20},
                {30, 30, 30},
                {40, 40, 40}
        };
        int sum = 0;

        for( int[] tmp : score){
            for(int i : tmp){
                sum += i;
            }
        }
    }
}
//2차원 배열에서 모든 요소의 합구하기
//향상된 for문으로 2차원 배열안의 1차원배열을 꺼내 활용하는 모습을 눈여겨보자.