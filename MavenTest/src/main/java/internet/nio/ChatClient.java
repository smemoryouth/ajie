package internet.nio;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

class ClientThread implements Runnable {
    private String ip;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    public ClientThread(String ip, int port) {
        this.ip = ip;
        this.port = port;

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            System.out.println("客户端" + ip + "开始");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            socketChannel.connect(new InetSocketAddress(8888));
            socketChannel.register(selector, SelectionKey.OP_WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                //获取注册在selector上的所有的就绪状态的serverSocketChannel中发生的事件
                Set<SelectionKey> set = selector.selectedKeys();
                Iterator<SelectionKey> iterator = set.iterator();
                SelectionKey key;
                while (iterator.hasNext()) {
                    key = iterator.next();
                    if (!key.isValid()) {
                        key.cancel();
                        continue;
                    }
                    handleInput(key);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                } else {
                    System.out.println("error");
                }
            }

            if (key.isReadable()) {
                ByteBuffer bf = ByteBuffer.allocate(1024);
                int bytes = sc.read(bf);
                if (bytes > 0) {
                    bf.flip();
                    byte[] byteArray = new byte[bf.remaining()];
                    bf.get(byteArray);
                    String msg = new String(byteArray);
                    System.out.println("服务器 ：" + msg);
                } else if (bytes < 0) {
                    key.cancel();
                    sc.close();
                }
            }
        }
    }


    private void doWrite(SocketChannel sc) throws IOException {
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
        ByteBuffer bf = ByteBuffer.allocate(sin.readLine().length());
        bf.put(sin.readLine().getBytes());
        bf.flip();
        sc.write(bf);
    }
}

/**
 * description：
 *
 * @author ajie
 * data 2018/10/23 21:52
 */
public class ChatClient {
    public static void main(String[] args) {
        ScheduledExecutorService pool = new ScheduledThreadPoolExecutor(3,
                new BasicThreadFactory.Builder().namingPattern("mypool-%d").daemon(false).build());
        pool.execute(new ClientThread("127.0.0.1", 8800));
    }
}
