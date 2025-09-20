package sorting;

import java.util.Arrays;

public class Selection  {

    public static void main(String[] args) {
        Selection selection = new Selection();
        int[] array = {10, 4, 2, 1, 7, 5};

        long startTime = System.nanoTime();
        selection.sort(array);
        long endTime = System.nanoTime();

        System.out.println("Selection sort took " + (endTime - startTime) / 1_000_000 + "ms");
        System.out.println(Arrays.toString(array));
    }

    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) { // -1 as last element sorted automatically

            int minIndex = i;
            // find min element in unsorted part
            for(int j=i + 1; j < array.length; j++) { // i + 1 as i element already considered min before the iteration
                if(array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // swap smallest with current i index
            if(minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }

}
