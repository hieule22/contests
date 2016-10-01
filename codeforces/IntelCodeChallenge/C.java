import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * http://codeforces.com/contest/722/problem/C
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
            long[] prefix = new long[n + 1];
            for (int i = 1; i <= n; ++i) {
                long a = in.nextLong();
                prefix[i] = prefix[i - 1] + a;
            }

            TreeMap<Long, Integer> segments = new TreeMap<>();
            segments.put(prefix[n], 1);
            TreeSet<Integer> removed = new TreeSet<>();
            removed.add(0);
            removed.add(n + 1);

            for (int i = 0; i < n; ++i) {
                int position = in.nextInt();
                int lowerBound = removed.lower(position);
                int upperBound = removed.higher(position);
                removed.add(position);

                long previous = prefix[upperBound - 1] - prefix[lowerBound];
                if (segments.get(previous) == 1) {
                    segments.remove(previous);
                } else {
                    segments.put(previous, segments.get(previous) - 1);
                }

                long left = prefix[position - 1] - prefix[lowerBound];
                addToMap(segments, left);
                long right = prefix[upperBound - 1] - prefix[position];
                addToMap(segments, right);

                out.println(segments.lastKey());
            }
        }

        private static void addToMap(Map<Long, Integer> map, long value) {
            if (!map.containsKey(value)) {
                map.put(value, 1);
            } else {
                map.put(value, map.get(value) + 1);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}

