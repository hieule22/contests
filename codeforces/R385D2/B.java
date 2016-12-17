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
        private static final char PUZZLE = 'X';

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int rows = in.nextInt();
            int cols = in.nextInt();
            char[][] grid = new char[rows][];
            for (int i = 0; i < rows; ++i)
                grid[i] = in.next().toCharArray();

            // Find top left corner.
            int topRow = -1, topCol = -1;
            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    if (grid[r][c] == PUZZLE) {
                        topRow = r;
                        topCol = c;
                        break;
                    }
                }
                if (topRow != -1 && topCol != -1)
                    break;
            }

            // Find rectangle width.
            int width = 0;
            {
                int c = topCol;
                while (c < cols && grid[topRow][c] == PUZZLE) {
                    ++width;
                    ++c;
                }
            }

            // Find rectangle height.
            int height = 0;
            {
                int r = topRow;
                while (r < rows && grid[r][topCol] == PUZZLE) {
                    ++height;
                    ++r;
                }
            }

            // Check that everything inside rectangle boundary is a puzzle.
            for (int r = topRow; r < topRow + height; ++r) {
                for (int c = topCol; c < topCol + width; ++c) {
                    if (grid[r][c] != PUZZLE) {
                        out.println("NO");
                        return;
                    }
                }
            }

            // Check that there are no puzzle outside rectangle boundary.
            int total = 0;
            for (int r = 0; r < rows; ++r) {
                for (int c = 0; c < cols; ++c) {
                    if (grid[r][c] == PUZZLE)
                        ++total;
                }
            }

            if (total == height * width)
                out.println("YES");
            else
                out.println("NO");
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
