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
        GameOfTwoStacks solver = new GameOfTwoStacks();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class GameOfTwoStacks {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int aLength = in.nextInt();
            int bLength = in.nextInt();
            long maximum = in.nextInt();

            long[] aPrefixes = new long[aLength + 1];
            for (int i = 1; i <= aLength; ++i) {
                aPrefixes[i] = aPrefixes[i - 1] + in.nextInt();
            }
            long[] bPrefixes = new long[bLength + 1];
            for (int i = 1; i <= bLength; ++i) {
                bPrefixes[i] = bPrefixes[i - 1] + in.nextInt();
            }

            int result = 0;
            for (int aCount = 0; aCount <= aLength; ++aCount) {
                if (aPrefixes[aCount] > maximum) {
                    break;
                }

                long residue = maximum - aPrefixes[aCount];
                int low = 0, high = bLength;
                while (low < high) {
                    int mid = low + (high - low + 1) / 2;
                    if (bPrefixes[mid] <= residue) {
                        low = mid;
                    } else {
                        high = mid - 1;
                    }
                }

                result = Math.max(result, aCount + low);
            }

            out.println(result);
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
