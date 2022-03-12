package ch15_IO;

import java.io.*;

class StringReaderWriterEx {
    public static void main(String[] args) {
        String inputData = "ABCD";
        StringReader input  = new StringReader(inputData);
        StringWriter output = new StringWriter();

        int data = 0;

        try {
            while((data = input.read())!=-1) {
                output.write(data);	// void write(int b)
                System.out.println("4번진행");// 문자하나하나 씩 읽어옴.
            }
        } catch(IOException e) {}

        System.out.println("Input Data  :" + inputData);
        System.out.println("Output Data :" + output.toString());
		System.out.println("Output Data :" + output.getBuffer().toString());
    }
}
