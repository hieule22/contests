package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;

public class Spheres {
    private static final int MOD = (int)1e9 + 7;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int c = in.nextInt();
        int[] upper = new int[c + 1];
        int[] lower = new int[c + 1];
        for (int i = 0; i < n; i++) upper[in.nextInt()]++;
        for (int i = 0; i < m; i++) lower[in.nextInt()]++;

        long[] cnt = new long[c + 1];
        for (int i = 0; i < c + 1; i++)
            cnt[i] = (long)lower[i] * upper[i] % MOD;

        long[][] dp = new long[c + 1][c + 1];
        for (int i = 0; i <= c; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1] * cnt[i];
                dp[i][j] %= MOD;
            }
        }
        for (int i = 2; i <= c; i++) out.print(dp[c][i] + " ");
        out.println(0);
    }
}
