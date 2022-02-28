package ch9_javalang;

class Point implements Cloneable{
    int x, y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "x="+ x+ ", y = " + y;
    }

//    public Object clone(){
    public Point clone(){
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Point)obj; //공변 반환타입
//        return obj;

    }
}

public class Ch9_7CloneEx1 {
    public static void main(String[] args) {
        Point original = new Point(3, 5);
        //Point copy = (Point)(original.clone());   // 복사해온 값에다가 Point 형변환을 시킴.
        Point copy = original.clone();
        System.out.println(original);
        System.out.println(copy);
    }
}
