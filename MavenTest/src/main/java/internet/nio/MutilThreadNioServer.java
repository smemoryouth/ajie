package internet.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * desc:多线程实现NIO，实现这个
 * <p>
 * 采用主线程接收请求，将接收到的用户请求通道提交给子线程
 * 子线程专门连接通道的读写请求
 *
 * @author :gongdezhe
 * @date:2018/10/26
 */
public class MutilThreadNioServer {
    /**
     * 线程池
     */
    ExecutorService pool = Executors.newCachedThreadPool();
    /**
     * 服务端接收连接通道
     */
    private ServerSocketChannel server;
    /**
     * 接收连接的选择器
     */
    private Selector selector;

    public static void main(String[] args) throws IOException {
        int port = 9999;
        MutilThreadNioServer server = new MutilThreadNioServer();
        server.bind(port);
        server.start();
    }

    /**
     * serverSocket实例并绑定端口
     *
     * @param port
     * @throws IOException
     */
    public void bind(int port) throws IOException {
        //Server实例
        server = ServerSocketChannel.open();
        //选择器实例
        selector = Selector.open();
        server.bind(new InetSocketAddress(port));

        server.configureBlocking(false);

        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端：port " + port + " 占用成功 服务启动成功");
    }

    /**
     * 主线程选择器进行选择处理并且
     *
     * @throws IOException
     */
    public void start() throws IOException {
        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isValid() && key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel channel = server.accept();
                    SocketAddress address = channel.getRemoteAddress();
                    System.out.println("客户端：" + address + " 已上线");
                    ThreadTask task = new ThreadTask(channel);
                    pool.submit(task);
                }
            }
        }
        selector.close();
        server.close();
        pool.shutdown();
    }

    class ThreadTask implements Runnable {
        /**
         * 子线程选择器
         */
        Selector selector;
        /**
         * 缓存
         */
        ByteBuffer buffer;

        ThreadTask(SocketChannel channel) throws IOException {
            selector = Selector.open();
            channel.configureBlocking(false);
            channel.register(this.selector, SelectionKey.OP_READ);
            this.buffer = ByteBuffer.allocate(1024);
            SocketAddress address = channel.getLocalAddress();
            System.out.println("线程：" + Thread.currentThread().getName() + " 处理 " + address + "用户请求");
        }

        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();
                        if (key.isValid() && key.isReadable()) {
                            SocketChannel channel = (SocketChannel) key.channel();

                            SocketAddress address = channel.getLocalAddress();

                            StringBuffer sBuffer = new StringBuffer();
                            while (channel.read(buffer) > 0) {
                                buffer.flip();
                                int num = buffer.remaining();
                                sBuffer.append(new String(buffer.array(), 0, num));
                                buffer.clear();
                            }
                            String msg = sBuffer.toString();
                            if ("".equals(msg)) {
                                continue;
                            }
                            if (msg.trim().startsWith("exit")) {

                                channel.close();
                                key.cancel();
                                System.out.println(address + "下线了");
                                return;
                            }

                            System.out.println("客户端" + address + " 发送消息： " + msg);
                            buffer.clear();
                            buffer.put("[server recv] ok\n".getBytes());
                            buffer.flip();
                            channel.write(buffer);
                            buffer.clear();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

