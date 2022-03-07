package ch13_Thread;

class ThreadEx12 {
    public static void main(String args[]) {
        ThreadEx12_1 th1 = new ThreadEx12_1();
        ThreadEx12_2 th2 = new ThreadEx12_2();

        th1.start();
        th2.start();

        try {
//            th1.sleep(2000);
            Thread.sleep(2000);
            // 위코드로 하나 아래로 하나 결과가 같음. 어차피 현재 실행중인 thread에 대하여 sleep을 걸기때문.
        } catch(InterruptedException e) {}

        System.out.print("<<main 종료>>");
    } // main
}

class ThreadEx12_1 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.print("<<th1 종료>>");
    } // run()
}

class ThreadEx12_2 extends Thread {
    public void run() {
        for(int i=0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.print("<<th2 종료>>");
    } // run()
}
// 결과가 그떄그때 다르게 나온다.
