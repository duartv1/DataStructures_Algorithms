package BST;

public class BSTTestClass {
    public static void main(String[] args) {
        BSTSet set = new BSTSet();
        set.printBSTSet();
        set.add(29);
        //duplicates:
        set.add(3);
        set.add(3);
        set.add(22);
        set.add(67);
        set.add(1);
        set.add(9);
        set.printBSTSet();
        set.remove(4);
        set.remove(22);
        set.printBSTSet();
        System.out.println("********************************");
        System.out.println("Is 56 is in the set?");
        System.out.println(set.isIn(56));
        System.out.println("********************************");
        System.out.println("Is 3 is in the set?");
        System.out.println(set.isIn(3));
        System.out.println("********************************");
        BSTSet set2 = new BSTSet();
        //duplicates:
        set2.add(34);
        set2.add(35);
        set2.add(2);
        set2.add(7);
        System.out.println("Union with set 1 and 2");
        System.out.print("Set 1: ");
        set.printBSTSet();
        System.out.print("Set 2: ");
        set2.printBSTSet();
        BSTSet union = new BSTSet();
        union = set.union(set2);
        union.printBSTSet();
        System.out.println("********************************");
        BSTSet inter = new BSTSet();
        inter = set.intersection(set2);
        inter.printBSTSet();
        System.out.println("********************************");
        
        System.out.println("Size of set 1 is: "+set.size());
        System.out.println("height of set 1 is: "+set.height());
        System.out.println("********************************");
        System.out.println("********************************Print set 1 non rec: ");
        set2.printNonRec();
} 
}
