import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
public class D {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        private long[] weights;
        private List<List<Integer>> graph;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            weights = new long[n];
            for (int i = 0; i < n; ++i)
                weights[i] = in.nextLong();
            graph = new ArrayList<>(n);
            for (int i = 0; i < n; ++i)
                graph.add(new ArrayList<>());
            for (int i = 0; i < n - 1; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            Record record = dfs(0, -1);
            if (record.maxTwoGifts == Long.MIN_VALUE)
                out.println("Impossible");
            else
                out.println(record.maxTwoGifts);
        }

        private Record dfs(int node, int parent) {
            Record record = new Record();
            record.total = weights[node];

            long firstGift = Long.MIN_VALUE;
            long secondGift = Long.MIN_VALUE;
            for (int child : graph.get(node)) {
                if (child == parent) continue;
                Record childRecord = dfs(child, node);
                // Update total weight at this subtree.
                record.total += childRecord.total;
                // The best two gifts might come from this subtree.
                record.maxTwoGifts = Math.max(record.maxTwoGifts, childRecord.maxTwoGifts);
                // The best one gift might come from this subtree.
                record.maxOneGift = Math.max(record.maxOneGift, childRecord.maxOneGift);
                // One of best one gifts might come from this subtree.
                if (childRecord.maxOneGift >= firstGift) {
                    secondGift = firstGift;
                    firstGift = childRecord.maxOneGift;
                } else if (childRecord.maxOneGift > secondGift) {
                    secondGift = childRecord.maxOneGift;
                }
            }

            // The subtree itself might yield the maximum one gift.
            record.maxOneGift = Math.max(record.maxOneGift, record.total);
            // Each gift comes from a different subtree.
            if (firstGift != Long.MIN_VALUE && secondGift != Long.MIN_VALUE)
                record.maxTwoGifts = Math.max(record.maxTwoGifts, firstGift + secondGift);
            return record;
        }

        private class Record {
            private long maxTwoGifts;
            private long maxOneGift;
            private long total;

            private Record() {
                maxTwoGifts = maxOneGift = Long.MIN_VALUE;
                total = 0;
            }

        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private static final int BUFFER_SIZE = 32768;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(
                    new InputStreamReader(stream), BUFFER_SIZE);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
