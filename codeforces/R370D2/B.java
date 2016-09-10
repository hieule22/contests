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
public class B {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Solution solver = new Solution();
        solver.solve(1, in, out);
        out.close();
    }

    static class Solution {
        public static final char[] direction = {'U', 'D', 'L', 'R'};
      
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            char[] moves = in.next().toCharArray();
            if (moves.length % 2 == 1) {
                out.println(-1);
                return;
            }

            long[] cnt = new long[4];
            for (char move : moves) {
              for (int i = 0; i < direction.length; ++i) {
                if (move == direction[i])
                  ++cnt[i];
              }
            }

            long result = Math.abs(cnt[0] - cnt[1]) + Math.abs(cnt[2] - cnt[3]);
            out.println(result / 2);
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

