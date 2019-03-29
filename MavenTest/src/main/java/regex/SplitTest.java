package regex;

import java.util.Arrays;
import java.util.Scanner;

/**
 * description：正则表达式的分割功能演示：好友按年龄段搜索
 *
 * @author ajie
 * data 2018/7/28 23:38
 */
public class SplitTest {
    public static void main0(String[] args) {
        // 定义年龄段
        String ages = "18-24";
        // 定义分隔符
        String regex = "-";
        String[] arr = ages.split(regex);

        // 确定判断条件
        int startAge = Integer.parseInt(arr[0]);
        int endAge = Integer.parseInt(arr[1]);

        // 键盘录入年龄信息
        System.out.println("请输入年龄：");
        int age = new Scanner(System.in).nextInt();

        // 判断
        if (age >= startAge && age <= endAge) {
            System.out.println("just you");
        } else {
            System.out.println("oh,not you");

        }
    }

    public static void main1(String[] args) {
        String s1 = "aa,bb,cc";
        String[] str1 = s1.split(",");
        for (String i : str1) {
            System.out.println(i);
        }
        System.out.println("=============");

        String s2 = "aa.bb.cc";
        String[] str2 = s2.split("\\.");
        for (String i : str2){
            System.out.println(i);
        }
        System.out.println("==============");

        String s3 = "aa   bb         cc";
        String[] str3 = s3.split(" +");
        for(String i : str3){
            System.out.println(i);
        }
        System.out.println("===============");

        String s4 = "S:\\java集合框架与多线程\\正则与其他日常类";
        // 对上面的每一个\都进行转义，所以需要四个\
        String[] str4 = s4.split("\\\\");
        for (String i : str4){
            System.out.println(i);
        }
    }

    /**
     * 将“52 54 22 36 65 10”转化为 “10 22 36 52 54 65”输出
     * @param args
     */
    public static void main(String[] args){
        String s = "52 54 22 36 65 10";

        // 分割成字符数组
        String[] str = s.split(" ");

        // 字符数组转化为int型数组
        int[] array = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            array[i] = Integer.parseInt(str[i]);
        }

        // 排序
        Arrays.sort(array);

        // int型数组组装成字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]).append(" ");
        }
        // 去掉末尾空格
        String str2 = sb.toString().trim();

        // 输出字符串
        System.out.println(str2);
    }
}
