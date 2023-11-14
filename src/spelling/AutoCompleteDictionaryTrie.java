package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		word = word.toLowerCase();
		TrieNode curr = root;
		
		for (int i = 0; i < word.length(); i++) {

			
			if (curr.getChild(word.charAt(i)) != null) {
				curr = curr.getChild(word.charAt(i));
				// if words already in the dictionary, return false 
				if (curr.endsWord() && curr.getText().equalsIgnoreCase(word)) {
					return false;
				}
			} else {
				curr = curr.insert(word.charAt(i));
				size++;
			}
		}
		
		curr.setEndsWord(true);
		
	    return true;
	}
	
	private int wordCount(TrieNode curr) {
		
		int counter = 0;
		
		if (curr.endsWord()) {
			counter++;
		}
		
		// convert set to an iterable data structure 
		// System.out.println("----------------------New round-----------------------");
		// printNode(curr);
		// System.out.println(curr.getValidNextCharacters().size());
		
		
		if (curr.getValidNextCharacters() == null) {
			return counter;
		}
		
		Set<Character> charSet = curr.getValidNextCharacters();
			
		// System.out.println(charSet);

		Iterator<Character> charIterator = charSet.iterator();
			
		while (charIterator.hasNext()) {
			counter += wordCount(curr.getChild(charIterator.next()));
		}

		return counter;
	}

	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
		return wordCount(root);
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		s = s.toLowerCase();
		TrieNode curr = root;
		
		// System.out.println("----------------------New round-----------------------");
		// System.out.println("string: " + s);
		
		for (int i = 0; i < s.length(); i++) {
			
			// System.out.println(curr.getText());
			
			// if current node has child linking to the character
			if (curr.getChild(s.charAt(i)) != null) {
				curr = curr.getChild(s.charAt(i));
				
				// if it is a word in the trie, return true 
				if (curr.endsWord() && curr.getText().toLowerCase().equals(s)) {
					// System.out.println(curr.getText());
					return true;
				}
			} else {
				return false;
			}
		}
		
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 prefix = prefix.toLowerCase();
    	 TrieNode curr = root;
    	 
    	 for (int i = 0; i < prefix.length(); i++) {
    		 if (curr.getChild(prefix.charAt(i)) != null) {
    			 curr = curr.getChild(prefix.charAt(i));
    			 
    			 // found the stem in the trie
        		 if (curr.getText().toLowerCase().equals(prefix)) {
        			 break;
        		 }
    		 } else {
    			 return new ArrayList<String>();
    		 }
    	 }
    	 
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.add(curr);
    	 
    	 //    Create a list of completions to return (initially empty)
    	 List<String> completions = new ArrayList<String>();
    	 
    	 //    While the queue is not empty and you don't have enough completions:
    	 while (queue.isEmpty() == false && completions.size() < numCompletions) {
    		 
        	 //       remove the first Node from the queue
    		 TrieNode firstNode = queue.remove();
    		 
        	 //       If it is a word, add it to the completions list
    		 if (firstNode.endsWord()) {
    			 completions.add(firstNode.getText());
    		 }
    		 
        	 //       Add all of its child nodes to the back of the queue
    		Set<Character> charSet = firstNode.getValidNextCharacters();
    		Iterator<Character> charIterator = charSet.iterator();
    		while (charIterator.hasNext()) {
    			queue.add(firstNode.getChild(charIterator.next()));
    		}

    	 }

    	 // Return the list of completions
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}