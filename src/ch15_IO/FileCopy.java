package ch15_IO;

import java.io.*;

class FileCopy {
    public static void main(String args[]) {
        try {
            FileInputStream  fis = new FileInputStream(args[0]);
            FileOutputStream fos = new FileOutputStream(args[1]);

            int data =0;
            while((data=fis.read())!=-1) {
                fos.write(data);	 // void write(int b)
            }

            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO
//javac FileCopy.java -encoding UTF-8
// java ch15_IO.FileCopy input.txt inputcopy.bak
//java ch15_IO.FileCopy input.txt inputcopy.txt
// inputcopy.bak 로 복사되었다.
