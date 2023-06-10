import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class TreeHandler extends Thread {
    private final Socket clientSocket;
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
                System.out.println("Connected " + clientSocket);

                out.println("Podaj typ drzewa " + handler.getParsers());
                while (!handler.setParser(in.readLine())) {
                    out.println("Niepoprawny typ drzewa");
                }

                out.println("Utworzono drzewo\0Podaj komendę " + handler.getCommands() + "\0>> ");
                while (!handler.terminated) {
                    out.println(handler.handle(in.readLine()).replace('\n','\0') + "\0>>");
                }
            }

            System.out.println("Disconnected " + clientSocket);
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
