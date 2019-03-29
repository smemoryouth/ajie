package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description：正则表达式的查找功能
 *
 * @author ajie
 * data 2018/7/31 11:43
 */
public class FindTest {
    public static void main(String[] args) {
        String s = " sss d sd sds dff ff e f fd fdfdf ssf bbb";

        // 规则：找到3个字符的单词，注意\b单词边界的使用
        String regex = "\\b\\w{3}\\b";

        // 将正则表达式转变为模式对象
        Pattern pattern = Pattern.compile(regex);

        // 将模式对象转化为匹配器对象,并传入要匹配的字符串
        Matcher matcher = pattern.matcher(s);

        // 查找，调用group方法之前必须调用find方法
        while (matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
