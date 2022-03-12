package ch15_IO.example;

import java.io.*;
import java.util.Vector;

class FileMergeTest {
    public static void main(String[] args) {
        // 예외의 경우 처리
        if(args.length < 2) { // 입력값이 2보다 작으면 메세지를 출력하고 종료한다.
            System.out.println("USAGE: java FileMergeTest MERGE_FILENAME FILENAME1 FILENAME2 ...");
            System.exit(0);
        }
        // 값이 잘들어갔을때
        try {
            Vector v = new Vector();
            for(int i=1; i < args.length;i++) {
                File f = new File(args[i]);
                if(f.exists()) {
                    v.add(new FileInputStream(args[i]));    //txt 파일을 읽어들임.
                } else {
                    System.out.println(args[i]+ " - 존재하지 않는 파일입니다 .");
                    System.exit(0);
                }
            }
            // SequenceInputStream의 매개변수로 세개이상은 매개변수 에러가 나오는데, Vector로 넣으면 안뜨는 모습을 확인할수 있었다.
            SequenceInputStream input = new SequenceInputStream(v.elements());  // Enumeration안의 원소들 출력
            FileOutputStream output = new FileOutputStream(args[0]);            // 출력해줄 곳 설정 (result.txt)
            int data = 0;
            while((data = input.read())!=-1) {
                output.write(data);
            }
        } catch(IOException e) {}
    } // main
}
// cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO\example
// javac FileMergeTest.java -encoding UTF-8
//cd..
//cd..
// java ch15_IO.example.FileMergeTest result.txt 1.txt 2.txt 3.txt
// type result.txt
// java ch15_IO.example.FileMergeTest result.txt 1.txt 2.txt
// type result.txt

//-------------나의풀이
// 두개가 들어오면 두개 처리, 3개가들어오면 3개처리를 해주지 못했다.
//public class FileMergeTest {
//    public static void main(String[] args) {
//
//        String output = args[0];
//        String input1 = args[1];
//        String input2 = args[2];
//        String input3 = args[3];
//
//        try {
//            FileOutputStream fos = new FileOutputStream(output);
//
//            FileInputStream fis1 = new FileInputStream(input1);
//            FileInputStream fis2 = new FileInputStream(input2);
//            FileInputStream fis3 = new FileInputStream(input3);
//
//            SequenceInputStream sis = new SequenceInputStream(fis1,fis2);
//            SequenceInputStream sis2 = new SequenceInputStream(sis,fis3);   // 보조스트림도 InputStream의 자손이므로 들어간다!
//
//            int data = 0;
//
//            try {
//                while ((data=sis2.read()) != -1) {
//                    fos.write(data);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
//// cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO\example
//// javac FileMergeTest.java -encoding UTF-8
////cd..
////cd..
//// java ch15_IO.example.FileMergeTest result.txt 1.txt 2.txt 3.txt