import java.util.ArrayList;

public class AddAndRemove {
    public static void main(String[] args) {
        // Create an ArrayList
        ArrayList<String> list = new ArrayList<>();

        // Add elements to the ArrayList
        list.add("apple");
        list.add("banana");
        list.add("orange");

        // Print the ArrayList
        System.out.println("Before adding and removing element: " + list);

        // Add an element at index 1
        list.add(1, "grape");
        System.out.println("After Adding: " + list);

        // Remove the element at index 2
        list.remove(2);
        System.out.println("After removing: " + list);

        // Print the updated ArrayList
        System.out.println("Update Array List: " + list);
    }
}
