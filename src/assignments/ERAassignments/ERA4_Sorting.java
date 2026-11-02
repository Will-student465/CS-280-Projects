package assignments.ERAassignments;

import assignments.sorting.BubbleSort;

import java.util.Random;

import java.util.ArrayList;

public class ERA4_Sorting<T extends Comparable<T>> extends BubbleSort<Integer> {

    public static void main(String[] args) {

        Random random = new Random();

        ERA4_Sorting<Integer> sorter = new ERA4_Sorting<>();

        System.out.println("N,Runtime(ns)");

        for (int n = 100; n < 1000; n += 10) {
            
            Integer[] array = new Integer[n];

            for (int l = 0; l < n; l++) {
                array[l] = random.nextInt(n) + 1;
            }


            long start = System.nanoTime();

            sorter.sort(array);

            long end = System.nanoTime();

            System.out.println(n + "," + (end - start));
        }
    }
    
}
