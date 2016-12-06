import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
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
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        private int[] weights;
        private int[] beauties;
        private List<List<Integer>> adj;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int w = in.nextInt();
            weights = new int[n];
            beauties = new int[n];
            for (int i = 0; i < n; ++i)
                weights[i] = in.nextInt();
            for (int i = 0; i < n; ++i)
                beauties[i] = in.nextInt();

            adj = new ArrayList<>(n);
            for (int i = 0; i < n; ++i)
                adj.add(new ArrayList<>());

            for (int i = 0; i < m; ++i) {
                int x = in.nextInt() - 1;
                int y = in.nextInt() - 1;
                adj.get(x).add(y);
                adj.get(y).add(x);
            }

            // BFS to find connected components.
            boolean[] visited = new boolean[n];
            List<Group> groups = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                if (!visited[i]) {
                    Group group = new Group();
                    dfs(i, group, visited);
                    groups.add(group);
                }
            }

            // dp[i][j] denotes the maximal beauty obtainable from examining the first i groups
            // with j remaining weight.
            int[][] dp = new int[groups.size() + 1][w + 1];
            for (int[] row : dp)
                Arrays.fill(row, Integer.MIN_VALUE);
            for (int i = 0; i <= w; ++i)  // Have not examined any group.
                dp[0][i] = 0;

            for (int i = 1; i <= groups.size(); ++i) {
                Group current = groups.get(i - 1);
                for (int remain = 0; remain <= w; ++remain) {
                    // Take the entire group.
                    if (remain + current.totalWeight <= w)
                        dp[i][remain] = Math.max(dp[i][remain],
                                dp[i - 1][remain + current.totalWeight] + current.totalBeauty);
                    // Take no member.
                    dp[i][remain] = Math.max(dp[i][remain], dp[i - 1][remain]);
                    // Take a single member.
                    for (int member : current.members) {
                        int weight = weights[member];
                        int beauty = beauties[member];
                        if (remain + weight <= w)
                            dp[i][remain] = Math.max(dp[i][remain], dp[i - 1][remain + weight] + beauty);
                    }
                }
            }

            int result = Integer.MIN_VALUE;
            for (int i = 0; i <= groups.size(); ++i) {
                for (int j = 0; j <= w; ++j)
                    result = Math.max(result, dp[i][j]);
            }

            out.println(result);
        }

        private void dfs(int node, Group group, boolean[] visited) {
            group.totalBeauty += beauties[node];
            group.totalWeight += weights[node];
            group.members.add(node);
            visited[node] = true;

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor])
                    dfs(neighbor, group, visited);
            }
        }

        private class Group {
            private int totalWeight;
            private int totalBeauty;
            private List<Integer> members;

            private Group() {
                totalBeauty = totalWeight = 0;
                members = new ArrayList<>();
            }

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
