package ch4_from_if_to_dowhile;

public class Ch4Ex1 {
    public static void main(String[] args) {
        int x= 11;
        char ch =' ';
        int year = 0;
        String str = "yes";
        boolean powerOn = false;
        if(10 < x && x < 20){
            System.out.println(true);
        }
        if(!(ch==' ' || ch =='\t')){
            System.out.println(true);
        }
        if(ch == 'x'|| ch=='X'){
            System.out.println(true);
        }
        if('0' < ch && ch < '9'){
            System.out.println(true);
        }
        if(('a'< ch && ch < 'z') || ('A'< ch && ch < 'Z')){
            System.out.println(ch);
        }
        if((year/400 == 0) || (year / 4 ==0 && year / 100 != 0)){
            System.out.println(true);
        }
        if(powerOn==false){
            System.out.println(powerOn);
        }
        if(str.equals("yes")){
            System.out.println(str);
        }
    }
}
