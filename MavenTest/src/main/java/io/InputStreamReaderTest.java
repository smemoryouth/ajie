package io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


/**
 * description：InputStreamReader,将字节流转换为字符流
 *
 * @author ajie
 * data 2018/7/23 15:41
 */
public class InputStreamReaderTest {

    /**
     * 使用默认编码表
     *
     * @throws IOException
     */
    public static void method1() throws IOException {
        // 字节流输入
        InputStream is = System.in;

        // 创建使用默认编码表的 InputStreamReader,isr现在已经是字符流了
        InputStreamReader isr = new InputStreamReader(is);

        // 获取当前流使用的编码表名称
        System.out.println(isr.getEncoding());

        // 字符流输出
        BufferedReader br = new BufferedReader(isr);

        // 按行读取
        String line;
        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            System.out.println(line);
        }
        br.close();
        isr.close();
    }

    /**
     * 使用给定编码表
     *
     * @throws IOException
     */
    public static void method2() throws IOException {
        // 字节流输入
        InputStream is = System.in;

        // 指定的编码表
        Charset charset = Charset.forName("GBK");

        // 创建使用给定编码表的 InputStreamReader
        InputStreamReader isr = new InputStreamReader(is, charset);

        // 获取当前流使用的编码表名称
        System.out.println(isr.getEncoding());

        // 字符流输出
        BufferedReader br = new BufferedReader(isr);

        // 按行读取
        String line;
        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            System.out.println(line);
        }
        br.close();
        isr.close();
    }

    /**
     * 指定编码表的解码器
     *
     * @throws IOException
     */
    public static void method3() throws IOException {
        // 字节流输入
        InputStream is = System.in;

        // 指定的编码表
        Charset charset = Charset.forName("GBK");

        // 指定编码表的解码器
        CharsetDecoder decoder = charset.newDecoder();

        // 创建使用给定编码表解码器的 InputStreamReader
        InputStreamReader isr = new InputStreamReader(is, decoder);

        // 获取当前流使用的编码表名称
        System.out.println("当前" + isr.getEncoding());
        System.out.println(isr.ready());

        // 字符流输出
        BufferedReader br = new BufferedReader(isr);
        System.out.println(isr.ready());

        // 按行读取
        String line;
        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            System.out.println(line);
            System.out.println(isr.ready());
        }
        br.close();
        isr.close();
    }

    /**
     * 使用指定编码表
     *
     * @throws IOException
     */
    public static void method4() throws IOException {
        // 字节流输入
        InputStream is = System.in;

        // 创建使用指定编码表的 InputStreamReader
        InputStreamReader isr = new InputStreamReader(is, "GBK");

        // 获取当前流使用的编码表名称
        System.out.println(isr.getEncoding());

        // 字符流输出
        BufferedReader br = new BufferedReader(isr);

        // 按行读取
        String line;
        while ((line = br.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            System.out.println(line);
        }
        br.close();
        isr.close();
    }

    public static void method5() throws IOException {
        FileInputStream fis = new FileInputStream("C:\\Users\\阿劼\\Desktop\\012.txt");
        InputStreamReader isr = new InputStreamReader(fis);

        OutputStream os = System.out;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
//        char[] ch1 = new char[fis.available()];
//        int length;
//        while ((length = isr.read(ch1)) != -1){
//            bw.write(ch1, 0, length);
//            bw.flush();
//        }
//        bw.close();
//        fis.close();

//        int ch2;
//        while ((ch2 = isr.read()) != -1){
//            System.out.print(ch2);
//        }
//        System.out.println();
//        bw.close();
//        fis.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("默认： " + Charset.defaultCharset());
        method3();
    }
}
