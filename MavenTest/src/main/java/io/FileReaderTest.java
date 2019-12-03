package io;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

/**
 * description：FileReader输入流验证，将一个文本文档中的内容输出到打印台
 *
 * @author ajie
 * data 2018/7/4
 */
public class FileReaderTest {
    @Test
    public void test() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\阿劼\\Desktop\\111.txt");

        int ch;
        while ((ch = fr.read()) != -1) {
            System.out.print((char) ch);
        }
        fr.close();
    }

    @Test
    public void test1() throws IOException {
        FileReader fr = new FileReader("C:\\Users\\阿劼\\Desktop\\111.txt");
        char[] buf = new char[1024];
        int len;
        while ((len = fr.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }
        fr.close();
    }
}
