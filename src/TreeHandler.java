import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.function.Function;

public class TreeHandler extends Thread {
    private final Socket clientSocket;

    public TreeHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            LinkedHashMap<String,Runnable> treeTypes = new LinkedHashMap<>();
            treeTypes.put("integer",()-> new ConsoleHandler<>(Integer::parseInt).runServer(clientSocket));
            treeTypes.put("double",()-> new ConsoleHandler<>(Double::parseDouble).runServer(clientSocket));
            treeTypes.put("string",()-> new ConsoleHandler<>(Function.identity()).runServer(clientSocket));

            if (in.readLine().equals("start")) {
                System.out.println("Connected " + clientSocket);

                out.println("Podaj typ drzewa " + treeTypes.keySet());
                Runnable handler;
                while ((handler = treeTypes.get(in.readLine())) == null){
                    out.println("Niepoprawny typ drzewa");
                }
                handler.run();

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
