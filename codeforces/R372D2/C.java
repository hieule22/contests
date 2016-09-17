import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.math.BigInteger;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * C. Plus and Square Root http://codeforces.com/contest/716/problem/C
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class Main {
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
            int levelCount = in.nextInt();
            long[] plusPresses = new long[levelCount];
            List<BigInteger> displayValues = new ArrayList<>();
            displayValues.add(new BigInteger("2"));
            for (int i = 1; i <= levelCount; ++i) {
                BigInteger product = multiply(i, i + 1);
                BigInteger delta = product.pow(2).subtract(displayValues.get(i - 1)).divide(
                        new BigInteger(Integer.toString(i)));
                plusPresses[i - 1] = delta.longValue();
                displayValues.add(product);
            }

            for (long press : plusPresses) {
                out.println(press);
            }
        }

        private BigInteger multiply(int a, int b) {
            BigInteger aa = new BigInteger(Integer.toString(a));
            BigInteger bb = new BigInteger(Integer.toString(b));
            return aa.multiply(bb);
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
