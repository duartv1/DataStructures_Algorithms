package BST;

public class MyStack {
    private TNode [] elements;
    private int top = -1;
    //constructor
    public MyStack(int t){
        this.elements = new TNode[t];
    }
    //returns the size of the stack
    public int getSize(){
        return (this.top +1);
    }
    //adds item onto top of stack
    public void push(TNode t){
        //if our stack is full, copy the array and double the size, then add in the old array
        //in the larger new array --> covered last sem in 2SH4, followed notes from Dr. Dumitrescu
        if(top == this.elements.length -1){
            TNode [] newArray = new TNode[2*this.elements.length];
        
        for(int i = 0; i< elements.length;i++){
            newArray[i] = elements[i];
        }this.elements = newArray;
        }
        elements[++top] = t;   
    }
    //
    public TNode pop() throws ArrayIndexOutOfBoundsException{
       if(isEmpty()){
           System.out.println("Empty!");
           return null;
       }
       else{
           //set the array at where it used to be as null, then return the element we popped
           TNode tee = this.elements[top];
           elements[top--] = null;
           return (tee);
       }
    }
    //will be empty if bvariable top is equal to 0 or -1!!
    public boolean isEmpty(){
        return (top<0);
    }
}

    