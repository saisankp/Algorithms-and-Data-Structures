import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  Prathamesh Sai Sankar
 *  @version 10/30/2020 05:36:00
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        
        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     
        testDLL =  new DoublyLinkedList<Integer>(); //when the head is null
        testDLL.insertBefore(-2, 1);				// and pos is negative
        assertEquals("1", testDLL.toString());
        testDLL.insertBefore(-2, 1);			//now the head points to 1 (not negative), and the pos is negative
        assertEquals("1,1", testDLL.toString());
    
    }

   

    
    /* Check if the testEmpty works
    */
   @Test
   	public void testIsEmpty()
    {
	   //test empty list
	   DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
	  
	   assertTrue(testDLL.isEmpty()); //should return true because testDLL is empty.
	   testDLL.insertBefore(0,1);
	   testDLL.insertBefore(1,2);
	   testDLL.insertBefore(2,3);
	   
	   //now testDLL should have "1,2,3", so it's NOT empty.
	   assertFalse(testDLL.isEmpty()); //should return false because testDLL is not empty. 
    }
   
   /* Check if the get() works
    */
   @Test
   	public void testGet() {
	   
	   // get from an empty list (should be null)
	   DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
	   assertEquals(null, testDLL.get(5)); // trying to get info from an empty 
	   
	   //get from a list that is non-empty 
	   testDLL.insertBefore(0,1);
	   testDLL.insertBefore(1,2);
	   testDLL.insertBefore(2,3);
	   
	   DoublyLinkedList<Integer> testDLL2 = new DoublyLinkedList<Integer>();
	   testDLL2.insertBefore(0,1);
	   
	   //now testDLL should have "1,2,3", so it's NOT empty.
	   assertEquals("1", testDLL.get(0).toString());
	   assertEquals("2", testDLL.get(1).toString());
	   assertEquals("3", testDLL.get(2).toString());
	   
	   //now try a negative number for pos, it should return null.
	   assertEquals(null, testDLL.get(-20));
	   
	 //now try a number for pos that is outside of the range of the DLL, it should return null.
	   assertEquals(null, testDLL.get(20));
   }
   
   
   /* Check if the deleteAt() works
    */
   @Test
   	public void testDeleteAt() {
	DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
    	
    	//Lets delete stuff from a DLL with 5 elements.
        testDLL.insertBefore(0,5);
        testDLL.insertBefore(1,4);
        testDLL.insertBefore(2,6);
        testDLL.insertBefore(0,9);
        testDLL.insertBefore(1,1);
        //the DLL should have "9,1,5,4,6", which is already tested using testInsertBefore
        
        testDLL.deleteAt(1);
        assertEquals( "Checking deleteAt to a list containing 5 elements at position 1", "9,5,4,6", testDLL.toString() );
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to a list containing 4 elements at position 0", "5,4,6", testDLL.toString() );
        testDLL.deleteAt(1);
        assertEquals( "Checking deleteAt to a list containing 3 elements at position 2", "5,6", testDLL.toString() );
        testDLL.deleteAt(1);
        assertEquals( "Checking deleteAt to a list containing 2 elements at position 1", "5", testDLL.toString() );
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to a list containing 1 element at position 0", "", testDLL.toString() );
        
        //Lets delete stuff from a DLL with one element
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to a list containing 1 element at position 0", "", testDLL.toString() );
        
        //Lets delete stuff from a DLL with two elements
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,9);
        testDLL.insertBefore(1,4);
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to a list containing 2 elements at position 0", "4", testDLL.toString() );
        testDLL.insertBefore(0,5);
        testDLL.deleteAt(1);
        assertEquals( "Checking deleteAt to a list containing 2 elements at position 1", "5", testDLL.toString() );
        
        testDLL.insertBefore(1, 4); // dll should be "5,4"
        assertFalse(testDLL.deleteAt(2));
        
        
        //Lets delete stuff from a DLL with no elements at all to see if it causes an errors.
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt to a list containing zero elements", "", testDLL.toString() );
        
        testDLL.insertBefore(1, 1);
        assertFalse(testDLL.deleteAt(-2));
   }
   
   
   
   /* Check if the reverse() works
    */
   @Test
   	public void testReverse() {
	   
	   DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
	 //Lets reverse stuff from a DLL with 5 elements.
       testDLL.insertBefore(0,5);
       testDLL.insertBefore(1,4);
       testDLL.insertBefore(2,6);
       testDLL.insertBefore(0,9);
       testDLL.insertBefore(1,1);
      
       //the DLL should have "9,1,5,4,6", which is already tested using testInsertBefore
       
       testDLL.reverse();
       assertEquals( "Checking reverse to a list with 5 elements", "6,4,5,1,9", testDLL.toString() );
       
       //Lets reverse stuff from a DLL with 1 element.
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,6);
       testDLL.reverse();
       assertEquals( "Checking reverse to a list with 1 element", "6", testDLL.toString() );
       
       //Lets reverse stuff from a DLL with 2 elements.
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,6);
       testDLL.insertBefore(1,4);
       testDLL.reverse();
       assertEquals( "Checking reverse to a list with 2 elements", "4,6", testDLL.toString() );
       
       //Lets reverse stuff from a DLL with 0 elements.
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.reverse();
       assertEquals( "Checking reverse to a list with zero elements", "", testDLL.toString() );
	   
   }
   
  
   /* Check if the makeUnique() works
    */
   @Test
   	public void testUnique() {
	   
	   DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
 
		//Lets make a DLL with 5 elements and 2 repeating numbers unique
   	   testDLL.insertBefore(0,9);
       testDLL.insertBefore(1,10);
       testDLL.insertBefore(2,10);
       testDLL.insertBefore(5,11);
       testDLL.insertBefore(5,11);
       testDLL.makeUnique();
       assertEquals( "Checking makeUnique to a list of 5 elements with 2 repeating elements", "9,10,11", testDLL.toString() );
	   
     //Lets make a DLL with 5 elements and 5 repeating numbers unique
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,7);
       testDLL.insertBefore(1,7);
       testDLL.insertBefore(2,7);
       testDLL.insertBefore(4,7);
       testDLL.insertBefore(5,7);
       testDLL.makeUnique();
       assertEquals( "Checking makeUnique to a list of 5 elements with 5 repeating elements", "7", testDLL.toString() );
       
       
     //Lets make a DLL with 8 elements and 0 repeating numbers unique, it should return the same DLL.
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,1);
       testDLL.insertBefore(1,2);
       testDLL.insertBefore(2,3);
       testDLL.insertBefore(69,6);
       testDLL.insertBefore(99,7);
       testDLL.insertBefore(101,8);
       testDLL.insertBefore(199,9);
       testDLL.insertBefore(420,10);
       testDLL.makeUnique();
       assertEquals( "Checking makeUnique to a list of 6 elements with 0 repeating elements", "1,2,3,6,7,8,9,10", testDLL.toString() );
       
     //Lets make a DLL with 1 element unique, it should return the same data back.
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,69);
       testDLL.makeUnique();
       assertEquals( "Checking makeUnique to a list of 1 element", "69", testDLL.toString() );
       
     //Lets make a DLL with 2 elements unique
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,69);
       testDLL.insertBefore(1,420);
       testDLL.makeUnique();
       assertEquals( "Checking makeUnique to a list of 2 unique elements", "69,420", testDLL.toString() );
       
     //Lets make a DLL with 2 elements that are the same unique
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.insertBefore(0,23);
       testDLL.insertBefore(1,23);
       testDLL.makeUnique();
       assertEquals( "Checking makeUnique to a list of 2 same elements", "23", testDLL.toString() );
       assertFalse(testDLL.deleteAt(1));
       
       
       
     //Lets make a DLL with 0 elements unique, it should just return null as a string, i.e. "".
       testDLL = new DoublyLinkedList<Integer>();
       testDLL.makeUnique();
       assertEquals( "Checking to a list of 0 elements", "", testDLL.toString() );
       
       //Let's test one last time
       testDLL.insertBefore(0, 2);
       testDLL.insertBefore(1, 1);
       testDLL.insertBefore(2, 2);
       testDLL.insertBefore(3, 1);
       testDLL.insertBefore(4, 1);
       testDLL.insertBefore(5, 2);
       testDLL.insertBefore(6, 1);
       //test DLL should have "2,1,2,1,1,2,1"
	   
       testDLL.makeUnique();
       assertEquals("2,1", testDLL.toString());
       testDLL.deleteAt(1);
      assertEquals("2",  testDLL.toString());
      
      testDLL = new DoublyLinkedList<Integer>();
      testDLL.insertBefore(0, 1);
      testDLL.insertBefore(1, 1);
      //now testDLL = "1,1"
      testDLL.makeUnique();
      assertEquals("1", testDLL.toString());
      assertFalse(testDLL.deleteAt(1)); // can't remove position 1 if there's only 1 element in position 0.
      testDLL.deleteAt(0);		//now let's actually delete the one node in the DLL.
      assertEquals("", testDLL.toString());      
   }
   
   /* Check if the push() works
    */
   @Test
   	public void testPush() {
	   DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
   	
   	//let's push stuff 6 times and see if they actually push onto the stack.
   	testDLL.push(1);
   	testDLL.push(2);
   	testDLL.push(3);
   	testDLL.push(4);
   	testDLL.push(5);
   	testDLL.push(6);

   	assertEquals( "Checking push with 6 iterations of push", "6,5,4,3,2,1", testDLL.toString() );
   }
   
   
   /* Check if the pop() works
    */
   @Test
   	public void testPop() {
	   DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
   	
	 //let's push stuff 6 times and see if they actually push onto the stack like for testPush().
   	testDLL.push(1);
   	testDLL.push(2);
   	testDLL.push(3);
   	testDLL.push(4);
   	testDLL.push(5);
   	testDLL.push(6);
   	//we know from testPush that the stack should have "6,5,4,3,2,1" after the operations above.
   	
   	testDLL.pop();
   	assertEquals( "Checking pop with a previous 6 iterations of push", "5,4,3,2,1", testDLL.toString() );
   	testDLL.pop();
   	assertEquals( "Checking pop with a previous 6 iterations of push", "4,3,2,1", testDLL.toString() );
   	testDLL.pop();
   	assertEquals( "Checking pop with a previous 6 iterations of push", "3,2,1", testDLL.toString() );
   	testDLL.pop();
   	assertEquals( "Checking pop with a previous 6 iterations of push", "2,1", testDLL.toString() );
   	testDLL.pop();
   	assertEquals( "Checking pop with a previous 6 iterations of push", "1", testDLL.toString() );
   	testDLL.pop();
   	assertEquals( "Checking pop with a previous 6 iterations of pushboy", "", testDLL.toString() );
   	
   	//let's try popping when the stack is empty. It should return "".
   	testDLL.pop();
   	assertEquals( "Checking pop with a previous 6 iterations of pushya", "", testDLL.toString() );
   }
   
   /* Check if the enqueue() works
    */
   @Test
   	public void testEnqueue() {
	   DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
   	
   	//let's enqueue six elements and see if they are ordered in LILO (last in last out)
   	testDLL.enqueue(1);
   	testDLL.enqueue(2);
   	testDLL.enqueue(3);
   	testDLL.enqueue(4);
   	testDLL.enqueue(5);
   	testDLL.enqueue(6);
   	
   	assertEquals( "Checking enqueue with six elements", "1,2,3,4,5,6", testDLL.toString() );
   }
   
   /* Check if the dequeue() works
    */
   @Test
   	public void testDequeue() {
	   DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
 
   	testDLL.enqueue(1);
   	testDLL.enqueue(2);
   	testDLL.enqueue(3); 	
   	testDLL.enqueue(4);
   	testDLL.enqueue(5);
   	testDLL.enqueue(6);
   	//according to the method testEnqueue we already know the queue should be "1,2,3,4,5,6".
   	
   	
   	testDLL.dequeue();
   	assertEquals( "Checking dequeue with 6 elements", "2,3,4,5,6", testDLL.toString() );
   	testDLL.dequeue();
   	assertEquals( "Checking dequeue with 5 elements", "3,4,5,6", testDLL.toString() );
   	testDLL.dequeue();
   	assertEquals( "Checking dequeue with 4 elements", "4,5,6", testDLL.toString() );
   	testDLL.dequeue();
   	assertEquals( "Checking dequeue with 3 elements", "5,6", testDLL.toString() );
   	testDLL.dequeue();
   	assertEquals( "Checking dequeue with 2 element", "6", testDLL.toString() );
   	testDLL.dequeue();
   	assertEquals( "Checking dequeue with 1 elements", "", testDLL.toString() );
   	
   	//let's try and dequeue from an empty queue, it should just be "".
   	testDLL.dequeue();
   	assertEquals( "Checking dequeue with 0 elements", "", testDLL.toString() );
   	
   }
   
}
