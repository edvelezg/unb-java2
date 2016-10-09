package data.structures;

public class BinarySearchTree {
    public BSTNode root;

    public BinarySearchTree() {
        root = null;
    }

    public boolean find(int id) {
        BSTNode current = root;
        while (current != null) {
            if (current.data == id) {
                return true;
            } else if (current.data > id) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        BSTNode parent = root;
        BSTNode current = root;
        boolean isLeftChild = false;
        while (current.data != id) {
            parent = current;
            if (current.data > id) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            if (current == null) {
                return false;
            }
        }
        // if i am here that means we have found the node
        // Case 1: if node to be deleted has no children
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild == true) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // Case 2 : if node to be deleted has only one child
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else if (current.left != null && current.right != null) {

            // now we have found the minimum element in the right sub tree
            BSTNode successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public BSTNode getSuccessor(BSTNode deleleNode) {
        BSTNode successsor = null;
        BSTNode successsorParent = null;
        BSTNode current = deleleNode.right;
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
        if (successsor != deleleNode.right) {
            successsorParent.left = successsor.right;
            successsor.right = deleleNode.right;
        }
        return successsor;
    }

    public void insert(int id) {
        BSTNode newNode = new BSTNode(id);
        if (root == null) {
            root = newNode;
            return;
        }
        BSTNode current = root;
        BSTNode parent = null;
        while (true) {
            parent = current;
            if (id < current.data) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public void display() {
        display(root);
    }

    public void display(BSTNode root) {
        if (root != null) {
            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);
        }
    }

    public static void main(String arg[]) {
        BinarySearchTree b = new BinarySearchTree();
        b.insert(3);
        b.insert(8);
        b.insert(1);
        b.insert(2);
        b.insert(4);
        b.insert(9);
        b.delete(9);
        b.insert(9);
        System.out.println("Original Tree : ");
        b.display();
        System.out.println("");
        System.out.println("Check whether Node with value 4 exists : " + b.find(4));
        // b.display();
        System.out.println("\n Delete Node with one child (1) : " + b.delete(1));
        b.display();
        System.out.println("\n Delete Node with Two children (8) : " + b.delete(8));
        b.display();
    }

    public void DSW() {
        if (null != root) {
            createBackbone();// effectively: createBackbone( root)
            createPerfectBST();// effectively: createPerfectBST( root)
        }
    }

    /**
     * Time complexity: O(n)
     */
    private void createBackbone() {
        BSTNode grandParent = null;
        BSTNode parent = root;
        BSTNode leftChild;

        while (null != parent) {
            leftChild = parent.left;
            if (null != leftChild) {
                grandParent = rotateRight(grandParent, parent, leftChild);
                parent = leftChild;
            } else {
                grandParent = parent;
                parent = parent.right;
            }
        }
    }

    /************************************************************************
     *   Before      After
     *    Gr          Gr
     *     \           \
     *     Par         Ch
     *    /  \        /  \
     *   Ch   Z      X   Par
     *  /  \            /  \
     * X    Y          Y    Z
     ***********************************************************************/
    private BSTNode rotateRight(BSTNode grandParent, BSTNode parent, BSTNode leftChild) {
        if (null != grandParent) {
            grandParent.right = leftChild;
        } else {
            root = leftChild;
        }
        parent.left = leftChild.right;
        leftChild.right = parent;
        return grandParent;
    }

    /**
     * Time complexity: O(n)
     */
    private void createPerfectBST() {
        int n = 0;
        for (BSTNode tmp = root; null != tmp; tmp = tmp.right) {
            n++;
        }
        // m = 2^floor[lg(n+1)]-1, ie the greatest power of 2 less than n: minus
        // 1
        int m = greatestPowerOf2LessThanN(n + 1) - 1;
        makeRotations(n - m);

        while (m > 1) {
            makeRotations(m /= 2);
        }
    }

    /**
     * Time complexity: log(n)
     */
    private int greatestPowerOf2LessThanN(int n) {
        int x = MSB(n);// MSB
        return (1 << x);// 2^x
    }

    /**
     * Time complexity: log(n) return the index of most significant set bit:
     * index of least significant bit is 0
     */
    public int MSB(int n) {
        int ndx = 0;
        while (1 < n) {
            n = (n >> 1);
            ndx++;
        }
        return ndx;
    }

    private void makeRotations(int bound) {
        BSTNode grandParent = null;
        BSTNode parent = root;
        BSTNode child = root.right;
        for (; bound > 0; bound--) {
            try {
                if (null != child) {
                    rotateLeft(grandParent, parent, child);
                    grandParent = child;
                    parent = grandParent.right;
                    child = parent.right;
                } else {
                    break;
                }
            } catch (NullPointerException convenient) {
                break;
            }
        }
    }

    private void rotateLeft(BSTNode grandParent, BSTNode parent, BSTNode rightChild) {
        if (null != grandParent) {
            grandParent.right = rightChild;
        } else {
            root = rightChild;
        }
        parent.right = rightChild.left;
        rightChild.left = parent;
    }
}

class BSTNode {
    int     data;
    BSTNode left;
    BSTNode right;

    public BSTNode(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}