package num15059;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] a= new int[3];
		int[] r = new int[3];
		
		for(int i = 0; i < 3; i++)
		{
			a[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < 3; i++)
		{
			r[i] = Integer.parseInt(st.nextToken());
		}
		
		int need = 0;
		for(int i = 0; i < 3; i++)
		{
			if(r[i] > a[i])
			{
				need += (r[i] - a[i]);
			}
		}
		bw.write(Integer.toString(need));
		bw.flush();
		bw.close();
		br.close();
	}
}
