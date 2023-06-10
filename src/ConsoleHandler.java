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
        commands.put("draw", p -> tree.draw().replace('\n', '\0'));
        commands.put("remove", p -> tree.delete(parser.parse(p)));
        commands.put("exit", p -> {
            terminated = true;
            return "Wychodzenie...";
        });


    }

    public boolean setParser(String string) {
        return parser.setConverter(string);
    }


    public String handle(String command) {
        command += " null";
        String[] args = command.split(" ");
        Function<String, String> function = commands.get(args[0]);
        if (function != null) {
            try {
                return function.apply(args[1]);
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
    public String getParsers(){
        return parser.getOptions();
    }
}
