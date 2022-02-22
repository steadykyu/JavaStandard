package ch6_object2;
class A {
    public void methodA(B b){
        b.methodB();
    }
}
class B{
    public void methodB(){
        System.out.println("methodB()");
    }
}
public class Ch_7_27_InterfaceTest {
    public static void main(String[] args) {
        A a = new A();
        a.methodA(new B());
    }
}
// 클래스끼리 직접적인 관계로 호출하는 경우
// 의존적이므로, B가 B클래스가 변경되거나, methodB가 변경되면 또 수정해 주어야한다.
