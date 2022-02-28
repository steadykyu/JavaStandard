package ch9_javalang.example;


public class Ex9_13 {
    public static void main(String[] args) {
        String src = "aabbccAABBCCaa";
        System.out.println(src);
        System.out.println("aa를 " + stringCount(src, "aa") +" 개 찾았습니다.");
    }
    static int stringCount(String src, String key) {
        return stringCount(src, key, 0);
    }
    static int stringCount(String src, String key, int pos) {
        int count = 0;
        int index = 0;
        if (key == null || key.length() == 0)
            return 0;

//        while (true) {
//            if (src.indexOf(key, index) != -1) {
//                count++;
//                index = src.indexOf(key, index) + key.length();
//            }else{
//                break;
//            }
//        }

        while((index = src.indexOf(key, pos))!=-1) {
            count++;
            pos = index + key.length();
        }
        //해답
        //대입과 동시에 비교해서 코드를 간결하게 한 모습이다.
        //조건문 안에서는 일반적인 개념과 다르게 대입후 비교할 수 있다.

        return count;
    }
}
