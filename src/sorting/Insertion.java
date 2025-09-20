package sorting;

import java.util.Arrays;

public class Insertion  {

    public static void main(String[] args) {
        Insertion insertion = new Insertion();
        int[] array = {10, 13,7,3, 6,5,4,3,2};

        long startTime = System.nanoTime();
        insertion.sort(array);
        long endTime = System.nanoTime();

        System.out.println("Insertion sort took " + (endTime - startTime) / 1_000_000 + "ms");
        System.out.println(Arrays.toString(array));
    }

    public void sort(int[] array) {
        // Best case: already sorted O(n) only 1 comparison per element
        // Worst case: O(n^2) each element shifts all previous elements

        // Loop through unsorted array and take first item each time
        for(int i = 1; i < array.length ; i++)  { // start from 1 as otherwise no items to compare to
            // Shift any larger items to right
            int current = array[i];

            int j = i;
            while(j > 0 && array[j-1] > current) {
                array[j]  = array[j-1];
                j--;
            }
            array[j] = current;
        }
    }

}
