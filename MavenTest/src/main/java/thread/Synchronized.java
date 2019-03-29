package thread;

class Count {
    private int count;

    Count(int count) {
        this.count = count;
    }

    void input(int count) {
        this.count += count;
    }

    void output(int count) {
        this.count -= count;
    }

    int getCount() {
        return count;
    }
}

/**
 * description：synchronized测试，加在代码块上，锁的是当前代码块，
 * 加在方法上锁的是对象实例，加在静态方法上锁的是类，静态存在于方法区
 *
 * @author ajie
 * data 2018/9/10 20:40
 */
public class Synchronized {
    public static void main(String[] args) {
        Count account = new Count(100);
        System.out.println("起始余额为：" + account.getCount());
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (account) {
                        account.input(100);
                        System.out.println("线程1加入100后余额为：" + account.getCount());
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (account) {
                        account.input(200);
                        System.out.println("线程2加入200后余额为：" + account.getCount());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (account) {
                        if (account.getCount() >= 1200) {
                            account.output(1200);
                            System.out.println("线程3支出1200后余额为：" + account.getCount());
                        } else {
                            System.out.println("线程3扣款时余额不足");
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
