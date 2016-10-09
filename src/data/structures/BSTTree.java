package data.structures;

class BSTreeNode {
    int value;
    BSTreeNode left;
    BSTreeNode right;

    public BSTreeNode(int value, BSTreeNode left, BSTreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class BSTTree {

    private BSTreeNode overallroot; 

    public BSTreeNode getOverallroot() {
        return overallroot;
    }

    public void setOverallroot(BSTreeNode overallroot) {
        this.overallroot = overallroot;
    }

    public BSTTree() {
        setOverallroot(null);
    }

    public void add(int value) {
        if (overallroot == null) {
            overallroot = new BSTreeNode(value, null, null);
            return;
        }
        add(getOverallroot(), value);
    }

    public void recAdd(int value) {
        setOverallroot(recursiveAdd(getOverallroot(), value));
    }

    public BSTreeNode add(BSTreeNode root, int val) {
        BSTreeNode cur = root;
        while (true) {
            BSTreeNode par = cur;
            if (cur.value > val) {
                cur = cur.left;
                if (cur == null) {
                    cur = new BSTreeNode(val, null, null);
                    par.left = cur;
                    return cur;
                }
            }
            else {
                cur = cur.right;
                if (cur == null) {
                    cur = new BSTreeNode(val, null, null);
                    par.right = cur;
                    return cur;
                }
            }

        }
    }

    private BSTreeNode recursiveAdd(BSTreeNode root, int value) {
        if (root == null) {
            // System.out.println(String.format("Returning new node %s",
            // value));
            return new BSTreeNode(value, null, null);
        }
        else if (root.value >= value) {
            // System.out.println(String
            // .format("Add to Stack node %s", root.value));
            root.left = recursiveAdd(root.left, value);
            // System.out.println(String.format("Assign node %s left-> %s",
            // root.value, root.left.value));
        }
        else {
            // System.out.println(String
            // .format("Add to Stack node %s", root.value));
            root.right = recursiveAdd(root.right, value);
            // System.out.println(String.format("Assign node %s right-> %s",
            // root.value, root.right.value));
        }
        // System.out.println(String.format("Returning node %s", root.value));
        return root;
    }

    public boolean contains(int val) {
        BSTreeNode found = find(getOverallroot(), val);
        if (found == null) {
            return false;
        }
        return true;
    }

    public BSTreeNode find(BSTreeNode root, int val) {
        BSTreeNode cur = root;
        while (cur != null) {
            if (cur.value == val) {
                return cur;
            }
            else if (cur.value > val) {
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }
        return null;
    }

    public BSTreeNode recursiveFind(BSTreeNode root, int val) {
        if (root == null) {
            return null;
        }
        else if (root.value == val) {
            return root;
        }
        else if (root.value > val) {
            recursiveFind(root.left, val);
        }
        else {
            recursiveFind(root.right, val);
        }
        return null;
    }

    public void createBST(int[] a) {
        overallroot = createBST2(a, 0, a.length - 1);
    }

//	public void createBST(int[] a) {
//		createBST(a, 0, a.length - 1);
//	}

    public BSTreeNode createBST2(int[] a, int start, int end) {
        if (end < start) {
            return null;
        }

        int mid = (start + end) / 2;
        return new BSTreeNode(a[mid], createBST2(a, start, mid - 1), createBST2(a, mid + 1, end));
    }

//	public void createBST(int[] a, int start, int end) {
//		if (end < start) {
//			return;
//		}
//		int mid = (start + end) / 2;
//		this.add(a[mid]);
//		createBST(a, mid + 1, end);
//		createBST(a, start, mid - 1);
//	}

    public void printSideways() {
        printSideways(getOverallroot(), 0);
    }

    // post: prints in reversed preorder the tree with given
    // root, indenting each line to the given level
    private void printSideways(BSTreeNode root, int level) {
        if (root != null) {
            printSideways(root.left, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.value);
            printSideways(root.right, level + 1);
        }
    }

    public static void main(String[] args) {
        BSTTree t = new BSTTree();
        int[] a = new int[15];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        t.createBST(a);
        t.printSideways();
    }
}
