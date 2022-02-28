package ch9_javalang.example;

import java.util.StringTokenizer;

class Ex9_3 {
    public static void main(String[] args) {
        String fullPath = "c:\\jdk1.8\\work\\PathSeparateTest.java";
        String path = "";
        String fileName = "";

        String[] split = fullPath.split("\\\\");
        for(int i = 0; i < split.length; i++){
            if(i == split.length-1){
                fileName = split[i];
            }else if(i == split.length-2){
                path += split[i];
            }else
                path += split[i]+"\\\\";
        }

        System.out.println("fullPath:"+fullPath);
        System.out.println("path:"+path);
        System.out.println("fileName:"+fileName);
    }
}
// 해답
//int pos = fullPath.lastIndexOf("\\");
//if(pos!=-1) {
//        path = fullPath.substring(0, pos);
//        fileName = fullPath.substring(pos+1);
//        }
// 근데 이러면 응용성없으므로 내께 더 나은듯하다.