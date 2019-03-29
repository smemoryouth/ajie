package netty.classtest.tunlun;

public class ServerMain {
    public static void main(String[] args) {
        Server server = new Server();
        server.bind(9999);
    }
}
