package ch15_IO;

import java.io.*;

class FileEx6 {
    static int found = 0;

    public static void main(String args[]) {
        if(args.length != 2) {
            System.out.println("USAGE : java FileEx6 DIRECTORY KEYWORD");
            System.exit(0);
        }

        File dir = new File(args[0]);
        String keyword = args[1];

        if(!dir.exists() || !dir.isDirectory()) {
            System.out.println("유효하지 않은 디렉토리입니다.");
            System.exit(0);
        }

        try {
            findInFiles(dir, keyword);
        } catch(IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("총 " + found + "개의 라인에서 '" + keyword + "'을/를 발견하였습니다. ");
    } // main

    public static void findInFiles(File dir, String keyword) throws IOException
    {
        File[] files = dir.listFiles();

        for(int i=0; i < files.length; i++) {
            if(files[i].isDirectory()) {
                findInFiles(files[i], keyword);
            } else {
                String filename  = files[i].getName();
                String extension = filename.substring(filename.lastIndexOf(".")+1); //확장자만 빼옴.
                extension = "," + extension + "," ;             // 확장자들을 이와같은 형식으로 모음 ,java, 또는 ,txt, 등등

                if(",java,txt,bak,".indexOf(extension) == -1) continue; //확장자가 옆 기준에 속하지않으면 다시 for문 처음으로

                filename = dir.getAbsolutePath() + File.separator + filename;   //경로 + 이름

                FileReader     fr = new FileReader(files[i]);   // 해당 파일을 읽어온다.
                BufferedReader br = new BufferedReader(fr);     // 버퍼로 만든다.

                String data = "";
                int lineNum = 0;

                while((data=br.readLine())!=null) {
                    lineNum++;
                    if(data.indexOf(keyword)!=-1) {
                        found++;
                        System.out.println("["+filename+ "("+lineNum+")" + "]" + data);
                    }
                } // while

                br.close();
            }
        } // for
    } // findInFiles
} // class

