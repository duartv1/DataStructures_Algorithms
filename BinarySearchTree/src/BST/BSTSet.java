package BST;

public class BSTSet{
    private TNode root;
    //when the set is empty, root =null.
    public BSTSet(){
        this.root=null;
    }
    //Constructor for the set - turns a given array into a binary search tree
    public BSTSet(int[] input){
        //call the private void finction of BSTSet
        BSTSet(input);
    }
    //the private constructor for this set --> in seperate function because we call it three times. 
    //Once from public constructor, and twice for the left and right sides of the tree 
    private void BSTSet(int[] input){ 
        //splits array into twos and finds the middle of these arrays.
        //then, we put it the input into a tree
        int midpoint;
        int len = input.length;
        //if empty array, we have an empty tree --> return nothing
        if(len == 0){
            return;
        }
        //no midpoint if we only have element.
        else if(len == 1){
            midpoint = 0;
        }
        //find midpoint. if odd number, right half has one extra element.
        else{
            midpoint = (len / 2);
        }
        //we will copy our array into var copyy, and add midpoint to our tree
        int[] deepCopy = sort(input);
        this.add(deepCopy[midpoint]);
        //create left and right side sets
        int [] left = new int [midpoint];
        int [] right = new int [deepCopy.length-(midpoint+1)];
        //populate our left array and right arrays
        int i=0;
        for(i=0; i<left.length;i++){
            left [i] = deepCopy[i];
        }
        for(int j=0; j<right.length;j++){
            right[j] = deepCopy[i+1];
            i++;
        }
        //get left and right side by calling the public function again
        BSTSet(left);
        BSTSet(right);
    }
    //will check if elelemnt bv is in the tree. we call findTree (referenced from lecture slides)
    public boolean isIn(int v){
        return (this.findTree(v,this.root) != -1); // returns true if element is in the tree
    }
    private int findTree(int x, TNode t){
        //if empty, return -1
        if(t == null){
            return -1;
        }
        //find if element is on left or right side of tree, and then recursively call this function until found
        if(x < t.element){
            return(findTree(x,t.left));
        }
        if(x>t.element){
            return(findTree(x,t.right));
        }else{
            //if after recursion not found, we return false - not in tree
            return (t.element);
        }
    }
    
    public void add (int v){
        //if the number is not already in the tree, add it in, using inserTree function, referenced from lecture
        if(!this.isIn(v)){
            this.root = insertTree(v,this.root);
        }
    }
    private TNode insertTree (int v, TNode t){
        //create new node if no next node, otherwise recursively find which side of the tree to add onto and where.
        if (t == null){
            t = new TNode(v,null,null);
        }else if(v < t.element){
            t.left = insertTree (v, t.left);
        }else if (v > t.element){
            t.right = insertTree(v,t.right);
        }
        return t;
    }
   
    public boolean remove (int v){
        //check if the number is in the tree
        if(this.isIn(v)){
            //if so, delete it and return true to say it was removed
            this.root = deleteTree(v, this.root);
            return true;
        }else{
            //if not in tree, return false to say it was not added in.
            return false;
        }
    }
    private TNode deleteTree (int v, TNode t){ 
        //traverse left side of this subtree if v is less than t
        if (v <t.element){
            t.left = deleteTree(v,t.left);
        }
        //traverse right side of this subtree if v is greater than t
        else if(v > t.element){
            t.right = deleteTree (v, t.right);
        }//if we are at the desired number, find the smaller num --> left of subtree, and replace the right with the number and remove it
        else if (t.left != null && t.right != null){
            t.element = (findMin(t.right)).element;
            t.right = removeMin(t.right);
        }
        else{
            //if only onee child 
            if ((t.left != null)==true){
                t=t.left;
            }
            else{
                t=t.right;
            }
        }
        return t;
    }
    private TNode removeMin(TNode t){
        if (t.left != null){
            t.left= removeMin(t.left);
            return t;
        }else{
            return t.right;
        }
    }
    //find smallest number (will be on the left side of the tree).
    private TNode findMin(TNode t){
        if(t.left !=null){
            t.left = findMin(t.left);
        }
            return t;
    }
    //takes a set s and unionizes it with current set (this) --> no repeats, as it is a set!
    public BSTSet union(BSTSet s){
        BSTSet union = new BSTSet();
        
        int i = 0;
        //copy whole this tree into union, and then all of s --> repeated numbers are dealt with in deepcopy function
        union.deepcopy(this.root, union);
        union.deepcopy(s.root, union);
        
        //we will turn our union into an array 
        int [] orderedarr= new int[union.size()];
        
        //create a pointer node to use to traverse tree
        TNode pointer= union.root;
        toArray(pointer, orderedarr, i);
        sort(orderedarr);
        BSTSet unionized = new BSTSet(orderedarr);
        return unionized;
    }
    private void deepcopy(TNode t, BSTSet s){
        //copy nothing if t is null
        if(t == null){
            return;
        } 
        else {
            //add t into s
            s.add(t.element); 
            //copy left subtree
            if(t.left != null){
                deepcopy(t.left,s);
            }
            //copy right subtree
            if(t.right != null){
                deepcopy(t.right,s);
            }
        }
    }
    
    public BSTSet intersection (BSTSet s){
        BSTSet intersect = new BSTSet();
        TNode thisroot = this.root;
        intersection(thisroot, s, intersect);
        
        TNode intersectroot = intersect.root;
        int [] sorted = new int[intersect.size()];
        int index = 0;
        toArray(intersectroot,sorted,index);
        sort(sorted);
        BSTSet interfinal = new BSTSet (sorted);
        return interfinal;
    }
    
    private TNode intersection(TNode t, BSTSet s,BSTSet inter){
        //if our node is not null and our number is in both sets (intersects)
        //if so, add to inter set --> the intersection set
        if(t != null && s.isIn(t.element)){ 
            inter.add(t.element);
        }
        if(t.left!=null){
            t.left = intersection(t.left,s,inter);
        }
        if(t.right != null){
            t.right = intersection(t.right,s,inter);
        }
        return t;
    }
    //returns the size of our tree (referenced from lecture notes)
    public int size(){
        return size(this.root);
    }
    //if we have an empty tree, return zero. If full, add 1 for the root, and left and right sides recursively.
    private int size(TNode t){
        if(t == null){
            return 0;
        }else{
            return (1 + size(t.left) + size(t.right));
        }
    }
    //returns the height of the tree. if no height, return -1 to indicate this.
    public int height(){
        if(this.root == null){
            return -1;
        }
        return height(this.root);
    }
    //if not height, return -1, otherwise create seperate height variables for left and right side --> take bigger one
    private int height(TNode t){ 
        if(t == null){
            return -1;
        }
        int leftheight = height(t.left);
        int rightheight = height(t.right);
        
        if(leftheight >rightheight){
            return (leftheight+1);
        }else{
            return (leftheight+1);
        }
    }
    public  boolean isEmptyTree(){
        return(root ==null);
    }
    public void printBSTSet(){
        if(this.root==null){
            System.out.println("The set is empty");
        }else{
            System.out.print("The set elements are: ");
            printBSTSet(root);
            System.out.print("\n");
        }
    }
    private void printBSTSet (TNode t){
        if(t!=null){
            printBSTSet(t.left);
            System.out.print(" "+t.element+", ");
            printBSTSet(t.right);
        }
    }
    public void printNonRec(){
        //create new stack, and also tnode that will start at root
        MyStack stack = new MyStack(this.size());
        TNode t = this.root;
        //while we are still encountering nodes, push on t and then change to the left most
        while(t!= null){
            stack.push(t);
            t = t.left;
        }
        //while stack has size bigger than 1
        while (stack.getSize() > 0){
            //remove top item on stack
            t = stack.pop();
            System.out.println(t.element + " ");
            //if right side is not null, set next t as rightmost
            if(t.right != null){
                t = t.right;
            //push all of t onto stack until none left, and move left.
            while(t != null){
                stack.push(t);
                t = t.left;
            }
        }
    }
    }
    //make into array
    private  int toArray (TNode t , int [] array, int i){
        //return -1 to signal that t is null
        if(t == null){
            return -1;
        }
        //iterate through BSTSet left side
        if(t.left != null){
            i = toArray(t.left,array,i);
        }
        //add root to middle element
        array[i++] = t.element;
        //add right side of tree into array
        if(t.right != null){
            i = toArray(t.right,array,i);
        }
        //return the array
        return i;
    }
    //sort the input array arr
    private int[] sort(int[] arr){
        int i=0;
        boolean running = false;
        //create a copy of our array (will be input array)
        int[] deepCopy = new int[arr.length];
        for(i=0;i<arr.length;i++){
            deepCopy[i]=arr[i];
        }
        //loop where we will be foing the sorting
        while(running!=true){
            running = true;
            //if we come across a larger element, on the left, switch them
            for(i=1;i<deepCopy.length;i++){
                if(deepCopy[i-1]>deepCopy[i]){
                    running = false;
                    int x = deepCopy[i];
                    deepCopy[i]=deepCopy[i-1];
                    deepCopy[i-1]=x;
                }
            }
        }
        return deepCopy;
    }
}
/*
References:
Lecture notes 
http://pages.cs.wisc.edu/~vernon/cs367/notes/9.BST.html
https://algs4.cs.princeton.edu/32bst/
http://btechsmartclass.com/DS/U5_T1.html
*/

