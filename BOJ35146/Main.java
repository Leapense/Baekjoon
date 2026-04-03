import java.io.*;
import java.util.*;

public class Main {

	static class FastScanner {

		private final InputStream in;
		private final byte[] buffer = new byte[1 << 16];
		private int ptr = 0,
			len = 0;

		FastScanner(InputStream is) {
			in = is;
		}

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
		FastScanner fs = new FastScanner(System.in);
		BufferedOutputStream out = new BufferedOutputStream(System.out);

		int T = fs.nextInt();
		StringBuilder sb = new StringBuilder(1 << 20);

		for (int i = 0; i < T; i++) {
			int x = fs.nextInt();
			sb.append("1 ").append(x).append(" 1 10\n");
			if (sb.length() >= (1 << 20)) {
				out.write(sb.toString().getBytes());
				sb.setLength(0);
			}
		}

		if (sb.length() > 0) {
			out.write(sb.toString().getBytes());
		}

		out.flush();
	}
}
