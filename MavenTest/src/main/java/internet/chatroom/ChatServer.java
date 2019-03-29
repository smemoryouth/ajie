package internet.chatroom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ServerThread extends Thread {

    Socket socket;
    /**
     * 客户端ID
     */
    String clientID;
    /**
     * 输出流
     */
    private PrintWriter os;

    ServerThread(Socket socket) {
        this.socket = socket;
        this.clientID = socket.getInetAddress().toString() + socket.getPort();
    }

    @Override
    public void run() {
        try {
            String line;
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.os = new PrintWriter(socket.getOutputStream());

            try {
                while (true) {
                    if (!"over".equals(line = is.readLine())) {
                        System.out.println(this.clientID + ":" + line);
                        ChatServer.sendClients(this.clientID + ":" + line);
                    } else {
                        // 下线
                        ChatServer.disconnect(this);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            os.close();
            is.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void send(String msg) {
        os.println(msg);
        os.flush();
    }
}

/**
 * description：一对多服务器端
 *
 * @author ajie
 * data 2018/10/18 16:28
 */
public class ChatServer {
    /**
     * 客户端队列
     */
    private static ArrayList<ServerThread> clients = new ArrayList<>(10);

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(555, 10);
            System.out.println("服务端开启");
        } catch (IOException e) {
            System.out.println("error : 服务器开启失败");
        }
        while (true) {
            assert serverSocket != null : "服务器异常关闭";
            Socket socket = serverSocket.accept();
            System.out.println(socket.getLocalAddress() + "建立链接");
            ServerThread c = new ServerThread(socket);
            clients.add(c);
            c.start();
        }
    }

    /**
     * 客户端转发信息
     *
     * @param msg
     */
    static synchronized void sendClients(String msg) {
        for (ServerThread serverThread : clients) {
            serverThread.send(msg);
        }
    }

    /**
     * 下线
     *
     * @param serverThread
     */
    static synchronized void disconnect(ServerThread serverThread) {
        try {
            serverThread.socket.close();
        } catch (IOException e) {
            System.out.println(serverThread.clientID + "关闭异常");
            e.printStackTrace();
        } finally {
            System.out.println(serverThread.clientID + "退出链接");
            clients.remove(serverThread);
        }
    }
}
