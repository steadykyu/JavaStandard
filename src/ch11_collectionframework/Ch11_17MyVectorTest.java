package ch11_collectionframework;

import java.util.*;

class Ch11_17MyVectorTest {
    public static void main(String args[]) {
        Ch11_16MyVector2 v = new Ch11_16MyVector2();
        v.add("0");
        v.add("1");
        v.add("2");
        v.add("3");
        v.add("4");

        System.out.println("삭제 전 : " + v);
        Iterator it = v.iterator();
        it.next();
        it.remove();
        it.next();
        it.remove();

        System.out.println("삭제 후 : " + v);
    }
}

