package ch14_lamda_stream.ch14_3;

@FunctionalInterface
interface MyFunction {
    void myMethod();
}

class Outer {
    int val=10;	// Outer.this.val

    class Inner {
        int val=20;	// this.val

        void method(int i) {  // 	void method(final int i) {
            int val=30; // final int val=30;
//			i = 10;      // 에러. 상수의 값을 변경할 수 없음.

            MyFunction f = () -> {
                System.out.println("             i :" + i);
                System.out.println("           val :" + val);
                System.out.println("      this.val :" + ++this.val);      // 외부클래스의 인스턴스 변수이므로 값 변경이 가능한 모습
                System.out.println("Outer.this.val :" + ++Outer.this.val);// 위와동일
            };
            // 람다식은 익명객체(익명클래스)이므로, 지역클래스와 같은 동작방식을 가지고 있는 모습.(지역익명객체상태)
            f.myMethod();
        }
    } // Inner클래스의 끝
} // Outer클래스의 끝

class LambdaEx3 {
    public static void main(String args[]) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.method(100);
    }
}

