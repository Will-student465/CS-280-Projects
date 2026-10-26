package assignments.ERAassignments.ERA3_DeQueuing;

import assignments.datastructures.PriorityDequeuer;

import assignments.datastructures.PriorityEnqueuer;

import assignments.datastructures.MinHeap;

import java.util.Random;


public class DeQueuingTests<T extends Comparable<T>> extends PriorityDequeuer<T>  {


    public static void main(String[] args) {
        System.out.println("NEW VERSION TEST");

        Random random = new Random();


        System.out.println("N,Runtime(ns)");


        for (int n = 1; n < 1000; n += 20)   {



            //PriorityDequeuer<Integer> queue = new PriorityDequeuer<>();
            PriorityEnqueuer<Integer> queue = new PriorityEnqueuer<>();
            //MinHeap<Integer> queue = new MinHeap<>();


            for (int l = 0; l < n; l++) {
                queue.enqueue(random.nextInt(n) + 1);
            }


            long start = System.nanoTime();

            for (int i = 0; i < 1; i++) {
                queue.dequeue();
            }


            long end = System.nanoTime();


            long totalTime = (end - start) / 100;

            System.out.println(n + "," + totalTime);
        }



    }
}

