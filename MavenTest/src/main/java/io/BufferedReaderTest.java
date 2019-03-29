package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * description：字符缓冲区的readLine方法验证
 *
 * @author ajie
 * data 2018/7/5
 */
public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {

        FileReader fr = new FileReader("buf.txt");
        BufferedReader bufr = new BufferedReader(fr);

//        // 按行读取
//        String line;
//        while ((line = bufr.readLine()) != null) {
//            System.out.println(line);
//        }
//        bufr.close();

        // 按字节读取
       /* int ch = 0;
        while((ch = bufr.read()) != -1){
            System.out.println((char)ch);
        }
        bufr.close();*/

        // 按字符数组读取
        char[] ch1 = new char[1024];
        int len;
        while ((len = bufr.read(ch1)) != -1) {
            System.out.println(new String(ch1, 0, len));
        }
        bufr.close();
    }
}
