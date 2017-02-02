package graph;
/*
 * GraphTraversal.java
 *
 *	You have to write the dft and bft methods for this homework.
 *
 * Data Structures CS102
 * Professor Yap
 * Fall 2016
 *
 *************************************************** */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class GraphTraversal {

    public void dft(int adjMatrix[][], int source) {
      boolean markMatrix[] = new boolean[adjMatrix.length];
      clearMarks(markMatrix);
      Stack<Integer> stack = new Stack<>();
      stack.push(new Integer(source));

      while (!stack.isEmpty()){
        source = stack.pop();
        if (!markMatrix[source]){
          markMatrix[source] = true;
          System.out.print(source + " ");
          for(int i = adjMatrix.length - 1; i > 0; i--){
            if (adjMatrix[source][i] == 1) stack.push(new Integer(i));
          }
        }
      }
    }//dft

    public void clearMarks(boolean markMatrix[]){
      for (int i = 0; i < markMatrix.length; i++){
        markMatrix[i] = false;
      }
    }

    public void bft(int adjMatrix[][], int source) {
	     boolean markMatrix[] = new boolean[adjMatrix.length];
       clearMarks(markMatrix);
       LinkedList<Integer> queue = new LinkedList<>();
       int vertex;

       queue.addFirst(new Integer(source));
       markMatrix[source] = true;
       do{
         vertex = queue.removeLast();
         System.out.print(vertex + " ");
         for(int i = 0; i < adjMatrix.length; i++){
           if(adjMatrix[vertex][i] == 1 && !markMatrix[i]){
             queue.addFirst(new Integer(i));
             markMatrix[i] = true;
           }
         }
       } while(!queue.isEmpty());
    }// bft

    public static void main(String args[])
    {
        String path =  (args.length > 0 ? args[0]
			: "src/graph/graph8.txt");
        int    mode =  (args.length > 1 ? Integer.parseInt(args[1])
			: 0  );
        int  source = (args.length > 2  ? Integer.parseInt(args[2])
      : 0  );

        try {
            Scanner scanner = new Scanner(new File(path));
            int element = 0;
            int nNodes = 0;
            int adjMatrix[][] = null;
            while (scanner.hasNext()) {
                if (element == 0) {
                    nNodes = scanner.nextInt();
                    element++;
                } else {
                    adjMatrix = new int[nNodes][nNodes];
                    for (int i = 0; i < nNodes; i++) {
                        for (int j = 0; j < nNodes; j++) {
                            adjMatrix[i][j] = scanner.nextInt();
                        }
                    }
                }
            }
            scanner.close();

            GraphTraversal gt = new GraphTraversal();
            if (mode == 0) {
                System.out.println("depth first...");
                gt.dft(adjMatrix, source);
            } else {
                System.out.println("breadth first...");
                gt.bft(adjMatrix, source);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }//main
}//GraphTraversal
