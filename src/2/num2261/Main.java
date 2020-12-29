package num2261;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Point[] points = new Point[n];
		StringTokenizer st;
		for(int i = 0; i < n; i++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(points, (a, b) -> (a.x - b.x));
		
		TreeSet<Point> set = new TreeSet<>((a, b) -> ((a.y == b.y) ? a.x - b.x : a.y - b.y));
		set.add(points[0]);
		set.add(points[1]);
		
		long answer = distSquare(points[0], points[1]);
		int start = 0;
		for(int i = 2; i < n; i++)
		{
			Point cur = points[i];
			
			while(start < i) {
				Point point = points[start];
				long x = cur.x - point.x;
				if(x * x > answer) {
					set.remove(point);
					start += 1;
				}
				else
				{
					break;
				}
			}
			
			int d = (int)Math.sqrt((double) answer) + 1;
			Point from = new Point(-10001, cur.y - d);
			Point to = new Point(10001, cur.y + d);
			for(Point point : set.subSet(from, to)) {
				long distance = distSquare(cur, point);
				answer = Math.min(answer, distance);
			}
			set.add(cur);
		}
		System.out.println(answer);
	}
	static long distSquare(Point A, Point B) {
		return (long)((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
	}
}
class Point {
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
