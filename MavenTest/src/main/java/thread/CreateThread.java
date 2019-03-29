package thread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 方式一测试加入、礼让、守护、优先级
 */
class MyThread extends Thread {

    MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ":" + i);
//            Thread.yield();
        }
    }
}

/**
 * 方式一测试中断
 */
class MyThread1 extends Thread {

    MyThread1(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("start " + new Date());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("interrupt " + new Date());
        }
        System.out.println("end " + new Date());
    }
}

/**
 * 方式二
 */
class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

/**
 * 方式三
 */
class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
            sum += i;
        }
        return sum;
    }
}

/**
 * description：创建线程的三种方式
 *
 * @author ajie
 * data 2018/8/2 17:53
 */
public class CreateThread {
    public static void main(String[] args) {
        createThread();
        createRunnable();
        createCallable();
        createInin();
    }

    /**
     * 第一种方式的测试
     */
    private static void createThread() {
//        System.out.println(Thread.currentThread().getName());
//        myThread thread = new myThread();
//        myThread thread1 = new myThread();
//        thread.setName("one");
//        thread1.setName("two");

        MyThread1 thread = new MyThread1("one");
        MyThread thread1 = new MyThread("two");


        // 设置优先级
//        thread.setPriority(2);
//        thread1.setPriority(6);

        // 设置守护线程
//        thread.setDaemon(true);
//        thread1.setDaemon(true);

        thread.start();
//        thread1.start();

        // 线程中断
//        thread1.stop();
        try {
            Thread.sleep(3000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        Thread.currentThread().setName("zero");
//        for (int i = 0; i < 5; i++) {
//            System.out.println(Thread.currentThread().getName() + ":" + i);
//        }
    }

    /**
     * 第二种方式的测试
     */
    private static void createRunnable() {
        MyRunnable myRunnable = new MyRunnable();
//        Thread thread1 = new Thread(myRunnable);
//        Thread thread2 = new Thread(myRunnable);
//        thread1.setName("one");
//        thread2.setName("two");
        Thread thread1 = new Thread(myRunnable, "one");
        Thread thread2 = new Thread(myRunnable, "two");
        thread1.start();
        thread2.start();
    }

    private static void createCallable() {
        MyCallable myCallable = new MyCallable();

        // 用FutureTask包装MyCallable对象
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);

        // 以FutureTask对象为参数创建线程
        Thread thread = new Thread(futureTask, "one");
        thread.start();

        try {
            // 接收线程运行后结果
            Integer sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    private static void  createInin(){
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }, "four");
    }
}
