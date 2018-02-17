package data.structures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class TreeNode {
    int      data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data, TreeNode lt, TreeNode rt) {
        this.data = data;
        this.left = lt;
        this.right = rt;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.data + "";
    }
}

public class BinaryTree {
    
    TreeNode root;

    public BinaryTree() {
    }

    public int getNumElems(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 1 + getNumElems(root.left) + getNumElems(root.right);
        return sum;
    }

    public int getMaxHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 1 + Math.max(getMaxHeight(root.left), getMaxHeight(root.right));
        return sum;
    }

    public int getMinHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 1;
        sum = sum + Math.min(getMinHeight(root.left), getMinHeight(root.right));
        return sum;
    }

    public TreeNode buildTree(int[] array, int idx) {
        if (idx >= array.length) {
            return null;
        } else {
            return new TreeNode(array[idx], buildTree(array, 2 * idx + 1), buildTree(array, 2 * idx + 2));
        }
    }

    public void buildTree(int[] array) {
        root = buildTree(array, 0);

    }

    public void printTree() {
        printTree(root);
    }

    public void printTree(TreeNode tmpRoot) {

        java.util.LinkedList<TreeNode> currentLevel = new java.util.LinkedList<TreeNode>();
        java.util.LinkedList<TreeNode> nextLevel = new java.util.LinkedList<TreeNode>();
        currentLevel.add(tmpRoot);

        while (!currentLevel.isEmpty()) {
            Iterator<TreeNode> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                TreeNode currentNode = iter.next();
                if (currentNode.left != null) {
                    nextLevel.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nextLevel.add(currentNode.right);
                }
                System.out.print(currentNode.data + " ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new java.util.LinkedList<TreeNode>();
        }
    }

    public static TreeNode addToTree(int arr[], int start, int end) {
//        System.out.println(String.format("addToTree(arr=%s, start=%d end=%d)", arr.toString(), start, end)); 
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(arr[mid], null, null);
//        System.out.println("TreeNode n = new TreeNode(arr[mid], null, null);");
        n.left = addToTree(arr, start, mid - 1);
        n.right = addToTree(arr, mid + 1, end);
        return n;
    }
    
    public void createMinimalBST(int[] a) {
        root = addToTree(a, 0, a.length-1);
    }
    public LinkedList<LinkedList<TreeNode>> getLevelLists() {
        LinkedList<TreeNode> curtLevel;
        LinkedList<TreeNode> nextLevel = new LinkedList<TreeNode>();
        LinkedList<LinkedList<TreeNode>> lists = new LinkedList<LinkedList<TreeNode>>();

        nextLevel.add(root);
        while(!nextLevel.isEmpty()) {
            curtLevel = nextLevel;
            lists.add(curtLevel);
            nextLevel = new LinkedList<TreeNode>();
            for (Iterator<TreeNode> iterator = curtLevel.iterator(); iterator.hasNext();) {
                TreeNode node = (TreeNode) iterator.next();
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
        }
        
        return lists;       
    }
    

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        int[] a = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        tree.createMinimalBST(a);
//        tree.printTree();
        LinkedList<LinkedList<TreeNode>> lists = tree.getLevelLists();
        for (LinkedList<TreeNode> list : lists) {
            System.out.println(Arrays.toString(list.toArray())); 
        }

        // int maxH = tree.getMaxHeight(tree.rt);
        // int minH = tree.getMinHeight(tree.rt);
        // System.out.println(maxH);
        // System.out.println(minH);

    }
}