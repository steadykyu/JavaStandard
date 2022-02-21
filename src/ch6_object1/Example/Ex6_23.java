package ch6_object1.Example;

public class Ex6_23 {

    static int max(int[] data){
        if(data==null){
            return -999999;
        }else if (data.length==0){
            return -999999;
        }else{
            int maxVal = 0;
            for(int i = 0; i < data.length; i++){
                if(maxVal < data[i]){
                    maxVal = data[i];
                }
            }
            return maxVal;
        }
    }

    public static void main(String[] args)
    {
        int[] data = {3,2,9,4,7};
        System.out.println(java.util.Arrays.toString(data));
        System.out.println("최대값 :"+max(data));
        System.out.println("최대값 :"+max(null));
        System.out.println("최대값 :"+max(new int[]{})); // 0 최대값 크기가 인 배열
    }

}
