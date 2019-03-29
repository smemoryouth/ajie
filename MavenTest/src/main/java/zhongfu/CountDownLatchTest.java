package zhongfu;

import java.util.concurrent.CountDownLatch;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/1/29 15:21
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        MyThreadOwn threadOwn = new MyThreadOwn(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(threadOwn).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("耗时：" + (end - start));
    }
}

class MyThreadOwn implements Runnable {

    private CountDownLatch latch;

    MyThreadOwn(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }
}
