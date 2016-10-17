import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            int m = in.nextInt();
            int k = in.nextInt();
            int[] colors = new int[n];
            int[] parents = new int[n];
            for (int i = 0; i < n; ++i) {
                colors[i] = in.nextInt();
                parents[i] = i;
            }

            for (int i = 0; i < m; ++i) {
                int left = in.nextInt() - 1;
                int right = in.nextInt() - 1;
                int leftGroup = getGroup(left, parents);
                int rightGroup = getGroup(right, parents);
                parents[rightGroup] = leftGroup;
            }

            Map<Integer, Map<Integer, Integer>> groups = new HashMap<>();
            for (int i = 0; i < n; ++i) {
                int group = getGroup(i, parents);
                if (!groups.containsKey(group))
                    groups.put(group, new HashMap<>());

                Map<Integer, Integer> current = groups.get(group);
                if (!current.containsKey(colors[i]))
                    current.put(colors[i], 0);
                current.put(colors[i], current.get(colors[i]) + 1);
            }

            long result = 0;
            for (Map<Integer, Integer> group : groups.values()) {
                int total = 0;
                int maxFrequency = Integer.MIN_VALUE;
                for (int frequency : group.values()) {
                    total += frequency;
                    maxFrequency = Math.max(maxFrequency, frequency);
                }
                result += total - maxFrequency;
            }
            out.println(result);
        }

        private int getGroup(int node, int[] parent) {
            if (parent[node] == node)
                return node;
            return parent[node] = getGroup(parent[node], parent);
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
