package io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * description：FileWriter输出流验证，输出文本信息到指定位置
 *
 * @author ajie
 * data 2018/7/4
 */
public class FileWriterTest {
    /**
     * 文本换行
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void main(String[] args) throws IOException {


        /**
         * 创建一个可以往文件中写入字符数据的字符输出流对象。
         * 既然是往一个文件中写入文字数据，那么在创建对象时，就必须明确该文件(用于存储数据的目的地)。
         *
         * 如果文件不存在，则会自动创建，如果文件存在，则会被覆盖。
         *
         * 如果构造函数中加入true，可以实现对文件进行续写！续写的文件不会对源文件进行覆写
         */
        FileWriter fw = new FileWriter("d:\\00\\demo000000.txt", true);

        /*
         * 调用Writer对象中的write(string)方法，写入数据。
         * 其实数据写入到临时存储缓冲区中。
         */
        fw.write("abcde" + LINE_SEPARATOR + "hahaha");

        // 续写
        fw.write(LINE_SEPARATOR + "啦啦啦");

        // 进行刷新，将数据直接写到目的地中
//		fw.flush();

        // 关闭流，关闭资源。在关闭前会先调用flush刷新缓冲中的数据到目的地。
        fw.close();
    }
}
