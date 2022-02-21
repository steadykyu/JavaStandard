package ch6_object1;



class Data { int x; }

class ch6_9PrimitiveParmEx {
    public static void main(String[] args) {
        Data d = new Data();
        d.x = 10;
        System.out.println("main() : x= " + d.x);

        change(d.x);
        System.out.println("After change(d.x)");
        System.out.println("main() : x = " + d.x);
    }

    static void change(int x){
        x = 1000;
        System.out.println("change() : x = "+ x);
    }
}
// 기본자료형일때,


