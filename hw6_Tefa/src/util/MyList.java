package util;

/*****************************************
 * MyList.java
 *	-- Basic singly linked list utility
 *	-- Should make this generic
 *
 * Chee Yap
 * Fall 2016, Data Structure class
 *
 *****************************************/
import java.util.Random;

public class MyList{

    public class Node {
		public int val;
		public Node next;
		Node(int v){ val=v; }  //constructor
    }

    /*****************************************
     * MEMBERS
     *****************************************/
    public int vv = 0;	//verbosity

    /*****************************************
     * METHODS
     *****************************************/


    // Generate a random list of Integers of size nn:
    //
    public Node randomList(int nn, Random rg){
	Node u = new Node(rg.nextInt(nn));
	Node v = u;
	for (int i=1; i<nn; i++){
	    v.next = new Node(rg.nextInt(2*nn)); // generate numbers <2*nn 
	    v = v.next;
	}
	v.next = null;
	return u;
    }

    public int size(Node u){
	int n=0;
	while (u!=null && n++<10000)
	    u=u.next;
	if (n==10000) // safety check for infinite loop
	    System.err.println("list longer than 10000");
	return n;
    }//

    // reverse a list (return the new head)
    public Node reverse(Node u){
	if (u==null || u.next==null) return u;
	Node v=u.next;
	Node w=reverse(u.next);
	v.next = u;
	u.next = null; 	// redundant -- to get rid of it, use inner call
	return w;
    }//reverse

    /*****************************************
     * Various "show" methods are useful for debugging:
     *   Some (not all) have a verbosity parameter (vv):
     *
     *   	vv<0 means show nothing
     *   	vv=0 means show minimal
     *   	vv>0 means show more
     *****************************************/
    // Show list (prepend with message):
    public void showList(Node u, String msg){
	if (msg !=null)
		System.out.printf("%s\n  " + msg);
	while (u!=null){
		System.out.printf("%d, ", u.val);
		u = u.next;
	}
	System.out.printf("\n");
    }//showList
    public void showList(Node u, String msg, int vv){
	if (vv>=0) showList(u,msg);
    }

    // Show first m nodes:
    public void showList(Node u, int m, String msg){
	if (msg !=null)
		System.out.printf("%s \n  ", msg);

	while (m-- >0 && u!=null){
		System.out.printf(" %d ", u.val);
		u = u.next;
	}
    }//showList
    public void showList(Node u, int m, String msg, int vv){
	if (vv>=0) showList(u, m, msg);
    }

    // Show first m nodes and last n nodes:
    public void showList(Node u, int m, int n, String msg){
	if (msg !=null)
		System.out.printf("%s \n  ", msg);

	while (m-- >0 && u!=null){
		System.out.printf(" %d ", u.val);
		u = u.next;
	}
	if (u==null){
	    System.out.printf("\n");
	    return;
	}
	// else, we did print exactly m nodes
	int rest = 0;

	// count length of the rest of list
	Node v=u;
	while (v!=null) {
	    rest++;
	    v=v.next;
	}

	// determine how many items to omit:
	rest = (rest > n) ? rest-n : 0;
	if (rest >0)
		System.out.printf("...(%d omitted)...", rest);

	//omit "rest" many items
	for (int i=0; i<rest; i++)
	    u = u.next;

	// show the last <=n items:
	while (u!=null) {
		System.out.printf(" %d ", u.val);
		u = u.next;
	}
	System.out.printf("\n");
    }//showList
    public void showList(Node u, int m, int n, String msg, int vv){
	if (vv>=0) showList(u, m, n, msg);
    }

    // plain printing
    public void showMsg(String msg){
	if (msg!=null) System.out.println(msg);
    }
    public void showMsg(String msg, int vv){
	if (vv>=0 && msg!=null) System.out.println(msg);
    }


    public double min(double a, double b){
	return (a<b)? a: b;
    }
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static void main (String[] args){
	//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	int nn = (args.length>0)? Integer.parseInt(args[0]) : 10;
	int vv = (args.length>1)? Integer.parseInt(args[1]) : 0;

	// if vv<0, then do not show

	Random rg = new Random();

	MyList mL = new MyList();
	mL.vv = vv; // set verbosity globally

	// Generate random list and show it:
	Node u = mL.randomList(nn, rg);
	mL.showMsg("List, shown with m=3:",vv);
	mL.showList(u,3, 3, "Original list",vv);

	// Reverse list and show it:
	u = mL.reverse(u);
	mL.showMsg("Reversed List",vv);
	mL.showMsg("List, shown with m=3:",vv);
	mL.showList(u,3, 3, "Original list",vv);

    }//main



    
}
