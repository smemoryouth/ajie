package data;

/**
 * @author 阿劼
 */
public class Test2 {
    private static int getNum(int a, int b, int m) {
        if (a + b < 0 && m >= 0) {
            return -1;
        }

        int count = 0;

        while (a < m && b < m) {
            if (a > b) {
                a = a + b;
                b = a - b;
                a = a - b;
            }
            a += b;
            count++;
            if (a >= m) {
                break;
            }
        }
        return count;
    }


}
