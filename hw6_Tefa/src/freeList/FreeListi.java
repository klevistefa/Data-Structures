package freeList;

/***************************************************
 * FreeListi (not needed for homework 6)
 *
 *    Class FreeListi<T>
 *	extends the generic class
 *		FreeList<T>
 *    and its main method tests
 *		T=Integer. 
 *
 *************************************************** 
 * Chee Yap
 * Fall 2016 CS102
 *************************************************** */

import java.util.Random;

public class FreeListi<T>
    extends FreeList<T>{
	
	//////////////////////////////////////////
	// MAIN METHOD:
	//////////////////////////////////////////
	public static void main(String[] args) {
		int n = (args.length>0)? Integer.parseInt(args[0]) : 12;
		int s = (args.length>1)? Integer.parseInt(args[1]) : 0;

		Random rg = (s==0)? new Random() : new Random(s);
		
		// 1. Get an instance of FreeList:
		FreeList<String> fl = new FreeList<String>(n);
		
		// 2. Create two lists:
		int oddList  = NUL;
		int evenList = NUL;
		int evenListTail = NUL; // to "append" to this list, need tail!
		
		// 3. Generate n random numbers and insert (add or app)
		// 	them into the odd or even lists:
		System.out.printf(">> Generating the odd and even lists:\n");
		for (int i = 0; i< n; i++){
		    int val = rg.nextInt(1000);
		    if (val%2 == 1) {
			// Note that we must update oddList:
			oddList = fl.add(oddList, ""+val);
			System.out.printf("  add to oddList: %d\n", val);
		    } else {
			// Note that we DO NOT need to update evenList,
			// 	But we must update evenListTail:
			if (evenList == NUL) {
			    evenList = fl.add(evenList, ""+val);
			    evenListTail = evenList;
			    System.out.printf("  app to evenList: %d\n", val);
			} else {
			    evenListTail = fl.app(evenListTail, ""+val);
			    System.out.printf("  app to evenList: %d\n", val);
			}
		    }//else
		}//for
		
		// 4. Print information:
		System.out.printf(">> Here is the odd list:\n");
		fl.showList(oddList);
		System.out.printf(">> Here is the even list:\n");
		fl.showList(evenList);
		System.out.printf(">> Here is the freePool list:\n");
		fl.showFree();
	
		// 5. Remove all nodes from oddList
		// 	NOTE: remove is mainly good at removing the tail of a
		// 	list, but not the head of a list.  We take advantage
		// 	of the question allowing us to remove slots in any
		// 	order we like: so we keep removing the SECOND slot
		// 	until there is no more SECOND slot!
		System.out.printf(">> Depleting the odd list:\n");
		int slot = fl.remove(oddList);
		while (slot != NUL) {
		    System.out.printf("  removed slot %d, with value %s\n",
			    slot, fl.getVal(slot));
		    fl.releaseSlot(slot);
		    slot = fl.remove(oddList);
		}
		if (oddList != NUL) { // if one more slot to remove!
		    System.out.printf("  removed (last) slot %d, with value %s\n",
			    oddList, fl.getVal(oddList));
		    fl.releaseSlot(oddList);
		    oddList = NUL;
		}

		// 6. Repeat the a
		System.out.printf(">> Here is the odd list:\n");
		fl.showList(oddList);
		System.out.printf(">> Here is the even list:\n");
		fl.showList(evenList);
		System.out.printf(">> Here is the freePool list:\n");
		fl.showFree();
		
	}//main
	
}//
