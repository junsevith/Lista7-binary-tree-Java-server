public class Node<T extends Comparable<T>> {
   public T key;
   public Node<T> parent;
   public Node<T> left = null;
   public Node<T> right = null;

   public Node(T key, Node<T> parent) {
      this.key = key;
      this.parent = parent;
   }

   public void insert(T t) {
      if (t.compareTo(key) == 0) {
         System.out.println("Próba dodania elementu który już znajduje się w tablicy, nie dodano: " + t);
         return;
      } else if (t.compareTo(key) < 0) {
         if (left == null) {
            left = new Node<>(t, this);
//            System.out.println("Dodano: " + t);
         } else {
            left.insert(t);
         }
      } else if (t.compareTo(key) > 0) {
         if (right == null) {
            right = new Node<>(t, this);
//            System.out.println("Dodano: " + t);
         } else {
            right.insert(t);
         }
      }

   }

   public void delete() {
      Node<T> node = null;
      if (left != null && right != null) {
         node = this.minVal();
      } else if (left != null || right != null) {
         node = left == null ? right : left;
      }
      this.replace(node);

   }

   private void replace(Node<T> node) {
      Node<T> prev = null;
      if (parent.left == this) {
         prev = parent.left;
         parent.left = node;
      } else if (parent.right == this) {
         prev = parent.right;
         parent.right = node;
      }

      if (node != null && prev != null) {
         node.parent = prev.parent;
         node.right = prev.right;
         node.left = prev.left;
         if (node.right != null) {
            node.right.parent = node;
         }
         if (node.left != null) {
            node.left.parent = node;
         }
      }
   }

   private Node<T> minVal() {
      if (this.left == null) {
         this.replace(null);
         return this;
      } else {
         return this.left.minVal();
      }
   }

   /**
    * Zwraca następny na prawo w aktualnym rzędzie element node, lub null gdy nie istnieje
    *
    * @return node
    */
   public Node<T> nextNode() {
      if (parent != null) {
         if (this == parent.left && parent.right != null) {
            return parent.right;
         } else {
            return parent.nextChild();
         }
      }
      return null;
   }

   private Node<T> nextChild() {
      Node<T> next = nextNode();
      if (next != null) {
         return next.firstChild();
      } else {
         return null;
      }

   }

   public Node<T> firstChild() {
      if (left != null) {
         return left;
      } else if (right != null) {
         return right;
      } else {
         return nextChild();
      }
   }


}
