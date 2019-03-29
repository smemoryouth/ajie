package zhongfu;

import java.io.*;

class FilterEndwith implements FilenameFilter {

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(".txt");
    }
}

/**
 * description：
 * 1	public final int read(byte[] r, int off, int len)throws IOException
 * 从所包含的输入流中将 len 个字节读入一个字节数组中。如果len为-1，则返回已读字节数。
 * 2	Public final int read(byte [] b)throws IOException
 * 从所包含的输入流中读取一定数量的字节，并将它们存储到缓冲区数组 b 中。
 * 3
 * public final Boolean readBooolean()throws IOException,
 * public final byte readByte()throws IOException,
 * public final short readShort()throws IOException
 * public final Int readInt()throws IOException
 * 从输入流中读取字节，返回输入流中两个字节作为对应的基本数据类型返回值。
 * 4	public String readLine() throws IOException
 * 从输入流中读取下一文本行。
 *
 * @author ajie
 * data 2018/12/12 14:20
 */
public class DataTest {
    public static void main(String[] args)throws IOException{

        DataInputStream in = new DataInputStream(new FileInputStream("HelloWorld.txt"));
//        DataOutputStream out = new DataOutputStream(new FileOutputStream("test1.txt"));
        BufferedReader d  = new BufferedReader(new InputStreamReader(in));

        String count;
        while((count = d.readLine()) != null){
            String u = count.toUpperCase();
            System.out.println(u);
//            out.writeBytes(u);
        }
        d.close();
        //out.close();
    }
}
