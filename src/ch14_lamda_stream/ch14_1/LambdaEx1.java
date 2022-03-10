package ch14_lamda_stream.ch14_1;

@FunctionalInterface        // 함수형 인터페이스 선언
interface MyFunction {
    void run();  // public abstract void run();
}

class LambdaEx1 {

    static void execute(MyFunction f) { // 매개변수의 타입이 MyFunction인 메서드
        f.run();
    }

    static MyFunction getMyFunction() { // 반환 타입이 MyFunction인 메서드
        MyFunction f = () -> System.out.println("f3.run()");
        return f;
    }

    public static void main(String[] args) {
        // 람다식으로 MyFunction의 run()을 구현
        MyFunction f1 = ()-> System.out.println("f1.run()");

        MyFunction f2 = new MyFunction() {  // 익명클래스로 run()을 구현
            public void run() {   // public을 반드시 붙여야 함
                System.out.println("f2.run()");
            }
        };

        MyFunction f3 = getMyFunction();

        f1.run();
        f2.run();
        f3.run();
        // 3개가 전부 이름은 run()이지만 다른 메서드를 의미하고 있다.

        execute(f1);        // MyFunction interface를 구현한 익명객체(줄여서 구현체)를 참조하는 f1 을 넣었다., f1참조변수가 가리키는 run()을 출력한다.
        execute( ()-> System.out.println("run()") );
    }
}

