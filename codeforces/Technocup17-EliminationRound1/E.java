import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.Comparator;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class E {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();
            char[] s = in.next().toCharArray();
            Structure struct = new Structure(s, k);

            int[] relation = new int[s.length];
            Arrays.fill(relation, -1);
            int g = in.nextInt();
            for (int i = 0; i < g; ++i) {
                char[] game = in.next().toCharArray();
                int low = struct.getLower(game);
                int high = struct.getHigher(game);
                for (int j = low; j <= high; ++j) {
                    relation[struct.getIndex(j)] = i;
                }
            }

            for (int i = 0; i < k; ++i) {
                boolean[] seen = new boolean[g];
                int current = i;
                boolean unique = true;
                for (int j = 0; j < n; ++j) {
                    if (relation[current] < 0 || seen[relation[current]]) {
                        unique = false;
                        break;
                    }
                    seen[relation[current]] = true;
                    current = (current + k) % s.length;
                }
                if (unique) {
                    out.println("YES");
                    int start = i;
                    for (int j = 0; j < n; ++j) {
                        out.printf("%d ", relation[start] + 1);
                        start = (start + k) % s.length;
                    }
                    out.println();
                    return;
                }
            }

            out.println("NO");
        }

        private class Structure {
            private Integer[] tree;
            private int[][] ranks;
            private char[] s;

            public Structure(char[] s, int k) {
                this.s = s;
                final int length = s.length;
                final int maxPower = (int) Math.ceil(Math.log(k) / Math.log(2));
                tree = new Integer[length];
                ranks = new int[maxPower + 1][length];

                for (int i = 0; i < length; ++i) {
                    tree[i] = i;
                    ranks[0][i] = s[i] - 'a';
                }

                for (int i = 1; i <= maxPower; ++i) {
                    final int currentPower = i;
                    Comparator<Integer> comparator = (a, b) -> {
                        if (ranks[currentPower - 1][a] == ranks[currentPower - 1][b]) {
                            a = (a + (1 << (currentPower - 1))) % length;
                            b = (b + (1 << (currentPower - 1))) % length;
                        }

                        return ranks[currentPower - 1][a] - ranks[currentPower - 1][b];
                    };
                    Arrays.sort(tree, comparator);

                    // Update ranks
                    int currentRank = ranks[currentPower][tree[0]];
                    for (int j = 1; j < length; ++j) {
                        if (comparator.compare(tree[j], tree[j - 1]) != 0)
                            ++currentRank;
                        ranks[currentPower][tree[j]] = currentRank;
                    }
                }
            }

            public int getIndex(int index) {
                return tree[index];
            }

            public int getLower(char[] target) {
                int low = 0, high = tree.length - 1;
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (compare(mid, target) >= 0)
                        high = mid;
                    else  // compare(mid, target) < 0
                        low = mid + 1;
                }
                return compare(low, target) == 0 ? low : Integer.MAX_VALUE;
            }

            public int getHigher(char[] target) {
                int low = 0, high = tree.length - 1;
                while (low < high) {
                    int mid = low + (high - low + 1) / 2;
                    if (compare(mid, target) <= 0)
                        low = mid;
                    else  // compare(mid, target) > 0
                        high = mid - 1;

                }
                return compare(low, target) == 0 ? low : Integer.MIN_VALUE;
            }

            private int compare(int index, char[] target) {
                int start = tree[index];
                for (char c : target) {
                    if (s[start] != c)
                        return s[start] - c;
                    start = (start + 1) % s.length;
                }
                return 0;
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
