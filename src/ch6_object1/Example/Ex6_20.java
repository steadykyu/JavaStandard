package ch6_object1.Example;

public class Ex6_20 {

    static int[] shuffle(int[] numArr){

        for(int i = 0; i < 100 ; i++){
            int n = (int)(Math.random() * numArr.length);
            int tmp = numArr[n];
            numArr[n] = numArr[0];
            numArr[0] = tmp;
        }
         return numArr;
    }

    public static void main(String[] args)
    {
        int[] original = {1,2,3,4,5,6,7,8,9};
        System.out.println(java.util.Arrays.toString(original));
        int[] result = shuffle(original);
        System.out.println(java.util.Arrays.toString(result));
    }

}
