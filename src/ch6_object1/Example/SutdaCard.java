package ch6_object1.Example;

public class SutdaCard {
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
