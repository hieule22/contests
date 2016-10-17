import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
            long b = in.nextLong();
            long d = in.nextLong();
            long s = in.nextLong();

            long total = b + d + s;
            long days = Math.max(b, Math.max(d, s));

            long result = Long.MAX_VALUE;
            for (long i = days; i <= days + 1; ++i) {
                for (int arrive = 0; arrive < 4; ++arrive) {
                    for (int depart = 0; depart < 4; ++depart) {
                        long bb = i;
                        if (arrive > 0) bb--;
                        if (depart < 1) bb--;

                        long dd = i;
                        if (arrive > 1) dd--;
                        if (depart < 2) dd--;

                        long ss = i;
                        if (arrive > 2) ss--;
                        if (depart < 3) ss--;

                        if (bb >= b && dd >= d && ss >= s) {
                            result = Math.min(result, bb + dd + ss - total);
                        }
                    }
                }
            }

            out.println(result);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
