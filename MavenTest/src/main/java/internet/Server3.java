package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * description：服务器端，端对端通信
 *
 * @author ajie
 * data 2018/10/18 12:48
 */
public class Server3 {

    public static void main(String[] args) throws IOException {
        // 结束标识
        String flag = "over";

        ServerSocket server = new ServerSocket(666);
        System.out.println("服务器端开始");

        Socket socket = server.accept();
        System.out.println("建立连接");

        PrintWriter os = new PrintWriter(socket.getOutputStream());
        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("客户端：" + is.readLine());
        String line;
        line = sin.readLine();

        while (!line.equals(flag)){
            os.println(line);
            os.flush();
            System.out.println("客户端：" + is.readLine());
            line = sin.readLine();
        }

        os.close();
        is.close();
        socket.close();
        server.close();
        System.out.println("断开连接");
    }
}
