package BST;

public class MyQueue {
    //keep track of pointers for front and back of queue, qsize and create a list of tnodes for the queue
    private int front;
    public int back;
    private int qsize;
    private TNode[] queue;
    
    //constructor, initializse variables
    public MyQueue(int isize){
        qsize=isize;
        queue = new TNode[this.qsize];
        front=-1;
        back=-1;
    }
    //to add something to the queue --> enqueue
    public void add(TNode value) {
        //if we looped around the queue, and it is full, throw exception
        if ((back+1)%qsize==front) {
            throw new IllegalStateException("Error:Queue is full!!");
        } 
        //add queue to back --> queue is empty, add this is an elem
        else if (front == -1 && back == -1) {
            front++;
            back++;
            queue[back] = value;

        } 
        //formula for finding back is utilized to then add the value at desired index --> from lecture notes
        else {
            back=(back+1)%qsize;
            queue[back] = value;

        }
    }
    //to remove something from the queue
    public TNode remove() {
        //create new node with no right or left elems
        TNode value = new TNode(0, null, null);
        //if queue is empty, throw exception
        if (front == -1 && back == -1) {
            System.out.println("Queue is empty!");
            return null;
        } 
        else if (front == back) {
            value = queue[front];
            front = -1;
            back = -1;
        }
        else {
            value = queue[front];
            front=(front+1)%qsize;
        }
        return value;
    }
    //if queue is empty, then the front and back pointers will both = -1. if this is true, return true, else false.
    public boolean isempty() {
        return (front == -1 && back == -1);
    }
}
/*
References:
Lecture notes
http://cs.lmu.edu/~ray/notes/queues/
https://introcs.cs.princeton.edu/java/43stack/
*/
