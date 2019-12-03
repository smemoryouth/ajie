package xfuxi;

import java.util.Arrays;

/**
 * description：冒泡排序，插入排序，选择排序
 *
 * @author ajie
 * data 2018/9/3 19:01
 */
public class BasicSort {
    public static void main(String[] args){
        int[] arr = new int[]{2, 10, 5, 41};
        search(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void bubber(int[] array){
        int length = array.length;
        int temp;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] < array[j + 1]){
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
    public static void insert(int[] array){
        int length = array.length;
        int temp;
        int j;
        for (int i = 1; i < length; i++) {
            temp = array[i];
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > temp){
                    array[j + 1] = array[j];
                }else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
    }

    public static void search(int[] array){
        int length = array.length;
        int tmp;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i] < array[j]){
                    tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }
}
