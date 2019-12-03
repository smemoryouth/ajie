package xfuxi;

import java.io.*;

/**
 * description：文件复制
 *
 * @author ajie
 * data 2018/9/17 14:02
 */
public class FileCopy {
    private static void copyFolder(File src, File dest) throws IOException {
        if (!src.exists()){
            return;
        }
        if (!dest.exists()){
            dest.getParentFile().mkdir();
        }
        if (src.isDirectory()) {
            File[] files = src.listFiles();
            assert files != null;
            for (File file : files) {
                if (file.getName().endsWith(".java")) {
                    String name = file.getName();
                    String[] names = name.split("\\.", 2);
                    System.out.println(names[0]);
                    String newName = names[0] + ".ok";
                    file.renameTo(dest);

                    File srcFile = new File(src, file.getName());
                    File destFile = new File(dest, file.getName());
                    // 递归
                    copyFolder(srcFile, destFile);
                }
            }
        } else {
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }
    public static void main(String[] args) throws IOException {
        File src = new File("D:\\新建文件夹");
        File dest = new File("D:\\新建文件");
        copyFolder(src, dest);
    }
}

