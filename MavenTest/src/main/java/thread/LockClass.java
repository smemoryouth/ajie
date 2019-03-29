package thread;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyLockTickets implements Runnable {

    private int tickets = 100;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {

        while (true) {
            try {
                lock.lock();
                if (tickets > 0) {
                    try{
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "出售第" + (tickets--) + "张票");
                }
            } finally {
                lock.unlock();
            }
        }

    }
}

/**
 * description：lock类测试
 * 可重入锁，实现了公平锁和非公平锁，锁申请等待限时，可中断
 *
 * @author ajie
 * data 2018/8/3 17:09
 */
public class LockClass {
    @Test
    public void lockClassMain() {
        MyLockTickets myLockTickets = new MyLockTickets();
        Thread thread1 = new Thread(myLockTickets, "窗口1");
        Thread thread2 = new Thread(myLockTickets, "窗口2");
        Thread thread3 = new Thread(myLockTickets, "窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
