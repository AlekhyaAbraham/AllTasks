import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // choose the pivot element
            int pivot = arr[high];
            // partition the array around the pivot
            int pivotIndex = partition(arr, low, high, pivot);
            // recursively sort the left and right subarrays
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high, int pivot) {
        int left = low;
        int right = high - 1;
        while (true) {
            // find element on left that should be on right
            while (left <= right && arr[left] < pivot) {
                left++;
            }
            // find element on right that should be on left
            while (left <= right && arr[right] > pivot) {
                right--;
            }
            // if the indices have crossed, partitioning is complete
            if (left >= right) {
                break;
            }
            // swap elements and move indices
            swap(arr, left, right);
            left++;
            right--;
        }
        // move pivot to its final position
        swap(arr, left, high);
        return left;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // generate a random array of length 10
        Random rand = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10);
        }
        // print the original array
        System.out.println("Original array: " + Arrays.toString(arr));
        // sort the array using Quick Sort
        quickSort(arr, 0, arr.length - 1);
        // print the sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
