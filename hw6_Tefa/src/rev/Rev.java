package rev;

/*****************************************
 * Rev.java
 *	We want to compare the running times of 3
 *	algorithms for list reversal:
 *	  	
 *		reverse(u)  -- recursive algorithm
 *		iReverse(u) -- iterative algorithm
 *		iReverse2(u) -- iterative algorithm
 *
 *	-----------------------------
 *	A. Here is how iReverse(u) works:
 *	-----------------------------
 *	   Say we have a list with 3 consecutive nodes u, v, w:
 *
 *                ... -> u -> v -> w -> ...
 *
 *	Here, u and v are non-null but w might be null. 
 *	Let the following 3 Node variables (call them "pointers")
 *			p, q, r 
 *	that refer to u, v and w respectively.
 *	Recursively, suppose the list has been reversed
 *	up to, and including, u:   
 *	
 *                     ... <- u    v -> w -> ...
 *                            |    |    |     
 *                            p    q    r     
 *
 *	A single iteration amounts to the two transformation steps:
 *
 *		(Step I) Reverse the list at v, i.e.,
 *		       q.next = p;
 *
 *                          ... <- u <- v    w -> ...
 *                                 |    |    |     
 *                                 p    q    r     
 *
 *		(Step II) Advance the 3 pointers, i.e.,
 *		       p = q;
 *		       q = r;
 *		       if (r!=null) r=r.next;
 *
 *                          ... <- u <- v    w -> x ...
 *                                      |    |    | 
 *                                      p    q    r 
 *
 *
 *	-----------------------------
 *	B. Here is how iReverse2(u) works:
 *	-----------------------------
 *		To avoid 3 pointer assignments in Step II, we had
 *		discussed this in lecture the idea of
 *		using a "circular array" pp[] of 3 pointers
 *		so that Step II has only ONE pointer assignment!
 *		Let  
 *			int idx;
 *		be used as the index into pp[].  Thus we can get
 *		to any pointer by writing
 *			pp[ (idx + i) % 3]
 *		for i=0,1,2.
 *
 *	-----------------------------
 *	C. INFORMATION about timing
 *	-----------------------------
 *		Whenever we try to time an operation, we run it 3 times
 *		and take the average. We will use System.nanoTime() that
 *		returns a long integer, but for our purposes we assign the
 *		value to a double because we want to do some division when we
 *		take average.   Also, we want to see time in milliseconds or
 *		10^{-3} seconds.  To convert from nanoseconds or 10^{-9}
 *		seconds, you divide by 10^6.
 *		Finally, to display double, we suggest using
 *			System.out.printf("%.3f", time-in-milliseconds);
 *		so that we see only 3 decimal places of the time.
 *		
 *	-----------------------------
 *	D. THE TIMING TABLE produced by timeTable(...)
 *	-----------------------------
 *		There are 20 rows for each value of n, the size
 *			of the random list being tested.
 *		n ranges from nn (initial value) and increases
 *			by increments of 200.
 *		For each value of n, there are 6 columns of numbers:
 *			The first 3 columns are the times (in milliseconds)
 *				of each of the 3 reverse methods
 *			The next 3 columns are the corresponding ratios
 *				of each time over the best time.
 *				(So, the ratio of the best time is =1)
 *		E.g., the first row of our sample table is
 *
 *              n     |  Recur   Iter    Iter2  | Recur    Iter    Iter2
 *            ======= | ======= ======= ======= | ======= ======= =======
 *                10  |  0.002   0.001   0.001  |  2.500   1.167    1.000
 *
 *		This tells us the Iter2 has the best time (ratio of 1.000)
 *		and Recur is 2.5 times slower.  Note that Iter is
 *		1.167 slower than Iter2 (but since we print only 3 decimal 
 *		places for time, so we cannot see a difference when we look
 *		at the actual time (both are listed as 0.001 msecs).
 * Chee Yap
 * Fall 2016, CS102
 *
 *****************************************/
import java.util.Random;
import util.MyList;

class Rev extends MyList{

    /*****************************************
     * MEMBERS
     *****************************************/
    static int reps = 3;  // running times are averages over
    			  // this many reps (repeats)

    /*****************************************
     * METHODS
     *****************************************/

    // iterative Reversal (cyclic version)
    Node iReverse2(Node u){
	return reverse(u);
    }// iReverse

    // iterative Reversal (direct version)
    Node iReverse(Node u){
	return reverse(u);
    }// iReverse


    // Timing of (recursive) reversal
    static void timeRec(Rev rev, Node u){
	double startTime, duration;
	rev.showList(u, 4, 4, "Timing Recursive Reversal -- Input List is:");
	   startTime = System.nanoTime();
		for (int j = 0; j<reps; j++)
		    u = rev.reverse(u);
	   duration = (System.nanoTime() - startTime)/(reps*1000000); // in msec
	   System.out.printf("   >> average time = %f msecs.\n",
		   duration);
    }//

    // Timing of (iterative) reversal
    static void timeIter(Rev rev, Node u){
	double startTime, duration;
	rev.showList(u, 4, 4, "Timing Iterative Reversal -- Input List is:");
	   startTime = System.nanoTime();
		for (int j = 0; j<reps; j++)
		    u = rev.iReverse(u);
	   duration = (System.nanoTime() - startTime)/(reps*1000000); // in msec
	   System.out.printf("   >> average time : %f msecs.\n",
		   duration);
    }//

    // Timing of (iterative) reversal (version 2)
    static void timeIter2(Rev rev, Node u){
	double startTime, duration;
	rev.showList(u, 4, 4,
		"Timing Iterative (version2) Reversal -- Input List is:");
	   startTime = System.nanoTime();
		for (int j = 0; j<reps; j++)
		    u = rev.iReverse2(u);
	   duration = (System.nanoTime() - startTime)/(reps*1000000); // msec
	   System.out.printf("   >> average time : %f msecs.\n",
		   duration);
    }//

    // Time Row 
    static void timeRow(Rev rev, Node u, double[] avgTime, double[] ratio){
	double startTime, bestTime;
	startTime = System.nanoTime();

	for (int j=0; j<reps; j++)
	    u=rev.reverse(u);
	avgTime[0] = (System.nanoTime() - startTime)/(reps*1000000); //in msec

	startTime = System.nanoTime();
	for (int j=0; j<reps; j++)
	    u=rev.iReverse(u);
	avgTime[1] = (System.nanoTime() - startTime)/(reps*1000000); //in msec

	startTime = System.nanoTime();
	for (int j=0; j<reps; j++)
	    u=rev.iReverse2(u);
	avgTime[2] = (System.nanoTime() - startTime)/(reps*1000000); //in msec

	bestTime = rev.min(rev.min(avgTime[0],avgTime[1]), avgTime[2]);
	ratio[0] = avgTime[0]/bestTime;
	ratio[1] = avgTime[1]/bestTime;
	ratio[2] = avgTime[2]/bestTime;
    }
    // Time Table 
    static void timeTable(Rev rev, Random rg, int nn, int inc, int kk){
	double[] avgTime = new double[3];
	double[] ratio = new double[3];

	System.out.printf(
	" ------- | ----------------------- | -----------------------\n");
	System.out.printf(
	"         | (average time in msecs) |      (timing ratios)   \n");
	System.out.printf(
	" ------- | ----------------------- | -----------------------\n");
	System.out.printf(
	"   n     |  Recur   Iter    Iter2  | Recur    Iter    Iter2\n");
	System.out.printf(
	" ======= | ======= ======= ======= | ======= ======= =======\n");

	int n = nn;
	for (int k=0; k<kk; k++){
	   Node u = rev.randomList(n, rg);
	   timeRow(rev, u, avgTime, ratio);

	   System.out.printf(
	   "  %5d  |  %3.3f   %3.3f   %3.3f  |  %3.3f   %3.3f    %3.3f\n",
	    n,  avgTime[0], avgTime[1], avgTime[2],
		ratio[0], ratio[1], ratio[2]);
	   n = n+inc;
    	}
    }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static void main (String[] args){
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// nn is size of list to test
		int nn = (args.length>0)? Integer.parseInt(args[0]) : 10;
	// ss is seed for random number generator
		int ss = (args.length>1)? Integer.parseInt(args[1]) : 0; 
	// mm is used in various ways (e.g., to show only first mm
	//	and last mm elements of the long list)
		int mm = (args.length>2)? Integer.parseInt(args[2]) : 4; 
	// vv is the verbosity -- use it for your debugging...
		int vv = (args.length>3)? Integer.parseInt(args[3]) : 0;
	// tt is the test we want to run:
	//	tt=4 is the most important, as it constructs our table
		int tt = (args.length>4)? Integer.parseInt(args[4]) : 0; 

	Random rg = new Random();

	Rev rev = new Rev();
	rev.vv = vv;	 // set verbosity globally

	// Generate random list and show it:
	Node u = rev.randomList(nn, rg);

    if (tt==0) {// no timing -- just basic operations
	rev.showList(u,mm,mm,"Original list:");

	// Reverse list and show it:
	u = rev.reverse(u);
	rev.showList(u,mm,mm,"Recursively Reversed list:");

	// iReverse list and show it:
	u = rev.iReverse(u);
	rev.showList(u,mm,mm, "Iteratively Reversed list:");

	// iReverse2 list and show it:
	u = rev.iReverse2(u);
	rev.showList(u,mm,mm, "Iteratively Reversed (version2) list:");

	return;
    }

    if (tt==1){
	timeRec(rev, u);
	timeIter(rev, u);
	timeIter2(rev, u);
    }

    if (tt==2){
	timeRec(rev, u);
    }

    if (tt==3){
	timeIter(rev, u);
    }

    if (tt==4){		// Constructing a timing table
			// nn is determined by a command line argument
			// but we fix these here:
	int inc = 200;	// inc = size of increment
	int kk = 20;	// kk  = number of increments
	timeTable(rev, rg, nn, inc, kk);
    }

    }//main
}
