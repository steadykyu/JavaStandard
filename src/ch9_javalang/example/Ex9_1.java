package ch9_javalang.example;

class Ex9_1 {
    public static void main(String[] args) {
        SutdaCard c1 = new SutdaCard(3,true);
        SutdaCard c2 = new SutdaCard(3,true);
        System.out.println("c1="+c1);
        System.out.println("c2="+c2);
        System.out.println("c1.equals(c2):"+c1.equals(c2));
    }
}
class SutdaCard {
    int num;
    boolean isKwang;

    SutdaCard() {
        this(1, true);
    }
    SutdaCard(int num, boolean isKwang) {
        this.num = num;
        this.isKwang = isKwang;
    }
    public boolean equals(Object obj) {
        //해답 Ex2에 설명있음.
        if (obj instanceof SutdaCard) {
            SutdaCard c = (SutdaCard) obj;
            return num == c.num && isKwang == c.isKwang;
        }
        return false;
    }


//        if(this.toString().equals(obj.toString())){
//            return true;
//        }else
//            return false;
//    }
    public String toString() {
        return num + ( isKwang ? "K":"");
    }
}

