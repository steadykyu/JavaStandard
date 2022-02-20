package ch5_array;

public class ch5Ex6 {
    public static void main(String args[]) {
// . 큰 금액의 동전을 우선적으로 거슬러 줘야한다
        int[] coinUnit = {500, 100, 50, 10};
        int money = 2680;
        System.out.println("money="+money);
        int[] coinCount = new int[4];
        for(int i=0;i<coinUnit.length;i++) {
            coinCount[i] = money / coinUnit[i];
            money %= coinUnit[i];
        }
        System.out.println("500원: "+ coinCount[0]);
        System.out.println("100원: "+ coinCount[1]);
        System.out.println("50원: "+ coinCount[2]);
        System.out.println("10원: "+ coinCount[3]);

    } // main
}
//for(int i=0;i<coinUnit.length;i++) {
//        System.out.println(coinUnit[i]+" : "+money/coinUnit[i]); 원
//        money = money%coinUnit[i];
//        }
// 훨씬 간결하다.
