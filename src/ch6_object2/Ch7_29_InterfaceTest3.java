package ch6_object2;

public class Ch7_29_InterfaceTest3 {
    public static void main(String[] args) {
        A3 a = new A3();
        a.methodA();
//        인스턴스값을 직접 생성하지 않고, getInstance를 통해 인스턴스를 받는다
//        나중에 인스턴스 값을 변경하려면 getInstance만 바꾸면 된다.
    }
}

class A3{
    void methodA(){
        I3 i = InstanceManager.getInstance();
        i.methodB();
        System.out.println(i.toString());
        // i에 toString을 추상메서드로 하지는 않았지만, 모든 객체는 Object클래스에 정의된 메서드를 가지고 있으므로 사용가능하다.
    }
}
interface I3{
    public abstract void methodB();
}

class B3 implements I3 {
    public void methodB(){
        System.out.println("methodB in B class");
    }
    public String toString() {return "class B";}
}

class InstanceManager {
    public static I3 getInstance(){
        return new B3();
    }
}
//