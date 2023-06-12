import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.function.Function;

public class ConsoleHandler {
   private final Parser parser = new Parser();
   private final Tree<?> tree = new Tree<>();

   public boolean terminated = false;
   LinkedHashMap<String, Function<String, String>> commands = new LinkedHashMap<>();

   public ConsoleHandler() {
      commands.put("insert", p -> tree.insert(parser.parse(p)));
      commands.put("search", p -> tree.search(parser.parse(p)) != null ? "Znaleziono element o podanym kluczu" : "Nie znaleziono elementu");
      commands.put("draw", p -> tree.draw());
      commands.put("remove", p -> tree.delete(parser.parse(p)));
      commands.put("help", p -> getCommands());
      commands.put("exit", p -> {
         terminated = true;
         return "Wychodzenie...";
      });


   }

   public boolean setParser(String string) {
      return parser.setConverter(string);
   }


   public String handle(String command) {
      String[] args = command.split(" ");
      if (args.length < 2) {
         args = (command + " null").split(" ");
      }
      Function<String, String> function = commands.get(args[0]);
      if (function != null) {
         try {
            StringBuilder msg = new StringBuilder();
            Arrays.stream(args).skip(1).forEach(p -> msg.append(function.apply(p)).append("\n"));
//                return function.apply(args[1]);
            return msg.toString().strip();
         } catch (Exception e) {
            return e.getMessage();
         }
      } else {
         return "Niepoprawna komenda";
      }

   }

   public String getCommands() {
      return commands.keySet().toString();
   }

   public String getParsers() {
      return parser.getOptions();
   }

   public String getType() {
      return parser.getType();
   }
}
