package num14264;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// �� ������ ���ﰢ���� ���̸� ���϶�� �����̴�.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long l = Long.parseLong(br.readLine());
		
		System.out.println((Math.sqrt(3) / 4.0) * Math.pow(l, 2));
	}
}