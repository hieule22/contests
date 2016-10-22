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
public class B
{
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
        private final char[] seats = {'f', 'e', 'd', 'a', 'b', 'c'};

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String line = in.next();
            long row = Long.parseLong(line.substring(0, line.length() - 1));
            char seat = line.charAt(line.length() - 1);

            long rank = -1;
            long advances = -1;
            long k = row / 4;
            if (row % 4 == 0) {
                rank = 2 * k;
                advances = row - 3;
            } else if (row % 4 == 1) {
                rank = 2 * k + 1;
                advances = row - 1;
            } else if (row % 4 == 2) {
                rank = 2 * k + 2;
                advances = row - 1;
            } else if (row % 4 == 3) {
                rank = 2 * k + 1;
                advances = row - 3;
            }

            long res = (rank - 1) * seats.length + (indexOf(seats, seat) + 1) + advances;
            out.println(res);
        }

        private static int indexOf(char[] values, char target) {
            for (int i = 0; i < values.length; ++i) {
                if (target == values[i])
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

    }
}
