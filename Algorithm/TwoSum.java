public class TwoSum {

    /**
     * Count the number of pairs (i, j) with i < j such that a[i] + a[j] == 0.
     */
    public static int count(int[] a) {
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] + a[j] == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
