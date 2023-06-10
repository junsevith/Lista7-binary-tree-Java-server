import java.net.*;
import java.io.*;

public class TreeServer {
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) new TreeHandler(serverSocket.accept()).start();
    }

    public static void main(String[] args) throws IOException {
        TreeServer server = new TreeServer();
        server.start(6666);
    }
}
