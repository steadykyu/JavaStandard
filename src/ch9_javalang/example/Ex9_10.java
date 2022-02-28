package ch9_javalang.example;

public class Ex9_10 {

}
class Exercise9_10
{
    static String format(String str, int length, int alignment){
        int diff = length - str.length();

        if(diff < 0){
            return str.substring(0,length);
        }else{
            char[] result = new char[length];
            for(int i = 0; i < result.length; i++){
                result[i] = ' ';
            }
            char[] source = str.toCharArray();

            switch (alignment){
                case 0:
                    System.arraycopy(source,0,result,0,source.length);
                    break;
                case 1:
                    System.arraycopy(source,0,result,diff/2,source.length);
                    break;
                case 2:
                    System.arraycopy(source,0,result,diff,source.length);
                    break;
            }
            return new String(result);
//            return String.valueOf(result)
//            이거 쓰면 내장되어있는 parseChar()메서드를 예외없이 동작시키기 위해 trim()으로 공백을 다 제거해버리는 것 같다.
//            char[] 의 원본 값을 문자열에 다 가져가고 싶다면 위처럼 String생성 주입으로 해주자.
        }


    }
    public static void main(String[] args) {
        String str = "가나다";
        System.out.println(format(str,7,0)); // 왼쪽 정렬
        System.out.println(format(str,7,1)); // 가운데 정렬
        System.out.println(format(str,7,2)); // 오른쪽 정렬
    }
}
