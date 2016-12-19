import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Comparator;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();  // Number of winter days.
            int k = in.nextInt();  // Winter tire endurance.
            int[] temps = new int[n];

            int subzero = 0;
            for (int i = 0; i < n; ++i) {
                temps[i] = in.nextInt();
                if (temps[i] < 0)
                    ++subzero;
            }

            if (subzero > k) {
                out.println(-1);
                return;
            }

            List<Interval> separations = new ArrayList<>();
            int last = -1;
            for (int i = 0; i < temps.length; ++i) {
                if (temps[i] < 0) {
                    if (last == -1 || last == i - 1) {
                        last = i;
                    } else {
                        separations.add(new Interval(last + 1, i - 1));
                        last = i;
                    }
                }
            }

            separations.sort(Comparator.comparingInt(Interval::length));

            int remain = k - subzero;
            for (Interval interval : separations) {
                if (remain - interval.length() < 0)
                    break;
                remain -= interval.length();
                for (int i = interval.begin; i <= interval.end; ++i)
                    temps[i] = -1;
            }

            if (last != -1 && last != n - 1) {
                int length = (n - 1) - (last + 1) + 1;
                if (remain >= length) {
                    for (int i = last + 1; i <= n - 1; ++i)
                        temps[i] = -1;
                }
            }

            int changes = 0;
            for (int i = 0; i < temps.length; ++i) {
                if (temps[i] < 0) {
                    if (i == 0 || temps[i - 1] >= 0)
                        ++changes;
                } else {
                    if (i > 0 && temps[i - 1] < 0)
                        ++changes;
                }
            }

            out.println(changes);
        }

        private class Interval {
            private int begin;
            private int end;

            private Interval(int begin, int end) {
                this.begin = begin;
                this.end = end;
            }

            private int length() {
                return end - begin + 1;
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

    }
}
