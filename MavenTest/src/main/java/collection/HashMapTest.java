package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * description：hashmap集合
 *
 * @author ajie
 * data 2018/6/22
 */
public class HashMapTest {
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("x", "y");
        hashMap.put("w", "z");
        hashMap.put("s", "z");
        hashMap.put("s", "z");

        System.out.println(hashMap);
        /*hashMap.put(null, "00");
        System.out.println(hashMap);
        hashMap.put(null, "12145");
        hashMap.put("000",null);
        System.out.println(hashMap);*/

        /**
         * 获取键值对
         */
        for (Map.Entry<String, String> next : hashMap.entrySet()) {
            String key = next.getKey();
            String value = next.getValue();
            System.out.println(key + ":" + value);
        }

        /**
         * 获取key
         */
        for (String s1 : hashMap.keySet()) {
            System.out.println(s1);
        }

        /**
         * 获取value
         */
        for (String s : hashMap.values()) {
            System.out.println(s);
        }
    }
}
