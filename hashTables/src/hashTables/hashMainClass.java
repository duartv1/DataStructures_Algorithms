package hashTables;

public class hashMainClass {
    public static void main(String[] args) {
       int  maxnum= 5;  
       double loadfactor = 0.4;
       //size = maxnum/loadfactor-->maxnum = size*loadfactor
       
       HashTableLin hashTable= new  HashTableLin(maxnum, loadfactor);  
       hashTable.insert(10);  
       hashTable.insert(50000);  
       hashTable.insert(34567789);  
       hashTable.insert(9); 
       
       hashTable.printKeys();  
       System.out.println("");
       
       hashTable.insert(23); 
       hashTable.insert(765);  
       hashTable.insert(7777777); 
       
       hashTable.insert(12);  
       hashTable.insert(5);  
       hashTable.insert(777); 
       
       hashTable.printKeys();  
       int data = 12;//Find 12 
       System.out.println("");
       System.out.println("12 is in table? " + hashTable.isIn(data));
       hashTable.printKeysAndIndexes();
      
       HashTableLin tester = new HashTableLin(100000, 0.5);
       tester.iterativeProbe();
    }   
}
