package freeList;

/***************************************************
 * FreeList
 *
 *    Class FreeList<T>
 *	implements the generic interface
 *		FreeListInterface<T>
 *    and its main method tests
 *		T=String
 *    using Zoombini names.
 *
 *	For homework 6, we leave only 5 methods for you to implement:
 *		(a)  newSlot,  releaseSlot
 *		(b)  remove,  add,  app
 *
 *	These methods are listed at the bottom of this class.
 *		Nothing else should be modified.
 *
 ***************************************************
 * Chee Yap
 * Fall 2016 CS102
 *************************************************** */

import java.util.Random;
import java.io.IOException;
import zoombinis.Zoombinis;

public class FreeList <T>
    implements FreeListInterface<T>{

	int MAX = 12;		// MAX is the array size
	int freeSize = MAX;   	// the number of available free slots
	T[] tArray;		// tArray = array of type T
	int[] intArray;		// intArray = array of ints
	int freePool = 0;	// first slot in the pool of free slots

	// CONSTRUCTORS:
	@SuppressWarnings("unchecked")
	FreeList(){
	   tArray = (T[])  new Object[MAX];
	   intArray = new int[MAX];
	   for (int i=0; i<MAX; i++)
		   intArray[i] = i+1;
	   intArray[MAX-1] = NUL;
	}

	@SuppressWarnings("unchecked")
	FreeList(int n){
		MAX = n;
		freeSize = n;
		tArray = (T[])new Object[MAX];
		intArray = new int[MAX];
		for (int i=0; i<MAX; i++)
		  intArray[i] = i+1;
		intArray[MAX-1] = NUL;
	}

	//////////////////////////////////////////
	// MAIN METHOD: DO NOT MODIFY!
	//////////////////////////////////////////
	public static void main(String[] args)
			throws IOException {
		int nn = (args.length>0)? Integer.parseInt(args[0]) : 12;
		int ss = (args.length>1)? Integer.parseInt(args[1]) : 0;

		Random rg = (ss==0)? new Random() : new Random(ss);

		// 1. Get an instance of FreeList, and of Zoombinis:
		FreeList<String> fl = new FreeList<String>(nn);
	 	Zoombinis zoom = new Zoombinis(ss, 16, 2, "src/zoombinis");

		// 2. Create two lists:
		int oddList  = NUL;
		int evenList = NUL;
		int evenListTail = NUL; // to "append" to this list, we need
					// its tail!

		// 3. Generate random Zoombini names and insert (add or app)
		// 	them into the odd or even lists:
		System.out.printf(">> Generating the odd and even lists:\n");
		for (int i = 0; i< nn; i++){
		   String val = zoom.randomName();
		   if (val.length() %2 == 1) {
		      // Note that we must update oddList:
		      oddList = fl.add(oddList, val);
		      System.out.printf("  add to oddList: %s\n", val);
		   } else {
		      // Note that we never need to update evenList,
		      // 	But we must update evenListTail:
		      if (evenList == NUL) {
		          evenList = fl.add(evenList, val);
		          evenListTail = evenList;
		          System.out.printf("  app to evenList: %s\n", val);
		      } else {
		          evenListTail = fl.app(evenListTail, val);
		          System.out.printf("  app to evenList: %s\n", val);
		      }
		   }
		}

		// 4. Print information:
		System.out.printf(">> Here is the odd list:\n");
		fl.showList(oddList);
		System.out.printf(">> Here is the even list:\n");
		fl.showList(evenList);
		System.out.printf(">> Number of free slots is %d\n",
			fl.freeSize());

		// 5. Remove all nodes from oddList
		// 	NOTE: remove is NOT good at removing the head
		// 	of a list. Since we can remove slots
		// 	in any order we like, we keep removing the SECOND
		// 	slot until there is no more SECOND slot.
		System.out.printf(">> Depleting the odd list:\n");
		int slot = fl.remove(oddList);
		while (slot != NUL) {
		    System.out.printf("  removed slot %d, with value %s\n",
			    slot, fl.getVal(slot));
		    fl.releaseSlot(slot);
		    slot = fl.remove(oddList);
		}
		if (oddList != NUL) { // if one more slot to remove!
		    System.out.printf("  removed (last) slot %d, value %s\n",
			    oddList, fl.getVal(oddList));
		    fl.releaseSlot(oddList);
		    oddList = NUL;
		}

		// 6. Repeat the actions of 4.
		System.out.printf(">> Here is the odd list:\n");
		fl.showList(oddList);
		System.out.printf(">> Here is the even list:\n");
		fl.showList(evenList);
		System.out.printf(">> Number of free slots is %d\n",
			fl.freeSize());

	}//main


/***************************************************
 * OVERRIDE METHODS
 *************************************************** */
	@Override
	public int freeSize() {
		return freeSize;
	}

	@Override
	public T getVal(int N) {
		return tArray[N];  // n better be non-NUL
	}

	@Override
	public int getNext(int N) {
		return intArray[N];  // n better be non-NUL
	}

	@Override
	public void setNext(int N, int M) {
		intArray[N] = M;
	}

	@Override
	public void showFree() {
		show(freePool, "freePool List ");
	}

	@Override
	public void showList(int N) {
		show(N, "List " + N);
	}

	/// show  methods
	void show(int N, String label){
		int nn=0;
		System.out.printf("%s :", label);
		while (N != NUL) {
			if (nn++ %6 == 0) System.out.printf("\n  --> ");
			System.out.printf(" %s:(%s,%s) ", N, intArray[N],
				tArray[N]);
			N = intArray[N];
		}
		System.out.printf("\n  ==> List Size is %d\n", nn);
	}
	void showAll(){
		System.out.printf("Show All: avail =%d :\n", MAX);
		for (int i = 0; i< MAX; i++) {
			if (i %6 == 0) System.out.printf("\n  --> ");
			System.out.printf(" %s:(%s,%s) ", i, intArray[i],
				tArray[i]);
		}
		System.out.println("");
	}

	@Override
	public void setVal(int N, T val) {
		tArray[N]=val;
	}


	@Override
	public int search(int N, T val){ // look for T in list N, return the
	 			   // slot containing T, or return NUL.
	    if (N == NUL || getVal(N) == val)
		return N;
	    return search( getNext(N), val);
	}

	public int getIndex(int N, int i){
	    if (i<0) return NUL;
	    if (i==0) return N;
	    return getIndex(getNext(N), i-1);
	}

/***************************************************
 * 5 METHODS TO BE IMPLEMENTED
 *    Nothing above this line should be modified!
 *************************************************** */

	//IMPLEMENT THIS:
	@Override
	public int newSlot() {
		int hold = freePool;
    freePool = getNext(freePool);
    freeSize--;
    return hold;
	}

	//IMPLEMENT THIS:
	@Override
	public void releaseSlot(int N) {
     if (N == NUL) return;
     int M = freePool;
     freePool = N;
     setNext(freePool, M);
     freeSize++;
	}

	//IMPLEMENT THIS:
	@Override
	public int remove(int N) { // remove the N.next from list:
       if (getNext(N) == NUL) return NUL;
       int M = getNext(N);
       setNext(N, getNext(M));
       return M;
	}

	//IMPLEMENT THIS:
	@Override
	public int app(int N, T val) {  // app(end) new slot to follow N
    int M = newSlot();
    setVal(M, val);
    setNext(M, getNext(N));
    setNext(N, M);
    return M;
	}

	//IMPLEMENT THIS:
	@Override
	public int add(int N, T val) {  // add new slot in front of N
		int M = newSlot();
    if (N == NUL){
      N = M;
      setNext(M, NUL);
    } else {
    setNext(M, N);
    N = M;
  }
  setVal(M, val);
  return M;
	}
}// class
