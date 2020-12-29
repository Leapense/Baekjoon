package num2501;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 1; i <= n; i++)
		{
			if(n % i == 0)
			{
				list.add(i);
			}
		}
		if(k - 1 >= list.size())
		{
			System.out.println("0");
		}
		else
		{
			System.out.println(list.get(k - 1));
		}
	}
}
