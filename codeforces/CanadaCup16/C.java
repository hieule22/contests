import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        private static final int ROWS = 2;
        private static final int COLS = 13;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            char[] target = in.next().toCharArray();
            int[] positions = new int[26];
            Arrays.fill(positions, -1);
            int first = -1, last = -1;
            for (int i = 0; i < target.length; ++i) {
                char cur = target[i];
                int index = cur - 'A';
                if (positions[index] > -1) {
                    first = positions[index];
                    last = i;
                    break;
                }
                positions[index] = i;
            }

            if (last - first == 1) {
                out.println("Impossible");
                return;
            }

            int separation = last - first - 1;
            char[][] grid = new char[ROWS][COLS];

            grid[0][separation / 2] = target[first];

            // Fill section between first and last
            int counter = first + 1;
            for (int i = separation / 2 - 1; i >= 0; --i) // First row
                grid[0][i] = target[counter++];
            for (int i = 0; i < separation - (separation / 2); ++i) // Second row
                grid[1][i] = target[counter++];

            // Fill section preceding first.
            counter = first - 1;
            for (int i = separation / 2 + 1; i < COLS && counter >= 0; ++i)
                grid[0][i] = target[counter--]; // First row
            for (int i = COLS - 1; i >= 0 && counter >= 0; --i)
                grid[1][i] = target[counter--]; // Wrap around in last row

            // Fill section after last.
            counter = last + 1;
            for (int i = separation - (separation / 2); i < COLS && counter < target.length; ++i)
                grid[1][i] = target[counter++]; // Second row
            for (int i = COLS - 1; i >= 0 && counter < target.length; --i)
                grid[0][i] = target[counter++]; // Wrap back in first row

            // Print result
            for (char[] row : grid) {
                for (char cell : row)
                    out.print(cell);
                out.println();
            }
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

    }
}
