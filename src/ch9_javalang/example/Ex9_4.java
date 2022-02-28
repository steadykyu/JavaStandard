package ch9_javalang.example;

class Ex9_4 {
    static void printGraph(int[] dataArr, char ch) {
        for(int num : dataArr){

            for(int i =0;i < num;i++){
                System.out.print(ch);
            }
            System.out.println(num);
        }

    }
    public static void main(String[] args) {
        printGraph(new int[]{3,7,1,4},'*');
    }
}
//향상된 for문은 값변경 x이므로, 읽어올때만 쓰는게 좋다.