package ch9_javalang;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ch9_37ScannerEx3 {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\Users\\kimkyuha\\Desktop\\javastandard\\src\\ch9_javalang\\data3.txt"));
        int cnt = 0;
        int totalSum = 0;


        while (sc.hasNextLine()){
            String line = sc.nextLine().trim();    //넣을게 없어서 예외발생.
            Scanner sc2 = new Scanner(line).useDelimiter(","); //구분자로 나눠서 읽어오기.

            int sum =0;

            while(sc2.hasNextInt()) {
                    sum += sc2.nextInt();
                }
                System.out.println(line + ", sum = "+ sum);
                totalSum += sum;
                cnt++;
        }
        sc.close();

        System.out.println("Line: " + cnt + ", Total: "+ totalSum);
    }
}
