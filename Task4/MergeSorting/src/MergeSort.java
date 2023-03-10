import java.util.Arrays;
import java.util.Random;

public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // find the middle point
            int middle = (left + right) / 2;
            // recursively sort the left and right halves
            mergeSort(arr, left, middle);
            mergeSort(arr, middle + 1, right);
            // merge the sorted halves
            merge(arr, left, middle, right);
        }
    }

    public static void merge(int[] arr, int left, int middle, int right) {
        // create temporary arrays for the left and right halves
        int[] leftArr = Arrays.copyOfRange(arr, left, middle + 1);
        int[] rightArr = Arrays.copyOfRange(arr, middle + 1, right + 1);
        // merge the two halves
        int i = 0, j = 0, k = left;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
        // copy any remaining elements from the left array
        while (i < leftArr.length) {
            arr[k++] = leftArr[i++];
        }
        // copy any remaining elements from the right array
        while (j < rightArr.length) {
            arr[k++] = rightArr[j++];
        }
    }

    public static void main(String[] args) {
        // generate a random array of length 10
        Random rand = new Random();
        int[] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10);
        }
        // print the original array
        System.out.println("Original array: " + Arrays.toString(arr));
        // sort the array using Merge Sort
        mergeSort(arr, 0, arr.length - 1);
        // print the sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
