package ch11_collectionframework;

import java.util.*;

class Ch11_38PropertiesEx4{
    public static void main(String[] args) {
        Properties sysProp = System.getProperties();
        System.out.println("java.version :" + sysProp.getProperty("java.version"));
        System.out.println("=========================================================================");
        System.out.println("user.languag :" + sysProp.getProperty("user.language"));
        System.out.println("=========================================================================");
        sysProp.list(System.out);
    }
}
