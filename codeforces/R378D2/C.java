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

            int k = in.nextInt();
            int[] b = new int[k];
            for (int i = 0; i < k; ++i) {
                b[i] = in.nextInt();
            }

            List<EatInterval> intervals = new ArrayList<>();
            int last = -1;
            for (int i = 0; i < k; ++i) {  // Go through all new weights.
                int total = 0;
                int first = last + 1;

                // Determine first and last of this eat interval.
                while (total < b[i] && last + 1 < a.length) {
                    ++last;
                    total += a[last];
                }

                // If this sub-array sum does not add up to b[i], reject.
                if (total != b[i]) {
                    out.println("NO");
                    return;
                }

                // Find the heaviest monster in this interval.
                int maxWeight = a[last];
                int maxMonster = last;
                boolean areDifferent = false;
                // Traverse in opposite direction.
                for (int j = last - 1; j >= first; --j) {
                    areDifferent = (areDifferent || (a[j] != a[j + 1]));
                    if (maxWeight < a[j]) {
                        maxWeight = a[j];
                        maxMonster = j;
                    }
                }

                // If all monsters in this interval weigh the same and
                // there is more than one monster, reject.
                if (!areDifferent && last - first != 0) {
                    out.println("NO");
                    return;
                }

                intervals.add(new EatInterval(first, last, maxMonster));
            }

            // If all monsters are not examined, reject.
            if (last != a.length - 1) {
                out.println("NO");
                return;
            }

            out.println("YES");
            // Reconstruct each eat interval in reverse order.
            for (int i = intervals.size() - 1; i >= 0; --i) {
                EatInterval current = intervals.get(i);
                // Eat to the right first.
                for (int j = current.maxMonster + 1; j <= current.last; ++j) {
                    out.printf("%d R\n", current.maxMonster + 1);
                }

                // Eat to the left if there is something to eat to the left.
                if (current.last != current.maxMonster) {
                    int index = current.maxMonster + 1;
                    for (int j = current.maxMonster - 1; j >= current.first; --j) {
                        out.printf("%d L\n", index);
                        --index;  // Shift leftwards.
                    }
                } else if (current.first != current.maxMonster) {  // More than 1 monster.
                    while (a[current.maxMonster] <= a[current.maxMonster - 1]) {
                        --current.maxMonster;
                    }

                    // Eats to the left first.
                    int index = current.maxMonster + 1;
                    for (int j = current.maxMonster - 1; j >= current.first; --j) {
                        out.printf("%d L\n", index);
                        --index;  // Shift leftwards.
                    }

                    // Eats to the right.
                    for (int j = current.maxMonster + 1; j <= current.last; ++j) {
                        out.printf("%d R\n", index);
                    }
                }
            }
        }

        class EatInterval {
            int first;
            int last;
            int maxMonster;

            public EatInterval(int first, int last, int maxMonster) {
                this.first = first;
                this.last = last;
                this.maxMonster = maxMonster;
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
