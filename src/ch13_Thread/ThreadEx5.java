package ch13_Thread;

class ThreadEx5 {
    static long startTime = 0;

    public static void main(String args[]) {
        ThreadEx5_1 th1 = new ThreadEx5_1();
        th1.start();
        startTime = System.currentTimeMillis();

        for(int i=0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.print("소요시간1:" + (System.currentTimeMillis() - ThreadEx5.startTime));
    }
}

class ThreadEx5_1 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.println();
        System.out.print("소요시간2:" + (System.currentTimeMillis() - ThreadEx5.startTime));
    }
}
// 내가 지금 여러 프로세스를 쓰고있어서 그런가, 책과 다르게 멀티쓰레드가 훨씬 빠르게 나오네..