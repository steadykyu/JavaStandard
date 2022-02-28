package ch9_javalang.example;

public class Ex9_8 {

    static double round(double dnum, int length){
        return Math.round(dnum* Math.pow(10,length)) / Math.pow(10,length)  ;
    }

    public static void main(String[] args) {
        System.out.println(round(3.1415,1));
        System.out.println(round(3.1415,2));
        System.out.println(round(3.1415,3));
        System.out.println(round(3.1415,4));
        System.out.println(round(3.1415,5));
    }
}
