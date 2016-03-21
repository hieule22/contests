package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class TaskB {
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        if (n > m) {
            out.println("YES");
            return;
        }
        boolean[][] dp = new boolean[n + 1][m];
        dp[1][a[0] % m] = true;
        for (int i = 2; i <= n; i++) {
            dp[i][a[i - 1] % m] = true;
            for (int j = 0; j < m; j++) {
                int index = j - (a[i - 1] % m);
                while (index < 0) index += m;
                dp[i][j] = dp[i][j] | dp[i - 1][j] | dp[i - 1][index];
            }
        }
        if (dp[n][0]) out.println("YES");
        else out.println("NO");
    }
}
