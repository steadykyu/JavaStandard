package ch6_object1;


class DataX2 { int x; }
class ch6_6ReferenceParamEx {
    public static void main(String[] args) {
        DataX2 d = new DataX2();
        d.x = 10;
        System.out.println("main() : x= " + d.x);

        change2(d);
        System.out.println("After change(d.x)");
        System.out.println("main() : x = " + d.x);
    }
        static void change2 (DataX2 d){
            d.x = 1000;
            System.out.println("change() : x = "+ d.x);
        }
}
// 참조형 변수일때는 매개변수에서 주소를 복사하기 때문에, 주소안의 값이 변경이 된다.
// array, 새로운클래스들 등등
