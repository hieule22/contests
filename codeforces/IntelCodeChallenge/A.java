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
 * http://codeforces.com/contest/722/problem/A
 * @author Hieu Le
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int format = in.nextInt();
            char[] input = in.next().toCharArray();
            int hour = Integer.parseInt(String.valueOf(input[0]) + input[1]);
            if (format == 12) {
                if (hour == 0) {
                    input[0] = '1';
                } else if (hour > 12 && input[1] == '0') {
                    input[0] = '1';
                } else if (hour > 12) {
                    input[0] = '0';
                }
            } else {
                if (hour > 23) {
                    input[0] = '0';
                }
            }

            int minute = Integer.parseInt(String.valueOf(input[3]) + input[4]);
            if (minute > 59) {
                input[3] = '0';
            }

            out.println(new String(input));
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

