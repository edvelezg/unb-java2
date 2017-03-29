package other;

public class TrieNode {

    // make child nodes
    private TrieNode[] c;
    // flag for end of word
    private boolean    flag = false;

    public static void main(String[] args) {
        TrieNode trie = new TrieNode();
        trie.insert("HELLO");
        trie.insert("HELIOS");
        trie.insert("HELICON");

        if (trie.has2("HELIOS")) {
            System.out.println("I have it");
        } else {
            System.out.println("I don't have it");
        }

        if (trie.has2("HELI")) {
            System.out.println("I have it");
        } else {
            System.out.println("I don't have it");
        }

    }

    public TrieNode() {
        c = new TrieNode[26]; // 1 for each letter in alphabet
    }

    protected void insert(String word) {
//        System.out.println(this.c + ".insert(" + word + ")");
        int val = word.charAt(0) - 64;

        // if the value of the child node at val is null, make a new node
        // there to represent the letter
        if (c[val] == null) {
            c[val] = new TrieNode();
            System.out.println("c[ " + word.charAt(0) + " ]  = new TrieNode();");
        }

        // if word length > 1, then word is not finished being added.
        // otherwise, set the flag to true so we know a word ends there.
        if (word.length() > 1) {
            c[val].insert(word.substring(1));
        } else {
            System.out.println("c[ " + word.charAt(0) + " ].flag = true;");
            c[val].flag = true;
        }
    }

    public boolean has(String word) {
        System.out.println("has(word="+word+")");
        int val = word.charAt(0) - 64;

        if (c[val] != null && word.length() > 1) {
            return c[val].has(word.substring(1)); // <-- Change is on this line
        } else if (c[val].flag == true && word.length() == 1) {
            return true;
        }

        return false;
    }

    public boolean has2(String word) {
        System.out.println("has2(word="+word+")");
        boolean found = false;
        int val = word.charAt(0) - 64;

        if (c[val] != null && word.length() > 1) {
            found = c[val].has2(word.substring(1)); // <-- Change is on this line
        } else if (c[val].flag == true && word.length() == 1) {
            found = true;
        }
        return found;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}