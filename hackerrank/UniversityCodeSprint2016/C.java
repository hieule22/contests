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
public class C {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        KindergartenAdventures solver = new KindergartenAdventures();
        solver.solve(1, in, out);
        out.close();
    }

    static class KindergartenAdventures {
        private static final int OFFSET = (int) 2e5;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] times = new int[n];
            for (int i = 0; i < n; ++i)
                times[i] = in.nextInt();

            BinaryIndexedTree tree = new BinaryIndexedTree(2 * OFFSET);
            // Preemptive read.
            for (int i = 0; i < n; ++i) {
                tree.update(OFFSET + times[i] - i, 1);
            }

            int maxCount = tree.read(OFFSET);
            int result = 1;

            for (int i = 1; i < n; ++i) {
                tree.update(OFFSET + times[i - 1], -1);
                if (OFFSET + times[i - 1] - (n - 1) - i <= 0) {
                    throw new RuntimeException("BAD");
                }
                tree.update(OFFSET + times[i - 1] - (n - 1) - i, 1);
                int temp = tree.read(OFFSET - i);
                if (maxCount < temp) {
                    maxCount = temp;
                    result = i + 1;
                }
            }

//        System.err.println(maxCount);
            out.println(result);
        }

        private class BinaryIndexedTree {
            private int capacity;
            private int[] data;

            public BinaryIndexedTree(int capacity) {
                this.capacity = capacity;
                data = new int[capacity + 1];
            }

            public int read(int index) {
                int result = 0;
                while (index > 0) {
                    result += data[index];
                    index -= (index & -index);
                }
                return result;
            }

            public void update(int index, int value) {
                while (index <= capacity) {
                    data[index] += value;
                    index += (index & -index);
                }
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
