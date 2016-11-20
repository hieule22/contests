import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class D {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        private static char HIT = '1';

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();  // The length of grid.
            int a = in.nextInt();  // The number of ships.
            int b = in.nextInt();  // The length of ship.
            int k = in.nextInt();  // The number of shots.
            char[] cells = in.next().toCharArray();

            int maxShipCount = 0;
            int last = 0;
            List<Interval> intervals = new ArrayList<>();
            for (int i = 0; i < cells.length; ++i) {
                if (cells[i] == HIT) {
                    if (i > last)
                        intervals.add(new Interval(last, i - 1));
                    int between = i - last;
                    maxShipCount += (between / b);
                    last = i + 1;
                }
            }

            if (cells.length > last)
                intervals.add(new Interval(last, cells.length - 1));
            maxShipCount += (cells.length - last) / b;
//        System.err.printf("Max ship count: %d\n", maxShipCount);

            List<Integer> shots = new ArrayList<>();
            int remain = maxShipCount;
            for (Interval interval : intervals) {
                if (remain < a) break;
                int addition = process(interval, b, shots, remain, a);
                remain -= addition;
            }

            out.println(shots.size());
            for (int shot : shots)
                out.print((shot + 1) + " ");
            out.println();
        }

        private int process(Interval interval, int length, List<Integer> shots, int remain, int a) {
            // Start index of next ship to cover.
            int start = interval.begin;
            int count = 0;
            while (start + length - 1 <= interval.end) { // Check if remaining section needs coverage.
                int shot = start + length - 1;
                shots.add(shot);
                ++count;
                if (remain - count < a)
                    return count;
                start = shot + 1;
            }
            return count;
        }

        private class Interval {
            private int begin;
            private int end;

            public Interval(int begin, int end) {
                this.begin = begin;
                this.end = end;
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
