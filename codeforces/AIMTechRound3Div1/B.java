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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            long a00 = in.nextLong();
            long a01 = in.nextLong();
            long a10 = in.nextLong();
            long a11 = in.nextLong();

            // Find the number of 1's and 0's.
            int m = count(a11);
            int n = count(a00);
//        System.err.printf("%d %d\n", m, n);
            // Check if two equations have integral roots.
            if (m == -1 || n == -1) {
                out.println("Impossible");
                return;
            }

            if (m == 1 && n > 1 && a10 == 0 && a01 == 0)  // All 0's.
                m = 0;
            else if (n == 1 && m > 1 && a01 == 0 && a10 == 0)  // All 1's.
                n = 0;
            else if (m == 1 && n == 1 && a01 == 0 && a10 == 0)  // A single number.
                m = 0;

            // Check if the number of inversions agree.
            long actual = a10 + a01;
            long expected = (long) m * n;
            if (actual != expected) {
                out.println("Impossible");
                return;
            }

            char[] s = new char[m + n];
            for (int i = 0; i < s.length; ++i) {
                // Try to place 0.
                if (n > 0 && a01 >= m) {
                    s[i] = '0';
                    --n;
                    a01 -= m;
                } else if (m > 0 && a10 >= n) {
                    s[i] = '1';
                    --m;
                    a10 -= n;
                } else {
                    out.println("Impossible");
                    return;
                }
            }

            out.println(s);
        }

        private int count(long c) {
            // Solve x^2 - x - 2c = 0.
            long delta = 1 + 8 * c;
            if (!isSquare(delta))
                return -1;
            long sqrt = (long) Math.sqrt(delta);
            long numerator = 1 + sqrt;
            if (numerator % 2 == 1)
                return -1;
            return (int) (numerator / 2);
        }

        private boolean isSquare(long a) {
            long sqrt = (long) Math.sqrt(a);
            return sqrt * sqrt == a;
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
