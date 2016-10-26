import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Comparator;
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
            int n = in.nextInt();  // Number of games.
            int k = in.nextInt();  // Length of game name.
            String disk = in.next();
            Integer[] orders = construct(disk, k);

            int[] dict = new int[disk.length()];
            Arrays.fill(dict, -1);
            int g = in.nextInt();  // Number of choices.
            for (int i = 0; i < g; ++i) {
                String gameName = in.next();
                int low = getLowerBound(disk, orders, gameName);
                int high = getUpperBound(disk, orders, gameName);
                for (int j = low; j <= high; ++j)
                    dict[orders[j]] = i;
            }

            boolean[] seen = new boolean[g];
            for (int i = 0; i < k; ++i) {
                Arrays.fill(seen, false);
                int start = i;
                boolean valid = true;
                for (int j = 0; j < n; ++j) {
                    int index = dict[start];
                    if (index == -1 || seen[index]) {
                        valid = false;
                        break;
                    }
                    seen[index] = true;
                    start = (start + k) % disk.length();
                }
                if (valid) {
                    out.println("YES");
                    start = i;
                    for (int j = 0; j < n; ++j) {
                        out.printf("%d ", dict[start] + 1);
                        start = (start + k) % disk.length();
                    }
                    return;
                }
            }
            out.println("NO");
        }

        private int getLowerBound(CharSequence s, Integer[] orders, String target) {
            int low = 0, high = orders.length - 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (compare(s, orders[mid], target) < 0)  // s[mid] < target
                    low = mid + 1;
                else  // s[mid] >= target
                    high = mid;
            }
            return (compare(s, orders[low], target) == 0) ? low : Integer.MAX_VALUE;
        }

        private int getUpperBound(CharSequence s, Integer[] orders, String target) {
            int low = 0, high = orders.length - 1;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                if (compare(s, orders[mid], target) > 0)  // s[mid] > target
                    high = mid - 1;
                else  // s[mid] <= target
                    low = mid;
            }
            return (compare(s, orders[low], target) == 0) ? low : Integer.MIN_VALUE;
        }

        private int compare(CharSequence a, int start, CharSequence b) {
            for (int i = 0; i < b.length(); ++i) {
                if (a.charAt(start) != b.charAt(i))
                    return a.charAt(start) - b.charAt(i);
                start = (start + 1) % a.length();
            }
            return 0;
        }

        private Integer[] construct(CharSequence s, int k) {
            int limit = (int) Math.ceil(Math.log(k) / Math.log(2));
            Integer[] result = new Integer[s.length()];
            for (int i = 0; i < result.length; ++i)
                result[i] = i;
            int[][] ranks = new int[limit + 1][s.length()];
            for (int i = 0; i < s.length(); ++i)
                ranks[0][i] = s.charAt(i) - 'a';
            Arrays.sort(result, (o1, o2) -> ranks[0][o1] - ranks[0][o2]);

            for (int i = 1; i < ranks.length; ++i) {
                final int currentPower = i;
                Comparator<Integer> segmentComparator = (a, b) -> {
                    if (ranks[currentPower - 1][a] == ranks[currentPower - 1][b]) {
                        a = (a + (1 << (currentPower - 1))) % s.length();
                        b = (b + (1 << (currentPower - 1))) % s.length();
                    }
                    return ranks[currentPower - 1][a] - ranks[currentPower - 1][b];
                };
                Arrays.sort(result, segmentComparator);
                int currentRank = ranks[i][result[0]] = 0;
                for (int j = 1; j < result.length; ++j) {
                    if (segmentComparator.compare(j, j - 1) != 0)
                        ++currentRank;
                    ranks[i][result[j]] = currentRank;
                }
            }
            return result;
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
