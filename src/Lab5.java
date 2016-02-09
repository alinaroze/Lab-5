import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Vector;

public class Lab5{
    /**
    * Partitions an array for quickSort.
    * @param first is the index of the first element to sort with
    * <code>first <= last</code>.
    * @param last is the index of the last element to sort with
    * <code>first <= last</code>.
    * @param theArray is the array to be sorted: the element
    * between <code>first</code> and <code>last</code> (with
    * <code>first <= last</code>)will be sorted.
    * @return the index of the pivot element of
    * theArray[first..last]. Upon completion of the method, this will
    * be the index value lastS1 such that <code>S1 =
    * theArray[first..lastS1-1] < pivot theArray[lastS1] == pivot S2 =
    * theArray[lastS1+1..last] >= pivot </code>
    */
    private static <E extends Comparable<? super E>> int partition(E[] theArray, 
				 int first, int last) {
	// tempItem is used to swap elements in the array
	E tempItem; 
	E pivot = theArray[first];   // reference pivot
	// initially, everything but pivot is in unknown
	int lastS1 = first;          // index of last item in S1
	// move one item at a time until unknown region is empty
	for (int firstUnknown = first + 1; firstUnknown <= last; 
	     ++firstUnknown) {
	    // Invariant: theArray[first+1..lastS1] < pivot
	    //            theArray[lastS1+1..firstUnknown-1] >= pivot
	    // move item from unknown to proper region
	    if (theArray[firstUnknown].compareTo(pivot) < 0) {
		// item from unknown belongs in S1
		++lastS1;
		tempItem = theArray[firstUnknown];
		theArray[firstUnknown] = theArray[lastS1];
		theArray[lastS1] = tempItem;
	    }  // end if
	    // else item from unknown belongs in S2
	}  // end for
	// place pivot in proper position and mark its location
	tempItem = theArray[first];
	theArray[first] = theArray[lastS1];
	theArray[lastS1] = tempItem;
	return lastS1;
    }  // end partition

    public static <E extends Comparable<? super E>> E  kSmall(int k,
		  E[] array, int first, int last){

    	int pivot = partition(array,first,last);
    	if(k == pivot){
    		return array[pivot];
    	}
    	else if (k < pivot-first+ 1){
    		return kSmall(k,array,first, pivot - 1 );
    	}
    	else {
    		return kSmall(k-(pivot-first+1),array,pivot+1,last);
    	}
		
    }
    

    public static void main(String[] args){
	try{
	    Scanner console = new Scanner(System.in);
	    System.out.println("Enter the name of the file containing the data");
	    String filename = console.next();
	    // read the data in the file
	    Vector<Integer> vec = new Vector<Integer>();
	    Scanner scanData = new Scanner(new File(filename));
	    while(scanData.hasNext())
		vec.add(scanData.nextInt());
	    scanData.close();
	    Integer[] myArray = new Integer[vec.size()];
	    int count=0;
	    System.out.println("The integers in the file "+filename +" are: ");
	    for (Integer val:vec){
		myArray[count]=val;
		System.out.print(val+" ");
		count++;
	    }
	
	    // code to ask user for an index, k, between 1 and the number of integers read in, N
	    // if number entered is outside the [1,N] range, exit loop
	    // otherwise call ksmall to find the kth smallest element, write out the value with
	    // an appropriate message, and loop for the next input
	    
	    Scanner console1 = new Scanner(System.in);
	    System.out.print("Enter and index between 1 and the number of integers in the file. Or enter a number outside " +
	    		"of the parameters to exit the program.");
	    Integer input = console.nextInt();
	    while( input >= 1 && input < myArray.length)
	    {
	    	System.out.print("The "+input +" smallest number is: " +input.kSmall(input ,myArray, 1, myArray.length));
	    }
	}
	catch(IOException e){
	    System.err.println("IOError!!!\n" + e);
	    System.exit(9);
	}
    }
}//end class
