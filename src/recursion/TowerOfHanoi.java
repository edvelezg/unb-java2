package recursion;

import java.util.Stack;

public class TowerOfHanoi {
	private Stack<Integer> disks;
	private int index;

	public TowerOfHanoi(int i) {
		disks = new Stack<Integer>();
		index = i;
	}

	public int index() {
		return index;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
//		return super.toString();
		return String.format("[%c]", index + 'A');
	}

	public static void main(String[] args) {
		int n = 3;
		TowerOfHanoi[] towers = new TowerOfHanoi[3];
		
		for (int i = 0; i < 3; i++)
			towers[i] = new TowerOfHanoi(i);
		
		for (int i = n-1; i >= 0; i--)
			towers[0].add(i);
		
		towers[0].moveDisks(n, towers[2], towers[1]);
	}

	public void add(int d) {
		if (!disks.isEmpty() && disks.peek() <= d) {
			System.out.println("Error placing disk " + d);
		} else {
			disks.push(d);
		}
	}

	public void moveTopTo(TowerOfHanoi t) {
		int top = disks.pop();
		t.add(top);
		System.out.println("Move disk " + top + " from " + this + " to " + t);
	}

	public void print() {
		System.out.println("Contents of TowerOfHanoi " + index());
		for (int i = disks.size() - 1; i >= 0; i--) {
			System.out.println(" " + disks.get(i));
		}
	}

	public void moveDisks(int n, TowerOfHanoi destination, TowerOfHanoi buffer) {
		System.out.println(this+".moveDisks(n="+n+", dest="+destination+", buff="+buffer+")");
		if (n > 0) {
			moveDisks(n - 1, buffer, destination);
			moveTopTo(destination);
			buffer.moveDisks(n - 1, destination, this);
		}
	}
}


