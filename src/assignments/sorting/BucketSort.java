package assignments.sorting;

public class BucketSort extends SortingAlgorithm<Integer> {
    

    /**
     * Sorts an array by counting each element into an array of "buckets" to be dumped back into the array in sorted order
     * 
     * @param array the array of integers to be sorted
     */
    public void sort(Integer[] array) {

        if (array == null || array.length <= 1) { // checks if array can be sorted
            return;
        }

        int min = array[0]; 

        int max = array[0];

        for (int i = 1; i < array.length; i++) {

            if (min > array[i]) min = array[i];

            if (max < array[i]) max = array[i];
            }

        int arraySize = max - min + 1;

        int[] buckets = new int[arraySize]; // allocates an array of "Buckets" with each index being a valuebucket between min and max

        // iterates each index("bucket") according to number of values in original array
        for (int i = 0; i < array.length; i++) {

            buckets[array[i] - min]++;
        }

        int index = 0;

        // pushes all elements out of buckets in sorted order
        for (int i = 0; i < arraySize; i++) {

            while (buckets[i] > 0) {

                array[index] = i + min;

                index++;

                buckets[i]--;
            }
        }
    }


    /**
     * Run validation texts.
     * @param args comman line args
     */
    public static void main(String[] args) {
        SortingAlgorithm.validate (new BucketSort());
        System.out.println("BucketSort has passed all tests."); 
    }
}

    


