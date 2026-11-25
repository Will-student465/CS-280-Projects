package assignments.BonusAssignments;

import java.util.Random;


public class MonteCarloIntegration {

    public static void main(String[] args) {

        Random rand = new Random();

        int N = 1000;
        int hits = 0;

        double bullseye = 0.2;

        for (int i = 0; i < N; i++) {

            double x = -1 + 2 * rand.nextDouble();
            double y = -1 + 2 * rand.nextDouble();

            double pythagorean = x*x + y*y;

            if (pythagorean <= bullseye * bullseye) {
                hits++;
            }
        }

        double average = (double) hits / N;

        System.out.println("Darts thrown: " + N);
        System.out.println("Bullseye hit probability: " + average);



    }


    
}
