import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
   public static void main(String[] args) {
      LinkedHashMap<String,Runnable> treeTypes = new LinkedHashMap<>();
      treeTypes.put("integer",()-> new ConsoleHandler<>(Integer::parseInt).runTerminal());
      treeTypes.put("double",()-> new ConsoleHandler<>(Double::parseDouble).runTerminal());
      treeTypes.put("string",()-> new ConsoleHandler<>(Function.identity()).runTerminal());

      System.out.println("Podaj typ drzewa " + treeTypes.keySet());
      Scanner scanner = new Scanner(System.in);
      Runnable handler;
      while ((handler = treeTypes.get(scanner.nextLine())) == null){
         System.out.println("Niepoprawny typ drzewa");
      }
      System.out.println("Utworzono drzewo");
      handler.run();

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