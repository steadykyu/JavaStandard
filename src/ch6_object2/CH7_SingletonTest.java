package ch6_object2;

final class Singleton {
    private static Singleton s = new Singleton();

    private Singleton(){
        //...
    }

    public static Singleton getInstance(){
        if (s==null)
            s = new Singleton();
        return s;
    }
}
class SingletonTest{
    public static void main(String[] args) {
//        Singleton s = new Singleton(); 생성자가 private하기 때문에, 외부클래스에서 인스턴스를 만들 수 없다.
        Singleton s = Singleton.getInstance();
    }
}
