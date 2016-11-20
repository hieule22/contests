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
            int rows = in.nextInt();
            int cols = in.nextInt();

            int[][] cells = new int[rows][cols];
            int[][] rowPrefix = new int[rows][cols];
            int[][] colPrefix = new int[rows][cols];

            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    cells[r][c] = in.nextInt();
                    if (cells[r][c] == 1) {
                        rowPrefix[r][c] = colPrefix[r][c] = 1;
                    }
                }
            }

            // Add all row prefixes.
            for (int c = 0; c < cols; ++c) {
                for (int r = 1; r < rows; ++r) {
                    rowPrefix[r][c] += rowPrefix[r - 1][c];
                }
            }

            // Add all column prefixes
            for (int r = 0; r < rows; ++r) {
                for (int c = 1; c < cols; ++c) {
                    colPrefix[r][c] += colPrefix[r][c - 1];
                }
            }

            int result = 0;
            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    if (cells[r][c] == 1)
                        continue;
                    // Check up direction.
                    if (rowPrefix[r][c] > 0)
                        ++result;
                    // Check down direction.
                    if (rowPrefix[rows - 1][c] > rowPrefix[r][c])
                        ++result;
                    // Check left direction.
                    if (colPrefix[r][c] > 0)
                        ++result;
                    // Check right direction.
                    if (colPrefix[r][cols - 1] > colPrefix[r][c])
                        ++result;
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
