package ch4_from_if_to_dowhile;

public class Ch4Ex13 {
    public static void main(String[] args)
    {
        String value = "12o34";
        char ch = ' ';
        boolean isNumber = true;
//        ch는 아스키 코드값이 있으니까!
        for(int i=0; i < value.length() ;i++) {
            char c = value.charAt(i);
            if('1'<= c && c <= '9'){
                isNumber = true;
            }else{
                isNumber = false;
                break;
            }
        }
        if (isNumber) {
            System.out.println(value+" 는 숫자입니다.");
        } else {
            System.out.println(value+"  는 숫자가 아닙니다.");
        }
    } // end of main
}
