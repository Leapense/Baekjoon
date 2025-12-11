import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node {
        int vertex;
        int dist;
        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A].add(new Edge(B, C));
            graph[B].add(new Edge(A, C));
        }

        final int INF = Integer.MAX_VALUE;
        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = INF;
        }

        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.dist, o2.dist);
            }
        });

        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curV = cur.vertex;
            int curDist = cur.dist;

            if (curDist > dist[curV]) {
                continue;
            }

            for (Edge e : graph[curV]) {
                int nextV = e.to;
                int nextDist = curDist + e.cost;
                if (nextDist < dist[nextV]) {
                    dist[nextV] = nextDist;
                    pq.offer(new Node(nextV, nextDist));
                }
            }
        }

        if (dist[N] == INF) {
            System.out.println(-1);
        } else {
            System.out.println(dist[N]);
        }
    }
}