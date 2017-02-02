/***************************************************
 *
 * Insertion Sort
 *
 *
 * Data Structures (CS102 Fall 2015)
 * Prof.Yap
 *
 ***************************************************/
package sort;

import java.util.Random;

class InsertSort extends Sort{

    static void sort(){
	sort(0, nn-1);
    }

    void dynamicSort(){
      sort();
    }
    // We need this more general version of InsertSort
    //		for our MergeHybrid Application!
    //
    static void sort(int start, int end){
	for (int i=start+1; i<=end; i++) {
	    	int remember = getA(i);
		int j;
		for (j=i-1; j>=start; j--){
			if (compareTo(j,remember)>0)
			    moveTo(j,j+1);
			else
			    break;
		}
		setA(j+1, remember);
	}//for
    }//InsertSort


    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/
    // Main Method:
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/

    public static void main (String[] args) {

	getParams(args);

	fillRandom(3*nn);	// Fill array A with random ints:

	showOne("Array before InsertSort:");
	showAA(3);		// show first and last 3 ints
	sort();			// sort it

	showOne("Array after InsertSort:");
	showAA(3);		// show first and last 3 ints
	selfTest(isSorted(),	"CORRECTLY SORTED!",
				"ERROR -- NOT SORTED!");
    }//main
}//InsertSort
