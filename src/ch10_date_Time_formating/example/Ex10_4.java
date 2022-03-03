package ch10_date_Time_formating.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Ex10_4 {
    public static void main(String[] args) {
        System.out.println("날짜를 yyyy/MM/dd의 형태로 입력해주세요.");
        System.out.print(">>");
        Scanner scan = new Scanner(System.in);

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        DateFormat df2 = new SimpleDateFormat("입력하신 날짜는 E요일입니다.");
        while(scan.hasNextLine()){

            String scanStr = scan.nextLine();
            Date d = null;
            try {
                d = df.parse(scanStr);
                System.out.println(df2.format(d));
                break;
            } catch (Exception e) {
                System.out.println("날짜를 yyyy/MM/dd의 형태로 입력해주세요.");
                System.out.print(">>>>");
            }
        }
    }
}
// Scanner에서 값을 읽는 부분이 nextLine()
// System.in은 내부에서 읽겠다고 설정만 하는 것이고,
// 만약 txt를 읽었다면 txt를 가져오기만 하고  nextLine() 에서 읽기 시작하는것이다.
// 유효성 검사 : validation이란 어떤 데이터의 값이 유효한지, 타당한지 확인하는 것을 의미합니다.

/** do while이 더 깔끔했을듯
 * do {
 * System.out.println(" " + pattern 날짜를
 * + " .( :2007/05/11)"); 의 형태로 입력해주세요 입력예
 * try {
 * System.out.print(">>");
 * inDate = df.parse(s.nextLine()); // ParseException . 이 발생할 수 있다
 * break; // parse() . 에서 예외가 발생하면 이 문장은 수행되지 않는다
 * } catch(Exception e) {}
 * } while(true);
 */
