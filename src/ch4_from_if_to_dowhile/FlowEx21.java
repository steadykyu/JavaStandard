package ch4_from_if_to_dowhile;

public class FlowEx21 {
    public static void main(String[] args) {
        for(int i=1; i<= 5; i++){
            for(int j=1; j <= 5; j++){
                if(i==j){
                    System.out.printf("[%d %d]",i , j);
                }else{
                    // char형이면 ' '를 써주자. %5c로 공백크기 결정가능하다.
                    System.out.printf("%5c", ' ');
                }
            }
            System.out.println("");
        }
    }
}
