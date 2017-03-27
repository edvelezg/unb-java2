package data.structures;

class MyQueue {
    GraphNode first, last;

    public void enqueue(GraphNode n) {
        if (first == null) {
            first = n;
            last = first;
        } else {
            last.next = n;
            last = n;
        }
//        print();
    }
    
    public void print()
    {
        GraphNode node = first;
        while (node != null) {
            System.out.print(node.val + " => ");
            node = node.next;
        }
        System.out.println(" null ");
    }

    public GraphNode dequeue() {
        if (first == null) {
            return null;
        } else {
            GraphNode temp = new GraphNode(first.val, first.neighbors);
            first = first.next;
            return temp;
        }
    }
}