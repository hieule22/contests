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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();  // Number of potions.
            int m = in.nextInt();  // Number of first type spells.
            int k = in.nextInt();  // Number of second type spells.
            int x = in.nextInt();  // Number of seconds to prepare one potion.
            int s = in.nextInt();  // Number of manapoints.

            int[] a = new int[m];  // Number of seconds to prepare one potion with first type spell.
            for (int i = 0; i < m; ++i)
                a[i] = in.nextInt();
            int[] b = new int[m];  // Number of manapoints for first type spell.
            for (int i = 0; i < m; ++i)
                b[i] = in.nextInt();

            int[] c = new int[k];
            for (int i = 0; i < k; ++i)
                c[i] = in.nextInt();
            int[] d = new int[k];
            for (int i = 0; i < k; ++i)  // Number of manapoints for second type spell.
                d[i] = in.nextInt();

            // Don't use any spell.
            long result = (long) x * n;

            // Use only first type spell.
            int minTime = Integer.MAX_VALUE;
            for (int i = 0; i < a.length; ++i) {
                if (b[i] <= s)
                    minTime = Math.min(minTime, a[i]);
            }
            result = Math.min(result, (long) minTime * n);

            // Use only second spell.
            for (int i = c.length - 1; i >= 0; --i) {
                if (d[i] <= s) {
                    int offset = n - c[i];
                    result = Math.min(result, (long) offset * x);
                    break;
                }
            }

            // Use both spells.
            for (int i = 0; i < a.length; ++i) {
                if (b[i] <= s) {
                    int residue = s - b[i];
                    int low = 0, high = d.length - 1;
                    while (low < high) {
                        int mid = low + (high - low + 1) / 2;
                        if (d[mid] <= residue)
                            low = mid;
                        else
                            high = mid - 1;
                    }
                    if (d[low] <= residue) {
                        int offset = n - c[low];
                        result = Math.min(result, (long) offset * a[i]);
                    }
                }
            }

            out.println(result);
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
