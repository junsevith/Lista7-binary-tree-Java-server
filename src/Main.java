public class Main {
   public static void main(String[] args) {
      Tree<Integer> tree = new Tree<>();
      for (int i : new Integer[]{3,1,4,1,5,9,2,6,5,3,5,12,44,0,11}) {
         tree.insert(i);
      }
//      tree.delete(6);

//      System.out.println(tree.root.left.key);
      tree.draw();
   }
}