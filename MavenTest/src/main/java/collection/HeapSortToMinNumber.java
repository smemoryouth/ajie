package collection;


import java.util.ArrayList;
import java.util.Random;

/**
 * description：
 *
 * @author ajie
 * data 2018/7/7
 */
public class HeapSortToMinNumber {
    /**
     * 数据个数
     */
    private static final int MAXNUM = 100000;
    /**
     * 子父位置计算因子
     */
    private static final int INDEX_MULTIPLE = 2;
    /**
     * 要取得最小值的个数
     */
    private static final int MIN_NUMBER = 10;

    /**
     * 堆排序的一次调整
     *
     * @param arr   要调整的集合
     * @param start 开始位置
     * @param end   结束位置
     */
    public static void adjust(ArrayList<Integer> arr, int start, int end) {

        int tmp = arr.get(start);
        for (int x = start * INDEX_MULTIPLE + 1; x <= end; x = x * INDEX_MULTIPLE + 1) {
            if (x < end && arr.get(x) < arr.get(x + 1)) {
                x++;
            }
            if (arr.get(x) > tmp) {
                arr.set(start, arr.get(x));
                start = x;
            } else {
                break;
            }
        }
        arr.set(start, tmp);
    }

    /**
     * 堆排序实现
     *
     * @param arr 要排序的集合
     */
    public static void heapSort(ArrayList<Integer> arr) {
        for (int i = (arr.size() - 1 - 1) / INDEX_MULTIPLE; i >= 0; i--) {
            adjust(arr, i, arr.size() - 1);
        }

        for (int j = 0; j < arr.size() - 1; j++) {
            Integer tmp = arr.get(0);
            arr.set(0, arr.get(arr.size() - 1 - j));
            arr.set(arr.size() - 1 - j, tmp);
            adjust(arr, 0, arr.size() - 1 - 1 - j);
        }
    }

    /**
     * 取得10个最小值
     * @param arr
     */
    public static void getMinTenNumber(ArrayList<Integer> arr) {
        for (int i = 0; i < MIN_NUMBER; i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>(100000);
        Random random = new Random();
        for (int i = 0; i < MAXNUM; i++) {
            array.add(random.nextInt(MAXNUM));
        }
        long l = System.currentTimeMillis();
        heapSort(array);
        //System.out.println((array));
        getMinTenNumber(array);
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
    }
}
