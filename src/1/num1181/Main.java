package num1181;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		ArrayList<String> sortedList = new ArrayList<>();
		for(int i = 0; i < n; i++)
		{
			sortedList.add(br.readLine());
		}
		
		Comparator<String> c = new Comparator<String>() {
			public int compare(String s1, String s2) {
				if(s1.length() < s2.length()) {
					return -1;
				}
				else if(s1.length() == s2.length()) {
					return s1.compareTo(s2);
				}
				return 1;
			}
		};
		
		Collections.sort(sortedList, c);
		
		for(int i = 0; i < sortedList.size(); i++)
		{
			if(i != sortedList.size() - 1 && sortedList.get(i).equals(sortedList.get(i + 1))) {
				continue;
			}
			else {
				System.out.println(sortedList.get(i));
			}
		}
	}
}