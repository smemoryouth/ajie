package zhongfu;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description：volatile的验证，使用countDownLatch
 *
 * @author ajie
 * data 2019/1/4 14:41
 */
public class VolatileTest {
    private static CountDownLatch countDownLatch = new CountDownLatch(10);
    public AtomicInteger inc = new AtomicInteger();

    public void increase(){
        inc.getAndIncrement();
    }

    public static void main(String[] args){
        final VolatileTest volatileTest = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    volatileTest.increase();
                    countDownLatch.countDown();
                }
            }).start();
        }

        try{
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(volatileTest.inc.get());
    }
}
