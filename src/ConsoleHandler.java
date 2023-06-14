import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleHandler<T extends Comparable<T>>{
   private final Tree<T> tree = new Tree<>();

   public boolean terminated = false;
   LinkedHashMap<String, Function<String, String>> commands = new LinkedHashMap<>();

   public ConsoleHandler(Function<String, T> parser) {

      commands.put("insert", p -> tree.insert(parser.apply(p)));
      commands.put("search", p -> tree.search(parser.apply(p)) != null ? "Znaleziono element o podanym kluczu" : "Nie znaleziono elementu");
      commands.put("draw", p -> tree.draw());
      commands.put("remove", p -> tree.delete(parser.apply(p)));
      commands.put("help", p -> getCommands());
      commands.put("exit", p -> {
         terminated = true;
         return "Wychodzenie...";
      });


   }


   public String handle(String command) {
      if (command == null) {
         return "Brak komendy";
      }
      String[] args = command.split(" ");
      if (args.length < 2) {
         args = (command + " null").split(" ");
      }

      Function<String, String> function = commands.get(args[0]);
      if (function != null) {
         StringBuilder msg = new StringBuilder();
         Arrays.stream(args).skip(1).forEach(p -> msg.append(function.apply(p)).append("\n"));
//                return function.apply(args[1]);
         return msg.toString().strip();
      } else {
         return "Niepoprawna komenda";
      }

   }

   public String getCommands() {
      return commands.keySet().toString();
   }

   public void runTerminal() {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Podaj komendę " + getCommands());
      while (!terminated) {
         System.out.print(">> ");
         System.out.println(handle(scanner.nextLine()));
      }
   }

   public void runServer(Socket socket) {
      try {
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

         out.println("Podaj komendę " + getCommands() + "\0>> ");
         while (!terminated) {
            out.println(handle(in.readLine()).replace('\n', '\0') + "\0>> ");
         }

         in.close();
         out.close();
         socket.close();
      } catch (Exception e){
         System.out.println(e);
      }
   }
}
