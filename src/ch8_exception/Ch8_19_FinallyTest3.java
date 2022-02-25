package ch8_exception;

public class Ch8_19_FinallyTest3 {
    public static void main(String[] args) {
        Ch8_19_FinallyTest3.method1();
        System.out.println("method()의 수행을 마치고 main메서드로 돌아왔습니다.");
    }

    static void method1(){
        try{
            System.out.println("method1()이 호출되었습니다.");
            return;
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("method1()의 finally블럭이 실행되었습니다.");
        }
    }
}
// return을 해도 finally는 수행하고 method를 나가는 모습