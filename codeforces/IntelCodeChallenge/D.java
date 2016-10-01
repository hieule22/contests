import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
import java.util.Collections;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * http://codeforces.com/contest/722/problem/D
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
        private Map<Integer, Integer> next;

        public TaskD() {
            next = new HashMap<>();
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] y = new int[n];
            for (int i = 0; i < n; ++i) {
                y[i] = in.nextInt();
            }

            Map<Integer, Integer> count = new HashMap<>();
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                int current = y[i];
                while (current != 0) {
                    if (!count.containsKey(current)) {
                        values.add(current);
                        count.put(current, 1);
                    } else {
                        count.put(current, count.get(current) + 1);
                    }
                    current = getNext(current);
                }
            }
            Collections.sort(values, Collections.reverseOrder());

            int low = 0, high = values.size() - 1;
            while (low < high) {
                int mid = low + (high - low + 1) / 2;
                List<Integer> result = isPossible(mid, count, n, values);
                if (result != null) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }

            List<Integer> result = isPossible(low, count, n, values);
            for (int element : result)
                out.print(element + " ");
            out.println();
        }

        private int getNext(int current) {
            if (next.containsKey(current)) {
                return next.get(current);
            }
            next.put(current, current / 2);
            return next.get(current);
        }

        private List<Integer> isPossible(int limitIndex, Map<Integer, Integer> count, int target,
                                         List<Integer> values) {
            int progress = 0;
            List<Integer> elements = new ArrayList<>();
            for (int i = limitIndex; i < values.size(); ++i) {
                int currentLimit = values.get(i);
                if (count.get(currentLimit) == 0) {
                    continue;
                }
                progress++;
                elements.add(currentLimit);
                int current = currentLimit;
                while (current != 0) {
                    count.put(current, count.get(current) - 1);
                    current = getNext(current);
                }
                if (progress == target) {
                    break;
                }
            }

            // Restore
            for (int element : elements) {
                int current = element;
                while (current != 0) {
                    count.put(current, count.get(current) + 1);
                    current = getNext(current);
                }
            }

            if (elements.size() == target) {
                return elements;
            } else {
                return null;
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

