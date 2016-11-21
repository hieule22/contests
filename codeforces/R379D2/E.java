import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Queue;
import java.util.LinkedList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class E {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] colors = new int[n];
            for (int i = 0; i < n; ++i) {
                colors[i] = in.nextInt();
            }

            List<List<Pair<Integer, Integer>>> graph = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < n - 1; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int weight = (colors[u] == colors[v]) ? 0 : 1;
                graph.get(u).add(new Pair<>(v, weight));
                graph.get(v).add(new Pair<>(u, weight));
            }

            // Find the diameter of the tree.
            int[] firstDist = new int[n];
            bfs(0, firstDist, graph);
            int first = -1, maxDist = Integer.MIN_VALUE;
            for (int i = 0; i < n; ++i) {
                if (maxDist < firstDist[i]) {
                    maxDist = firstDist[i];
                    first = i;
                }
            }

            bfs(first, firstDist, graph);
            int second = -1;
            maxDist = Integer.MIN_VALUE;
            for (int i = 0; i < n; ++i) {
                if (maxDist < firstDist[i]) {
                    maxDist = firstDist[i];
                    second = i;
                }
            }

            int[] secondDist = new int[n];
            bfs(second, secondDist, graph);

            int result = Integer.MAX_VALUE;
            for (int i = 0; i < n; ++i) {
                int temp = Math.max(firstDist[i], secondDist[i]);
                result = Math.min(result, temp);
            }
            out.println(result);
        }

        private void bfs(int root, int[] dist, List<List<Pair<Integer, Integer>>> graph) {
            Arrays.fill(dist, Integer.MAX_VALUE);
            Queue<Integer> frontier = new LinkedList<>();
            dist[root] = 0;
            frontier.add(root);
            while (!frontier.isEmpty()) {
                int node = frontier.poll();
                for (Pair<Integer, Integer> edge : graph.get(node)) {
                    int neighbor = edge.first, weight = edge.second;
                    if (dist[neighbor] == Integer.MAX_VALUE) {
                        dist[neighbor] = dist[node] + weight;
                        frontier.add(neighbor);
                    }
                }
            }
        }

    }

    static class Pair<U, V> {
        public U first;
        public V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private static final int BUFFER_SIZE = 32768;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
