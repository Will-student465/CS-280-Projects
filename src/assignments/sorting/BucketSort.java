package assignments.sorting;

public class BucketSort extends SortingAlgorithm<Integer> {
    

    public void sort(Integer[] array) {

        if (array == null || array.length <= 1) {
            return;
        }

        int min = array[0];

        int max = array[0];

        for (int i = 1; i < array.length; i++) {

            if (min > array[i]) min = array[i];

            if (max < array[i]) max = array[i];
            }

        int arraySize = max - min + 1;

        int[] buckets = new int[arraySize];

        // iterates each index("bucket") according to number of values in origional array
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

    


