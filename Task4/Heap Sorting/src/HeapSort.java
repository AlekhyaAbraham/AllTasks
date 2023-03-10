import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    public static void heapSort(int[] arr) {
        // build a max heap from the array
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }
        // repeatedly extract the maximum element and move it to the end
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int n, int i) {
        int largest = i; // initialize the largest element as the root
        int left = 2 * i + 1; // left child index
        int right = 2 * i + 2; // right child index
        // check if the left child is larger than the root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        // check if the right child is larger than the root
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        // swap the root with the largest element if necessary
        if (largest != i) {
            swap(arr, i, largest);
            // recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // generate a random array of length 10
        Random rand = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(100);
        }
        // print the original array
        System.out.println("Original array: " + Arrays.toString(arr));
        // sort the array using Heap Sort
        heapSort(arr);
        // print the sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}

