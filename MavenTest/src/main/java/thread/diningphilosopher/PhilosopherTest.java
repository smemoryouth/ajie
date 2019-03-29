package thread.diningphilosopher;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description：每个哲学家按照先右后左的顺序拿筷子，则会产生循环等待的死锁
 * 死锁解决思路：改变第五个哲学家拿筷子的顺序，破坏死锁产生的条件
 *
 * @author ajie
 * data 2018/9/14 16:20
 */
public class PhilosopherTest {

    @Test
    public  void test() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        int size = 5;
        int thinkingTime = 0;
        Chopstick[] chopstick = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopstick[i] = new Chopstick(i);
        }
        for (int i = 0; i < size - 1; i++){
            pool.execute(new Philosopher(chopstick[i], chopstick[i + 1], i, thinkingTime));
        }
        pool.execute(new Philosopher(chopstick[0], chopstick[size - 1], size - 1, thinkingTime));
        Thread.sleep(1000);
        pool.shutdown();
    }
}


