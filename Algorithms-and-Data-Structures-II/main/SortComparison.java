import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Prathamesh Sai
 *  @version HT 2020
 */


// PART 1 OF ASSIGNMENT: Implementation
 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){
    	//if the input is null or has a length less than or equal to 1, there is no point sorting anything! (already sorted)
    	if(a == null || a.length <= 1) {
    		return a;
    	}
    	
    	//Otherwise, let's start sorting using insertion sort!
    	
    	//declare a variable for swapping the elements.
    	double temp;
    	//the outer-loop is for starting at the first element and going right -->
    	for(int i = 1; i < a.length; i++) {
    		
    		//the inner nested-loop is for starting at the same index as i, but going left <--
    		for(int j = i; j > 0; j--) {
    			
    			//if the current element is less than the element to the left, we swap them! (In an ideal situation, the current element should be greater than the element to the left)
    			if(a[j] < a[j-1]) {
    				//swap them both
    				temp = a[j];
        			a[j] = a[j-1];
        			a[j-1] = temp; 					
    			}		
    		}
    	}
    	return a;
    }//end insertionsort
	
	    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
    	//if the input is null or has a length less than or equal to 1, there is no point sorting anything! (already sorted)
    	if(a == null || a.length <= 1) {
    		return a;
    	}
    	
    	//Otherwise, let's start sorting using selection sort!
    	
    	int n = a.length;
    	//The outer-loop is for starting at the first element and moving right across the unsorted sub-array -->
    	// We have i < n-1 because at the second last element, we already know that the last element is in order!
		for (int i = 0; i < n - 1; i++) {

			// Find the minimum element in unsorted array
			int minElementIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (a[j] < a[minElementIndex])
					minElementIndex = j;
			}
			// Swap the found minimum element with the first element
			double temp = a[minElementIndex];
			a[minElementIndex] = a[i];
			a[i] = temp;
		}
    	return a;
    }//end selectionsort

    
    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[]){
    	//if the input is null or has a length less than or equal to 1, there is no point sorting anything! (already sorted)
    	if(a == null || a.length <= 1) {
    		return a;
    	}
    	
    	//Otherwise, let's start sorting using quick sort!
    	
    	//shuffle array before sorting for quick-sort!
    	//we do this because the worst case for quick-sort is an already sorted array!
    	//by shuffling the array, we can reduce the chance of the input being sorted already.
    	shuffleArray(a);
    	
    	//Use the recursive helper method
    	quickSortRecursiveQuick(a, 0, a.length - 1);
		return a;
    }//end quicksort

    private static void quickSortRecursiveQuick(double[] a, int low, int high) {
    	if (high <= low) {
        	return;
        }
    	
    	//Call the partition method, it's time complexity is linear
        int partitionIndex = partition(a, low, high);
        
        //Call this method again but pass in the two disjoint subsets, causing a time complexity of Log N.
        quickSortRecursiveQuick(a, low, partitionIndex - 1);
        quickSortRecursiveQuick(a, partitionIndex + 1, high);
      }

      private static int partition(double[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        double pivot = a[lo];
        while (true) {
        
        //we use <= and >= as an improvement for quick-sort! (rather than < and > ).
          while (a[++i] <= pivot) {
        	  if (i == hi) {
        		  break;
        	  }
          }
          while (a[--j] >= pivot) {
        	  if (j == lo) {
        		  break;
        	  }
          }
          
          //exit when i and j overlap!
          if (i >= j) {
        	  break;
          }
          
          	//swap the elements at positions i and j
            double temp = a[j];
		    a[j] = a[i];
			a[i] = temp;
        }
        
        //swap the elements at positions j and lo
        double temp = a[lo];
		a[lo] = a[j];
		a[j] = temp;
        return j;
      }
    
      //method to shuffle an array of type double, typically useful for sorting before using quick sort!
      static void shuffleArray(double[] ar)
      {
    	//get a random number as an index!
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
          int index = rnd.nextInt(i + 1);
          // Swap the two numbers to make the array shuffled!
          double a = ar[index];
          ar[index] = ar[i];
          ar[i] = a;
        }
      }
      
    /**
     * Sorts an array of doubles using a recursive implementation of Merge Sort. (Top-Down merge sort)
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
	public static double[] mergeSortRecursive(double[] a) {
		//if the input is null or has a length less than or equal to 1, there is no point sorting anything! (already sorted)
    	if(a == null || a.length <= 1) {
    		return a;
    	}
    	
    	//Otherwise, let's start sorting!
		double[] auxiliary = new double[a.length];
		//Kick off recursion by passing in 0 and array length-1 as indices (i.e. the full original array)
		mergeSortRecursive(a, auxiliary, 0, a.length - 1);
		return a;
	} // end mergeSortRecursive

	private static void mergeSortRecursive(double[] a, double[] aux, int low, int high) {
		if (high <= low) {
			return;
		}
		int mid = low + (high - low) / 2;
		mergeSortRecursive(a, aux, low, mid);
		mergeSortRecursive(a, aux, mid + 1, high);
		merge(a, aux, low, mid, high);
	}
      
      
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort. (Bottom-up merge sort)
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSort(double a[]) {
    	//if the input is null or has a length less than or equal to 1, there is no point sorting anything! (already sorted)
    	if(a == null || a.length <= 1) {
    		return a;
    	}
    	
    	//Otherwise, let's start sorting!
        int n = a.length;
        double[] auxiliary = new double[n];
        
        //Pass through array merging sub-arrays of size 1,2,4...etc
        for (int sz = 1; sz < n; sz = sz+sz) {
          for (int low = 0; low < n - sz; low += sz+sz) {
            merge(a, auxiliary, low, low+sz-1, Math.min(low+sz+sz-1, n-1));
          }
        }
        return a;
    }//end mergesort
    
    private static void merge(double[] a, double[] aux, int lo, int mid, int hi) {
        
    	//Copy Array into auxiliary array.
    	for(int k = lo; k <= hi; k++) {
    		aux[k] = a[k];
    	}

    	//Merge elements back into original, but sort them!
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
          if (i > mid) {
        	  a[k] = aux[j++];
          }
          else if (j > hi) {
        	  a[k] = aux[i++];
          }
          else if (a[j] < aux[i]) {
        	  a[k] = aux[j++];
          }
          else a[k] = aux[i++];
        }
      }
 }//end class

