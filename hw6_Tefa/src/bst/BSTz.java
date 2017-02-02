package bst;

/*****************************************
 *   file: BSTz.java
 *	-- in this generic variant of BSTz.java we use mm=0 and mm=1
 *		to decide whether T=Integer or T=String
 *
 *	-- in case of T=String, the random
 *		entries are Zoombini names!
 * 
 *	Conversion of BST class  into a generic class
 *			(read BST.java)
 *
 * 	Methods:
 * 	   boolean add(int k) 	 -- Adds key k to the tree (if k is not
 * 					already in the tree).
 * 					Returns true iff successful.
 * 	   boolean delete(int k) -- Deletes node with key k (if k is 
 * 						in the tree)
 * 	   BinNode search(int k) -- Returns a node u that contains
 * 					k, or u is the immediate 
 * 					predecessor or successor of k.
 * 				 	This means we can insert k as
 * 					the left or right child of u
 * 	   void inorder()	 -- Returns the in-order list of keys 
 * 					in the BST.
 * 	   BinNode min(BinNode u)	 -- Returns the minimum node below u
 * 	   BinNode max(BinNode u)	 -- Returns the maximum node below u
 *
 * 	Usage:
 *	  >> java BSTz [nn=10 ss=0 mm=1 vv=0] 
 * 					     
 *		where	nn = size of the test
 *			ss = seed
 *			mm = test level
 *			vv = verbosity level
 * 					     
 * 
 * Data Structures (CS 102)
 * Fall 2016
 * Professor Yap
 * Nov 2, 2016
*****************************************/

import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;
import zoombinis.NewZoombinis;

public class BSTz <T extends Comparable<? super T>> {

	public class BinNode <T extends Comparable<? super T>> {
		T key;
		BinNode<T> left=null, right=null, parent=null;
		
		// for display only:
		String label;
		int pos, weight, halflength; 

		//Constructor
		BinNode(T k) {
		    key=k; }
		BinNode(T k, String l) {
		    key=k; label=l; }
	}//class

	////////////////////////////////////////////////////
	// MEMBERS
	////////////////////////////////////////////////////
		BinNode<T> root=null; 
		int size=0;
		int count=0; 

	////////////////////////////////////////////////////
	// METHODS
	////////////////////////////////////////////////////

	// returns a node: if the tree is non-empty, the node is non-null
	// and either contains key k or is the immediate successor or
	// predecessor of k.
	public BinNode<T> search(T k){
		if (root == null) return null;
		BinNode<T> u = root;
		
		while (u.key.compareTo(k)!=0) { //invariant: u is non-null
		   if (u.key.compareTo(k)>0) {
		       if (u.left == null) return u;
		       else u=u.left;
		   } else {
		       if (u.right == null) return u;
		       else u=u.right;
		   }
		}
		return u;
	}//search

	// adds a new key to the tree (if possible)
	public boolean add(T k){
		if (root == null) {
		    root = new BinNode<T>(k);
		    size = 1;
		    return true;
		}
		BinNode<T> u = search(k);
		if (u.key.compareTo(k) == 0) {
		    return false; 	// cannot add
		}
		size++;
		if (u.key.compareTo(k)>0) {
		    u.left = new BinNode<T>(k);
		    u.left.parent = u;}
		else {
		    u.right = new BinNode<T>(k);
		    u.right.parent = u;}
		return true;
	}//add

	// inorder traversal:
	public void inorder(BinNode<T> u){
		if (u == null) return;
		System.out.printf("(");
		inorder(u.left);
		System.out.printf("%s", u.key.toString());
		inorder(u.right);
		System.out.printf(")");
	}//inorder

	public void inorder(int verbose){
		if (verbose>1) 	System.out.printf("\n ===============\n");
		if (verbose>0)
		   inorder(root);
		else
		    System.out.printf(
			"\n BST size=%d, root=%s, min=%s, max=%s\n",
				size, root.key,
				this.min(this.root).key,
				this.max(this.root).key );
		if (verbose>1) 	System.out.printf("\n ===============\n");
	}
	public void inorder(){ inorder(root); }
			
	// min(u): where u is non-null 
	private BinNode<T> min(BinNode<T> u) {
		while (u.left != null) u = u.left;
		   return u;
	}//min

	// max(u): where u is non-null 
	private BinNode<T> max(BinNode<T> u) {
		while (u.right != null) u = u.right;
		   return u;
	}//max

	// cut (u): where u is non-null and has at most one child 
	private void cut(BinNode<T> u){
	   BinNode<T> w; // if u has a non-null child, it is w!
	   if (u.left == null) w=u.right;
	   else w = u.left;
	   if (u.parent == null) {
		   root = w; 
		   if (w!=null) w.parent=null;
		   }
	   else {// u.parent != null
		   if (u.parent.left == u) {
			   u.parent.left = w;
			   if (w!=null) w.parent=u.parent;
		   } else {
			   u.parent.right = w;
			   if (w!=null) w.parent=u.parent;
		   }
	   }
	}//cut

	// delete(key): returns true if key was deleted
	public boolean delete(T k){
		   BinNode<T> u = search(k);
		   if (u == null || u.key.compareTo(k) != 0) return false;
		   delete(u);
		   return true;
	}//delete(key)

	// delete(node): 
	public void delete(BinNode<T> u){
		   BinNode<T> v = u; // v is the node we want to cut!
		   if (u.left != null && u.right != null) {
		   	  v = min(u.right);
		      u.key = v.key;
		   }
		   cut(v);
		   size--;
	}//delete(node)

	////////////////////////////////////////////////////
	// TESTING METHODS
	////////////////////////////////////////////////////

	// diagnostics
	public void diag(String msg, int vv){
	   if (vv>0)	System.out.println(msg);
	   if (vv>1){
	      if (root==null) 
	    	System.out.println("DIAG: empty tree\n");
	      else {
		System.out.printf("DIAG: root=%s, size=%d\n",
			root.key.toString(), size);
		if (vv>2)
		   System.out.printf("DIAG: "); 
		   inorder(vv);
	      }
	   }
	}//diag


   // <T=Integer> testAdd(rg, bstz, nn, aList, vv)
   public void testAdd (Random rg, 
	     BSTz<Integer> bstz,
	     int nn, ArrayList<Integer> aList, int vv){
	int count = 0;
	for (int i = 0; i < nn; i++) {
	    int a = rg.nextInt(2 * nn);
	    if (bstz.add(a)) {
		System.out.printf("+%d; ", a);
		if (a % 2 == 1)
		   aList.add(a);
	    } else
	    	System.out.printf("=%d; ", a);
	}//for
	bstz.diag("\n ======== After "+nn+" random insertions: ", 0);
	bstz.diag("\n", 0);
   }//testAdd <T=Integer>

   // <T=String> testAdd(rg, bstz, nn, aList, vv)
   public void testAdd (int ss,
	     BSTz<String> bstz,
	     int nn, ArrayList<String> aList, int vv)
   		throws IOException {
	int count = 0;
	Random rg = (ss==0)? new Random() : new Random(ss);
	NewZoombinis zoom = new NewZoombinis(ss, 16, 2, "src/zoombinis");
	for (int i = 0; i < nn; i++) {
	    String zz = zoom.randomName();
	    if (bstz.add(zz)) {
		System.out.printf("+%s; ", zz);
		if (rg.nextInt(2) == 1)
		   aList.add(zz);
	    } else
	    	System.out.printf("=%s; ", zz);
	}//for
	bstz.diag("\n ======== After "+nn+" random insertions: ", 0);
	bstz.diag("\n", 0);
   }//testAdd <T=String>

   // <T=Integer> testDelete(rg, bstz, delList, vv)
   public void testDelete (Random rg,
	 	BSTz<Integer> bstz,
	 	ArrayList<Integer> delList, int vv){
	if (vv>1){
	   System.out.println("");
	   for (Integer i: delList)
		System.out.printf(" %d, ", i);
	}
	System.out.println("");
	int nn=delList.size();
	
	while (delList.size()>0){
	  	Integer k = delList.remove(
				rg.nextInt(delList.size()) );
	    	if (bstz.delete(k)) 
	        	bstz.diag("-- Deleted " + k.toString(), vv);
		else
	        	bstz.diag("-- ERROR: failed delete of "
				+ k.toString(), vv);
	}//while
	// Show final BST only if we did not show the individual deletions
	if (vv<2) bstz.diag("\n ======== After "+nn+" random removals: ", 0);
   }//testDelete <T=Integer>

   // <T=String> testDelete(ss, bstz, delList, vv)
   public void testDelete (int ss,
	 	BSTz<String> bstz,
	 	ArrayList<String> delList, int vv){

       Random rg = (ss==0)?  new Random(): new Random(ss);
	
	if (vv>1){
	   System.out.println("");
	   for (String i: delList)
		System.out.printf(" %s, ", i);
	}

	System.out.println("");
	int nn=delList.size();
	
	while (delList.size()>0){
	  	String k = delList.remove(
				rg.nextInt(delList.size()) );
	    	if (bstz.delete(k)) 
	        	bstz.diag("-- Deleted " + k, vv);
		else
	        	bstz.diag("-- ERROR: failed delete of "
				+ k, vv);
	}//while
	// Show final BST only if we did not show the individual deletions
	if (vv<2) bstz.diag("\n ======== After "+nn+" random removals: ", 2);
   }//testDelete <T=String>

////////////////////////////////////////////////////
// MAIN METHOD
////////////////////////////////////////////////////
   public static void main(String[] args) throws IOException {
		int nn = (args.length>0)? Integer.parseInt(args[0]) : 10;
		int ss = (args.length>1)? ss = Integer.parseInt(args[1]) : 0;
		int mm = (args.length>2)? Integer.parseInt(args[2]) : 0;
			//mm=0 means T=Integer	
			//mm=1 means T=String	
		int vv = (args.length>3)? Integer.parseInt(args[3]) : 0;
		String pp = (args.length>4)? args[4] : "src/bst/zoombinis";
		
		Random rg = (ss==0)? rg = new Random() : new Random(ss);
		
		System.out.printf(
			"=======> BSTz: nn=%d, ss=%d, mm=%d, vv=%d\n\n",
		   		nn, ss, mm, vv);
		
		BSTz<Integer> ti= new BSTz<Integer>();
		BSTz<String> ts= new BSTz<String>();
		ArrayList<Integer> delList = new ArrayList<Integer>();
		ArrayList<String> delLists = new ArrayList<String>();

	////////////////////////////////////////////////////
	// n random additions:
	////////////////////////////////////////////////////
	if (mm==0)
		ti.testAdd(rg, ti, nn, delList, vv);
	else
		ts.testAdd(ss, ts, nn, delLists, vv);

	////////////////////////////////////////////////////
	// random removals from aList:
	////////////////////////////////////////////////////
	int finalSize=-1;
	boolean flag=false;
	if (mm==0) {
	    	finalSize = ti.size - delList.size();
		ti.testDelete(rg, ti, delList, vv);
		if (ti.size == finalSize)
		    flag=true;
	} else {
		finalSize = ts.size - delLists.size();
		ts.testDelete(ss, ts, delLists, vv);
		if (ts.size == finalSize)
		    flag=true;
	}
	if (flag)
	  System.out.printf("\nCORRECT!  final BST size is %d !\n", finalSize);
	else
	  System.out.printf("\nERROR!  final BST size is %d !\n", finalSize);
  }//main

}//BSTz




