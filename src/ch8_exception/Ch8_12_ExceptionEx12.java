package ch8_exception;

public class Ch8_12_ExceptionEx12 {
    public static void main(String[] args) throws Exception{
        method1();
    }

    static void method1() throws Exception{
        method2();
    }

    static  void method2() throws Exception{
        throw new Exception();
    }
}
// 에러가 발생한 메서드의 위치를 알 수 있다.
// 순차적으로 나오므로 method2에서 발생하고, 이후로 예외를 넘겨준것을 볼수 있다.
