package ch15_IO;

import java.io.*;

class DataInputStreamEx1 {
    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream("sample.dat");
            DataInputStream dis = new DataInputStream(fis);

            // sample.dat 만들때 했던 순서대로 읽어서 출력해야한다.
            System.out.println(dis.readInt());
            System.out.println(dis.readFloat());
            System.out.println(dis.readBoolean());
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // main
}

