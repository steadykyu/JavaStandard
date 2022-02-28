package ch9_javalang;

import java.util.Scanner;

public class Ch9_35ScannerEx1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] arrArr = null;

        while(true){
            String input = s.nextLine();

            input = input.trim();
            arrArr = input.split(" +"); //하나부터 여러 공백을 구분자로 문자열을 자른다.

            String command = arrArr[0].trim();

            if("".equals(command)) continue;

            command = command.toLowerCase();

            if(command.equals("q")){
                System.exit(0);
            } else {
                for(int i =0; i < arrArr.length; i++)
                    System.out.println(arrArr[i]);
            }
        }
    }
}
