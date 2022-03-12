package ch15_IO.example;

import java.io.File;

public class DirectoryInfoTest {
    static int totalFiles = 0;
    static int totalDirs = 0;
    static int totalSize = 0;
    public static void main(String[] args) {

        if(args.length != 1) {
            System.out.println("USAGE : java DirectoryInfoTest DIRECTORY");
            System.exit(0);
        }
        File dir = new File(args[0]);
        if(!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다.");
            System.exit(0);
        }
        countFiles(dir);
        System.out.println();
        System.out.println("총" + totalFiles + "개의 파일");
        System.out.println("총" + totalDirs + "개의 디렉토리");
        System.out.println("총" + totalSize + "크기 bytes");
    } // main
    public static void countFiles(File dir) {
        File[] list = dir.listFiles();      //File 형태의 배열로 가져온다.

        for(int i = 0; i < list.length; i++){
            if(list[i].isDirectory()){
                totalDirs++;
                countFiles(list[i]);
            }else{
                totalFiles++;
                totalSize += list[i].length();      // 글자 하나하나를 읽어 바이트수를 얻을 수있다.
            }
        }

    } // countFiles
}
//cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO\example
//javac DirectoryInfoTest.java -encoding UTF-8
//cd..
//cd..
// java ch15_IO.example.DirectoryInfoTest ch15_IO
// java ch15_IO.example.DirectoryInfoTest .
// 컴파일 할때의 현재 디렉토리만 가능한데.. 이거 IDE환경이라 조금 복잡하다..
