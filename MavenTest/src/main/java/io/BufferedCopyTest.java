package io;

import java.io.*;

/**
 * description：通过缓冲区实现复制操作
 *
 * @author ajie
 * data 2018/7/5
 */
public class BufferedCopyTest {
    /**
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        FileReader fr = new FileReader("demo.txt");
        BufferedReader bufr = new BufferedReader(fr);

        FileWriter fw = new FileWriter("democopy_3.txt");
        BufferedWriter bufw = new BufferedWriter(fw);

        String line;
        while ((line = bufr.readLine()) != null) {
            bufw.write(line);
            bufw.newLine();
            bufw.flush();
        }
        bufr.close();
        bufw.close();
        long l2 = System.currentTimeMillis();
        System.out.println(l2 -l1);
    }
}
