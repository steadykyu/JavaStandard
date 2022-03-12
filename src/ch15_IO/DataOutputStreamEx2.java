package ch15_IO;

import java.io.*;
import java.util.Arrays;

class DataOutputStreamEx2 {
    public static void main(String args[]) {
        ByteArrayOutputStream bos = null;
        DataOutputStream      dos = null;

        byte[] result = null;

        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            dos.writeInt(10);
            dos.writeFloat(20.0f);
            dos.writeBoolean(true);

            result = bos.toByteArray();

            String[] hex = new String[result.length];

            for(int i=0;i<result.length; i++) {
                if(result[i] < 0) {
                    hex[i] = String.format("%02x", result[i]+256);      // 부호 없는 정수에는 256을 더하면 10진수값을 16진수로 바꿀수 있다.(책확인)
                } else {
                    hex[i] = String.format("%02x", result[i]);
                }
            }

            System.out.println("10진수  :" + Arrays.toString(result));
            System.out.println("16진수  :" + Arrays.toString(hex));

            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // main
}

