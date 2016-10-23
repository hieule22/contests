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
public class TarjanSCC {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskF solver = new TaskF();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskF {
        private List<List<Edge>> graph;
        private int[] dfsNum;
        private int[] dfsLow;
        private boolean[] visited;
        private Stack stack;
        private int dfsCounter;
        private int maxSize;
        private int maxRoot;
        private Pair[] directedEdges;
        private static final int UNVISITED = -1;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            graph = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                graph.add(new ArrayList<>());

            directedEdges = new Pair[m];
            for (int i = 0; i < m; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                graph.get(u).add(new Edge(v, i));
                graph.get(v).add(new Edge(u, i));
                directedEdges[i] = new Pair(u, v);
            }

            dfsNum = new int[n];
            Arrays.fill(dfsNum, UNVISITED);
            dfsLow = new int[n];
            visited = new boolean[n];
            stack = new Stack(n);
            dfsCounter = 0;
            maxSize = maxRoot = -1;
            tarjanSCC(-1, 0);

            Arrays.fill(dfsNum, UNVISITED);
            Arrays.fill(dfsLow, 0);
            Arrays.fill(visited, false);
            dfsCounter = 0;
            tarjanSCC(-1, maxRoot);

            out.println(maxSize);
            for (Pair edge : directedEdges) {
                out.printf("%d %d\n", edge.first + 1, edge.second + 1);
            }
        }

        private void tarjanSCC(int parent, int u) {
            dfsLow[u] = dfsNum[u] = dfsCounter++;  // dfsLow[u] <= dfsNum[u]
            stack.push(u);
            visited[u] = true;
            for (Edge edge : graph.get(u)) {
                int v = edge.neighbor;
                int index = edge.index;
                if (v == parent) continue;
                if (dfsNum[v] == UNVISITED) {
                    tarjanSCC(u, v);
                    directedEdges[index] = new Pair(v, u);
                } else {
                    directedEdges[index] = new Pair(u, v);
                }
                if (visited[v]) {// Condition for update
                    dfsLow[u] = Math.min(dfsLow[v], dfsLow[u]);
                }
            }

            if (dfsLow[u] == dfsNum[u]) {  // This is the root of an SCC
                int currentSize = 0;
                int v;
                do {
                    v = stack.pop();
                    visited[v] = false;
                    currentSize++;
                } while (u != v);

                // Update largest strongly connected component.
                if (maxSize < currentSize) {
                    maxSize = currentSize;
                    maxRoot = u;
                }
            }
        }

        private class Pair {
            private int first;
            private int second;

            public Pair(int first, int second) {
                this.first = first;
                this.second = second;
            }

        }

        private class Edge {
            int neighbor;
            int index;

            public Edge(int neighbor, int index) {
                this.neighbor = neighbor;
                this.index = index;
            }

        }

        private class Stack {
            private int[] data;
            private int size = 0;

            public Stack(int capacity) {
                data = new int[capacity];
            }

            public void push(int value) {
                data[size] = value;
                size++;
            }

            public int pop() {
                size--;
                return data[size];
            }

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
