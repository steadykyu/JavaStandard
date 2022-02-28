package ch9_javalang;

import java.util.StringTokenizer;

public class Ch9_40StringTokenizerEx3 {
    public static void main(String[] args) {
        String source =
                "1,김천재,100,100,100|2,박수재,95,80,90|3,이자바,80,90,90";
        StringTokenizer st = new StringTokenizer(source,"|,");

        while(st.hasMoreTokens()){
            String token = st.nextToken();
            System.out.println(token);
        }
    }
}

