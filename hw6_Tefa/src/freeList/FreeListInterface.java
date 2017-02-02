package freeList;

public interface FreeListInterface <T>{
	
	//*****************************************
	// MEMBERS, CONSTANTS
	//*****************************************
	final static int NUL = -1;
	
	//static int MAX = 20; // total number of slots
	//int freePool = 0; // first slot in the pool of free slots
	//int freeSize = MAX; // number of free slots in pool
	
	//*****************************************
	// GETTING and SETTING slots
	//*****************************************
	T getVal(int N);            // get value of slot N
	int getNext(int N);         // get next slot after N
	void setVal(int N, T val);  // set value of Slot N
	void setNext(int N, int M); // set slot M as the next slot after N
	
	//*****************************************
	// NEW and FREE slots
	//*****************************************
	int freeSize();   	// number of free slots available
	int newSlot(); 		// returns a free slot (this is our "new")
	void releaseSlot(int N);   // returns slot N to the free list

	//*****************************************
	// INSERT and REMOVE slots from linked slots:
	//	2 versions of insert: add and app(=append)
	//*****************************************
	int remove(int N);      // remove slot M that follows slot N,
				// and return M.  It is the user's 
				// responsibility to do releaseSlot(M).
	int app(int N, T val); // first M = getFree(N), then setVal(M, val),
			// then setNext(N,M), finally return M.
			// NOTE: app is short for "append"
	int add(int N, T val); // first M = getFree(N), then setVal(M, val),
			// then setNext(M,M), finally return M.

	int search(int N, T val); // look for T in list N, return the
	 			   // slot containing T, or return NUL.
	
	int getIndex(int N, int i); // return the i-th slot in List(N)

	//*****************************************
	//Helpers:
	//*****************************************
	void showFree();  	// Show the freePool list
	void showList(int N);	// Show the list N
	// void showAll();      // Show all slots, free or not
}

