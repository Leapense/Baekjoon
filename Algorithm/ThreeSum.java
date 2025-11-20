public class ThreeSum {

    /**
     * Count triples (i, j, k) with i < j < k and a[i] + a[j] + a[k] == 0.
     */
    public static int count(int[] a) {
        int n = a.length;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }
}
