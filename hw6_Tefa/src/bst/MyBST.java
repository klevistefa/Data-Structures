package bst;

/*************************************************************
 * file: MyBST.java
 *
 *	This extends BST class (see BST.java for details)
 *
 *	Members: 		Node root = null;
 *				int size = 0;
 *				int count = 0;
 *
 *	    (For simplicity, we just say "Node" for BinNode)
 *		
 *	BASIC METHOD implemented in BST.java 
 *		boolean add(int k) 	 
 *		boolean delete(int k) 	
 *		BinaryNode search(int k)
 *		void inorder()		
 *		Node min(Node u)
 *		Node max(Node u)
 *	TEST Methods implemented in BST.java
 *		Node randomNode(rg)	
 *		boolean testGetIndex(u)
 *		void diag(msg, vv)
 *		void testAdd(rg, nn, aList)
 *		void testDelete(rg, aList)
 *
 *	OTHER Methods: (to be implemented in this class)
 *		int sizeOf(u) 	  -- Number of nodes in T(u)
 * 		int indexOf(k, u) -- Number of nodes with keys <= k
 *		Node get(i, u)	  -- Returns v s.t. i=indexOf(v.key, u)
 *		Node succ(u)	  -- Returns the successor of u (or null)
 *		Node pred(u)	  -- Returns the predecessor of u (or null)
 *
 *	We implemented this for you:
 *		void testGetIndex(rg, mm)
 *		void testSuccPred(rg, mm)
 *
 *	Usage:
 *	Usage:
 *		>> java BST [nn=10 ss=0 mm=0 vv=0]	
 *			where   nn = size of test
 *				ss = random seed
 *				mm = test level
 *				vv = verbosity level
 *
 * Data Structures (CS 102)
 * Fall 2016
 * Professor Yap
 * Nov 2, 2016
 ************************************************************/

import java.util.Random;
import java.util.ArrayList;

public class MyBST extends BST {

////////////////////////////////////////////////////
// MEMBERS
////////////////////////////////////////////////////
   // no new members

////////////////////////////////////////////////////
// BASIC METHODS (implemeted for you)
////////////////////////////////////////////////////
   // see BST.java

////////////////////////////////////////////////////
// MORE METHODS (you need to implement these)
////////////////////////////////////////////////////
   // sizeOf(u) = number of nodes in T(u)
   public int sizeOf(BinNode u){
       if (u==null) return 0;
       return 1 + sizeOf(u.left) + sizeOf(u.right);
   }//sizeOf
   
   // indexOf(k, u) = number of nodes in T(u) whose keys are <= k
   public int indexOf(int k, BinNode u){
   	if (u==null) return 0;
   	int i = indexOf(k, u.left);
   	if (k < u.key) return i;
   	if (k == u.key) return i+1;
   	return (i+1+ indexOf(k, u.right));
   	}//indexOf(k,u)
   // indexOf(k) = indexOf(k, root)
   public int indexOf(int k) { return indexOf(k, root); }
   
   // inverse of index(k, u):
   // 	get(i, u) = node v such that i=indexOf(v.key, u) = i
   public BinNode get(int i, BinNode u){
       if (u==null || i<=0) return null;
       int s=sizeOf(u.left);
       if (i > s+1+sizeOf(u.right)) return null;
       if (i <= s) return get(i, u.left);
       if (i == s+1) return u;
       return get(i-s-1, u.right);
   	}//get(i)
   public BinNode get(int i) {return get(i, root);}
   
   public boolean isLeftChild(BinNode u){
       if (u == null || u.parent == null) return false;
       return (u == u.parent.left);
   }
   public boolean isRightChild(BinNode u){
       if (u == null || u.parent == null) return false;
       return (u == u.parent.right);
   }
   public BinNode succ(BinNode u){
       if (u==null) return null;
       if (u.right != null) return min(u.right);
       while (isRightChild(u)) u=u.parent;
       if (u.parent==null) return null;
       return u.parent;
   }//succ
   public BinNode pred(BinNode u){
       if (u==null) return null;
       if (u.left != null) return max(u.left);
       while (isLeftChild(u)) u = u.parent;
	if (u.parent == null) return null;
	return u.parent;
   }//pred
////////////////////////////////////////////////////
// TESTING METHODS
////////////////////////////////////////////////////
   // testing index
   public boolean checkGetIndex(BinNode u){
   	if (u == get( indexOf(u.key, root), root))
   		return true;
   	return false;
   }
   public void testGetIndex(Random rg, int mm, int vv){
   	count=0;
   	int err=0;
   	for (int i=0; i<mm; i++)
   	    if (! checkGetIndex( randomNode(rg) ))   err++;
   	if (err==0)
   	    System.out.printf("\n ====== testGetIndex is CORRECT!!\n");
   	else
   	    System.out.printf("\n ====== %d ERRORs in testGetIndex!!\n", err);
   	diag("\n ========= GetIndex count = " + count, vv);
   }//testGetIndex(rg, mm)

   public boolean checkSuccPred(BinNode u){
       BinNode v = succ(u);
       boolean b = true;
       if (v == null)	b = (u == max(root));
       else		b = b && (u == pred(v));
       v = pred(u);
       if (v == null)	b = b && (u == min(root));
       else		b = b && (u == succ(v));
       return b;
   }//checkSuccPred
   public void testSuccPred(Random rg, int mm, int vv){
   	count=0;
   	int err=0;
   	for (int i=0; i<mm; i++)
   	    if (! checkSuccPred( randomNode(rg) ))   err++;
   	if (err==0)
   	    System.out.printf("\n ====== testSuccPred is CORRECT!!\n");
   	else
   	    System.out.printf("\n ====== %d ERRORs in testSuccPred!!\n", err);
   	diag("\n ========= SuccPred count = " + count, vv);
   }//testGetIndex(rg, mm)
   
   
////////////////////////////////////////////////////
// MAIN METHOD   -- this has the tests in BST and more... 
////////////////////////////////////////////////////
  public static void main(String[] args) {

	int nn = (args.length>0)? Integer.parseInt(args[0]) : 10;
	int ss = (args.length>1)? ss = Integer.parseInt(args[1]) : 111;
	int mm = (args.length>2)? Integer.parseInt(args[2]) : 0;
	int vv = (args.length>3)? Integer.parseInt(args[3]) : 1;
	
	Random rg = (ss==0)? rg = new Random() : new Random(ss);

	System.out.printf("=======> BST: nn=%d, ss=%d, mm=%d, vv=%d\n\n",
					nn, ss, mm, vv);

	ArrayList<Integer> aList = new ArrayList<Integer>();
	
	MyBST tt = new MyBST();

	////////////////////////////////////////////////////
	// n random additions:
	////////////////////////////////////////////////////
	tt.testAdd(rg, nn, vv, aList);

	////////////////////////////////////////////////////
	// random get + indexOf tests 
	////////////////////////////////////////////////////
	mm = nn;
	if (mm>20) mm = 20;  // nn may be big!
	tt.testGetIndex(rg, mm, vv);

	////////////////////////////////////////////////////
	// random succ + pred tests 
	////////////////////////////////////////////////////
	tt.testSuccPred(rg, mm, vv);
  }//main

}//BST


