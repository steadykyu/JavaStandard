package ch13_Thread;

class ThreadEx19 {
    static long startTime = 0;

    public static void main(String args[]) {
        ThreadEx19_1 th1 = new ThreadEx19_1();
        ThreadEx19_2 th2 = new ThreadEx19_2();

        th1.start();
        th2.start();
        startTime = System.currentTimeMillis();

//        try {
//            th1.join();	// main쓰레드가 th1의 작업이 끝날 때까지 기다린다.
//            th2.join();	// main쓰레드가 th2의 작업이 끝날 때까지 기다린다.
//        } catch(InterruptedException e) {}

        System.out.print("소요시간:" + (System.currentTimeMillis() - ThreadEx19.startTime));
        // join이 있기에 작업이 끝날때까지 기다리게 되고, main쓰레드가 소요시간을 측정할 수 있게되었다.
        // 주석처리하면 소요시간이 0 나온다.
    } // main
}
// -
class ThreadEx19_1 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print(new String("-"));
        }
    } // run()
}
// |
class ThreadEx19_2 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print(new String("|"));
        }
    } // run()
}

