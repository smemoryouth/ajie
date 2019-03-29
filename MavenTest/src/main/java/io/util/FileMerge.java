package io.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;

/**
 * description：碎片文件合并
 *
 * @author ajie
 * data 2018/7/26 22:34
 */
public class FileMerge {
    public static void main(String[] args) throws IOException {
        File dir = new File("g:\\partfiles");
        mergeFile(dir);
    }

    public static void mergeFile(File dir) throws IOException {


        // 获取指定目录下的配置文件对象。
        File[] files = dir.listFiles(new SuffixFilter(".properties"));

        if (files.length != 1) {
            throw new RuntimeException(dir + ",该目录下没有properties扩展名的文件或者不唯一");
        }
        //记录配置文件对象。
        File confile = files[0];

        //获取该文件中的信息
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(confile);

        prop.load(fis);

        String filename = prop.getProperty("filename");
        int count = Integer.parseInt(prop.getProperty("partcount"));


        //获取该目录下的所有碎片文件。
        File[] partFiles = dir.listFiles(new SuffixFilter(".part"));
        if (partFiles.length != (count - 1)) {
            throw new RuntimeException(" 碎片文件不符合要求，个数不对!应该" + count + "个");
        }

        //将碎片文件和流对象关联 并存储到集合中。
        ArrayList<FileInputStream> al = new ArrayList<>();
        for (int x = 0; x < partFiles.length; x++) {
            al.add(new FileInputStream(partFiles[x]));
        }

        //将多个流合并成一个序列流。
        Enumeration<FileInputStream> en = Collections.enumeration(al);
        SequenceInputStream sis = new SequenceInputStream(en);

        // 输出
        FileOutputStream fos = new FileOutputStream(new File(dir, filename));

        byte[] buf = new byte[1024];
        int len;
        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.close();
        sis.close();
    }
}
