/***************************************************
 *
 * QuickSort:
 *
 * We started developing this code in class:
 *
 * 	The main loop was outlined on the blackboard.
 *
 * NOTE: there is no bug now.
 *
 * 	There are only 3 small changes
 * 		from what we developed in class!
 * 	See (BUG1), (BUG2) and (BUG3) below.
 *
 *	I also added a "check()" routine to make sure that
 *	the final array is really sorted.
 *
 * Data Structures (CS102 Spring 2015)
 * Prof.Yap
 * May 6, 2015
 *
 ***************************************************/
package sort;

import java.util.Random;


class QuickSort extends Sort {

    static void sort(){
	sort(0, nn-1);
    }

    void dynamicSort(){
      sort();
    }

    ////////////////////////////////////////////////////
    // METHODS:
    ////////////////////////////////////////////////////

    // k = split(i, j)
    //
    // 	We will rearrange the array A[i,j] so that
    //
    // 		A[i,k] <=  A[k+1,j]
    //
    //  We will use A[i] as the "pivot", in fact:
    //
    // 		A[i,k] <= A[i] < A[k+1,j]
    //
    static int split(int i, int j){

	int big = j, small = i+1;		// Normally, we want
						//	A[big] > pivot=A[i]
						// and
						//	A[small] <= pivot=A[i]

	while (big >= small) {			// stop when big<small

	    while (small<=j && compare(small,i)<=0) //(BUG1) do not let small
		small++;			//  to increase beyond j

	    while (compare(big,i)>0) 	//(NOTE) we do not have a similar issue
		big--;			//  with big because of the pivot !

	    if (big<=small)
		break;
	    // At this point, we have
	    // 			A[big] <= A[i] < A[small]
	    // So this justifies a swap!
	    swap(big, small);
	    big--;
	    small++;
	}
	swap(i,big);		// this is the final swap to move the pivot A[i]
				// into the position between the
				// 		"small part" A[i+1..big],
				// and the
				// 		"big part" A[small..j]
	return big;		// where k=big
    }//split


    static void sort(int i, int j){
	if (j <= i) return;
	int k = split(i,j);	// i<j
	if (k>i) sort(i, k-1);	//(BUG3) In class, we did not check if k>i
	if (k<j) sort(k+1, j);	//(BUG3) In class, we did not check if k<j
    }//sort


    ////////////////////////////////////////////////////
    // Main Method
    ////////////////////////////////////////////////////
    public static void main (String[] args) {

	getParams(args);

	fillRandom(3*nn);

	showOne("Array before QuickSort:");
	showAA(3);		// show first and last 3 ints
	sort();			// sort it

	showOne("Array after QuickSort:");
	showAA(3);		// show first and last 3 ints
	selfTest(isSorted(),	"CORRECTLY SORTED!",
				"ERROR -- NOT SORTED!");
    }//main
}//QuickSort
