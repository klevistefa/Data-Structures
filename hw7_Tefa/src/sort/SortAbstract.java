/*
 * SortAbstract.java
 *
 *	(THIS IS NOT REALLY AN ABSTRACT CLASS, but we  pretend it
 *	is one so that we can see all the methods in a glance)
 *
 * 	This abstract class provides basic utilities for sorting,
 *	but it implements no sorting algorithm.  It is to
 *	be implemented and extended by classes that do the actual sorting.
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

public abstract class SortAbstract{

	// PUBLIC STATIC MEMBERS:
	static int ss = 0;   // seed 
	static int nn = 100; // (initial) size of problem
	static int mm = 100; // increments for problem size 
	static int pp = 1;   // number for problems (number of rows in table)
	static int rr = 3;   // number of Repetitions for timing
	static int tt = 0;   // (First) Type of sorting algorithm 
	static int uu = 1;   // Second Type of sorting algorithm 
	static int vv = 0;   // verbose mode
	static int xx = 0;   // experiment mode

	// PRIVATE STATIC MEMBERS:  (these appear in the Sort class)
	//private static Random rg = null; // random number generator
	//private static int[] AA = null;  // array to be sorted
	//private static int[] BB = null;  // temporary array
	//private static int count = 0;    // number of comparisons
	//private static int moves = 0;    // number of moves

	// Catalog of Sorting Algorithms
	static String[] sortType = {
	    		"InsertSort", "BubbleSort", "SelectSort", 
	    		"MergeSort", "QuickSort", "HeapSort",
			"MergeHybrid"};

	
	// STATIC METHODS
	static void init(){}	   	   // set count=0, moves=0
	static void initRg(){}		   // initialize rg
	static int getCount(){		   // returns count
	    		return 0;}
	static int getMoves(){		   // returns moves
	    		return 0;}
	static void setA(int i, int val){} // AA[i] = val
	static void setB(int i, int val){} // BB[i] = val
	static int getA(int i){		   // returns AA[i]
	    		return 0;}
	static int getB(int i){		   // returns BB[i]
	    		return 0;}
	static int compareTo(int i, int value){  // Compare AA[i]: value
	    		return 0;}
	static int compare(int i, int j){  // Compare AA[i]: AA[j]
	    		return 0;}
	static void swap(int i, int j){}   // Swap AA[i] and AA[j]
	static void moveTo(int i, int j){}   // Move AA[i] into AA[j]
	static void copyAB(int i, int j){} // Copy AA[i..j] to BB[i..j]
	static void copyBA(int i, int j){} // Similar to copyAB

	// SORTING UTILITY
	static boolean isSorted(){	// true if AA is sorted
	    		return true;}
	static void fillRandom(int mm){}// fill AA with random entries <= mm
	static void fillZeroes(){} 	// fill AA with zero entries
	static void fillSorted(){}      // fill AA with sorted entries
	static void fillNearSorted(){}  // fill with near sorted entries

	// OUTPUT DIAGNOSTICS
	static void showAA(int n){} // show first n and last n values
				   // of AA. If n=0, show all.
	static void showBB(int n){} // similarly for BB

	static void selfTest(      // show one of 2 messages
		boolean b, String ifTrue, String ifFalse){}

	static void showOne(String s1){} // show one string
	static void showTwo(String s1, String s2){} // show 2 strings

	static String showTime(double t){  // show time
	    		return "";}



}//SortAbstract



