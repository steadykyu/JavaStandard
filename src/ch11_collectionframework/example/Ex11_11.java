package ch11_collectionframework.example;

import java.util.*;
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
    // equals 메서드를 재정의 했음.
    public boolean equals(Object obj) {
        if(obj instanceof SutdaCard) {
            SutdaCard c = (SutdaCard)obj;
            return num==c.num && isKwang==c.isKwang;
        } else {
            return false;
        }
    }

// 오답  hash'C'ode 해야함.
//    public int hashcode(){
//        return toString().hashCode();
//    }

    public int hashCode() {
        return toString().hashCode(); // String hashCode() . 클래스의 를 호출한다
    }

    public String toString() {
        return num + ( isKwang ? "K":"");
    }
}
class Ex11_11 {
    public static void main(String[] args) {
        SutdaCard c1 = new SutdaCard(3,true);
        SutdaCard c2 = new SutdaCard(3,true);
        SutdaCard c3 = new SutdaCard(1,true);

        HashSet set = new HashSet();
        set.add(c1);
        set.add(c2);
        set.add(c3);

        System.out.println(set);
    }
}
    /**해답
     * 서로 다른 객체라도 인스턴스 변수 값이 같으면  같게 하고 싶은경우인데,
     * Object의 hashCode()는 객체의 주소값에서 hash값을 얻으므로 불가능하다.
     * 그러므로 재정의된 String의 hashCode()를 사용하였다. 문자열 값만 같으면(equals - true) hashCode() 값도 같게 재정의 되어있다.
     * 인스턴스변수들의 값을 결합한 문자열을 만들고 그 문자열에 대해  hashCode()를 호출하는 방법은 쉬우면서도 효과적이다.
     */
