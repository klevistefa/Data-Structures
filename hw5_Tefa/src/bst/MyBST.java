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
 *		void diag(msg, vv)
 *		void testAdd(rg, nn, vv, aList)
 *		void testDelete(rg, vv, aList)
 *
 *	METHODS THAT YOU HAVE TO IMPLEMENT: 
 *		int sizeOf(u) 	  -- Number of nodes in T(u)
 * 		int indexOf(k, u) -- Number of nodes with keys <= k
 *		Node get(i, u)	  -- Returns v s.t. i=indexOf(v.key, u)
 *		Node succ(u)	  -- Returns the successor of u (or null)
 *		Node pred(u)	  -- Returns the predecessor of u (or null)
 *
 *	We implemented this for you in MyBST.java:
 *		void testGetIndex(rg, mm, vv)
 *		void testSuccPred(rg, mm, vv)
 *
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
import util.MyQueue;

public class MyBST extends BST {

public ArrayList<BinNode> ArrayBST = new ArrayList<BinNode>();
////////////////////////////////////////////////////
// MEMBERS
////////////////////////////////////////////////////
   // no new members

////////////////////////////////////////////////////
// BASIC METHODS (implemented for you)
////////////////////////////////////////////////////
   // see BST.java

////////////////////////////////////////////////////
// METHODS that you need to implement:
////////////////////////////////////////////////////

   // sizeOf(u) = number of nodes in T(u)
   public int sizeOf(BinNode u){ 
       if (u == null) {
           return 0; 
       } else {
           return (sizeOf(u.left) + 1 + sizeOf(u.right));
       }
   }
   
   // indexOf(k, u) = number of nodes in T(u) whose keys are <= k
   public int indexOf(int k, BinNode u){
       ArrayBST.clear();
       sortBSTinArray(u);
       int i = 0;
       while (i < ArrayBST.size()){
           if (k >= ArrayBST.get(i).key){
               i++;
           } else {
               break;
           }
       }
       return i;
   }
   //method that just traverse the tree and increments count
   public void sortBSTinArray(BinNode u){
	   if (u == null) return;
	   sortBSTinArray(u.left);
           ArrayBST.add(u);
	   sortBSTinArray(u.right);
	  
	}//inorder
   
   
   // 	get(i, u) = node v such that i=indexOf(v.key, u) = i
   // 		get(i,u) IS THE INVERSE of index(k, u) !!
   public BinNode get(int i, BinNode u){
       ArrayBST.clear();
       sortBSTinArray(u);
       return ArrayBST.get(i - 1);
   }
   
   //  return true iff  (u == u.parent.left)
   public boolean isLeftChild(BinNode u){ 
       if (u == u.parent.left){return true;}
       return false; 
   }
   
   //  return true iff  (u == u.parent.right)
   public boolean isRightChild(BinNode u){ 
       if (u == u.parent.right){return true;}
       return false; 
   }
   
   // return null if u has no successor!
   public BinNode succ(BinNode u){ 

        if (u.key == max(root).key) return null;
        
        if (u.right != null) return min(u.right);
        
        BinNode p = u.parent;
        while (p != null && u == p.right){
            u = p;
            p = p.parent;
        }
        return p;
   }

   // return null if u has no predecessor!
   public BinNode pred(BinNode u){ 
       if (u.key == min(root).key) return null;
       if (u.left != null) return max(u.left);
       
       BinNode p = u.parent;
       while (p != null && u == p.left){
           u = p;
           p = p.parent;
       }
       
       return p;
   }

   // showTree(u) will display this info:
   //	(1) levels 0, 1, 2 of the tree T(u) (use nice visualization)
   //
   //       E.g.,                   U
   //                        _______|________ 
   //                       L                R
   //                   ____|____        ____|____
   //                  LL       LR      RL       RR
   //	(2) size of tree, height of tree
   //	(3) min and max key in tree
   //
   public void showTree(BinNode u){ 
   
       System.out.println("Printed level by level: ");
       
       int h = height(u); 
       
       for (int i = 0; i <=h; i++){
           if (i != 0) System.out.print("Level " + i + ": ");
           printLevels(u , i);
           System.out.println("");
       }
       
        int minKey = min(u).key;
        int maxKey = max(u).key;
        
       System.out.println("Min key in the tree is " + minKey + " and max key is " + maxKey + ".");
   }
   
   public void printLevels(BinNode u, int h){
       if (u == null) return;
       if (h == 1) System.out.print(" " + u.key);
       else {
           printLevels(u.left, h - 1);
           printLevels(u.right, h - 1);
       }
   }
   
   public int height(BinNode u){
       if (u == null) return 0;
       return 1 + Math.max(height(u.left), height(u.right));
   }

////////////////////////////////////////////////////
// TESTING METHODS
////////////////////////////////////////////////////
   // testing index
   public boolean checkGetIndex(BinNode u){
       if (u==null) return false;
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
// MAIN METHOD  -- DO NOT CHANGE THIS!
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

	System.out.println("\n\nTHIS IS OUR TREE----------------------");
	tt.showTree(tt.root);
	System.out.println("--------------------------------------");

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


