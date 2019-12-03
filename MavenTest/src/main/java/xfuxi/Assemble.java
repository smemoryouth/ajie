package xfuxi;

import java.util.Scanner;

/**
 * description：键盘录入内容的所有组合
 *
 * @author ajie
 * data 2018/9/13 23:34
 */
public class Assemble {
    public static void permutateSequence(char[] strArrs, int i) {
        char temp;
        if (strArrs == null || i > strArrs.length || i < 0) {
            return;
        } else if (i == strArrs.length) {
            System.out.println(strArrs);
        } else {
            for (int j = i; j < strArrs.length; j++) {
                temp = strArrs[j];
                strArrs[j] = strArrs[i];
                strArrs[i] = temp;
                permutateSequence(strArrs, i + 1);
                temp = strArrs[j];
                strArrs[j] = strArrs[i];
                strArrs[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char strArrs[] = str.toCharArray();
        permutateSequence(strArrs, 0);
    }
}
