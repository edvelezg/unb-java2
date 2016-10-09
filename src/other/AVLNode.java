package other;
/**
 * @author antonio081014
 * @time Jul 5, 2013, 9:31:32 PM
 */
public class AVLNode<T extends Comparable<T>> implements Comparable<AVLNode<T>> {

	private T data;
	private AVLNode<T> left;
	private AVLNode<T> right;
	public int level;

	public AVLNode(T data) {
		this(data, null, null);
	}

	public AVLNode(T data, AVLNode<T> left, AVLNode<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public AVLNode<T> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<T> left) {
		this.left = left;
	}

	public AVLNode<T> getRight() {
		return right;
	}

	public void setRight(AVLNode<T> right) {
		this.right = right;
	}

	@Override
	public int compareTo(AVLNode<T> o) {
		return this.data.compareTo(o.data);
	}

	@Override
	public String toString() {
		return "Level " + level + ": " + data;
	}

}