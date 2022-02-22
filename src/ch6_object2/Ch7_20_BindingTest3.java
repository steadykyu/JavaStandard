package ch6_object2;

public class Ch7_20_BindingTest3 {
    public static void main(String[] args) {
        Parent2 p = new Child2();
        Child2 c = new Child2();

        System.out.println("p.x = "+ p.x);
        p.method();
        System.out.println();
        System.out.println("c.x = "+ c.x);
        c.method();
        // 메서드는 참조변수에 상관이 없이 자식메서드로 오버라이딩 된다.
        // 직접 접근하는 변수는 영향이 있는 모습.
    }
}

class Parent2{
    int x = 100;

    void method(){
        System.out.println("Parent Method");
    }
}

class Child2 extends Parent2{

    int x = 200;

    void method(){
        System.out.println("x=" + x);
        System.out.println("super.x=" + super.x);
        System.out.println("this.x=" + this.x);

    }

}
