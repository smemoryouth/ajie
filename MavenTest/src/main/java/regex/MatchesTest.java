package regex;

import org.junit.Test;

import java.util.Scanner;

/**
 * description：正则表达式判断功能演示：邮箱判断
 *
 * @author ajie
 * data 2018/7/28 23:21
 */
public class MatchesTest {
    public static void main(String[] args) throws InterruptedException {
        // 键盘录入邮箱
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入邮箱：");
        String email = scanner.nextLine();

        // 定义邮箱格式
        //String regex = "[a-zA-Z_0-9]+@[a-zA-Z_0-9]{2,6}(\\.[a-zA-Z_0-9]{2,3})+";
//        String regex = "\\w+@\\w{2,6}(\\.\\w{2,3})+";
        String regex = "[A-Z]\\w{7,19}";
        // 判断并输出结果
        System.out.println(email.matches(regex));
        Thread.sleep(10);

    }

    @Test
    public void test() {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!^%*?&#])[A-Za-z\\d$@$!^%*?&#]{8,20}";
//        Pattern compile = Pattern
//                .compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!^%*?&#])[A-Za-z\\d$@$!^%*?&#]{8,20}");
//        Matcher matcher = compile.matcher("dfsdghjjj512");
//        if (matcher.find()) {
//            System.out.println(matcher.group());
//        }
        String str = "a@123456789@.cpm";
        System.out.println(str.matches(regex));
    }
}
