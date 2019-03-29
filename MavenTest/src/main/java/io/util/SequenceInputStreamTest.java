package io.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/**
 * description：SequenceInputStream演示,序列流
 *
 * @author ajie
 * data 2018/7/26 20:55
 */
public class SequenceInputStreamTest {
    public static void main(String[] args) throws IOException {
        long l1 = System.currentTimeMillis();
        vectorShow();
        long l2 = System.currentTimeMillis();
        System.out.println("Vector耗时：" + (l2 - l1));

        l1 = System.currentTimeMillis();
        collectionShow();
        l2 = System.currentTimeMillis();
        System.out.println("Collections耗时：" + (l2 - l1));
    }

    /**
     * 利用Vector实现枚举
     * @throws IOException
     */
    public static void vectorShow() throws IOException {
        Vector<FileInputStream> v = new Vector<>();
        v.add(new FileInputStream("a.txt"));
        v.add(new FileInputStream("buf.txt"));
        v.add(new FileInputStream("infodfd0.txt"));
        Enumeration<FileInputStream> en = v.elements();
        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream("demo123.txt");
        byte[] buf = new byte[1024];
        int len;
        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.close();
        sis.close();
    }

    /**
     * 利用Collection实现枚举
     * @throws IOException
     */
    public static void collectionShow() throws IOException {
        ArrayList<FileInputStream> arrayList = new ArrayList<>();

        arrayList.add(new FileInputStream("a.txt"));
        arrayList.add(new FileInputStream("buf.txt"));
        arrayList.add(new FileInputStream("infodfd0.txt"));

        Enumeration<FileInputStream> en = Collections.enumeration(arrayList);
        SequenceInputStream sis = new SequenceInputStream(en);
        FileOutputStream fos = new FileOutputStream("demo123.txt");
        byte[] buf = new byte[1024];
        int len;
        while ((len = sis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        fos.close();
        sis.close();
    }
}
/**
 * Enumeration实现原理
 */
		/*
		final Iterator<FileInputStream> it = al.iterator();
		Enumeration<FileInputStream> en = new Enumeration<FileInputStream>(){

			@Override
			public boolean hasMoreElements() {

				return it.hasNext();
			}

			@Override
			public FileInputStream nextElement() {

				return it.next();
			}

		};*/