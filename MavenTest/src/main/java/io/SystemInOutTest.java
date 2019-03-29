package io;

import java.io.*;

/**
 * description：
 * 1,需求：将键盘录入的数据写入到一个文件中。
 * 2,需求：将一个文本文件内容显示在控制台上。
 * 3,需求：将一个文件文件中的内容复制到的另一个文件中。
 *
 * @author ajie
 * data 2018/7/8
 */
public class SystemInOutTest {
    public static void main(String[] args) throws IOException {

        // 需求1
        /*BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("a.txt")));*/

        // 需求2
        /*BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream("a.txt")));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));*/

        // 需求3
        BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream("a.txt")));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("b.txt")));
        String line;

        while ((line = bufr.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            bufw.write(line.toUpperCase());
            bufw.newLine();
            bufw.flush();
        }
    }
}
