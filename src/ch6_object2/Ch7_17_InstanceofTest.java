package ch6_object2;

public class Ch7_17_InstanceofTest {
    public static void main(String[] args) {
        Car2 car = new Car2();

        Car2 carAmb = new ambulance();
        System.out.println(car instanceof Car2);
        System.out.println(car instanceof ambulance);


        System.out.println(carAmb instanceof ambulance);
    }
}
class Car2{}
class ambulance extends  Car2{}