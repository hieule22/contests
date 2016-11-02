import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class B {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        private int[] parents;
        private List<Integer> roots;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            parents = new int[n + 1];
            for (int i = 1; i <= n; ++i)
                parents[i] = in.nextInt();
            int[] ranks = new int[n + 1];

            roots = new ArrayList<>();
            int[] visited = new int[n + 1];
            int order = 0;
            for (int i = 1; i <= n; ++i) {
                if (ranks[i] == 0) {
                    ++order;
                    ranks[i] = dfs(i, ranks, visited, order);
                }
            }

            int mainRoot = -1;
            for (int root : roots) {
                if (parents[root] == root) { // No need to reset this root node.
                    mainRoot = root;
                    break;
                }
            }
            if (mainRoot == -1) {
                mainRoot = roots.get(0);
            }

            int result = 0;
            for (int root : roots) {
                if (root != mainRoot || parents[root] != mainRoot) {
                    parents[root] = mainRoot;
                    ++result;
                }
            }

            out.println(result);
            for (int i = 1; i <= n; ++i)
                out.printf("%d ", parents[i]);
            out.println();
        }

        private int dfs(int node, int[] ranks, int[] visited, int order) {
            int parent = parents[node];
            visited[node] = order;

            if (parent == node) {  // Original root.
                roots.add(node);
                return ranks[node] = node;
            }

            if (visited[parent] == order) {  // Back edge on this BFS spanning tree.
                roots.add(node);
                return ranks[node] = node;
            }

            if (ranks[parent] > 0) {  // Parent is part of existing subtree.
                return ranks[node] = ranks[parent];
            }

            return ranks[node] = dfs(parent, ranks, visited, order);
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
