import java.io.*;
import java.util.*;

/**
 * BOJ - Demand for Cycling
 * Java 15
 */
public class Main {

	static class FastScanner {

		private final InputStream in = System.in;
		private final byte[] buffer = new byte[1 << 16];
		private int ptr = 0,
			len = 0;

		private int read() throws IOException {
			if (ptr >= len) {
				len = in.read(buffer);
				ptr = 0;
				if (len <= 0) return -1;
			}
			return buffer[ptr++];
		}

		int nextInt() throws IOException {
			int c;
			do {
				c = read();
			} while (c <= ' ');

			int sign = 1;
			if (c == '-') {
				sign = -1;
				c = read();
			}

			int val = 0;
			while (c > ' ') {
				val = val * 10 + (c - '0');
				c = read();
			}
			return val * sign;
		}
	}

	static class IntList {

		int[] a = new int[8];
		int size = 0;

		void add(int v) {
			if (size == a.length) a = Arrays.copyOf(a, a.length << 1);
			a[size++] = v;
		}

		int get(int i) {
			return a[i];
		}

		int size() {
			return size;
		}

		int[] toArray() {
			return Arrays.copyOf(a, size);
		}
	}

	static class Point {

		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		boolean same(Point o) {
			return this.x == o.x && this.y == o.y;
		}
	}

	static class RowConvexShape {

		int[] yLow, yHigh;
		int[] left, right;

		RowConvexShape(int[] yLow, int[] yHigh, int[] left, int[] right) {
			this.yLow = yLow;
			this.yHigh = yHigh;
			this.left = left;
			this.right = right;
		}
	}

	static class ColConvexShape {

		int[] xLow, xHigh;
		int[] bottom, top;

		ColConvexShape(int[] xLow, int[] xHigh, int[] bottom, int[] top) {
			this.xLow = xLow;
			this.xHigh = xHigh;
			this.bottom = bottom;
			this.top = top;
		}
	}

	static void addMap(TreeMap<Integer, Integer> map, int key) {
		map.put(key, map.getOrDefault(key, 0) + 1);
	}

	static void removeMap(TreeMap<Integer, Integer> map, int key) {
		int c = map.get(key);
		if (c == 1) map.remove(key);
		else map.put(key, c - 1);
	}

	static int[] uniqueSorted(int[] arr) {
		int[] copy = arr.clone();
		Arrays.sort(copy);
		int m = 0;
		for (int v : copy) {
			if (m == 0 || copy[m - 1] != v) {
				copy[m++] = v;
			}
		}
		return Arrays.copyOf(copy, m);
	}

	static RowConvexShape horizontalClosure(int[] px, int[] py) {
		int n = px.length;

		int[] ys = uniqueSorted(py);
		int m = ys.length;

		HashMap<Integer, Integer> yIndex = new HashMap<>(m * 2);
		for (int i = 0; i < m; i++) yIndex.put(ys[i], i);

		int verticalCount = 0;
		for (int i = 0; i < n; i++) {
			int j = (i + 1) % n;
			if (px[i] == px[j]) verticalCount++;
		}

		int[] xVal = new int[verticalCount];
		int[] start = new int[verticalCount];
		int[] end = new int[verticalCount];
		int[] nextAdd = new int[verticalCount];
		int[] nextRem = new int[verticalCount];
		int[] headAdd = new int[m];
		int[] headRem = new int[m];
		Arrays.fill(headAdd, -1);
		Arrays.fill(headRem, -1);

		int idx = 0;
		for (int i = 0; i < n; i++) {
			int j = (i + 1) % n;
			if (px[i] != px[j]) continue;

			int y1 = py[i];
			int y2 = py[j];
			if (y1 > y2) {
				int tmp = y1;
				y1 = y2;
				y2 = tmp;
			}

			int l = yIndex.get(y1);
			int r = yIndex.get(y2);

			xVal[idx] = px[i];
			start[idx] = l;
			end[idx] = r;

			nextAdd[idx] = headAdd[l];
			headAdd[l] = idx;

			nextRem[idx] = headRem[r];
			headRem[r] = idx;

			idx++;
		}

		TreeMap<Integer, Integer> activeX = new TreeMap<>();
		IntList yLow = new IntList();
		IntList yHigh = new IntList();
		IntList left = new IntList();
		IntList right = new IntList();

		for (int i = 0; i < m - 1; i++) {
			for (int e = headRem[i]; e != -1; e = nextRem[e]) {
				removeMap(activeX, xVal[e]);
			}
			for (int e = headAdd[i]; e != -1; e = nextAdd[e]) {
				addMap(activeX, xVal[e]);
			}

			if (!activeX.isEmpty()) {
				yLow.add(ys[i]);
				yHigh.add(ys[i + 1]);
				left.add(activeX.firstKey());
				right.add(activeX.lastKey());
			}
		}

		return new RowConvexShape(
			yLow.toArray(),
			yHigh.toArray(),
			left.toArray(),
			right.toArray()
		);
	}

	static ColConvexShape verticalClosure(RowConvexShape h) {
		int k = h.left.length;

		int[] xsTmp = new int[2 * k];
		for (int i = 0; i < k; i++) {
			xsTmp[2 * i] = h.left[i];
			xsTmp[2 * i + 1] = h.right[i];
		}
		int[] xs = uniqueSorted(xsTmp);
		int m = xs.length;

		HashMap<Integer, Integer> xIndex = new HashMap<>(m * 2);
		for (int i = 0; i < m; i++) xIndex.put(xs[i], i);

		int[] nextAdd = new int[k];
		int[] nextRem = new int[k];
		int[] headAdd = new int[m];
		int[] headRem = new int[m];
		Arrays.fill(headAdd, -1);
		Arrays.fill(headRem, -1);

		for (int i = 0; i < k; i++) {
			int l = xIndex.get(h.left[i]);
			int r = xIndex.get(h.right[i]);

			nextAdd[i] = headAdd[l];
			headAdd[l] = i;

			nextRem[i] = headRem[r];
			headRem[r] = i;
		}

		TreeMap<Integer, Integer> activeBottom = new TreeMap<>();
		TreeMap<Integer, Integer> activeTop = new TreeMap<>();

		IntList xLow = new IntList();
		IntList xHigh = new IntList();
		IntList bottom = new IntList();
		IntList top = new IntList();

		for (int i = 0; i < m - 1; i++) {
			for (int e = headRem[i]; e != -1; e = nextRem[e]) {
				removeMap(activeBottom, h.yLow[e]);
				removeMap(activeTop, h.yHigh[e]);
			}
			for (int e = headAdd[i]; e != -1; e = nextAdd[e]) {
				addMap(activeBottom, h.yLow[e]);
				addMap(activeTop, h.yHigh[e]);
			}

			if (!activeBottom.isEmpty()) {
				xLow.add(xs[i]);
				xHigh.add(xs[i + 1]);
				bottom.add(activeBottom.firstKey());
				top.add(activeTop.lastKey());
			}
		}

		return new ColConvexShape(
			xLow.toArray(),
			xHigh.toArray(),
			bottom.toArray(),
			top.toArray()
		);
	}

	static boolean collinear(Point a, Point b, Point c) {
		return (a.x == b.x && b.x == c.x) || (a.y == b.y && b.y == c.y);
	}

	static void addPoint(ArrayList<Point> list, Point p) {
		if (!list.isEmpty() && list.get(list.size() - 1).same(p)) {
			return;
		}
		while (list.size() >= 2) {
			Point a = list.get(list.size() - 2);
			Point b = list.get(list.size() - 1);
			if (collinear(a, b, p)) {
				list.remove(list.size() - 1);
			} else {
				break;
			}
		}
		list.add(p);
	}

	static ArrayList<Point> buildPolygon(ColConvexShape s) {
		int k = s.xLow.length;
		ArrayList<Point> poly = new ArrayList<>();

		// 아래 체인
		addPoint(poly, new Point(s.xLow[0], s.bottom[0]));
		for (int i = 0; i < k; i++) {
			addPoint(poly, new Point(s.xHigh[i], s.bottom[i]));
			if (i + 1 < k && s.bottom[i] != s.bottom[i + 1]) {
				addPoint(poly, new Point(s.xHigh[i], s.bottom[i + 1]));
			}
		}

		// 오른쪽 위로
		if (s.bottom[k - 1] != s.top[k - 1]) {
			addPoint(poly, new Point(s.xHigh[k - 1], s.top[k - 1]));
		}

		// 위 체인
		for (int i = k - 1; i >= 0; i--) {
			addPoint(poly, new Point(s.xLow[i], s.top[i]));
			if (i > 0 && s.top[i] != s.top[i - 1]) {
				addPoint(poly, new Point(s.xLow[i], s.top[i - 1]));
			}
		}

		// 시작점과의 cyclic collinear 정리
		if (poly.size() > 1 && poly.get(poly.size() - 1).same(poly.get(0))) {
			poly.remove(poly.size() - 1);
		}

		boolean changed = true;
		while (changed && poly.size() >= 3) {
			changed = false;
			while (
				poly.size() >= 3 &&
				collinear(
					poly.get(poly.size() - 2),
					poly.get(poly.size() - 1),
					poly.get(0)
				)
			) {
				poly.remove(poly.size() - 1);
				changed = true;
			}
			while (
				poly.size() >= 3 &&
				collinear(poly.get(poly.size() - 1), poly.get(0), poly.get(1))
			) {
				poly.remove(0);
				changed = true;
			}
		}

		return poly;
	}

	public static void main(String[] args) throws Exception {
		FastScanner fs = new FastScanner();

		int n = fs.nextInt();
		int[] px = new int[n];
		int[] py = new int[n];

		for (int i = 0; i < n; i++) {
			px[i] = fs.nextInt();
			py[i] = fs.nextInt();
		}

		RowConvexShape h = horizontalClosure(px, py);
		ColConvexShape v = verticalClosure(h);
		ArrayList<Point> answer = buildPolygon(v);

		StringBuilder sb = new StringBuilder();
		sb.append(answer.size()).append('\n');
		for (Point p : answer) {
			sb.append(p.x).append(' ').append(p.y).append('\n');
		}
		System.out.print(sb.toString());
	}
}
