package thread.diningphilosopher;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 哲学家就餐问题
 *
 * 解决方案：仲裁者
 * @author 阿劼
 */
public class Philosoper1 implements Runnable {
    private Stick1 fork;
    private int name;
    private Random random;


    public Philosoper1(Stick1 fork, int name) {
        this.fork = fork;
        this.name = name;
        this.random = new Random();
    }

    /**
     * 思考
     */
    private void doThinking() {
        System.out.println("哲学家:"+name+"在思考");
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 吃饭
     */
    private void doEating() {
        System.out.println("哲学家:"+name+"在吃饭");
        try {
            Thread.sleep(random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            doThinking();
            fork.takeFork(name);
            doEating();
            fork.putDownFork(name);
        }
    }

    public static void main(String[] args) {
        Stick1 fork = new Stick1();
        Philosoper1 p0 = new Philosoper1(fork,0);
        Philosoper1 p1 = new Philosoper1(fork,1);
        Philosoper1 p2 = new Philosoper1(fork,2);
        Philosoper1 p3 = new Philosoper1(fork,3);
        Philosoper1 p4 = new Philosoper1(fork,4);

        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(p0);
        threadPool.execute(p1);
        threadPool.execute(p2);
        threadPool.execute(p3);
        threadPool.execute(p4);

        threadPool.shutdown();
    }

}
