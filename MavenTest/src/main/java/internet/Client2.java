package internet;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * description：客户端，接受客户端回复的信息
 *
 * @author ajie
 * data 2018/10/17 20:46
 */
public class Client2 {
    public static void main(String[] args) throws IOException {
        int port = 8888;
        String host = "127.0.0.1";
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        // 创建于服务器端的连接,两种方式
//        Socket socket = new Socket(host, port);
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        System.out.println("客户端开始");

        // 向服务器发送消息
        Scanner in = new Scanner(System.in);
        OutputStream os = socket.getOutputStream();
        InputStream is = socket.getInputStream();
        BufferedReader reader  = new BufferedReader(new InputStreamReader(is));

        while (in.hasNext()) {
            String s = in.nextLine();
            os.write((s + "\n").getBytes());
            os.flush();
            System.out.println("server recv：" + reader.readLine());
        }

        // 关闭资源
        in.close();
        os.close();
        socket.close();
        System.out.println("客户端关闭");
    }
}
