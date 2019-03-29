package thread;


import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class Sell1 extends Thread {

    private int tickets = 100;
    Object obj = new Object();

    Sell1(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName()
                            + "出售第" + (tickets--) + "张票");
                }
            }
        }
    }
}

class Sell2 implements Runnable {

    private int tickets = 100;

    private final Object object = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()
                            + "出售第" + (tickets--) + "张票");
                }
            }
        }
    }
}

class Sell3 implements Callable<Integer> {

    private int tickets = 100;

    @Override
    public Integer call() {
        while (true) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName()
                        + "出售第" + (tickets--) + "张票");
            }
        }
    }
}

/**
 * description：三种方式比较
 *
 * @author ajie
 * data 2018/8/2 19:10
 */
public class CreateThreadCompare {

    public static void main(String[] args) {
        createThread();
//        createRunnable();
//        createCallable();
    }

    private static void createThread() {
        Sell1 thread1 = new Sell1("窗口1");
        Sell1 thread2 = new Sell1("窗口2");
        Sell1 thread3 = new Sell1("窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void createRunnable() {
        Sell2 sell = new Sell2();
        Thread thread1 = new Thread(sell, "窗口1");
        Thread thread2 = new Thread(sell, "窗口2");
        Thread thread3 = new Thread(sell, "窗口3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static void createCallable() {
        Sell3 sell = new Sell3();
        FutureTask<Integer> futureTask1 = new FutureTask<>(sell);
        FutureTask<Integer> futureTask2 = new FutureTask<>(sell);
        FutureTask<Integer> futureTask3 = new FutureTask<>(sell);
        Thread thread1 = new Thread(futureTask1, "窗口1");
        Thread thread2 = new Thread(futureTask2, "窗口2");
        Thread thread3 = new Thread(futureTask3, "窗口3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
