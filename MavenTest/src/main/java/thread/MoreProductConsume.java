package thread;

import org.junit.Test;

/**
 * 资源
 */
class Resource2 {
    private String name;
    private int count = 1;
    private boolean flag = false;

    /**
     * 输入同步函数
     *
     * @param name 产品名
     */
    synchronized void set(String name) {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.name = name + count;
        count++;
        System.out.println("生产者" + Thread.currentThread().getName() + ":" + this.name);
        flag = true;
        notifyAll();
    }

    /**
     * 输出同步函数
     */
    synchronized void out() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("消费者" + Thread.currentThread().getName() + ":" + this.name);
        flag = false;
        notifyAll();
    }
}

/**
 * 生产者
 */
class Producer implements Runnable {
    private Resource2 r;

    Producer(Resource2 r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.set("tea");
        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {
    private Resource2 r;

    Consumer(Resource2 r) {
        this.r = r;
    }

    @Override
    public void run() {
        while (true) {
            r.out();
        }
    }
}

/**
 * description：多生产者多消费者问题
 *
 * @author ajie
 * data 2018/8/3 22:41
 */
public class MoreProductConsume {

    @Test
    public void moreProductConsumeMain() {
        Resource2 r = new Resource2();
        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);

        Thread t0 = new Thread(pro, "one");
        Thread t1 = new Thread(pro, "two");
        Thread t2 = new Thread(con, "three");
        Thread t3 = new Thread(con, "four");
        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}