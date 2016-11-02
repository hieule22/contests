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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++i)
                a[i] = in.nextInt();

            int[][][] dp = new int[n][2][2];
            for (int i = 0; i < n; ++i)
                for (int j = 0; j < 2; ++j)
                    for (int k = 0; k < 2; ++k) dp[i][j][k] = Integer.MAX_VALUE;

            dp[0][0][0] = 1;
            switch (a[0]) {
                case 1:
                    dp[0][1][0] = 0;
                    break;
                case 2:
                    dp[0][0][1] = 0;
                    break;
                case 3:
                    dp[0][1][0] = dp[0][0][1] = 0;
            }

            for (int i = 1; i < n; ++i) {
                {
                    int temp = Integer.MAX_VALUE;
                    for (int j = 0; j < 2; ++j)
                        for (int k = 0; k < 2; ++k)
                            temp = Math.min(temp, dp[i - 1][j][k]);
                    dp[i][0][0] = temp + 1;
                }

                if (a[i] == 1 || a[i] == 3) {
                    dp[i][1][0] = Math.min(dp[i - 1][0][0], dp[i - 1][0][1]);
                }

                if (a[i] == 2 || a[i] == 3) {
                    dp[i][0][1] = Math.min(dp[i - 1][0][0], dp[i - 1][1][0]);
                }
            }

            int result = Integer.MAX_VALUE;
            for (int i = 0; i < 2; ++i)
                for (int j = 0; j < 2; ++j)
                    result = Math.min(result, dp[n - 1][i][j]);

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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
