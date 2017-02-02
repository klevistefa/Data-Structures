/* MyStack.java
 * 	Trivial implementation of Generic Stack
 *		using Generic ArrayList:
 *
 *	We only have three methods:
 *		--push
 *		--push
 *		--isEmpty
 *
 * Chee Yap
 * Data Structure Course, Fall 2016
 *****************************************/
package util;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class MyStack<E> extends ArrayList<E> {
   public void push(E item) {
       add(item);
   }
   public E pop() {
       return remove(size()-1);
   }

   public boolean isEmpty(){
       return super.isEmpty();
   }
   // MAIN:
   public static void main(String[] args){
	   MyStack<Integer> si = new MyStack<Integer>();
	   si.push(3);
	   si.push(1);
	   si.push(4);
	   si.push(1);
	   si.push(5);
	   si.push(9);
	   System.out.println("Original Stack:");
	   for (Integer i: si)
		   System.out.println(i);
	   si.pop();
	   si.pop();
	   si.pop();
	   System.out.println("After popping three times:");
	   for (Integer i: si)
		   System.out.println(i);
	   System.out.printf("Check:           is Stack empty now? %s\n",
		   si.isEmpty());
	   si.pop();
	   si.pop();
	   si.pop();
	   System.out.printf("Pop thrice more: is Stack empty now? %s\n",
		   si.isEmpty());


   }//main
}

