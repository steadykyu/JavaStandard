package ch4_from_if_to_dowhile;

public class Ch4Ex8 {
    public static void main(String[] args) {
        double y;
        for(int x = 1; x<=5; x += 2){
            y = 2.5-0.5*x;
            System.out.println("x="+ x +  ", y=" + y);
        }
    }
}
//
//for(int x=0; x <=10;x++) {
//        for(int y=0; y <=10;y++) {
//        if(2*x+4*y==10) {
//        System.out.println("x="+x+", y="+y);