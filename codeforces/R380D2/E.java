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
            int chief = in.nextInt();
            int[] frequencies = new int[n];
            int result = 0;
            for (int i = 0; i < n; ++i) {
                int a = in.nextInt();
                if (i == chief - 1 && a != 0) {
                    ++result;
                    a = 0;
                }
                ++frequencies[a];
            }

            int residue = frequencies[0] - 1;
            result += residue;
            frequencies[0] -= residue;
            frequencies[n - 1] += residue;

            int last = findLast(frequencies, n - 1);
            for (int i = 0; i < last; ++i) {
                if (frequencies[i] == 0) {
                    ++frequencies[i];
                    --frequencies[last];
                    // Make changes only when residue has run out.
                    if (residue == 0)
                        ++result;
                    else
                        --residue;
                }
                last = findLast(frequencies, last);
            }

            out.println(result);
        }

        private int findLast(int[] frequencies, int current) {
            for (int i = current; i >= 0; --i) {
                if (frequencies[i] > 0)
                    return i;
            }
            return -1;
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
