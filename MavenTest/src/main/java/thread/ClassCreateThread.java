package thread;

class ImThread implements Runnable{
    @Override
    public void run() {
        System.out.println("用户线程开始");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        System.out.println("用户线程结束");
    }
}

class ExThread extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class MyDeamon extends Thread{
    @Override
    public void run() {
        System.out.println("守护线程开始");
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        System.out.println("守护线程结束");
    }
}
/**
 * description：
 *
 * @author ajie
 * data 2018/8/6 10:18
 */
public class ClassCreateThread {
    public static void main(String[] args) throws InterruptedException {
        ImThread imThread = new ImThread();
        Thread thread = new Thread(imThread, "用户线程");
        MyDeamon thread1 = new MyDeamon();
        MyDeamon thread2 = new MyDeamon();
        thread1.setName("守护线程1");
        thread2.setName("守护线程2");
        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread.start();
        thread1.start();
        thread2.start();

//        ExThread thread3 = new ExThread();
//        thread1.setName("two");
//        thread1.start();
////        System.out.println(thread1.getState());
//        thread1.join();
//        System.out.println(thread1.getState());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++) {
//                    System.out.println(Thread.currentThread().getName() + ":" + i);
//                }
//            }
//        }, "three").start();

    }
}
