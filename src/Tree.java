public class Tree<T extends Comparable<T>> {
    public Node<T> root = null;

    public Tree() {
    }

    /**
     * Dodaje element do drzewa
     * @param t klucz dodawanego elementu
     * @return informacja o tym, czy dodawanie się powiodło
     */
    public String insert(T t) {
        if (root == null) {
            Node<T> anchor = new Node<>(null, null);
            root = new Node<>(t, anchor);
            anchor.right = root;
            return "Utworzono element root: " + t;
        } else {
            return root.insert(t);
        }
    }

    /**
     * Przeszukuje drzewo, szukając elementu o kluczu t
     * @param t klucz szukanego elementu
     * @return element o podanym kluczu lub null, gdy nie istnieje
     */
    public Node<T> search(T t) {
        Node<T> node = root;
        while (node != null && !node.key.equals(t)) {
            if (t.compareTo(node.key) < 0) {
                node = node.left;
            } else if (t.compareTo(node.key) > 0) {
                node = node.right;
            }
        }
        return node;
    }

    /**
     * Rysuje drzewo
     * @return narysowane drzewo
     */
    public String draw() {
        if (root != null){
            return root.drawLine("", "");
        } else {
            return "Drzewo nie posiada elementów";
        }

    }

    public String delete(T t) {
        Node<T> node = search(t);

        if (node != null) {
            node.delete();
            if (node == root) {
                try {
                    System.out.println(root.parent.left.key);
                } catch (Exception e){
                    System.out.println("null");
                }
                try{
                    System.out.println(root.parent.right.key);
                } catch (Exception e){
                    System.out.println("null");
                }


                root = root.parent.left != null ? root.parent.left : root.parent.right;
            }
            return "Usunięto: " + t;
        }
        return "Nie znaleziono węzła o podanym kluczu: " + t;
    }

    public void oldDraw() {
        Node<T> row = root;
        while (row != null) {
            Node<T> temp = row;
            while (temp != null) {
                System.out.print(temp.key + "<-" + (temp.parent != null ? temp.parent.key : null) + " ");
                temp = temp.nextNode();
            }
            row = nextRow(row);
        }
    }


    private Node<T> nextRow(Node<T> node) {
        Node<T> temp = node;
        Node<T> output = root.firstChild();

        while (temp.parent != null) {
            temp = temp.parent;
            output = output.firstChild();
        }
        return output;
    }
}
