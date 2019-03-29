package zhongfu;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/1/11 15:17
 */
public class ConCurrentHashMapTest {
    public static void main(String[] args){
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 3; i++) {
            map.put(i, i);
        }

        Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<Integer, Integer> in = it.next();
            System.out.println(in.getKey() + ":" +in.getValue());
        }
        System.out.println("==================");

        Iterator<Integer> it2 = map.values().iterator();
        while (it2.hasNext()){
            System.out.println(it2.next());
        }
        System.out.println("=====================");

        for (Integer in : map.values()){
            System.out.println(in);
        }

    }
}
