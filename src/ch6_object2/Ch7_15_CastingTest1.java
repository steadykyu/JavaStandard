package ch6_object2;

public class Ch7_15_CastingTest1 {
    public static void main(String[] args) {
        Car car = null;
        FireEngine fe = new FireEngine();
        FireEngine fe2 = null;

        fe.water();
        car = fe;
//        car.water();    참조변수가 인스턴스보다 작으므로 불가능
        fe2 = (FireEngine) car;
        fe2.water();
        // fe2참조변수는 FireEngine의 인스턴스의 멤버개수와 같다. 그리고 car는 결국 FireEngine을 참조하고있다.
        // 그러므로 사용 가능. 이래서 형변환 전에 instanceof 연산자로 인스턴스를 확인해 보는 습관을 길러야한다.
    }
}
class Car {
    String color;
    int door;

    void drive(){
        System.out.println("drive, Brrrr~~");
    }

    void stop(){
        System.out.println("stop!!!");
    }
}
class FireEngine extends Car {
    void water(){
        System.out.println("water!!!");
    }
}