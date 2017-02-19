import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
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
        TheStoryOfATree solver = new TheStoryOfATree();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class TheStoryOfATree {
        private List<List<Integer>> graph;
        private List<Set<Integer>> guessedChildren;
        private int[] upward;
        private int[] downward;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int nNodes = in.nextInt();
            graph = new ArrayList<>(nNodes);
            guessedChildren = new ArrayList<>(nNodes);
            for (int i = 0; i < nNodes; ++i) {
                graph.add(new ArrayList<>());
                guessedChildren.add(new HashSet<>());
            }

            for (int i = 0; i < nNodes - 1; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int nGuesses = in.nextInt();
            int threshold = in.nextInt();
            for (int i = 0; i < nGuesses; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                guessedChildren.get(u).add(v);
            }

            upward = new int[nNodes];
            downward = new int[nNodes];
            getUpward(-1, 0);
            for (int child : graph.get(0))
                getDownward(0, child);

            int nWinRoots = 0;
            for (int i = 0; i < nNodes; ++i) {
                if (upward[i] + downward[i] >= threshold) {
                    ++nWinRoots;
                }
            }

            int gcd = getGCD(nWinRoots, nNodes);
            out.printf("%d/%d\n", nWinRoots / gcd, nNodes / gcd);
        }

        private void getUpward(int parent, int node) {
            for (int child : graph.get(node)) {
                if (child == parent) {
                    continue;
                }
                if (guessedChildren.get(node).contains(child)) {
                    ++upward[node];
                }
                getUpward(node, child);
                upward[node] += upward[child];
            }
        }

        private void getDownward(int parent, int node) {
            if (guessedChildren.get(node).contains(parent)) {
                ++downward[node];
            }

            downward[node] += downward[parent];
            int increase = upward[parent] - upward[node];
            if (guessedChildren.get(parent).contains(node)) {
                --increase;
            }
            downward[node] += increase;

            for (int child : graph.get(node)) {
                if (child == parent) {
                    continue;
                }
                getDownward(node, child);
            }
        }

        private static int getGCD(int a, int b) {
            if (b == 0) {
                return a;
            }
            return getGCD(b, a % b);
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
