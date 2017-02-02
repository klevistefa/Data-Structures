/***************************************************
 *
 * MergeSortPlus
 *
 * 	This is MergeSort, but with InsertionSort at the tail end...
 *
 * Data Structures (CS102 Fall 2015)
 * Prof.Yap
 *
 ***************************************************/
package sort;

import java.util.Random;

class MergeSort extends Sort {

    // Merging input subarrays
    // 		A[i,k]     with    A[k+1, j]
    // and output to temporary subarray
    // 		B[i,j]
    // and finally copying back into A[i,j]
    //
    static void merge(int i, int j, int k){		// i <= k < j
	int imin =i, jmin = k+1;
	int kmin =i;

	while (imin<=k && jmin<=j){
	    if (compare(imin,jmin)<0)
		setBtoA( kmin++,  imin++);
	    else
		setBtoA( kmin++,  jmin++);
	}

	if (imin>k)
	    for ( ; jmin <=j; jmin++)
		setBtoA( kmin++, jmin);
	else
	    for ( ; imin <=k; imin++)
		setBtoA( kmin++, imin);

	copyBA( i, j );
    }//merge

    static void sort(){
	sort(0, nn-1);
    }

    void dynamicSort(){
      sort();
    }

    static void sort(int start, int end){
	if (start >= end) return;
	sort(start, (start+end)/2);
	sort(((start+end)/2) +1, end);
	merge(start, end, (start+end)/2);
    }

    ////////////////////////////////////////////////////
    // Main Method
    ////////////////////////////////////////////////////
    public static void main (String[] args) {

	getParams(args);

	fillRandom(3*nn);
	showOne("Array before MergeSort:");
	showAA(3);		// show first and last 3 ints
	sort();			// sort it

	showOne("Array after MergeSort:");
	showAA(3);		// show first and last 3 ints
	selfTest(isSorted(),	"CORRECTLY SORTED!",
				"ERROR -- NOT SORTED!");
    }//main
}//MergeSort
