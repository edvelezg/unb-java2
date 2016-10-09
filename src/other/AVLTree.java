package other;
import java.util.LinkedList;
import java.util.Queue;

public class AVLTree<T extends Comparable<T>> {
	AVLNode<T> root;

	public AVLTree() {
		root = null;
	}

	public T Maximum() {
		AVLNode<T> local = root;
		if (local == null)
			return null;
		while (local.getRight() != null)
			local = local.getRight();
		return local.getData();
	}

	public T Minimum() {
		AVLNode<T> local = root;
		if (local == null)
			return null;
		while (local.getLeft() != null) {
			local = local.getLeft();
		}
		return local.getData();
	}

	private int depth(AVLNode<T> node) {
		if (node == null)
			return 0;
		return 1 + Math.max(depth(node.getLeft()), depth(node.getRight()));
	}

	public AVLNode<T> insert(T data) {
		root = insert(root, data);
		switch (balanceNumber(root)) {
		case 1:
			root = rotateLeft(root);
			break;
		case -1:
			root = rotateRight(root);
			break;
		default:
			break;
		}
		return root;
	}

	public AVLNode<T> insert(AVLNode<T> node, T data) {
		if (node == null)
			return new AVLNode<T>(data);
		if (node.getData().compareTo(data) > 0) {
			node = new AVLNode<T>(node.getData(), insert(node.getLeft(), data),
					node.getRight());
			// node.setLeft(insert(node.getLeft(), data));
		} else if (node.getData().compareTo(data) < 0) {
			// node.setRight(insert(node.getRight(), data));
			node = new AVLNode<T>(node.getData(), node.getLeft(), insert(
					node.getRight(), data));
		}
		// After insert the new node, check and rebalance the current node if
		// necessary.
		switch (balanceNumber(node)) {
		case 1:
			node = rotateLeft(node);
			break;
		case -1:
			node = rotateRight(node);
			break;
		default:
			return node;
		}
		return node;
	}

	private int balanceNumber(AVLNode<T> node) {
		int L = depth(node.getLeft());
		int R = depth(node.getRight());
		if (L - R >= 2)
			return -1;
		else if (L - R <= -2)
			return 1;
		return 0;
	}

	private AVLNode<T> rotateLeft(AVLNode<T> node) {
		AVLNode<T> q = node;
		AVLNode<T> p = q.getRight();
		AVLNode<T> c = q.getLeft();
		AVLNode<T> a = p.getLeft();
		AVLNode<T> b = p.getRight();
		q = new AVLNode<T>(q.getData(), c, a);
		p = new AVLNode<T>(p.getData(), q, b);
		return p;
	}

	private AVLNode<T> rotateRight(AVLNode<T> node) {
		AVLNode<T> q = node;
		AVLNode<T> p = q.getLeft();
		AVLNode<T> c = q.getRight();
		AVLNode<T> a = p.getLeft();
		AVLNode<T> b = p.getRight();
		q = new AVLNode<T>(q.getData(), b, c);
		p = new AVLNode<T>(p.getData(), a, q);
		return p;
	}

	public boolean search(T data) {
		AVLNode<T> local = root;
		while (local != null) {
			if (local.getData().compareTo(data) == 0)
				return true;
			else if (local.getData().compareTo(data) > 0)
				local = local.getLeft();
			else
				local = local.getRight();
		}
		return false;
	}

	public String toString() {
		return root.toString();
	}

	public void PrintTree() {
		root.level = 0;
		Queue<AVLNode<T>> queue = new LinkedList<AVLNode<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			AVLNode<T> node = queue.poll();
			System.out.println(node);
			int level = node.level;
			AVLNode<T> left = node.getLeft();
			AVLNode<T> right = node.getRight();
			if (left != null) {
				left.level = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.level = level + 1;
				queue.add(right);
			}
		}
	}
	
	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<Integer>();
		tree.insert(0);
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
		tree.insert(7);
		tree.insert(8);
		tree.PrintTree();
	}           
}               
                                                