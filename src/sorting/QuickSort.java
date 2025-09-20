package sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] array = {5, 1, 2, 3, 5, 6, 7, 8, 9};

        long startTime = System.nanoTime();
        quickSort(array);
        long endTime = System.nanoTime();

        System.out.println("Quick sort took " + (endTime - startTime) / 1_000_000 + "ms");
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }


    private static int choosePivot(int[] array, int start, int end) {
        // return index of pivot
        return new Random().nextInt(end - start + 1) + start;
    }

    // sample x random elements from the subarray
    // return the median of these x elements
    private static int medianOfX(int[] array, int start, int end, int x) {
        if(x==1) {
            return new Random().nextInt(end - start + 1) + start;

        } else {
            int[] samples = new int[x];
            for(int i = 0; i < x; i++) {
                samples[i] = new Random().nextInt(end - start + 1) + start;
            }

            // Find the median without sorting the full array
            for (int i = 0; i <= x / 2; i++) {
                int minIndex = i;
                for (int j = i + 1; j < x; j++) {
                    if (array[samples[j]] < array[samples[minIndex]]) {
                        minIndex = j;
                    }
                }
                // Swap found min with the current position
                int temp = samples[i];
                samples[i] = samples[minIndex];
                samples[minIndex] = temp;
            }

            return samples[x / 2];
        }
    }


    private static void quickSort(int[] array, int start, int end) {
        if(start >= end) {
            return;
        }

        // Choose pivot, use either medianOfX or choosePivot
        int pivotIndex = medianOfX(array, start, end, 5);

        // Partition the left and right side of the array
        pivotIndex = partition(array, start, end, pivotIndex);

        // Recursively sorts array until done
        quickSort(array, start, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end, int pivotIndex) {
        int temp = array[start];
        array[start] = array[pivotIndex];
        array[pivotIndex] = temp;

        int pivot = array[start];

        int left = start + 1;
        int right = end;

        while(left <= right) {
            while(left <= right && array[left] <= pivot) {
                left++;
            }

            while(left <= right && array[right] > pivot) {
                right--;
            }

            if(left < right) {
                temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        temp = array[start];
        array[start] = array[right];
        array[right] = temp;

        return right;
    }

}