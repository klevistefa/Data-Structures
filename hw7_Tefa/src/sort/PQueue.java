package sort;
//package pqueue;

import java.util.Random;


//file: PQueue.java
//
//	Simple implementation of a (max) Priority Queue.
//
//	Priorities are ints, and Queue is an array.
//

//	Members:
//		int[] queue;
//		int size = 0;
//		int MAX; //maximum value of size
//
//	Methods:
//		int dequeue() 			-- removes the max priority item
//							already in the tree)
//		void enqueue(int k) 		-- addes item with priority k
//
//	Usage:
//		> java PQueue [n=10 [seed=0]]
//
//		This creates a PQueue and enqueues it with
//			n random numbers in [0,n).
//
//		Then it dequeues it until it is empty.
//
//
//	EXTENSIONS:
//		(0) Introduce error checking
//		(1) Allow automatic doubling of MAX
//		(2) Allow automatic halfing of MAX
//		(3) Do an application with items=(key,data)
//
//
//Data Structures (CS 102)
//Professor Yap
//Fall 2016
//


public class PQueue extends Sort {

	////////////////////////////////////////////////////
	// members:
	////////////////////////////////////////////////////
	int		size=0;		// 0 <= size < nn

//	int[]		qq;
//	int		MAX=10;		// 0 <= size <= MAX

	////////////////////////////////////////////////////
	// Constructors:
	////////////////////////////////////////////////////
/*
	public PQueue(int n){
		if (n==0) MAX=10;	// default size
		else MAX=n;
		qq=new int[MAX];
	}
	public PQueue(){
		qq=new int[MAX];
	}
*/

	////////////////////////////////////////////////////
	// Methods:
	////////////////////////////////////////////////////
	boolean isFull(){
		return (size==nn);
	}
	boolean isEmpty(){
		return (size==0);
	}
	void enqueue(int k){
		reheapUp(k);
	}
	int dequeue(){
		int tmp=getA(0); 		// item of max priority
//reheapDown(qq[--size]);
		reheapDown(--size);
		return tmp;
	}
	 int left(int i){
		return(2*i+1);
	}
	 int right(int i){
		return(2*i+2);
	}
	 int parent(int i){
		if (i==0) return 0;
		return (i-1)/2;
	}
	 boolean leaf(int i){
		return (left(i)>=size);
	}

	void dynamicSort(){
		sort();
	}

	 void reheapUp(int k){
		int i=size++;
//		while (i>0 && k>qq[parent(i)]){
		while (i>0 && compareTo(parent(i), k)<0){
//			qq[i]=qq[parent(i)];
			moveTo(parent(i),i);
			i=parent(i);
		}
//		qq[i]=k;
		setA(i,k);
	}//reheapUp

	 void reheapDown(int k){
		int i = 0;	// i is initially the root
		int j;		// j is the child of i to be compared

		while (!leaf(i)){
//			if (right(i)<size && qq[right(i)]>qq[left(i)])
			if (right(i)<size && compare(right(i),left(i))>0)
				j=right(i);
			else
				j=left(i);
//			if (k< qq[j]) {
			if (compareTo(j,k)>0) {
//				qq[i]=qq[j];
				moveTo(j,i);
				i=j;
			} else {
//				qq[i]=k;
				setA(i,k);
				return;
			}
		}//while

//		qq[i]=k;
		setA(i,k);
	}//reheapDown

	////////////////////////////////////////////////////
	// MAIN METHOD:
	////////////////////////////////////////////////////

	public static void main(String[] args) {

	        getParams(args);

		Random rg2 = (ss==0)? new Random(): new Random(ss);

		PQueue pq = new PQueue();

		showTwo("Priority Queue!\n", "====> Start random enqueues:");

		// n random additions:
		for (int i=0; i<nn; i++){
		    	int a = rg2.nextInt(3*nn);
			pq.enqueue(a);
			System.out.printf("Enqueued %d; ", a);
		}
		showOne("\n ====> After n random enqueues:\n");
		showAA(mm);
		showOne("\n ====> Start dequeues:\n");

		// n removals:
		for (int i=0; i<nn; i++){
			System.out.printf("\nDequeued %d; ", getA(0));
			pq.dequeue();
		}
		showOne("\n ====> After n dequeues:\n");
		showAA(mm);
	}//main
}//PQueue
