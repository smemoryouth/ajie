package thread;

class SellTickets extends Thread {
    private int tickets = 100;

    SellTickets(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName()
                            + "出售第" + (tickets--) + "张票");
                }
            }
        }
    }
}

/**
 * description：
 *
 * @author ajie
 * data 2018/10/10 21:21
 */
public class Test {

    @org.junit.Test
    public void test() {
        Thread thread1 = new SellTickets("窗口1");
        Thread thread2 = new SellTickets("窗口2");
        Thread thread3 = new SellTickets("窗口3");
        Thread thread4 = new SellTickets("窗口4");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
