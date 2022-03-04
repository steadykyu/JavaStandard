package ch11_collectionframework;

import java.util.*;

class Ch11_35PropertiesEx1 {
    public static void main(String[] args) {
        Properties prop = new Properties();

        // prop에 키와 값(key, value)을 저장한다.
        prop.setProperty("timeout","30");
        prop.setProperty("language","kr");
        prop.setProperty("size","10");
        prop.setProperty("capacity","10");

        // prop에 저장된 요소들을 Enumeration을 이용해서 출력한다.
        Enumeration e = prop.propertyNames();

        while(e.hasMoreElements()) {
            String element = (String)e.nextElement();
            System.out.println(element + "="+ prop.getProperty(element));
        }

        System.out.println();
        prop.setProperty("size","20");	// size의 값을 20으로 변경한다.
        System.out.println("size="       + prop.getProperty("size"));
        System.out.println("capacity="   + prop.getProperty("capacity", "20"));     // 있어서 defaultValue가 안나옴
        System.out.println("loadfactor=" + prop.getProperty("loadfactor", "0.75")); // 없어서 defaultValue가 나옴.

        System.out.println(prop);	// prop에 저장된 요소들을 출력한다.
        prop.list(System.out);      // prop에 저장된 요소들을 화면(System.out)에 출력한다.
    }
}

