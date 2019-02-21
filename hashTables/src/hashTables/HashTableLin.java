package hashTables;
import java.util.Random;

public class HashTableLin {
    private Integer[] table;
    private int size=0;
    private int numkeystored;
    private double lambda; //max allowed load factor
    
    //private int maxNum;
    
    public HashTableLin(int maxNum, double load){
        this.lambda = load;
        this.numkeystored = 0;
        this.size = nearestprimenum((int)(maxNum/load));
        table= new Integer[this.size]; 
        for(int i=0; i<size; i++){
            table[i]=0;
        }
        
    }
    public HashTableLin(int size, double load, HashTableLin none){
        this.lambda = load;
        this.numkeystored = none.numkeystored;
        this.size = size;
        this.table= new Integer[size]; //i need to set these all to nullsomehow...
        for(int i=0; i<size; i++){
            table[i]=0;
        }
    }
    private int nearestprimenum(int n) {
        //check if n is a multiple of 2
        int i=n;
        while(isPrime(i)==false){
            i++;
        }
        return i;
    } 
    private boolean isPrime(int n) {
        if (n%2==0) return false;
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    
    public void insert(int n){
        if(isIn(n)==false){
            //System.out.println((double)this.numkeystored/this.size);///System.out.println(lambda);//System.out.println();//if we are not within the allowable load range rehash. then, insert. 
            if(((double)this.numkeystored/((double)this.size-1))>lambda){
                rehash();
            }
            //calculate hash key
            int key = n%this.size;
            while(table[key]!=0){
                key++;
            }
            table[key] = n;
            numkeystored++;
        }
        else{
            System.out.println("Key already in table!");
        }
    }
    private void rehash(){
        Integer[] oldtable = this.table;
        int oldsize =this.size;
        int newnumstore = this.numkeystored;
        
        int newsize = nearestprimenum(2*this.size);
        this.size = newsize;
        //System.out.println("here");//'h
        
        HashTableLin largertable = new HashTableLin(newsize, this.lambda, this);
        for(int i=0; i<oldsize; i++){
            int key = oldtable[i]%this.size;
            while(largertable.table[key]!=0){
                key++;
            }
            largertable.table[key] = oldtable[i];
            this.numkeystored++;
        }
        this.table = largertable.table;
    }
    public boolean isIn(int n){
        int  hashVal=n%this.size;  
        while (table[hashVal]!= 0 ){
            if (table[hashVal]==n){  
                return true; // find elements  
            }  
            hashVal++;  
            hashVal=hashVal%size;  
        }  
        return false; // not found   
    }
    public void printKeys(){
        for(int i=0; i<size; i++){
            if(table[i]!=null){
                System.out.print(table[i]+", ");
            }
        }
    }
    public void printKeysAndIndexes(){
        for(int i=0; i<size; i++){
            if(table[i]!=null){
                System.out.println("Index: "+i+ "Key: "+table[i]);
            }
        }
    }
    public int getKeysStored(){
        return this.numkeystored;
    }
    public double lambda(){
        return this.lambda;
    }
    public int size(){
        return this.size;
    }
    public int probe(int num){
        int probes = 0;
        int count = num % this.size;
        //make sure its in there first!
        if(this.isIn(num) == true){
           return 0;
        }
        //if found break --> cause we are counting number of probes. if not, keep counting and probing!
        while(true){
            if(this.table[count] == 0){
                this.table[count] = num;
                this.numkeystored++;
                probes++;
                break;
            }
            else{
                probes++;
                count++;
                if(count == this.size - 1){
                    count = 0;
                }
            }
        }
        return probes;   
   }
    public void iterativeProbe(){
        for(double j = 0.1;j <= 0.9; j = j + 0.1){
            double avg = 0;
            //calculate theoretical aberage based on given formula
            double theoretical_avg = 0.5*(1+(1/(1-j)));
            //prepare for experiemental average
                HashTableLin temp = new HashTableLin(100000,j);
                for(int i=0;i<100000;i++){
                    Random rand = new Random();
                    int num= rand.nextInt(198000)+1;
                    avg += temp.probe(num);
                }
            avg = avg/(100000);
            System.out.println("At lamda = " + j);
            System.out.println("");
            System.out.println("experimental average: "+avg);
            System.out.println("theoretical average is "+theoretical_avg);
            System.out.println("------------------------------------------------------------------------");
        }
    }
}
