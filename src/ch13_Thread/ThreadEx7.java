package ch13_Thread;

import javax.swing.JOptionPane;

class ThreadEx7 {
    public static void main(String[] args) throws Exception 	{
        ThreadEx7_1 th1 = new ThreadEx7_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
    }
}

class ThreadEx7_1 extends Thread {
    public void run() {
        for(int i=10; i > 0; i--) {
            System.out.println(i);
            try {
                sleep(1000);
            } catch(Exception e ) {}
        }
    }
}
// 입력을 천천히 하더라도 , start()를 통해 쓰레드에서 run 메서드문이 수행되고 있다.