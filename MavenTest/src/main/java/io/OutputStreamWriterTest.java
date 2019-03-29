package io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/**
 * description：OutputStreamWriter，将字符流转换为字节流
 *
 * @author ajie
 * data 2018/7/23 20:11
 */
public class OutputStreamWriterTest {

    /**
     * 输入与读取
     *
     * @param bufr 输入流
     * @param bufw 输出流
     * @throws IOException 输入为null
     */
    private static void show(BufferedReader bufr, BufferedWriter bufw) throws IOException {
        String line;
        while ((line = bufr.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            bufw.write(line);
            bufw.newLine();
            bufw.flush();
        }
    }

    /**
     * 使用默认字符编码的 OutputStreamWriter
     *
     * @throws IOException UnsupportedEncodingException
     */
    public static void method1() throws IOException {
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        // 创建使用默认字符编码的OutputStreamWriter
        OutputStreamWriter osw = new OutputStreamWriter(System.out);
        // 获取当前流使用的编码表的名称
        System.out.println(osw.getEncoding());

        BufferedWriter bufw = new BufferedWriter(osw);
        show(bufr, bufw);

        bufr.close();
        osw.close();
        bufw.close();
    }

    /**
     * 使用给定编码表的 OutputStreamWriter
     *
     * @throws IOException 无法辨识的编码表
     */
    public static void method2() throws IOException {

        // 指定的编码表
        Charset charset = Charset.forName("GBK");

        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        // 创建使用给定字符集的 OutputStreamWriter
        OutputStreamWriter osw = new OutputStreamWriter(System.out, charset);
        // 获取当前流使用的编码表的名称
        System.out.println(osw.getEncoding());

        BufferedWriter bufw = new BufferedWriter(osw);

        show(bufr, bufw);
        bufr.close();
        osw.close();
        bufw.close();
    }

    /**
     * 使用给定字符集编码器的 OutputStreamWriter
     *
     * @throws IOException 无法辨识的编码表
     */
    public static void method3() throws IOException {
        // 指定编码表的编码器
        CharsetEncoder encoder = Charset.forName("GBK").newEncoder();

        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        // 创建使用给定字符集编码器的 OutputStreamWriter
        OutputStreamWriter osw = new OutputStreamWriter(System.out, encoder);

        // 获取当前流使用的编码表的名称
        System.out.println(osw.getEncoding());

        BufferedWriter bufw = new BufferedWriter(osw);

        show(bufr, bufw);
        bufr.close();
        osw.close();
        bufw.close();
    }

    /**
     * 使用指定字符集的 OutputStreamWriter
     *
     * @throws IOException
     */
    public static void method4() throws IOException {

        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

        // 创建使用指定字符集的 OutputStreamWriter
        OutputStreamWriter osw = new OutputStreamWriter(System.out, "GBK");
        // 获取当前流使用的编码表的名称
        System.out.println(osw.getEncoding());

        BufferedWriter bufw = new BufferedWriter(osw);

        show(bufr, bufw);
        bufr.close();
        osw.close();
        bufw.close();
    }

    public static void method5() throws IOException {
        // 字符流输入
        FileReader fr = new FileReader("C:\\Users\\阿劼\\Desktop\\123.txt");
        BufferedReader br = new BufferedReader(fr);

        // 字节流输出
        OutputStream os = System.out;

        // 创建使用默认字符编码的 OutputStreamWriter
        OutputStreamWriter osw = new OutputStreamWriter(os);

        char[] ch1 = new char[1024];
        int length;
        while ((length = br.read(ch1)) != -1) {
            osw.write(ch1, 0, length);
        }
        osw.close();
        fr.close();

//        int ch2;
//        while ((ch2 = br.read()) != -1){
//            osw.write(ch2);
//        }
//        osw.flush();
//        osw.close();
//        fr.close();

//        osw.write("asdfghjkl", 0, 5);
//        osw.close();
    }

    public static void method6() throws IOException {
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(System.out));
        show(bufr, bufw);
        bufr.close();
        bufw.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(Charset.defaultCharset());
        method4();
    }
}
