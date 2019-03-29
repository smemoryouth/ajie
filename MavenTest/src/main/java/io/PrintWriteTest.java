package io;

import java.io.*;

/**
 * description：
 *
 * @author ajie
 * data 2018/7/25 22:52
 */
public class PrintWriteTest {
    public static void main(String[] args) throws IOException {
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        // true表示自动刷新
        PrintWriter out = new PrintWriter(new FileWriter("out.txt"), true);
        String line;
        while ((line = bufr.readLine()) != null) {
            if ("over".equals(line)) {
                break;
            }
            out.println(line);
        }
        out.close();
        bufr.close();
    }
}
