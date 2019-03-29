package internet;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * description：服务器2，带回复的通信
 *
 * @author ajie
 * data 2018/10/17 20:46
 */
public class Server2 {
    public static void main(String[] args) throws IOException {

        int port = 6666;

        // 创建socket对象
        ServerSocket server = new ServerSocket(port);
        System.out.println("服务器端开始");

        // 获取与服务器端相连的客户端域名
        Socket socket = server.accept();
        System.out.println("建立连接");

        // 获取客户端发送的数据
        Scanner in = new Scanner(socket.getInputStream());
        OutputStream out = socket.getOutputStream();

        while (in.hasNext()) {
            String s = in.nextLine();
            System.out.println("client info : " + s);
            out.write("ok\n".getBytes());
        }

        in.close();
        out.close();
        socket.close();
        server.close();
        System.out.println("断开连接");
    }
}
