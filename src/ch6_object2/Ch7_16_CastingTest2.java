package ch6_object2;

public class Ch7_16_CastingTest2 {
    public static void main(String[] args) {
        Car car = new Car();
        Car car2 = null;
        FireEngine fe = null;

        car.drive();
        fe = (FireEngine) car;      // 에러발생. 자식의 참조변수크기가 인스턴스 크기보다 더 크다. 그러므로 에러
        fe.drive();
        car2 = fe;
        car2.drive();

    }
}
