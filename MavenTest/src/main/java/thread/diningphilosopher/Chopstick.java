package thread.diningphilosopher;

/**
 * description：
 *
 * @author ajie
 * data 2018/9/14 16:15
 */
public class Chopstick {
    private int index;
    private boolean use = false;

    public Chopstick(int index) {
        super();
        this.index = index;
    }

    @Override
    public String toString() {
        return "筷子 [" + index + "]";
    }

    /**
     * 获取筷子
     * @throws InterruptedException
     */
    synchronized void take() throws InterruptedException {
        while (use) {
            wait();
        }
        use = true;
    }

    /**
     * 释放筷子
     */
    synchronized void drop() {
        use = false;
        notifyAll();
    }
}
