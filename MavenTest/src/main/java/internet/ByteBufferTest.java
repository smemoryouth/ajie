package internet;

import java.nio.ByteBuffer;

/**
 * description：ByteBuffer实例
 * NIO中的缓冲区，主要负责和channel交互，进行数据的读写缓存
 *
 * @author ajie
 * data 2018/10/22 19:14
 */
public class ByteBufferTest {
    public static void main(String[] args){
        ByteBuffer buffer = ByteBuffer.allocate(100);
        String msg = "hello";
        buffer.put(msg.getBytes());
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        System.out.println(new String(bytes));
        buffer.clear();

    }
}
