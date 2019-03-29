package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * description：求10000个数中第一个重复的数
 *
 * @author ajie
 * data 2018/7/4
 */
public class ClassTest2 {
    private static final int NUM = 10000;

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < NUM; i++) {
            arrayList.add(random.nextInt(10));
        }
        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        Iterator<Integer> integer = arrayList.iterator();
        while (integer.hasNext()) {
            Integer i = integer.next();
            if (linkedHashMap.containsKey(i)) {
                System.out.println(i);
                return;
            }else{
                linkedHashMap.put(i, 0);
            }
        }
        /*LinkedHashMap<Integer,Integer> linkedHashMap1 = new LinkedHashMap<>();
        for (int i = 0; i < arrayList.size(); i++) {
            Integer integer1 = linkedHashMap1.get(arrayList.get(i));
            linkedHashMap1.put(arrayList.get(i), integer1 == null ? 1 : integer1 + 1);
            if(linkedHashMap1.containsKey(arrayList.get(i))) {
                System.out.println(arrayList.get(i));
                break;
            }
        }*/
    }
}
