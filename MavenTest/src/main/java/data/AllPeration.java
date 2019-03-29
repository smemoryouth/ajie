package data;

import java.util.Arrays;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/3/1 18:03
 */
public class AllPeration {

    public static void main(String[] args){
        int[] arr = {1, 2, 3};
        perm(arr, 0, arr.length - 1);
    }
    private static void perm(int[] arr, int k, int m) {

        if (k == m) {
            System.out.println(Arrays.toString(arr));
        } else {
            for (int i = k; i <= m; i++) {
                swap(arr, i, k);
                perm(arr, k + 1, m);
                swap(arr, i, k);
            }
        }
    }

    private static void swap(int[] arr, int k, int m) {
        int tmp = arr[k];
        arr[k] = arr[m];
        arr[m] = tmp;
    }
}
