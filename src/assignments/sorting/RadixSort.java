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

        
        for (int i = 0; i < array.length; i++) {
            int digit = (array[i] / place) % 10;
            count[digit]++;
        }
    }
}
