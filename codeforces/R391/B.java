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
public class B {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        private static final int MAX = (int) 1e5;
        private int[] primeDivisors;

        public TaskB() {
            primeDivisors = new int[MAX + 1];
            for (int i = 2; i <= MAX; ++i) {
                if (primeDivisors[i] == 0) {
                    primeDivisors[i] = i;
                    for (int j = i + i; j <= MAX; j += i)
                        primeDivisors[j] = Math.max(primeDivisors[j], i);
                }
            }
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] primeCounts = new int[MAX + 1];
            for (int i = 0; i < n; ++i) {
                int strength = in.nextInt();
                while (strength > 1) {
                    int primeDivisor = primeDivisors[strength];
                    ++primeCounts[primeDivisor];
                    while (strength % primeDivisor == 0)
                        strength /= primeDivisor;
                }
            }

            int result = 1;
            for (int count : primeCounts)
                result = Math.max(count, result);
            out.println(result);
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
