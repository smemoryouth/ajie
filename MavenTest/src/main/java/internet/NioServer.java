package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * description：NIO实例
 *
 * @author ajie
 * data 2018/10/19 19:57
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        int port = 123;

        // 获取ServerSocketChannel实例
        ServerSocketChannel sc = ServerSocketChannel.open();
        // 绑定通信端口
        sc.bind(new InetSocketAddress(port));
        // 设置ServerSocket实例为非阻塞
        sc.configureBlocking(false);

        // 获取selector实例，NIO中所有的信道信息注册到Selector上统一处理
        Selector s = Selector.open();

        // 设置当前信道接受网络请求
        sc.register(s, SelectionKey.OP_ACCEPT);
        System.out.println("服务端开始");

        // 开辟50字节的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(50);
        // 获取信道数量，本身是阻塞的，至少等待一个注册的信道时间发生才会立马返回,判断是否有事件发生
        while (s.select() > 0) {
            Set<SelectionKey> keys = s.selectedKeys();
            // 遍历所有信道
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (!key.isValid()) {
                    // 信道无效则删除
                    key.cancel();
                    continue;
                }

                if (key.isAcceptable()) {
                    System.out.println("当前信道为接受请求");
                    // 信道接受请求
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    // 获取相连的客户端请求
                    SocketChannel client = channel.accept();
                    // 设置当前信道非阻塞
                    client.configureBlocking(false);
                    // 注册当前信道到selector上，并设置为读
                    client.register(s, SelectionKey.OP_READ);
                    // 删除
                    key.cancel();
                    continue;
                }

                if (key.isReadable()) {
                    // 当前信道为可读事件
                    SocketChannel channel = (SocketChannel)key.channel();
                    // 读取信道数据
                    int read = channel.read(buffer);
                    String msg = new String(buffer.array(), 0, read);
                    System.out.println("客户端：" + msg);
                    buffer.clear();
//                    String recMsg = msg + "\n";
//                    buffer.put(recMsg.getBytes()).flip();
//                    // 往信道写入数据
//                    channel.write(buffer);
//                    buffer.clear();
                    channel.register(s, SelectionKey.OP_WRITE);
                    key.cancel();
                    continue;
                }
                if (key.isWritable()){
                    SocketChannel channel = (SocketChannel)key.channel();
                    BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
                    buffer.put(sin.readLine().getBytes()).flip();
                    channel.write(buffer);
                    System.out.println("服务器：" + sin);
                    buffer.clear();
                    channel.register(s, SelectionKey.OP_READ);
                    key.cancel();
                }
            }
        }
        // 关闭selector
        s.close();
        // 关闭ServerSocket信道
        sc.close();
    }
}
