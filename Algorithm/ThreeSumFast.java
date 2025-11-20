import java.util.Arrays;

public class ThreeSumFast {

    /**
     * Faster ThreeSum using sorting + binary search.
     * Counts triples (i, j, k) with i < j < k and a[i] + a[j] + a[k] == 0.
     */
    public static int count(int[] a) {
        int n = a.length;
        int cnt = 0;

        // 1) sort the array
        Arrays.sort(a);

        // 2) for each pair (i, j), binary search for -(a[i] + a[j]) in (j+1 .. n-1)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int target = -a[i] - a[j];

                int k = Arrays.binarySearch(a, j + 1, n, target);
                if (k > j) { // found in the range (j+1..n-1)
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
