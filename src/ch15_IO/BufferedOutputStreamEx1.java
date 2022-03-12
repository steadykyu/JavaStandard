package ch15_IO;

import java.io.*;

class BufferedOutputStreamEx1 {
    public static void main(String args[]) {
        try {
            FileOutputStream fos = new FileOutputStream("123.txt");
            // BufferedOutputStream의 버퍼 크기를 5로 한다.
            BufferedOutputStream bos = new BufferedOutputStream(fos, 5);
            // 파일 123.txt에  1 부터 9까지 출력한다.
            for(int i='1'; i <= '9'; i++) {
                bos.write(i);
            }

            //fos.close();
            bos.close();    // 안에 FileOutputStream의 close가 내장되어있음. / 버퍼에 남아있는 내용을 출력
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO
//javac BufferedOutputStreamEx1.java -encoding UTF-8
//cd..
//java ch15_IO.BufferedOutputStreamEx1
// 123.txt에 12345 값을 출력함.

// bos.close()로 수정후 컴파일후 실행해보면 123456789 출력

