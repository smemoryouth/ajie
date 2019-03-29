package io;

import java.io.*;

/**
 * description：
 *
 * @author ajie
 * data 2018/7/27 10:56
 */
public class RandomAccessFileTest {
    public static void main(String[] args) {
//        try {
//            RandomAccessFile raf = new RandomAccessFile("a.txt", "rw");
//            RandomAccessFile raf2 = new RandomAccessFile("b.txt", "rw");
//            raf2.seek(raf2.length());
//            int ch;
//            while ((ch = raf.read()) != -1) {
//                raf2.write(ch);
//            }
//            raf.close();
//            raf2.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        randomFiile(2,"xyz");
    }

    /**
     * 在一个文件的任意位置插入字符串
     * @param pos 位置
     * @param content 待插入的字符串
     */
    public static void randomFiile(int pos, String content) {
        try {
            // 创建临时文件，用于保存原文件在pos位置后面的内容
            File temp = File.createTempFile("max", ".tmp", new File("C:\\Users\\阿劼\\Desktop"));
            // 程序运行结束时该临时文件自动删除
            temp.deleteOnExit();

            // 创建关联临时文件的输入输出流
            FileOutputStream outputStream = new FileOutputStream(temp);
            FileInputStream inputStream = new FileInputStream(temp);

            // 关联要操作的源文件
            RandomAccessFile rw = new RandomAccessFile("a.txt", "rw");

            // 指针跳到指定位置
            rw.seek(pos);

            int tmp;
            // 将指针后面的内容保存到临时文件
            while ((tmp = rw.read()) != -1) {
                outputStream.write(tmp);
            }

            // 在指定位置后面插入要加入的内容
            rw.seek(pos);
            rw.write(content.getBytes());

            // 将临时文件中的内容再添加到源文件的末尾
            while ((tmp = inputStream.read()) != -1) {
                rw.write(tmp);
            }

            outputStream.close();
            inputStream.close();
            rw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
