import java.io.*;
import java.util.*;

public class Main {
    static final double LOG2_INV = 1.0 / Math.log(2.0);
    static final double TOL = 0.005 + 1e-12;
    
    static double log2(double x) {
        return Math.log(x) * LOG2_INV;
    }
    
    static double theoreticalEntropy(double p, int m) {
        double h = 0.0;
        if (p > 0) h -= p * log2(p);
        double q = 1.0 - p;
        if (q > 0) {
            double r = q / (m - 1);
            h -= q * log2(r);
        }
        
        return h;
    }
    
    static double entropyFromCounts(int[] counts, int L) {
        double h = 0.0;
        for (int c : counts) {
            if (c <= 0) continue;
            double p = (double) c / (double) L;
            h -= p * log2(p);
        }
        
        return h;
    }
    
    static int[] evenDist(int L, int m) {
        int[] c  = new int[m];
        int base = L / m;
        int rem = L % m;
        for (int i = 0; i < m; i++) {
            c[i] = base + (i < rem ? 1 : 0);
        }
        return c;
    }
    
    static int[] makeCounts(int L, int m, int c0) {
        int[] c = new int[m];
        c[0] = c0;
        int remaining = L - c0;
        int base = remaining / (m - 1);
        int rem = remaining % (m - 1);
        
        for (int i = 1; i < m; i++) {
            c[i] = base + (i - 1 < rem ? 1 : 0);
        }
        
        return c;
    }
    
    static char[] buildPool63() {
        StringBuilder sb = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ch++) sb.append(ch);
        for (char ch = 'a'; ch <= 'z'; ch++) sb.append(ch);
        for (char ch = 'A'; ch <= 'Z'; ch++) sb.append(ch);
        sb.append('.');
        return sb.toString().toCharArray();
    }
    
    static char[] charsFor(int m, char[] pool63) {
        if (m <= 63) {
            char[] a = new char[m];
            System.arraycopy(pool63, 0, a, 0, m);
            return a;
        } else {
            char[] a = new char[64];
            a[0] = pool63[0];
            a[1] = ' ';
            for (int i = 2; i < 64; i++) {
                a[i] = pool63[i - 1];
            }
            
            return a;
        }
    }
    
    static String buildString(int[] counts, char[] chars) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < counts.length; i++) {
            for (int k = 0; k < counts[i]; k++) sb.append(chars[i]);
        }
        return sb.toString();
    }
    
    static double minEntropyAllPositive(int L, int m) {
        int[] c = new int[m];
        c[0] = L - (m - 1);
        for (int i = 1; i < m; i++) c[i] = 1;
        return entropyFromCounts(c, L);
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null) return;
        double target = Double.parseDouble(line.trim());
        char[] pool63 = buildPool63();
        
        if (Math.abs(target - 0.0) <= TOL) {
            System.out.print("0");
            return;
        }
        
        int minM = Math.max(2, (int) Math.ceil(Math.pow(2.0, target - 0.005)));
        String answer = null;
        
        for (int phase = 1; phase <= 2 && answer == null; phase++) {
            for (int L = 1000; L >= 1 && answer == null; L--) {
                int maxM = Math.min(64, L);
                
                int mStart, mEnd;
                if (phase == 1) {
                    if (L < minM) continue;
                    mStart = minM;
                    mEnd = Math.min(maxM, minM + 20);
                } else {
                    mStart = 1;
                    mEnd = maxM;
                }
                
                for (int m = mStart; m <= mEnd && answer == null; m++) {
                    if (m == 1) {
                        continue;
                    }
                    
                    double maxH = log2(m);
                    if (maxH < target - TOL) continue;
                    
                    double minH = minEntropyAllPositive(L, m);
                    if (minH > target + TOL) continue;
                    
                    int[] uni = evenDist(L, m);
                    double hu = entropyFromCounts(uni, L);
                    if (Math.abs(hu - target) <= TOL) {
                        char[] chars = charsFor(m, pool63);
                        answer = buildString(uni, chars);
                        break;
                    }
                    
                    double lo = 1.0 / m;
                    double hi = (double) (L - (m - 1)) / (double) L;
                    
                    if (hi < lo) continue;
                    for (int it = 0; it < 80; it++) {
                        double mid = (lo + hi) * 0.5;
                        double hm = theoreticalEntropy(mid, m);
                        if (hm > target) lo = mid;
                        else hi = mid;
                    }
                    
                    int baseC0 = (int) Math.round(hi * L);
                    baseC0 = Math.max(1, Math.min(baseC0, L - (m - 1)));
                    
                    for (int d = -8; d <= 8; d++) {
                        int c0 = baseC0 + d;
                        if (c0 < 1 || c0 > L - (m - 1)) continue;
                        
                        int[] cnt = makeCounts(L, m, c0);
                        double h = entropyFromCounts(cnt, L);
                        if (Math.abs(h - target) <= TOL) {
                            char[] chars = charsFor(m, pool63);
                            answer = buildString(cnt, chars);
                            break;
                        }
                    }
                }
            }
        }
        
        if (answer == null) {
            int L = 64;
            int m = 64;
            
            int[] cnt = evenDist(L, m);
            char[] chars = charsFor(m, pool63);
            answer = buildString(cnt, chars);
        }
        
        System.out.print(answer);
    }
}