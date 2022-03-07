package ch13_Thread.example;

import javax.swing.*;

class Exercise13_9 {
    public static void main(String[] args) throws Exception {
        Exercise13_9_1 th1 = new Exercise13_9_1();
        th1.start();
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
        th1.interrupt(); // 쓰레드에게 작업을 멈추라고 요청한다 - interrupeted를 true로 만든다.
    }
}
class Exercise13_9_1 extends Thread {
    public void run() {
        int i = 10;
        while (i != 0 && !isInterrupted()) {    // interrupeted가 true가 되어야 종료가 된다.
            System.out.println(i--);
            try {
                Thread.sleep(1000); // 1초 지연
            } catch (InterruptedException e) {
                interrupt();
                // interrupt()가 interrupeted를 true로 만들지만,
                // sleep 상태일때 종료시키면 InterruptedException를 발생시키면서 동시에 interrupeted()를 false로 초기화 한다.
            }
        }
    System.out.println("카운트가 종료되었습니다.");
} // main
}