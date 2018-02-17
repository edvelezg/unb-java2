package google.interview;

// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution {
	class BST {
		int data;
		BST lft;
		BST rgt;

		public BST(int data, BST lft, BST rgt) {
			this.data = data;
			this.lft = lft;
			this.rgt = rgt;
		}
	}

	BST root = null;

	void insert(int n) {
		if (root == null)
			root = new BST(n, null, null);
		else {
			BST curr = root;
			while (true) {
				if (n < curr.data) {
					if (curr.lft == null) {
						curr.lft = new Solution.BST(n, null, null);
						break;
					} else {
						curr = curr.lft;
					}
				} else {
					if (curr.rgt == null) {
						curr.rgt = new Solution.BST(n, null, null);
						break;
					} else
						curr = curr.rgt;
				}
			}
		}

	}
}