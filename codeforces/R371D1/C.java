import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractCollection;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Collections;
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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; ++i) {
                a[i] = in.nextInt();
            }

            int[][] medians = new int[n][n];
            long[][] updates = new long[n][n];

            for (int i = 0; i < n; ++i) {
                PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Collections.reverseOrder());
                PriorityQueue<Integer> rightHeap = new PriorityQueue<>();
                long leftSum = 0;
                long rightSum = 0;
                for (int j = i; j < n; ++j) {
                    int element = a[j] - (j - i + 1);
                    if (leftHeap.size() < rightHeap.size()) {
                        if (element < rightHeap.peek()) {
                            leftSum += element;
                            leftHeap.add(element);
                        } else {
                            int rightTop = rightHeap.poll();
                            leftSum += rightTop;
                            rightSum += element - rightTop;
                            leftHeap.add(rightTop);
                            rightHeap.add(element);
                        }
                    } else {
                        if (leftHeap.isEmpty() || element > leftHeap.peek()) {
                            rightSum += element;
                            rightHeap.add(element);
                        } else {
                            int leftTop = leftHeap.poll();
                            rightSum += leftTop;
                            leftSum += element - leftTop;
                            rightHeap.add(leftTop);
                            leftHeap.add(element);
                        }
                    }

                    int median;
                    if (leftHeap.size() > rightHeap.size()) {
                        median = leftHeap.peek();
                    } else if (leftHeap.size() < rightHeap.size()) {
                        median = rightHeap.peek();
                    } else {
                        median = (leftHeap.peek() + rightHeap.peek()) / 2;
                    }
                    medians[i][j] = median;
                    updates[i][j] = ((long) median * leftHeap.size() - leftSum)
                            + (rightSum - (long) median * rightHeap.size());
                }
            }

            long[] memo = new long[n];
            int[] minLast = new int[n];

            memo[0] = 0;
            minLast[0] = a[0];

            for (int i = 1; i < n; ++i) {
                memo[i] = updates[0][i];
                minLast[i] = medians[0][i] + i + 1;

                for (int j = 0; j < i; ++j) {
                    if (medians[j + 1][i] < minLast[j]) {
                        continue;
                    }
                    long changes = updates[j + 1][i] + memo[j];
                    int last = medians[j + 1][i] + i - j;
                    if (changes < memo[i]) {
                        memo[i] = changes;
                        minLast[i] = last;
                    } else if (changes == memo[i]) {
                        minLast[i] = Math.min(minLast[i], last);
                    }
                }
            }

            out.println(memo[n - 1]);
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
