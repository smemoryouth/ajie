package collection;

import java.util.*;

/**
 * description：求10w个数中重复次数最少的10个数及其对应的次数
 *
 * @author ajie
 * data 2018/7/9
 */
public class ClassTest3 {
    private static final int NUM = 100000;

    public static void main(String[] args) {
        /**
         * 存储数据
         */
        ArrayList<Integer> arrayList = new ArrayList<>(100000);

        /**
         * 统计数据重复次数
         */
        HashMap<Integer, Integer> hashMap = new HashMap<>(10000);

        /**
         * 大根堆，求得重复次数最少的10个
         */
        PriorityQueue< Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(10, (o1, o2) -> o2.getValue() - o1.getValue());

        /**
         * 10000个数据存储到ArrayList中
         */
        Random random = new Random();
        for (int i = 0; i < NUM; i++) {
            arrayList.add(random.nextInt(10000));
        }

        /**
         * 以重复次数为value，以数据为key，将数据保存到HashMap中
         */
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (!hashMap.containsKey(i)) {
                hashMap.put(i, 0);
            }
            hashMap.put(i, hashMap.get(i) + 1);
        }
        /**
         * 将HashMap的键值对存入PriorityQueue中
         */
        Iterator<Map.Entry<Integer, Integer>> iterator1 = hashMap.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator1.next();
            if (priorityQueue.size() < 10) {
                priorityQueue.add(next);
            } else {
                assert priorityQueue.peek() != null;
                if (next.getValue() < priorityQueue.peek().getValue()) {
                    priorityQueue.remove();
                    priorityQueue.add(next);
                }
            }
        }
        System.out.println("--------");
        System.out.println(priorityQueue.element());
        System.out.println(priorityQueue.poll());
        System.out.println("=======");

        /**
         * 迭代输出PriorityQueue中统计好的键值对
         */
        for (Object aPriorityQueue : priorityQueue) {
            System.out.println(aPriorityQueue);
        }

        /**
         * 求n个数中最小的10个
         */
        /*for (int i = 0; i < NUM; i++) {
            if (priorityQueue.size() < 10) {
                priorityQueue.add(arrayList.get(i));
            } else {
                if (arrayList.get(i) < priorityQueue.peek()) {
                    priorityQueue.remove();
                    priorityQueue.add(arrayList.get(i));
                }
            }
        }
        System.out.println(priorityQueue);*/

    }
}
