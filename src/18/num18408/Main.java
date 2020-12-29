package num18408;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int one_cnt = 0, two_cnt = 0;
		for(int i = 0; i < s.length(); i++)
		{
			if(s.charAt(i) == '1')
			{
				one_cnt += 1;
			}
			else if(s.charAt(i) == '2')
			{
				two_cnt += 1;
			}
		}
		if(one_cnt < two_cnt)
		{
			System.out.println("2");
		}
		else
		{
			System.out.println("1");
		}
		
	}
}
