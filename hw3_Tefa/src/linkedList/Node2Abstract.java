/* Node2Abstract.java
 *	is an abstract class for a generic doubly-linked lists
 *
 *	You should write a Node2 class to extend this.
 *
 *	NOTE: the "2" in "Node2" indicates the doubly-linked nature
 *
 *	Methods:
 *		void	add(T x)	-- adds new node with value x 
 *					   after the this node.
 *		Node2	remove()	-- removes this.next, and returns
 *					   the this node.
 *		void	showHead(int m) -- show the first m values
 *		void	showTail(int m) -- show the last m values
 *
 *		void	removeOdd()	-- assuming T=Integer, you remove
 *					   all the odd values
 */
package linkedList;

import java.util.Random;

abstract class Node2Abstract<T> {
    T val;
    Node2<T> next, prev;

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //CONSTRUCTORS:
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    Node2Abstract(T val){
	this.val = val;
    }

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //METHODS:
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // 
    abstract void add(T x);		// Inserts a new node with val=x
    					//  between this and this.next

    abstract Node2<T> remove();	// Removes this.next, and returns
    					//  the removed node (not value).
    					//  The list u is unchanged if you do:
    					// 	u.add(x); u.remove()

    abstract void showHead(int m);	// Print the first m values.
    		    // If m is more than the length of list, just print list.
		    // This assumes that type T has a toString() method:
		    // E.g., if m=3, you print 3 lines in this format:
		    //    0. first value
		    //    1. second value
		    //    2. third value

    abstract void showTail(int m);	// print the last m values (in order)
		    // Assuming the length of list is 10, and m=3,
		    // you should print 3 lines in this format:
		    //	    7. the 7-th value
		    //	    8. the 8-th value
		    //	    9. the 9-th value

    abstract Node2<T> removeOdd();	// assuming T=Integer, you remove
		    		// all the odd values.  Returns
    				// the new list (might be empty!)

    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    // MAIN -- DO NOT CHANGE THIS!
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    public static void main(String[] args){
	int nn = (args.length>0)?  Integer.parseInt(args[0]) : 10;
	int mm = (args.length>1)?  Integer.parseInt(args[1]) : 3;
	int ss = (args.length>2)?  Integer.parseInt(args[2]) : 0;
	Random rg = (ss>0) ?  new Random(ss) : new Random();

	System.out.printf("nn=%d, mm=%d, ss=%d\n", nn, mm, ss);

	// initial node (assume nn>0):
	Node2<Integer> iList = new Node2<Integer>(
				new Integer(rg.nextInt(2*nn)) );

	for (int i=1; i<nn; i++){
	    iList.add( new Integer(rg.nextInt(2*nn)) );
	}

	System.out.printf("Show the first %d entries\n", mm);
		iList.showHead(mm);
	System.out.printf("Show the last %d entries\n", mm);
		iList.showTail(mm);
	System.out.printf("Show the last %d entries\n", 2*mm);
		iList.showTail(2*mm);
	
	System.out.printf("\n\nNow remove all odd values from list\n");
	iList = iList.removeOdd();
	System.out.printf("Show the first %d entries\n", mm);
		iList.showHead(mm);
	System.out.printf("Show the last %d entries\n", mm);
		iList.showTail(mm);
	
    }//main

}//class

