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
            int n = in.nextInt();

            int[] crushes = new int[n];
            for (int i = 0; i < n; ++i) {
                crushes[i] = in.nextInt() - 1;
            }

            int[] lengths = new int[n];
            Arrays.fill(lengths, -1);
            for (int i = 0; i < n; ++i) {
                int current = crushes[i];
                for (int j = 1; j <= n; ++j) {
                    if (current == i) {
                        lengths[i] = j;
                        break;
                    }
                    current = crushes[current];
                }
            }

            long lcm = 1;
            for (int i = 0; i < n; ++i) {
                if (lengths[i] == -1) {
                    out.println(-1);
                    return;
                }
                if (lengths[i] % 2 == 1)
                    lengths[i] = lengths[i] * 2;
                lcm = findLcm(lcm, lengths[i]);
            }

            out.println(lcm / 2);
        }

        private long findLcm(long a, long b) {
            return a / findGcd(a, b) * b;
        }

        private long findGcd(long a, long b) {
            while (b != 0) {
                long temp = a % b;
                a = b;
                b = temp;
            }
            return a;
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
