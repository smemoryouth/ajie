package thread;

/**
 * description：
 *
 * @author ajie
 * data 2018/10/12 16:36
 */
public class ABC implements Runnable {
    public Object AB ;
    public Object BC ;
    public Object CA ;

    public ABC (Object AB, Object BC, Object CA) {
        this.AB = AB;
        this.BC = BC;
        this.CA = CA;
    }


    @Override
    public void run() {
        synchronized (this) {
        int i = 0;
        while ( i< 10) {
            String name = Thread.currentThread().getName();
            try {
                if (name.equals("Test")) {
                    synchronized (CA) {
                        CA.wait();
                        System.out.print("Test");
                        synchronized (AB) {

                            AB.notify();
                        }
                    }
                } else if (name.equals("B")) {
                    synchronized (AB) {
                        AB.wait();
                        System.out.print("B");
                        synchronized (BC) {

                            BC.notify();
                        }
                    }
                } else if (name.equals("C")) {
                    synchronized (BC) {
                        BC.wait();
                        System.out.print("C");
                        synchronized (CA) {

                            CA.notify();
                        }
                    }
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }
    }

    public static void main(String[] args) {
        Object AB = new Object();
        Object BC = new Object();
        Object CA = new Object();
        ABC abc = new ABC(AB, BC, CA);
        new Thread(abc, "Test").start();
        new Thread(abc, "B").start();
        new Thread(abc, "C").start();
//        synchronized (CA) {
//            CA.notifyAll();
//        }
        CA.notify();
//        AB.notify();
//        BC.notify();

    }
}
