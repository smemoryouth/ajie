package thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * description：文件复制，按线程个数确定每个线程复制的量
 *
 * @author ajie
 * data 2018/9/24 21:02
 */
public class Copy2 {
    public static void main(String[] args) {
        File file = new File("D:\\新建文件夹\\新建文件夹\\1.rar");
        int num = 10;
        System.out.println(file.length());
        String sourcePath = file.getAbsolutePath();
        String destPath = "D:\\新建文件\\2.rar";
        long l1 = System.currentTimeMillis();
        System.out.println(copyFile(num, sourcePath, destPath));
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }


    private static boolean copyFile(int threadNum, String sourcePath, String destPath) {
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            System.out.println("未知文件");
            return false;
        }
        if (new File(destPath).exists()) {
            System.out.println("文件已存在");
            return false;
        }
        // 最后的剩余大小
        long length = sourceFile.length() % threadNum;
        // 每个线程复制的长度
        long threadLength = sourceFile.length() / threadNum;
        for (int i = 0; i < threadNum; i++) {
            new FileThread((threadLength * i), (threadLength * (i + 1)), sourcePath, destPath).start();
        }
        if (length != 0) {
            new FileThread((threadLength * threadNum), threadLength * threadNum + length, sourcePath,
                    destPath).start();
        }
        return true;
    }


    static class FileThread extends Thread {
        private long begin;
        private long end;
        private RandomAccessFile in;
        private RandomAccessFile out;

        FileThread(long begin, long end, String sourcePath, String destPath) {
            this.begin = begin;
            this.end = end;
            try {
                this.in = new RandomAccessFile(sourcePath, "rw");
                this.out = new RandomAccessFile(destPath, "rw");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                in.seek(begin);
                out.seek(begin);
                int ch;
                byte[] buffer = new byte[(int) (end - begin)];
                while (begin < end && (ch = in.read(buffer)) != -1) {
                    begin += ch;
                    out.write(buffer, 0, ch);
                    System.out.println(Thread.currentThread().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
