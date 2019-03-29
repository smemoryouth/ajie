package zhongfu;

import java.io.RandomAccessFile;

/**
 * description：随机访问流之对汉字的处理
 *
 * @author ajie
 * data 2018/12/17 14:13
 */
public class RandomAccessPrint {
    public static void main(String[] args) {
        String path = "HelloWorld.txt";
        print(path);
    }

    private static void print(String path) {
        try {
            RandomAccessFile file = new RandomAccessFile(path, "rw");
            long len = file.length();
            while (len-- != 0) {
                file.seek(len);
                char c = (char) file.readByte();
                if (c <= 255 && c >= 0) {
                    System.out.print(c);
                } else {
                    len--;
                    byte[] bytes = new byte[2];
                    file.read(bytes);
                    System.out.print(new String(bytes, "utf-8"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
