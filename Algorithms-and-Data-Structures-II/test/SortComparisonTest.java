import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/*
 * PART 2 OF ASSIGNMENT: Testing
 * MY RESULTS:
#------------------------#--------#-----------#----------------#-----------------#---------------#
|       File Name        | Insert | Selection |    QuickSort   | Recursive-Merge |Iterative-Merge|
|------------------------#--------#-----------#----------------#-----------------#---------------|
|      numbers1000       | 5ms    | 3ms       | 2ms            | 0ms             | 1ms           |
|------------------------#--------#-----------#----------------#-----------------#---------------|
|  numbers1000Duplicates | 8ms    | 4ms       | 2ms            | 2ms             | 1ms           |
|------------------------#--------#-----------#----------------#-----------------#---------------|
|     numbers10000       | 103m   | 36ms      | 4ms            | 4ms             | 5ms           |
|------------------------#--------#-----------#----------------#-----------------#---------------|
|numbersNearlyOrdered1000| 8ms    | 6ms       | 2ms            | 1ms             | 0ms           |
|------------------------#--------#-----------#----------------#-----------------#---------------|
|   numbersReverse1000   | 10ms   | 8ms       | 2ms            | 1ms             | 1ms           |
|------------------------#--------#-----------#----------------#-----------------#---------------|
|    numbersSorted1000   | 7ms    | 5ms       | 3ms            | 1ms             | 1ms           |
#------------------------#--------#-----------#----------------#-----------------#---------------#
 */

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Prathamesh Sai
 *  @version HT 2020
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty() {
    	//for all sorting methods, an empty input will result in an empty output!
    	double[] input = {};
    	double[] output = input.clone();
    	
    	//test every sort method
        assertTrue(testInsertionSort(input, output));
        assertTrue(testSelectionSort(input, output));
        assertTrue(testQuickSort(input, output));
        assertTrue(testMergeSortRecursive(input, output));
        assertTrue(testMergeSort(input, output));
    }
    
    @Test
    public void testNull() {
    	//for all sorting methods, an input that is null will result in an output that is null!
    	double[] input = null;
    	double[] output = null;
    	
    	//test every sort method
    	SortComparison.insertionSort(input);
    	assertTrue(Arrays.equals(input, output));
    	SortComparison.selectionSort(input);
    	assertTrue(Arrays.equals(input, output));
    	SortComparison.quickSort(input);
    	assertTrue(Arrays.equals(input, output));
    	SortComparison.mergeSortRecursive(input);
    	assertTrue(Arrays.equals(input, output));
    	SortComparison.mergeSort(input);
    	assertTrue(Arrays.equals(input, output));
    }

    
    @Test
    public void testOneElement() {
      //for all sorting methods, an input of length 1 will result in the input value being the output! (No sorting required!)
      double[] input = {0};
      double[] output = input.clone();
      
      //test every sort method
      assertTrue(testInsertionSort(input, output));
      assertTrue(testSelectionSort(input, output));
      assertTrue(testQuickSort(input, output));
      assertTrue(testMergeSortRecursive(input, output));
      assertTrue(testMergeSort(input, output));
    }
    
    
    @Test
    public void testWithProperElements() {
     //use actual values for the input, and compare with the result from Arrays.sort
      double[] input = {420.69, 999.999, 69.69, 707.90, 5003.20, 4920.50, 40.4, 205.5, 1002.0, 6969.68, 3928.30};
      double[] output = input.clone();
      Arrays.sort(output);
      
      //test every sort method
      assertTrue(testInsertionSort(input, output));
      assertTrue(testSelectionSort(input, output));
      assertTrue(testQuickSort(input, output));
      assertTrue(testMergeSortRecursive(input, output));
      assertTrue(testMergeSort(input, output));
    }
    
    //Methods to help with testing:
    
    //method to use when checking if the input/output from insertion sort is valid.
	private boolean testInsertionSort(double[] input, double[] expectedOutput) {
		double[] theInputToBeTested = input.clone();
		SortComparison.insertionSort(theInputToBeTested);
		return Arrays.equals(theInputToBeTested, expectedOutput);
	}
    
	//method to use when checking if the input/output from selection sort is valid.
	private boolean testSelectionSort(double[] input, double[] expectedOutput) {
		double[] theInputToBeTested = input.clone();
		SortComparison.selectionSort(theInputToBeTested);
		return Arrays.equals(theInputToBeTested, expectedOutput);
	}
	
	//method to use when checking if the input/output from quick sort is valid.
	private boolean testQuickSort(double[] input, double[] expectedOutput) {
		double[] theInputToBeTested = input.clone();
		SortComparison.quickSort(theInputToBeTested);
		return Arrays.equals(theInputToBeTested, expectedOutput);
	}
	
	//method to use when checking if the input/output from recursive merge sort is valid.
	private boolean testMergeSortRecursive(double[] input, double[] expectedOutput) {
		double[] theInputToBeTested = input.clone();
		SortComparison.mergeSortRecursive(theInputToBeTested);
		return Arrays.equals(theInputToBeTested, expectedOutput);
	}
	
	//method to use when checking if the input/output from normal iterative merge sort is valid.
	private boolean testMergeSort(double[] input, double[] expectedOutput) {
		double[] theInputToBeTested = input.clone();
		SortComparison.mergeSort(theInputToBeTested);
		return Arrays.equals(theInputToBeTested, expectedOutput);
	}

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     * @throws IOException 
     * 
     * PART 3 OF ASSIGNMENT: Algorithm performance comparison
     */
    public static void main(String[] args) throws IOException {
		//Simply swap the string passed in, for the name of the text file!
		double[] numbersFromFile = readDoublesFromFile("numbersSorted1000.txt");

		double[] testingArray;
		long startingTime, finishingTime, timeTaken, averageTime = 0;
		
		//get the average of 3 trials for each type of sorting
		long repetitions = 3;

		for (int i = 0; i < repetitions; i++) {
			testingArray = numbersFromFile.clone();
			startingTime = System.currentTimeMillis();
			SortComparison.insertionSort(testingArray);
			finishingTime = System.currentTimeMillis();
			timeTaken = finishingTime - startingTime;
			averageTime = averageTime + timeTaken;
		}

		averageTime = averageTime / repetitions;

		System.out.println("Time taken for insertion sort is " + averageTime + "ms");

		startingTime = 0;
		finishingTime = 0;
		timeTaken = 0;
		averageTime = 0;

		for (int i = 0; i < repetitions; i++) {
			testingArray = numbersFromFile.clone();
			startingTime = System.currentTimeMillis();
			SortComparison.selectionSort(testingArray);
			finishingTime = System.currentTimeMillis();
			timeTaken = finishingTime - startingTime;
			averageTime = averageTime + timeTaken;
		}

		averageTime = averageTime / repetitions;
		System.out.println("Time taken for selection sort is " + averageTime + "ms");

		startingTime = 0;
		finishingTime = 0;
		timeTaken = 0;
		averageTime = 0;

		for (int i = 0; i < repetitions; i++) {
			testingArray = numbersFromFile.clone();
			startingTime = System.currentTimeMillis();
			SortComparison.quickSort(testingArray);
			finishingTime = System.currentTimeMillis();
			timeTaken = finishingTime - startingTime;
			averageTime = averageTime + timeTaken;
		}

		averageTime = averageTime / repetitions;
		System.out.println("Time taken for quick sort is " + averageTime + "ms");

		startingTime = 0;
		finishingTime = 0;
		timeTaken = 0;
		averageTime = 0;

		for (int i = 0; i < repetitions; i++) {
			testingArray = numbersFromFile.clone();
			startingTime = System.currentTimeMillis();
			SortComparison.mergeSortRecursive(testingArray);
			finishingTime = System.currentTimeMillis();
			timeTaken = finishingTime - startingTime;
			averageTime = averageTime + timeTaken;
		}

		averageTime = averageTime / repetitions;
		System.out.println("Time taken for recursive merge sort is " + averageTime + "ms");

		
        startingTime = 0;
        finishingTime = 0;
        timeTaken = 0;
        averageTime = 0;
       
     for (int i = 0; i < repetitions; i++) {
     	testingArray = numbersFromFile.clone();
         startingTime = System.currentTimeMillis();
         SortComparison.mergeSort(testingArray);
         finishingTime = System.currentTimeMillis();
         timeTaken = finishingTime - startingTime;
         averageTime = averageTime + timeTaken;
       }

      averageTime = averageTime/repetitions;
      System.out.println("Time taken for iterative merge sort is " + averageTime + "ms");
    }

    //method to read the doubles from the given input files, and return an array of doubles from the file!
    public static double[] readDoublesFromFile(String fileName) throws IOException {
    	Path filePath = Paths.get(fileName);
    	Scanner scanner = new Scanner(filePath);
    	List<Double> doubles = new ArrayList<>();
    	while(scanner.hasNext()) {
    		if(scanner.hasNextDouble()) {
    			doubles.add(scanner.nextDouble());
    		}
    		else {
    			scanner.next();
    		}
    	}
    	double[] array = new double[doubles.size()];
    	for(int i = 0; i < doubles.size(); i++) array[i] = doubles.get(i);
    	return array;
    }
    
}

