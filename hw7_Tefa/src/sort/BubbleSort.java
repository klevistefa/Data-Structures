/***************************************************
 *
 * Bubble Sort
 *
 * We started developing this code in class:
 * 	The main loop was outlined on the blackboard.
 *	Please complete this and test this yourself.
 *
 * Data Structures (CS102 Spring 2015)
 * Prof.Yap
 * April 27
 *
 ***************************************************/
package sort;

import java.util.Random;

class BubbleSort extends Sort {

    static void sort(){

	for (int i=0; i<nn; i++)
	   for (int j=nn-1; j>i; j--)
		if (compare(j-1,j)>0)
			    swap(j-1,j);
    }

    void dynamicSort(){
      sort();
    }

    public static void main (String[] args) {

	getParams(args);

	fillRandom(3*nn);

	showOne("Array before BubbleSort:");
	showAA(3);		// show first and last 3 ints
	sort();			// sort it

	showOne("Array after BubbleSort:");
	showAA(3);		// show first and last 3 ints
	selfTest(isSorted(),	"CORRECTLY SORTED!",
				"ERROR -- NOT SORTED!");
    }//main
}//BubbleSort
