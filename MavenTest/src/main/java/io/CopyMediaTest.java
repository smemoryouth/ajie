package io;

import java.io.*;

/**
 * description：用字节流复制媒体文件
 *
 * @author ajie
 * data 2018/7/6
 */
public class CopyMediaTest {
    public static void main(String[] args) throws IOException {

//        copy3();
        copy2();

    }

    /**
     * 单个字符读取写入，完全没有效率的操作，不建议使用
     */

    public static void copy4() throws IOException {
        FileInputStream fis = new FileInputStream("c:\\0.mp3");
        FileOutputStream fos = new FileOutputStream("c:\\4.mp3");

        int ch;

        while ((ch = fis.read()) != -1) {
            fos.write(ch);
        }

        fos.close();
        fis.close();
    }

    /**
     * 产生对应大小的数组，当文件较大时会产生内存溢出，不建议使用
     */
    public static void copy3() throws IOException {
        long l1 = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("d:\\往南.mp3");
        FileOutputStream fos = new FileOutputStream("s:\\1.mp3");

        byte[] buf = new byte[fis.available()];
        fis.read(buf);
        fos.write(buf);
        fos.close();
        fis.close();

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

    /**
     * 使用缓冲区完成
     * @throws IOException
     */
    public static void copy2() throws IOException {

        long l1 = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream("d:\\往南.mp3");
        BufferedInputStream bufis = new BufferedInputStream(fis);

        FileOutputStream fos = new FileOutputStream("s:\\2.mp3");
        BufferedOutputStream bufos = new BufferedOutputStream(fos);
        byte[] b = new byte[bufis.available()];
        int ch;

        while ((ch = bufis.read(b)) != -1) {
            bufos.write(ch);
        }

//        bufis.read(b);
//        bufos.write(b);

        bufos.close();
        bufis.close();

        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

    /**
     * 不使用缓冲区实现
     * @throws IOException
     */
    public static void copy1() throws IOException {

        FileInputStream fis = new FileInputStream("c:\\0.mp3");
        FileOutputStream fos = new FileOutputStream("c:\\1.mp3");

        byte[] buf = new byte[1024];

        int len;

        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }

        fos.close();
        fis.close();
    }
}
