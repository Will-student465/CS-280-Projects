package assignments.algorithms;
import assignments.sorting.InsertionSort;
import assignments.sorting.SortingAlgorithm;
public class ERADemo {


    public static void main(String[] args) {

    





        for (int n = 10; n < 20000; n += 100) {
            Integer[] array = randomArray(n);
            InsertionSort<Integer> sorter = new InsertionSort<>();
            long start = System.nanoTime();
            sorter.sort(array);
            long end = System.nanoTime();
            double duration = (end-start)/1e9;
            System.out.println(n + "\t" + duration);
        }
    }

        private static Integer[] randomArray(int N) {
            Integer[] array = new Integer[N];
            for (int i = 0; i < array.length; i++) {
                array[i] = (int)(Math.random() * N);
            }
            return array;
        }
    }



//* 
// worst case O(N^2)s
// Best case: O(N)
// Average: (N^k)
// T(N) = C * N^K + o(N^K)
// I want:: Y = m - x + b  
// 
// Properties of LOGARITHMS::::::::::::::::::::::
// y = b^x  <-> x = log (base b) y
// Addition formula: log(x*y) = log(x) + log(y)
// Change of Base:: log(base b)(x) = logx/logb
// Exponent Formula:: log(x^k) = klogx 



//BEFORE MONDAY:: ADD






