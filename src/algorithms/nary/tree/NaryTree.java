package algorithms.nary.tree;

import java.util.ArrayList;

public class NaryTree<T> {

    private Node<T> root;
    
    public NaryTree(Node<T> root) {
        this.root = root;
    }
    
    public boolean isEmpty() {
        return (root == null);
    }
    
    public Node<T> getRoot() {
        return root;
    }
    
    public void setRoot(Node<T> node) {
        this.root = node;
    }
    
    public boolean exists(T key) {
        return find(root, key);
    }
    
    private boolean find(Node<T> node, T key) {
        System.out.println("find(node="+node+"key="+key+")");
        boolean res = false;
        if (node.getData().equals(key))
            return true;
        else {
            for (Node<T> child : node.getChildren()) {
                if (find(child, key)) {
//                    System.out.println("if (find(child, key)) {");
                    res = true;
//                    System.out.println("res = true;");
                }
            }
        }
        System.out.println("res = " + res);
        return res;
    }

    public int size() {
        return getNumberOfDescendants(root) + 1;
    }
    
    private int getHeight(Node<T> node) {   	
        int n = node.getChildren().size();
        if (n == 0) {
        	return 0;
        }
        ArrayList<Integer> heights = new ArrayList<>(); 
        for (Node<T> child : node.getChildren()) {
            heights.add(1 + getHeight(child));
        }
        
        int maxHeight = heights.get(0);
        for (int i = 1; i < heights.size(); i++) {
			maxHeight = Math.max(heights.get(i), maxHeight);
		}
        
        return maxHeight;
    }


    private int getNumberOfDescendants(Node<T> node) {
        int n = node.getChildren().size();
        for (Node<T> child : node.getChildren()) {
            n += getNumberOfDescendants(child);
        }
        return n;
    }

    public ArrayList<Node<T>> getPreOrderTraversal() {
        ArrayList<Node<T>> preOrder = new ArrayList<Node<T>>();
        buildPreOrder(root, preOrder);
        return preOrder;
    }

    private void buildPreOrder(Node<T> node, ArrayList<Node<T>> preOrder) {
        preOrder.add(node);
        System.out.println(node.getData());
        for (Node<T> child : node.getChildren()) {
            buildPreOrder(child, preOrder);
        }
    }
    
    public static void main(String[] args) {
     // Create a new Integer type node
        Node<Integer> root = new Node<Integer>(1);
        // Add a child
        Node<Integer> child = new Node<Integer>(2);
        root.addChild(child);
        root.addChild(new Node<Integer>(3));
        child.addChild(new Node<Integer>(5));
        child.addChild(new Node<Integer>(6));
        Node<Integer> child2 = new Node<Integer>(4);
        root.addChild(child2);
        child2.addChild(new Node<Integer>(7));
        child2.addChild(new Node<Integer>(8));
        
        Node<Integer> child3 = new Node<Integer>(9);
        child2.addChild(child3);
        
        child3.addChild(new Node<Integer>(10)).addChild(new Node<Integer>(11));

        // Create a tree, providing the root node
        NaryTree<Integer> tree = new NaryTree<Integer>(root);

        // Get the pre-order traversal
        System.out.println("num elems: " + tree.size());
        System.out.println("num elems: " + tree.getNumberOfDescendants(root));
        int height = tree.getHeight(root);
        System.out.println("height=" + height);
        
        int descendants = tree.getNumberOfDescendants(child3);
        System.out.println("descendants="+descendants);
        
//        boolean res = tree.find(root, 6);
//        System.out.println(res);
        
    }
}
