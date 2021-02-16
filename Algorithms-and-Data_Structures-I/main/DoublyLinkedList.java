import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Prathamesh Sai Sankar
 *  @version 10/30/2020 05:36:00
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{
    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode
    {
        public final T data; // this field should never be updated. It gets its
                             // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;
    
        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */
        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
        {
          data = theData;
          prev = prevNode;
          next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() 
    {
      head = null;
      tail = null;
    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  There seems to only be a simple if else statement here.
     *  We don't call any other functions in this method.
     *  There seems to be no loops here.
     *  An if else statement runs at a constant running time so it's worst case asymptotic running time is Theta(1).
     *  
     */
    public boolean isEmpty()
    {
      if(head != null)  {
    	  return false;
      }
      else 
    	  return true;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     * There are if and else statements which all operate at running time, which is Theta(1).
     * However, there is one for loop, which causes a worst case asympototic running time of Theta(N)
     * This is because the for loop goes through all elements of the DLL in a worst case scenario.
     * Therefore this method has a worst-case asymptotic running time cost of Theta(N)
     *  
     */
    public void insertBefore( int pos, T data ) 
    {
    
    	if(head == null) {	//if it's an empty DDL, just make a new node
    		DLLNode nodeToBeAdded = new DLLNode(data, null, null);
    		head = nodeToBeAdded;
    		tail = nodeToBeAdded;
    		return;
    		
    	}
    	
    	else if (pos <= 0) {		// if pos is negative OR there's only one element in the doubly linked list,
    		DLLNode nodeToBeAdded = new DLLNode(data, null, head); // just add to the head of the list.
    		
			head.prev = nodeToBeAdded;
			nodeToBeAdded.next = head;
			head = nodeToBeAdded;	
			return;
    		
    	}
    	
    	else {
    	//find the position in the doubly linked list where we have to "insert before"
		DLLNode nodeFinder = head;	//start at the head
		for(int i = 0; i < pos; i++) {
			if(nodeFinder.next != null) { //added for for JUNIT
			nodeFinder = nodeFinder.next;	//keep going to the next node until you hit pos (i.e. where you have to "insert before").
			}
			
			else {
			
				DLLNode nodeToBeAdded = new DLLNode(data, tail, null); // reached end of list so pos is greater than the size, so add to the tail of the list.
            	tail.next = nodeToBeAdded;	
            	nodeToBeAdded.prev = tail;
            	tail = nodeToBeAdded;
				
            	return;
            	
			}
		}									// Now nodeFinder points to the node where we have to "insert before"
		
		//create the new node to be inserted, previous and next node are null.
    	DLLNode nodeToBeAdded = new DLLNode(data, null, null);
		
		DLLNode moveNode = nodeFinder.prev;	// make the variable moveNode point to whatever node is previous to nodeFinder
		
		moveNode.next = nodeToBeAdded;		// the "next" of the previous node to nodeFinder will point to the new nodeToBeAdded
		nodeToBeAdded.prev = moveNode;		// the "previous" of the new nodeToBeAdded will point to moveNode (which is before nodeFinder)
		nodeFinder.prev = nodeToBeAdded;	// the "previous" to nodeFinder will point to the new nodeToBeAdded
		nodeToBeAdded.next = nodeFinder;	// the "next" of the new nodeToBeAdded will point to nodeFinder. 
		
		
    	}
      }
    	
   
    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: Theta(N).
     *
     * Justification:
     *  All operations here run at constant time, for example the if else statements.
     *  There is one for-loop which is needed to get to the data to be returned.
     *  This for loop gives an worst case asymptotic running time of Theta(N). 
     *  Therefore this method has a worst-case asymptotic running time cost of Theta(N)
     *
     */
    public T get(int pos) 
    {
    	
    	if(pos < 0) { //if pos is negative, just return null
    		return null;
    	}
    	//if pos isn't negative, continue..
    	T data = null;
    	DLLNode getIt = head;
    	for(int i=0; i < pos; i++) {	//loop until you get to the node where pos is
    		if( getIt != null && getIt.next != null) {
    		getIt = getIt.next;
    		}
    		else {
    			return null; //if the next node is null, we know that pos was too big and we ended up past the end of the list, so return null.
    		}
    	}
    	if(getIt != null) {
    	data = getIt.data;		//get the data where pos is
    	}
    	return data;	
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified. 
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  The majority of this method uses if and else statements which are constant time operations.
     *  However, there is a for-loop which is used to find a reference to the node we must delete.
     *  This for-loop gives rise to a worst case asymptotic running time of Theta(N).
     *  Therefore this method also has a worst-case asymptotic running time cost of Theta(N)
     *  
     */
    public boolean deleteAt(int pos) 
    {
      boolean deleted = false;
      boolean oneNode = true;
      
      if(pos < 0) {
        return deleted;
      }
      
      DLLNode nodeFinder = head;
      
      if(nodeFinder != null) {	// if the DLL has one element, 
    	  if(nodeFinder.next == null) {		//and the position to be deleted is bigger than the size of the DLL, return false.
    		  if (pos >= 1) {
    			  return false;
    		  }
    	  }
      }
      
       if(nodeFinder != null) {
		if(nodeFinder.next != null){	
		for(int index = 0; index < pos; index++){	//loop through the DLL to find the position where we need to delete!
			if(nodeFinder.next == null) {
				return false;
			}
			nodeFinder = nodeFinder.next;
		}
		}
		
		if(tail == head) { //if tail and head point to the same thing, then we know that there's only one element in the DLL.
			head = null;
			tail = null;
			deleted = true;
		}
		
		else {
			if(nodeFinder.prev != null && nodeFinder.next != null) { //if the item to be removed is not the head or tail
				nodeFinder.prev.next = nodeFinder.next;
				nodeFinder.next.prev = nodeFinder.prev;
				deleted = true;
			}
			
			
			else if(nodeFinder == head) { //if the item to be removed is the head
				
				
				head = nodeFinder.next;
				if(head != null) {
				head.prev = null;
				deleted = true;
				}
			}
			else {
				
				tail = nodeFinder.prev;
				tail.next = null;
				deleted = true;
				
			}
			nodeFinder = null;
		}
      }
      
      return deleted;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  This method contains a while-loop.
     *  A while loop has an asymptotic running time of Theta(N).
     *  This is because it has to go through almost all cases and use constant time operations to swap nodes.
     *  Therefore this method has a worst-case asymptotic running time cost of Theta(N)
     *  
     */
    public void reverse()
    {
    	 DLLNode nodeFinder = head; 
    	 DLLNode temporary = null; 
        
         
         while (nodeFinder != null) {  // swap the previous and next for every node till the end
        	 temporary = nodeFinder.prev; 
        	 nodeFinder.prev = nodeFinder.next; 
        	 nodeFinder.next = temporary; 
             nodeFinder = nodeFinder.prev; 
         } 
   
         // once it's reversed, change the head making sure temporary isn't null (added from JUnit testing)
        
         if (temporary != null) { 
             head = temporary.prev; 
         } 
      
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: Theta(N^2)
     *
     * Justification:
     *  We have a nested for-loop to compare the data of two elements in a DLL.
     *  Since one for loop has a worst case running time of Theta(N), we can say that
     *  this nested for-loop gives rise to a worst case asymptotic running time of Theta(N^2).
     *  Therefore this method has a worst-case asymptotic running time cost of Theta(N^2).
     *   
     */
     public void makeUnique()
    {
         DLLNode nodeFinder;
         DLLNode node2;
         DLLNode temporary;
         
         //Checks whether list is empty  
         if(head == null || head.next == null) {  
             return;  
         }  
         else {  
             
             for(nodeFinder = head; nodeFinder != null; nodeFinder = nodeFinder.next) {  //nodeFinder points to head 
                 for(node2 = nodeFinder.next; node2 != null; node2 = node2.next) {  	//node2 points to the node next to nodeFinder
                     if(nodeFinder.data == node2.data) {  //if there's a duplicate,
                         temporary = node2;  //keep the duplicate node in temporary  
                         //let's remove the duplicate node by linking node2's previous to point to the node next to node2
                         node2.prev.next = node2.next;  
                         if(node2.next != null)  
                        	 node2.next.prev = node2.prev;  
                        //remove all reference to the duplicate node. 
                         temporary = null;  
                     }  
                 }  
             }  
         }  
     }  
    


    /*----------------------- STACK API 
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     * Inside of this method, we call insertBefore() which will cause a worst case asymptotic running time of Theta(N).
     * Therefore this method has a worst-case asymptotic running time cost of Theta(N).
     *  
     *  
     */
    public void push(T item) 
    {
    	insertBefore(0, item);
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  Inside this method, we called the function get() which has a worst case asympototic running time of Theta(N).
     *  Also, inside this method we called the function deleteAt() after which has a worst case asympototic running time of Theta(N).
     *  Each of these method calls is Theta(N), and so overall we a worst case asympototic running time of Theta(N).
     *  Therefore this method has a worst-case asymptotic running time cost of Theta(N).
     */
    public T pop() 
    {
    	T data = null;
    	data = get(0); //get whatever element is at the top of the stack (i.e. at the head)
    	 deleteAt(0); 
      return data;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */
 
    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification:
     *  All operations are constant time operations. 
     *  There are no loops.
     *  We use an if else statement to execute this method.
     *  Therefore this method has an asymptotic worst case running time of Theta(1).
     */
    public void enqueue(T item) 
    {
    	if( head == null) { // if the list is empty, just add the element to the DLL.
    		DLLNode nodeToBeAdded = new DLLNode(item, null, null);
    		head = nodeToBeAdded;
    		tail = nodeToBeAdded;
    		return;
    	}
    	
    	else { 		//otherwise, add to the end of the list to "enqueue" it.
            DLLNode addToEnd = new DLLNode(item, tail, null);
            tail.next = addToEnd;
            addToEnd.prev = tail;
            tail = addToEnd;
    	}
    }

     /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: Theta(N)
     *
     * Justification:
     *  We simply call the function pop() in this method.
     *  pop() has a worst case running time of Theta(N).
     *  Since we call pop(), we must include pop()'s asymptotic worst case running time in the asymptotic worst case running time of this method.
     *  Therefore this function also has an asymptotic worst case running time of Theta(N).
     */
    public T dequeue() 
    {
      return pop(); //we can simply pop whatever is at the head, following LILO (last in last out)
    }
 

    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() 
    {
      StringBuilder s = new StringBuilder();
      boolean isFirst = true; 

      // iterate over the list, starting from the head
      for (DLLNode iter = head; iter != null; iter = iter.next)
      {
        if (!isFirst)
        {
          s.append(",");
        } else {
          isFirst = false;
        }
        s.append(iter.data.toString());
      }

      return s.toString();
    }


}


