package eclipse;

/**
 * next数组的取得
 */
public class Kmp {
    private static void getNext(int[] next, String sub) {
        // 设置next数组的前两位为-1和0
        next[0] = -1;
        next[1] = 0;
        // 定义i + 1
        int i = 2;
        int k = 0;
        while(i < sub.length()) {
            if(k == -1 || sub.charAt(k) == sub.charAt(i - 1)) {
                // i与k的对应关系
                next[i] = k + 1;
                i++;
                k++;
            }else {
                k = next[k];
            }
        }
    }

    private static int kmp(String str, String sub, int pos) {
        // 判断索引合法性
        if (pos < 0 || pos > str.length() || sub.length() > str.length()) {
            return -1;
        }

        // 主串遍历索引
        int i = pos;
        // 子串遍历索引
        int j = 0;

        // 定义next数组
        int [] next = new int[sub.length()];
        getNext (next, sub);

        while (i < str.length() && j < sub.length()) {
            // 若字符对应相同，则索引后移
            if (j == -1 || str.charAt(i) == sub.charAt(j)){
                i++;
                j++;
            }else {
                // 字符不相同，子串索引回到对应的next数组所指明的位置
                j = next[j];
            }
        }
        // 遍历完毕，返回子串在主串的开始索引
        if (j >= sub.length()) {
            return i - j;
        }else {
            return -1;
        }
    }

    public static void main(String[] args) {
        String sub = "fdfff";
        String str = "fdfdvdfdfvvdfdf";
        System.out.println(kmp(str, sub, 0));

    }
}
