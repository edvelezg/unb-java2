package google.interview;
class MyList {
	Node head;
	Node max;
	
	class Node {
		int data;
		Node next;
		
		public Node(int data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}
	
	public MyList()
	{
		max = null;
		head = null;
	}

	public void push(int data) {
		if (head == null) {
			head = new Node(data, null);
			max = head;
		}
		else {
			Node newNode = new Node(data, head);
			if (max.data < data) {
				max = newNode;
			}
			head = newNode;
		}
	}
	
	public Node pop() {
		if (head == null) {
			System.out.println("Nothing to pop");
			return null;
		}
		else {
			Node node = head;
			head = head.next;
			return node;
		}
	}
	
	
	public void print() {
		Node cur = head;
		while (cur != null)
		{
			System.out.println(cur.data);
			cur = cur.next;
		}
	}
	
	public void nthToLast(int n) {
		Node cur = head;
		int count = 0;
		while (cur != null)
		{
			count += 1;
			cur = cur.next;
		}
		
		if (n >= count) {
			return;
		}
		
		cur = head;
		for (int i = 0 ; i < count - n - 1; ++i) {
			cur = cur.next;
		}
		System.out.println(cur.data);
	}
	
	public void nthToLast2(int n) {
		Node cur = head;
		Node prev = head;
		int i = 0;
		int j = 0;
		
		while (cur != null)
		{
			if (i - j > n) {
				prev = prev.next;
				j += 1;
			}
			i += 1;
			cur = cur.next;
		}

		if (n >= i) {
			System.out.println("DNE");
		} else {
			System.out.println(prev.data);
		}
	}
	
	public void reverse()
	{
		Node prev = null;
		Node curr = head;
		Node next = curr.next;
		while( next != null)
		{
			curr.next = prev;
			prev = curr;
			curr = next;
			next = next.next;
		}
		head = curr;
	}
	
	static int getBit(int n, int i)
	{
		return (n & (1 << i)) > 0 ? 1 : 0;
	}	
	
	public static void main(String[] args) {
		MyList l = new MyList();
		l.push(1);
		l.push(2);
		l.push(3);
		l.print();
		l.reverse();
		l.print();
		// l.nthToLast2(2);
		// int n = 25
		// for (int i = 31; i >= 0; --i) {
		// 	if (getBit(n, i) == 1) {
		// 		System.out.print(1);
		// 	} else {
		// 		System.out.print(0);
		// 	}
		// }
		
		// Node n = l.pop();
		// if (n != null) {
		// 	System.out.println(n.data);
		// }
		
		// System.out.println(getBit(2, 0));
		// System.out.println((1 << 2) & 2);
		// System.out.println(1 << 3);
	}
	
}