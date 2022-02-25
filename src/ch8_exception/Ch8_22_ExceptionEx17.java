package ch8_exception;

public class Ch8_22_ExceptionEx17 {
    public static void main(String[] args) {
        try {
            method1();
        } catch (Exception e) {
            System.out.println("main 메서드에서 예외가 처리되었습니다.");
        }
    }

    static void method1() throws Exception{
        try{
            throw new Exception();
        } catch (Exception e){
            System.out.println("method1메서드에서 예외가 처리되었습니다.");
            throw e; // 다시 예외 발생 시키기.
        }
    }
//
//    static int method1() throws Exception{
//        try{
//            System.out.println("method1() 이 호출되었습니다.");
//            return 0;
//        } catch (Exception e){
//            System.out.println("method1메서드에서 예외가 처리되었습니다.");
//            return 1;         // 비록 catch문이 수행이 안되더라도, return문이 존재해야한다.
//        }
//    }
}
