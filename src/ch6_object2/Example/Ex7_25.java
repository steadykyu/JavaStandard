package ch6_object2.Example;
class Outer {
    class Inner {
        int iv=100;
    }
}
public class Ex7_25 {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner oi = outer.new Inner();

        System.out.println(oi.iv);

    }
}
