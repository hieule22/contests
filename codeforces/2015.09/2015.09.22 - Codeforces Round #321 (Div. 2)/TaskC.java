package taskdirectory;

import hieule.utils.io.InputReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TaskC {
    boolean[] hasCat;
    int m;
    Map<Integer, List<Integer>> adj;
    public void solve(int testNumber, InputReader in, PrintWriter out) {
        int n = in.nextInt();
        m = in.nextInt();
        hasCat = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (in.nextInt() == 1) hasCat[i] = true;
        }

        adj = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (!adj.containsKey(x)) adj.put(x, new LinkedList<Integer>());
            adj.get(x).add(y);
            if (!adj.containsKey(y)) adj.put(y, new LinkedList<Integer>());
            adj.get(y).add(x);
        }
        int res;
        if (n == 1) res = 1;
        else res = dfs(0, 1, 0);
        out.println(res);
    }

    private int dfs(int previous, int vertex, int cnt) {
        if (hasCat[vertex]) cnt ++;
        else cnt = 0;
        if (cnt > m) return 0;
        if (vertex != 1 && adj.get(vertex).size() == 1) return 1;
        int res = 0;
        for (int child : adj.get(vertex)) {
            if (child == previous) continue;
            res += dfs(vertex, child, cnt);
        }
        return res;
    }
}
