package ch9_javalang;

import java.util.ArrayList;
import java.util.Arrays;

public class Ch9_8CloneEx2 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        int[] arrClone = arr.clone();
        arrClone[0] = 6;

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arrClone));
    }
}
