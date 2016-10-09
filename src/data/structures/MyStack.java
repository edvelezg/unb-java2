package data.structures;

class StackNode
{
    int data;
    StackNode next;
    
    public StackNode(int data, StackNode next) {
        this.data = data;
        this.next = next;
    }
    
    public void print() {
        System.out.print(String.format("[%d]->", data));
    }
}

public class MyStack {
    StackNode top;
    StackNode pop() {
        if (top != null) {
            top = top.next;
            return top;
        }
        return null;
    }
    
    void push(StackNode item)
    {
        if (top == null) {
            top = item;
        }
        item.next = top;
        top = item;
    }
}
