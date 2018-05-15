package data.structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class TreeNode {
	int data;
	TreeNode left;
	TreeNode right;

	public TreeNode(int data, TreeNode lt, TreeNode rt) {
		this.data = data;
		this.left = lt;
		this.right = rt;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.data + "";
	}
}

public class BinaryTree {
	static int TWO_NODES_FOUND = 2;
	static int ONE_NODE_FOUND = 1;
	static int NO_NODES_FOUND = 0;

	TreeNode root;

	public BinaryTree() {
	}

	public int getNumElems(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int sum = 1 + getNumElems(root.left) + getNumElems(root.right);
		return sum;
	}

	// Checks how many “special” nodes are located under this root
	int covers(TreeNode root, TreeNode p, TreeNode q) {
		int ret = NO_NODES_FOUND;
		if (root == null)
			return ret;
		if (root == p || root == q)
			ret += 1;
		ret += covers(root.left, p, q);
		if (ret == TWO_NODES_FOUND) // Found p and q
			return ret;
		return ret + covers(root.right, p, q);
	}

	TreeNode commonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (q == p && (root.left == q || root.right == q))
			return root;
		int nodesFromLeft = covers(root.left, p, q); // Check left side
		if (nodesFromLeft == TWO_NODES_FOUND) {
			if (root.left == p || root.left == q)
				return root.left;
			else
				return commonAncestor(root.left, p, q);
		} else if (nodesFromLeft == ONE_NODE_FOUND) {
			if (root == p)
				return p;
			else if (root == q)
				return q;
		}
		int nodesFromRight = covers(root.right, p, q); // Check right side
		if (nodesFromRight == TWO_NODES_FOUND) {
			if (root.right == p || root.right == q)
				return root.right;
			else
				return commonAncestor(root.right, p, q);
		} else if (nodesFromRight == ONE_NODE_FOUND) {
			if (root == p)
				return p;
			else if (root == q)
				return q;
		}
		if (nodesFromLeft == ONE_NODE_FOUND && nodesFromRight == ONE_NODE_FOUND)
			return root;
		else
			return null;
	}

	public int getMaxHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int sum = 1 + Math.max(getMaxHeight(root.left), getMaxHeight(root.right));
		return sum;
	}

	public int getMinHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int sum = 1;
		sum = sum + Math.min(getMinHeight(root.left), getMinHeight(root.right));
		return sum;
	}

	public TreeNode buildTree(int[] array, int idx) {
		if (idx >= array.length) {
			return null;
		} else {
			return new TreeNode(array[idx], buildTree(array, 2 * idx + 1), buildTree(array, 2 * idx + 2));
		}
	}

	public void buildTree(int[] array) {
		root = buildTree(array, 0);

	}

	public void printTree() {
		printTree(root);
	}

	public void printTree(TreeNode tmpRoot) {

		java.util.LinkedList<TreeNode> currentLevel = new java.util.LinkedList<TreeNode>();
		java.util.LinkedList<TreeNode> nextLevel = new java.util.LinkedList<TreeNode>();
		currentLevel.add(tmpRoot);

		while (!currentLevel.isEmpty()) {
			Iterator<TreeNode> iter = currentLevel.iterator();
			while (iter.hasNext()) {
				TreeNode currentNode = iter.next();
				if (currentNode.left != null) {
					nextLevel.add(currentNode.left);
				}
				if (currentNode.right != null) {
					nextLevel.add(currentNode.right);
				}
				System.out.print(currentNode.data + " ");
			}
			System.out.println();
			currentLevel = nextLevel;
			nextLevel = new java.util.LinkedList<TreeNode>();
		}
	}

	public static TreeNode addToTree(int arr[], int start, int end) {
		// System.out.println(String.format("addToTree(arr=%s, start=%d end=%d)",
		// arr.toString(), start, end));
		if (end < start) {
			return null;
		}
		int mid = (start + end) / 2;
		TreeNode n = new TreeNode(arr[mid], null, null);
		// System.out.println("TreeNode n = new TreeNode(arr[mid], null, null);");
		n.left = addToTree(arr, start, mid - 1);
		n.right = addToTree(arr, mid + 1, end);
		return n;
	}

	public void createMinimalBST(int[] a) {
		root = addToTree(a, 0, a.length - 1);
	}

	public LinkedList<LinkedList<TreeNode>> getLevelLists() {
		LinkedList<TreeNode> curtLevel;
		LinkedList<TreeNode> nextLevel = new LinkedList<TreeNode>();
		LinkedList<LinkedList<TreeNode>> lists = new LinkedList<LinkedList<TreeNode>>();

		nextLevel.add(root);
		while (!nextLevel.isEmpty()) {
			curtLevel = nextLevel;
			lists.add(curtLevel);
			nextLevel = new LinkedList<TreeNode>();
			for (Iterator<TreeNode> iterator = curtLevel.iterator(); iterator.hasNext();) {
				TreeNode node = (TreeNode) iterator.next();
				if (node.left != null) {
					nextLevel.add(node.left);
				}
				if (node.right != null) {
					nextLevel.add(node.right);
				}
			}
		}

		return lists;
	}

	boolean containsTree(TreeNode t1, TreeNode t2) {
		if (t2 == null)
			return true; // The empty tree is always a subtree
		else
			return subTree(t1, t2);
	}

	boolean subTree(TreeNode r1, TreeNode r2) {
		System.out.println("subTree(" + r1 + ", " + r2 + ")");
		if (r1 == null)
			return false; // big tree empty & subtree still not found.
		if (r1.data == r2.data) {
			if (matchTree(r1, r2)) {
				System.out.println("subTree(" + r1.data + ", " + r2.data + ") <-- true");
				return true;
			}
			System.out.println("subTree(" + r1.data + ", " + r2.data + ") <-- false");
		}
		return (subTree(r1.left, r2) || subTree(r1.right, r2));
	}

	boolean matchTree(TreeNode r1, TreeNode r2) {
		System.out.println("matchTree(" + r1 + ", " + r2 + ")");
		if (r2 == null && r1 == null) {
			System.out.println("matchTree(" + r1 + ", " + r2 + ") <-- true ");
			return true; // nothing left in the subtree
		}
		if (r1 == null || r2 == null) {
			System.out.println("matchTree(" + r1 + ", " + r2 + ") <-- false ");
			return false; // big tree empty & subtree still not found
		}
		if (r1.data != r2.data) {
			System.out.println("matchTree(" + r1.data + ", " + r2.data + ") <-- false");
			return false; // data doesn’t match
		}
		return (matchTree(r1.left, r2.left) && matchTree(r1.right, r2.right));
	}

	public void printSideways() {
		printSideways(root, 0);
	}

	// post: prints in reversed preorder the tree with given
	// root, indenting each line to the given level
	private void printSideways(TreeNode root, int level) {
		if (root != null) {
			printSideways(root.right, level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("    ");
			}
			System.out.println(root.data);
			printSideways(root.left, level + 1);
		}
	}

	void findSum(TreeNode head, int sum, ArrayList<Integer> buffer, int level) {
		System.out.println("findSum(head="+head+", sum="+sum+", buffer="+buffer+", level="+level+")");
		if (head == null)
			return;
		int tmp = sum;
		buffer.add(head.data);
		for (int i = level; i > -1; i--) {
			tmp -= buffer.get(i);
			if (tmp == 0)
				print(buffer, i, level);
		}
		ArrayList<Integer> c1 = (ArrayList<Integer>) buffer.clone();
		ArrayList<Integer> c2 = (ArrayList<Integer>) buffer.clone();
		findSum(head.left, sum, c1, level + 1);
		findSum(head.right, sum, c2, level + 1);
	}

	void print(ArrayList<Integer> buffer, int level, int i2) {
		for (int i = level; i <= i2; i++) {
			System.out.print(buffer.get(i) + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		int[] a = new int[] { 0, 1, 2, 3, 4, 5, 2, 7, 4, 5, 6, 11, 12, 13, 14 };
		tree.createMinimalBST(a);

		BinaryTree tree2 = new BinaryTree();
		int[] b = new int[] { 4, 5, 6 };
		tree2.createMinimalBST(b);

		tree.printSideways();
		tree2.printSideways();
		
		ArrayList<Integer> buffer = new ArrayList<>();
		tree.findSum(tree.root, 10, buffer, 0);
		// LinkedList<LinkedList<TreeNode>> lists = tree.getLevelLists();
		// for (LinkedList<TreeNode> list : lists) {
		// System.out.println(Arrays.toString(list.toArray()));
		// }

		tree.subTree(tree.root, tree2.root);
	}
}