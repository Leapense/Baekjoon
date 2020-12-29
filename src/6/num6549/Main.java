package num6549;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line[];
		int i, n, arr[];
		StringBuilder sb = new StringBuilder();
		SegmentTree sgt;
		while((line = br.readLine().split(" ")).length > 1)
		{
			arr = new int[n = Integer.parseInt(line[0])];
			for(i = 1; i <= n; i++) arr[i - 1] = Integer.parseInt(line[i]);
			
			sgt = new SegmentTree(arr);
			sb.append(sgt.getMaxWidth(0, n - 1) + "\n");
		}
		System.out.println(sb);
	}
}
class SegmentTree {
	private static int n, arr[], rangeMinIndex[], INF = 0x7fffffff;
	public SegmentTree(int arr[])
	{
		n = arr.length;
		this.arr = arr.clone();
		rangeMinIndex = new int[n << 2];
		
		init(0, n - 1, 1);
	}
	
	private int init(int left, int right, int node) {
		if(left == right) return rangeMinIndex[node] = left;
		
		int mid = (left + right) >>> 1;
		int leftMinIndex = init(left, mid, node<<1);
		int rightMinIndex = init(mid + 1, right, (node << 1) + 1);
		
		return rangeMinIndex[node] =
				arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
	}
	
	private int query(int left, int right, int node, int nodeLeft, int nodeRight) {
		if(nodeRight < left || right < nodeLeft) return INF;
		if(left <= nodeLeft && nodeRight<=right) return rangeMinIndex[node];
		
		int mid = (nodeLeft + nodeRight) >>> 1;
		int leftMinIndex = query(left, right, node << 1, nodeLeft, mid);
		int rightMinIndex = query(left, right, (node << 1) + 1, mid + 1, nodeRight);
		
		if(leftMinIndex == INF) return rightMinIndex;
		else if(rightMinIndex == INF) return leftMinIndex;
		else return arr[leftMinIndex] < arr[rightMinIndex] ? leftMinIndex : rightMinIndex;
	}
	
	public long getMaxWidth(int left, int right) {
		long maxWidth, tmpWidth;
		int minIndex = query(left, right, 1, 0, n - 1);
		
		maxWidth = (long)(right - left + 1) * (long)arr[minIndex];
		
		if(left <= minIndex - 1) {
			tmpWidth = getMaxWidth(left, minIndex - 1);
			maxWidth = Math.max(maxWidth, tmpWidth);
		}
		
		if(minIndex + 1 <= right) {
			tmpWidth = getMaxWidth(minIndex + 1, right);
			maxWidth = Math.max(maxWidth, tmpWidth);
		}
		return maxWidth;
	}
}
