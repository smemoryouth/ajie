package io;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * description：
 *
 * @author ajie
 * data 2018/7/25 22:32
 */
public class PrintStreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream printStream = new PrintStream("print.txt");
        printStream.write(97);
        printStream.println();
        printStream.print(20);
        printStream.close();
    }
}
