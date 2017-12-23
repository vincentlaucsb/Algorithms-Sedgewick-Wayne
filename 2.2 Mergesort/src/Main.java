import java.util.concurrent.ThreadLocalRandom;

public class Main {
    private static Integer[] _testData(int n) {
        Integer[] ret = new Integer[n];

        for (int i = 0; i < n; i++) {
            // Random int from 0-999 (inclusive)
            ret[i] = ThreadLocalRandom.current().nextInt(0, 1000);
        }

        return ret;
    }

    public static void main(String[] args) {
        System.out.println("Execution speed test");

        Integer[] base_arr = _testData(50000);
        Integer[] arr1 = base_arr;
        Integer[] arr2 = base_arr;

        long start_time = System.currentTimeMillis();
        Merge.sort(arr1);
        long end_time = System.currentTimeMillis();

        System.out.println(end_time - start_time);

        start_time = System.currentTimeMillis();
        ImprovedMerge.sort(arr2);
        end_time = System.currentTimeMillis();

        System.out.println(end_time - start_time);
    }
}
