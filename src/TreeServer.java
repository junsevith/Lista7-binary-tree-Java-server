import java.net.ServerSocket;

public class TreeServer {

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            while (true) new TreeHandler(serverSocket.accept()).start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        TreeServer server = new TreeServer();
        server.start(6666);
    }
}
