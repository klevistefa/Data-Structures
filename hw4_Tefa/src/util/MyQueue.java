/* MyQueue.java
 * 	Trivial implementation of Generic Queue
 *		using Generic ArrayList:
 *
 *	We only have three methods:
 *		--enqueue
 *		--dequeue
 *		--isEmpty
 *
 * Chee Yap
 * Data Structure Course, Fall 2016
 *****************************************/
package util;

import java.util.ArrayList;

public class MyQueue<E> extends ArrayList<E> {
   public void enqueue(E item) {
       add(item); }

   public E dequeue() {
       return remove(0); }

   public boolean isEmpty(){
       return super.isEmpty(); }

   //////////////////////////////////////////////////
   // MAIN:
   //////////////////////////////////////////////////
   public static void main(String[] args){
	   MyQueue<Integer> qi = new MyQueue<Integer>();
	   qi.enqueue(3);
	   qi.enqueue(1);
	   qi.enqueue(4);
	   qi.enqueue(1);
	   qi.enqueue(5);
	   qi.enqueue(9);
	   System.out.println("Original Queue:");
	   for (Integer i: qi)
		   System.out.println(i);
	   qi.dequeue();
	   qi.dequeue();
	   qi.dequeue();
	   System.out.println("After dequeueing three times:");
	   for (Integer i: qi)
		   System.out.println(i);
	   System.out.printf("Check:           is Queue empty now? %s\n",
		   qi.isEmpty());
	   qi.dequeue();
	   qi.dequeue();
	   qi.dequeue();
	   System.out.printf("Dequeue thrice more: is Queue empty now? %s\n",
		   qi.isEmpty());

   }//main
}

