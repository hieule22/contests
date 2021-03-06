import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
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
            int n = in.nextInt();  // The number of servers.
            int q = in.nextInt();  // The number of task.

            int[] nextFreeTime = new int[n];
            Arrays.fill(nextFreeTime, 1);
            for (int i = 0; i < q; ++i) {
                int t = in.nextInt();  // Start time.
                int k = in.nextInt();  // Number of required servers.
                int d = in.nextInt();  // Interval.
                int available = 0;
                for (int server : nextFreeTime) {
                    if (server <= t)
                        ++available;
                }
                if (available < k) {
                    out.println(-1);
                    continue;
                }

                int sum = 0;
                for (int j = 0; j < nextFreeTime.length && k > 0; ++j) {
                    if (nextFreeTime[j] <= t) {
                        k--;
                        nextFreeTime[j] = t + d;
                        sum += (j + 1);
                    }
                }
                out.println(sum);
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
