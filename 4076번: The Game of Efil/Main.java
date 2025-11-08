import java.io.*;
import java.util.*;

public class Main {
	static class FastScanner {
		private final InputStream in;
		private final byte[] buffer = new byte[1 << 16];
		private int ptr = 0, len = 0;

		FastScanner(InputStream is) {this.in = is;}
		private int read() throws IOException {
			if (ptr >= len) {
				len = in.read(buffer);
				ptr = 0;
				if (len <= 0) return -1;
			}
			return buffer[ptr++];
		}
		
		int nextInt() throws IOException {
			int c, sgn = 1, x = 0;
			do {c = read();} while (c <= ' ');
			if (c == '-') { sgn = -1; c = read(); }
			while (c > ' ') {
				x = x * 10 + (c - '0');
				c = read();
			}
			return x * sgn;
		}
	}

	public static void main(String[] args) throws Exception {
		FastScanner fs = new FastScanner(System.in);
		StringBuilder out = new StringBuilder();

		int caseNo = 1;
		while (true) {
			int m = fs.nextInt();
			int n = fs.nextInt();
			if (m == 0 && n == 0) break;

			int k = fs.nextInt();
			int cells = m * n;

			int target = 0;
			for (int i = 0; i < k; i++) {
				int r = fs.nextInt();
				int c = fs.nextInt();
				int idx = r * n + c;
				target |= (1 << idx);
			}

			int[][] nei = new int[cells][8];
			for (int idx = 0; idx < cells; idx++) {
				int r = idx / n;
				int c = idx % n;
				int t = 0;
				for (int dr = -1; dr <= 1; dr++) {
					for (int dc = -1; dc <= 1; dc++) {
						if (dr == 0 && dc == 0) continue;
						int rr = r + dr;
						int cc = c + dc;
						if (rr < 0) rr += m;
						if (rr >= m) rr -= m;
						if (cc < 0) cc += n;
						if (cc >= n) cc -= n;
						nei[idx][t++] = rr * n + cc;
					}
				}
			}

			long count = 0;
			int limit = 1 << cells;

			for (int state = 0; state < limit; state++) {
				int next = 0;

				for (int idx = 0; idx < cells; idx++) {
					int cur = (state >>> idx) & 1;
					int s = 0;
					int[] nb = nei[idx];
					s += (state >>> nb[0]) & 1;
					s += (state >>> nb[1]) & 1;
					s += (state >>> nb[2]) & 1;
					s += (state >>> nb[3]) & 1;
					s += (state >>> nb[4]) & 1;
					s += (state >>> nb[5]) & 1;
					s += (state >>> nb[6]) & 1;
					s += (state >>> nb[7]) & 1;

					boolean aliveNext = (cur == 1) ? (s == 2 || s == 3) : (s == 3);
					if (aliveNext) next |= (1 << idx);
				}
				if (next == target) count++;
			}
			if (count == 0) {
				out.append("Case ").append(caseNo++).append(": Garden of Eden.\n");
			} else {
				out.append("Case ").append(caseNo++).append(": ").append(count).append(" possible ancestors.\n");
			}
		}
		System.out.print(out.toString());
	}
}
