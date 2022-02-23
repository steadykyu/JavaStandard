package ch6_object2;

class A2{
    void Play(I i){
        i.play();
    }
}
interface I {
    public abstract void play();
}

class B2 implements I{
    public void play(){
        System.out.println("play in the B");
    }
}

class C2 implements I{
    public void play(){
        System.out.println("play in the C");
    }
}
public class Ch7_28_InterfaceTest2 {
    public static void main(String[] args) {
        A2 a = new A2();
        // 인스턴스를 생성하고 매개변수를 통해 동적으로 인스턴스를 제공하는 방법이다.
        a.Play(new B2());
        a.Play(new C2());
    }
}
// A2의 코드는 변화시키지 않아도, B2와 C2의 메서드를 적용시킬 수있다.

