package ch6_object1.Example;

public class Ex6_6 {
    // (x,y) (x1,y1) . 두 점 와 간의 거리를 구한다
    static double getDistance(int x, int y, int x1, int y1) {
            int a = x - x1;
            int b = y - y1;
            double c = Math.sqrt(a*a+b*b);
            return c;
    }
    public static void main(String args[]) {
        System.out.println(getDistance(1,1,2,2));
    }
}
