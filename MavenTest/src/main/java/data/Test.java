package data;

import java.util.Arrays;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/2/26 10:09
 */
public class Test {
    public static void main(String[] args) {
//        fun(4);
        int[] arr = {2, 12, 15, 24, 33};
//        System.out.println(midSearch(arr, 0, arr.length - 1, 13));
        int[] arr1 = {1, 2, 3};
//        int[] arr2 = {52, 21, 54, 45, 2};
//        find(arr2, 0, arr2.length - 1);
//        System.out.println(findK(arr, 0, arr2.length - 1, 3));
    }


    private static int findK(int[] arr, int start, int end, int k) {
        if (start == end && k == 1) {
            return arr[start];
        }
        int index = partion(arr, start, end);
        int pos = index - start + 1;
        if (k <= pos) {
            return findK(arr, start, index, k);
        } else {
            return findK(arr, index, end, k);
        }
    }

    /**
     * 找最小的两个
     *
     * @param arr
     * @param start
     * @param end
     */
    private static void find(int[] arr, int start, int end) {
        int n = arr.length;
        int first = arr[0] < arr[1] ? arr[0] : arr[1];
        int second = arr[0] < arr[1] ? arr[1] : arr[0];
        for (int i = 2; i < n; i++) {
            if (arr[i] < first) {
                second = first;
                first = arr[i];
            } else if (arr[i] < second) {
                second = arr[i];
            }
        }
        System.out.println("最一小：" + first);
        System.out.println("最二小：" + second);
    }

    private static int partion(int[] arr, int start, int end) {
        int tmp = arr[start];
        while (start < end) {
            while (start < end && arr[end] > tmp) {
                end--;
            }
            if (start < end) {
                arr[start] = arr[end];
            }
            while (start < end && arr[start] < tmp) {
                start++;
            }
            if (start < end) {
                arr[end] = arr[start];
            }
        }
        arr[start] = tmp;
        return start;
    }

    @org.junit.Test
    public void test(){
        int[] arr = {1, 4, 2, 5, 3, 8, 1, 5, 2};
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] % 2 != 0){
                for (int j = arr.length - 1; j >= i; j--) {
                    if (arr[j] % 2 == 0){
                        arr[i] = arr[i] + arr[j];
                        arr[j] = arr[i] - arr[j];
                        arr[i] = arr[i] - arr[j];
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 二分查找递归
     *
     * @param arr
     * @param left
     * @param right
     * @param val
     * @return
     */
    private static int midSearch(int[] arr, int left, int right, int val) {
        if (null == arr || left > right) {
            return -1;
        }

        int mid = (right - left + 1) >>> 1 + left;

        if (arr[mid] == val) {
            return mid;
        } else if (val < arr[mid]) {
            return midSearch(arr, left, mid - 1, val);
        } else {
            return midSearch(arr, mid + 1, right, val);
        }
    }

    /**
     * 二分查找
     *
     * @param arr
     * @param val
     * @return
     */
    private static int fun(int[] arr, int val) {
        int l = 0;
        int r = arr.length - 1;
        int pos = -1;
        while (l <= r) {
            int mid = (r - l + 1) >>> 1 + l;
            if (val < arr[mid]) {
                r = mid - 1;
            } else if (val > arr[mid]) {
                l = mid + 1;
            } else {
                while (mid > l && arr[mid] == val) {
                    mid--;
                }
                pos = mid;
                break;
            }
        }
        return pos;
    }
}
