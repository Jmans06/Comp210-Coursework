package assn03;

// Starter Code provided with Assignment #3 for COMP210 Fall 2024
// The given main method has some examples of how to create and modify the linked lists
// It contains suggestions on how to test your code after completing the TBD Tasks

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList<Integer>();
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(30);
        System.out.println("list = " + list.toString());
        System.out.println("size of list = " + list.size());
        System.out.println("list contains 10?: " + list.contains(10));     // implemented
        System.out.println("list contains 50?: " + list.contains(50));
        System.out.println("set element at index 2 to be 10");
        list.set(2, 10);
        System.out.println("get element at index 2 = " + list.get(2));
        System.out.println("list = " + list.toString());
        System.out.println("Last Index of element 10 in list = " + list.lastIndexOf(10));

        list.remove(20);
        System.out.println("list after removing 20 = " + list.toString());

        System.out.println("index of '30' = " + list.indexOf(30));


        // Test task 1
        //System.out.println("List before removing = "+list.toString());
        list.removeAtIndex(1);  // TBD
        System.out.println("Task 1: list after removing element at index 1 = " + list.toString());

        // Test task 2
        LinkedList list2 = new LinkedList();
        list2.add(20);
        list2.add(30);
        System.out.println("list2 = " + list2.toString());
        System.out.println("Task 2: list == list2 ?: " + list.isEqual(list2)); // not yet implemented

        // Test task 3
        list.add(30);
        list.add(30);
        list.add(30);
        list.add(40);
        list.add(50);
        System.out.println("list before removing repeats = " + list.toString());
        list.removeRepeats();
        System.out.println("Task 3: list after removing repeats = " + list.toString());

        // Test task 4
        list.clear();
        System.out.println("List before reverse = "+list.toString());
        list.reverse(2);
        System.out.println("List after reverse = "+list.toString());
        // Test task 5

        list.merge(list2);
        System.out.println("Task 5: List after merge = "+list.toString());

    }
}
