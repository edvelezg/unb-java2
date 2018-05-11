package data.structures;

class ListNode {
	int data;
	ListNode next;

	public ListNode(int data, ListNode next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		ListNode node = this;
		while (node != null) {
			buf.append(String.format("->[%d]", node.data));
			node = node.next;
		}
		buf.append("->X");
		return String.format("%s", buf.toString());
	}
}

public class MyLinkedList {

	ListNode head;
	int size = 0;
	private static boolean borrow = false;

	public MyLinkedList() {
		head = null;
	}

	public ListNode nthToLast(ListNode head, int n) {
		if (head == null || n < 1) {
			return null;
		}
		ListNode p1 = head;
		ListNode p2 = head;
		for (int j = 0; j < n - 1; ++j) { // skip n-1 steps ahead
			if (p2 == null) {
				return null; // not found since list size < n
			}
			p2 = p2.next;
		}
		while (p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1;
	}

	public ListNode getNthToLastElement(int n) {
		int count = 0;
		ListNode cur = head;
		while (cur != null) {
			cur = cur.next;
			count++;
		}
		int idx = count - n - 1;
		if (idx >= 0) {
			cur = head;
			int i = 0;
			while (i < idx) {
				cur = cur.next;
				++i;
			}
			return cur;
		}
		return null;
	}

	public MyLinkedList(String word) {
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			try {
				int num = Character.getNumericValue(ch);
				add(num);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public MyLinkedList(ListNode head) {
		this.head = head;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return size;
	}

	public void push(int data) {
		if (head == null) {
			head = new ListNode(data, null);
		} else {
			head = new ListNode(data, head);
		}
		size++;
	}

	public void add(int data) {
		if (head == null) {
			head = new ListNode(data, null);
		} else {
			ListNode curr = head;
			while (curr.next != null) {
				curr = curr.next;
			}
			curr.next = new ListNode(data, null);
		}
		size++;
	}

	public void print() {
		System.out.println(head);
	}

	public void reverse() {
		ListNode prev = null;
		ListNode curr = head;
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		head = prev;
	}

	public void rec_reverse() {
		head = rec_reverse(head);
	}

	public ListNode rec_reverse(ListNode curr) {
		if (curr == null || curr.next == null) {
			return curr;
		}

		ListNode next = curr.next;
		curr.next = null;
		ListNode rest = rec_reverse(next);
		next.next = curr;
		return rest;
	}

	public void paddZeros(int diff) {
		head = paddZeros(head, diff);
		System.out.println("After padding with 0s");
		print();
	}

	private ListNode paddZeros(ListNode sNode, int diff) {
		// if (sNode == null) return null;
		// The first zero in the padding
		ListNode zHead = new ListNode(0, null);
		diff--;
		ListNode temp = zHead;
		while (diff > 0) {
			temp.next = new ListNode(0, null);
			temp = temp.next;
			diff--;
		}
		temp.next = sNode;
		return zHead;
	}

	private ListNode subtractLinkedListHelper(ListNode l1, ListNode l2) {
		System.out.printf("subtractLinkedListHelper(borrow=%s)\n", borrow ? "true" : "false");
		if (l1 == null && l2 == null && borrow == false)
			return null;

		ListNode previous = subtractLinkedListHelper(l1 != null ? l1.next : null, l2 != null ? l2.next : null);

		int d1 = l1.data;
		int d2 = l2.data;
		int diff = 0;

		/*
		 * if you have given the value value to next digit then reduce the d1 by 1
		 */
		if (borrow) {
			d1--;
			borrow = false;
		}

		/*
		 * If d1 < d2 , then borrow the number from previous digit. Add 10 to d1 and set
		 * borrow = true;
		 */
		if (d1 < d2) {
			borrow = true;
			d1 = d1 + 10;
		}

		/* subtract the digits */
		diff = d1 - d2;

		/* Create a Node with sub value */
		ListNode current = newNode(diff);

		/* Set the Next pointer as Previous */
		current.next = previous;

		System.out.printf("--> subtractLinkedListHelper(borrow=%s)\n", borrow ? "true" : "false");
		return current;
	}

	/*
	 * This API subtracts two linked lists and returns the linked list which shall
	 * have the subtracted result.
	 */
	@SuppressWarnings("unused")
	public MyLinkedList subtractLinkedList(MyLinkedList l2) {
		// Base Case.
		if (this == null && l2 == null)
			return null;

		// In either of the case, get the lengths of both
		// Linked list.
		int len1 = this.size;
		int len2 = l2.size;

		MyLinkedList lNode = null;
		MyLinkedList sNode = null;

		MyLinkedList temp1 = this;
		MyLinkedList temp2 = l2;

		// If lengths differ, calculate the smaller Node
		// and padd zeros for smaller Node and ensure both
		// larger Node and smaller Node has equal length.
		if (len1 != len2) {
			lNode = len1 > len2 ? this : l2;
			sNode = len1 > len2 ? l2 : this;
			sNode.paddZeros(Math.abs(len1 - len2));
		} else {
			// If both list lengths are equal, then calculate
			// the larger and smaller list. If 5-6-7 & 5-6-8
			// are linked list, then walk through linked list
			// at last Node as 7 < 8, larger Node is 5-6-8
			// and smaller Node is 5-6-7.
			ListNode ln1 = this.head;
			ListNode ln2 = l2.head;
			while (ln1 != null && ln2 != null) {
				if (ln1.data != ln2.data) {
					lNode = ln1.data > ln2.data ? temp1 : temp2;
					sNode = ln1.data > ln2.data ? temp2 : temp1;
					break;
				}
				ln1 = ln1.next;
				ln2 = ln2.next;
			}
		}

		// After calculating larger and smaller Node, call
		// subtractLinkedListHelper which returns the subtracted
		// linked list.
		MyLinkedList.borrow = false;
		return subtractLinkedListHelper(lNode, sNode);
	}

	public ListNode subtractLinkedListHelperLN(ListNode l1, ListNode l2) {
		System.out.printf("subtractLinkedListHelper(borrow=%s)\n", borrow ? "true" : "false");
		if (l1 == null && l2 == null && borrow == false)
			return null;

		ListNode previous = subtractLinkedListHelper(l1 != null ? l1.next : null, l2 != null ? l2.next : null);

		int d1 = l1.data;
		int d2 = l2.data;
		int diff = 0;

		/*
		 * if you have given the value value to next digit then reduce the d1 by 1
		 */
		if (borrow) {
			d1--;
			borrow = false;
		}

		/*
		 * If d1 < d2 , then borrow the number from previous digit. Add 10 to d1 and set
		 * borrow = true;
		 */
		if (d1 < d2) {
			borrow = true;
			d1 = d1 + 10;
		}

		/* subtract the digits */
		diff = d1 - d2;

		/* Create a Node with sub value */
		ListNode current = newNode(diff);

		/* Set the Next pointer as Previous */
		current.next = previous;

		System.out.printf("--> subtractLinkedListHelper(borrow=%s)\n", borrow ? "true" : "false");
		return current;
	}

	public MyLinkedList subtractLinkedListHelper(MyLinkedList l1, MyLinkedList l2) {
		MyLinkedList.borrow = false;
		ListNode res = subtractLinkedListHelper(l1.head, l2.head);
		return new MyLinkedList(res);
	}

	private static ListNode newNode(int data) {
		// Created new ListNode2 with data.
		return new ListNode(data, null);
	}

	public static void main(String[] args) {

		MyLinkedList l1 = new MyLinkedList();
		reverse_demo();

		// MyLinkedList l1 = new MyLinkedList("56801");
		// MyLinkedList l2 = new MyLinkedList("57991");
		// MyLinkedList lr = l1.subtractLinkedList(l2);
		// l1.print();
		// l2.print();
		// lr.print();
	}

	@SuppressWarnings("unused")
	private static void reverse_demo() {
		MyLinkedList ll = new MyLinkedList();
		ll.add(3);
		ll.add(2);
		ll.add(1);
		ll.add(0);
		// ll.print();
		System.out.println(ll.getNthToLastElement(2));
		System.out.println(ll.nthToLast(ll.head, 1));
		
		// ll.rec_reverse();
		// ll.print();
		// ll.reverse();
		// ll.print();
	}
}
