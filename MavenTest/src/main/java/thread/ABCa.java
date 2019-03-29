package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description：按顺序调用 condition 的await和signal方法
 *
 * @author ajie
 * data 2018/10/12 17:34
 */
public class ABCa {


    public static void main(String[] args) {
        //循环次数
        final int maxNum = 10;
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition aCondition = reentrantLock.newCondition();
        Condition bCondition = reentrantLock.newCondition();
        Condition cCondition = reentrantLock.newCondition();
        // 这个地方不能用String currentThread="Test";线程内部，要加final，加了final就不能修改引用了
        final String[] currentThread = { "Test" };
        Thread aThread = new Thread(() -> {
            for (int index = 0; index < maxNum; index++) {
                reentrantLock.lock();
                try {
                    while (currentThread[0] != "Test") {
                        aCondition.await();
                    }
                    System.out.print("Test");
                    currentThread[0] = "B";
                    bCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        });


        Thread bThread = new Thread(() -> {
            for (int index = 0; index < maxNum; index++) {
                reentrantLock.lock();
                try {
                    while (currentThread[0] != "B") {
                        bCondition.await();
                    }
                    System.out.print("B");
                    currentThread[0] = "C";
                    cCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }


            }


        });


        Thread cThread = new Thread(() -> {
            for (int index = 0; index < maxNum; index++) {
                reentrantLock.lock();
                try {
                    while (currentThread[0] != "C") {
                        cCondition.await();
                    }
                    System.out.print("C");
                    currentThread[0] = "Test";
                    aCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }


        });
        aThread.start();
        bThread.start();
        cThread.start();


    }


}
