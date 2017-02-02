package bst;

//file: BST.java
//	DO NOT MODIFY THIS FILE!
//
//	Simple implementation of a Binary Search Tree (extends BST.java)
//
//	The keys in the binary tree are integers.
//	Assume the keys in a tree are unique (distinct).
//	There is an binary node class called BinNode:
//	    these nodes have parent pointers to facilitate deletion. 
//
//	Members:
//		Node root = null;
//		int size = 0;
//		int count = 0;		 -- to estimate complexity
//		
//	BASIC Methods (implemented for you)
//		boolean add(int k) 	 -- Adds key k to the tree (if k is not
//						already in the tree)
//					    Returns true iff successful.
//		boolean delete(int k) 	 -- Deletes node with key k (if k is 
//						in the tree)
//		BinaryNode search(int k) -- Returns a node u that contains
//						k, or u is the immediate 
//						predecessor or successor of k.
//				 		This means we can insert k as
//						the left or right child of u
//		void inorder()		 -- Returns the in-order list of keys 
//						in the BST.
//		Node min(Node u)	 -- Returns the minimum node below u
//		Node max(Node u)	 -- Returns the maximum node below u
//
//	TESTING Methods (implemented for you)
//		Node randomNode(rg)	 -- returns a random node in BST
//		void diag(msg, vv)	 -- diagnostic messages (vv=verbosity)
//		void testAdd(rg, nn, aList) -- adding random ints to BST
//		void testDelete(rg, aList) -- deleteing random keys of aList
//
//	Usage:
//		> java BST [nn=10 [ss=0 [mm=0 [vv=0]]]]	
//				where	nn = size of test
//					ss = random seed
//					mm = test level
//					vv = verbosity
//
//Data Structures (CS 102)
//Fall 2016
//Professor Yap
//Nov 2, 2016
//

import java.util.Random;
import java.util.ArrayList;

public class BST {

class BinNode {
	int key;
	BinNode left=null, right=null, parent=null;

	//Constructor
	BinNode(int k) { key=k;
	}
}//class

////////////////////////////////////////////////////
// MEMBERS
////////////////////////////////////////////////////
	BinNode root=null; 
	int size=0;
	int count=0;	// tracks the number of "operations"

////////////////////////////////////////////////////
// BASIC METHODS (implemented for you)
////////////////////////////////////////////////////
	// search(k) returns a node: if the tree is non-empty,
	// the node is non-null
	// and either contains key k or is the immediate successor or
	// predecessor of k.
	public BinNode search(int k){
	   count++;
	   if (root == null) return null;
	   BinNode u = root;
	   
	   while (u.key != k) { //invariant: u is non-null
	      count++;
	      if (u.key > k) {
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
	public boolean add(int k){
	   	count++;
	   if (root == null) {
	       root = new BinNode(k);
	       size = 1;
	       return true;
	   }
	   BinNode u = search(k);
	   if (u.key == k) {
	       return false; 	// cannot add
	   }
	   size++;
	   BinNode v = new BinNode(k);
	   if (u.key > k) {
	       u.left = v;
	       u.left.parent = u;}
	   else {
	       u.right = v;
	       u.right.parent = u;}
	   return true;
	}//add

	// inorder traversal:
	public void inorder(BinNode u){
	   count++;
	   if (u == null) return;
	   System.out.printf("(");
	   inorder(u.left);
	   System.out.printf("%d", u.key);
	   inorder(u.right);
	   System.out.printf(")");
	}//inorder
	public void inorder(){ inorder(root); System.out.println(); }
	   	
	// min(u): 
	public BinNode min(BinNode u) {
	      count++;
	      if (u==null) return null;
	      while (u.left != null) {
	          count++;
	          u = u.left;
	      }
	      return u;
	}//min

	// max(u): 
	public BinNode max(BinNode u) {
	      count++;
	      if (u==null) return null;
	      while (u.right != null) {
	          count++;
	          u = u.right;
	      }
	      return u;
	}//max

	// cut (u): where u is non-null and has at most one child 
	private void cut(BinNode u){
	  BinNode w; // if u has a non-null child, it is w!
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
	public boolean delete(int k){
	   BinNode u = search(k);
	   if (u == null || u.key != k) return false;
	   delete(u);
	   return true;
	}//delete(key)

	// delete(node): 
	public void delete(BinNode u){
	   BinNode v = u; // v is the node we want to cut!
	   if (u.left != null && u.right != null) {
	   	  v = min(u.right);
	      u.key = v.key;
	   }
	   cut(v);
	   size--;
	}//delete(node)

////////////////////////////////////////////////////
// MORE METHODS (you need to implement these)
////////////////////////////////////////////////////
   // sizeOf(u) = number of nodes in T(u)
   public int sizeOf(BinNode u){ return -1; }
   
   // indexOf(k, u) = number of nodes in T(u) whose keys are <= k
   public int indexOf(int k, BinNode u){ return -1; }
   public int indexOf(int k) { return indexOf(k, root); }
   
   // inverse of index(k, u):
   // 	get(i, u) = node v such that i=indexOf(v.key, u) = i
   public BinNode get(int i, BinNode u){ return null; }
   public BinNode get(int i) {return get(i, root);}

////////////////////////////////////////////////////
// TESTING METHODS
////////////////////////////////////////////////////
   // randomNode(rg): returns a random node in BST
   public BinNode randomNode(Random rg){
       if (root==null) return null;
       return get(1+rg.nextInt(size));
   }//randomNode
   
   // testing index
   public boolean testGetIndex(Random rg){
   	BinNode u = randomNode(rg);
   	if (u == get( indexOf(u.key) ))
   		return true;
   	return false;
   }//testGetIndex
   
   // diagnostics messages
   public void diag(String msg, int verbose){
   	if (verbose>0)
   	    System.out.println(msg);
   	if (verbose>1){
   	   if (root==null) 
   	 	System.out.println("   DIAG: empty tree\n");
   	   else {
   		System.out.printf("   DIAG: root=%d, size=%d\n",
			root.key, size);
   	   if (verbose>2 && root.left!=null)
   	        System.out.printf("   DIAG: right=%d,\n", root.right.key );
   			System.out.printf("   DIAG: tree="); inorder();
   	   }
   	}
   }//diag
   
   // testAdd(rg, nn, vv, aList)
   public void testAdd(Random rg, int nn, int vv, ArrayList<Integer> aList){
	count=0;
	for (int i=0; i<nn; i++){
	    	int a = rg.nextInt(2*nn);
		if (add(a)){
			System.out.printf("+%d; ", a);
			if (a%2 == 1) 
			    aList.add(new Integer(a));
		} else
			System.out.printf("=%d; ", a);
	}
	diag("\n ========= BST after " + nn + " random adds:\n", vv);
	diag("\n ========= random adds count = " + count, vv);
   }//testAdd

   // testDelete(rg, aList)
   public void testDelete(Random rg, int vv, ArrayList<Integer> aList){
	count=0;
	while (aList.size()>0){
	    int i = rg.nextInt(aList.size());
	    int k = aList.remove(i);
	    if (delete(k)) {
	        diag("deleted " + k + ": ", 2);
	    } else
		diag("Failure!",1); // should not happen
	}
	diag("\n ======== After random removals: ",vv);
	diag("\n ========= random removals count = " + count, vv);
   }//testDelete
 

////////////////////////////////////////////////////
// MAIN METHOD
////////////////////////////////////////////////////
  public static void main(String[] args) {

	int nn = (args.length>0)? Integer.parseInt(args[0]) : 10;
	int ss = (args.length>1)? ss = Integer.parseInt(args[1]) : 0;
	int mm = (args.length>2)? Integer.parseInt(args[2]) : 0;
	int vv = (args.length>3)? Integer.parseInt(args[3]) : 1;
	
	Random rg = (ss==0)? rg = new Random() : new Random(ss);
	
	System.out.printf("=======> BST: nn=%d, ss=%d, mm=%d, vv=%d\n\n",
					nn, ss, mm, vv);
	
	ArrayList<Integer> aList = new ArrayList<Integer>();
	
	BST tt = new BST();
	
	////////////////////////////////////////////////////
	// n random additions:
	////////////////////////////////////////////////////
	tt.testAdd(rg, nn, vv, aList);

	////////////////////////////////////////////////////
	// random removals from aList:
	////////////////////////////////////////////////////
	tt.testDelete(rg, vv, aList);

  }//main

}//BST


