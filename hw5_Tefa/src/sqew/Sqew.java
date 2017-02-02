/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqew;

/**
 *
 * @author Klevis
 */
public class Sqew
        implements SqewInterface {
    
    String name;
    int sq [];
    int tos = 0;
    int bos = 0;

    public Sqew (String name, int MAXSIZE){
      sq = new int[MAXSIZE];
      this.name = name;
    }

    public int pop(){
        int toReturn = sq[tos];
        tos = (tos + 1) % sq.length;
        return toReturn;
    }

    public void enqueue(int k){
      if (!isFull()){
        bos = (bos + 1)%sq.length;
        sq[bos] = k;
      }
    }

    public void push(int k){
      if (!isFull()){
        tos = (tos + 1)%sq.length;
        sq[tos] = k;
      }
    }

    public int dequeue(){
      return pop();
    }

    public boolean isFull(){
      return (tos == (bos + 1)%5);
    }

    public boolean isEmpty(){
      return (tos == bos);
    }
    
    public void init(){
        this.tos = 0;
        this.bos = 0;
    }
    
}
