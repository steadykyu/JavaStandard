package ch15_IO.example;

import java.io.*;

public class FileHead {
    public static void main(String[] args) {
        try {
            int lineNum = Integer.parseInt(args[0]);
            String fileName = args[1];
            
            File f = new File(fileName);                // Reader로 읽기전에 File로 해야, File에서만 사용할수 있는 메서드들을 활용 할 수 있다.

            if (f.exists() && !f.isDirectory()) {       // File 클래스의 메서드들

                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line = "";
                int i = 1;
                while((line = br.readLine())!=null && i <= lineNum){
                        System.out.println(i + ":" + line);
                        i++;
                    }
                }else {
                        throw new FileNotFoundException(fileName + "은/는 디렉토리이거나, 존재하지 않는 파일입니다.");
                 }
        }catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
        }catch (Exception e) {
                    System.out.println("USAGE: java FileHead 10 FILENAME");
        }
    }
}
//    cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO\example
//    javac FileHead.java -encoding UTF-8
//    cd..
//    cd..
//    java ch15_IO.example.FileHead 10 15_1Ex.txt


//// -----------------------------------------나의 답--------------------------------
//public class FileHead {
//    public static void main(String[] args) {
//        int line = Integer.parseInt(args[0]);
//        String fileName = args[1];
//
//        try {
//            FileReader fr = new FileReader(fileName);     //이렇게 하면 File이 안존재 할때는 처리가 불가능함.
//            try {
//                BufferedReader br = new BufferedReader(fr);
//                for(int i = 0; i < line; i++) {
//                    String data = br.readLine();
//                    System.out.println(data);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//}
//    cd C:\Users\kimkyuha\Desktop\javastandard\src\ch15_IO\example
//    javac FileHead.java -encoding UTF-8
//    cd..
//    cd..
//    java ch15_IO.example.FileHead 10 15_1Ex.txt