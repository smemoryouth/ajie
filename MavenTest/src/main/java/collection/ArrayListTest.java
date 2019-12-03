package collection;

import java.util.ArrayList;

/**
 * description：ArrayList接口的说明及交集并集差集
 *
 * @author ajie
 * data 2018/6/13
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> array1 = new ArrayList<>();
        ArrayList<Integer> array2 = new ArrayList<>();
        ArrayList<Integer> array3 = new ArrayList<>();

        array1.add(3);
        array1.add(4);
        array1.add(2);
        array1.add(5);

        array2.add(3);
        array2.add(4);
        array2.add(5);
//        union(array1, array2);
//        getTogether(array1, array2);
//        getUn(array1, array2);
        //System.out.println(array1);
//        System.out.println(array2);
        //System.out.println(array1);
        /*System.out.println(array1.remove(0));
        System.out.println(array1.set(0,10));*/
//        union(array1, array2);
//        getTogether(array1, array2);
//        getUn(array1, array2);
        /*Long l = System.currentTimeMillis();
        for(int x = 0; x < 300000; x++){
            array1.add(x);
        }
        Long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);

        Long l2 = System.currentTimeMillis();
        for(int x = 0; x < 300000; x++){
            array2.add(x);
        }
        Long l3= System.currentTimeMillis();
        System.out.println(l3 - l2);*/
        for (int x = 0; x < array2.size(); x++) {
            if (!array1.contains(array2.get(x))) {
                array1.add(array2.get(x));
            } else {
                array3.add(array2.get(x));
            }
        }
        System.out.println(array3);
        //array2.removeAll(array3);
        //System.out.println(array1);
        /*System.out.println(array2);
        System.out.println(array3);*/
        /*System.out.println(array1.remove(0));
        System.out.println(array1.remove(array1.get(0)));
        System.out.println(array1.remove(null));

        System.out.println(array1);*/
//        Iterator<Integer> integer = array1.iterator();
//
//        while (integer.hasNext()) {
//
//            System.out.println(integer.next());
//            integer.next();
//            integer.remove();
//        }
//        System.out.println("======");
//        for(Integer integer1 : array1){
//            System.out.print(integer1 + " ");
//        }
    }

    /**
     * 并集
     *
     * @param array1
     * @param array2
     */
    public static void union(ArrayList<Integer> array1, ArrayList<Integer> array2) {
        array1.addAll(array2);
        System.out.println(array1);
    }

    /**
     * 交集
     *
     * retainAll() 删除array1中不与array2相同的元素
     * @param array1
     * @param array2
     */
    public static void getTogether(ArrayList<Integer> array1, ArrayList<Integer> array2) {
        array1.retainAll(array2);
        System.out.println(array1);

    }

    /**
     * 差集
     *
     * @param array1 0
     * @param array2 0
     */
    public static void getUn(ArrayList<Integer> array1, ArrayList<Integer> array2) {
        array2.remove(array1);
        System.out.println(array1);
    }
}
