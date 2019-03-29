package internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * description：客户端
 *
 * @author ajie
 * data 2018/10/17 19:34
 */
public class Client {
    public static void main(String[] args) throws IOException {
        int port = 6666;
        String host = "127.0.0.1";
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        // 创建于服务器端的连接,两种方式
//        Socket socket = new Socket(host, port);
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        System.out.println("客户端开始");

        // 向服务器发送消息
        OutputStream out = socket.getOutputStream();
        InputStream scanner = System.in;

        try {
            while (true) {
                if (scanner != null) {
                    out.write(scanner.read());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            out.close();
            socket.close();
        }
        System.out.println("客户端关闭");
    }
}
