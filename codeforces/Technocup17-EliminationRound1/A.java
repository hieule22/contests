import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Collection;
import java.io.IOException;
import java.util.Queue;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class A {
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
            long a = in.nextLong();
            long b = in.nextLong();

            Queue<Path> frontier = new LinkedList<>();
            frontier.add(new Path(a, ""));
            String result = null;

            while (!frontier.isEmpty()) {
                Path head = frontier.poll();
                if (head.value == b) {
                    result = head.moves;
                    break;
                } else if (head.value < b) {
                    frontier.add(new Path(head.value * 2, head.moves + "0"));
                    frontier.add(new Path(head.value * 10 + 1, head.moves + "1"));
                }
            }

            if (result == null) {
                out.println("NO");
                return;
            }

            long current = a;
            out.println("YES");
            out.println(result.length() + 1);
            out.print(current + " ");
            for (char move : result.toCharArray()) {
                if (move == '0') {
                    current *= 2;
                } else {
                    current = current * 10 + 1;
                }
                out.print(current + " ");
            }
            out.println();
        }

        private class Path {
            private long value;
            private String moves;

            public Path(long value, String moves) {
                this.value = value;
                this.moves = moves;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
