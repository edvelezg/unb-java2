package data.structures;

import java.util.Arrays;
import java.util.LinkedList;

class GraphNode {
    int         val;
    GraphNode   next;
    GraphNode[] neighbors;
    boolean     visited;

    GraphNode(int x) {
        val = x;
    }

    GraphNode(int x, GraphNode[] n) {
        val = x;
        neighbors = n;
    }

    public String toString() {
        return String.format("[%d]", this.val);
    }
}

public class Graph {
    static GraphNode[] nodes;
    
    public static void main(String[] args) {
        GraphNode n1 = new GraphNode(1);
        GraphNode n2 = new GraphNode(2);
        GraphNode n3 = new GraphNode(3);
        GraphNode n4 = new GraphNode(4);
        GraphNode n5 = new GraphNode(5);

//      n1.neighbors = new GraphNode[] { n2, n3 };
//      n2.neighbors = new GraphNode[] { n4 };
//      n3.neighbors = new GraphNode[] {    };
//      n4.neighbors = new GraphNode[] { n3, n5 };
//      n5.neighbors = new GraphNode[] { n1 };

        n1.neighbors = new GraphNode[] { n3, n5 };
        n2.neighbors = new GraphNode[] { n1 };
        n3.neighbors = new GraphNode[] {};
        n4.neighbors = new GraphNode[] { n3, n2 };
        n5.neighbors = new GraphNode[] { n4 };
 
        nodes = new GraphNode[] {n1,n2,n3,n4,n5};
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].val + " => ");
            System.out.println(Arrays.toString(nodes[i].neighbors));
        }
        System.out.println();
//        breathFirstSearch(n1, 5);

        printHeader();
        depthFirstSearch(n1, 2);
        isThereAPath(n3, n2);

    }

    private static void printHeader() {
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].val + "\t");
        }
        System.out.println();
    }

    public static void depthFirstSearch(GraphNode root, int x) {
        LinkedList<GraphNode> s = new LinkedList<GraphNode>();
        s.push(root);
        root.visited = true;
        printVisited();
//        print(root, x);
        while (!s.isEmpty()) {
            GraphNode curNode = s.peek();
            GraphNode uvNode = null;
            for (GraphNode nb : curNode.neighbors) {
                if (nb.visited == false) {
                    uvNode = nb;
                    break;
                }
            }
            if (uvNode != null) {
                s.push(uvNode);
                uvNode.visited = true;
                printVisited();
//                print(uvNode, x);

            } else {
                s.pop();
            }
        }
    }

    private static void print(GraphNode root, int x) {
        if (root.val == x) {
            System.out.println(root + "<- found");
        } else {
            System.out.println(root);
        }
    }

    public static void breathFirstSearch(GraphNode root, int x) {

        MyQueue queue = new MyQueue();
        root.visited = true;
        queue.enqueue(root);
        printVisited();
        
        while (queue.first != null) {
            GraphNode c = (GraphNode) queue.dequeue();
//            print(c, x);
            
            for (GraphNode n : c.neighbors) {
                if (!n.visited) {
                    n.visited = true;
                    queue.enqueue(n);
                    printVisited();
                }
            }
        }
    }
    
    public static void printVisited() {
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].visited + "\t");
        }
        System.out.println();
    }

    public static void isThereAPath(GraphNode x, GraphNode y) {
        MyQueue queue = new MyQueue();
        x.visited = true;
        queue.enqueue(x);

        while (queue.first != null) {
            GraphNode c = (GraphNode) queue.dequeue();
            
            System.out.println(c);
            if (c.val == y.val) {
                System.out.println("There is a path");
                return;
            }
            
            for (GraphNode n : c.neighbors) {
                if (!n.visited) {
                    n.visited = true;
                    queue.enqueue(n);
                }
            }
        }
    }

}
