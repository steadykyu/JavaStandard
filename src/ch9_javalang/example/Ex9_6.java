package ch9_javalang.example;

class Ex9_6 {
    public static String fillZero(String src, int length) {
        String result = "";
        if(src == null || src.length()==length){
            return src;
        }else if (length <= 0){
            return "";
        }else if (src.length() > length){
            return src.substring(0,length);
        }else{
            char[] chars = new char[length];
            for(int i =0; i < chars.length; i++){
                chars[i] = '0';
            }
//            for(char c : chars){
//                c = '0';                  // 값 오류
//            }
//            System.out.println(chars);
//            향상된 for문은 읽어오는것만 가능하다.

            for(int i = 0; i < src.length(); i++){
                chars[i+src.length()] = src.charAt(i);
            }
            return String.valueOf(chars);
        }
    }
    public static void main(String[] args) {
        String src = "12345";
        System.out.println(fillZero(src,10));
        System.out.println(fillZero(src,-1));
        System.out.println(fillZero(src,3));
        System.out.println(String.valueOf('0'));
    }
}
// String에서 char[] 로 바꾸기 : tocharArr() 또는 charAt 사용하기
// char[] 에서 String        : String.valueOf(char[]);