import java.util.ArrayList;
import java.util.LinkedList;

       public class LinkedtoArray {
        public static void main(String[] args) {
            // Create a LinkedList
            LinkedList<String> linkedList = new LinkedList<>();
            linkedList.add("apple");
            linkedList.add("banana");
            linkedList.add("orange");

            // Print the LinkedList
            System.out.println("Linked List: " + linkedList);

            // Convert the LinkedList to an ArrayList
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.addAll(linkedList);

            // Print the ArrayList
            System.out.println("Array List: " + arrayList);
        }
    }
