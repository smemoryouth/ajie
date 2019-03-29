package eclipse;

import java.util.*;

/**
 * description：二分法
 *
 * @author ajie
 * data 2018/6/13
 */
public class BinarySearch {

    public static int halfSearch(int[] array, int from, int to, int key) {

        if (from > to) {
            return -1;
        }
        int mid = (from + to) >>> 1;
        if (array[mid] == key) {
            return mid;
        } else if (key < array[mid]) {
            return halfSearch(array, from, mid - 1, key);
        } else{
            return halfSearch(array, mid + 1, to, key);
        }
    }


    public static void main(String[] args) {
        int[] array = new int[] {454, 78, 21, 44, 5, 54, 25};
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(halfSearch(array, 0, array.length - 1, 54));

    }

}
