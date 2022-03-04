package ch11_collectionframework;

import java.util.*;
        import java.io.*;

class Ch11_37PropertiesEx3 {
    public static void main(String[] args) {
        Properties prop = new Properties();

        prop.setProperty("timeout","30");
        prop.setProperty("language","한글");
        prop.setProperty("size","10");
        prop.setProperty("capacity","10");

        try {
            prop.store(new FileOutputStream("output.txt"), "Properties Example");
            prop.storeToXML(new FileOutputStream("output.xml"),  "Properties Example");
        } catch(IOException e) {
            e.printStackTrace();
        }
    } // main의 끝
}
// 외부 javastandard에 만들어진것을 확인 할수 있다.