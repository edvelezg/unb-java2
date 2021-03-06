package data.structures;

// Simple binary tree class that includes methods to construct a
// tree of ints, to print the structure, and to print the data
// using a preorder, inorder or postorder traversal.  The trees
// built have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the tree.  The
// documentation refers to these as "sequential trees."

import java.util.*;

class IntTreeNode {
	public int data;
	public IntTreeNode left;
	public IntTreeNode right;
	public int sum = 0;

	// constructs a leaf node with given data
	public IntTreeNode(int data) {
		this(data, null, null);
	}

	// constructs a branch node with given data, left subtree,
	// right subtree
	public IntTreeNode(int data, IntTreeNode left, IntTreeNode right) {
		// System.out.println(String.format("new IntTreeNode(%d)", data));
		this.data = data;
		this.left = left;
		this.right = right;
	}
}

public class IntTree {
	private IntTreeNode overallRoot;

	public IntTree() {
		overallRoot = null;
	}

	// pre : max > 0
	// post: constructs a sequential tree with given number of
	// nodes
	public void buildSeqTree(int max) {
		if (max <= 0) {
			throw new IllegalArgumentException("max: " + max);
		}
		setOverallRoot(buildSeqTree(1, max));
	}

	// post: returns a sequential tree with n as its root unless
	// n is greater than max, in which case it returns an
	// empty tree
	private IntTreeNode buildSeqTree(int n, int max) {
		System.out.println(String.format("buildTree(%d, %d)", n, max));
		if (n > max) {
			System.out.println("(n > max)");
			return null;
		} else {
			return new IntTreeNode(n, buildSeqTree(2 * n, max), buildSeqTree(2 * n + 1, max));
		}
	}

	public IntTreeNode createBST(int[] array) {
		if (array.length == 0) {
			overallRoot = null;
			return overallRoot;
		}
		overallRoot = createBST(array, 0, array.length - 1);
		return overallRoot;
	}

	public IntTreeNode createBST(int[] array, int start, int end) {
		if (end < start) {
			return null;
		}
		int mid = (start + end) / 2;
		return new IntTreeNode(array[mid], createBST(array, start, mid - 1), createBST(array, mid + 1, end));
	}

	// post: prints the tree with given root in post order
	public void preorderNoRecursion() {
		IntTreeNode n = getOverallRoot();
		Stack<IntTreeNode> s = new Stack<IntTreeNode>();
		s.push(n);
		while (!s.isEmpty()) {
			n = s.pop();
			if (n.right != null) {
				s.push(n.right);
			}
			if (n.left != null) {
				s.push(n.left);
			}
			System.out.println(n.data);
		}
	}

	public void inOrderNoRecursion1() {
		Stack<IntTreeNode> s = new Stack<IntTreeNode>();
		IntTreeNode node = getOverallRoot();
		s.push(node);
		while (node.left != null) {
			node = node.left;
			System.out.println(node.data);
			s.push(node);
		}
		// once we push all left most elems, we look at the right and it's
		// elements
		while (s.size() > 0) {
			node = s.pop();
			if (node.right != null) {
				node = node.right;
				System.out.println(node.data);
				s.push(node);
				while (node.left != null) {
					node = node.left;
					System.out.println(node.data);
					s.push(node);
				}
			}
		}
	}

	// post: prints the tree contents using a preorder traversal
	public void printPreorder() {
		System.out.print("preorder:");
		printPreorder(getOverallRoot());
		System.out.println();
	}

	// post: prints the tree contents using a preorder traversal
	// post: prints in preorder the tree with given root
	private void printPreorder(IntTreeNode root) {
		if (root != null) {
			System.out.print(" " + root.data);
			printPreorder(root.left);
			printPreorder(root.right);
		}
	}

	// post: prints the tree contents using a inorder traversal
	public void printInOrder() {
		System.out.print("inorder:");
		printInorder(getOverallRoot());
		System.out.println();
	}

	// post: prints in inorder the tree with given root
	private void printInorder(IntTreeNode root) {
		if (root != null) {
			printInorder(root.left);
			System.out.print(" " + root.data);
			printInorder(root.right);
		}
	}

	// post: prints the tree contents using a postorder traversal
	public void printPostorder() {
		System.out.print("postorder:");
		printPostorder(getOverallRoot());
		System.out.println();
	}

	// post: prints in postorder the tree with given root
	private void printPostorder(IntTreeNode root) {
		if (root != null) {
			printPostorder(root.left);
			printPostorder(root.right);
			System.out.print(" " + root.data);
		}
	}

	// post: prints in postorder the tree with given root
	public void BFS() {
		IntTreeNode root = getOverallRoot();
		Queue<IntTreeNode> q = new ArrayDeque<IntTreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			IntTreeNode node = q.remove();
			if (node.right != null) {
				q.add(node.right);
			}
			if (node.left != null) {
				q.add(node.left);
			}
		}
	}

	public int sum() {
		return sum(getOverallRoot());
	}

	private int sum(IntTreeNode root) {
		if (root == null) {
			return 0;
		}
		return root.data + sum(root.left) + sum(root.right);
	}

	public int countLevels() {
		return levelOf(getOverallRoot());
	}

	private int levelOf(IntTreeNode rt) {
		if (rt == null) {
			return 0;
		}
		return 1 + Math.max(levelOf(rt.left), levelOf(rt.right));
	}

	public int countLeaves() {
		return countLeaves(getOverallRoot());
	}

	private int countLeaves(IntTreeNode root) {
		if (root == null) {
			return 0;
		} else if (root.left == null && root.right == null) {
			return 1;
		}
		return countLeaves(root.left) + countLeaves(root.right);

	}

	public void inOrderNORecursion() {
		Stack<IntTreeNode> s = new Stack<IntTreeNode>();
		IntTreeNode node = getOverallRoot();
		s.push(node);
		while (node.left != null) {
			node = node.left;
			s.push(node);
		}
		while (s.size() > 0) {
			node = s.pop();
			if (node.right != null) {
				node = node.right;
				s.push(node);
				while (node.left != null) {
					node = node.left;
					s.push(node);
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private void printStack(Stack<IntTreeNode> s) {
		IntTreeNode[] itn = new IntTreeNode[s.size()];
		s.toArray(itn);
		for (int i = 0; i < itn.length; i++) {
			System.out.print(", " + itn[i].data);
		}
		System.out.println();
	}

	// post: prints the tree contents, one per line, following an
	// inorder traversal and using indentation to indicate
	// node depth; prints right to left so that it looks
	// correct when the output is rotated.
	public void printSideways() {
		printSideways(getOverallRoot(), 0);
	}

	// post: prints in reversed preorder the tree with given
	// root, indenting each line to the given level
	private void printSideways(IntTreeNode root, int level) {
		if (root != null) {
			printSideways(root.right, level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("    ");
			}
			System.out.println(root.data);
			printSideways(root.left, level + 1);
		}
	}

	public void printBoundary() {
		printLeft();
		printLeaves();
		printRight();
	}

	public void printRight() {
		printRight(getOverallRoot());
	}

	public void printLeft() {
		printLeft(getOverallRoot());
	}

	// post: prints in inorder the tree with given root
	private void printLeaves() {
		printLeaves(getOverallRoot());
	}

	// post: prints in inorder the tree with given root
	private void printLeaves(IntTreeNode root) {
		if (root != null) {
			printLeaves(root.left);
			if (root.left == null && root.right == null) {
				System.out.print(root.data + " > ");
			}
			printLeaves(root.right);
		}
	}

	// post: prints in reversed preorder the tree with given
	// root, indenting each line to the given level
	private void printRight(IntTreeNode root) {
		if (root != null) {
			printRight(root.right);
			if (root.left != null && root.right != null) {
				System.out.print(root.data + " > ");
			}
		}
	}

	// post: prints in reversed preorder the tree with given
	// root, indenting each line to the given level
	private void printLeft(IntTreeNode root) {
		if (root != null) {
			if (root.left != null && root.right != null) {
				System.out.print(root.data + " > ");
			}
			printLeft(root.left);
		}
	}

	public IntTreeNode getOverallRoot() {
		return overallRoot;
	}

	public void setOverallRoot(IntTreeNode overallRoot) {
		this.overallRoot = overallRoot;
	}

	public void sumNodes() {
		int recSum = sumNodes(getOverallRoot(), 0);
		printSumNodes();
		System.out.println(String.format("Result after recursion: %d", recSum));
		System.out.println(String.format("Expected Sum: %d", (6 * (6 / 2)) + 6 / 2));
	}

	// post: prints the tree contents, one per line, following an
	// inorder traversal and using indentation to indicate
	// node depth; prints right to left so that it looks
	// correct when the output is rotated.
	public void printSumNodes() {
		printSumNodes(getOverallRoot(), 0);
	}

	// post: prints in reversed preorder the tree with given
	// root, indenting each line to the given level
	private void printSumNodes(IntTreeNode root, int level) {
		if (root != null) {
			printSumNodes(root.right, level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("    ");
			}
			// System.out.println(String.format("%d,%d", root.data, root.sum));
			System.out.println(String.format("%d", root.sum));
			printSumNodes(root.left, level + 1);
		}
	}

	public int sumNodes(IntTreeNode rt, int sum) {
		if (rt == null)
			return 0;
		sum = rt.data;
		// System.out.println(String.format("sumNodes(%d, %d)", rt.data, sum));
		sum += sumNodes(rt.left, sum);
		// System.out.println(String.format("sumNodes(%d, %d)", rt.data, sum));
		sum += sumNodes(rt.right, sum);
		// System.out.println(String.format("sumNodes(%d, %d)", rt.data, sum));
		rt.sum = sum;
		return sum;
	}

	public static int maxDepth(IntTreeNode rt) {
		if (rt == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(rt.left), maxDepth(rt.right));
	}

	public static int minDepth(IntTreeNode rt) {
		if (rt == null) {
			return 0;
		}
		return 1 + Math.min(minDepth(rt.left), minDepth(rt.right));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IntTree t = new IntTree();
		int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };

		t.createBST(array);
		t.printSideways();
		t.printLeaves();
		t.printPreorder();

		t.sumNodes();
		t.BFS();
		// System.out.println(t.sum());
		// System.out.println(t.countLevels());
		// System.out.println(t.countLeaves());
		// t.inOrderNORecursion();

		// t.printBoundary();
		// System.out.println();
		// t.printPreorder();
		// t.printInorder();
		// t.printPostorder();
	}

}
