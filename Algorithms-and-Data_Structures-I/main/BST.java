/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author Prathamesh Sai
 *
 *************************************************************************/

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST

    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    /**
     * Tree height.
     *
     * Asymptotic worst-case running time using Theta notation: Theta(N)
     * REASONING: The worst-case running time is Theta(N) because we go through each branch in the tree until we get to the leaf of the tree.
     * Afterwards, we return the branch that is the most deep, hence getting the maximum number of links from the root to the leaf.
     * This means that we will have a worst-case running time of Theta(N) since we go through each branch until the leaf.
     *
     * @return the number of links from the root to the deepest leaf.
     *
     * Example 1: for an empty tree this should return -1.
     * Example 2: for a tree with only one node it should return 0.
     * Example 3: for the following tree it should return 2.
     *   B
     *  / \
     * A   C
     *      \
     *       D
     */
    public int height() {
    	
      //if the tree is empty, return -1.
      if(isEmpty()) {
    	  return -1;
      }
      
      //if the tree only has one node
      else if(root.left == null && root.right == null) {
    	  return 0;
      }
      
      //otherwise, get the height using recursion but use the height as 0 to start it off.
      else {
    	  int heightOfTree = height(root, 0);
    	  return heightOfTree;
      }
    }

    //private recursive method for finding the height of a binary search tree.
    private int height(Node node, int height) {
    	//store the height value passed into the function into two variables.
    	int heightOfRightSide = height;
    	int heightOfLeftSide = height;
    	
    	
    	//if the node on the right is not null, then call the height method again for the right node but pass in height+1 to increment the height.
    	if(node.right != null) {
    		heightOfRightSide = height(node.right, height+1);
    	}
    	
    	//if the node on the left is not null, then call the height method again for the left node but pass in height+1 to increment the height.
    	if(node.left != null) {
    		heightOfLeftSide = height(node.left, height+1);
    	}
    	
    	
    	//once recursion is finished, check which is the deepest.
    	if(heightOfLeftSide > heightOfRightSide) {
    		return heightOfLeftSide;
    	}
    	else {
    		return heightOfRightSide;
    	}
    }
    
    
    /**
     * Median key.
     * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
     * is the element at position (N+1)/2 (where "/" here is integer division)
     *
     * @return the median key, or null if the tree is empty.
     */
     //The running time should be Theta(h), where h is the height of the tree (given in question)
    public Key median() {
    	
      //if the tree is empty, then the method should return null.
      if (isEmpty()) {
    	  return null;
      }
      else {
      //use root.N+1/2 and decrement by 1 before passing into the recursive function select.
      int positionOfMedian = ((root.N+1)/2) -1;
      Key answer = select(positionOfMedian);
      return answer;
      }
    }

    //private recursive method for finding the median.
     private Key select(int integer) {
    	//use another recursive method to find the median.
    	Node x = select(root, integer);
    	return x.key;
    	}
     
     //second private recursive method for finding the median.
     private Node select(Node x, int integer) {
    	int y = size(x.left);
 

    	//if the size of x.left is less than the int passed in, then call the function itself with x.right, and integer-size(x.left)-1.
    	if (y < integer) {
    	return select(x.right, integer-y-1);
    	}
    	
    	//if the size of x.left is bigger than the int passed in, then call the function itself with x.left.
    	else if (y > integer) {
    		return select(x.left, integer);
    	}
    	
    	//once the recursion is done, return the answer.
    	else {
    		return x;
    	}
    	}


    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesized.
     */
    public String printKeysInOrder() {
    	if (isEmpty()) {
    		return "()";
    	}
    	else {
    	  String result = "(" + printKeysInOrder(root) + ")";
    	  return result;
    	}
       
    }
    
    private String printKeysInOrder(Node x) {
    	//if the node being passed in is null, return an empty string.
    	if (x == null) {
    		return "";
    	}
    	
    	else {
         String Right = printKeysInOrder(x.right); //use recursion to get print the keys on the right.
    	 String Left = printKeysInOrder(x.left); //use recursion to get print the keys on the left.
         String answer = "(" + Left + ")" + x.key + "(" + Right + ")"; //return the string in the correct format
         return answer; 
    	}
        
    }
    
    /**
     * Pretty Printing the tree. Each node is on one line -- see assignment for details.
     *
     * @return a multi-line string with the pretty ascii picture of the tree.
     */
    public String prettyPrintKeys() {
    	//start off with an empty string to use.
    	String concatenatedString = "";
    	
    	//get the output by calling our private recursive method. 
    	String output = prettyPrint(root, concatenatedString);
    	return output;
    }
    
  //private recursive method for outputting a multi-line string representation of a tree.
    private String prettyPrint(Node input, String prefix) {
    	
    	//if the node is null, then add the appropriate null string to the existing string.
    	if(input == null) {
    		return prefix + "-null\n";
    	}
    	
    	//if the node is not null, then,
    	else {
    	   String output = prefix + "-" + input.val +"\n"; //concatenate the strings like the desired output.
           output = output + prettyPrint(input.left, prefix + " |"); //use recursion with the left subtree
           output = output + prettyPrint(input.right, prefix + "  "); //use recursion with the right subtree
           return output;
    	}
    }
    
    /**
     * Deletes a key from a tree (if the key is in the tree).
     * Note that this method works symmetrically from the Hibbard deletion:
     * If the node to be deleted has two child nodes, then it needs to be
     * replaced with its predecessor (not its successor) node.
     *
     * @param key the key to delete
     */
    public void delete(Key key) {
    	root = delete(root, key); //call the recursive delete method.
    }

    private Node delete(Node x, Key key) {
    	//if the node is empty, return null
    	if (x == null) {
    		return null;
    	}
    	
    	//use compareTo to see whether to delete x.left or x.right
    	int compare = key.compareTo(x.key);
    	
    	//if the result is greater than 0, then use recursion with the right node.
    	if (compare > 0) {
    		x.right = delete(x.right, key);
    	}
    	
    	//if the result is less than 0, then use recursion with the left node.
    	else if (compare < 0) {
    		x.left = delete(x.left, key);
    	}
    	
    	else {
    		//if the left node is null, return the right node.
    		if(x.left == null) {
    			return x.right;
    		}
    		//if the right node is null, return the left node.
    		if(x.right == null) {
    			return x.left;
    		}
    		
    		//use a node variable to store x, then use deleteMax.
    		Node node = x;
    		x = max(node.left);
    		x.left = deleteMax(node.left);
    		x.right = node.right;
    	}
    	
    	 x.N = size(x.left)+ size(x.right) + 1;  
    	 //after the recursion is finished, return the node.
    	 return x;
    }
    
  //private recursive method for deleting max
    private Node deleteMax(Node node) {
    	//if the right node is null, return the left node.
    	if(node.right == null) {
    		return node.left;
    	}
    	
    	node.right = deleteMax(node.right); //use recursion to call deleteMax again.
    	node.N = size(node.right) + size(node.left) + 1; //set the number of nodes in the subtree
    	return node;
    }
    
  //private method for the max
    private Node max (Node node) {
    	
    	//if the right node is null, return the node passed into the method
    	if(node.right == null) {
    		return node;
    	}
    	
    	//get the max of the right node and store it in a variable.
    	Node nodeToReturn = max(node.right);
    	
    	//return the variable.
    	return nodeToReturn;
    }
}