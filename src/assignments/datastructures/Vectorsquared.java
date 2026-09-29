package assignments.datastructures;

import java.util.Vector;

public class Vectorsquared {
    public static void main(String[] args) {
        int[] sizes = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

        System.out.println("N, TotalCreationTime(s)");

        for (int N : sizes) {
            long start = System.nanoTime();

            Vector<Integer> Vector= new Vector<>();
            for (int i = 0; i < N; i++) {
                Vector.add(0, i); 
            }

            long end = System.nanoTime();
            double totalSeconds = (end - start) / 1e9;

            System.out.printf("%d, %.6f%n", N, totalSeconds);
        }
    }
}
