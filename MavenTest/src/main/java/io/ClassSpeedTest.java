package io;

import java.io.*;
import java.util.Random;

/**
 * description：
 *
 * @author ajie
 * data 2018/7/23
 */
public class ClassSpeedTest implements Serializable {
    private static final int MAXNUMBER = 100000;

    /**
     * 数据初始化
     *
     * @param path
     * @throws IOException
     */
    public static void init(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        Random random = new Random();
        for (int i = 0; i < MAXNUMBER; i++) {
            fileWriter.write(random.nextInt(10000) + "\r\n");
        }
        fileWriter.close();

    }

    /**
     * FileInputStream and FileOutputStream
     *
     * @param fromPath
     * @param toPath
     * @throws IOException
     */
    public static void streamCopy(String fromPath, String toPath) throws IOException {
        long l1 = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(fromPath);
        FileOutputStream fos = new FileOutputStream(toPath);
        int ch;
        while ((ch = fis.read()) != -1) {
            fos.write(ch);
        }
        fos.close();
        fis.close();
        long l2 = System.currentTimeMillis();
        System.out.println("FileInput/FileOutput time is " + (l2 - l1) + " ms");
    }

    /**
     * BufferedReader and BufferedWriter
     *
     * @param fromPath
     * @param toPath
     * @throws IOException
     */
    public static void writerStreamCopy(String fromPath, String toPath) throws IOException {
        long l1 = System.currentTimeMillis();
        BufferedReader bd = new BufferedReader(new FileReader(fromPath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(toPath));

        String line;
        while ((line = bd.readLine()) != null) {
            bw.write(line);
        }
        bd.close();
        bw.close();
        long l2 = System.currentTimeMillis();
        System.out.println("writerStreamCopy time is " + (l2 - l1) + " ms");

    }

    /**
     * BufferedInputStream and BufferedOutputStream
     *
     * @param fromPath
     * @param toPath
     * @throws IOException
     */
    public static void bufferCopy(String fromPath, String toPath) throws IOException {
        long l1 = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromPath));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toPath));
        int ch;
        while ((ch = bis.read()) != -1) {
            bos.write(ch);
        }
        bos.close();
        bis.close();
        long l2 = System.currentTimeMillis();
        System.out.println("bufferCopy time is " + (l2 - l1) + " ms");
    }

    /**
     * Object输入输出流
     *
     * @param fromPath
     * @param toPath
     * @throws IOException
     */
    public static void objectCopy(String fromPath, String toPath) throws IOException {
        long l1 = System.currentTimeMillis();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fromPath));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(toPath));
        int ch;
        while ((ch = ois.read()) != -1) {
            oos.write(ch);
            oos.flush();
        }
        ois.close();
        oos.close();
        long l2 = System.currentTimeMillis();
        System.out.println("objectStreamCopy time is " + (l2 - l1) + " ms");

    }

    public static void main(String[] args) throws IOException {
        String form = "d:\\00\\0100.txt";
        String to = "C:\\Users\\阿劼\\Desktop\\0.txt";
        String to1 = "C:\\Users\\阿劼\\Desktop\\00.txt";
        String to2 = "C:\\Users\\阿劼\\Desktop\\000.txt";
        String to3 = "C:\\Users\\阿劼\\Desktop\\0000.txt";

//        init(form);
//        streamCopy(form, to);
//        bufferCopy(form, to1);
        objectCopy(form, to2);
        writerStreamCopy(form, to3);
    }
}
