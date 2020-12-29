package num10814;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int n = scan.nextInt();
		String[][] sArr = new String[n][2];
		
		for(int i = 0; i < n; i++)
		{
			sArr[i][0] = scan.next();
			sArr[i][1] = scan.next();
		}
		
		Arrays.sort(sArr, new Comparator<String[]>() {
			@Override
			public int compare(String[] a, String[] b) {
				return Integer.compare(Integer.parseInt(a[0]), Integer.parseInt(b[0]));
			}
		});
		
		for(int i = 0; i < n; i++)
		{
			System.out.println(sArr[i][0] + " " + sArr[i][1]);
		}
		scan.close();
	}
}
