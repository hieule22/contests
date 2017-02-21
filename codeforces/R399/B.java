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
            long n = in.nextLong();
            long low = in.nextLong();
            long high = in.nextLong();

            int answer = 0;
            for (long i = low; i <= high; ++i)
                answer += compute(n, i);
            out.println(answer);
        }

        private int compute(long n, long position) {
            if (n <= 1) {
                return (int) n;
            }
            long semiLength = (getLength(n) - 1) / 2;

            if (position <= semiLength) {
                return compute(n / 2, position);
            }

            if (position == semiLength + 1) {
                return (int) (n % 2);
            }

            return compute(n / 2, position - semiLength - 1);
        }

        private long getLength(long n) {
            long highestSetBit = Long.highestOneBit(n);
            return (highestSetBit << 1) - 1;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
