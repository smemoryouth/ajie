package io.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * description：文件切割器
 *
 * @author ajie
 * data 2018/7/26 22:09
 */
public class FileSplitTest {
    /**
     * 定义碎片文件的大小
     */
    private static final int SIZE = 1024 * 1024;

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        File file = new File("d:\\往南.mp3");
        splitFile(file);
    }

    private static void splitFile(File file) throws IOException {

        // 用读取流关联源文件。
        FileInputStream fis = new FileInputStream(file);

        // 定义一个1M的缓冲区。
        byte[] buf = new byte[SIZE];

        // 创建目的。
        FileOutputStream fos;

        int len;
        int count = 1;


        /*
         * 切割文件时，必须记录住被切割文件的名称，以及切割出来碎片文件的个数。 以方便于合并。
         * 这个信息为了进行描述，使用键值对的方式。用到了properties对象
         */
        Properties prop = new Properties();
        File dir = new File("g:\\partfiles");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        while ((len = fis.read(buf)) != -1) {
            fos = new FileOutputStream(new File(dir, (count++) + ".part"));
            fos.write(buf, 0, len);
            fos.close();
        }

        //将被切割文件的信息保存到prop集合中。
        prop.setProperty("partcount", String.valueOf(count));
        prop.setProperty("filename", file.getName());

        fos = new FileOutputStream(new File(dir, "split.properties"));

        //将prop集合中的数据存储到文件中。
        prop.store(fos, "save file info");

        fos.close();
        fis.close();

    }

    public static void splitFile0(File file) throws IOException {

        // 用读取流关联源文件。
        FileInputStream fis = new FileInputStream(file);

        // 定义一个1M的缓冲区。
        byte[] buf = new byte[SIZE];

        // 创建目的。
        FileOutputStream fos = null;

        int len = 0;
        int count = 1;

        File dir = new File("g:\\partfiles");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        while ((len = fis.read(buf)) != -1) {

            fos = new FileOutputStream(new File(dir, (count++) + ".part"));
            fos.write(buf, 0, len);
        }
        assert fos != null;
        fos.close();
        fis.close();

    }
}
