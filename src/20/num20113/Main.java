package num20113;

import java.util.*;

public class Main {
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		ArrayList<Integer> vote_number = new ArrayList<>();
		
		for(int i = 0; i < n; i++)
		{
			vote_number.add(sc.nextInt());
		}
		
		Map<Integer, Integer> duplicate_count = new HashMap<Integer, Integer>();
		for(int i = 0; i < vote_number.size(); i++)
		{
			if(duplicate_count.containsKey(vote_number.get(i))) {
				duplicate_count.put(vote_number.get(i), duplicate_count.get(vote_number.get(i)) + 1);
			} else {
				duplicate_count.put(vote_number.get(i), 0);
			}
		}
		Iterator<Integer> keys = duplicate_count.keySet().iterator();
		StringBuilder sb = new StringBuilder();
		int max = 0;
		int temp = 0;
		while(keys.hasNext()) {
			int key = keys.next();
			if(duplicate_count.get(key) == 0) continue;
			else 
			{
				if(max < duplicate_count.get(key))
				{
					temp = key;
					max = duplicate_count.get(key);
				}
				else if(max == duplicate_count.get(key))
				{
					sb.append("skipped");
					System.out.println(sb);
					return;
				}
			}
		}
		sb.append(temp);
		System.out.println(sb);
	}
}
