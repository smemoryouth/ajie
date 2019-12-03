package test0;


import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class MyGirlFriend implements Runnable {
    private String name;
    private int age;

    MyGirlFriend(){}

    private MyGirlFriend(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()
                    + "创造" + new MyGirlFriend("myGirlFriend"
                    + new Random().nextInt(1000), 18));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return name + ":" + age + "岁";
    }
}

/**
 * @author 阿劼
 */
public class GirlFriendFactory {
    @Test
    public void createGirlFriend() {
        ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(3,
                new BasicThreadFactory.Builder()
                .namingPattern("myGirlFriendCreateThread-%d").daemon(false).build());
        int threadNumber = 5;
        int girlNumber = 0;
        while (girlNumber < 1000) {
            for (int i = 0; i < threadNumber; i++) {
                pool.submit(new MyGirlFriend());
            }
            girlNumber++;
        }
        pool.shutdown();
    }
}
