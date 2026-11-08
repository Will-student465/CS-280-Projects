package assignments.sorting;

public class RadixSort<T extends Comparable<T>>  {
    
    public void Sort(int[] array) {

        int max = array[0];

        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }

        for (int place = 1; max / place > 0; place *= 10) {
            mainAlgorithm(array, place);
        }
    }

    private void mainAlgorithm (int[] array, int place) {
        int length = array.length;

        int[] output = new int[length];

        int[] count = new int[10];

        
        for (int i = 0; i < array.length; i++) { // counts each occurrence of a digit
            int digit = (array[i] / place) % 10;
            count[digit]++;
        }


        for (int i = 1; i < 10; i++) { // converts count into a cumulative count
            count[i] += count[i - 1];
        }


        for (int i = length - 1; i >= 0; i--) { // builds output array

            int digit = (array[i] / place) % 10;

            output[count[digit] - 1] = array[i];

            count[digit]--;
        }


        for (int i = 0; i < length; i++) {
            array[i] = output[i];
        }
    }
}
