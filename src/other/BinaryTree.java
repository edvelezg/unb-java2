package other;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class BinaryTree {

    Node root;
    int size;

    public BinaryTree() {
        root = null;
        size = 0;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.addValue(5);
        tree.addValue(3);
        tree.addValue(6);
        tree.addValue(2); 
        tree.addValue(4);
        tree.addValue(7);
        tree.addValue(8);

        System.out.println("The tree height is "
                           + tree.getTreeHeight(tree.root));
        if (tree.findNodeRecursive(tree.root, 2) != null) System.out.println("found it");
        if (tree.findNodeRecursive(tree.root, 3) != null) System.out.println("found it");
        if (tree.findNodeRecursive(tree.root, 8) != null) System.out.println("found it");
        if (tree.findNodeRecursive(tree.root, 9) != null) System.out.println("found it");

        System.out.println("Preorder traversals:");
        tree.preOrder(tree.root);
        System.out.println();
        tree.root.preOrder2();
        System.out.println();

        BinaryTree tree2 = new BinaryTree();
        tree2.addValue(20);
        tree2.addValue(8);
        tree2.addValue(4);
        tree2.addValue(12);
        tree2.addValue(10);
        tree2.addValue(14);
        tree2.addValue(22);
        tree2.root.preOrder2();
        System.out.println();

        /** stack work */

        Node n1 = tree2.findNodeRecursive(tree2.root, 4);
        Node n2 = tree2.findNodeRecursive(tree2.root, 14);
        Node cA = tree2.getLCA2(n1, n2);

        System.out.println("Common ancestor: " + cA.getValue());

//		System.out.println(tree2.countNodes(tree2.root));

        Node[] nodes = tree2.toArray();
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(String.format("%-5d", nodes[i].value));
        }

        tree2.Heapify();

    }

    public void preOrder(Node root) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.print(String.format("%-5d", n.value));

            if (n.getRight() != null) {
                stack.push(n.getRight());
            }

            if (n.getLeft() != null) {
                stack.push(n.getLeft());
            }
        }
    }

    public void postOrderIt(Node root) {
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.print(String.format("%-5d", n.value));

            if (n.getRight() != null) {
                stack.push(n.getRight());
            }

            if (n.getLeft() != null) {
                stack.push(n.getLeft());
            }
        }
    }

    // public void preOrder(Node root)
    // {
    // if (root == null)
    // return;
    // root.print();
    // preOrder(root.getLeft());
    // preOrder(root.getRight());
    // }

    public void postOrder(Node root) {
        if (root == null) return;
        this.postOrder(root.getLeft());
        this.postOrder(root.getRight());
        this.root.print();
    }

    public void inOrder(Node root) {
        if (root == null) return;
        this.inOrder(root.getLeft());
        this.inOrder(root.getRight());
        this.root.print();
    }

    public Node findNodeIterative(Node root, int value) {
        while (root != null) {
            if (root.value == value) break;
            if (root.value < value) {
                root = root.getRight();
            }
            else { // currval > value
                root = root.getLeft();
            }
        }
        return root;
    }

    public Node findNodeRecursive(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (root.value == value) {
            return root;
        }
        else if (root.value < value) {
            return findNodeRecursive(root.right, value);
        }
        else {
            return findNodeRecursive(root.left, value);
        }
    }

    public int getTreeHeight(Node root) {
        if (root == null) return 0;

        return 1 + Math
               .max(getTreeHeight(root.left), getTreeHeight(root.right));
    }

    public void addValue(int value) {
        // iterativeAddNode(value);

        root = recursiveAddNode(root, value);

    }

    private Node recursiveAddNode(Node root, int value) {
        if (root == null) {
            root = new Node(null, null, value);
        }
        else if (value > root.value) {
            root.right = recursiveAddNode(root.right, value);
        }
        else {
            root.left = recursiveAddNode(root.left, value);

        }
        return root;
    }

    private void iterativeAddNode(int value) {
        if (root == null) {
            root = new Node(null, null, value);
        }
        else {
            Node current = root;
            while (current != null) {
                if (current.value < value) {
                    if (current.right == null) {
                        current.right = new Node(null, null, value);
                        break;
                    }
                    current = current.right;
                }
                else {
                    if (current.left == null) {
                        current.left = new Node(null, null, value);
                        break;
                    }
                    current = current.left;
                }
            }
        }
    }

    public List<Node> pathToNode(Node n, Node n1, List<Node> l) {
        l.add(n);
        if (n1.value == n.getValue()) {
            return l;
        }
        else if (n1.value > n.getValue()) {
            return pathToNode(n.right, n1, l);
        }
        else {
            return pathToNode(n.left, n1, l);
        }
    }

    public Node getLCA2(Node n1, Node n2) {
        Node lca = root;
        while (lca != null) {
            if (n1.getValue() > lca.value && n2.getValue() > lca.value) {
                lca = lca.getRight();
            }
            else if (n1.getValue() < lca.value && n2.getValue() < lca.value) {
                lca = lca.getLeft();
            }
            else {
                return lca;
            }
        }
        return null; // If the tree is null
    }

    public Node getLCA(Node n1, Node n2) {
        List<Node> s1 = new ArrayList<Node>();
        pathToNode(root, n1, s1);
        List<Node> s2 = new ArrayList<Node>();
        pathToNode(root, n2, s2);

        Node cA = root;
        for (int i = 0; i < Math.max(s1.size(), s2.size()); i++) {
            if (s1.get(i) == null || s2.get(i) == null
                || !s1.get(i).equals(s2.get(i))) {
                break;
            }
            cA = s1.get(i);
        }

        return cA;
    }

    public Node Heapify2() {
        int size = traverse(root, 0, null); // Count the number of nodes
        Node[] arr = new Node[size];
        traverse(root, 0, arr); // Load nodes in array

        // sort the array by values
        Arrays.sort(arr, new Comparator<Node>() {
            @Override
            public int compare(Node m, Node n) {
                int mv = m.getValue();
                int nv = n.getValue();

                return (mv > nv) ? 1 : (mv == nv) ? 0 : -1;
            }
        });

        // Re-assign children
        for (int i = 0; i < arr.length; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            arr[i].left = left >= arr.length ? null : arr[left];
            arr[i].right = right >= arr.length ? null : arr[right];
        }

        return arr[0];
    }

    public int traverse(Node n, int count, Node[] arr) {
        if (n == null) {
            return count;
        }
        if (arr != null) {
            arr[count] = n;
        }
        count++;
        count = traverse(n.left, count, arr);
        count = traverse(n.right, count, arr);
        return count;
    }

    public Node[] Heapify() {
        Node[] nodes = this.toArray();
        Arrays.sort(nodes,
                    new Comparator<Node>() {
            @Override
            public int compare(Node m, Node n) {
                int mv = m.getValue(), nv = n.getValue();
                return mv < nv ? -1 : (mv == nv) ? 0 : 1;
            }

        });

        System.out.println();
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(String.format("%-5d", nodes[i].getValue()));
        }

        for (int i = 0; i < nodes.length; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            nodes[i].left = (left >= nodes.length) ? null : nodes[left];
            nodes[i].right = (right >= nodes.length) ? null : nodes[right];
        }
        System.out.println();
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(String.format("%-5d", nodes[i].getValue()));
        }

        return null;
    }

    public Node[] toArray() {
        int numNodes = countNodes(root);
        Node[] array = new Node[numNodes];

        Stack<Node> s = new Stack<Node>();
        s.push(root);
        for (int i = 0; !s.isEmpty(); i++) {
            Node n = s.pop();
            array[i] = n;
            if (n.right != null) s.push(n.right);
            if (n.left != null) s.push(n.left);
        }

        return array;
    }

    public int countNodes(Node n) {
        if (n == null) return 0;

        return 1 + countNodes(n.left) + countNodes(n.right);
    }


    class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getValue() {
            return value;
        }

        public void print() {
            System.out.print(String.format("%-5d", value));
        }

        public void preOrder2() {
            this.print();
            if (this.getLeft() != null) {
                this.getLeft().preOrder2();
            }
            if (this.getRight() != null) {
                this.getRight().preOrder2();
            }
        }
    }

}
