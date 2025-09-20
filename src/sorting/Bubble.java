package sorting;

import java.util.Arrays;

public class Bubble {

    public static void main(String[] args) {
        Bubble bubble = new Bubble();
        int[] array = {10, 4, 2, 1, 7, 5};

        long startTime = System.nanoTime();
        bubble.sort(array);
        long endTime = System.nanoTime();

        System.out.println("Bubble sort took " + (endTime - startTime) / 1_000_000 + "ms");
        System.out.println(Arrays.toString(array));
    }

    public void sort(int[] array) {
        // Having a sorted flag makes the best case time complexity O(n) instead of O(n^2)
        for(int i = array.length; i > 0; i--) {
            boolean sorted = true;
            for (int j = 1; j < i; j++) {
                if(array[j] < array[j-1]) {
                    int temp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = temp;
                    sorted = false;
                }
            }

            if(sorted)
                return;
        }
    }

}
