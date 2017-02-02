/*
 * TestSort.java
 *
 *	Experiment 0
 *		-- has been implemented
 *		-- it just calls the various sorts
 *		-- but you need to debug MergeSort
 *		-- and implement MergeHybrid
 *
 *	Experiment 1
 *		-- produces a table of run time in this form:
 *
 *		====   ========  ======  ======= ======  =====
 *		  n    BestTime  Insert  Bubble  Select  Quick ...
 *		         (msec)   Sort    Sort    Sort    Sort
 *		====   ========  ======  ======= ======  =====
 *              1000    123.45    2.34    9.87    3.21    1.00
 *              2000    234.56    2.43    8.78    3.12    1.00
 *              3000     ....
 *
 *			where we only show the ratios of the running
 *			times of the other methods to the best time.
 *
 *	Experiment 2
 *		-- produces a similar table, but this time we
 *			count the number of comparisons, not time.
 *
 *	NOTE: WE WILL NOT REQUIRE Experiment 3...
 *	Experiment 3
 *		-- is open-ended for your own design
 *		-- basically, we want to determine the best cutOff
 *		   for a hybrid Merge-Insert Algorithm.
 *		-- you have to explain how you want to show this
 *
 * Data Structures CS102
 * Professor Yap
 * Fall 2016
 *
 *************************************************** */
package sort;


import java.util.Random;

public class TestSort extends Sort{

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/
    // PLEASE IMPLEMENT THESE EXPERIMENTS
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%/

	static void experiment1() {
	    showOne("=== Experiment 1 ===");
			double [] avgTime = new double[7];
			double [] ratio = new double[7];
			initRg();

			System.out.println("\n");
			System.out.printf(
			"   n     |  BestTime ||  Insert   Bubble    Select   Quick    Heap     Merge    Hybrid\n");
			System.out.println("\n");
			System.out.printf(
			" ======= | ========= || ===============================================================\n");
			System.out.println("\n");

			int n = nn;
			for(int p = 0; p < pp; p++){
				System.out.printf(
				" %5d   |   %s  ||    %3.3f    %3.3f    %3.3f    %3.3f    %3.3f    %3.3f    %3.3f\n",
				AA.length, showTime(1000*timeRow(avgTime, ratio, n)), ratio[0], ratio[1], ratio[2], ratio[3],
				ratio[4], ratio[5], ratio[6]);
				System.out.println("\n");
				n += mm;
			}
	}//experiment1

	static double timeRow(double[] avgTime, double[] ratio, int nn){
		AA = new int[nn];
		init();
		initRg();
		fillRandom(3*nn);
		avgTime[0] = timing(new InsertSort(), AA, rr);
		avgTime[1] = timing(new BubbleSort(), AA, rr);
		avgTime[2] = timing(new SelectSort(), AA, rr);
		avgTime[3] = timing(new MergeSort(), AA, rr);
		avgTime[4] = timing(new QuickSort(), AA, rr);
		avgTime[5] = timing(new HeapSort(), AA, rr);
		avgTime[6] = timing(new MergeHybrid(), AA, rr);


		double bestTime = avgTime[0];

		for (int i = 1; i < avgTime.length; i++){
			if (avgTime[i] < bestTime) bestTime = avgTime[i];
		}

		for (int i = 0; i < ratio.length; i++){
			ratio[i] = avgTime[i]/bestTime;
		}

    return bestTime;
	}

	static void experiment2() {
	    showOne("=== Experiment 2 ===");
			int[] avgCount = new int[7];
			double[] ratio = new double[7];

			System.out.println("\n");
			System.out.printf(
			"   n     |  Count  ||  Insert   Bubble    Select   Quick    Heap     Merge    Hybrid\n");
			System.out.println("\n");
			System.out.printf(
			" ======= | ======= || ===============================================================\n");
			System.out.println("\n");
			int n = nn;
			for(int p = 0; p < pp; p++){
				System.out.printf(
				" %5d   |   %d  ||  %3.3f    %3.3f    %3.3f    %3.3f    %3.3f    %3.3f    %3.3f\n",
				AA.length, countRow(avgCount, ratio, n), ratio[0], ratio[1], ratio[2], ratio[3],
				ratio[4], ratio[5], ratio[6]);
				System.out.println("\n");
				n += mm;
			}
	}//experiment2

	static int countRow (int avgCount[], double[] ratio, int nn){
		AA = new int[nn];
		initRg();
		avgCount[0] = counting(0, AA, rr, nn);
		avgCount[1] = counting(1, AA, rr, nn);
		avgCount[2] = counting(2, AA, rr, nn);
		avgCount[3] = counting(3, AA, rr, nn);
		avgCount[4] = counting(4, AA, rr, nn);
		avgCount[5] = counting(5, AA, rr, nn);
		avgCount[6] = counting(6, AA, rr, nn);

		int bestCount = avgCount[0];
		for (int i = 1; i < avgCount.length; i++){
			if (avgCount[i] < bestCount) bestCount = avgCount[i];
		}

		for (int i = 0; i < ratio.length; i++){
			ratio[i] = avgCount[i]/bestCount;
		}
     return bestCount;
	}

	static void experiment3() {
	    // THIS IS NOT REQUIRED FOR HW7
	    showOne("=== Experiment 3 ===");
	}//experiment3

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

    }//ma
}//TestSort
