package ch9_javalang.example;

class Ex9_12 {

    static int getRand(int from, int to){
        int tmp;
        if(from > to){
            tmp = from;
            from = to;
            to = tmp;
        }
        int diff = to - from;

        int result = Math.min(from, to) + (int)(Math.random()*(diff+1));
        return result;
    }

    public static void main(String[] args)
    {
        for(int i=0; i< 20; i++)
            System.out.print(getRand(1,-3)+",");
    }
}

//    public static int getRand(int from, int to) {
//        return (int)(Math.random() * (Math.abs(to-from)+1))+ Math.min(from,to);
//    }
// 해답 : 절대값을 이용해서 계산함