package xfuxi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * description：求几个数的所有组合形式
 *
 * @author ajie
 * data 2018/9/4 17:15
 */
public class NumberAssemble {
    public static void main(String[] args) {
        String[] array = new String[]{"1", "2", "3"};
        listAll(Arrays.asList(array), "");
    }

    private static void listAll(List<String> strings, String prefix) {
        if (strings.isEmpty()){
            System.out.println(prefix);
        }
        for (int i = 0; i < strings.size(); i++) {
            List<String> temp = new LinkedList<>(strings);
            listAll(temp, prefix + ((LinkedList) temp).remove(i));
        }
    }
}
