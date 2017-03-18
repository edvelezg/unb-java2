package other;

class LinkedTree {
    TreeNode root;
    int      size;

    public LinkedTree() {
        root = null;
        size = 0;
    }

    class TreeNode {
        Object   item;
        TreeNode parent;
        TreeNode first;
        TreeNode next;

        public TreeNode(Object item, TreeNode parent, TreeNode first, TreeNode next) {
            this.item = item;
            this.parent = parent;
            this.first = first;
            this.next = next;
        }

        public void preorder() {
            System.out.println(String.format("%5d", this.item));
            if (first != null) {
                first.preorder();
            }

            if (next != null) {
                next.preorder();
            }
        }

        public void postorder() {
            if (first != null) {
                first.postorder();
            }
            System.out.println(String.format("%5d", this.item));
            if (next != null) {
                next.postorder();
            }
        }

        public void traversal() {
            if (first != null) {
                first.traversal();
            }
            if (next != null) {
                next.traversal();
            }
            System.out.println(String.format("%5d", this.item));
        }
    }

    public static void main(String[] args) {
        LinkedTree l = new LinkedTree();
        l.root = l.new TreeNode(5, null, null, null);
        l.root.first = l.new TreeNode(6, l.root, null, null);
        l.root.first.next = l.new TreeNode(7, l.root, null, null);
        l.root.first.next.next = l.new TreeNode(8, l.root, null, null);

        TreeNode node = l.root.first.next;

        node.first = l.new TreeNode(9, node, null, null);
        node.first.next = l.new TreeNode(10, node, null, null);
        node.first.next.next = l.new TreeNode(11, node, null, null);

        l.root.preorder();
        System.out.println();
        l.root.postorder();

    }

}
