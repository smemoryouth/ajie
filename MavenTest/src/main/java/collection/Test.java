package collection;

import java.util.HashMap;

/**
 * description：
 *
 * @author ajie
 * data 2018/11/23 8:43
 */
public class Test {
    public static void main(String[] args){
        HashMap<Long, Integer> map = new HashMap<>();
        long num = 512234L;
        map.put(num, 1);
        long num2 = 512234L;
        map.put(num2, 2);
        System.out.println(map);
        System.out.println(2);
    }
}
