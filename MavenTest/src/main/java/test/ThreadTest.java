package test;

import java.util.concurrent.atomic.AtomicInteger;

class MyThread extends Thread {
    private int id;
    private static AtomicInteger c = new AtomicInteger(0);

    MyThread(int id) {
        super();
        this.id = id;
    }

    @Override
    public void run() {
        while (c.get() < 75) {
            if (c.get() / 5 % 3 == id) {
                for (int i = 0; i < 5; i++) {
                    c.getAndIncrement();
                    System.out.println("Thread" + id + ":" + c.get());
                }
            }
        }
    }
}

/**
 * description：14页
 *
 * @author ajie
 * data 2018/11/22 10:58
 */
public class ThreadTest {
    public static void main(String[] args) {
        new MyThread(0).start();
        new MyThread(1).start();
        new MyThread(2).start();
    }
}

