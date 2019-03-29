package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



/**
 * description：缓冲区验证
 *
 * @author ajie
 * data 2018/7/5
 */
public class BufferedWriteTest {

    public static final int NUM = 5;

    public static void main(String[] args) throws IOException {

        FileWriter fw = new FileWriter("buf.txt");


        /*
        为了提高写入的效率。使用了字符流的缓冲区。
        创建了一个字符写入流的缓冲区对象，并和指定要被缓冲的流对象相关联
         */
        BufferedWriter bufw = new BufferedWriter(fw);

        for (int x = 1; x <= NUM; x++) {
            bufw.write("abcdef" + x);
            bufw.newLine();
            bufw.flush();
        }
        // 关闭缓冲区,其实关闭的就是被缓冲的流对象
        bufw.close();
    }
}
