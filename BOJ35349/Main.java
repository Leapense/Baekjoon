import java.io.*;
import java.util.*;

public class Main {

	static class FastScanner {

		private final InputStream in = System.in;
		private final byte[] buffer = new byte[1 << 16];
		private int ptr = 0,
			len = 0;

		private int read() throws IOException {
			if (ptr >= len) {
				len = in.read(buffer);
				ptr = 0;
				if (len <= 0) return -1;
			}

			return buffer[ptr++];
		}

		int nextInt() throws IOException {
			int c;
			do {
				c = read();
			} while (c <= ' ');

			int sign = 1;
			if (c == '-') {
				sign = -1;
				c = read();
			}

			int val = 0;
			while (c > ' ') {
				val = val * 10 + (c - '0');
				c = read();
			}
			return val * sign;
		}
	}

	public static void main(String[] args) throws Exception {
		FastScanner fs = new FastScanner();

		int N = fs.nextInt();
		int K = fs.nextInt();

		int[] arr = new int[N];
		int oddCount = 0;
		int evenCount = 0;

		for (int i = 0; i < N; i++) {
			arr[i] = fs.nextInt();
			if ((arr[i] & 1) == 1) oddCount++;
			else evenCount++;
		}

		int[] odd = new int[oddCount];
		int[] even = new int[evenCount];

		int oi = 0,
			ei = 0;
		for (int x : arr) {
			if ((x & 1) == 1) odd[oi++] = x;
			else even[ei++] = x;
		}

		Arrays.sort(odd);
		Arrays.sort(even);

		long answer = 0;

		if (oddCount >= K) {
			long sum = 0;
			for (int i = oddCount - 1; i >= oddCount - K; i--) {
				sum += odd[i];
			}
			answer = Math.max(answer, sum);
		}

		if (evenCount >= K) {
			long sum = 0;
			for (int i = evenCount - 1; i >= evenCount - K; i--) {
				sum += even[i];
			}
			answer = Math.max(answer, sum);
		}

		System.out.println(answer);
	}
}
