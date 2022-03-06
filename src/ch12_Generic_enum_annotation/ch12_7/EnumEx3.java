package ch12_Generic_enum_annotation.ch12_7;
// 마치 익명클래스의 느낌으로, 추상메서드를 구현하고 있다.
enum Transportation {
    BUS(100)      { int fare(int distance) { return distance*BASIC_FARE;}},
    TRAIN(150)    { int fare(int distance) { return distance*BASIC_FARE;}},
    SHIP(100)     { int fare(int distance) { return distance*BASIC_FARE;}},
    AIRPLANE(300) { int fare(int distance) { return distance*BASIC_FARE;}};

    protected final int BASIC_FARE; // protected로 해야 각 상수에서 접근가능

    Transportation(int basicFare) { // private Transportation(int basicFare) {
        BASIC_FARE = basicFare;
    }

    public int getBasicFare() { return BASIC_FARE; }

    abstract int fare(int distance); // 거리에 따른 요금 계산    / 추상메서드 선언!
}

class EnumEx3 {
    public static void main(String[] args) {
        System.out.println("bus fare="     +Transportation.BUS.fare(200));  // 거리값 수정해도 괜찮
        System.out.println("train fare="   +Transportation.TRAIN.fare(100));
        System.out.println("ship fare="    +Transportation.SHIP.fare(100));
        System.out.println("airplane fare="+Transportation.AIRPLANE.fare(100));
    }
}

