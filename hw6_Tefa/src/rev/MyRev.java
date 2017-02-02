package rev;

/*****************************************
 * MyRev.java
 *
 *	MyRev is just an extension of Rev.
 *
 *	You only provide the implementation of 2 methods:
 *
 *		Node iReverse(Node u);
 *		Node iReverse2(Node u);
 *
 *	Just use the main method of Rev.java.
 *
 * Chee Yap
 * Fall 2016, CS102
 *
 *****************************************/
import java.util.Random;
import util.MyList;

class MyRev extends Rev{

    /*****************************************
     * METHODS
     *****************************************/

    // iterative Reversal (cyclic version)
    Node iReverse2(Node u){
      // Scenario when the list is made by just two elements
      if (u.next.next == null){
        Node temp = u.next;
        u.next.next = u;
        u.next = null;
        u = temp;
      //General case scenario
      }else{
        Node []pp = new Node[3];
        pp[0] = u;
        pp[1] = u.next;
        pp[2] = u.next.next;
        pp[0].next = null;
        int i=0;
        while(true){
            pp[(i+1)%3].next = pp[i%3];
            pp[i%3] = pp[(i+2)%3].next;
            if (pp[i%3] == null){
                i++;
                pp[(i+1)%3].next = pp[i%3];
                break;
            }
            i++;
        }

        u = pp[(i+1)%3];
      }

	return u;
    }// iReverse

    // iterative Reversal (direct version)
    Node iReverse(Node u){
      // Scenario when the list is made by just two elements
      if (u.next.next == null){
        Node temp = u.next;
        u.next.next = u;
        u.next = null;
        u = temp;
      // General case scenario
      } else {
        Node p = u;
        Node q = u.next;
        Node r = u.next.next;

        u.next = null;
        p = u;

        while (true){
          q.next = p;
          p = q;
          q = r;

          if (r!= null) r = r.next;
          else {
            u = p;
            break;
          }
        }
      }
	return u;
    }// iReverse

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    public static void main (String[] args){
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	// nn is size of list to test
		int nn = (args.length>0)? Integer.parseInt(args[0]) : 10;
	// ss is seed for random number generator
		int ss = (args.length>1)? Integer.parseInt(args[1]) : 0;
	// mm is used in various ways (e.g., to show only first mm
	//	and last mm elements of the long list)
		int mm = (args.length>2)? Integer.parseInt(args[2]) : 4;
	// vv is the verbosity -- use it for your debugging...
		int vv = (args.length>3)? Integer.parseInt(args[3]) : 0;
	// tt is the test we want to run:
	//	tt=4 is the most important, as it constructs our table
		int tt = (args.length>4)? Integer.parseInt(args[4]) : 0;

	Random rg = new Random();

	MyRev rev = new MyRev();
	rev.vv = vv;	 // set verbosity globally

	// Generate random list and show it:
	Node u = rev.randomList(nn, rg);

    if (tt==0) {// no timing -- just basic operations
	rev.showList(u,mm,mm,"Original list:");

	// Reverse list and show it:
	u = rev.reverse(u);
	rev.showList(u,mm,mm,"Recursively Reversed list:");

	// iReverse list and show it:
	u = rev.iReverse(u);
	rev.showList(u,mm,mm, "Iteratively Reversed list:");

	// iReverse2 list and show it:
	u = rev.iReverse2(u);
	rev.showList(u,mm,mm, "Iteratively Reversed (version2) list:");

	return;
    }

    if (tt==1){
	timeRec(rev, u);
	timeIter(rev, u);
	timeIter2(rev, u);
    }

    if (tt==2){
	timeRec(rev, u);
    }

    if (tt==3){
	timeIter(rev, u);
    }

    if (tt==4){		// Constructing a timing table
			// nn is determined by a command line argument
			// but we fix these here:
	int inc = 200;	// inc = size of increment
	int kk = 20;	// kk  = number of increments
	timeTable(rev, rg, nn, inc, kk);
    }

    }//main
}
