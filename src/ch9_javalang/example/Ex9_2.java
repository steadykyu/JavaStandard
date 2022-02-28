package ch9_javalang.example;

class Ex9_2 {
    public static void main(String[] args) {
        Point3D p1 = new Point3D(1,2,3);
        Point3D p2 = new Point3D(1,2,3);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println("p1==p2?"+(p1==p2));
        System.out.println("p1.equals(p2)?"+(p1.equals(p2)));
    }
}
class Point3D {
    int x,y,z;
    Point3D(int x, int y, int z) {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    Point3D() {
        this(0,0,0);
    }
    public boolean equals(Object obj) {     // Object obj = Point3D인스턴스;
        if (obj instanceof Point3D) {       // obj는 자료형은 Object지만, 인스턴스는 main에서 Point3D가 들어올것이다.
            Point3D p = (Point3D) obj;      // 인스턴스 값을 사용하기 위해 자료형을 자식클래스로 선언해준다.
            return x == p.x && y == p.y && z == p.z;
        }
        return false;
    }

//    내건 너무 String 클래스에 의존적이다.
//        if(this.toString().equals(obj.toString())){
//            return true;
//        }else
//            return false;
//    }
    public String toString() {
        return "{"+x+","+y+","+z+"}";
    }
}
