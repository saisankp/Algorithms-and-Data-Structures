import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  Prathamesh Sai
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  
  //TODO write more tests here.

  
  /** <p>Test {@link BST#prettyPrintKeys()}.</p> */
      
 @Test
 public void testPrettyPrint() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     assertEquals("Checking pretty printing of empty tree",
             "-null\n", bst.prettyPrintKeys());
      
                          //  -7
                          //   |-3
                          //   | |-1
                          //   | | |-null
     bst.put(7, 7);       //   | |  -2
     bst.put(8, 8);       //   | |   |-null
     bst.put(3, 3);       //   | |    -null
     bst.put(1, 1);       //   |  -6
     bst.put(2, 2);       //   |   |-4
     bst.put(6, 6);       //   |   | |-null
     bst.put(4, 4);       //   |   |  -5
     bst.put(5, 5);       //   |   |   |-null
                          //   |   |    -null
                          //   |    -null
                          //    -8
                          //     |-null
                          //      -null
     
     String result = 
      "-7\n" +
      " |-3\n" + 
      " | |-1\n" +
      " | | |-null\n" + 
      " | |  -2\n" +
      " | |   |-null\n" +
      " | |    -null\n" +
      " |  -6\n" +
      " |   |-4\n" +
      " |   | |-null\n" +
      " |   |  -5\n" +
      " |   |   |-null\n" +
      " |   |    -null\n" +
      " |    -null\n" +
      "  -8\n" +
      "   |-null\n" +
      "    -null\n";
     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
     }

 /** <p>Test {@link BST#delete(Comparable)}.</p> */
 @Test
 public void testDelete() {
     BST<Integer, Integer> bst = new BST<Integer, Integer>();
     bst.delete(1);
     assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
     
     bst.put(7, 7);   //        _7_
     bst.put(8, 8);   //      /     \
     bst.put(3, 3);   //    _3_      8
     bst.put(1, 1);   //  /     \
     bst.put(2, 2);   // 1       6
     bst.put(6, 6);   //  \     /
     bst.put(4, 4);   //   2   4
     bst.put(5, 5);   //        \
                      //         5
     
     assertEquals("Checking order of constructed tree",
             "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
     
     bst.delete(9);
     assertEquals("Deleting non-existent key",
             "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

     bst.delete(8);
     assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

     bst.delete(6);
     assertEquals("Deleting node with single child",
             "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

     bst.delete(3);
     assertEquals("Deleting node with two children",
             "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
 }
 
 
 
 
  @Test
 public void testHeight() {
	  
	//test height of an empty tree.
   BST<Integer, Integer> bst = new BST<Integer, Integer>();
   assertEquals("Height of an Empty tree", -1, bst.height());
   
   //test height of a tree with one node.
   bst.put(7, 7); 
   assertEquals("Height of tree with one node", 0, bst.height());
   
   
   // now let's use a sample binary tree of height 3 and see if the height method works.
   bst.put(3, 3);  
   bst.put(1, 1);
   bst.put(5, 5);
   bst.put(0, 0);
   bst.put(2, 2);
   bst.put(4, 4);
   bst.put(6, 6);
   bst.put(11, 11);
   bst.put(9, 9);
   bst.put(13, 13);
   bst.put(8, 8);
   bst.put(10, 10);
   bst.put(12, 12);
   bst.put(14, 14);
   assertEquals("Height of a tree with height 3", 3, bst.height());
   
   //tree with one left node
   bst = new BST<Integer, Integer>();
   bst.put(15, 15);
   bst.put(14, 14);
   assertEquals("Height of tree with one left node", 1, bst.height());
  
   //tree with one right node
   bst = new BST<Integer, Integer>();
   bst.put(12, 12);
   bst.put(13, 13);
   assertEquals("Height of tree with one right node", 1, bst.height());
   
   //tree with one right node and one left node
   bst.put(11, 11);
   assertEquals("Height of tree with one right and one left node", 1, bst.height());
   
   
   //tree that leans to the right
   bst = new BST<Integer, Integer>();
   bst.put(3, 3);
   bst.put(2, 2);
   bst.put(42, 42);
   bst.put(1, 1);
   bst.put(4, 4);
   bst.put(40, 40);
   bst.put(41, 41);
   assertEquals("Height of tree leaning to the right", 4,  bst.height());
   
   
   //tree that leans to the left
   bst = new BST<Integer, Integer>();
   bst.put(40, 40);
   bst.put(3, 3);
   bst.put(42, 42);
   bst.put(2, 2);
   bst.put(4, 4);
   bst.put(41, 41);
   bst.put(1, 1);
   assertEquals("Height of tree leaning to the left", 3,  bst.height());
  }
  
  @Test
  public void testMedian() {
	  
   //test the median of an empty tree.
   BST<Integer, Integer> bst = new BST<Integer, Integer>();
   assertEquals("Median of an Empty tree", null, bst.median());
   bst.put(8, 8);
   bst.put(9, 9);
   bst.put(4, 4);
   bst.put(2, 2);
   bst.put(3, 3);
   bst.put(7, 7);
   bst.put(5, 5);
   bst.put(6, 6);
   assertEquals("Median of a non-empty tree", "5" , bst.median().toString());
   
   //median of a tree with one right node
   bst = new BST<Integer, Integer>();
   bst.put(9, 9);
   bst.put(10, 10);
   assertEquals("Median of tree with one right node","9", bst.median().toString());
   
   //median of a tree with one left node
   bst = new BST<Integer, Integer>();
   bst.put(13, 13);
   bst.put(12, 12);
   assertEquals("Median of tree with one left node", "12", bst.median().toString());
   
   //median of a tree with a right and left node.
   bst.put(15, 15);
   assertEquals("Median of tree with one left and one right node", "13",  bst.median().toString());
   
   //median of right leaning tree
   bst = new BST<Integer, Integer>();
   bst.put(1, 1);
   bst.put(2, 2);
   bst.put(3, 3);
   bst.put(4, 4);
   bst.put(5, 5);
   assertEquals("right leaning tree", "3", bst.median().toString());
   
   //median of left leaning tree
   bst = new BST<Integer, Integer>();
   bst.put(10, 10);
   bst.put(9, 9);
   bst.put(8, 8);
   bst.put(7, 7);
   bst.put(6, 6);
   bst.put(5, 5);
   assertEquals("left leaning tree", "7", bst.median().toString());
  }
  
  @Test
  public void testContains() {
   BST<Integer, Integer> bst = new BST<Integer, Integer>();
   assertEquals("Is the key 1 in an empty tree", false,  bst.contains(1));
   
   bst.put(3, 3);  
   bst.put(1, 1);
   bst.put(5, 5);
   bst.put(0, 0);
   bst.put(2, 2);
   bst.put(4, 4);
   bst.put(6, 6);
   bst.put(11, 11);
   bst.put(9, 9);
   bst.put(13, 13);
   bst.put(8, 8);
   bst.put(10, 10);
   bst.put(12, 12);
   bst.put(14, 14);
   
   assertEquals("Is 17 a key in this BST (nope!)", false,  bst.contains(17));
   assertEquals("Is 11 a key in this BST? (yes!)", true,  bst.contains(11));
   
   
   //tree with one left node
   bst = new BST<Integer, Integer>();
   bst.put(15, 15);
   bst.put(14, 14);
   assertEquals("is 15 in this BST? (yes)", true,  bst.contains(15));
   assertEquals("is 14 in this BST? (yes)", true,  bst.contains(14));
  
   //tree with one right node
   bst = new BST<Integer, Integer>();
   bst.put(12, 12);
   bst.put(13, 13);
   assertEquals("is 12 in this BST? (yes)", true,  bst.contains(12));
   assertEquals("is 12 in this BST? (yes)", true,  bst.contains(13));
   
   //tree with one right node and one left node
   bst.put(11, 11);
   assertEquals("is 11 in this BST? (yes)", true,  bst.contains(13));
   
   
   //tree that leans to the right
   bst = new BST<Integer, Integer>();
   bst.put(3, 3);
   bst.put(2, 2);
   bst.put(42, 42);
   bst.put(1, 1);
   bst.put(4, 4);
   bst.put(40, 40);
   bst.put(41, 41);
   assertEquals("is 40 in this BST (yes)", true,  bst.contains(40));
   assertEquals("is 9 in this BST (no)", false,  bst.contains(9));
   
   
   //tree that leans to the left
   bst = new BST<Integer, Integer>();
   bst.put(40, 40);
   bst.put(3, 3);
   bst.put(42, 42);
   bst.put(2, 2);
   bst.put(4, 4);
   bst.put(41, 41);
   bst.put(1, 1);
   assertEquals("is 2 in this BST? (yes)", true,  bst.contains(2));
   assertEquals("is 19 in this BST? (no)", false,  bst.contains(19));
  }
  
  @Test
  public void testGet() {
 	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
 	 assertEquals("testing on an empty tree", null,  bst.get(3));
 	   bst.put(3, 3);  
 	   bst.put(1, 1);
 	   bst.put(5, 5);
 	   bst.put(0, 0);
 	   bst.put(2, 2);
 	   bst.put(4, 4);
 	   bst.put(6, 6);
 	   bst.put(11, 11);
 	   bst.put(9, 9);
 	   bst.put(13, 13);
 	   bst.put(8, 8);
 	   bst.put(10, 10);
 	   bst.put(12, 12);
 	   bst.put(14, 14);
       assertEquals("get the value associated with 3", "3",  bst.get(3).toString());
       assertEquals("get the value associated with 1", "1",  bst.get(1).toString());
       assertEquals("get the value associated with 5", "5",  bst.get(5).toString());
       assertEquals("get the value associated with 0", "0",  bst.get(0).toString());
       assertEquals("get the value associated with 2", "2",  bst.get(2).toString());
       assertEquals("get the value associated with 4", "4",  bst.get(4).toString());
       assertEquals("get the value associated with 6", "6",  bst.get(6).toString());
       assertEquals("get the value associated with 11", "11",  bst.get(11).toString());
       assertEquals("get the value associated with 9", "9",  bst.get(9).toString());
       assertEquals("get the value associated with 13", "13",  bst.get(13).toString());
       assertEquals("get the value associated with 8", "8",  bst.get(8).toString());
       assertEquals("get the value associated with 10", "10",  bst.get(10).toString());
       assertEquals("get the value associated with 12", "12",  bst.get(12).toString());
       assertEquals("get the value associated with 14", "14",  bst.get(14).toString());
  }
  
  @Test
  public void testPrintKeysInOrder() {
	  BST<Integer, Integer> bst = new BST<Integer, Integer>();
	  bst.put(7, 7);
	  assertEquals("print keys in order for BST", "(()7())",  bst.printKeysInOrder());
  }
  
  
  @Test
  public void testPut() {
 	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
 	 //putting a value that is already in the tree
 	 bst.put(7, 7);    
 	 bst.put(7, 7); 
 	 assertEquals("using put twice for the same value", "(()7())",  bst.printKeysInOrder());
 	 
 	 bst = new BST<Integer, Integer>();
 	 bst.put(7, null); 
 	 assertEquals("using put but the value is NULL", "()",  bst.printKeysInOrder());
  }
}
