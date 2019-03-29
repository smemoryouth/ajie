package collection;

import java.util.*;

/**
 * description：HashMap和Hashtable的数据存入耗时比较
 *
 * @author ajie
 * data 2018/7/4
 */
public class TimeCompareTest {
    private static final int NUM = 10000;

    public static void main(String[] args) {

        /**
         * 1w个数据存入HashMap
         */
        HashMap<Integer, Integer> hashMap = new HashMap<>(10);
        long l2 = System.currentTimeMillis();
        for (int i = 0; i < NUM; i++) {
            hashMap.put(i, i);
        }
        long l3 = System.currentTimeMillis();
        System.out.println(l3 - l2);
        /**
         * 1w个数据存入Hashtable
         */
        Hashtable<Integer, Integer> hashtable = new Hashtable<>();
        long l = System.currentTimeMillis();
        for (int i = 0; i < NUM; i++) {
            hashtable.put(i, i);
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
    }
}
