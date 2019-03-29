package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * description: 考试题2
 *
 * @author ajie
 * data 2018/8/1 10:30
 */
public class CollectionTestTwo {
    public static void main(String[] args) {
        List<List<Integer>> list1 = new ArrayList<>();
        List<List<Integer>> list2 = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>();
        a.add(4);
        a.add(8);
        list1.add(a);

        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(9);
        a2.add(13);
        list1.add(a2);

        ArrayList<Integer> b = new ArrayList<>();
        b.add(6);
        b.add(12);
        list2.add(b);
        interSection(list1, list2);
    }

    /**
     * 求交集过程
     *
     * @param collects1 集合1
     * @param collects2 集合2
     */
    private static void interSection(List<List<Integer>> collects1,
                                     List<List<Integer>> collects2) {
        // 参数检验
        if (!(isValid(collects1) && isValid(collects2))) {
            return;
        }
        for (List<Integer> next : collects1) {
            for (List<Integer> next2 : collects2) {
                interMatching(next, next2);
            }
        }
    }

    /**
     * 具体实现过程
     *
     * @param list1
     * @param list2
     */
    private static void interMatching(List<Integer> list1, List<Integer> list2) {
        Integer a1 = list1.get(0);
        Integer a2 = list1.get(1);
        Integer b1 = list2.get(0);
        Integer b2 = list2.get(1);
        if (a1 > b2 || b1 > a2) {
            return;
        }
        if (a1.equals(b2)) {
            System.out.print("[" + a1 + "] ");
        }
        if (a2.equals(b1)) {
            System.out.print("[" + a2 + "] ");
        }
        if (a1 > b1) {
            System.out.print("[" + a1 + ",");
            if (b2 < a2) {
                System.out.print(b2 + "] ");
            } else {
                System.out.print(a2 + "] ");
            }
        }
        if (b1 >= a1) {
            System.out.print("[" + b1 + ",");
            if (b2 < a2) {
                System.out.print(b2 + "] ");
            } else {
                System.out.print(a2 + "] ");
            }
        }
    }

    /**
     * 参数检验
     *
     * @param collects 参数
     * @return boolean
     */
    private static boolean isValid(List<List<Integer>> collects) {
        if (collects.size() < 1) {
            System.out.println("the two collections have not union！");
            return false;
        }
        for (List<Integer> next : collects) {
            if (next.size() != 2) {
                System.out.println("error capacity!");
                return false;
            }
            if (next.get(0) > next.get(1)) {
                System.out.println("error sequence!");
                return false;
            }
        }
        return true;
    }
}
