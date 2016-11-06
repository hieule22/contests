import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
public class FRIEMEET {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        FriendsMeeting solver = new FriendsMeeting();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class FriendsMeeting {
        private long total = 0;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            List<List<Edge>> graph = new ArrayList<>(n);
            for (int i = 0; i < n; ++i)
                graph.add(new ArrayList<>());
            for (int i = 0; i < n - 1; ++i) {
                int u = in.nextInt() - 1;
                int v = in.nextInt() - 1;
                int w = in.nextInt();
                graph.get(u).add(new Edge(v, w));
                graph.get(v).add(new Edge(u, w));
            }

            Set<Integer> choices = new HashSet<>();
            for (int i = 0; i < m; ++i)
                choices.add(in.nextInt() - 1);

            int[] parents = new int[n];
            Arrays.fill(parents, -1);
            total = 0;
            Result result = postOrder(0, graph, parents, choices);

            long denominator = (long) result.numNodes * result.numNodes;
            long gcd = findGCD(total, denominator);
            out.printf("%d %d\n", total / gcd, denominator / gcd);
        }

        private Result postOrder(int node, List<List<Edge>> graph, int[] parents,
                                 Set<Integer> choices) {
            long sum = 0;
            int totalNumNodes = 0;
            List<Long> partials = new ArrayList<>();
            List<Integer> partialNumNodes = new ArrayList<>();
            for (Edge edge : graph.get(node)) {
                int child = edge.neighbor;
                int weight = edge.weight;
                if (parents[node] == child)
                    continue;
                parents[child] = node;
                Result result = postOrder(child, graph, parents, choices);
                long partial = result.sumWeight + (long) weight * result.numNodes;
                sum += partial;
                totalNumNodes += result.numNodes;
                partials.add(partial);
                partialNumNodes.add(result.numNodes);
            }

            boolean isChoice = choices.contains(node);
            long result = 0;
            if (isChoice)
                totalNumNodes++;

            for (int i = 0; i < partials.size(); ++i) {
                long temp = partials.get(i) * (totalNumNodes - partialNumNodes.get(i)) * 2;
                result += temp;
            }
            total += result;

            return new Result(sum, totalNumNodes);
        }

        private long findGCD(long a, long b) {
            long temp;
            while (b != 0) {
                temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        }

        private class Edge {
            private int neighbor;
            private int weight;

            public Edge(int neighbor, int weight) {
                this.neighbor = neighbor;
                this.weight = weight;
            }

        }

        private class Result {
            private long sumWeight;
            private int numNodes;

            public Result(long sumWeight, int numNodes) {
                this.sumWeight = sumWeight;
                this.numNodes = numNodes;
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
