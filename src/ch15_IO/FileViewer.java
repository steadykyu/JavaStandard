package ch15_IO;

import java.io.*;

class FileViewer {
    public static void main(String args[]) throws IOException{
        FileInputStream fis = new FileInputStream(args[0]);
        int data = 0;

        while((data=fis.read())!=-1) {
            char c = (char)data;
            System.out.print(c);
        }
    }
}
// cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO
// javac FileViewer.java -encoding UTF-8
// java ch15_IO.FileViewer input.txt
// 1byte씩 읽으므로, 한글 주석은 못읽은는듯