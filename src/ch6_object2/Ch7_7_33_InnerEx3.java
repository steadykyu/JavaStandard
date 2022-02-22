package ch6_object2;

public class Ch7_7_33_InnerEx3 {
    private int outerlv = 0;
    static int outerCv = 0;

    class InstanceInner {
        int iiv = outerlv;
        int iiv2 = outerCv;
    }

    static class StaticInner{
//        int siv = outerlv;
        static int scv = outerCv;
    }

    void myMethod(){
        int lv = 0;             //final이 생략되어있다.
        final int LV = 0;
//        lv = 200;
        class LocalInner {
            int liv = outerlv;
            int liv2 = outerCv;

            int liv3 = lv;
            int liv4 = LV;

        }

    }
}
