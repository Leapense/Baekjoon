package num8246;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// �ؼ�: �� ���� ���ڴ� ��ĥ �� ����.
	// �ؼ�: �׷��� ���̺��� �� ���� ���ڰ� �ʿ��ұ�?
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		System.out.println((int)((a / k) * (b / k) - Math.max(a / k - 2, 0) * Math.max(b / k - 2, 0)));
	}
}