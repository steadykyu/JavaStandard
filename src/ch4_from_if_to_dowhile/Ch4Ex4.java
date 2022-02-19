package ch4_from_if_to_dowhile;

public class Ch4Ex4 {
    public static void main(String[] args) {
        int sum = 0;
        Loop1: while(true){
            for(int i = 1; i < 10000 ; i++){
                if(i % 2 == 0){
                    sum -= i;
                }else{
                    sum += i;
                }
                if(sum >= 100){
                    System.out.printf("%d %d",i, sum);
                    break Loop1;
                }
            }
        }
    }
}
// 증감자에 부호를 붙여줄수 있다. 신기방기 s=-s

// true . 조건식의 값이 이므로 무한반복문이 된다
//for(int i=1;true; i++, s=-s) { // s 1, -1, 1, -1... 매 반복마다 의 값은
//        num = s * i; // i (s) . 와 부호 를 곱해서 더할 값을 구한다
//        sum += num;
//        if(sum >=100) // 100 . 총합이 보다 같거나 크면 반복문을 빠져 나간다
//        break;
//        }