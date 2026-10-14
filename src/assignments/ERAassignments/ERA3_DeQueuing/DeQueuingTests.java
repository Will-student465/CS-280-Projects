package assignments.ERAassignments.ERA3_DeQueuing;

import assignments.datastructures.PriorityDequeuer;


import java.util.Random;


public class DeQueuingTests<T extends Comparable<T>> extends PriorityDequeuer<T> {
    
    public static void main(String[] args) {


        Random random = new Random();


        System.out.println("N,Runtime(ns)");


        for (int n = 1; n <= 100000000; n += 100000) {


            long totalTime = 0;


            for (int trial = 0; trial < 100; trial++) {


                PriorityDequeuer<Integer> queue = new PriorityDequeuer<>();
                //PriorityEnqueuer<Integer> queue = new PriorityEnqueuer<>();


                for (int i = 0; i < n; i++) {
                    queue.enqueue(random.nextInt(n) + 1);
                }


                long start = System.nanoTime();


                
                queue.dequeue();


                long end = System.nanoTime();


                totalTime += (end - start);
            }



            System.out.println(n + "," + totalTime);
        }
    }
}



