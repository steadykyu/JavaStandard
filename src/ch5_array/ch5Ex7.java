package ch5_array;

public class ch5Ex7 {
    public static void main(String args[])
    {
        if(args.length!=1) {
            System.out.println("USAGE: java Exercise5_7 3120");
            System.exit(0);
        }
// . . 문자열을 숫자로 변환한다 입력한 값이 숫자가 아닐 경우 예외가 발생한다
        int money = Integer.parseInt(args[0]);
        System.out.println("money="+money);
        int[] coinUnit = {500, 100, 50, 10 }; // 동전의 단위
        int[] coin = {5, 5, 5, 5}; // 단위별 동전의 개수
        for(int i=0;i<coinUnit.length;i++) {
            int coinNum = 0;

            coinNum = money / coinUnit[i];
            if(coinNum >= 5){
                money = money - coinUnit[i] * coin[i];
                System.out.println(coinUnit[i]+"원 : "+ coin[i]);
                coin[i] = 0;
            }else{
                money = money - coinUnit[i] * coinNum;
                System.out.println(coinUnit[i]+"원 : "+coinNum);
                coin[i] -= coinNum;
            }

        }
        if(money > 0) {
            System.out.println("거스름돈이 부족합니다.");
            System.exit(0); // . 프로그램을 종료한다
        }
        System.out.println("=남은 동전의 개수 =");
        for(int i=0;i<coinUnit.length;i++) {
            System.out.println(coinUnit[i]+"원 :"+coin[i]);
        }
    } // main

}
/* (1) . 아래의 로직에 맞게 코드를 작성하시오
1. (money) 금액 을 동전단위로 나눠서 필요한 동전의 개수(coinNum) 를 구한다
2. coin배열 에서 (coinNum)만큼의 동전을 뺀다
만일 충분한 동전( coin .) 이 없다면 배열 에 있는 만큼만 뺀다
3. . 금액에서 동전의 개수(coinNum)  와 동전단위를 곱한 값을 뺀다
*/
//경로 src까지 찾은ㅎ 후
//javac -encoding UtF-8 ch5Ex7.java && java ch5Ex7.java 3170

// 훨씬 간결;
//        coinNum = money/coinUnit[i];
//        if(coin[i] >= coinNum) {
//        coin[i] -= coinNum;
//        } else {
//        coinNum = coin[i];
//        coin[i] = 0;
//        }
//        money -= coinNum*coinUnit[i];
