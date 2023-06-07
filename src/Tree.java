public class Tree<T extends Comparable<T>> {
   public Node<T> root = null;

   public Tree() {

   }

   public Node<T> search(T t){
      if (root != null){
         Node<T> node = root;
         while (node != null && node.key != t){
            if (t.compareTo(node.key) < 0) {
               node = node.left;
            } else if (t.compareTo(node.key) > 0) {
               node = node.right;
            }
         }
         return node;
      } else {
         return null;
      }

   }
   public void insert(T t) {
      if (root == null) {
         root = new Node<>(t, new Node<>(null, null));
      } else {
         root.insert(t);
      }
   }

   public void delete(T t){
      search(t).delete();

   }
   public void draw() {
      Node<T> row = root;
      while (row != null) {
         Node<T> temp = row;
         while (temp != null) {
            System.out.print(temp.key + "<-" + temp.parent.key + " ");
            temp = temp.nextNode();
         }
         row = nextRow(row);
         System.out.println();
      }
   }

   private Node<T> nextRow(Node<T> node) {
      Node<T> temp = node;
      Node<T> output = root.firstChild();

      while (temp.parent.key != null) {
         temp = temp.parent;
         output = output.firstChild();
      }
      return output;
   }
}
