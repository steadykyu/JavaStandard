package ch15_IO;

import java.io.*;
import java.util.Arrays;

class IOEx4 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;

        byte[] temp = new byte[4];

        ByteArrayInputStream  input  = null;
        ByteArrayOutputStream output = null;

        input  = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        try {
            while(input.available() > 0) {
                int len = input.read(temp); // 읽은건 읽은거고, 반환값으로 읽어 온 데이터의 개수를 반환한다.
                output.write(temp, 0, len); // 바로 전의 읽어 온 개수만큼만 write한다.
            }
        } catch(IOException e) {}

        outSrc = output.toByteArray();

        System.out.println("Input Source  :" + Arrays.toString(inSrc)); //[123456789]
        System.out.println("temp          :" + Arrays.toString(temp));  // [8967] 효율을 위해 덧붙여서 읽어옴.
        System.out.println("Output Source :" + Arrays.toString(outSrc));//[123456789]
    }
}

