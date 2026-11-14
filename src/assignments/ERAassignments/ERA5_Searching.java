package assignments.ERAassignments;

import java.util.Random;

import assignments.datastructures.HashSet;

import assignments.datastructures.VectorSet;

import assignments.datastructures.TreeSet;


public class ERA5_Searching<T extends Comparable<T>> extends VectorSet<T> {

    Random ran = new Random();

    public static void main(String[] args) {

        Random ran = new Random();

        for (int n = 40000; n < 40001; n += 1000) {

            //VectorSet<Integer> set = new VectorSet<>();
            //TreeSet<Integer> set = new TreeSet<>();
            HashSet<Integer> set = new HashSet<>();
            

            while (set.length() < n) {
                set.add(ran.nextInt(n) + 1);
            }

            long start = System.nanoTime();
            set.contains(n + 1);
            long end = System.nanoTime();

            System.out.println(n + "," + (end - start));
        }

    }
}
