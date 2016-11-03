import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.BitSet;
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
        private Operation[] operations;
        private Map<Integer, List<Pair<Integer, Integer>>> graph;
        private int[] answers;
        private int n;
        private int m;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            n = in.nextInt();
            m = in.nextInt();
            int q = in.nextInt();

            int[] tags = new int[q + 1];
            for (int i = 0; i < tags.length; ++i)
                tags[i] = i;

            graph = new HashMap<>();
            operations = new Operation[q];

            for (int i = 1; i <= q; ++i) {
                int type = in.nextInt();
                int first = in.nextInt();
                if (type == 1 || type == 2) {
                    operations[i - 1] = new Operation(type, first, in.nextInt());
                    int from = tags[i - 1];
                    int to = tags[i];
                    if (!graph.containsKey(from))
                        graph.put(from, new ArrayList<>());
                    graph.get(from).add(new Pair<>(to, i - 1));
                } else if (type == 3) {
                    operations[i - 1] = new Operation(type, first);
                    int from = tags[i - 1];
                    int to = tags[i];
                    if (!graph.containsKey(from))
                        graph.put(from, new ArrayList<>());
                    graph.get(from).add(new Pair<>(to, i - 1));
                } else {
                    tags[i] = tags[first];
                }
            }

            answers = new int[q + 1];
            answers[0] = 0;

            BitSet[] bookcase = new BitSet[n];
            for (int i = 0; i < n; ++i)
                bookcase[i] = new BitSet(m);

            dfs(0, bookcase);
            for (int i = 1; i <= q; ++i) {
                out.println(answers[tags[i]]);
            }
        }

        private void dfs(int node, BitSet[] bookcase) {
            if (!graph.containsKey(node))
                return;
            for (Pair<Integer, Integer> pair : graph.get(node)) {
                int next = pair.first;
                Operation op = operations[pair.second];
                if (op.type == 1) {
                    if (bookcase[op.first].get(op.second)) {
                        answers[next] = answers[node];
                        dfs(next, bookcase);
                    } else {
                        answers[next] = answers[node] + 1;
                        bookcase[op.first].set(op.second);
                        dfs(next, bookcase);
                        bookcase[op.first].clear(op.second);
                    }
                } else if (op.type == 2) {
                    if (bookcase[op.first].get(op.second)) {
                        answers[next] = answers[node] - 1;
                        bookcase[op.first].clear(op.second);
                        dfs(next, bookcase);
                        bookcase[op.first].set(op.second);
                    } else {
                        answers[next] = answers[node];
                        dfs(next, bookcase);
                    }
                } else if (op.type == 3) {
                    int temp = bookcase[op.first].cardinality();
                    bookcase[op.first].flip(0, m);
                    answers[next] = answers[node] - temp + (m - temp);
                    dfs(next, bookcase);
                    bookcase[op.first].flip(0, m);
                }
            }
        }

        private class Operation {
            private int type;
            private int first;
            private int second;

            public Operation(int type, int first) {
                this(type, first, -1);
            }

            public Operation(int type, int first, int second) {
                this.type = type;
                this.first = first - 1;
                this.second = second - 1;
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
