import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
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
public class F {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskF solver = new TaskF();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskF {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] a = new int[n];
            long minimum = Integer.MAX_VALUE;
            long partial = 0;
            for (int i = 0; i < a.length; ++i) {
                a[i] = in.nextInt();
                partial += a[i];
                minimum = Math.min(minimum, partial);
            }

            // minGuess[i] gives the minimum guess that needs i removals
            long[] minGuess = new long[a.length + 1];
            for (int removals = 0; removals < minGuess.length; ++removals) {
                long low = 0, high = -minimum;
                while (low < high) {
                    long mid = low + (high - low) / 2;
                    int temp = analyze(a, mid);
                    if (temp > removals)
                        low = mid + 1;
                    else
                        high = mid;
                }
                minGuess[removals] = low;
            }

            for (int i = 0; i < m; ++i) {
                long guess = in.nextLong();
                int low = 0, high = minGuess.length - 1;
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (minGuess[mid] <= guess) // Need less removals
                        high = mid;
                    else if (minGuess[mid] > guess)  // Need more removals
                        low = mid + 1;
                }
                out.println(low);
            }
        }

        private int analyze(int[] a, long guess) {
            PriorityQueue<Integer> qualityQueue = new PriorityQueue<>();
            int result = 0;
            for (int element : a) {
                if (element < 0)
                    qualityQueue.add(element);
                guess += element;
                if (guess < 0) {
                    result++;
                    int minQuality = qualityQueue.poll();
                    guess -= minQuality;
                }
            }
            return result;
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }
}
