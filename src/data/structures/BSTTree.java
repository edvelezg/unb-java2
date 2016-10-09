package data.structures;

import java.util.Arrays;
import java.util.LinkedList;

class BSTN {
    int  val;
    BSTN left;
    BSTN right;

    public BSTN(int value, BSTN left, BSTN right) {
        this.val = value;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return String.format("%d", this.val);
    }
}

public class BSTTree {

    private BSTN root;

    public BSTN getRoot() {
        return root;
    }

    public void setRoot(BSTN root) {
        this.root = root;
    }

    public BSTTree() {
        setRoot(null);
    }

    public void add(int value) {
        if (root == null) {
            root = new BSTN(value, null, null);
            return;
        }
        add(getRoot(), value);
    }

    public void recAdd(int value) {
        setRoot(recursiveAdd(getRoot(), value));
    }

    public BSTN add(BSTN root, int val) {
        BSTN cur = root;
        while (true) {
            BSTN par = cur;
            if (cur.val > val) {
                cur = cur.left;
                if (cur == null) {
                    cur = new BSTN(val, null, null);
                    par.left = cur;
                    return cur;
                }
            } else {
                cur = cur.right;
                if (cur == null) {
                    cur = new BSTN(val, null, null);
                    par.right = cur;
                    return cur;
                }
            }

        }
    }

    public boolean add(BSTN root, BSTN n) {
        BSTN cur = root;
        while (true) {
            if (cur.val > n.val) {
                if (cur.left == null) {
                    cur.left = n;
                    return true;
                }
                cur = cur.left;
            } else {
                if (cur.right == null) {
                    cur.right = n;
                    return true;
                }
                cur = cur.right;
            }
        }
    }

    private BSTN recursiveAdd(BSTN root, int value) {
        if (root == null) {
            // System.out.println(String.format("Returning new node %s",
            // value));
            return new BSTN(value, null, null);
        } else if (root.val >= value) {
            // System.out.println(String
            // .format("Add to Stack node %s", root.value));
            root.left = recursiveAdd(root.left, value);
            // System.out.println(String.format("Assign node %s left-> %s",
            // root.value, root.left.value));
        } else {
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
        BSTN found = find(getRoot(), val);
        if (found == null) {
            return false;
        }
        return true;
    }

    public BSTN find(BSTN root, int val) {
        BSTN cur = root;
        while (cur != null) {
            if (cur.val == val) {
                return cur;
            } else if (cur.val > val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return null;
    }

    public BSTN recursiveFind(BSTN root, int val) {
        if (root == null) {
            return null;
        } else if (root.val == val) {
            return root;
        } else if (root.val > val) {
            recursiveFind(root.left, val);
        } else {
            recursiveFind(root.right, val);
        }
        return null;
    }

    public void createBST(int[] a) {
        createBST(a, 0, a.length-1);
//        root = createBST(a, 0, a.length - 1);
    }

    public BSTN createBST2(int[] a, int start, int end) {
        if (end < start) {
            return null;
        }

        int mid = (start + end) / 2;
        BSTN n = new BSTN(a[mid], null, null);
        n.left = createBST2(a, start, mid - 1);
        n.right = createBST2(a, mid + 1, end);
        return n;
    }

    public void createBST(int[] a, int start, int end) {
        if (end < start) {
            return;
        }
        int mid = (start + end) / 2;
        this.add(a[mid]);
        createBST(a, start, mid - 1);
        createBST(a, mid + 1, end);
    }

    public void printSideways() {
        printSideways(getRoot(), 0);
    }

    // post: prints in reversed preorder the tree with given
    // root, indenting each line to the given level
    private void printSideways(BSTN root, int level) {
        if (root != null) {
            printSideways(root.left, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.val);
            printSideways(root.right, level + 1);
        }
    }
    
    public LinkedList<LinkedList<BSTN>> getLists()
    {
        LinkedList<LinkedList<BSTN>> lists = new LinkedList<LinkedList<BSTN>>();
        LinkedList<BSTN> curr;
        LinkedList<BSTN> next = new LinkedList<BSTN>();
        
        BSTN root = getRoot();
        next.add(root);
        while (!next.isEmpty()) {
            curr = next;
            lists.add(curr);
            next = new LinkedList<BSTN>();
            for (int i = 0; i < curr.size(); i++) {
                if (curr.get(i).left != null) {
                    next.add(curr.get(i).left);
                }
                if (curr.get(i).right != null) {
                    next.add(curr.get(i).right);
                }
            }
        }
        
        return lists;
    }
    

    public boolean delete(int id) {
        BSTN par = root;
        BSTN cur = root;
        boolean isLeftChild = false;
        while (cur.val != id) {
            par = cur;
            if (cur.val > id) {
                isLeftChild = true;
                cur = cur.left;
            } else {
                isLeftChild = false;
                cur = cur.right;
            }
            if (cur == null) {
                return false;
            }
        }
        // if i am here that means we have found the node
        // Case 1: if node to be deleted has no children
        if (cur.left == null && cur.right == null) {
            if (cur == root) {
                root = null;
            }
            if (isLeftChild == true) {
                par.left = null;
            } else {
                par.right = null;
            }
        }
        // Case 2 : if node to be deleted has only one child
        else if (cur.right == null) {
            if (cur == root) {
                root = cur.left;
            } else if (isLeftChild) {
                par.left = cur.left;
            } else {
                par.right = cur.left;
            }
        } else if (cur.left == null) {
            if (cur == root) {
                root = cur.right;
            } else if (isLeftChild) {
                par.left = cur.right;
            } else {
                par.right = cur.right;
            }
        } else if (cur.left != null && cur.right != null) {

            // now we have found the minimum element in the right sub tree
            BSTN successor = getSuccessor(cur);
            if (cur == root) {
                root = successor;
            } else if (isLeftChild) {
                par.left = successor;
            } else {
                par.right = successor;
            }
            successor.left = cur.left;
        }
        return true;
    }

    public BSTN getSuccessor(BSTN cur) {
        BSTN successsor = null;
        BSTN successsorParent = null;
        BSTN current = cur.right;
        while (current != null) {
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        // check if successor has the right child, it cannot have left child for
        // sure
        // if it does have the right child, add it to the left of
        // successorParent.
        // successsorParent
        if (successsor != cur.right) {
            successsorParent.left = successsor.right;
            successsor.right = cur.right;
        }
        return successsor;
    }

    public static void main(String[] args) {
        BSTTree t = new BSTTree();
        int[] a = new int[15];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }
        t.createBST(a);
        printLists(t);
        t.delete(8);
        printLists(t);
        t.delete(7);
        printLists(t);
        
//        t.printSideways();
    }

    private static void printLists(BSTTree t) {
        for (LinkedList<BSTN> l : t.getLists()) {
            BSTN[] array = l.toArray(new BSTN[l.size()]);
            System.out.println(Arrays.toString(array));
        }
    }
}
