import java.util.Arrays;

// -------------------------------------------------------------------------
/**
 *  This class contains only two static methods that search for points on the
 *  same line in three arrays of integers. 
 *
 *  @author  
 *  @version 18/09/18 12:21:09
 */
class Collinear 
{

   // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinear(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * Array a1, a2 and a3 contain points on the horizontal line y=1, y=2 and y=3, respectively.
     * A non-horizontal line will have to cross all three of these lines. Thus
     * we are looking for 3 points, each in a1, a2, a3 which lie on the same
     * line.
     *
     * Three points (x1, y1), (x2, y2), (x3, y3) are collinear (i.e., they are on the same line) if
     * 
     * x1(y2-y3)+x2(y3-y1)+x3(y1-y2)=0 
     *
     * In our case y1=1, y2=2, y3=3.
     *
     * You should implement this using a BRUTE FORCE approach (check all possible combinations of numbers from a1, a2, a3)
     *
     * ----------------------------------------------------------
     *
     * 	Running Time:
     * 	n = 1000 :  0.475 seconds
     * 	n = 2000 : 3.123 seconds
     * 	n = 4000 : 24.579 seconds 
     * 
     *  Using the spreadsheet:
     *  A = 2.847
     *  B = -29.487
     *  
     *  Predicting Running time for 5000: 34.2 seconds
     *  Accurate Running time for 5000: 44.8 seconds.
     *  
     *  %Error = 30.6%
     *  
     *  Order of Growth
     *  -------------------------
     *
     *  Calculate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of growth: O(N^3)
     *
     *  Explanation: The code below uses a brute force approach, and has three for loops. Since it is quite
     *  expensive to do, it is N * N * N = N^3 for the three loops, each with an order of growth of N.
     */
    static int countCollinear(int[] a1, int[] a2, int[] a3)
    {
    	final int y2minusy3 = 2-3;
    	final int y3minusy1 = 3-1;
    	final int y1minusy2 = 1-2;
    	int numberOfNonHorizontalLines = 0;
    	
    		for(int i = 0; i < a1.length; i++) {
    			for(int j = 0; j < a2.length; j++) {
    				for(int k = 0; k < a3.length; k++) {
    					if((a1[i] * (y2minusy3)) + ((a2[j] * (y3minusy1))) + (a3[k] * (y1minusy2)) == 0) {
    							numberOfNonHorizontalLines++;
    					}
    				}
    			}
    		}
    	
    	return numberOfNonHorizontalLines;
    }

    // ----------------------------------------------------------
    /**
     * Counts for the number of non-horizontal lines that go through 3 points in arrays a1, a2, a3.
     * This method is static, thus it can be called as Collinear.countCollinearFast(a1,a2,a3)
     * @param a1: An UNSORTED array of integers. Each integer a1[i] represents the point (a1[i], 1) on the plain.
     * @param a2: An UNSORTED array of integers. Each integer a2[i] represents the point (a2[i], 2) on the plain.
     * @param a3: An UNSORTED array of integers. Each integer a3[i] represents the point (a3[i], 3) on the plain.
     * @return the number of points which are collinear and do not lie on a horizontal line.
     *
     * In this implementation you should make non-trivial use of InsertionSort and Binary Search.
     * The performance of this method should be much better than that of the above method.
     *
     * 	Running time:
     * 	n = 1000: 0.066 seconds
     * 	n = 2000: 0.231 seconds
     * 	n = 4000: 1.005 seconds
     * 	n = 5000: 1.649 seconds
     * 
     * 	Speed up:
     * 	n = 1000 : 7.19696967 
     * 	n = 2000 : 13.51948052
     * 	n = 4000 : 24.45671642
     * 	n = 5000 : 37.63917526 
     * 
     *  Order of Growth
     *  -------------------------
     *
     *  Calculate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: O(N^2lgN) (i.e n squared log N)
     *
     *  Explanation: The method below contains two for loops, and on top of this we have a binary search call.
     *  The for loops have an order of growth of N each, and the binary seach call has lgN. 
     *  This makes it N * N * lgN = N^2lgN. This is much faster and better than the brute force method above 
     *  which has an order of growth N^3.
     *
     *
     */
    static int countCollinearFast(int[] a1, int[] a2, int[] a3)
    {
    	int count = 0;
		
			sort(a3);
			for (int i = 0; i < a1.length; i++) {
				for (int j = 0; j < a2.length; j++) {
					int arr3Collinear = -1 *(a1[i]) + 2*a2[j];
					if (binarySearch(a3, arr3Collinear)) {
						count++;
					}
						
				}
			}
		return count;
    }

    // ----------------------------------------------------------
    /**
     * Sorts an array of integers according to InsertionSort.
     * This method is static, thus it can be called as Collinear.sort(a)
     * @param a: An UNSORTED array of integers. 
     * @return after the method returns, the array must be in ascending sorted order.
     *
     * ----------------------------------------------------------
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Calculate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: O(N^2)
     *
     *  Explanation: Two linear for-loops.
     *
     */
    static void sort(int[] a)
    {
      for ( int j = 1; j<a.length; j++)
      {
        int i = j - 1;
        while(i>=0 && a[i]>a[i+1])
        {
          int temp = a[i];
          a[i] = a[i+1];
          a[i+1] = temp;
          i--;
        }
      }
    }

    // ----------------------------------------------------------
    /**
     * Searches for an integer inside an array of integers.
     * This method is static, thus it can be called as Collinear.binarySearch(a,x)
     * @param a: A array of integers SORTED in ascending order.
     * @param x: An integer.
     * @return true if 'x' is contained in 'a'; false otherwise.
     *
     * ----------------------------------------------------------
     *
     *  Order of Growth
     *  -------------------------
     *
     *  Calculate and write down the order of growth of your algorithm. You can use the asymptotic notation.
     *  You should adequately explain your answer. Answers without adequate explanation will not be counted.
     *
     *  Order of Growth: O(lgN)
     *
     *  Explanation: The code below has one outer while loop. After each iteration in the loop, the size
     *  of the array being searched is being halved, therefore this gives us an order of growth of lgN because 
     *  we are dividing the size of the array being searched.
     *
     */
	static boolean binarySearch(int[] a, int x) {
		int lo = 0;
		int hi = a.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (x < a[mid]) {
				hi = mid - 1;
			} 
			else if (x > a[mid]) {
				lo = mid + 1;
			} 
			else { 
				return true;
			}
		}
		return false;
	}


}