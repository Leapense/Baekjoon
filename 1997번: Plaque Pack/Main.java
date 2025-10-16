import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.math.*;

public class Main {
    static class Plaque {
        int h;
        int[] colMask;   // per column bitmask (bottom bit = y=0)
        int[] topBit;    // highest set bit per column, -1 if empty
        Plaque(int w, int h) { this.h = h; this.colMask = new int[w]; this.topBit = new int[w]; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(nextNonEmptyLine(br));
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        // Current box state
        BitSet[] boxCols = new BitSet[w];
        for (int j = 0; j < w; j++) boxCols[j] = new BitSet();
        int currHeight = 0;

        ArrayList<Integer> resultHeights = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int h = Integer.parseInt(nextNonEmptyLine(br).trim());
            Plaque p = new Plaque(w, h);

            // Read h lines: input is top -> bottom. Map to bottom-based y = h-1-rowIdx.
            char[][] rows = new char[h][];
            for (int r = 0; r < h; r++) {
                String line = nextShapeLine(br, w);
                rows[r] = line.toCharArray();
            }

            // Build per-column bitmask; LSB is bottom row y=0.
            for (int rTop = 0; rTop < h; rTop++) {
                int y = h - 1 - rTop; // bottom-based
                char[] row = rows[rTop];
                for (int j = 0; j < w; j++) {
                    if (row[j] == 'X') {
                        p.colMask[j] |= (1 << y);
                    }
                }
            }
            // Precompute topBit per column
            for (int j = 0; j < w; j++) {
                int m = p.colMask[j];
                if (m == 0) p.topBit[j] = -1;
                else p.topBit[j] = 31 - Integer.numberOfLeadingZeros(m);
            }

            // Try to place in current box
            int k = findShift(p.colMask, boxCols, currHeight);
            int predicted = predictHeight(currHeight, p.topBit, k);

            if (predicted > b) {
                // close current box
                if (currHeight > 0) resultHeights.add(currHeight);
                // new box
                for (int j = 0; j < w; j++) boxCols[j].clear();
                currHeight = 0;

                // place into empty box (k will be 0)
                k = findShift(p.colMask, boxCols, currHeight);
                predicted = predictHeight(currHeight, p.topBit, k);
            }

            // Apply plaque to the box
            applyPlaque(p.colMask, boxCols, k);
            currHeight = predicted;
        }

        // last box
        if (currHeight > 0) resultHeights.add(currHeight);

        // Output
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < resultHeights.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(resultHeights.get(i));
        }
        System.out.println(sb.toString());
    }

    // Read next non-empty line (for the first line with n w b etc.)
    private static String nextNonEmptyLine(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null) {
            if (!s.trim().isEmpty()) return s;
        }
        return "";
    }

    // Read exactly one shape line of length >= w (trim trailing spaces)
    private static String nextShapeLine(BufferedReader br, int w) throws IOException {
        while (true) {
            String s = br.readLine();
            if (s == null) return "";
            s = s.trim();
            if (s.length() >= w) return s.substring(0, w);
        }
    }

    // Find minimal k >= 0 such that no (X) overlaps occur.
    private static int findShift(int[] mask, BitSet[] cols, int currentHeight) {
        int w = mask.length;
        // It is guaranteed to find a valid k at latest k == currentHeight
        for (int k = 0; k <= currentHeight; k++) {
            boolean ok = true;
            for (int j = 0; j < w && ok; j++) {
                int m = mask[j];
                if (m == 0) continue;
                int bits = m;
                while (bits != 0) {
                    int lowbit = bits & -bits;
                    int y = Integer.numberOfTrailingZeros(lowbit);
                    if (cols[j].get(y + k)) { ok = false; break; }
                    bits ^= lowbit;
                }
            }
            if (ok) return k;
        }
        // Should never reach here; included for completeness.
        return currentHeight;
    }

    // Predicts new height after placing the plaque at shift k (without mutating)
    private static int predictHeight(int currentHeight, int[] topBit, int k) {
        int predicted = currentHeight;
        for (int j = 0; j < topBit.length; j++) {
            int tb = topBit[j];
            if (tb >= 0) {
                int colTopAfter = k + tb + 1;
                if (colTopAfter > predicted) predicted = colTopAfter;
            }
        }
        return predicted;
    }

    // Apply (write) the shifted plaque into the box columns
    private static void applyPlaque(int[] mask, BitSet[] cols, int k) {
        int w = mask.length;
        for (int j = 0; j < w; j++) {
            int m = mask[j];
            if (m == 0) continue;
            int bits = m;
            while (bits != 0) {
                int lowbit = bits & -bits;
                int y = Integer.numberOfTrailingZeros(lowbit);
                cols[j].set(y + k);
                bits ^= lowbit;
            }
        }
    }
}