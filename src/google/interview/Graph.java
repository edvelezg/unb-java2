package google.interview;

import java.util.Set;
import java.util.Stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    ArrayList<GraphNode> nodes;

    class GraphNode {
        boolean             visited = false;
        int                 val;
        Iterator<GraphNode> it;
        Set<GraphNode>      nbs;

        public GraphNode(int val) {
            this.val = val;
            nbs = new HashSet<GraphNode>();
        }

        public void pointTo(GraphNode[] nodes) {
            for (int i = 0; i < nodes.length; i++) {
                if (!this.nbs.contains(nodes[i])) {
                    this.nbs.add(nodes[i]);
                }
            }
        }

        @Override
        public String toString() {
            return this.val + "";
        }

        public void reset() {
            this.visited = false;
            it = nbs.iterator();
        }

        public GraphNode getNextUVN() {
            while (it.hasNext()) {
                GraphNode graphNode = (GraphNode) it.next();
                if (graphNode.visited == false) {
                    return graphNode;
                }
            }
            return null;
        }
    }

    public Graph() {
        nodes = new ArrayList<GraphNode>();
    }

    public GraphNode addNode(GraphNode node) {
        this.nodes.add(node);
        return node;
    }

    public void addNodes(GraphNode[] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            this.nodes.add(nodes[i]);
        }
    }

    public void reset() {
        for (int i = 0; i < nodes.size(); i++) {
            nodes.get(i).reset();
        }
    }

    public void linkNodes(GraphNode src, GraphNode[] targets) {
        src.pointTo(targets);
    }

    public void printGraph() {
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i).toString() + " => ");
            System.out.println(Arrays.toString(nodes.get(i).nbs.toArray()));
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        GraphNode n1 = g.new GraphNode(1);
        GraphNode n2 = g.new GraphNode(2);
        GraphNode n3 = g.new GraphNode(3);
        GraphNode n4 = g.new GraphNode(4);
        GraphNode n5 = g.new GraphNode(5);
        g.addNodes(new GraphNode[] { n1, n2, n3, n4, n5 });

        g.linkNodes(n1, new GraphNode[] { n3, n5 });
        g.linkNodes(n2, new GraphNode[] { n1 });
        g.linkNodes(n3, new GraphNode[] {});
        g.linkNodes(n4, new GraphNode[] { n3, n2 });
        g.linkNodes(n5, new GraphNode[] { n4 });

        g.printGraph();

        // g.bfs(n1);
        // g.dfs(n1);
       String str = Arrays.toString( g.dfs_find(n1, 2).toArray() );
       System.out.println(str);
    }

    public void bfs(GraphNode root) {
        reset();
        Queue<GraphNode> q = new LinkedList<GraphNode>();
        q.add(root);
        root.visited = true;
        System.out.println(root);
        while (!q.isEmpty()) {
            GraphNode p = q.remove();
            for (Iterator<GraphNode> it = p.nbs.iterator(); it.hasNext();) {
                GraphNode c = (GraphNode) it.next();
                if (!c.visited) {
                    q.add(c);
                    c.visited = true;
                    System.out.println(c);
                }
            }
        }
    }

    public void bfs2(GraphNode root) {
        reset();
        LinkedList<LinkedList<GraphNode>> q = new LinkedList<LinkedList<GraphNode>>();
        int idx = 0;
        q.add(new LinkedList<GraphNode>());
        q.get(idx).add(root);
        root.visited = true;

        for (Iterator<GraphNode> lit = q.get(idx).iterator(); lit.hasNext();) {
            while (lit.hasNext()) {
                GraphNode p = lit.next();

                ++idx;
                q.add(new LinkedList<GraphNode>());
                for (Iterator<GraphNode> gnit = p.nbs.iterator(); gnit.hasNext();) {
                    GraphNode c = (GraphNode) gnit.next();
                    if (!c.visited) {
                        q.get(idx).add(c);
                        c.visited = true;
                    }
                }
            }
            lit = q.get(idx).iterator();
        }

        for (Iterator<LinkedList<GraphNode>> lit = q.iterator(); lit.hasNext();) {
            LinkedList<GraphNode> linkedList = (LinkedList<GraphNode>) lit.next();
            System.out.println(Arrays.toString(linkedList.toArray()));
        }
    }

    public void bfs3(GraphNode root) {
        reset();
        LinkedList<LinkedList<GraphNode>> lists = new LinkedList<LinkedList<GraphNode>>();
        LinkedList<GraphNode> curr;
        LinkedList<GraphNode> next = new LinkedList<GraphNode>();

        next.add(root);
        root.visited = true;

        while (!next.isEmpty()) {
            curr = next;
            lists.add(curr);
            next = new LinkedList<GraphNode>();

            for (Iterator<GraphNode> it = curr.iterator(); it.hasNext();) {
                GraphNode p = it.next();
                for (Iterator<GraphNode> nbit = p.nbs.iterator(); nbit.hasNext();) {
                    GraphNode c = (GraphNode) nbit.next();
                    if (c.visited == false) {
                        c.visited = true;
                        next.add(c);
                    }
                }

            }
        }
        
        for (Iterator<LinkedList<GraphNode>> lit = lists.iterator(); lit.hasNext();) {
            LinkedList<GraphNode> list = (LinkedList<GraphNode>) lit.next();
            System.out.println(Arrays.toString(list.toArray()));
        }
    }

    public void dfs(GraphNode root) {
        reset();
        Stack<GraphNode> s = new Stack<GraphNode>();
        s.add(root);
        root.visited = true;
        System.out.println(root);
        while (!s.isEmpty()) {
            GraphNode p = s.peek();
            GraphNode c = p.getNextUVN();
            if (c != null) {
                c.visited = true;
                System.out.println(c);
                s.add(c);
            } else {
                s.pop();
            }
        }
    }

    public Stack<GraphNode> dfs_find(GraphNode root, int x) {
        reset();
        Stack<GraphNode> s = new Stack<GraphNode>();
        s.add(root);
        root.visited = true;
        System.out.println(root);
        while (!s.isEmpty()) {
            GraphNode p = s.peek();
            GraphNode c = p.getNextUVN();
            if (c != null) {
                if (c.val == x) {
                    s.add(c);
                    return s;
                }
                c.visited = true;
                System.out.println(c);
                s.add(c);
            } else {
                s.pop();
            }
        }
        return null;
    }

}
