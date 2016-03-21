package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LightingCities {

    private boolean[] hasLight;
    private Map<Integer, List<Integer>> adj;
    private boolean[] visited;
    private int[][] dp;

    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        hasLight = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (in.nextInt() == 1) hasLight[i] = true;
        }
        adj = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n - 1; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            if (!adj.containsKey(u)) adj.put(u, new LinkedList<Integer>());
            if (!adj.containsKey(v)) adj.put(v, new LinkedList<Integer>());
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 0; j < 2; j++) dp[i][j] = Integer.MAX_VALUE;
        recurse(1);
        int res = Math.min(dp[1][0], dp[1][1]);
        out.println(res);
    }

    void recurse(int city) {
        visited[city] = true;
        if (hasLight[city]) {
            dp[city][1] = 0;
            for (int neighbor : adj.get(city)) {
                if (!visited[neighbor]) {
                    recurse(neighbor);
                    dp[city][1] += Math.min(dp[neighbor][0], dp[neighbor][1]);
                }
            }
        } else {
            dp[city][0] = 0;
            dp[city][1] = 1;
            for (int neighbor : adj.get(city)) {
                if (!visited[neighbor]) {
                    recurse(neighbor);
                    dp[city][1] += Math.min(dp[neighbor][0], dp[neighbor][1]);
                    if (dp[city][0] != Integer.MAX_VALUE) {
                        if (dp[neighbor][1] != Integer.MAX_VALUE)
                            dp[city][0] += dp[neighbor][1];
                        else
                            dp[city][0] = Integer.MAX_VALUE;
                    }
                }
            }
        }
    }
}
