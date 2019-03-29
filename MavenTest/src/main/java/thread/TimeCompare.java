package thread;

import java.util.Hashtable;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * description：大批量数据插入HashMap，HashTable，ConcurrentHashMap耗时比较
 * 单线程情况下HashTable最短，ConcurrentHashMap时间最长
 * 多线程中HashMap不参与比较，ConcurrentHashMap耗时较HashTable短
 *
 * @author ajie
 * data 2018/9/21 19:21
 */
public class TimeCompare {
    public static void main(String[] args) {
        Random random = new Random();

//        HashMap<Integer, Integer> hashMap = new HashMap<>(10);
//        for (int i = 0; i < 200000; i++) {
//            hashMap.put(i, random.nextInt(1000));
//        }

//        System.out.println("hashmap:" + (l2 - l1));

//        l1 = System.currentTimeMillis();
        Hashtable<Integer, Integer> hashtable = new Hashtable<>(10);
        long l1 = System.currentTimeMillis();

        hashTableInsert(random, hashtable, 5000000, 4);
//        l2 = System.currentTimeMillis();
        long l2 = System.currentTimeMillis();
        System.out.println("hashtable:" + (l2 - l1));

        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>(10);
        l1 = System.currentTimeMillis();
        concurrentHashMapInsert(random, concurrentHashMap, 5000000, 4);
        l2 = System.currentTimeMillis();
        System.out.println("concurrenthashmap:" + (l2 - l1));
    }

    private static void concurrentHashMapInsert(Random random, ConcurrentHashMap<Integer, Integer> concurrentHashMap, int limitNumber, int limitThread) {
        for (int i = 0; i < limitThread; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < limitNumber; i++) {
                        concurrentHashMap.put(i, random.nextInt(1000));
                    }
                }
            }).start();
        }
    }

    private static void hashTableInsert(Random random, Hashtable<Integer, Integer> hashtable, int limitNumber, int limitThread) {
        for (int i = 0; i < limitThread; i++) {


            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < limitNumber; j++) {
                        hashtable.put(j, random.nextInt(1000));
                    }
                }
            }).start();
        }
    }

    public static void main1(String[] args){
        CountDownLatch countDownLatch1 = new CountDownLatch(5);
        CountDownLatch countDownLatch2 = new CountDownLatch(5);
    }
}
