package challenges;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TrieTreeImpl {

	public static void main(String[] args) throws IOException, InterruptedException {
		File file = new File("/home/sundaramtiwari/Documents/CodeEval/trieTree.txt");
		BufferedReader buffer = new BufferedReader(new FileReader(file));
		String line = buffer.readLine().trim();
		List<String> wordList = Arrays.asList(line.split(","));
		Collections.sort(wordList);

		Map<Character, Node> children = new HashMap<Character, Node>();
		TrieTree trie = new TrieTree(children);

		for (String word : wordList) {
			addWordToTrie(word.trim(), children);
		}

		trie.setChildren(children);
		//System.out.println(trie);

		long startTime = System.nanoTime();
		Set<String> patterns = trie.getPattern("unabl", trie);
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime + " ns.");
		System.out.println("Patterns: " + patterns);
	}

	private static void addWordToTrie(String word, Map<Character, Node> children) {
		for (int i = 0; i < word.length(); i++) {
			// Check the character in the provided children set. If it exists, recursively call addWordToTrie till a leaf is encountered. 
			// At this point, we add the remaining characters to trie or the terminating character.
			char character = word.charAt(i);

			// Case I: Character is in children
			if (children != null) {
				if (children.containsKey(character)) {
					// Case (A): Length of the word is > 1, we do recursion
					Node node = children.get(character);

					if (word.length() > 1) {
						if (node.getNext() == null) {
							Map<Character, Node> next = new HashMap<Character, Node>();
							node.setNext(next);
						}
						addWordToTrie(word.substring(1), node.getNext());
						return;
					}
					// Case (B): Length of the word is 1, check and add terminating character as a child node in children of this Node.
					else if (word.length() == 1) {
						Node node2 = children.get(character);
						Map<Character, Node> next = node2.getNext();
						if (!next.containsKey(Node.terminatingChar)) {
							next.putAll(getTerminatingNodeMap());
						} else {
							return;
						}
					}
				}
				// Case II: Character is not in children
				else {
					// Case (A): Length of the word is > 1, we do recursion
					if (word.length() > 1) {
						// Add current character to children Map and add remaining characters as child to this node.
						Map<Character, Node> next = new HashMap<Character, Node>();
						String remainingChars = word.substring(1);
						for (int j = 0; j < remainingChars.length(); j++) {
							if (remainingChars.length() == 1) {
								Node temp = new Node(remainingChars.charAt(j), getTerminatingNodeMap());
								next.put(remainingChars.charAt(j), temp);
							} else {
								addWordToTrie(remainingChars, next);
								break;
							}
						}

						Node charNode = new Node(character, next);
						children.put(character, charNode);
						return;
					}
					// Case (B): Length of the word is 1, we add it to children with a node that points to terminating character
					else {
						Node charNode = new Node(character, getTerminatingNodeMap());
						children.put(character, charNode);
						return;
					}
				}
			} else {
				children = new HashMap<Character, Node>();

			}
		}
	}

	public static Map<Character, Node> getTerminatingNodeMap() {
		Node terminatingNode = new Node(Node.terminatingChar, null);
		Map<Character, Node> terminatingMap = new HashMap<Character, Node>();
		terminatingMap.put(Node.terminatingChar, terminatingNode);
		return terminatingMap;
	}
}

class TrieTree {
	private Map<Character, Node> children;

	public TrieTree() {
		super();
	}

	public TrieTree(Map<Character, Node> children) {
		super();
		this.children = children;
	}

	public Set<String> getPattern(String pattern, TrieTree trie) {
		boolean exists = true;
		Map<Character, Node> children = trie.getChildren();
		Set<String> patternSet = new HashSet<String>();

		// Loop through characters of pattern and find children of last
		// character.
		for (int i = 0; i < pattern.length(); i++) {
			char ch = pattern.charAt(i);
			if (children.containsKey(ch)) {
				children = children.get(ch).getNext();
			} else {
				exists = false;
				break;
			}
		}

		// If pattern exists in trie, recursively find all the possible words
		if (exists) {
			addPossiblePatternstoSet(children, patternSet, pattern);
		} else {
			return patternSet;
		}

		return patternSet;
	}

	private String addPossiblePatternstoSet(Map<Character, Node> children, Set<String> patternSet, String parent) {
		for (Entry<Character, Node> entry : children.entrySet()) {
			Node temp = entry.getValue();
			if (temp == null || temp.getData() == Node.terminatingChar || temp.getNext() == null) {
				patternSet.add(parent);
			} else {
				String tempPattern = addPossiblePatternstoSet(temp.getNext(), patternSet, parent + temp.getData());
				if (tempPattern != null) {
					parent += tempPattern;
					patternSet.add(parent);
				}
			}
		}
		return null;
	}

	public Map<Character, Node> getChildren() {
		return children;
	}

	public void setChildren(Map<Character, Node> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TrieTree [" + children + "]";
	}

}

class Node {
	public static final char terminatingChar = '#';
	private char data;
	private Map<Character, Node> next;

	public Node() {
	}

	public Node(char data, Map<Character, Node> next) {
		super();
		this.data = data;
		this.next = next;
	}

	public char getData() {
		return data;
	}

	public void setData(char data) {
		this.data = data;
	}

	public Map<Character, Node> getNext() {
		return next;
	}

	public void setNext(Map<Character, Node> next) {
		this.next = next;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + data;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (data != other.data)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", next=" + next + "]";
	}
}
