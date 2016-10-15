import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
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
        private static final String[] sizes = {"S", "M", "L", "XL", "XXL", "XXXL"};
        private static Map<String, Integer> sizeToIndex;

        public TaskD() {
            sizeToIndex = new HashMap<>();
            for (int i = 0; i < sizes.length; ++i)
                sizeToIndex.put(sizes[i], i);
        }

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int[] sizeCount = new int[sizes.length];
            for (int i = 0; i < sizeCount.length; ++i)
                sizeCount[i] = in.nextInt();

            int n = in.nextInt();
            String[] ans = new String[n];
            List<Integer>[] orders = new List[5];
            for (int i = 0; i < orders.length; ++i)
                orders[i] = new ArrayList<>();

            for (int i = 0; i < n; ++i) {
                String[] preference = in.next().split(",");
                if (preference.length == 1) {
                    int wantedIndex = sizeToIndex.get(preference[0]);
                    if (sizeCount[wantedIndex] == 0) {
                        out.println("NO");
                        return;
                    } else {
                        ans[i] = preference[0];
                        sizeCount[wantedIndex]--;
                    }
                } else {
                    int firstIndex = sizeToIndex.get(preference[0]);
                    orders[firstIndex].add(i);
                }
            }

            for (int i = 0; i < sizes.length - 1; ++i) {
                for (int index : orders[i]) {
                    if (sizeCount[i] > 0) {
                        ans[index] = sizes[i];
                        sizeCount[i]--;
                    } else if (sizeCount[i + 1] > 0) {
                        ans[index] = sizes[i + 1];
                        sizeCount[i + 1]--;
                    } else {
                        out.println("NO");
                        return;
                    }
                }
            }

            out.println("YES");
            for (String selection : ans) {
                out.println(selection);
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
