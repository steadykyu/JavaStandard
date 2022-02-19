package ch4_from_if_to_dowhile;

public class FlowEx29 {
    public static void main(String[] args) {
        for(int i = 1; i <= 100; i++){
            System.out.printf("i=%d",i);

            int tmp = i;

            do {
                // tmp%10이 3의 배수인지 확인 + 0은 제외
                if(tmp%10%3==0 && tmp%10!=0)
                    System.out.printf("짝");
            }while ((tmp/=10)!=0);          // tmp = tmp/10 - 몫(십의자리)만 남는다.
        }
    }
}
// tmp%10 로 일의 자리수를 알수 있다.
