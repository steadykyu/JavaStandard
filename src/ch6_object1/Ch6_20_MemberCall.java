package ch6_object1;

public class Ch6_20_MemberCall {
    int iv = 10;
    static int cv = 20;

    int iv2 = cv;
//    static int cv2 = iv; 클래스 변수에 인스턴스 변수를 넣으려 하면 에러

    static void staticMethod1(){
        System.out.println(cv);
//        System.out.println(iv); 클래스 메서드에 인스턴스 변수를 쓰려고해서 에러
//        instanceMethod1();     클래스메서드에서 인스턴스 메서드를 사용하려고 해서 에러
    }

    void instanceMethod1(){
        System.out.println(cv);
        System.out.println(iv);
        staticMethod1();
//        인스턴스 메소드에서는 어떤것을 사용해도 다 가능하다.
    }
}

