package io;

import java.io.*;
import java.util.*;

/**
 * description：初始化一个1000条的消费记录文本，统计消费次数最多的人和他的单笔消费最大金额
 *
 * @author ajie
 * data 2018/7/31 12:39
 */
public class ConsumeCount {
    /**
     * 记录条数
     */
    public static final int COUNT = 30;

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(10);
        list.add("张三");
        list.add("李四");
        list.add("王五");
        list.add("赵六");
        list.add("小七");
        init("000.txt", list);
        count("000.txt");
    }

    /**
     * 统计
     */
    public static void count(String path) {
        // 将文本中的信息存储到HashMap中，其中key为姓名，value为统计消费记录的ArrayList
        HashMap<String, ArrayList<Integer>> hashMap = new HashMap<>(10);
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String ch;
            while ((ch = bufferedReader.readLine()) != null) {
                String[] str = ch.split(",");
                if (!hashMap.containsKey(str[0])) {
                    Integer integer = Integer.parseInt(str[1]);
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(integer);
                    hashMap.put(str[0], integers);
                } else {
                    Integer integer = Integer.parseInt(str[1]);
                    hashMap.get(str[0]).add(integer);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, ArrayList<Integer>> next : hashMap.entrySet()) {
            String per = next.getKey();
            ArrayList<Integer> arr = next.getValue();
            System.out.println(per + ":" + arr);
        }

        // 根据ArrayList的长度找到消费次数最多的人
        int maxLength = -1;
        String people = null;
        for (Map.Entry<String, ArrayList<Integer>> next : hashMap.entrySet()) {
            String p = next.getKey();
            ArrayList<Integer> arrayList = next.getValue();
            if (maxLength < arrayList.size()) {
                maxLength = arrayList.size();
                people = p;
            }
        }

        // 将消费最多的人的消费记录通过优先级队列找到其单笔消费最多的一次
        ArrayList<Integer> arrayList = hashMap.get(people);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(1);
        for (int i = 0; i < maxLength; i++) {
            if (priorityQueue.size() < 1) {
                priorityQueue.add(arrayList.get(i));
            } else {
                assert priorityQueue.peek() != null : ("error!");
                if (priorityQueue.peek() < arrayList.get(i)) {
                    priorityQueue.remove();
                    priorityQueue.add(arrayList.get(i));
                }
            }
        }

        // 输出
        System.out.println(people + " : " + priorityQueue.remove());
    }

    /**
     * 文本初始化
     */
    private static void init(String path, List<String> list) {

        // 参数校验
        File file = new File(path);
        if (!(file.isFile())) {
            System.out.println("error file path!");
            return;
        }
        if (list.size() < 1) {
            System.out.println("null list!");
            return;
        }

        FileOutputStream fileOutputStream = null;
        int num = list.size();
        Random random = new Random();
        try {
            fileOutputStream = new FileOutputStream(path);
            for (int i = 0; i < COUNT; i++) {
                try {
                    String sb = list.get(random.nextInt(num)) + ","
                            + random.nextInt(1000) + "\r\n";
                    fileOutputStream.write(sb.getBytes());
                    fileOutputStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
