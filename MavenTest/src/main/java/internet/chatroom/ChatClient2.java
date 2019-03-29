package internet.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * description：
 *
 * @author ajie
 * data 2018/10/19 21:27
 */
public class ChatClient2 {
    public static void main(String[] args){
        try{
            Socket socket = new Socket("127.0.0.1", 555);
            System.out.println("客户端启动");

            PrintWriter os = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

            new ClientThread(socket).start();

            String line;
            line = sin.readLine();
            while (!"over".equals(line)){
                os.println(line);
                os.flush();
                line = sin.readLine();
            }

            os.close();
            is.close();
            socket.close();
            System.out.println("客户端关闭");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
