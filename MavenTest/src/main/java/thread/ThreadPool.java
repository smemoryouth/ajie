package thread;



import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;

import java.util.concurrent.*;

class MyRunnable1 implements Runnable {
    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName());

    }
}

class MyCallable2 implements Callable<Integer> {

    @Override
    public Integer call() {
        System.out.println(Thread.currentThread().getName() + " -100");
        return 0;
    }
}

/**
 * description：利用Executors类创建线程池
 * Class ForkJoinPool：since7，提供了在一个任务里指定fork和join操作的机制和控制任务状态的方法，
 * 实现了工作窃取算法：某个线程从其他队列中窃取任务来执行。简单说就是反正闲着，没事找事。
 * Class  ScheduledThreadPoolExecutor：用于定时调度的实现，替换了Timer类
 *
 * @author ajie
 * data 2018/8/4 15:44
 */
public class ThreadPool {

    /**
     * 创建无大小限制的线程池
     */
    @Test
    private void executorsMethod1() {
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new MyRunnable1());
        pool.execute(new MyRunnable1());
        pool.shutdown();
    }

    /**
     * 创建固定大小的线程池
     */
    @Test
    private void executorsMethod2() {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(new MyRunnable1());
        pool.submit(new MyRunnable1());
        pool.shutdown();
    }

    /**
     * 创建单线程池
     */
    @Test
    private void executorsMethod3() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.submit(new MyRunnable1());
        pool.shutdown();
    }

    /**
     * 创建定时调度池
     */
    @Test
    private void executorsMethod4() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
        pool.scheduleWithFixedDelay(new MyRunnable1(), 3, 2, TimeUnit.SECONDS);
    }

    /**
     * 阿帕奇线程池模板
     */
    @Test
    private void executorsMethod5() {
        ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(3,
                new BasicThreadFactory.Builder().namingPattern("mypool-%d").daemon(false).build());
        pool.submit(new MyRunnable1());
        pool.submit(new MyCallable2());
        pool.shutdown();
    }

    /**
     * ThreadPoolExecutor使用
     */
    @Test
    private  void fun() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        pool.execute(new MyRunnable1());
        pool.execute(new MyRunnable1());
        pool.execute(new MyRunnable1());
        pool.execute(new MyRunnable1());
        pool.execute(new MyRunnable1());
        pool.shutdown();
    }


}

