package google.interview;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Vector;

import java.util.*;

class Node {
    int id;
    int parent;
    int weight;

    public Node(int id, int parent, int weight) {
        this.id = id;
        this.parent = parent;
        this.weight = weight;
    }
}

class TreeNode {
    Node             node;
    Vector<TreeNode> children;

    public TreeNode(Node node, Vector<TreeNode> children) {
        this.node = node;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder strB = new StringBuilder();
        if (node == null) {
            strB.append("null");
        }
        else {
//            strB.append(node.id);
//            strB.append(" -> ");
            strB.append(node.parent);
        }
        return strB.toString();
    }
}

class FileReader {
    private static Scanner input;

    // Processes the given String (id, name and hours worked)
    public static void processLine(String text) {
        // System.out.println(text);
        if (text.contains(",")) {
            String[] parts = text.split(",");
            int id = Integer.parseInt(parts[0]);
            int parent = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            System.out.println(String.format("%d, %d, %d ", id, parent, weight));
        }
    }

    public static List<Node> readNodesFromFile(String filename) {
        List<Node> nodes = new LinkedList<Node>();
        try {
            input = new Scanner(new File(filename));

            while (input.hasNextLine()) {
                String text = input.nextLine();
                // System.out.println(text);
                if (text.contains(",")) {
                    String[] parts = text.split(",");
                    int id = Integer.parseInt(parts[0]);
                    int parent = Integer.parseInt(parts[1]);
                    int weight = Integer.parseInt(parts[2]);
                    nodes.add(new Node(id, parent, weight));
                    // System.out.println(String.format("%d, %d, %d ", id,
                    // parent, weight));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return nodes;
    }
}

public class VectorTree {

    public static int subTreeSum(TreeNode root) {
        System.out.println("subTreeSum(root=" + root + ")");
        int sum = root.node.weight;
        for (int i = 0; i < root.children.size(); i++) {
            sum += subTreeSum(root.children.get(i));
        }
        System.out.println("Node with id " + root.node.id + " has total weight of: " + sum);
        return sum;
    }

    public static void bfs(TreeNode root) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode p = (TreeNode) s.pop();
            System.out.println("Visiting " + p.node.id);
            for (TreeNode c : p.children) {
                s.push(c);
            }
        }
    }

    public static TreeNode getTreeNode(Map<Integer, TreeNode> map, int id) {
        System.out.println("getTreeNode(map= {" + map + "} contains? id=" + id + "=" + ")");
        if (!map.containsKey(id)) {
            TreeNode sumNodes = new TreeNode(null, new Vector<TreeNode>());
            map.put(id, sumNodes);
        }
        return map.get(id);
    }

    public static int updateSum(TreeNode aNode, Map<Integer, TreeNode> map) {
        Queue<TreeNode> q = new ArrayDeque<TreeNode>();
        q.add(aNode);
        int sum = 0;
        while (!q.isEmpty()) {
            TreeNode n = q.remove();
            sum += n.node.weight;
            for (int i = 0; i < n.children.size(); i++) {
                q.add(n.children.get(i));
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        String filename = "nodes.txt";
        List<Node> nodes = null;
        nodes = FileReader.readNodesFromFile(filename);

        if (nodes == null) {
            System.out.println("Could not read nodes from file");
            return;
        }

        Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
        for (Node node: nodes) {
            TreeNode treeNode = new TreeNode(node, new Vector<TreeNode>());
            map.put(node.id, treeNode);
        }

        TreeNode rootNode = null;
        for (Map.Entry<Integer, TreeNode> entry: map.entrySet()) {
            TreeNode childNode = entry.getValue();

            int parId = childNode.node.parent;
            if (parId != 0) {
                TreeNode treeNode = map.get(parId);
                treeNode.children.add(childNode);
            }
            else {
                rootNode = childNode;
            }
        }

//        subTreeSum(rootNode);
        bfs(rootNode);
        
//      Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
//      for (Node node : nodes) {
//          TreeNode thisNode = getTreeNode(map, node.id);
//          thisNode.node = node;
//          if (node.parent != 0) {
//              TreeNode parentNode = getTreeNode(map, node.parent);
//              parentNode.children.add(thisNode);
//          } else {
//              // This is the root node of the tree.
//              rootNode = thisNode;
//          }
//      }


//        TreeNode rootNode = null;
//        Map<Integer, TreeNode> map = new HashMap<Integer, TreeNode>();
//        for (Node node : nodes) {
//            TreeNode thisNode = getTreeNode(map, node.id);
//            thisNode.node = node;
//            if (node.parent != 0) {
//                TreeNode parentNode = getTreeNode(map, node.parent);
//                parentNode.children.add(thisNode);
//            } else {
//                // This is the root node of the tree.
//                rootNode = thisNode;
//            }
//        }

    }
}
