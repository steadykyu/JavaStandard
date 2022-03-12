package ch15_IO;

import java.io.*;

class RandomAccessFileEx3 {
    public static void main(String args[]) {
        int sum = 0;

        try {
            RandomAccessFile raf = new RandomAccessFile("score2.dat", "r");
            int i=4;            // 국어점수를 가져오기 위해 1 index에 위치한 , 즉 4byte 자리 포인터위치의 값을 가져왔다.

            while(true) {
                raf.seek(i);
                sum += raf.readInt();
                i+=16;
            }
        } catch (EOFException e) {
            System.out.println("sum : " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
