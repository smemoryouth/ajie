package eclipse;

import java.util.Arrays;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/2/25 14:01
 */
public class A {
    public static void main(String[] args){
        int[] arr = {12, 20, 4, 78, 52, 124, 5};
//        sort(arr);
        quick(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        for (int i = (arr.length - 2) >>> 1; i >= 0 ; i--) {
            adjust(arr, i, arr.length - 1);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int tmp = arr[0];
            arr[0] = arr[arr.length - 1 - i];
            arr[arr.length - 1 -i] = tmp;
            adjust(arr, 0, arr.length -2 - i);
        }
    }

    public static void adjust(int[] arr, int start, int end){
        int tmp = arr[start];
        for (int i = start * 2 + 1; i <= end; i = i * 2 + 1) {
            if (i < end && arr[i] < arr[i + 1]){
                i++;
            }
            if (arr[i] > tmp){
                arr[start] = arr[i];
                start = i;
            } else {
                break;
            }
        }
        arr[start] = tmp;
    }

    public static void quick(int[] arr, int start, int end){
        int option = option(arr, start, end);
        if (option - 1 > start){
            quick(arr, start, option - 1);
        }
        if (option + 1 < end){
            quick(arr, option + 1, end);
        }
    }

    public static int option(int[] arr, int start, int end){
        int tmp = arr[start];
        while (start < end){
            while (start < end &&  arr[end] >= tmp){
                end--;
            }
            if (start < end){

                arr[start] = arr[end];
            }

            while (start < end && tmp >= arr[start]){
                start++;
            }
            if (start< end){

                arr[end] = arr[start];
            }
        }
        arr[start] = tmp;
        return start;
    }
}
