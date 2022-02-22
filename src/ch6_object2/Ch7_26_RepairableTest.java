package ch6_object2;

class Ch7_26_RepairableTest{
    public static void main(String[] args) {
        Tank tank = new Tank();
        Dropship dropship = new Dropship();

        Marine marine = new Marine();
        SCV scv = new SCV();

        scv.repair(tank);
        scv.repair(dropship);
//        scv.repair(marine);
//       인터페이스를 구현한 클래스들만 들어올수 있게된다.
    }
}

interface Repairable{}

class Unit {
    int hitPoint;
    final int MAX_HP;
    Unit(int hp){
        MAX_HP = hp;
    }
    //.....
}
class GroundUnit extends Unit {
    GroundUnit(int hp) {
        super(hp);
    }
}

class AirUnit extends Unit {
    AirUnit(int hp) {
        super(hp);
    }
}

class Tank extends GroundUnit implements Repairable {
    Tank() {
        super(150);
        hitPoint = MAX_HP;
    }

    public String toString() {
        return "Tank";
    }
    //...
}
class Dropship extends AirUnit implements Repairable {
    Dropship() {
        super(125);
        hitPoint = MAX_HP;
    }

    public String toString() {
        return "Dropship";
    }
    //...
}

class Marine extends GroundUnit{
    Marine(){
        super(40);
        hitPoint = MAX_HP;
    }
}
class SCV extends GroundUnit implements Repairable{
    SCV(){
        super(60);
        hitPoint = MAX_HP;
    }
    //인터페이스를 구현한 클래스들만 매개변수로 들어올수 있게된다.
    void repair(Repairable r){
        if(r instanceof Unit){
            Unit u = (Unit) r;
//            Unit u2 = new Tank();  여기는 되는데 왜 위는 안될까?
//            결국 참조변수 안에는 인스턴스가 들어있는것인데 그럼 형변환을 명시적으로 안해도 되지않나?
//            인스턴스인데 타입이 Repairable이기 때문에, 명시적 형변환으로 (Unit)으로 바꾸어야 하나보다.
            while(u.hitPoint != u.MAX_HP){
                u.hitPoint++;
            }
            System.out.println(u.toString() + "의 수리가 끝났습니다.");

        }
    }
    //........
}
