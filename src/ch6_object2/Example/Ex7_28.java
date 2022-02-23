package ch6_object2.Example;

import java.awt.*;
import java.awt.event.*;

class Ex7_28
{
    public static void main(String[] args)
    {
        Frame f = new Frame();
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){       //구현해야하는 windowClosing을 구현
                e.getWindow().setVisible(false);
                e.getWindow().dispose();
                System.exit(0);
            }
        }
        );
    }
}


//원본
//class Ex7_28
//{
//    public static void main(String[] args)
//    {
//        Frame f = new Frame();
//        f.addWindowListener(new EventHandler());
//    }
//}
//class EventHandler extends WindowAdapter
//{
//    public void windowClosing(WindowEvent e) {
//        e.getWindow().setVisible(false);
//        e.getWindow().dispose();
//        System.exit(0);
//    }
//}

