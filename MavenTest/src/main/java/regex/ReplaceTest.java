package regex;

/**
 * description：替换功能演示
 *
 * @author ajie
 * data 2018/7/31 11:13
 */
public class ReplaceTest {
    public static void main(String[] args) {
        String s = "hello123456world147852369java";
        // 一个字符替换所有出现的数字
//        String regex = "\\d+";
        // 一个字符替换一个数字
        String regex = "\\d";
        String str = "*";
        String result = s.replaceAll(regex, str);
        System.out.println(result);
    }
}
