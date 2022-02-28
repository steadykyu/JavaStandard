package ch9_javalang.example;

class Ex9_9 {

    static String delChar(String src, String delchar){
        StringBuffer sb = new StringBuffer("");

        for(int i = 0; i < src.length(); i++){
            if( delchar.indexOf(src.charAt(i)) != -1)
                continue;
            else
                sb.append(src.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("(1!2@3^4~5)"+" -> "
                + delChar("(1!2@3^4~5)","~!@#$%^&*()"));
        System.out.println("(1 2 3 4\t5)"+" -> "
                + delChar("(1 2 3 4\t5)"," \t"));
    }
}