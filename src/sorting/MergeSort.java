package sorting;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] array = { 100, 1, 6, 32, 1, 35, 5, 6, 53 ,3,1,1,1};

        long startTime = System.nanoTime();
        mergeSort(array);
        long endTime = System.nanoTime();

        System.out.println("Merge sort took " + (endTime - startTime) / 1_000_000 + "ms");
        System.out.println(Arrays.toString(array));
    }

    public static void mergeSort(int[] array) {
        // Base case
        if(array.length < 2) {
            return;
        }

        // Divide
        int n = array.length;
        int middle = n / 2;

        int[] leftArray = new int[middle];
        for(int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }

        int[] rightArray = new int[(n - middle)];
        for(int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }

        mergeSort(leftArray);
        mergeSort(rightArray);

        // Conquer
        merge(leftArray, rightArray, array);
    }

    private static void merge(int[] left, int[] right, int[] result) {
        int i = 0, j = 0, k = 0;

        // iterate through both until one is empty
        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while(i < left.length)
            result[k++] = left[i++];

        while(j < right.length)
            result[k++] = right[j++];
    }

}
