package io;

import java.io.*;
import java.util.*;

/**
 * description：统计两组各10000个数据中出现最多的10个
 *
 * @author ajie
 * data 2018/7/27 9:36
 */
public class NumberCount {
    private static void countNumber(HashMap<Integer, Integer> hashMap, ArrayList<Integer> arrayList1) {
        Iterator<Integer> iterator = arrayList1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (!hashMap.containsKey(i)) {
                hashMap.put(i, 0);
            }
            hashMap.put(i, hashMap.get(i) + 1);
        }
    }

    private static void countNumber1(HashMap<Integer, Integer> hashMap, PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue) {
        Iterator<Map.Entry<Integer, Integer>> iterator1 = hashMap.entrySet().iterator();
        while (iterator1.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator1.next();
            if (priorityQueue.size() < 10) {
                priorityQueue.add(next);
            } else {
                if (next.getValue() > priorityQueue.peek().getValue()) {
                    priorityQueue.remove();
                    priorityQueue.add(next);
                }
            }
        }
    }

    private static void show(PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue) {
        Iterator iterator2 = priorityQueue.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }
    }

    public static void main(String[] args) throws IOException {
        Random random2 = new Random();
        FileWriter fileWriter2 = new FileWriter("2.txt");
        for (int i = 0; i < 10000; i++) {
            fileWriter2.write(random2.nextInt(10000) + "\r\n");
        }
        fileWriter2.close();

        ArrayList<Integer> arrayList1 = new ArrayList<>(10000);
        FileInputStream fileInputStream1 = new FileInputStream("1.txt");
        int ch;
        while ((ch = fileInputStream1.read()) != -1) {
            arrayList1.add(ch);
        }

        ArrayList<Integer> arrayList2 = new ArrayList<>(10000);
        FileInputStream fileInputStream2 = new FileInputStream("2.txt");
        int ch2;
        while ((ch2 = fileInputStream2.read()) != -1) {
            arrayList2.add(ch2);
        }

        HashMap<Integer, Integer> hashMap1 = new HashMap<>(10000);
        countNumber(hashMap1, arrayList1);
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue1 = new PriorityQueue<>(10, Comparator.comparingInt(Map.Entry::getValue));
        countNumber1(hashMap1, priorityQueue1);
        show(priorityQueue1);
        System.out.println("======================");

        HashMap<Integer, Integer> hashMap2 = new HashMap<>(10000);
        countNumber(hashMap2, arrayList2);
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue2 = new PriorityQueue<>(10, Comparator.comparingInt(Map.Entry::getValue));
        countNumber1(hashMap2, priorityQueue2);
        show(priorityQueue2);
        System.out.println("========================");

        Iterator<Map.Entry<Integer, Integer>> integer = priorityQueue1.iterator();
        while (integer.hasNext()) {
            Map.Entry<Integer, Integer> next = integer.next();
            if (next.getValue() > priorityQueue2.peek().getValue()) {
                priorityQueue2.remove();
                priorityQueue2.add(next);
            }
        }
        show(priorityQueue2);
    }
}
