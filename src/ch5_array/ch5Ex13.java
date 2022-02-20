package ch5_array;

import java.util.Scanner;

public class ch5Ex13 {
    public static void main(String args[]) {
        String[] words = { "television", "computer", "mouse", "phone" };
        Scanner scanner = new Scanner(System.in);
        for(int i=0;i<words.length;i++) {
            char[] question = words[i].toCharArray(); // String char[] 을 로 변환
            int n = (int)(Math.random() * question.length -1);
            char tmp = ' ';
            tmp = question[i];
            question[i] = question[n];
            question[n] = tmp;

            System.out.printf("Q%d의 정답을 입력하세요. %s .>",
                    i+1, new String(question));
            String answer = scanner.nextLine();
// trim() answer , equals word[i] 으로 의 좌우 공백을 제거한 후 로 와 비교
            if(words[i].equals(answer.trim()))
                System.out.printf("맞았습니다.%n%n");
            else
            System.out.printf("틀렸습니다.%n%n");
        }
    } // main의 끝

}
