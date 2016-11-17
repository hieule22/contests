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
        ArrayConstruction solver = new ArrayConstruction();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class ArrayConstruction {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int s = in.nextInt();
            int t = in.nextInt();

            int[][] coeff = new int[2][n];
            for (int i = 0; i < n; ++i)
                coeff[0][i] = n - i;
            for (int i = 1; i < n; ++i)
                coeff[1][i] = i * (n - i);

            int[] result = new int[n];
            boolean solvable = false;
            for (int a = 0; a <= s; ++a) {
                if (a * coeff[0][0] > s)
                    break;
                result[0] = a;
                if (check(coeff, result, 1, s - a * coeff[0][0], t)) {
                    solvable = true;
                    break;
                }
            }

            if (!solvable) {
                out.println(-1);
            } else {
                int current = 0;
                for (int delta : result) {
                    current += delta;
                    out.printf("%d ", current);
                }
                out.println();
            }
        }

        private boolean check(int[][] coeff, int[] result, int index, int s, int t) {
            if (index == result.length) {
                return s == 0 && t == 0;
            }

            for (int i = 0; i <= s; ++i) {
                if (i * coeff[0][index] > s || i * coeff[1][index] > t)
                    return false;
                result[index] = i;
                if (check(coeff, result, index + 1, s - i * coeff[0][index], t - i * coeff[1][index]))
                    return true;
            }

            return false;
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
