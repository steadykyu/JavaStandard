package ch15_IO;

import java.io.*;

class DataOutputStreamEx1 {
    public static void main(String args[]) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;

        try {
            fos = new FileOutputStream("sample.dat");
            dos = new DataOutputStream(fos);
            dos.writeInt(10);
            dos.writeFloat(20.0f);
            dos.writeBoolean(true);

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // main
}
// 프로젝트인 javastandard 폴더에 생성.
