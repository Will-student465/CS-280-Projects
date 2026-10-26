package assignments.ERAassignments;

import assignments.sorting.MergeSort;

import java.util.Random;

import java.util.ArrayList;

public class ERA4_Sorting<T extends Comparable<T>> extends MergeSort<Integer> {

    public static void main(String[] args) {

        Random random = new Random();

        ERA4_Sorting<Integer> sorter = new ERA4_Sorting<>();

        System.out.println("N,Runtime(ns)");

        for (int n = 1; n < 100; n += 2) {
            
            Integer[] array = new Integer[n];

            for (int l = 0; l < n; l++) {
                array[l] = random.nextInt() + 1;
            }


            long start = System.nanoTime();

            sorter.sort(array);

            long end = System.nanoTime();

            System.out.println(n + "," + (end - start));
        }
    }
    
}
