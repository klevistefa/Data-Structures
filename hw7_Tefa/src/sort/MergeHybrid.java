/***************************************************
 *
 * MergeHybrid
 *
 * 	This is MergeSort, but with InsertSort at the base case.
 *
 *	 PLEASE modify the code from MergeSort!
 *
 * Data Structures (CS102 Fall 2015)
 * Prof.Yap
 *
 ***************************************************/
package sort;

import java.util.Random;

class MergeHybrid extends Sort{

    private static int cutOff=10;  // you need to experimentally find the best

    public static void setCutOff(int c) {
			cutOff = c; }


    // THIS IS JUST A PLACEHOLDER:
    static void sort(){
      sort(new InsertSort(), new MergeSort(), 0, nn-1);
    }

    void dynamicSort(){
      sort();
    }

    static void sort(InsertSort sort, MergeSort sort2, int lb, int ub){
      if(ub - lb <= cutOff) InsertSort.sort(lb, ub);
      else {
        int mid = (lb + ub)/2;
        sort(sort, sort2, lb, mid);
        sort(sort, sort2, mid, ub);
        MergeSort.merge(lb, mid, ub);
      }
    }

}//MergeHybrid
