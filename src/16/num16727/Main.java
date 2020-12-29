package num16727;
import java.io.*;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] Per = new int[2];
		int[] Est = new int[2];
		
		Per[0] = Integer.parseInt(st.nextToken());
		Est[0] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		Est[1] = Integer.parseInt(st.nextToken());
		Per[1] = Integer.parseInt(st.nextToken());
		
		int sum_Per = Per[0] + Per[1];
		int sum_Est = Est[0] + Est[1];
		
		if(sum_Per == sum_Est)
		{
			if(Per[0] > Est[1])
				System.out.println("Esteghlal");
			else if(Per[0] < Est[1])
				System.out.println("Persepolis");
			else
				System.out.println("Penalty");
		}
		else if(sum_Per > sum_Est)
			System.out.println("Persepolis");
		else
			System.out.println("Esteghlal");
		
	}
}
