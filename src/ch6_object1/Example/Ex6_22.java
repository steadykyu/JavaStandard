package ch6_object1.Example;

public class Ex6_22 {
    static Boolean isNumber(String str){
        Boolean isNum = false;
        for(int i = 0 ; i < str.length();i++){
            char c = str.charAt(i);
            if('0' <= c && c <= '9'){
                isNum = true;
            }else{
                isNum = false;
                return isNum;
            }
        }
        return isNum;
    }

    public static void main(String[] args) {
        String str = "123";
        System.out.println(str+"는 숫자입니까? "+isNumber(str));
        str = "1234o";
        System.out.println(str+"는 숫자입니까 ? "+isNumber(str));
    }
}
