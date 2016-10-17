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
public class F {
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
        public static final int MAX_N = 200000;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] prefix = new int[MAX_N + 1];
            for (int i = 0; i < n; ++i) {
                int a = in.nextInt();
                prefix[a]++;
            }

            for (int i = 1; i < prefix.length; ++i)
                prefix[i] += prefix[i - 1];

            long result = Long.MIN_VALUE;
            for (int i = 1; i < prefix.length; ++i) {
                if (prefix[i] == prefix[i - 1])
                    continue;  // No such element
                long temp = 0;
                for (int j = i; j < prefix.length; j += i) {
                    int limit = Math.min(j + i - 1, prefix.length - 1);
                    int count = prefix[limit] - prefix[j - 1];
                    temp += (long) count * j;
                }
                result = Math.max(result, temp);
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
