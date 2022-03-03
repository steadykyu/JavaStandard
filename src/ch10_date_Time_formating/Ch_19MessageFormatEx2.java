package ch10_date_Time_formating;

import java.text.MessageFormat;

import java.util.*;
import java.text.*;

class Ch_19MessageFormatEx2 {
        public static void main(String[] args) {
            String tableName = "CUST_INFO";
            String msg = "INSERT INTO "+ tableName +" VALUES (''{0}'',''{1}'',{2},''{3}'');";   // 홑따옴표

            Object[][] arguments = {
                    {"이자바","02-123-1234", "27", "07-09"},
                    {"김프로","032-333-1234", "33", "10-07"},
            };

            for(int i=0; i < arguments.length;i++) {    // 2
                String result = MessageFormat.format(msg, arguments[i]); // 각 row {} 가 값들이 들어간다.
                System.out.println(result);
            }
        } // main
    }
