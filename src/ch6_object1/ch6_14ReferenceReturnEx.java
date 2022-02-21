package ch6_object1;


class DataX3 { int x;}
class ch6_14ReferenceReturnEx {
    public static void main(String[] args) {
        DataX3 d = new DataX3(); //"0x100"
        d.x = 10;

        DataX3 d2 = copy(d); // tmp 주소값임. "0x200"
        d2.x = d2.x + 1;
        System.out.println("d.x = " + d.x);
        System.out.println("d2.x = " + d2.x);



    }

    static DataX3 copy(DataX3 d){
        DataX3 tmp = new DataX3();  // 새 객체 생성 "0x200"
        tmp.x = d.x;
        return tmp; // 참조형 자료를 반환으로 하였다. 이러면 그냥 주소가 움직이는거다. "0x200"
    }
}
