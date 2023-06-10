public class Tree<T extends Comparable<T>> {
    public Node<T> root = null;

    public Tree() {
    }

    public String insert(T t) {
        if (root == null) {
            root = new Node<>(t, null);
            return "Utworzono element root: " + t;
        } else {
            return root.insert(t);
        }
    }

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

    public String draw() {
        return root.drawLine("", "");
    }

    public String delete(T t) {
        Node<T> node = search(t);
        if (node != null) {
            node.delete();
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
