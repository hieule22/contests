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
public class B {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] left = new int[n];
            int[] right = new int[n];

            int totalLeft = 0, totalRight = 0;
            for (int i = 0; i < n; ++i) {
                left[i] = in.nextInt();
                totalLeft += left[i];
                right[i] = in.nextInt();
                totalRight += right[i];
            }

            int maxBeauty = Math.abs(totalLeft - totalRight);
            int column = 0;
            for (int i = 0; i < n; ++i) {
                int tempTotalLeft = totalLeft - left[i] + right[i];
                int tempTotalRight = totalRight - right[i] + left[i];
                int tempBeauty = Math.abs(tempTotalLeft - tempTotalRight);
                if (tempBeauty > maxBeauty) {
                    maxBeauty = tempBeauty;
                    column = i + 1;
                }
            }

            out.println(column);
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
