package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.*;

public class MagicSpells {

    private static final int MIN = -1000;
    private List<Set<Integer>> next;
    private List<Set<Integer>> prev;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] cost = new int[n + 1];
        for (int i = 0; i < n; i++) cost[i] = in.nextInt();
        cost[n] = MIN - 1;

        next = new ArrayList<>(n + 1);
        prev = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            next.add(new HashSet<Integer>());
            prev.add(new HashSet<Integer>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            next.get(x).add(y);
            prev.get(y).add(x);
        }

        for (int i = 0; i < n; i++) {
            next.get(i).add(n);
            prev.get(n).add(i);
        }

        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = cost[i];
            for (int j = 1; j <= k; j++)
                dp[i][j] = Math.max(cost[i], cost[i] * m);
        }

        LinkedList<Integer> frontier = new LinkedList<>();
        frontier.add(n);

        while (!frontier.isEmpty()) {
            int leaf = frontier.removeFirst();
            for (int node : prev.get(leaf)) {
                next.get(node).remove(leaf);
                if (next.get(node).size() == 0) frontier.add(node);
                dp[node][0] = Math.max(dp[node][0], cost[node] + dp[leaf][0]);
                for (int i = 1; i <= k; i++) {
                    dp[node][i] = Math.max(dp[node][i], cost[node] + dp[leaf][i]);
                    dp[node][i] = Math.max(dp[node][i], cost[node] * m + dp[leaf][i - 1]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= k; j++) {
                max = Math.max(max, dp[i][j]);
            }

        out.println(max);

    }

}
