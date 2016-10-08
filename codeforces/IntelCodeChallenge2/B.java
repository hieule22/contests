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
            int m = in.nextInt();
            int[][] table = new int[n][m];

            boolean noColumnSwap = true;
            for (int i = 0; i < n; ++i) {
                int inversions = 0;
                for (int j = 0; j < m; ++j) {
                    table[i][j] = in.nextInt();
                    if (table[i][j] != j + 1)
                        inversions++;
                }
                if (inversions > 2)
                    noColumnSwap = false;
                if (inversions > 4) {
                    out.println("NO");
                    return;
                }
            }

            if (noColumnSwap) {
                out.println("YES");
                return;
            }

            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    boolean possible = true;
                    for (int[] row : table) {
                        swap(row, i, j);
                        int inversions = 0;
                        for (int k = 0; k < row.length; ++k) {
                            if (row[k] != k + 1)
                                inversions++;
                        }
                        swap(row, i, j);
                        if (inversions > 2) {
                            possible = false;
                            break;
                        }
                    }

                    if (possible) {
                        out.println("YES");
                        return;
                    }
                }
            }

            out.println("NO");
        }

        private static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
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

