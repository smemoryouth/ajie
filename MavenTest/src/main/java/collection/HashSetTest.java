package collection;

import java.util.*;

/**
 * description：出现的数和第一次重复的数
 *
 * @author ajie
 * data 2018/7/11
 */
public class HashSetTest {
    private static final int NUMBER = 100000;

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(100000);
        HashSet<Integer> hashSet = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < NUMBER; i++) {
            arrayList.add(random.nextInt(100));
        }
        System.out.println(arrayList);
        for (Integer i : arrayList) {
            if (!hashSet.contains(i)) {
                hashSet.add(i);
            } else {
                System.out.println(i);
                break;
            }
        }
    }
}
