import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Edge>[] adj;
    static long totalWeight = 0;
    static long maxPathFromS = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Edge(v, w));
            adj[v].add(new Edge(u, w));

            totalWeight += w;
        }

        dfs(s, 0, 0);

        long result = (totalWeight * 2) - maxPathFromS;
        System.out.println(result);
    }

    private static void dfs(int u, int parent, long currentDist) {
        if (currentDist > maxPathFromS) {
            maxPathFromS = currentDist;
        }
        for (Edge edge : adj[u]) {
            int v = edge.to;
            if (v != parent) {
                dfs(v, u, currentDist + edge.weight);
            }
        }
    }
}