package ch15_IO;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

class FileConversion {
    public static void main(String args[]) {
        try {
//            FileReader fr= new FileReader(args[0]);
//            FileWriter fw= new FileWriter(args[1]);
            InputStreamReader fr = new InputStreamReader(new FileInputStream(args[0]),UTF_8);
            OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(args[1]), UTF_8);

            int data =0;
            while((data=fr.read())!=-1) {
                if(data!='\t' && data!='\n' && data!=' ' && data !='\r')
                    fw.write(data);
            }

            fr.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // main
}
// cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO
// javac FileConversion.java -encoding UTF-8
// cd..
//bak은 한글이 나오는데 : java ch15_IO.FileConversion input.txt convert.bak
//txt는 안나오네       : java ch15_IO.FileConversion input.txt convert.txt
// convert.txt

// Writer가 유니코드(uft16)를 특정 인코딩으로 변환한다고하는데 이 과정에서 글자가 깨지는 문제가 발생하는듯하다.
// 인코딩을 설정해줄 수 있는 or 자체인코딩없이 그저 출력하는 FileOutputStream을 쓰는게 나을듯.(FileCopy.java 참고)


