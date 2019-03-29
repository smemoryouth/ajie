package test;

import java.util.HashMap;
import java.util.Map;

/**
 * description：396/720
 *
 * @author ajie
 * data 2018/11/22 11:24
 */
public class NumberAssemble {
    private static HashMap<Long, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        int[] in = new int[]{1, 2, 2, 3, 4, 5};
        printSort(in);
        printMap(map);
    }

    private static void printSort(int[] in) {
        listAll(in, 0);
    }

    private static void listAll(int[] arr, int i) {
        int temp;
        long num ;
        boolean flag = true;
        if (i > arr.length || i < 0) {
            throw new RuntimeException("error index");
        } else if (i == arr.length) {
            for (int j = 0; j < i; j++) {
                if (arr[j] == 4) {
                    if (j == 2) {
                        flag = false;
                        break;
                    }
                } else if (arr[j] == 3) {
                    if (j != i - 1) {
                        if (arr[j + 1] == 5) {
                            flag = false;
                            break;
                        }
                    }
                } else if (arr[j] == 5) {
                    if (j != i - 1) {
                        if (arr[j + 1] == 3) {
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if (flag) {
                num = transfer(arr);
                Integer in = map.get(num);
                map.put(num, in == null ? 1 : in + 1);
            }
        } else {
            for (int j = i; j < arr.length; j++) {
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                listAll(arr, i + 1);
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
    }

    private static void printMap(HashMap<Long, Integer> map) {
        for (Map.Entry<Long, Integer> next : map.entrySet()) {
            Long str = next.getKey();
            Integer in = next.getValue();
            System.out.println(str + ":" + in);
        }
    }

    private static long transfer(int[] arr) {
        StringBuilder builder = new StringBuilder();
        for (int arri : arr) {
            builder.append(arri);
        }
        return Long.parseLong(builder.toString());
    }
}
