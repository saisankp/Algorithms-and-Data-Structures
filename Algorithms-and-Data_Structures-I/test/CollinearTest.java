import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  
 *  @version 18/09/18 12:21:26
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
	
	
	
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the two methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;

        assertEquals("countCollinear failed with 3 empty arrays",       expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast failed with 3 empty arrays", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleFalse()
    {
        int[] a3 = { 15 };
        int[] a2 = { 5 };
        int[] a1 = { 10 };

        int expectedResult = 0;

        assertEquals("countCollinear({10}, {5}, {15})",       expectedResult, Collinear.countCollinear(a1, a2, a3) );
        assertEquals("countCollinearFast({10}, {5}, {15})", expectedResult, Collinear.countCollinearFast(a1, a2, a3) );
    }

    // ----------------------------------------------------------
    /**
     * Check for no false positives in a single-element array
     */
    @Test
    public void testSingleTrue()
    {
        int[] a3 = { 15, 5 };       int[] a2 = { 5 };       int[] a1 = { 10, 15, 5 };

        int expectedResult = 1;

        assertEquals("countCollinear(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")",     expectedResult, Collinear.countCollinear(a1, a2, a3));
        assertEquals("countCollinearFast(" + Arrays.toString(a1) + "," + Arrays.toString(a2) + "," + Arrays.toString(a3) + ")", expectedResult, Collinear.countCollinearFast(a1, a2, a3));
    }


    // TODO: add more tests here. Each line of code and ech decision in Collinear.java should
    // be executed at least once from at least one test.

    @Test
    public void testCountCollinear() {
    	int[] a1 = {1,2 };
		int[] a2 = {2,};
		int[] a3 = { 3,4 };
		
		assertEquals(1,Collinear.countCollinear(a1,a2,a3)); //there is meant to be 1 collinear point.
		int [] A2 = {2,3}; //test using another version of a2.
		assertEquals(2,Collinear.countCollinear(a1,A2,a3)); //there is meant to be 2 collinear points.
		int [] A1 = {};	//test using another version of a1 (null).
		assertEquals(0,Collinear.countCollinear(A1,a2,a3)); //there is meant to be 0 collinear points.
    }
    
    @Test
    public void testCountCollinearFast() {
    	int[] a1 = {1,2 };
		int[] a2 = {2, 3};
		int[] a3 = { 3,4 };
		
		assertEquals(2,Collinear.countCollinear(a1,a2,a3)); //there is meant to be 2 collinear points.
		int [] A2 = {2}; //test using another version of a2.
		assertEquals(1,Collinear.countCollinear(a1,A2,a3)); //there is meant to be 1 collinear point.
		int [] A1 = {};	//test using another version of (null).
		assertEquals(0,Collinear.countCollinear(A1,a2,a3)); //there is meant to be 0 collinear points.
    }
    
    @Test
    public void binarySearch() {
    	int[] a1 = {6, 13, 14, 25, 33, 44, 51, 53, 64, 72, 84, 93, 95, 96, 97};
    	assertTrue(Collinear.binarySearch(a1,33));
    	int[] a2 = {1}; //try with one element array, does it give errors?
    	assertFalse(Collinear.binarySearch(a2,0));
    }
    @Test
    public void sort() {
    	int[] a1 = {100,68,420,500,2,4,1};
    	int [] sortedArr1 = {1,2,4,68,100,420,500};
    	Collinear.sort(a1);
    	for(int i = 0; i < a1.length; i++) {
    		assertTrue(a1[i] == sortedArr1[i]);
    	}
    }
}
