package num2606;

import java.util.*;
import java.io.*;

public class Main {
	static int nV;
	static int nE;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		nV = Integer.parseInt(br.readLine());
		nE = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer>> ad = new <ArrayList<Integer>> ArrayList();
		
		for(int i = 0; i < nV; i++)
		{
			ad.add(new <Integer> ArrayList());
		}
		
		for(int i = 0; i < nE; i++)
		{
			int t1, t2;
			st = new StringTokenizer(br.readLine(), " ");
			t1 = Integer.parseInt(st.nextToken());
			t2 = Integer.parseInt(st.nextToken());
			
			ad.get(t1).add(t2);
			ad.get(t2).add(t1);
		}
		System.out.println(ad.toString());
	}
}
