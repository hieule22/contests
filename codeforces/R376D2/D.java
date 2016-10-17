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
import java.util.Collections;
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
        public static final int IMPOSSIBLE = -1;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int c = in.nextInt();
            int[][] words = new int[n][];
            for (int i = 0; i < n; ++i) {
                int length = in.nextInt();
                words[i] = new int[length];
                for (int j = 0; j < length; ++j)
                    words[i][j] = in.nextInt();
            }

            List<Interval> forbidden = new ArrayList<>();
            int low = 0, high = c - 1;
            for (int i = 0; i < n - 1; ++i) {
                int[] first = words[i];
                int[] second = words[i + 1];
                int length = Math.min(first.length, second.length);
                boolean sharePrefix = true;
                for (int j = 0; j < length; ++j) {
                    if (first[j] != second[j]) {
                        sharePrefix = false;
                        if (first[j] < second[j]) {
                            if (c - second[j] + 1 <= c - first[j])
                                forbidden.add(new Interval(c - second[j] + 1, c - first[j]));
                        } else if (first[j] > second[j]) {
                            if (low <= c - first[j])
                                forbidden.add(new Interval(low, c - first[j]));
                            if (c - second[j] + 1 <= high)
                                forbidden.add(new Interval(c - second[j] + 1, high));
                        }
                        break;
                    }
                }

                if (sharePrefix && first.length > second.length) {
                    out.println(IMPOSSIBLE);
                    return;
                }
            }

            List<Interval> disjoint = merge(forbidden);
            if (disjoint.isEmpty()) {
                out.println(0);
                return;
            }

            if (low < disjoint.get(0).begin) {
                out.println(low);
                return;
            }

            if (disjoint.get(disjoint.size() - 1).end < high) {
                out.println(high);
                return;
            }

            for (int i = 0; i < disjoint.size() - 1; ++i) {
                if (disjoint.get(i).end + 1 < disjoint.get(i + 1).begin) {
                    out.println(disjoint.get(i).end + 1);
                    return;
                }
            }

            out.println(IMPOSSIBLE);
        }

        private List<Interval> merge(List<Interval> intervals) {
            Collections.sort(intervals, (o1, o2) -> o1.begin - o2.begin);

            List<Interval> result = new ArrayList<>();
            for (Interval interval : intervals) {
                if (result.isEmpty()) {
                    result.add(interval);
                    continue;
                }

                Interval last = result.get(result.size() - 1);
                if (last.end >= interval.begin) {
                    last.end = Math.max(last.end, interval.end);
                } else {
                    result.add(interval);
                }
            }

            return result;
        }

        private class Interval {
            private int begin;
            private int end;

            public Interval(int begin, int end) {
                this.begin = begin;
                this.end = end;
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
