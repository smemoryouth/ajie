package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * description：客户端，双端通信
 *
 * @author ajie
 * data 2018/10/18 12:39
 */
public class Client3 {
    public static void main(String[] args) throws IOException {
        String flag = "over";
        int port = 123;
        String host = "127.0.0.1";
        Socket socket = new Socket(host, port);
        System.out.println("客户端开始");

        PrintWriter os = new PrintWriter(socket.getOutputStream());
        BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

        String line = sin.readLine();
        while (!line.equals(flag)){
            os.println(line);
            os.flush();
            System.out.println("服务器：" + is.readLine());
            line = sin.readLine();
        }

        os.close();
        is.close();
        socket.close();
        System.out.println("客户端关闭");
    }
}
