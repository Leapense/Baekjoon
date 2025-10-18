import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws Exception {
		final long MOD = 1_000_000L;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine().trim());
		long T = 0L;
		long i = 1L;

		while (i <= n) {
			long q = n / i;
			long r = n / q;
			long cnt = r - i + 1;
			long sumRange = ((i + r) * cnt) / 2;
			T += q * sumRange;
			i = r + 1;
		}

		long sum1toN = n * (n + 1) / 2;
		long res = T - n - (sum1toN - 1);
		long ans = ((res % MOD) + MOD) % MOD;

		System.out.println(ans);
	}
}
