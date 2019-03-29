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
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description：NIO多线程服务器
 *
 * @author 龚德浙
 * data 2018/10/27 9:41
 */
public class MutilThreadNioServer1 {

    static class MainNio {
        private Selector selector;
        private ServerSocketChannel server;
        private LinkedList<WorkerTask> queue;

        public MainNio(int port, WorkerTask[] tasks) throws IOException {
            selector = Selector.open();
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            server.register(selector, SelectionKey.OP_ACCEPT);

            queue = new LinkedList <>();
            for (WorkerTask task:tasks) {
                queue.add(task);
            }

            System.out.println("服务端 占用端口port："+port +" 成功，并成功启动");
        }

        public void startServer() throws IOException {
            while (selector.select() > 0) {
                Iterator <SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (!key.isValid()) {
                        key.cancel();
                        continue;
                    }

                    if (key.isAcceptable()) {
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        SocketAddress address = channel.getRemoteAddress();
                        System.out.println("客户端："+ address +" 上线成功");

                        //采用轮询给每个子线程注册新用户
                        WorkerTask task = queue.poll();

                        task.setChannel(channel);
                        //wakeup一定是会把阻塞的selector唤醒一次的
                        Selector selector = task.getSelector();
                        selector.wakeup();
                        channel.register(selector, SelectionKey.OP_READ);
                        queue.offer(task);
                    }
                }
            }
        }


    }

    static class WorkerTask implements Runnable{
        //Socket通道
        private SocketChannel channel;
        //选择器
        private Selector selector;

        private ByteBuffer buffer;

        /**
         * 构造函数
         */
        public WorkerTask() throws IOException {
            selector = Selector.open();
            buffer = ByteBuffer.allocate(1024);
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
//                    Thread.currentThread().start();
                    int num = selector.select();
                    if (num <= 0) {
                        continue;
                    }
                    System.out.println(Thread.currentThread().getName()+" 处理消息。。。");
//                    System.out.println("select num :"+select);
                    Iterator <SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if (!key.isValid()) {
                            key.cancel();
                            continue;
                        }

                        if (key.isReadable()) {

                            SocketChannel channel = (SocketChannel) key.channel();
                            SocketAddress address = channel.getRemoteAddress();
                            StringBuffer sBuffer = new StringBuffer();
                            while (channel.read(buffer) > 0) {
                                buffer.flip();
                                int num1 = buffer.remaining();
                                sBuffer.append(new String(buffer.array(), 0, num1));
                                buffer.clear();
                            }

                            String msg = sBuffer.toString();
                            if (" ".equals(msg)) {
                                continue;
                            }

                            if (msg.trim().startsWith("exit")) {
                                System.out.println("用户端 "+address +" 下线了");
                                channel.close();
                                key.cancel();
                                return;
                            }

                            System.out.println(Thread.currentThread().getName()+" 处理用户端 "+ address +" 发送消息："+msg);
                            buffer.clear();
                            buffer.put("[server recv]ok \n".getBytes());
                            buffer.flip();
                            channel.write(buffer);
                            buffer.clear();

                        }
                    }
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        }

        public SocketChannel getChannel() {
            return channel;
        }

        public void setChannel(SocketChannel channel) {
            this.channel = channel;
        }

        public Selector getSelector() {
            return selector;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"channel\":")
                    .append(channel);
            sb.append(",\"selector\":")
                    .append(selector);
            sb.append(",\"buffer\":")
                    .append(buffer);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        final int CPU_NUM = 4;
        int port = 8888;
        ExecutorService pool = Executors.newFixedThreadPool(CPU_NUM);

        WorkerTask[] tasks = new WorkerTask[CPU_NUM];
        for (int i=0; i<CPU_NUM; i++) {
            tasks[i] = new WorkerTask();
            pool.submit(tasks[i]);
        }

        MainNio nioServer = new MainNio(port, tasks);
        nioServer.startServer();

        pool.shutdown();
    }
}
