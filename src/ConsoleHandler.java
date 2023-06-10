import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class ConsoleHandler {
    Parser parser;
    Tree<?> tree = new Tree<>();

    public boolean terminated = false;
    private final Map<String, Function<String, Boolean>> commands = Map.of(
            "Insert", p -> {tree.insert(parser.parse(p)); return true;},
            "Remove", p -> {tree.delete(parser.parse(p)); return true;},
            "Search", p -> tree.search(parser.parse(p)) != null,
            "Draw", p -> {tree.drawLeaves(); return true;},
            "Exit", p -> {terminated = true; return true;}
    );

    public ConsoleHandler(String type){
        parser = new Parser(type);
    }

    public void handle(String command){
        command += " null";
        String[] args = command.split(" ");

        commands.get(args[0]).apply(args[1]);
    }

    public String getCommands(){
        return commands.keySet().toString();
    }
}
