package ch6_object1.Example;

public class Ex6_2 {
    public static void main(String args[]) {
        SutdaCard card1 = new SutdaCard(3, false);
        SutdaCard card2 = new SutdaCard();
        System.out.println(card1.info()); // 3 . 이 출력된다
        System.out.println(card2.info()); // 1K . 가 출력된다
    }

    static class SutdaCard {
        int num;
        boolean isKwang;

        SutdaCard(){
            this(1, true);
        }

        SutdaCard(int num, boolean isKwang){
            this.num = num;
            this.isKwang = isKwang;
        }

        String info(){
            return num+ (isKwang? "K" : "");
        }
    }
}
