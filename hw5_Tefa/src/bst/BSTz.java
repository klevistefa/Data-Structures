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
 * 	   BinaryNode search(int k) -- Returns a node u that contains
 * 					k, or u is the immediate 
 * 					predecessor or successor of k.
 * 				 	This means we can insert k as
 * 					the left or right child of u
 * 	   void inorder()	 -- Returns the in-order list of keys 
 * 					in the BST.
 * 	   Node min(Node u)	 -- Returns the minimum node below u
 * 	   Node max(Node u)	 -- Returns the maximum node below u
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

import java.io.FileNotFoundException;
import java.util.Random;
import java.io.IOException;
import java.util.ArrayList;
import zoombinis.NewZoombinis;

@SuppressWarnings("unchecked")
public class BSTz <T extends Comparable<? super T>> {

	private class BinNode <T extends Comparable<? super T>> {
		T key;
		BinNode<T> left=null, right=null, parent=null;

		//Constructor
		BinNode(T k) { key=k;
		}
	}//class

	////////////////////////////////////////////////////
	// MEMBERS
	////////////////////////////////////////////////////
                BinNode<T> root = null;
                int size = 0;
                int count = 0;
                
        public BinNode<T> search(T k){
	   count++;
	   if (root == null) return null;
	   BinNode<T> u = root;
	   
	   while (u.key.compareTo(k) != 0) { //invariant: u is non-null
	      count++;
	      if (u.key.compareTo(k) > 0) {
	          if (u.left == null) return u;
	          else u=u.left;
	      } else {
	          if (u.right == null) return u;
	          else u=u.right;
	      }
	   }
	   return u;
	}//search
        
        public boolean add(T k){
	   	count++;
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
	   BinNode<T> v = new BinNode<T>(k);
	   if (u.key.compareTo(k) > 0) {
	       u.left = v;
	       u.left.parent = u;}
	   else {
	       u.right = v;
	       u.right.parent = u;}
	   return true;
	}//add
        
        
        public void inorder(BinNode<T> u){
	   count++;
	   if (u == null) return;
	   System.out.printf("(");
	   inorder(u.left);
	   System.out.print(u.key);
	   inorder(u.right);
	   System.out.printf(")");
	}//inorder
        public void inorder(){ inorder(root); System.out.println(); }
	   	
	// min(u): 
	public BinNode<T> min(BinNode<T> u) {
	      count++;
	      if (u==null) return null;
	      while (u.left != null) {
	          count++;
	          u = u.left;
	      }
	      return u;
	}//min

	// max(u): 
	public BinNode<T> max(BinNode<T> u) {
	      count++;
	      if (u==null) return null;
	      while (u.right != null) {
	          count++;
	          u = u.right;
	      }
	      return u;
	}//max
        
        public boolean delete(T k){
	   BinNode<T> u = search(k);
	   if (u == null || u.key.compareTo(k) != 0) return false;
	   delete(u);
	   return true;
	}//delete(key)
        
        public void delete(BinNode<T> u){
	   BinNode<T> v = u; // v is the node we want to cut!
	   if (u.left != null && u.right != null) {
	   	  v = min(u.right);
	      u.key = v.key;
	   }
	   cut(v);
	   size--;
	}//delete(node)
        
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
        
        public int sizeOf(BinNode<T> u){ return -1; }
   
       // indexOf(k, u) = number of nodes in T(u) whose keys are <= k
       public int indexOf(T k, BinNode<T> u){ return -1; }
       public int indexOf(T k) { return indexOf(k, root); }

       // inverse of index(k, u):
       // 	get(i, u) = node v such that i=indexOf(v.key, u) = i
       public BinNode<T> get(int i, BinNode<T> u){ return null; }
       public BinNode<T> get(int i) {return get(i, root);}

    ////////////////////////////////////////////////////
    // TESTING METHODS
    ////////////////////////////////////////////////////
       // randomNode(rg): returns a random node in BST
       public BinNode<T> randomNode(Random rg){
           if (root==null) return null;
           return get(1+rg.nextInt(size));
       }//randomNode

       // testing index
       public boolean testGetIndex(Random rg){
            BinNode<T> u = randomNode(rg);
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
                    System.out.print("   DIAG: root=" + root.key + ", size=" + size +"\n");
               if (verbose>2 && root.left!=null)
                    System.out.print("   DIAG: right=" + root.right.key + ",\n");
                            System.out.printf("   DIAG: tree="); inorder();
               }
            }
       }//diag

       // testAdd(rg, nn, vv, aList)
        @SuppressWarnings("BoxingBoxedValue")
       public void testAddInteger(Random rg, BSTz<T> bstz, int nn, int vv, ArrayList<Integer> aList) {
            count=0;
      
            for (int i=0; i<nn; i++){
                    Integer a = rg.nextInt(2*nn);
                    if (bstz.add((T) a)){
                            System.out.printf("+%s; ", a);
                            if (a%2 == 1) 
                                aList.add(new Integer(a));
                    } else
                            System.out.printf("=%s; ", a);
            }
            diag("\n ========= BST after " + nn + " random adds:\n", vv);
            diag("\n ========= random adds count = " + count, vv);
       }//testAddInteger
       
       public void testAddString(Random rg, BSTz<T> bstz, NewZoombinis zz, int nn, int vv, ArrayList<String> aList) {
            count=0;
      
            for (int i=0; i<nn; i++){
                    String a = zz.randomName();
                    if (bstz.add((T) a)){
                            System.out.printf("+%s; ", a);
                            aList.add(a);
                    } else
                            System.out.printf("=%s; ", a);
            }
            diag("\n ========= BST after " + nn + " random adds:\n", vv);
            diag("\n ========= random adds count = " + count, vv);
       }//testAdd

       // testDelete(rg, aList)
       public void testDeleteInteger(Random rg, int vv, BSTz<T> bstz, ArrayList<Integer> aList){
            count=0;
            while (aList.size()>0){
                int i = rg.nextInt(aList.size());
                Integer k = aList.remove(i);
                if (bstz.delete((T) k)) {
                    diag("deleted " + k + ": ", 2);
                } else
                    diag("Failure!",1); // should not happen
            }
            diag("\n ======== After random removals: ",vv);
            diag("\n ========= random removals count = " + count, vv);
       }//testDelete
       
       public void testDeleteString(Random rg, int vv, BSTz<T> bstz, ArrayList<String> aList){
            count=0;
            while (aList.size()>0){
                int i = rg.nextInt(aList.size());
                String k = aList.remove(i);
                if (bstz.delete((T) k)) {
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
      public static void main(String[] args) throws FileNotFoundException {
            
            
            
            int nn = (args.length>0)? Integer.parseInt(args[0]) : 10;
            @SuppressWarnings("UnusedAssignment")
            int ss = (args.length>1)? ss = Integer.parseInt(args[1]) : 0;
            int mm = (args.length>2)? Integer.parseInt(args[2]) : 0;
            int vv = (args.length>3)? Integer.parseInt(args[3]) : 1;
            

            @SuppressWarnings("UnusedAssignment")
            Random rg = (ss==0)? rg = new Random() : new Random(ss);

            System.out.printf("=======> BSTz: nn=%d, ss=%d, mm=%d, vv=%d\n\n",
                                            nn, ss, mm, vv);
            
            if (mm == 0){
                ArrayList<Integer> aList = new ArrayList<>();
                BSTz<Integer> tt = new BSTz<Integer>();
                tt.testAddInteger(rg, tt, nn, vv, aList);
                tt.testDeleteInteger(rg, vv, tt, aList);
            } else if (mm == 1){
                ArrayList<String> aList = new ArrayList<>();
                BSTz<String> tt = new BSTz<String>();
                
                NewZoombinis zz = new NewZoombinis(ss, nn, 0, "src/zoombinis");
                tt.testAddString(rg, tt, zz, nn, vv, aList);
                tt.testDeleteString(rg, vv, tt, aList);
            }
      }
      
}//class BSTz
