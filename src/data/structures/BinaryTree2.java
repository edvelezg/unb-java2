package data.structures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

class TreeNode2 {
    int       data;
    TreeNode2 left;
    TreeNode2 right;
    TreeNode2 parent;

    public TreeNode2(int data, TreeNode2 lt, TreeNode2 rt, TreeNode2 parent) {
        this.data = data;
        this.left = lt;
        this.right = rt;
        this.parent = parent;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return this.data + "";
    }
}

public class BinaryTree2 {

    TreeNode2 root;

    public BinaryTree2() {
    }

    public int getNumElems(TreeNode2 root) {
        if (root == null) {
            return 0;
        }

        int sum = 1 + getNumElems(root.left) + getNumElems(root.right);
        return sum;
    }

    public int getMaxHeight(TreeNode2 root) {
        if (root == null) {
            return 0;
        }

        int sum = 1 + Math.max(getMaxHeight(root.left), getMaxHeight(root.right));
        return sum;
    }

    public int getMinHeight(TreeNode2 root) {
        if (root == null) {
            return 0;
        }

        int sum = 1;
        sum = sum + Math.min(getMinHeight(root.left), getMinHeight(root.right));
        return sum;
    }

    public TreeNode2 buildTree(int[] array, int idx) {
        if (idx >= array.length) {
            return null;
        } else {

            TreeNode2 n = new TreeNode2(array[idx], null, null, null);
            n.left = buildTree(array, 2 * idx + 1);
            n.left.parent = n;
            n.right = buildTree(array, 2 * idx + 2);
            n.right.parent = n;
            return n;
        }
    }

    public void buildTree(int[] array) {
        root = buildTree(array, 0);

    }

    public void printTree() {
        printTree(root);
    }

    public void printTree(TreeNode2 tmpRoot) {

        java.util.LinkedList<TreeNode2> currentLevel = new java.util.LinkedList<TreeNode2>();
        java.util.LinkedList<TreeNode2> nextLevel = new java.util.LinkedList<TreeNode2>();
        currentLevel.add(tmpRoot);

        while (!currentLevel.isEmpty()) {
            Iterator<TreeNode2> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                TreeNode2 currentNode = iter.next();
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
            nextLevel = new java.util.LinkedList<TreeNode2>();
        }
    }

    public static TreeNode2 addToTree(int arr[], int start, int end) {
        // System.out.println(String.format("addToTree(arr=%s, start=%d
        // end=%d)", arr.toString(), start, end));
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode2 n = new TreeNode2(arr[mid], null, null, null);
        // System.out.println("TreeNode2 n = new TreeNode2(arr[mid], null,
        // null);");
        n.left = addToTree(arr, start, mid - 1);
        if (n.left != null) {
            n.left.parent = n;
        }
        n.right = addToTree(arr, mid + 1, end);
        if (n.right != null) {
            n.right.parent = n;
        }
        return n;
    }

    public void createMinimalBST(int[] a) {
        root = addToTree(a, 0, a.length - 1);
    }

    public LinkedList<LinkedList<TreeNode2>> getLevelLists() {
        LinkedList<TreeNode2> curtLevel;
        LinkedList<TreeNode2> nextLevel = new LinkedList<TreeNode2>();
        LinkedList<LinkedList<TreeNode2>> lists = new LinkedList<LinkedList<TreeNode2>>();

        nextLevel.add(root);
        while (!nextLevel.isEmpty()) {
            curtLevel = nextLevel;
            lists.add(curtLevel);
            nextLevel = new LinkedList<TreeNode2>();
            for (Iterator<TreeNode2> iterator = curtLevel.iterator(); iterator.hasNext();) {
                TreeNode2 node = (TreeNode2) iterator.next();
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

    public static TreeNode2 inorderSucc(TreeNode2 e) {
        if (e != null) {
            TreeNode2 p;
            // Found right children -> return 1st inorder node on right
            if (e.parent == null || e.right != null) {
                p = leftMostChild(e.right);
            } else {
                // Go up until we’re on left instead of right (case 2b)
                while ((p = e.parent) != null) {
                    if (p.left == e) {
                        break;
                    }
                    e = p;
                }
            }
            return p;
        }
        return null;
    }

    public static TreeNode2 leftMostChild(TreeNode2 e) {
        if (e == null)
            return null;
        while (e.left != null)
            e = e.left;
        return e;
    }

    public static TreeNode2 IOS(TreeNode2 e) {
        
        if (e == null) return null;
        
        TreeNode2 suc = null;
        if (e.parent == null || e.right != null) {
            suc = LMC(e.right);
        } else {
            while (e.parent != null) {
                suc = e.parent;
                if (suc.left == e)
                    return suc;
                e = suc;
            }
            return null;
        }
        return suc;
    }

    public static TreeNode2 LMC(TreeNode2 e) {
        if (e == null) return null;
        while (e.left != null)
            e = e.left;
        return e;
    }

    public static void main(String[] args) {
        BinaryTree2 tree = new BinaryTree2();

        int[] a = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
        tree.createMinimalBST(a);
        // tree.printTree();
        LinkedList<LinkedList<TreeNode2>> lists = tree.getLevelLists();
        for (LinkedList<TreeNode2> list : lists) {
            System.out.println(Arrays.toString(list.toArray()));
        }
        TreeNode2 treeNode2 = lists.get(3).get(3);
        TreeNode2 succ = BinaryTree2.IOS(treeNode2);
        System.out.println("Successor of:  " + treeNode2.data + " is " + (succ != null ? succ.data : "null"));

        // int maxH = tree.getMaxHeight(tree.rt);
        // int minH = tree.getMinHeight(tree.rt);
        // System.out.println(maxH);
        // System.out.println(minH);

    }
}