import java.io.*;
import java.util.*;

public class Main {
	static long solve(int n) {
		if (n <= 2) return 0L;
		if ((n & 1) == 0) {
			long m = n / 2L;
			return m * (m - 1);
		} else {
			long m = (n - 1L) / 2L;
			return m * m;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		while (s != null && s.trim().isEmpty()) s = br.readLine();
		int T = Integer.parseInt(s.trim());

		StringBuilder out = new StringBuilder();
		for (int tc = 0; tc < T; tc++) {
			String line = br.readLine();
			while (line != null && line.trim().isEmpty()) line = br.readLine();
			int n = Integer.parseInt(line.trim());
			out.append(solve(n)).append('\n');
		}
		System.out.print(out.toString());
	}
}
