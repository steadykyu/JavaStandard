package ch15_IO;
import java.io.*;
import java.util.Arrays;

class IOEx1 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;

        ByteArrayInputStream  input  = null;
        ByteArrayOutputStream output = null;

        input  = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        int data = 0;

        while((data = input.read())!=-1) {      // 배열형태를 stream으로 읽어온다. 값이 없으면 -1을 출력
            output.write(data);	// void write(int b)
        }

        outSrc = output.toByteArray(); // 스트림의 내용을 byte배열로 반환한다.

        System.out.println("Input Source  :" + Arrays.toString(inSrc)); // 읽어만 오는 것이므로 요소가 사라지지 않는다.
        System.out.println("Output Source :" + Arrays.toString(outSrc));
    }
}

