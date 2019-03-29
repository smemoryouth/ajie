package data;

import java.util.Scanner;

/**
 * description：
 *
 * @author 阿劼
 * data 2019/3/16 10:07
 */
public class Test3 {
    public static void main(String[] args) {
//        getMoney();

    }
    

    /**
     * 求硬币个数
     */
    private static void getMoney(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int money = scanner.nextInt();
            int[] arr = {64, 16, 4, 1};
            int balance = 1024 - money;
            int m = balance / 64;
            int n = balance % 64;
            System.out.println(getBalance(arr, 0, n, m));
        }
    }

    /**
     * 求硬币的个数
     * @param array
     * @param i
     * @param n
     * @param m
     * @return
     */
    private static int getBalance(int[] array, int i, int n, int m) {
        if (n == 0) {
            return m;
        } else {
            m = m + n / array[i + 1];
            n = n % array[i + 1];
            return getBalance(array, i + 1, n, m);
        }
    }

}
