package ch6_object2;

public class Ch7_31_InnerEx1 {
    class InstanceInner{
        int iv = 100;
//        static int cv = 100; 인스턴스 클래스안이라서 오류
        final static int CONST = 100; //상수가 될경우 가능
    }

    static class StaticInner{
        int iv = 200;                   // 인스턴스 변수 : static 메서드는 안에 쓰지도 못했는데, class는 쓸수는 있다.
        static int cv = 100;
    }

    void myMethod(){
        class LocalInner{
            int iv = 300;
//            static int cv = 300;
            final static int CONST = 300;       // 상수이므로 가능.
        }
    }

    public static void main(String[] args) {
        System.out.println(InstanceInner.CONST);
        System.out.println(StaticInner.cv);
        StaticInner si = new StaticInner();
        System.out.println(si.iv);
    }
}
