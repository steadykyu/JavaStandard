package ch6_object1.Example;

class Student{
    String name;
    int ban;
    int no;
    int kor;
    int eng;
    int math;

    public Student(String name, int ban, int no, int kor, int eng, int math) {
        this.name = name;
        this.ban = ban;
        this.no = no;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    int getTotal(){
        int sum = kor + eng +math;
        return sum;
    }

    double getAverage(){
        double avg = (kor + eng +math)/3.0;
        return avg;
    }

    String info(){
       return name+","+ban+","+no+","+kor+","+eng+","+math+","+getTotal()+","+String.format("%.1f",getAverage());
    }
}
public class Ex6_4 {
    public static void main(String args[]) {
//        Student s = new Student();
//        s.name = "홍길동";
//        s.ban = 1;
//        s.no = 1;
//        s.kor = 100;
//        s.eng = 60;
//        s.math = 76;
//        System.out.println("이름 :"+s.name);
//        System.out.println("총점 :"+s.getTotal());
//        System.out.println("평균 :"+String.format("%.1f",s.getAverage()));

        Student s = new Student("홍길동",1,1,100,60,76);
        System.out.println(s.info());
    }
}
