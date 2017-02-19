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
public class E {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        SherlocksArrayMergingAlgorithm solver = new SherlocksArrayMergingAlgorithm();
        solver.solve(1, in, out);
        out.close();
    }

    static class SherlocksArrayMergingAlgorithm {
        private static final long MOD = (long) 1e9 + 7;
        private static final int MAX_N = 1200;
        private long[] factorials;
        private long[][] nCr;

        public SherlocksArrayMergingAlgorithm() {
            factorials = new long[MAX_N + 1];
            factorials[0] = 1;
            for (int i = 1; i <= MAX_N; ++i) {
                factorials[i] = factorials[i - 1] * i % MOD;
            }
            nCr = new long[MAX_N + 1][MAX_N + 1];
            for (int n = 0; n <= MAX_N; ++n) {
                nCr[n][0] = 1;
                for (int k = 1; k <= n; ++k) {
                    nCr[n][k] = (nCr[n - 1][k - 1] + nCr[n - 1][k]) % MOD;
                }
            }
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] outcomes = new int[n];
            for (int i = 0; i < n; ++i) {
                outcomes[i] = in.nextInt();
            }

            long[][] memo = new long[n][n + 1];
            for (int end = 0; end < n; ++end) {
                for (int length = 1; length <= end + 1; ++length) {
                    // Assert that this sequence is increasing.
                    if (length > 1 && outcomes[end - length + 1] > outcomes[end - length + 2]) {
                        break;
                    }
                    // This sequence is a prefix.
                    if (length == end + 1) {
                        memo[end][length] = 1;
                        continue;
                    }

                    int lastEnd = end - length;
                    for (int lastLength = length; lastLength <= lastEnd + 1; ++lastLength) {
                        // Assert that last sequence is increasing.
                        if (lastLength > 1 &&
                                outcomes[lastEnd - lastLength + 1] > outcomes[lastEnd - lastLength + 2]) {
                            break;
                        }

                        long increase = memo[lastEnd][lastLength] * nCr[lastLength][length] % MOD;
                        memo[end][length] = (memo[end][length] + increase) % MOD;
                    }
                    memo[end][length] = memo[end][length] * factorials[length] % MOD;
                }
            }

            long result = 0;
            for (int length = 1; length <= n; ++length) {
                result = (result + memo[n - 1][length]) % MOD;
            }
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
