import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Podaj typ drzewa " + Parser.getOptions());
      ConsoleHandler handler = new ConsoleHandler(scanner.nextLine());

      System.out.println("Utworzono drzewo");
      System.out.println("Podaj komendę " + handler.getCommands());
      while (!handler.terminated){
         handler.handle(scanner.nextLine());
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