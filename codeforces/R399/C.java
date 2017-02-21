import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        private static final int MAX_STRENGTH = 1023;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int nRangers = in.nextInt();
            int nOperations = in.nextInt();
            int magic = in.nextInt();

            int[][] buckets = new int[2][MAX_STRENGTH + 1];
            for (int i = 0; i < nRangers; ++i) {
                ++buckets[0][in.nextInt()];
            }

            for (int i = 0; i < nOperations; ++i) {
                int current = (i % 2);
                int nSoFar = 0;
                for (int strength = 0; strength <= MAX_STRENGTH; ++strength) {
                    if (buckets[current][strength] > 0) {
                        int total = buckets[current][strength];
                        int nUpgrades;
                        if (nSoFar % 2 == 0) {  // Take the odd rangers.
                            nUpgrades = (total + 1) / 2;
                        } else {  // Take the even soldiers.
                            nUpgrades = total / 2;
                        }

                        int upgradedStrength = (strength ^ magic);
                        buckets[1 - current][upgradedStrength] += nUpgrades;
                        buckets[1 - current][strength] += total - nUpgrades;
                        nSoFar += total;
                        // Reset this bucket.
                        buckets[current][strength] = 0;
                    }
                }
            }

            int minStrength = -1;
            int current = (nOperations % 2);
            for (int i = 0; i <= MAX_STRENGTH; ++i) {
                if (buckets[current][i] > 0) {
                    minStrength = i;
                    break;
                }
            }

            int maxStrength = -1;
            for (int i = MAX_STRENGTH; i >= 0; --i) {
                if (buckets[current][i] > 0) {
                    maxStrength = i;
                    break;
                }
            }

            out.printf("%d %d\n", maxStrength, minStrength);
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
