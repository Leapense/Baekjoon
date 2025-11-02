import java.io.*;
import java.util.*;

public class Main {
	static final int MAX_N = (1 << 15) - 1;
	static final int MAX_ROOT = (int)Math.sqrt(MAX_N);
	static final int WIDTH = MAX_ROOT + 1;

	static int[] cumByFirstLE;

	static int[] pairSum;
	static int[] pairJ;
	static int pairCount;

	static void precompute() {
		int[] sq = new int[WIDTH];
		for (int i = 0; i < WIDTH; i++) sq[i] = i * i;

		int countPairs = 0;
		for (int i = 0; i <= MAX_ROOT; i++) {
			int si = sq[i];
			for (int j = 0; j <= i; j++) {
				int s = si + sq[j];
				if (s <= MAX_N) countPairs++;
			}
		}
		pairCount = countPairs;
		pairSum = new int[pairCount];
		pairJ = new int[pairCount];

		int[] cnt = new int[(MAX_N + 1) * WIDTH];

		int idx = 0;
		for (int i = 0; i <= MAX_ROOT; i++) {
			int si = sq[i];
			for (int j = 0; j <= i; j++) {
				int s = si + sq[j];
				if (s <= MAX_N) {
					pairSum[idx] = s;
					pairJ[idx] = j;
					idx++;
					cnt[s * WIDTH + i]++;
				}
			}
		}

		for (int s = 0; s <= MAX_N; s++) {
			int base = s * WIDTH;
			for (int i = 1; i <= MAX_ROOT; i++) {
				cnt[base + i] += cnt[base + i - 1];
			}
		}
		cumByFirstLE = cnt;
	}

	static long solveOne(int n) {
		long ans = 0L;
		for (int k = 0; k < pairCount; k++) {
			int s1 = pairSum[k];
			if (s1 > n) continue;
			int s2 = n - s1;
			int j1 = pairJ[k];
			ans += cumByFirstLE[s2 * WIDTH + j1];
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		precompute();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder out = new StringBuilder();
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (line.isEmpty()) continue;
			int n = Integer.parseInt(line);
			if (n == 0) break;
			out.append(solveOne(n)).append('\n');
		}
		System.out.print(out.toString());
	}
}
