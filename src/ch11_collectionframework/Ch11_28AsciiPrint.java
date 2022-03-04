package ch11_collectionframework;

class Ch11_28AsciiPrint{
    public static void main(String[] args) {
        char ch = ' ';
        for(int i=0; i < 95; i++)  // 공백(' ')이후의 문자들을 출력한다.
            System.out.print(ch++);
    }
}
//공백이 코드값 크기가 가장 크다.
