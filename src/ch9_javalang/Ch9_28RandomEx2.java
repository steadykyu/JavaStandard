package ch9_javalang;

import java.util.Random;

public class Ch9_28RandomEx2 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] number = new int[100];
        int[] counter = new int[10];

        for(int i =0; i< number.length; i++){
            System.out.print(number[i] = rand.nextInt(10));
            //  0~9의 값을 난수로 뽑는다.0<= <10
        }
        System.out.println();

        for(int i= 0; i < number.length;i++){
            counter[number[i]]++;
        }
        for(int i=0; i < counter.length; i++){
            System.out.println(i+"의 개수 : "+ printGraph('#',counter[i])
                                                + " " + counter[i]);
        }
    }
    public static String printGraph(char ch, int value){
        char[] bar = new char[value];

        for(int i =0; i< bar.length; i++)
            bar[i] = ch;

        return new String(bar);
    }
}
