package ch9_javalang;

final class CardClass{
    String kind;
    int num;

    CardClass(){
        this("SPADE", 1);
    }

    CardClass(String kind, int num){
        this.kind = kind;
        this.num = num;
    }

    public String toString(){
        return kind + ":" + num;
    }
}

public class Ch9_10ClassEx1 {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        CardClass c = new CardClass("HEART",3);

            CardClass c2 = CardClass.class.newInstance();
            // 예외처리해주어야 사용가능
            // CardClass 클래스 정보를 담은 Class클래스에서 인스턴스생성


        Class cObj = c.getClass();      // c의 자료형인 CardClass 의 정보를 담은 Class클래스

        System.out.println(c);
        System.out.println(c2);
        System.out.println(cObj.getName());
        System.out.println(cObj.toGenericString());
        System.out.println(cObj.toString());
    }
}
