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
public class C {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        private boolean[] visited;
        private List<List<Integer>> graph;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();  // The number of vertices.
            int m = in.nextInt();  // The number of edges.
            int k = in.nextInt();  // The number of governments.

            boolean[] isGovernment = new boolean[n];
            for (int i = 0; i < k; ++i) {
                int v = in.nextInt() - 1;
                isGovernment[v] = true;
            }

            graph = new ArrayList<>(n);
            for (int i = 0; i < n; ++i)
                graph.add(new ArrayList<>());
            for (int i = 0; i < m; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            List<Component> components = new ArrayList<>();
            visited = new boolean[n];
            // Find all components containing a government.
            int maxSize = Integer.MIN_VALUE;
            for (int i = 0; i < n; ++i) {
                if (isGovernment[i]) {
                    Component next = new Component();
                    dfs(i, next);
                    maxSize = Math.max(maxSize, next.size);
                    components.add(next);
                }
            }

            // Find all standalone vertices and map them all to the largest component.
            int standalone = 0;
            for (int i = 0; i < n; ++i) {
                if (!visited[i])
                    ++standalone;
            }

            int total = 0;
            boolean examineMaxComponent = false;
            for (Component component : components) {
                int size = component.size;
                if (component.size == maxSize && !examineMaxComponent) {
                    examineMaxComponent = true;
                    size += standalone;
                }

                total += size * (size - 1) / 2;
            }

            out.println(total - m);
        }

        private void dfs(int node, Component component) {
            visited[node] = true;
            component.size++;
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor])
                    dfs(neighbor, component);
            }
        }

        private class Component {
            private int size;

            private Component() {
                size = 0;
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
