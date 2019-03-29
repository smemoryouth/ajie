package io;

import java.io.*;

/**
 * description：main1用转换流将键盘输入的字节流转换为字符流输出
 * main:输出用输出流来做
 *
 * @author ajie
 * data 2018/7/8
 */
public class SystemInTransformStreamTest {
    public static void method1() throws IOException {
        // 字节流
        InputStream in = System.in;

        // 将字节转成字符的桥梁,装换流
        InputStreamReader isr = new InputStreamReader(in);

        // 字符流
        BufferedReader bufr = new BufferedReader(isr);

        String line;
        while ((line = bufr.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            System.out.println(line.toUpperCase());
        }
    }

    public static void main(String[] args) throws IOException {

        // 字节流
        InputStream in = System.in;

        // 将字节转成字符的桥梁,转换流
        InputStreamReader isr = new InputStreamReader(in);

        // 字符流
        BufferedReader bufr = new BufferedReader(isr);

        OutputStream out = System.out;
        OutputStreamWriter osw = new OutputStreamWriter(out);
        BufferedWriter bufw = new BufferedWriter(osw);
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

