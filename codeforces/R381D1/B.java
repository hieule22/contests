import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class B {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        private int[] results;
        private List<List<Integer>> graph;
        private Pair[][] ancestors;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] values = new int[n];
            for (int i = 0; i < n; ++i)
                values[i] = in.nextInt();
            graph = new ArrayList<>(n);
            for (int i = 0; i < n; ++i)
                graph.add(new ArrayList<>());
            ancestors = new Pair[n][20];
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < 20; ++j)
                    ancestors[i][j] = new Pair(-1, Long.MAX_VALUE);

            for (int i = 1; i < n; ++i) {
                int parent = in.nextInt() - 1;
                int weight = in.nextInt();
                graph.get(parent).add(i);
                ancestors[i][0] = new Pair(parent, weight);
            }

            for (int i = 1; i < 20; ++i) {
                for (int j = 0; j < n; ++j) {
                    int ancestor = ancestors[j][i - 1].first;
                    if (ancestor == -1 || ancestors[ancestor][i - 1].first == -1)
                        continue;
                    ancestors[j][i] = new Pair(ancestors[ancestor][i - 1].first,
                            ancestors[j][i - 1].second + ancestors[ancestor][i - 1].second);
                }
            }

            results = new int[n];
            for (int i = 1; i < n; ++i) {
                int range = countAncestors(i, values[i]);
                if (range > 0) {
                    ++results[getAncestor(i, 1)];
                    int start = getAncestor(i, range);
                    if (start != 0)
                        --results[getAncestor(start, 1)];
                }
            }

            dfs(0);

            for (int i = 0; i < n; ++i) {
                out.print(results[i]);
                out.print(i == n - 1 ? "\n" : " ");
            }
        }

        private int countAncestors(int node, int maxWeight) {
            int result = 0;
            while (ancestors[node][0].second <= maxWeight) {
                int low = 0, high = 19;
                while (low < high) {
                    int mid = low + (high - low + 1) / 2;
                    if (ancestors[node][mid].second <= maxWeight)
                        low = mid;
                    else
                        high = mid - 1;
                }
                result += (1 << low);
                maxWeight -= (int) ancestors[node][low].second;
                node = ancestors[node][low].first;
            }
            return result;
        }

        private int getAncestor(int node, int depth) {
            while (depth > 0) {
                int offset = Integer.highestOneBit(depth);
                int bitCount = Integer.numberOfTrailingZeros(offset);
                node = ancestors[node][bitCount].first;
                depth -= offset;
            }
            return node;
        }

        private void dfs(int node) {
            for (int child : graph.get(node)) {
                dfs(child);
                results[node] += results[child];
            }
        }

        private class Pair {
            private int first;
            private long second;

            public Pair(int first, long second) {
                this.first = first;
                this.second = second;
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
