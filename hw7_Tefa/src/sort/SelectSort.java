/***************************************************
 *
 * Selection Sort
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

class SelectSort extends Sort{

  void dynamicSort(){
    sort();
  }

    static void sort(){
	for (int i=0; i<nn; i++) {
		int jmin = i;
		for (int j=i+1; j<nn; j++)
		    if (compare(j,jmin)<0)
			jmin = j;
		swap(i,jmin);
	}
    }//sort

    public static void main (String[] args) {

	getParams(args);

	fillRandom(3*nn);
	showOne("Original array is");
	showAA(3);

	sort();
	showOne("\nFinal array is");
	showAA(3);
	selfTest(isSorted(),	"CORRECTLY sorted!\n",
				"ERROR!\n");
    }//main
}//SelectSort
