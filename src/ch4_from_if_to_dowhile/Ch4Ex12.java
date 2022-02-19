package ch4_from_if_to_dowhile;

public class Ch4Ex12 {
    public static void main(String[] args) {
        for (int i = 2; i <= 9; i++){
            for(int j = 1; j<=9; j++){
                if(j==4) break;
                System.out.printf("%d*%d=%d",i, j, i*j);
                System.out.println();
            }
            System.out.println();
        }
    }
}
