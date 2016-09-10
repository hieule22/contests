package tasks;

import util.io.InputReader;
import java.io.PrintWriter;

public class D {
    public static final long MOD = (long)1e9 + 7;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int a = in.nextInt();
        int b = in.nextInt();
        int k = in.nextInt();
        int t = in.nextInt();

        long[][] ways = new long[2 * k * t + 1][t + 1];
        long[] memo = new long[ways.length];

        int sumOffset = k * t;
        for (int i = 0; i < ways.length; ++i) {
            int sum = i - sumOffset;
            if (-k <= sum && sum <= k)
                ways[i][1] = 1;
            if (i == 0)
                memo[i] = ways[i][1];
            else
                memo[i] = (memo[i - 1] + ways[i][1]) % MOD;
        }

        for (int j = 2; j < t + 1; ++j) {
            long[] frontier = new long[memo.length];
            for (int i = 0; i < ways.length; ++i) {
                int low = Math.max(i - k, 0);
                int high = Math.min(i + k, ways.length - 1);
                if (low <= high) {
                    long delta = (low == 0) ? memo[high] : (memo[high] + MOD - memo[low - 1]) % MOD;
                    ways[i][j] = delta;
                }
                if (i == 0)
                    frontier[i] = ways[i][j];
                else
                    frontier[i] = (frontier[i - 1] + ways[i][j]) % MOD;
            }
            memo = frontier;
        }


        long[] prefix = new long[ways.length];
        prefix[0] = ways[0][t];
        for (int i = 1; i < prefix.length; ++i) {
            prefix[i] = (prefix[i - 1] + ways[i][t]) % MOD;
        }

        int delta = b - a;
        long res = 0;
        for (int i = 0; i < prefix.length; ++i) {
            int bound = Math.min(i - delta - 1, prefix.length - 1);
            if (bound >= 0) {
                long temp = (ways[i][t] * prefix[bound]) % MOD;
                res = (res + temp) % MOD;
            }
        }
        out.println(res);
    }
}
