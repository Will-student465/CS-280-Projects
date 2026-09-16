package assignments.ERAassignments.ERA1_Fibonacci;

import java.util.Arrays;


public class Main {


    public static void main(String[] args) {

        for (int n = 1; n <= 100; n += 1) {
        long start = System.nanoTime();
        Recursive(n); 
        long end = System.nanoTime();
        double duration = (end - start) / 1e9;
        System.out.println(n + "\t" + duration);
    }
}


    public static long fibonacci(int n)
    {
        if( n <= 1) {
            return 1;
        }
        

        long last = 1;
        long nextToLast = 1;
        long answer = 1;

        for( long i = 2; i <= n; i++){
            answer = last + nextToLast;
            nextToLast = last;
            last = answer;
        }
        return answer;


    }


    public static double Binet(int n) {
        double front = (1 + Math.sqrt(5)) / 2;
        double back = (1 - Math.sqrt(5)) / 2;
        double answer = (Math.pow(front, n) - Math.pow(back, n)) / Math.sqrt(5);
        return answer;

    }


    public static int Recursive(int n) {
        int[] memory = new int[n + 1];
        Arrays.fill(memory, -1);
        return Recursive(n, memory);
    }



    public static int Recursive(int n, int[] memory) {

        
        if (memory[n] != -1) {
            return memory[n];
        }
        if (n < 2) {
            memory[n] = n;
            return memory[n];
        }

        memory[n] = Recursive(n - 1, memory) + Recursive(n - 2, memory);
        return memory[n];
    }




}
