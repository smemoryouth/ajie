package thread.diningphilosopher;

/**
 * description：
 *
 * @author ajie
 * data 2018/9/14 16:19
 */
public class Philosopher implements Runnable {
    /**
     * 右边筷子
     */
    private Chopstick right;
    /**
     * 左边筷子
     */
    private Chopstick left;
    /**
     * 哲学家编号
     */
    private int index;
    /**
     * 思考时间
     */
    private int thinkTime;

    Philosopher(Chopstick right, Chopstick left, int index, int thinkingTime) {
        this.right = right;
        this.left = left;
        this.index = index;
        this.thinkTime = thinkingTime;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                thinking();
                System.out.println(this + "思考");
                right.take();
                System.out.println(this + "拿右边筷子");
                left.take();
                System.out.println(this + "拿左边筷子");
                System.out.println(this + "吃");
                right.drop();
                left.drop();
                thinking();
                System.out.println(this + "放下筷子思考");
            }
        } catch (InterruptedException e) {
            System.out.println(this + "InterruptedException");
        }
    }

    private void thinking() throws InterruptedException {
        Thread.sleep(thinkTime);
    }

    @Override
    public String toString() {
        return "哲学家 [" + index + "]";
    }
}

