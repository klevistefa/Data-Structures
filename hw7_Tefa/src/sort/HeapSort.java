/* file: HeapSort.java
 *
 * HeapSort:
 *
 *	This code needs to be debugged...
 *
 *
 *  Data Structure (CS 102)
 *  Prof.Yap, Fall 2016
 *
 ****************************************************/
//package pqueue;
package sort;

import java.util.Random;

public class HeapSort extends PQueue {

	static void sort(){
		sort(0, nn-1);
	}

	void dynamicSort(){
		sort();
	}

    	// dummy to be implemented:
	static void sort(int i, int j){
		int n = AA.length;
		for (int k = n / 2 - 1; k >= 0; k--){
			heapify(n, k);
		}
		for (int k = n-1; k >= 0; k--){
			swap(0, k);
				heapify(k, 0);
		}
	}

	static void heapify(int n, int i){
		int largest = i;
		int l = 2*i + 1;
		int r = 2*i + 2;

		if (l < n && compare(l, largest) == 1) largest = l;

		if (r < n && compare(r, largest) == 1) largest = r;

		if (largest != i){
			swap(i, largest);

			heapify(n, largest);
		}
	}

	// buildHeapStep(i)
	// 	is called by builHeap()
	void buildHeapStep(int i){
		int j=i;		// j is the child of i to be compared
//		int k = qq[i];	// k is the key to be moved down
		int k = getA(i);	// k is the key to be moved down

		while (!leaf(i)){
//			if (right(i)<size && qq[right(i)]>qq[left(i)])
			if (right(i)<size && compare(right(i),left(i))>0)
				j=right(i);
			else
				j=left(i);
//			if (k< qq[j]) {
			if (compareTo(j,k)>0) {
//				qq[i]=qq[j];
				moveTo(j,i);
				i=j;
			} else {
//				qq[i]=k;
				setA(i,k);
				return;
			}
		}//while

//		qq[j]=k;
		setA(j,k);
	}//buildHeap

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static void main (String[] args) {
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	getParams(args);


	fillRandom(3*nn);	// Fill array A with random ints:

	showOne("Array before HeapSort:");
	showAA(3);		// show first and last 3 ints
	sort();			// sort it

	showOne("Array after HeapSort:");
	showAA(3);		// show first and last 3 ints
	selfTest(isSorted(),	"CORRECTLY SORTED!",
				"ERROR -- NOT SORTED!");
    }//main
}//HeapSort
