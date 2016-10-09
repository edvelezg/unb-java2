package data.structures;

class ListNode
{
    int data;
    ListNode next;
    
    public ListNode(int data, ListNode next) {
        this.data = data;
        this.next = next;
    }
    
    public void print() {
        System.out.print(String.format("[%d]->", data));
    }
}

public class MyLinkedList {
    
    ListNode head;
    
    public MyLinkedList() {
        head = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void push(int data) {
        if (head == null) {
            head = new ListNode(data, null);
        } else {
            head = new ListNode(data, head);
        }
    }
    
    public void print() {
        ListNode node = head;
        while (node != null) {
            node.print();
            node = node.next;
        }
        System.out.println("X");
    }

    public void reverse() {
        ListNode prev = null;
        ListNode cur = head;
        
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
    }
    
    public static void main(String[] args) {
        MyLinkedList ll = new MyLinkedList();
        ll.push(0);
        ll.push(1);
        ll.push(2);
        
        ll.print();
        ll.reverse();
        ll.print();
    }
}
