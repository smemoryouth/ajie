package io;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * description：FileReader输入流验证，将一个文本文档中的内容输出到打印台
 *
 * @author ajie
 * data 2018/7/4
 */
public class FileReaderTest {
    public static void main1(String[] args) throws IOException {

        /*
         * 在创建读取流对象时，必须要明确被读取的文件。一定要确定该文件是存在的。
         *用一个读取流关联一个已存在文件。
         */
        FileReader fr = new FileReader("demo.txt");

        int ch;
        while ((ch = fr.read()) != -1) {
            System.out.println((char) ch);
        }
        fr.close();
    }

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("C:\\Users\\阿劼\\Pictures\\笔记\\IDEA快捷键.txt");

        /*
         * 使用read(char[])读取文本文件数据。
         * 先创建字符数组。
         */
        char[] buf = new char[1024];

        int len;

        while ((len = fr.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }
        fr.close();
    }
}
