package ch15_IO.example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class HexaViewer {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("USAGE: java HexaViewer FILENAME");
            System.exit(0);
        }

        String inputFile = args[0];
        try {
            FileInputStream input = new FileInputStream(inputFile);
            PrintStream output = new PrintStream(System.out);       //콘솔창에 출력하기로 설정.

            int data = 0;
            int i = 0;

            while ((data = input.read()) != -1) {
                output.printf("%02X", data);
                if (++i % 16 == 0) {
                    output.println();
                }
            }
            input.close();
            output.close();
        }catch(IOException e){
            e.printStackTrace();;
        }
    }
}
//    cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO\example
//    javac HexaViewer.java -encoding UTF-8
//    cd..
//    cd..
//    java ch15_IO.example.HexaViewer 15_2.txt

// 이거 입력값을 어떻게 설정해야하지? 이진값을 넣으라고 하는데