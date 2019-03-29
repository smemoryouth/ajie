package internet.nio;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class ServerThread implements Runnable{

    @Override
    public void run() {

    }
}
/**
 * description：
 *
 * @author ajie
 * data 2018/10/23 21:36
 */
public class ChatServer {
    public static void main(String[] args) throws IOException {
        ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(3,
                new BasicThreadFactory.Builder().namingPattern("mypool-%d").daemon(false).build());

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //将serverSocketChannel设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //serverSocketChannel的socket绑定服务端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8888));

        //将serverSocketChannel 注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器开始");

        while (true) {
            //当注册的事件到达时，方法返回
            selector.select();
            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator ite = selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                ite.remove();

                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // 获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    // 设置成非阻塞
                    channel.configureBlocking(false);
                    //在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限。
                    channel.register(selector, SelectionKey.OP_READ);
                    // 获得了可读的事件
                } else if (key.isReadable()) {
                    //取消可读触发标记
                    key.interestOps(key.interestOps() & (~SelectionKey.OP_READ));
                    //加入线程池
                    pool.execute(new ThreadHandlerChannel(key));
                }
            }
        }

    }

}
