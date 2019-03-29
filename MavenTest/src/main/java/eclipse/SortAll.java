package eclipse;

import java.util.Arrays;
import java.util.Random;

/**
 * description：所有的排序方法总结
 *
 * @author ajie
 * data 2018/05/28
 */
public class SortAll {
    private static final int INDEX_MULTIPLE = 2;
    private static final int MIN_NUMBER = 2;


    public static void fun(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubble(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    /**
     * 冒泡排序优化
     *
     * @param arr 要排序的数组
     */
    public static void bubble1(int[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {
            // 设置标志位
            boolean flag = false;
            for (int y = 0; y < arr.length - 1 - x; y++) {
                if (arr[y] > arr[y + 1]) {
                    int tmp = arr[y];
                    arr[y] = arr[y + 1];
                    arr[y + 1] = tmp;
                    // 发生交换则改变标志位的值
                    flag = true;
                }
            }
            // 若是标志位没发生改变，则说明已经有序
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arr 要排序的数组
     */
    public static void insert(int[] arr) {
        for (int x = 1; x < 2; x++) {
            int y;
            int tmp = arr[x];
            for (y = x - 1; y >= 0; y--) {
                if (arr[y] > tmp) {
                    arr[y + 1] = arr[y];
                } else {
                    break;
                }
            }
            arr[y + 1] = tmp;
        }
    }

    /**
     * 选择排序
     *
     * @param arr 要排序的数组
     */
    public static void search(int[] arr) {
        for (int x = 0; x < arr.length - 1; x++) {
            int tmp;
            for (int y = x + 1; y < arr.length; y++) {
                if (arr[y] < arr[x]) {
                    tmp = arr[x];
                    arr[x] = arr[y];
                    arr[y] = tmp;
                }
            }
        }
    }

    /**
     * 希尔排序的实现部分
     *
     * @param arr 要排序的数组
     * @param gap 增量值
     */
    private static void insertSort(int[] arr, int gap) {
        for (int x = gap; x < arr.length; x++) {
            int y;
            int tmp = arr[x];
            for (y = x - gap; y >= 0; y--) {
                if (arr[y] > tmp) {
                    arr[y + gap] = arr[y];
                } else {
                    break;
                }
            }
            arr[y + gap] = tmp;
        }
    }

    /**
     * 希尔排序的增量序列和实现
     *
     * @param arr 要排序的数组
     */
    public static void shell(int[] arr) {
        int[] d = {5, 3, 1};
        for (int x = 0; x < d.length; x++) {
            insertSort(arr, d[x]);
        }
    }

    /**
     * 基本找基准法
     *
     * @param arr   找基准的数组
     * @param start 开始的位置
     * @param end   结束的位置
     * @return 基准的下标
     */
    public static int portion(int[] arr, int start, int end) {
        int tmp = arr[start];
        while (start < end) {
            // 从后往前找比基准小的
            while (arr[end] >= tmp && start < end) {
                end--;
            }
            if (start >= end) {
                break;
            } else {
                arr[start] = arr[end];
            }
            // 从前往后找比基准大的
            while (tmp >= arr[start] && start < end) {
                start++;
            }
            if (start >= end) {
                break;
            } else {
                arr[end] = arr[start];
            }
        }
        arr[start] = tmp;
        return start;
    }

    /**
     * 快速排序的递归实现
     *
     * @param arr   要排序的数组
     * @param start 开始位置
     * @param end   结束位置
     */
    public static void quickSort(int[] arr, int start, int end) {
        // 待排序的数据个数小于MIN_NUMBER，直接用直接插入排序
        if (end - start + 1 <= MIN_NUMBER) {
            System.out.println("=======");
            insert(arr);
            return;
        }
        int par = portion(arr, start, end);
        // 基准左边数据个数大于一个，对左边进行一次找基准排序
        if (par > start + 1) {
            quickSort(arr, start, par - 1);
        }
        // 基准右边数据个数大于一个，对右边进行一次找基准排序
        if (par < end - 1) {
            quickSort(arr, par + 1, end);
        }
    }

    /**
     * 快速排序的非递归实现
     *
     * @param arr 要排序的数组
     */
    public static void quickSort(int[] arr) {
        // 定义一个数据栈
        int[] stack = new int[arr.length];
        int top = 0;
        int low = 0;
        int high = arr.length - 1;
        int par = portion(arr, low, high);
        // 找完一次基准以后若是其左右两边数据个数都大于一个，将新的开始和结束索引一次入栈
        if (par > low + 1) {
            stack[top++] = low;
            stack[top++] = par - 1;
        }
        if (par < high - 1) {
            stack[top++] = par + 1;
            stack[top++] = high;
        }
        // 当栈中存在数据时每次出栈两个数据来进行一次找基准，就类似递归的调用了找基准方法
        while (top > 0) {
            high = stack[--top];
            low = stack[--top];
            par = portion(arr, low, high);
            if (par > low + 1) {
                stack[top++] = low;
                stack[top++] = par - 1;
            }
            if (par < high - 1) {
                stack[top++] = par + 1;
                stack[top++] = high;
            }
        }
    }

    /**
     * 三分取基准法和随机取基准法的交换程序
     *
     * @param arr   交换的数组
     * @param start 开始位置
     * @param end   结束位置
     */
    private static void swap(int[] arr, int start, int end) {
        int tmp = arr[start];
        arr[start] = arr[end];
        arr[end] = tmp;
    }

    /**
     * 随机取基准法的递归实现
     *
     * @param arr   要排序的数组
     * @param start 开始位置
     * @param end   结束位置
     */
    public static void randSort(int[] arr, int start, int end) {
        // 在数据范围内找到一个随机的索引，将该索引对应的数据交换到数据头作为第一次的索引
        Random rand = new Random();
        int num = rand.nextInt(end - start + 1) + start;
        swap(arr, start, num);
        int par = portion(arr, start, end);
        if (par > start + 1) {
            quickSort(arr, start, par - 1);
        }
        if (par < end - 1) {
            quickSort(arr, par + 1, end);
        }
    }

    /**
     * 三分确定开始结束以及中间值有序
     *
     * @param arr   三分数组
     * @param start 开始位置
     * @param end   结束位置
     */
    private static void thirdPortion(int[] arr, int start, int end) {
        // 将数据开始，结束以及中间的值确定有序，将开始的值作为索引
        int mid = start + ((end - start) >>> 1);
        if (arr[mid] > arr[end]) {
            swap(arr, mid, end);
        }
        if (arr[start] > arr[end]) {
            swap(arr, start, end);
        }
        if (arr[start] > arr[mid]) {
            swap(arr, start, mid);
        }
    }

    /**
     * 三分取基准排序
     *
     * @param arr
     * @param start
     * @param end
     */
    public static void thirdSort(int[] arr, int start, int end) {
        // 用交换过的第一个元素为基准调用找基准方法
        thirdPortion(arr, start, end);
        int par = portion(arr, start, end);
        if (par > start + 1) {
            quickSort(arr, start, par - 1);
        }
        if (par < end - 1) {
            quickSort(arr, par + 1, end);
        }
    }

    /**
     * 聚焦相同元素法
     *
     * @param arr   数组
     * @param start 开始位置
     * @param end   结束位置
     * @param par   第一次的基准下标
     * @return 新的基准左右的下标
     */
    private static int[] focus(int[] arr, int start, int end, int par) {
        int parLeft = par - 1;
        int parRight = par + 1;
        for (int x = par - 1; x >= start; x--) {
            // 若一个值与基准相同
            if (arr[x] == arr[par]) {
                // 若是它的位置不在基准左右，交换
                if (x != parLeft) {
                    int tmp = arr[parLeft];
                    arr[parLeft] = arr[x];
                    arr[x] = tmp;
                    parLeft--;
                } else {
                    parLeft--;
                }
            }
        }
        for (int x = par + 1; x <= end; x++) {
            if (arr[x] == arr[par]) {
                if (x != parRight) {
                    int tmp = arr[parRight];
                    arr[parRight] = arr[x];
                    arr[x] = tmp;
                    parRight++;
                } else {
                    parRight++;
                }
            }
        }
        int[] array = new int[2];
        array[0] = arr[parLeft];
        array[1] = arr[parRight];
        return array;
    }

    /**
     * 聚焦相同元素的快速排序
     *
     * @param arr   要排序的数组
     * @param start 开始位置
     * @param end   结束位置
     */
    public static void focusAlikeQuickSort(int[] arr, int start, int end) {
        int par = portion(arr, start, end);
        int[] array = focus(arr, start, end, par);
        if (par > start + 1) {
            quickSort(arr, start, array[0]);
        }
        if (par < end - 1) {
            quickSort(arr, par + 1, array[1]);
        }
    }

    /**
     * 堆排序的一次调整
     *
     * @param arr   要调整的数组
     * @param start 开始位置
     * @param end   结束位置
     */
    private static void adjust(int[] arr, int start, int end) {

        int tmp = arr[start];
        for (int x = start * INDEX_MULTIPLE + 1; x <= end; x = x * INDEX_MULTIPLE + 1) {
            if (x < end && arr[x] < arr[x + 1]) {
                x++;
            }
            if (arr[x] > tmp) {
                arr[start] = arr[x];
                start = x;
            } else {
                break;
            }
        }
        arr[start] = tmp;
    }

    /**
     * 堆排序实现
     *
     * @param arr 要排序的数组
     */
    public static void heapSort(int[] arr) {
        for (int i = (arr.length - 1 - 1) / INDEX_MULTIPLE; i >= 0; i--) {
            adjust(arr, i, arr.length - 1);
        }

        for (int j = 0; j < arr.length - 1; j++) {
            int tmp = arr[0];
            arr[0] = arr[arr.length - 1 - j];
            arr[arr.length - 1 - j] = tmp;
            adjust(arr, 0, arr.length - 1 - 1 - j);
        }
    }

    /**
     * 归并排序的一次分组排序
     *
     * @param arr 要排序的数组
     * @param gap 分组的长度
     */
    private static void merge(int[] arr, int gap) {
        int start1 = 0;
        int end1 = start1 + gap - 1;
        int start2 = end1 + 1;
        int end2 = start2 + gap - 1 < arr.length - 1 ? start2 + gap - 1 : arr.length - 1;
        int[] tmp = new int[arr.length];
        int i = 0;
        while (start2 < arr.length) {
            while (start1 <= end1 && start2 <= end2) {
                if (arr[start1] < arr[start2]) {
                    tmp[i++] = arr[start1++];
                } else {
                    tmp[i++] = arr[start2++];
                }
            }
            while (start1 <= end1) {
                tmp[i++] = arr[start1++];
            }
            while (start2 <= end2) {
                tmp[i++] = arr[start2++];
            }
            start1 = end2 + 1;
            end1 = start1 + gap - 1;
            start2 = end1 + 1;
            end2 = start2 + gap - 1 < arr.length - 1 ? start2 + gap - 1 : arr.length - 1;
        }
        while (start1 < arr.length) {
            tmp[i++] = arr[start1++];
        }
        System.arraycopy(tmp, 0, arr, 0, tmp.length);
    }

    /**
     * 归并的分组实现
     *
     * @param arr 要排序的数组
     */
    public static void mergeSortTest(int[] arr) {
        for (int i = 1; i < arr.length; i *= INDEX_MULTIPLE) {
            merge(arr, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {314, 298, 508, 123, 486, 145};
//        mergeSortTest(arr);
//        quickSort(arr, 0, arr.length -1);
        insert(arr);
        System.out.println(Arrays.toString(arr));
    }
}

