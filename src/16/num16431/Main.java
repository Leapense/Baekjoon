package num16431;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		int Br, Bc, Dr, Dc, Jr, Jc;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Br = Integer.parseInt(st.nextToken());
		Bc = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		Dr = Integer.parseInt(st.nextToken());
		Dc = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		Jr = Integer.parseInt(st.nextToken());
		Jc = Integer.parseInt(st.nextToken());
		
		int B = Math.max(Math.abs(Jr - Br), Math.abs(Jc - Bc));
		int D = Math.abs(Jr - Dr) + Math.abs(Jc - Dc);
		if(B < D)
			System.out.println("bessie");
		else if(B > D)
			System.out.println("daisy");
		else
			System.out.println("tie");
		
	}
}
