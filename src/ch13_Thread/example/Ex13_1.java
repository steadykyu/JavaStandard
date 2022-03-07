package ch13_Thread.example;

class Ex13_1 {
    public static void main(String args[]) {
        Runnable r = new Thread1();
        Thread th1 = new Thread(r);

        // Thread th1 = new Thread(new Thread1());
        // interface 구현체를 Thread안에 넣어준다.
        th1.start();
    }
}
class Thread1 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print('-');
        }
    }
}