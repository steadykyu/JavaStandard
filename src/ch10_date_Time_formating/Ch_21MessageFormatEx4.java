package ch10_date_Time_formating;

import java.text.MessageFormat;

import java.util.*;
import java.text.*;
import java.io.*;

class Ch_21MessageFormatEx4 {
        public static void main(String[] args) throws Exception {
            String tableName = "CUST_INFO";
            String fileName  = "C:\\Users\\kimkyuha\\Desktop\\javastandard\\src\\ch10_date_Time_formating\\data4.txt";
            String msg = "INSERT INTO "+ tableName + " VALUES ({0},{1},{2},{3});";
            Scanner s = new Scanner(new File(fileName));

            String pattern = "{0},{1},{2},{3}";
            MessageFormat mf = new MessageFormat(pattern);

            while(s.hasNextLine()) {
                String line = s.nextLine();
                Object[] objs = mf.parse(line);
                System.out.println(MessageFormat.format(msg, objs));
            }

            s.close();	// 작업이 끝났으니 Scanner에서 사용한 파일을 닫아 준다.
        } // main
    }

