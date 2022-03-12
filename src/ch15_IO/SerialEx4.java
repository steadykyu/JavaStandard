package ch15_IO;

import java.io.*;
import java.util.ArrayList;

public class SerialEx4 {
    public static void main(String[] args) {
        try {
            String fileName = "UserInfo2.ser";
            FileInputStream     fis = new FileInputStream(fileName);
            BufferedInputStream bis = new BufferedInputStream(fis);

            ObjectInputStream in = new ObjectInputStream(bis);

            // 객체를 읽을 때는 출력한 순서와 일치해야한다.
            UserInfo2 u1 = (UserInfo2)in.readObject();
            UserInfo2 u2 = (UserInfo2)in.readObject();
            ArrayList list = (ArrayList)in.readObject();

            System.out.println(u1);
            System.out.println(u2);
            System.out.println(list);
            in.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    } // main
} // class
// UserInfo2 의 writeObject, readObject 구현체 안의 name에 주석을 처리해보았다.
// 객체의 직렬화 과정에서 name 값은 포함되지 않았고, 역직렬화 과정에서도 마찬가지다.
// writeObject, readObject 둘중하나만 name에 주석을 달면 오류가 발생한다.
// 역직렬화 할때는 직렬화할때와 같은 버전의 클래스(멤버-인스턴스변수 가 동일한 클래스)를 사용해야 하기때문이다.
// 코드로 말하자면 UserInfo2를 직렬화했을때의 상태와 역직렬화할때의 상태가 같아야 하기 때문이다.
