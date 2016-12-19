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
        private static final String IMPOSSIBLE = "===";
        private static final char[] LETTERS = {'A', 'C', 'G', 'T'};

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            in.nextInt();  // No op.
            char[] genome = in.next().toCharArray();
            if (genome.length % LETTERS.length != 0) {
                out.println(IMPOSSIBLE);
                return;
            }
            int average = genome.length / LETTERS.length;

            int[] letterCount = new int[LETTERS.length];
            for (char letter : genome) {
                for (int i = 0; i < LETTERS.length; ++i) {
                    if (letter == LETTERS[i])
                        ++letterCount[i];
                }
            }

            for (int count : letterCount) {
                if (count > average) {
                    out.println(IMPOSSIBLE);
                    return;
                }
            }

            for (int i = 0; i < genome.length; ++i) {
                if (genome[i] == '?') {
                    for (int j = 0; j < letterCount.length; ++j) {
                        if (letterCount[j] < average) {
                            genome[i] = LETTERS[j];
                            ++letterCount[j];
                            break;
                        }
                    }
                }
            }

            out.println(new String(genome));
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
