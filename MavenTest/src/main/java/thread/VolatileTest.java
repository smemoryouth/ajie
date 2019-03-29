package thread;

import org.junit.Test;

/**
 * description：volatile关键字的使用
 * 作用：1.保证内存的可见性
 *      2.阻止指令的重排序
 *
 * @author ajie
 * data 2018/8/8 10:57
 */
public class VolatileTest {
    private static volatile boolean flag = false;

    @Test
    public void test(){
        Thread thread = new Thread(() -> {
            int i = 1;
            while (!flag){
                i++;
            }
            System.out.println(i);
        });
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("main thread over");
    }
}
