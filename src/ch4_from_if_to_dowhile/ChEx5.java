package ch4_from_if_to_dowhile;

public class ChEx5 {
    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        while(i<=10){
            j = 0;
            while(j<=i){
                System.out.print("*");
                j++;
            }

            System.out.println();
            i++;
        }
    }
}
