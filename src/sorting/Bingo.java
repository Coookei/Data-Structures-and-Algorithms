package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bingo {

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 2, 8, 5, 2, 1, 7, 1};
        System.out.println("Original array: " + Arrays.toString(arr));
        bingoSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    public static void bingoSort(int[] arr) {
        int n = arr.length;

        if (n <= 1)
            return;

        int boundaryIndex = -1; // used to track boundary between sorted and unsorted

        int minElement = arr[0];
        for(int i = 1; i < n; i++) {
            if(arr[i] < minElement) {
                minElement = arr[i];
            }
        }
        while (boundaryIndex < n - 1) {
            List<Integer> minIndices = new ArrayList<>();
            for (int i = boundaryIndex + 1; i < n; i++) {
                if (arr[i] == minElement) {
                    minIndices.add(i);
                }
            }

            for (int index : minIndices) {
                boundaryIndex++;
                swap(arr, index, boundaryIndex); // move all instances of smallest element to correct position
            }

            minElement = Integer.MAX_VALUE; // set a new big min value to compare against
            for(int i = boundaryIndex + 1; i < n; i++) {
                if(arr[i] < minElement)
                    minElement = arr[i];
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

}
