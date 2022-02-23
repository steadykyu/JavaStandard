package ch6_object2.Example;

class MyTv2 {
    private boolean isPowerOn;
    private int channel;
    private int volume;
    private int beforeChannel;
    final int MAX_VOLUME = 100;
    final int MIN_VOLUME = 0;
    final int MAX_CHANNEL = 100;
    final int MIN_CHANNEL = 1;


    public void gotoPrevChannel() {
        setChannel(beforeChannel); // . 현재 체널을 이전 채널로 변경한다
    }

//    void gotoPrevChannel(){
//        int temp = channel;
//        this.channel = beforeChannel;
//        beforeChannel = temp;
//    }

    public boolean isPowerOn() {
        return isPowerOn;
    }

    public void setPowerOn(boolean powerOn) {
        isPowerOn = powerOn;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        beforeChannel = this.channel;
        this.channel = channel;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}

public class Ex7_10 {
        public static void main(String args[]) {
            MyTv2 t = new MyTv2();
            t.setChannel(10);
            System.out.println("CH:"+t.getChannel());
            t.setChannel(20);
            System.out.println("CH:"+t.getChannel());
            t.gotoPrevChannel();
            System.out.println("CH:"+t.getChannel());
            t.gotoPrevChannel();
            System.out.println("CH:"+t.getChannel());
        }

    }
