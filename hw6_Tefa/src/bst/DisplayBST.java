package bst;

/*****************************************
 *
 *   Displaying a BST
 *
 *	The class DisplayBST an extension of BSTz<String>
 *		from a previous homework.
 *
 *	Our goal is to display a BST such that if you project
 *		all the node labels vertically to the x-axis,
 *		you will get an inorder list of all these labels
 *		(without any spaces).
 *	E.g.,
 *                           CCC
 *                          __|_______________
 *                       BBBB                 G
 *                    _____|            ______|_
 *                  AAAAA              EEE     HH
 *                                 _____|___
 *                              DDDDDDD   FFFF
 *
 *	Other than labels, we only have '_' and '|' chars.
 *	When projected, we get:
 *
 *                  AAAAABBBBCCCDDDDDDDEEEFFFFGHH
 *
 *	We output line by line.
 *	Each line is determined by a tree level.
 *	The entire level of nodes are stored in queues:
 *		we use 2 queues q1, q2.
 *
 *	In each line of output, we keep track of the current cursor position
 *		    using the member variable "currPos".
 *
 *		THIS "currPos" IS UPDATED EACH TIME WE DISPLAY
 *		ANY CHARACTER.  TO ENSURE CONSISTENT BOOKKEEPING,
 *		ALL DISPLAYS USES THE OVERLOADED "show(...)" methods.
 *		WE ONLY UPDATE "currPos" in show(...) methods!
 *
 *	Each BinNode u is enhanced with additional members:
 *	   PostOrder:
 *		u.label		-- the label to print
 *		u.halflength	-- equals  label.length/2 (rounded down)
 *		u.weight	-- the total width of the subtree T(u)
 *				   So the display of T(u) uses u.weight spaces
 *	   Inorder:
 *		u.pos		-- the position of the leftmost
 *					label in the entire tree T(u)
 *
 *	These entries or statistics are computed recursively by a
 *		"fillStats(u,...)" method similar to
 *		our homework problem
 *		How to compute u.weight?
 *			Well, it is done in a postorder style,
 *			rather similar to u.size in the homework.
 *		How do we compute u.pos?
 *			This requires some information from the "top",
 *			rather like u.level in the homework.
 *
 *****************************************
 * Chee Yap
 * Data Structures, Fall 2016
 *****************************************/

import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;
import zoombinis.NewZoombinis;
import util.*;


class DisplayBST
    	extends BSTz<String> {

    /*****************************************
     * MEMBERS
     *****************************************/
     int currPos;	// output position.

////////////////////////////////////////////////////
// MAIN METHOD
////////////////////////////////////////////////////
   public static void main(String[] args) throws IOException {
		int nn = (args.length>0)? Integer.parseInt(args[0]) : 9;
		int ss = (args.length>1)? ss = Integer.parseInt(args[1]) : 111;
		int mm = (args.length>2)? Integer.parseInt(args[2]) : 0;
			//mm=0 means T=Integer
			//mm=1 means T=String
		int vv = (args.length>3)? Integer.parseInt(args[3]) : 0;
		String pp = (args.length>4)? args[4] : "src/bst/zoombinis";

		Random rg = (ss==0)? rg = new Random() : new Random(ss);

		System.out.printf(
			"=======> BSTz: nn=%d, ss=%d, mm=%d, vv=%d\n\n",
		   		nn, ss, mm, vv);

		DisplayBST dBST = new DisplayBST();
		ArrayList<String> delLists = new ArrayList<String>();

	////////////////////////////////////////////////////
	// First create a random tree:
	// 	using random Zoombini names
	////////////////////////////////////////////////////

	dBST.testAdd(ss, dBST, nn, delLists, vv);
		// NOTE: delLists is not used here (it is used to
		// 	mark nodes for random deletion)

	////////////////////////////////////////////////////
	// Display tree
	////////////////////////////////////////////////////

	dBST.fillStats(dBST.root, 0);	// First fill stats

	dBST.show();
	dBST.showBinaryTree ();			// Display binary tree

	dBST.show("\n0123456789 123456789 123456789 123456789");
	dBST.show("0123456789 123456789 123456789 12345\n");
  }//main


/*****************************************
 * METHODS
 *****************************************/

    // show n copies of a character c:
    void show(char c, int n){
	if (n<=0) return;
	String s = "" + c;
	for (int i=1; i<n; i++)
	    s = s+ c;
	System.out.printf(s);
	currPos = currPos+n;
    }

    // show a string s:
    void show(String s){
	System.out.printf(s);
	currPos = currPos + s.length();
    }

    // show a new line
    void show(){
	System.out.printf("\n");
	currPos = 0;
    }


    void showBinaryTree(){
		MyQueue<BinNode<String>> myQ1 =
		    			new MyQueue<BinNode<String>>();
		MyQueue<BinNode<String>> myQ2 =
		    			new MyQueue<BinNode<String>>();
		int cur = 0;
		myQ1.add(root);
		while (!myQ1.isEmpty()){
			show1(myQ1, myQ2);
			show2(myQ2, myQ1);
		}
    }//showBinaryTree

    //max(a,b) -- utility
    public int max(int a, int b) {
	return (a>b)? a : b;
    }

/*****************************************
 * METHODS to be implemented
 * DO NOT MODIFY ABOVE THIS LINE
 *****************************************/

    // IMPLEMENT THIS!
    public void fillStats(BinNode<String> u, int p) {
      if (u == null) return;
      u.label = u.key
      u.halflength =1 + u.label.length()/2;
      u.pos = findPosition(u).indexOf(u.key);

      for (int i = 0; i < 1+2*u.halflength; i++){
        u.label = u.label + " ";
      }

    }

    public String findPosition(BinNode<String> u){
      String projectedBST = "";
      if (u == null) return projectedBST;
      findPosition(u.left);
      if (u.key.length() % 2 == 0) projectedBST += u.key.toString() + " ";
      else projectedBST += u.key;
      findPosition (u.right);
      if (u.key.length() % 2 == 0) projectedBST += u.key.toString() + " ";
      else projectedBST += u.key;

      return projectedBST;
    }

    // IMPLEMENT THIS!
    // 	Show the labels in a level (stored in queue q1)
    // 		but enqueue the level into queue q2:
    void show1(MyQueue<BinNode<String>> q1, MyQueue<BinNode<String>> q2){
	show(); // starts a new line of output!
	while (!q1.isEmpty()){
	    BinNode<String> v = q1.dequeue();
	    // do something...
	    q2.add(v);
	}//while
    }//show1

    // IMPLEMENT THIS!
    // Show the "tree-links" below nodes of a level (stored in queue q1)
    // 		and enqueue the next level into queue q2.
    // 	The tree links look like  "___|_______" and is drawn below
    //	each non-leaf. but leaves do not have links below them.
    //
    void show2(MyQueue<BinNode<String>> q1, MyQueue<BinNode<String>> q2){
	show(); // starts a new line of output!
	while (!q1.isEmpty()){
	    BinNode<String> v = q1.dequeue();
	    // do something...
	    if (v.left!=null)
		 q2.add(v.left);
	    if (v.right!=null)
		 q2.add(v.right);
	}//while
    }//show2

}//DisplayBST class
