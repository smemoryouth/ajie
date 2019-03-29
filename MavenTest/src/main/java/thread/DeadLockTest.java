package thread;

import org.junit.Test;

class MyDeadLock extends Thread {

    private boolean flag;

    MyDeadLock(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (MyLock.OBJB) {
                System.out.println("if locka");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (MyLock.OBJA) {
                    System.out.println("if lockb");
                }
            }

        } else {
            synchronized (MyLock.OBJA) {
                System.out.println("else lockb");
                synchronized (MyLock.OBJB) {
                    System.out.println("else locka");
                }
            }

        }
    }
}

class MyLock {
    static final Object OBJA = new Object();
    static final Object OBJB = new Object();
}

/**
 * description：死锁实例
 *
 * @author ajie
 * data 2018/8/3 17:39
 */
class DeadLockTest {


    public static void main(String[] args) {
        MyDeadLock deadLock1 = new MyDeadLock(true);
        MyDeadLock deadLock2 = new MyDeadLock(false);
        deadLock1.setName("1号");
        deadLock2.setName("2号");
        deadLock1.start();
        deadLock2.start();
    }
}
