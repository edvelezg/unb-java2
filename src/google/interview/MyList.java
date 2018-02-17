package google.interview;

import java.util.ArrayList;

public class MyList {
	Node head;
	int count = 0;

	class Node {
		int data;
		Node next;

		public Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	public MyList() {
		head = null;
	}

	public void push(int data) {
		head = new Node(data, head);
		count += 1;
	}

	public Node pop() {
		if (head != null) {
			Node cur = head;
			head = head.next;
			return cur;
		}
		return null;
	}

	public void print() {
		Node cur = head;
		while (cur != null) {
			System.out.print(cur.data);
			System.out.print("->");
			cur = cur.next;
		}
		System.out.print("null\n");
	}

	public void reverse() {
		Node prev = null;
		Node cur = head;
		while (cur.next != null) {
			Node next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		cur.next = prev;
		head = cur;
	}

	public int recursiveSum(int n, ArrayList<Integer> array) {
		System.out.println("recursiveSum(n=" + n + ")");
		if (n == 0) {
			return n;
		}
		int sum = recursiveSum(n - 1, array) + n;
		array.add(sum);
		System.out.println("sum=" + sum);
		return sum;
	}

	public void swap(Node prev, Node cur) {

	}
	
	public void rec_reverse() {
		head = rec_reverse(head);
	}
	
	private Node rec_reverse(Node node)
	{
		if (node == null || node.next == null) return node;
		
		Node remaining = rec_reverse(node.next);
		node.next.next = node;
		node.next = null;
		return remaining;
	}

	public static void main(String[] args) {

		MyList l = new MyList();
		l.print();
		l.push(1);
		l.push(2);
		l.push(3);
		l.push(4);
		l.push(5);
		l.push(6);
		l.print();
		l.rec_reverse();
		l.print();

//		ArrayList<Integer> array = new ArrayList<Integer>();
//		l.recursiveSum(5, array);
//
//		for (Integer integer : array) {
//			System.out.println(integer);
//		}

	}	
}
