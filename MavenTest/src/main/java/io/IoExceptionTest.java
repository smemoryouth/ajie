package io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * description：IO异常处理的基本格式，即输出流的完整格式
 *
 * @author ajie
 * data 2018/7/4
 */
public class IoExceptionTest {
    /**
     * 文本换行
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("demo.txt", true);
            fw.write("abcde" + LINE_SEPARATOR + "hahaha");
            fw.write(LINE_SEPARATOR + "啦啦啦");
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    // code....
                    throw new RuntimeException("关闭失败");
                }
            }
        }
    }
}
