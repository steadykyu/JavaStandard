package ch13_Thread;

class ThreadEx10 implements Runnable  {
    static boolean autoSave = false;

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadEx10());
        t.setDaemon(true);		// 이 부분이 없으면 종료되지 않는다.데몬쓰레드로 설정
        t.start();

        for(int i=1; i <= 10; i++) {
            try{
                Thread.sleep(1000);     //1초마다.
            } catch(InterruptedException e) {}
            System.out.println(i);

            if(i==5)
                autoSave = true;
        }

        System.out.println("프로그램을 종료합니다.");
    }

    public void run() {
        while(true) {                           //무한정 돌아가도록 설정했지만 데몬쓰레드라 메인쓰레드 종료시 종료된다.
            try {
                Thread.sleep(3 * 1000);	// 3초마다
            } catch(InterruptedException e) {}

            // autoSave의 값이 true이면 autoSave()를 호출한다.
            if(autoSave) {
                autoSave();
            }
        }
    }

    public void autoSave() {
        System.out.println("작업파일이 자동저장되었습니다.");
    }
}
