/*
 * Sort.java
 *
 * 	This class provides basic utilities for sorting,
 *	but it implements no sorting algorithm.  It is to
 *	be extended by other classes that do the actual sorting.
 * 	For example,
 * 		InsertSort, MergeSort, QuickSort, etc.
 *
 *	Sort class provides various static members and methods.
 *		Static members:
 *			nn, ss, AA, BB, count, ...
 *		Static methods:
 *			compare, swap, isSorted, showAA, ...
 *			fillXXX (XXX = Random, Increasing, Decreasing, Zeros)
 *
 * 	Sort has a dummy method called
 *
 * 		public void sort() {}
 *
 * 	which must be overridden by the subclasses to sort the array AA.
 *
 *	Sort provides support
 *		counting the number of comparisons,
 *		swapping,
 *		filling AA with entries,
 *		timing,
 *		comparisons, ...
 *
 * 	We have a catalog of 7 sorting methods encoded by
 * 		String[] sortType;
 *
 *
 * Data Structures CS102
 * Professor Yap
 * Fall 2016
 *
 *************************************************** */
package sort;

import java.util.Random;

public class Sort extends SortAbstract{

	// PRIVATE STATIC MEMBERS:
	private static Random rg = null; // random number generator
	static int[] AA = null;  // array to be sorted
	private static int[] BB = null;  // temporary array
	private static int count = 0;    // number of comparisons
	private static int moves = 0;    // number of data moves

	public static int[] CC = null;  // temporary array

	static void getParams(String[] args){ // also initializes arrays
	   ss = // seed
		(args.length>0 ? ss = Integer.parseInt(args[0]) : 0);
	   nn = // size of problem
		(args.length>1 ? Integer.parseInt(args[1]) : 10);
	   mm = // increments for problem size
		(args.length>2 ? Integer.parseInt(args[2]) : 10);
	   pp = // number for Problems (number of rows in table)
		(args.length>3 ? Integer.parseInt(args[3]) : 1);
	   rr = // number of Repetitions for timing
		(args.length>4 ? Integer.parseInt(args[4]) : 3);
	   tt = // Type of sorting algorithm
		(args.length>5 ? Integer.parseInt(args[5]) : 0);
	   uu = // Second Type of sorting algorithm
		(args.length>6 ? Integer.parseInt(args[6]) : 1);
	   vv = // Verbosity
		(args.length>7 ? Integer.parseInt(args[7]) : 0);
	   xx = // Experiment
		(args.length>8 ? Integer.parseInt(args[8]) : 0);

	   rg = // random number generator
		(ss==0) ? rg = new Random() : new Random(ss) ;
	   AA = new int[nn];
	   BB = new int[nn];
	   CC = new int[nn];
        }//getParams

       static void showParams(){
	 System.out.printf("Parameters:\n");
	 System.out.printf(":: seed ss=%d, size nn=%d, increment mm=%d\n",
	    		ss, nn, mm );
	 System.out.printf(":: rows pp=%d, repeats rr=%d, type1 tt=%d\n",
	    		pp, rr, tt);
	 System.out.printf(":: type2 uu=%d, verbose vv=%d, experiment xx=%d\n",
	    		uu, vv, xx);
       }//showParams

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/
    // Main Method:
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/

    public static void main(String[] args) {

	getParams(args);

	// Experiments 1 and 2:
	switch(xx) {
	    case 0:
	    	experiment0(); return;
	    case 1:
	    	experiment1(); return;
	    case 2:
	    	experiment2(); return;
	    case 3:
	    	experiment3(); return;
	    default:
	    	experiment0(); return;
	}

    }//main

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/
    // Sort:
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/

       static void sort() {};	// to be overridden

			 void dynamicSort(){
	       sort();
	     }

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/
    // Experiments:
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/

	static void experiment0() {

	    int ttt = (tt<sortType.length)? tt : 4 ; // default to Qsort

	    showTwo("\n\n Experiment Zero:",
	    	    "   --> Testing " + sortType[tt] + ":\n\n");

	    init();
	    initRg();
	    fillRandom(3*nn);
	    showOne("Original array is");
	    showAA(3);
	    switch(tt){ 	// tt chooses the type of sorting
		case 0:
		    InsertSort.sort(); break;
		case 1:
		    BubbleSort.sort(); break;
		case 2:
		    SelectSort.sort(); break;
		case 3:
		    MergeSort.sort(); break;
		case 4:
		    QuickSort.sort(); break;
		case 5:
		    HeapSort.sort(); break; 	// THIS IS TO BE DEBUGGED
		case 6:
		    MergeHybrid.sort(); break;	// YOU NEED TO IMPLEMENT THIS
		default:
		    QuickSort.sort(); break;	// if tt>6, do this!
	    }//switch
	    showOne("\nFinal array is");
	    showAA(3);
	    selfTest(isSorted(), "CORRECT! Array AA is sorted!\n",
		    		 "ERROR! Array AA is not sorted!\n");
	    showOne("Number of comparisons = " + getCount());
	    showOne("Number of data moves = " + getMoves());

			for(int i = 0; i < AA.length; i++){
			System.out.print(AA[i] + " ");
			}
	}//experiment0

	static void experiment1() {
	    	showOne("Experiment 1");
	}//experiment1

	static void experiment2() {
	    showOne("Experiment 2");

	}//experiment2
	static void experiment3() {
	    showOne("Experiment 3");
	}//experiment3


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/
    // Implementation of Abstract Methods
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/

	//////////////////////////////////////////
	// STATIC METHODS to access private data (AA, BB, count)
	//////////////////////////////////////////
	static void init(){	   // set count=0
			count=0; moves=0; }

	static int getCount(){		   // returns count
	    		return count; }

	static int getMoves(){		   // returns moves
	    		return moves; }

	static void initRg(){		   // initialize Random number generator
	    rg = (ss==0)? new Random() : new Random(ss); }

	static void setA(int i, int val){
			AA[i] = val; }

	static void setB(int i, int val){
			BB[i] = val; }

	static void setBtoA(int idxB, int idxA){
			BB[idxB] = AA[idxA]; }

	static int getA(int i){		   // return AA[i]
	    		return AA[i]; }

	static int getB(int i){		   // return BB[i]
	    		return BB[i]; }

	static int compareTo(int i, int val){  // Compare AA[i]:value, count++
	    count++;
	    if (AA[i]>val) return 1;
	    if (AA[i]<val) return -1;
	    return 0; }

	static int compare(int i, int j){  // Compare AA[i]: AA[j], count++
	    count++;
	    if (AA[i]>AA[j]) return 1;
	    if (AA[i]<AA[j]) return -1;
	    return 0; }

	static void swap(int i, int j){    // Swap AA[i] and AA[j]
	    moves = moves +2;
	    int tmp = AA[i];
	    AA[i] = AA[j];
	    AA[j] = tmp;}

	static void moveTo(int i, int j){    // Move AA[i] into AA[j]
	    moves++;
	    AA[j] = AA[i];}

	static void copyAB(int i, int j){  // Copy AA[i..j] to BB[i..j]
	    moves = moves +j-i;
	    for (int k=i; k<=j; k++)
		BB[k] = AA[k];}

	static void copyBA(int i, int j){  // Similar to copyAB
	    moves = moves +j-i;
	    for (int k=i; k<=j; k++)
		AA[k] = BB[k];}

	static void copyAC(int[] C, int i, int j){// Copy AA[i..j] to C[i..j]
	    for (int k=i; k<=j; k++)
		C[k] = AA[k];}

	static void copyCA(int[] C, int i, int j){// Copy C[i..j] to AA[i..j]
	    for (int k=i; k<=j; k++)
		AA[k] = C[k];}

	//////////////////////////////////////////
	// SORTING UTILITY
	//////////////////////////////////////////
	static boolean isSorted(){	 // true if AA is sorted
	    for (int k=1; k<nn; k++)
		if (AA[k-1]>AA[k]) return false;
	    return true;}

	static void fillRandom(int maximum){
				   	// fill AA with random entries <= mm
	    for (int k=0; k<nn; k++)
		AA[k] = rg.nextInt(maximum); }

	static void fillZeroes(){     	// fill AA with zero entries
	    for (int k=0; k<nn; k++)
		AA[k] = 0; }

	static void fillSorted(){ 	// fill AA with increasing entries
	    for (int k=0; k<nn; k++)
		AA[k] = k; }

	static void fillReverseSorted(){ // fill AA with decreasing entries
	    for (int k=1; k<=nn; k++)
		AA[k] = nn-k; }

	static void fillNearSorted(){// fill AA with near sorted entries
	    for (int k=0; k<nn; k++)
		AA[k] = k;
	    int i = rg.nextInt(nn);
	    int j = rg.nextInt(nn);
	    swap(i,j);
	    }

        static void fillRandom(int[] AA){
				   	// fill AA with random entries <= mm
	    for (int k=0; k<nn; k++)
		AA[k] = rg.nextInt(nn); }

	//////////////////////////////////////////
	// INPUT-OUPUT UTILITY
	//////////////////////////////////////////
	static void showArray(int[] Arr, int n){
	    		// show first and last n values of Arr
	    		// show all if (n==0 || nn-2*n < 3)
	    System.out.printf("Array AA = (");
	    if (n==0 || (nn - 2*n < 3))
	    	for (int k=0; k<nn-1; k++)
			System.out.printf(" %d,", AA[k]);
	    else {
	        for (int k=0; k<n; k++)
		    System.out.printf(" %d,", AA[k]);
	        System.out.printf("...(%d omitted)...", nn-2*n);
	        for (int k=n; k>=2; k--)
		    System.out.printf(" %d,", AA[nn-k]);
	    }
	    System.out.printf(" %d)%n", AA[nn-1]);
	}//showArray

	static void showAA(int n){   // show first and last n values of BB
	    showArray(AA, n);
	}
	static void showBB(int n){   // show first and last n values of BB
	    showArray(BB, n);
	}

	static void showOne(String s1){
		System.out.printf("%s\n", s1); }

	static void showTwo(String s1, String s2){
		System.out.printf("%s:\n %s\n", s1, s2); }

	static String showTime(double t){
		return String.format("%3.3f", t); }

	static void selfTest( boolean b, String ifTrue, String ifFalse){
	    if (b)	System.out.println(ifTrue);
	    else	System.out.println(ifFalse); }

	//////////////////////////////////////////
	// TIMING AND TESTING
	//////////////////////////////////////////
	/*******************************
	 * timing( sorter, C, rr)
	 *
	 *   where
	 *	sorter is one of the sorting algorithms
	 *	C is array to be sorted, of size nn
	 *	rr is number of repetitions
	 *   returns
	 * 	the average time (in milliseconds) for rr iterations
	 *	of running sorter on input C
	 *
	 *   NOTE: Unfortunately, the time includes the copying of arrays
	 *   If rr is small, we could make rr copies of AA internally!
	 ******************************/
	   static double timing(Sort sorter, int[] C, int rr) {
	    	double time = System.nanoTime();
		for (int i=0; i<rr; i++){
	    	    	copyCA(C, 0, nn-1);
			sorter.dynamicSort();
		}
	    	double time2 = System.nanoTime();
		return (time2 - time)/(rr*1000000);
	   }//timing

		 static int counting(int j, int[] C, int rr, int nn){
			init();
			for(int i = 0; i < rr; i++){
				fillRandom(3*nn);
				switch(j){
					case 0: InsertSort.sort();
					return InsertSort.getCount()/rr;
					case 1: BubbleSort.sort();
					return BubbleSort.getCount()/rr;
					case 2: SelectSort.sort();
					return SelectSort.getCount()/rr;
					case 3: MergeSort.sort();
					return MergeSort.getCount()/rr;
					case 4: QuickSort.sort();
					return QuickSort.getCount()/rr;
					case 5: HeapSort.sort();
					return HeapSort.getCount()/rr;
					case 6: MergeHybrid.sort();
					return MergeHybrid.getCount()/rr;
				}
	 		}
			return 0;
	 	}//counting
}//Sort
