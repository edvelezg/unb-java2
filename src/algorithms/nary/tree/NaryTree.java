package algorithms.nary.tree;

import java.util.ArrayList;

public class NaryTree<T> {

	private Node<T> root;

	public Node<T> createBST(T[] array) {
		if (array.length == 0) {
			root = null;
			return root;
		}
		root = createBST(array, 0, array.length - 1);
		return root;
	}

	// NOTE: WIP doesn't work yet
	public Node<T> createTrinaryTree(T[] array, int start, int mid, int end) {
		if (end < start) {
			return null;
		}
		// int mid = (start + end) / ;
		Node<T> left = createBST(array, start, mid - 1);
		Node<T> right = createBST(array, mid + 1, end);
		Node<T> node = new Node<T>(array[mid]);
		node.addChild(left);
		node.addChild(right);

		return node;
	}

	public Node<T> createBST(T[] array, int start, int end) {
		if (end < start) {
			return null;
		}
		int mid = (start + end) / 2;
		Node<T> left = createBST(array, start, mid - 1);
		Node<T> right = createBST(array, mid + 1, end);
		Node<T> node = new Node<T>(array[mid]);
		node.addChild(left);
		node.addChild(right);

		return node;
	}

	public NaryTree(Node<T> root) {
		this.root = root;
	}

	public NaryTree() {
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

	public boolean find(Node<T> node, T key) {

		System.out.println("find(node=" + node + "key=" + key + ")");
		boolean res = false;
		if (node.getData().equals(key))
			return true;
		else {
			for (Node<T> child : node.getChildren()) {
				if (find(child, key)) {
					res = true;
				}
			}
		}
		System.out.println("res = " + res);
		return res;
	}

	public Node<T> getNode(Node<T> node, T key) {
		Node<T> retNode = null;
		System.out.println("getNode(node=" + node + ", key=" + key + ")");
		if (node.getData().equals(key)) {
			return node;
		} else {
			for (Node<T> child : node.getChildren()) {
				retNode = getNode(child, key);
			}
		}
		return retNode;
	}

	public int size() {
		return getNumberOfDescendants(root) + 1;
	}

	public int getHeight() {
		return getHeight(getRoot());
	}

	private int getHeight(Node<T> node) {
		// I don't think this code is right.
		int n = node.getChildren().size();
		if (n == 0) {
			return 1;
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

	private void printSideways() {
		printSideways(getRoot(), 0);
	}

	// post: prints in reversed preorder the tree with given
	// root, indenting each line to the given level
	private void printSideways(Node<T> root, int level) {
		if (root != null) {
			// int n = root.getChildren().size();
			for (Node<T> child : root.getChildren()) {
				printSideways(child, level + 1);
			}
			for (int i = 0; i < level; i++) {
				System.out.print("    ");
			}
			System.out.println(root.getData());
		}
	}

	private int getNumberOfDescendants(Node<T> node) {
		int n = node.getChildren().size();
		for (Node<T> child : node.getChildren()) {
			n += getNumberOfDescendants(child);
		}
		return n;
	}

	private int getNumberOfDescendants() {
		return getNumberOfDescendants(getRoot());
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

		customTreeDemo();

		// Create a tree, providing the root node
		NaryTree<Integer> tree = new NaryTree<Integer>();
		Integer[] array = new Integer[] { 0, 1, 2, 3, 4, 5, 6 };
		tree.createBST(array);
		tree.printSideways();
		Node<Integer> node = tree.getNode(tree.root, 2);
		int descendants = node.getNumberOfDescendants();
		System.out.println("descendants = " + descendants);
		System.out.println("height = " + tree.getHeight());
		System.out.println("size : " + tree.size());

		// tree.getPreOrderTraversal();
		// Get the pre-order traversal
		// System.out.println("num elems: " + tree.size());
		// System.out.println("num elems: " + tree.getNumberOfDescendants(root));
		// tree.getHeight(root);

		// boolean res = tree.find(root, 6);
		// System.out.println(res);

	}

	private static void customTreeDemo() {
		NaryTree<Integer> myT = createCustomTree();
		myT.printSideways();

		// Get descendants of a particular node, Node 4 should have 5 descendants
		Node<Integer> node = myT.getNode(myT.root, 8);
		int descendants = node.getNumberOfDescendants();
		System.out.println("descendants = " + descendants);
		System.out.println("height = " + myT.getHeight());
		System.out.println("size = " + myT.size());
	}

	private static NaryTree<Integer> createCustomTree() {
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

		NaryTree<Integer> tree = new NaryTree<>(root);
		return tree;
	}
}
