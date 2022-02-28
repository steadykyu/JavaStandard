package ch9_javalang;

class Circle implements Cloneable{
    Point p;
    double r;

    public Circle(Point p, double r) {
        this.p = p;
        this.r = r;
    }

    public Circle shallowCopy(){            //얕은 복사
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Circle) obj;
    }

    public Circle deepCopy(){               //깊은 복사
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Circle c = (Circle)obj;
        c.p = new Point(this.p.x, this.p.y);            // 참조변수에 새로운 객체를 넣어줌.

        return c;
    }

    public String toString(){
        return "[p=" + p + ", r="+ r +"]";
    }
}
// Point - 9_7EX
public class Ch9_9ShallowDeepCopy {
    public static void main(String[] args) {
        Circle c1 = new Circle(new Point(1,1),2.0);
        Circle c2 = c1.shallowCopy();
        Circle c3 = c1.deepCopy();

        System.out.println("c1="+c1);
        System.out.println("c2="+c2);
        System.out.println("c3="+c3);

        c1.p.x = 9;
        c1.p.y = 9;
        System.out.println("=============c1변경후=========");
        System.out.println("c1="+c1);
        System.out.println("c2="+c2);       // 같은 Point 객체를 공유하기 때문에, 값이 변한모습
        System.out.println("c3="+c3);
    }
}
