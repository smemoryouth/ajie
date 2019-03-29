package data;

/**
 * description：4399在线笔试
 *
 * @author 阿劼
 * data 2019/3/14 19:08
 */
public class OnlineTest4399 {
    public static void main(String[] args) {
        System.out.println(count(15));
    }

    /**
     * 求几个数组成一个数的个数，这几个数可以重复利用
     *
     * @param n
     * @return
     */
    public static long count(int n) {
        if (n <= 0) {
            return 0;
        }
        long[] dp = new long[n + 1];
        dp[0] = 1;

        int[] coins = new int[]{1, 5, 10, 20, 50, 100};
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] = dp[j] + dp[j - coins[i]];
            }
        }
        return dp[n];
    }

    /**
     * 一个数组中的数相加，是指定值的倍数，求相加的组合，其中数组的数不可重复相加
     * {1，2,3，4,5} 5
     * {1,2,3,4,5} {2,3} {5} {2,3,5} {1,4,5}
     *
     * @param arr1
     * @param k
     * @return
     */
    private static int getKMaxLength(int[] arr1, int k) {
        int n = arr1.length;
        int[] max = new int[k];
        int[] min = new int[k];
        int[] arr2 = new int[arr1.length + 1];
        arr2[0] = 0;
        for (int i = 0; i < n; i++) {
            arr2[i + 1] = arr2[i] + arr1[i];
        }
        for (int i = 0; i < k; i++) {
            max[i] = -1;
            min[i] = n + 1;
        }

        int mod;
        for (int i = 0; i < n + 1; i++) {
            mod = arr2[i] % k;

            max[mod] = Math.max(max[mod], i);
            min[mod] = Math.min(min[mod], i);

        }

        int count = 0;
        for (int i = 0; i < k; i++) {
            if (max[i] != -1 && min[i] != n + 1) {
                count = Math.max(count, max[i] - min[i]);
            }
        }
        return count;
    }

    /**
     * 矩阵从左上角到右下角的路径，只能向右向下移动
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        if (n < 1 || n > 100) {
            return -1;
        }
        if (m < 1 || m > 100) {
            return -1;
        }

        int N = n + m - 2;
        int K = n - 1;
        double res = 1.0;
        for (int i = 1; i <= n - 1; ++i) {
            res = res * (N - K + i) / i;
        }
        return (int) res;
    }

    /**
     * 几个人围圈数数，数到指定数的淘汰，求最后留下的，约瑟夫环
     * @param n
     * @param m
     * @return
     */
    public static int reverse(int n, int m) {
        int count = 0;
        int pos = 0;
        int[] arr = new int[n];
        int left = arr.length;
        while(left > 1) {
            if(arr[pos] == 0) {
                count++;
            }
            if(count == m) {
                arr[pos] = 1;
                count = 0;
                left--;
            }
            pos++;
            if(pos == arr.length) {
                pos = 0;
            }
        }
        for(int x = 0; x < arr.length; x++) {
            if(arr[x] == 0) {
                return x + 1;
            }
        }
        return -1;
    }

    /**
     * 求n以内的完数，即因子的和等于自身，
     * @param n
     */
    public static void getWanShu(int n) {
        int s;
        for (int i = 1; i <= n; i++) {
            s = 0;
            for (int j = 1; j < i; j++) {
                if (i % j == 0){

                    s = s + j;
                }
            }
            if (s == i){
                System.out.print(i + " ");

            }
        }
        System.out.println();
    }
}
