package test;

import static java.lang.Character.UnicodeBlock.*;

/**
 * description：0x4e00-0x9fbb
 *
 * @author ajie
 * data 2018/11/22 10:48
 */
public class ChineseTest {

    public static void main(String[] args) {
        String str = "sdfw我sfd我";
        checkChinese(str);
        checkChinese2(str);
    }

    private static void checkChinese2(String str) {
        char[] arr = str.toCharArray();
        for (char anArr : arr) {
            if (anArr >= 0x4e00 && anArr <= 0x9fbb) {
                System.out.print(anArr + " ");
            }
        }
    }

    private static void checkChinese(String str) {
        char[] arr = str.toCharArray();
        for (char anArr : arr) {
            if (check(anArr)) {
                System.out.print(anArr);
            }
        }
    }

    private static boolean check(char checkChar) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(checkChar);
        return CJK_UNIFIED_IDEOGRAPHS == ub || CJK_COMPATIBILITY_IDEOGRAPHS == ub ||
                CJK_COMPATIBILITY_FORMS == ub || CJK_RADICALS_SUPPLEMENT == ub ||
                CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A == ub ||
                CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B == ub;
    }
}
