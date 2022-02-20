package ch5_array;

public class MultiArrEx3 {
    public static void main(String[] args) {
        int[][] m1 = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] m2 = {
                {1,2},
                {3,4},
                {5,6}
        };
        final int ROW = m1.length;
        final int COL = m2[0].length; //m2 열길이 2
        final int M2_ROW = m2.length; //m2 행길이 3

        int[][] m3 = new int[ROW][COL];

        //행렬 곱
        for(int i = 0; i < ROW; i++)
            for(int j=0; j<COL;j++)
                for(int k = 0;k<M2_ROW;k++)
                    m3[i][j] += m1[i][k] * m2[k][j];

        // 행렬 m3 출력
        for(int i =0; i< ROW; i++){
            for(int j=0;j<COL;j++){
                System.out.printf("%3d ", m3[i][j]);
            }
            System.out.println();
        }
    }
}
// 와 이거는 식 풀어 해치고 패턴 안보면 절대 못풀듯.
// 가끔씩 그림이나 패턴을 노트에 그리고 코드짜는게 훨씬 좋을 거같다.
