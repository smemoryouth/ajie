package io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * description：
 *
 * @author ajie
 * data 2018/7/27 10:15
 */
public class InitNumber {
    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("1.txt");
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            fileWriter.write(random.nextInt(10000) + "\r\n");
        }
        fileWriter.close();
    }
}
