package collection;

import java.util.*;

/**
 * description：求10000个数字的重复个数
 * 启示点：Integer integer = hashMap.get(arrayList1.get(i));
 *        hashMap.put(arrayList1.get(i), integer == null ? 1 : integer + 1);
 *        遍历统计迭代器
 *        输出迭代器
 * @author ajie
 * data 2018/7/2
 */
public class ClassTest {
    public static final int NUM = 10000;

    public static void main(String[] args) {
        // 保存随机数
        ArrayList<Integer> arrayList1 = new ArrayList<>(10000);
        Random random = new Random();
        for (int i = 0; i < NUM; i++) {
            arrayList1.add(random.nextInt(10));
        }

        HashMap<Integer, Integer> hashMap = new HashMap<>(10);
        for (int i = 0; i < arrayList1.size(); i++) {
            Integer integer = hashMap.get(arrayList1.get(i));
            hashMap.put(arrayList1.get(i), integer == null ? 1 : integer + 1);
        }
        // 随机数作为key，通过value统计重复次数
        Iterator<Integer> integer1 = arrayList1.iterator();
        while (integer1.hasNext()){
            Integer i = integer1.next();
            if (!hashMap.containsKey(i)) {
                    hashMap.put(i, 0);
            }
            hashMap.put(i, hashMap.get(i) + 1);
        }

        // 通过entrySet方法迭代输出键值对
        Iterator<Map.Entry<Integer, Integer>> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
             Map.Entry<Integer, Integer> next = iterator.next();
            Integer key = next.getKey();
            Integer value = next.getValue();
            System.out.println(key + ":" + value);
        }
    }
}
