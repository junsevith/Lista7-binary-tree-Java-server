import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      ConsoleHandler handler = new ConsoleHandler();

      System.out.println("Podaj typ drzewa " + handler.getParsers());
      while (!handler.setParser(scanner.nextLine())){
         System.out.println("Niepoprawny typ drzewa");
      }
      System.out.println("Utworzono drzewo");

      System.out.println("Podaj komendę " + handler.getCommands());
      while (!handler.terminated){
         System.out.print(">>");
         System.out.println(handler.handle(scanner.nextLine()).replace("\0","\n"));
      }


//      for (String i : new String[]{"pies","kot","żaba","żółw","wiewiórka","jeleń","wieloryb","małpa","rekin"}) {
//         tree.insert(parser.parse(i));
//      }
//      for (int i : new Integer[]{7,3,6,2,9,5,1,4,8}) {
//         tree.insert(parser.parse(String.valueOf(i)));
//      }
//      System.out.println(tree.root.left.key);
//      tree.draw();
//      tree.delete(parser.parse("1"));
//      tree.drawLeaves();
//      System.out.println(parser.parse("12").getClass());

   }
}