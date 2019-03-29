package thread;

import java.io.*;

/**
 * description：文件夹的复制
 *
 * @author ajie
 * data 2018/9/22 18:42
 */
public class FileCopy {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        File sourceFile = new File("D:\\新建文件夹");
        File destFile = new File("D:\\新建文件");
       copyFile(sourceFile, destFile);
       long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

    private static void copyFile(File sourceFile, File destFile) {

        if (!sourceFile.exists()) {
            return;
        }
        if (!destFile.exists()) {
            destFile.getParentFile().mkdir();
        }
        if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles();
            if (files != null) {
                for (File file : files) {
                    File srcFile = new File(sourceFile, file.getName());
                    File desFile = new File(destFile, file.getName());
                    copyFile(srcFile, desFile);
                }
            }
        } else {
            copy(sourceFile, destFile);
        }
    }

    private static void copy(File sourceFile, File destFile) {
        BufferedInputStream in;
        BufferedOutputStream out;
        try {
            in = new BufferedInputStream(new FileInputStream(sourceFile));
            out = new BufferedOutputStream(new FileOutputStream(destFile));
            int ch;
            while ((ch = in.read()) != -1) {
                out.write(ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (out != null) {
//                try {
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    try {
//                        in.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
        }
    }
}
