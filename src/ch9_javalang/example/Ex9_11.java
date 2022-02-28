package ch9_javalang.example;

import java.util.Scanner;

public class Ex9_11 {
    public static void main(String[] args) {

        while(true) {

            System.out.println("시작 단과 끝 단, 두개의 정수를 입력해주세요.");
            System.out.print("USAGE : GugudanTest ");
            Scanner scan = new Scanner(System.in);
            String input1 = scan.next();
            String input2 = scan.next();

            int startdan = Integer.parseInt(input1);
            int finishdan = Integer.parseInt(input2);

            if ((startdan < 2 || startdan > 9) || (finishdan < 2 || finishdan > 9)){
                System.out.println("2와 9사이의 값을 입력해야합니다.");
                continue;
            } else{
                for(int dan = startdan; dan <= finishdan ; dan++) {

                    for (int num = 1; num <= 9; num++) {
                        System.out.println(dan + "*" + num + " = " + dan * num);
                    }
                    System.out.println("============");
                }
            }
            break;
        }
    }
}
