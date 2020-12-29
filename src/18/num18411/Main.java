package num18411;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		List<Integer> score = new ArrayList<>();
		
		for(int i = 0; i < 3; i++)
		{
			score.add(Integer.parseInt(st.nextToken()));
		}
		
		Collections.sort(score);
		Collections.reverse(score);
		
		System.out.println(score.get(0) + score.get(1));
	}
}
