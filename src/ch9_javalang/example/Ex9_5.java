package ch9_javalang.example;

public class Ex9_5 {
}

class Exercise9_5 {
    public static int count(String src, String target) {
        int count = 0; // 찾은 횟수
        int pos = 0; // 찾기 시작할 위치
        for(int i = 0; i < src.length(); i++ ){

            if(src.indexOf(target,pos)!= -1){
                count++;
                pos = src.indexOf(target,pos)+target.length();
//                System.out.println("pos="+pos);
            }else
                break;
        }
        return count;
    }
    public static void main(String[] args) {
        System.out.println(count("12345AB12AB345AB","AB"));
        System.out.println(count("12345","AB"));
//        System.out.println("===========test");
//        System.out.println(("12345AB12AB345AB".indexOf("AB",7)));

    }
}

// 해답
//while(true) {

//        pos = src.indexOf(target,pos);            // 이렇게 하면 더 간단하다.

//        if(pos!=-1) {
//        count++;
//        pos += target.length(); // pos . 를 찾은 단어 이후로 옮긴다
//        } else {
//// 3. indexOf -1 count . 의 결과가 이면 반복문을 빠져나가서 를 반환한다
//        break;
//        }
//        }