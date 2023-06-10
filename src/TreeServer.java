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

    private static class TreeHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public TreeHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                ConsoleHandler handler = new ConsoleHandler();
                if (in.readLine().equals("start")) {
                    System.out.println("Connected " + clientSocket.toString());

                    out.println("Podaj typ drzewa " + handler.getParsers());
                    while (!handler.setParser(in.readLine())) {
                        out.println("Niepoprawny typ drzewa");
                    }

                    out.println("Utworzono drzewo\0Podaj komendę " + handler.getCommands() + "\0>> ");
                    while (!handler.terminated) {
                        out.println(handler.handle(in.readLine()) + "\0>>");
                    }
                }
                System.out.println("Disconnected " + clientSocket.toString());
                in.close();
                out.close();
                clientSocket.close();
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }
}
