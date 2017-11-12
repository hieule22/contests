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
public class A {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        private static final int N = 6;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int[] scores = new int[N];
            int total = 0;
            for (int i = 0; i < N; ++i) {
                scores[i] = in.nextInt();
                total += scores[i];
            }

            for (int mask = 0; mask < (1 << N); ++mask) {
                int nChosen = 0;
                int sum = 0;
                for (int i = 0; i < N; ++i) {
                    if ((mask & (1 << i)) > 0) {
                        ++nChosen;
                        sum += scores[i];
                    }
                }

                if (nChosen == N / 2 && sum * 2 == total) {
                    out.println("YES");
                    return;
                }
            }

            out.println("NO");
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
