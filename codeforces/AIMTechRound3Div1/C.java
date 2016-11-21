import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class C {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            List<List<Integer>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; ++i)
                graph.add(new ArrayList<>());
            for (int i = 0; i < n - 1; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int[] parents = new int[n];
            int[] weights = new int[n];
            int[] maxChildWeights = new int[n];
            Arrays.fill(parents, -1);
            dfs(0, graph, parents, weights, maxChildWeights);
            dfs2(0, 0, parents, weights, graph);


            for (int i = 0; i < n; ++i) {
                int parentWeight = n - weights[i];
                int minWeight = parentWeight, minNode = -1;
                int maxWeight = parentWeight, maxNode = -1;
                for (int child : graph.get(i)) {
                    if (parents[i] == child) continue;
                    if (minWeight > weights[child]) {
                        minWeight = weights[child];
                        minNode = child;
                    }
                    if (maxWeight < weights[child]) {
                        maxWeight = weights[child];
                        maxNode = child;
                    }
                }
                // No replacement is needed.
                if (maxWeight <= n / 2) {
                    out.printf("1 ");
                    continue;
                }

                // i is a leaf.
                if (minNode == maxNode) {
                    out.printf("0 ");
                    continue;
                }

                // The maximum connected component is a subtree of i.
                if (maxNode != -1) {
                    if (maxWeight - maxChildWeights[maxNode] <= n / 2) {
                        out.printf("1 ");
                    } else {
                        out.printf("0 ");
                    }
                    continue;
                }

                // Maximum connected component is parent of i.

            }
        }

        private void dfs(int node, List<List<Integer>> graph, int[] parents, int[] weights,
                         int[] maxChildWeights) {
            weights[node] = 1;
            for (int child : graph.get(node)) {
                if (parents[node] == child) continue;
                parents[child] = node;
                dfs(child, graph, parents, weights, maxChildWeights);
                maxChildWeights[node] = Math.max(maxChildWeights[node], maxChildWeights[child]);
                if (weights[child] <= graph.size() / 2) {
                    maxChildWeights[node] = Math.max(maxChildWeights[node], weights[child]);
                }
                weights[node] += weights[child];
            }
        }

        private void dfs2(int node, int maxSizeUp, int[] parents, int[] weights,
                          List<List<Integer>> graph) {
            int sizeUp = weights.length - weights[node];
            int numChildren = graph.get(node).size() - (parents[node] == -1 ? 0 : 1);
            int[] maxPrefix = new int[numChildren];
            int[] maxSuffix = new int[numChildren];

            for (int child : graph.get(node)) {
                if (parents[node] == child) continue;

            }
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
