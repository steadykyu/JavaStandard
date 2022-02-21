package ch6_object1;

class MyMath2{
    long a, b;
    //인스턴스 변수 a b
    long add() { return  a+b;}

    // 클래스 메서드(static method)
    // 여기의 c,d 는 매개변수이다. 인스턴스 변수a, b는 못들어온다.
    static long add(long c, long d){return  c+d;}
}
public class ch6_19_MyMathTest2 {
    public static void main(String[] args) {
        System.out.println(MyMath2.add(100L, 200L));

        MyMath2 mm2 = new MyMath2();
        mm2.a = 200L;
        mm2.b = 400L;
        System.out.println(mm2.add());
    }
}
