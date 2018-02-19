package data.structures;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Date 04/25/2016
 * 
 * @author Tushar Roy
 *
 *         Insert/delete/search into trie data structure
 *
 *         Reference https://en.wikipedia.org/wiki/Trie
 */
public class Trie {

	private class TrieNode {
		char name;
		Map<Character, TrieNode> children;
		boolean endOfWord;

		public TrieNode(char name) {
			children = new HashMap<>();
			endOfWord = false;
			this.name = name;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return name + ": " + children.keySet().toString();
		}
	}

	private final TrieNode root;
	private static StringBuffer bf;

	public Trie() {
		root = new TrieNode('#');
	}

	/**
	 * Iterative implementation of insert into trie
	 */
	public void insert(String word) {
//		System.out.println("insert(word=" + word + ")");
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);
			if (node == null) {
				node = new TrieNode(ch);
				current.children.put(ch, node);
			}
			current = node;
//			System.out.println("node " + ch + " => " + node);
		}
		// mark the current nodes endOfWord as true
		current.endOfWord = true;
//		System.out.println("node " + current + " EOW = true");
	}

	/**
	 * Recursive implementation of insert into trie
	 */
	public void insertRecursive(String word) {
		insertRecursive(root, word, 0);
	}

	private void insertRecursive(TrieNode current, String word, int index) {
		if (index == word.length()) {
			// if end of word is reached then mark endOfWord as true on current
			// node
			current.endOfWord = true;
			return;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);

		// if node does not exists in map then create one and put it into map
		if (node == null) {
			node = new TrieNode(ch);
			current.children.put(ch, node);
		}
		insertRecursive(node, word, index + 1);
	}
	
    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
           char curLetter = word.charAt(i);
           if (node.children.containsKey(curLetter)) {
               node = node.children.get(curLetter);
           } else {
               return null;
           }
        }
        return node;
    }

    // Returns if the word is in the trie.
    public boolean searchWord(String word) {
       TrieNode node = searchPrefix(word);
       return node != null && node.endOfWord;
    }

	/**
	 * Iterative implementation of search into trie.
	 */
	public boolean search(String word) {
//		System.out.println("search(word=" + word + ")");
		TrieNode current = root;
//		System.out.println("root " + root);
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.children.get(ch);

			// if node does not exist for given char then return false
			if (node == null) {
				return false;
			}
//			System.out.println("node " + node);
			current = node;
		}
		// return true of current's endOfWord is true else return false.
//		System.out.println("node " + current + " EOW = " + current.endOfWord);
		return current.endOfWord;
	}

	/**
	 * Recursive implementation of search into trie.
	 */
	public boolean searchRecursive(String word) {
		return searchRecursive(root, word, 0);
	}

	private boolean searchRecursive(TrieNode current, String word, int index) {
		if (index == word.length()) {
			// return true of current's endOfWord is true else return false.
			return current.endOfWord;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		// if node does not exist for given char then return false
		if (node == null) {
			return false;
		}
		return searchRecursive(node, word, index + 1);
	}

	/**
	 * Delete word from trie.
	 */
	public void delete(String word) {
		delete(root, word, 0);
	}

	/**
	 * Returns true if parent should delete the mapping
	 */
	private boolean delete(TrieNode current, String word, int index) {
		if (index == word.length()) {
			// when end of word is reached only delete if currrent.endOfWord is
			// true.
			if (!current.endOfWord) {
				return false;
			}
			current.endOfWord = false;
			// if current has no other mapping then return true
			return current.children.size() == 0;
		}
		char ch = word.charAt(index);
		TrieNode node = current.children.get(ch);
		if (node == null) {
			return false;
		}
		boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

		// if true is returned then delete the mapping of character and trienode
		// reference from map.
		if (shouldDeleteCurrentNode) {
			current.children.remove(ch);
			// return true if no mappings are left in the map.
			return current.children.size() == 0;
		}
		return false;
	}
	
	public void findPrefix(String pref) {

		TrieNode node = searchPrefix(pref);
		System.out.println(node);
		bf = new StringBuffer(pref);
		getWords(node, pref);
		
//		Set<Character> keySet = curr.children.keySet();
//		for (Iterator it = keySet.iterator(); it.hasNext();) {
//			Character character = (Character) it.next();
//			TrieNode node = curr.children.get(character);
//			System.out.print(node);
//			getWords(node);
//		}
	}
	
	public static void getWords(TrieNode node, String pref) {
		if (node.endOfWord) {
			System.out.println(bf.toString());
		}
		Set<Character> keySet = node.children.keySet();
//		System.out.print("Heli");
		for (Character ch : keySet) {
			bf.append(ch);
			TrieNode trieNode = node.children.get(ch);
			getWords(trieNode, pref);
			bf.setLength(bf.length()-1);
		}
//		bf = new StringBuffer(pref);
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("Hello");
		t.insert("Helm");
		t.insert("Helios");
		t.insert("Helior");

		t.search("Helm");
		t.search("Helios");
		t.search("Help");
		
		t.findPrefix("Hel");

	}
}