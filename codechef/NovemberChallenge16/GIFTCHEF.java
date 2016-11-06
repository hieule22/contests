import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Hieu Le
 */
public class GIFTCHEF {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        GiftAndChef solver = new GiftAndChef();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class GiftAndChef {
        public static long MOD = (long) 1e9 + 7;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String text = in.next();
            String pattern = in.next();
            List<Integer> matches = kmp(text, pattern);
            long[] memo = new long[text.length() + 1];
            int pointer = matches.size() - 1;
            for (int i = memo.length - 2; i >= 0; --i) {
                if (pointer >= 0 && i == matches.get(pointer)) {
                    int last = matches.get(pointer) + pattern.length();
                    memo[i] = (1 + memo[i + 1] + memo[last]) % MOD;
                    pointer--;
                } else {
                    memo[i] = memo[i + 1];
                }
            }

            out.println(memo[0]);
        }

        private static List<Integer> kmp(String text, String pattern) {
            int[] backTable = new int[text.length() + 1];
            int i = 0, j = -1;
            backTable[0] = -1;
            while (i < pattern.length()) {
                while (j >= 0 && pattern.charAt(i) != pattern.charAt(j))
                    j = backTable[j];
                ++i;
                ++j;
                backTable[i] = j;
            }

            List<Integer> matches = new ArrayList<>();
            i = j = 0;
            while (i < text.length()) {
                while (j >= 0 && text.charAt(i) != pattern.charAt(j))
                    j = backTable[j];
                ++i;
                ++j;
                if (j == pattern.length()) {
                    matches.add(i - j);
                    j = backTable[j];
                }
            }
            return matches;
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
