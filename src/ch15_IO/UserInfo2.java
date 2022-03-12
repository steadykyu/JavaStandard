package ch15_IO;

import java.io.*;

class SuperUserInfo {
    String name;
    String password;

    SuperUserInfo() {
        this("Unknown","1111");
    }

    SuperUserInfo(String name, String password) {
        this.name = name;
        this.password = password;
    }
} // class SuperUserInfo

public class UserInfo2 extends SuperUserInfo implements java.io.Serializable {
    int age;

    public UserInfo2() {
        this("Unknown", "1111", 0);
    }

    public UserInfo2(String name, String password, int age) {
        super(name, password);
        this.age = age;
    }

    public String toString() {
        return "("+ name + "," + password + "," + age + ")";
    }

    private void writeObject(ObjectOutputStream out)
            throws IOException {
//        out.writeUTF(name);       //객체의 직렬화가 진행될때 name은 직렬화 되지 않도록 설정해봄
        out.writeUTF(password);             // 직접 직렬화
        out.defaultWriteObject();           // 자동 직렬화
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//        name = in.readUTF();
        password = in.readUTF();            // 직접 직렬화
        in.defaultReadObject();             // 자동 직렬화

    }
} // class UserInfo2

