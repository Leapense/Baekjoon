package num11466;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		double w = Double.parseDouble(st.nextToken());
		double h = Double.parseDouble(st.nextToken());
		
		double h1 = h / 3.0;
		double h2 = h / 2.0;
		double h3 = h;
		double w1 = w;
		double w2 = w / 2.0;
		double w3 = w / 3.0;
		
		double[] mins = new double[3];
		
		mins[0] = Math.min(h1, w1);
		mins[1] = Math.min(h2, w2);
		mins[2] = Math.min(h3, w3);
		
		Arrays.sort(mins);
		
		System.out.println(mins[2]);
	}
}
