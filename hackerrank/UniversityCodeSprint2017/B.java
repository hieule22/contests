import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.math.BigInteger;
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
        SeparateTheNumbers solver = new SeparateTheNumbers();
        int testCount = Integer.parseInt(in.next());
        for (int i = 1; i <= testCount; i++)
            solver.solve(i, in, out);
        out.close();
    }

    static class SeparateTheNumbers {
        private static final BigInteger INF = generateInfinity();

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String s = in.next();
            final int length = s.length();
            BigInteger[][] values = new BigInteger[length][length];
            for (int begin = 0; begin < length; ++begin) {
                for (int end = begin; end < length; ++end) {
                    values[begin][end] = new BigInteger(s.substring(begin, end + 1));
                }
            }

            BigInteger[][] memo = new BigInteger[length][length];
            for (BigInteger[] temp : memo) {
                Arrays.fill(temp, INF);
            }

            for (int end = 0; end < length; ++end) {
                // Base case.
                if (s.charAt(0) != '0' || end == 0) {
                    memo[0][end] = values[0][end];
                }

                for (int begin = 1; begin <= end; ++begin) {
                    if (s.charAt(begin) == '0') {
                        continue;
                    }

                    BigInteger nextValue = values[begin][end];
                    for (int lastBegin = 0; lastBegin < begin; ++lastBegin) {
                        BigInteger lastValue = values[lastBegin][begin - 1];
                        BigInteger difference = nextValue.subtract(lastValue);
                        if (difference.equals(BigInteger.ONE)) {
                            memo[begin][end] = min(memo[begin][end], memo[lastBegin][begin - 1]);
                        }
                    }
                }
            }

            BigInteger result = INF;
            for (int begin = 1; begin < length; ++begin) {
                result = min(result, memo[begin][length - 1]);
            }

            if (result.equals(INF)) {
                out.println("NO");
            } else {
                out.println("YES " + result.toString());
            }
        }

        private static BigInteger min(BigInteger a, BigInteger b) {
            return a.compareTo(b) < 0 ? a : b;
        }

        private static BigInteger generateInfinity() {
            char[] digits = new char[32];
            Arrays.fill(digits, '9');
            return new BigInteger(String.valueOf(digits));
        }

    }

    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;
        private static final int BUFFER_SIZE = 32768;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(
                    new InputStreamReader(stream), BUFFER_SIZE);
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
