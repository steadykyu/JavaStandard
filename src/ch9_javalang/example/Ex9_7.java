package ch9_javalang.example;

public class Ex9_7 {

    static boolean contains(String str, String contain){
        return str.contains(contain);
    }
    public static void main(String[] args) {
        System.out.println(contains("12345","23"));
        System.out.println(contains("12345","67"));
    }
}



