package io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * description：文本文档的复制操作,字符流
 *
 * @author ajie
 * data 2018/7/4
 */
public class CopyFileTest {
    private static final int BUFFER_SIZE = 1024;

    public static void main1(String[] args) throws IOException {
    long l1 = System.currentTimeMillis();
        // 读取一个已有的文本文件，使用字符读取流和文件相关联
        FileReader fr = new FileReader("demo.txt");

        // 创建一个目的，用于存储读到数据
        FileWriter fw = new FileWriter("copytext_1.txt");

        // 频繁的读写操作
        int ch;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);
        }
        // 关闭流资源
        fw.close();
        fr.close();
        long l2 = System.currentTimeMillis();
        System.out.println("字符流耗时：" + (l2 - l1));
    }

    public static void main(String[] args) {

        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader("demo.txt");
            fw = new FileWriter("d:\\00\\00.txt");

            // 创建一个临时容器，用于缓存读取到的字符
            char[] buf = new char[BUFFER_SIZE];

            // 定义一个变量记录读取到的字符数，(其实就是往数组里装的字符个数 )
            int len;
            while ((len = fr.read(buf)) != -1) {
                fw.write(buf, 0, len);
            }

        } catch (Exception e) {
            throw new RuntimeException("读写失败");
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
