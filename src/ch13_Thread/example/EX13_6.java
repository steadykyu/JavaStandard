package ch13_Thread.example;

class Exercise13_6
{
    public static void main(String[] args) throws Exception
    {
        Thread4 th1 = new Thread4();
        th1.setDaemon(true);
        th1.start();
        try {
            th1.sleep(5*1000);
        } catch(Exception e) {}
        throw new Exception("꽝~!!!");
    }
}
class Thread4 extends Thread {
    public void run() {
        for(int i=0; i < 10; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch(Exception e) {}
        }
    } // run()
}
// main 쓰레드에 예외가 발생하여 쓰레드, 호출스택이 종료된다.
// 그러므로 데몬쓰레드 또한 종료된다.
