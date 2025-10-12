import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long X = Long.parseLong(st.nextToken());
		long Y = Long.parseLong(st.nextToken());
		long D = Y - X;

		if (D <= 0) {
			System.out.println(0);
			return;
		}

		long n = (long) Math.sqrt(D);
		long ans;

		if (n * n == D) {
			ans = 2 * n - 1;
		} else if (D <= n * n + n) { 
			ans = 2 * n;
		} else {
			ans = 2 * n + 1;
		}

		System.out.println(ans);
	}
}
