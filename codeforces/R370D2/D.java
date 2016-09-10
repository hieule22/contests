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
public class D {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solution solver = new Solution();
        solver.solve(1, in, out);
        out.close();
    }

    static class Solution {
        public static final long MOD = (long) 1e9 + 7;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int a = in.nextInt();
            int b = in.nextInt();
            int k = in.nextInt();
            int t = in.nextInt();

            long[] ways = new long[2 * k * t + 1];
            long[][] memo = new long[ways.length][2];

            int sumOffset = k * t;
            for (int i = 0; i < ways.length; ++i) {
                int sum = i - sumOffset;
                if (-k <= sum && sum <= k)
                    ways[i] = 1;
                if (i == 0)
                    memo[i][1] = ways[i];
                else
                    memo[i][1] = (memo[i - 1][1] + ways[i]) % MOD;
            }

            for (int j = 2; j < t + 1; ++j) {
                int index = j % 2;
                for (int i = 0; i < ways.length; ++i) {
                    int low = Math.max(i - k, 0);
                    int high = Math.min(i + k, ways.length - 1);
                    if (low <= high) {
                        long delta = (low == 0) ? memo[high][1 - index] :
                                (memo[high][1 - index] + MOD - memo[low - 1][1 - index]) % MOD;
                        ways[i] = delta;
                    }
                    if (i == 0)
                        memo[i][index] = ways[i];
                    else
                        memo[i][index] = (memo[i - 1][index] + ways[i]) % MOD;
                }
            }

            int delta = b - a;
            long res = 0;
            int index = t % 2;
            for (int i = 0; i < ways.length; ++i) {
                int bound = Math.min(i - delta - 1, memo.length - 1);
                if (bound >= 0) {
                    long temp = (ways[i] * memo[bound][index]) % MOD;
                    res = (res + temp) % MOD;
                }
            }
            out.println(res);
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

