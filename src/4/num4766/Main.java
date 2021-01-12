package num4766;

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double a = Double.parseDouble(br.readLine());
		while(true)
		{
			double temp = Double.parseDouble(br.readLine());
			if(temp == 999)
			{
				break;
			}
			else
			{
				System.out.printf("%.2f\n", (temp - a));
				a = temp;
			}
		}
	}

}

