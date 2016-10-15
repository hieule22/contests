import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.util.regex.Pattern;
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
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String cheque = in.next();
            String delimitingRegex = "[a-z]+";
            Pattern pattern = Pattern.compile(delimitingRegex);
            String[] prices = pattern.split(cheque);

            long total = 0;
            for (String price : prices) {
                if (!price.isEmpty())
                    total += standardize(price);
            }
            out.println(prettify(total));
        }

        private long standardize(String number) {
            int decimals = 0;
            int limit = number.length();
            if (number.length() >= 3 && number.charAt(number.length() - 3) == '.') {
                decimals = Integer.parseInt(number.substring(number.length() - 2));
                limit = number.length() - 3;

            }
            int integrals = 0;
            for (int i = 0; i < limit; ++i) {
                char digit = number.charAt(i);
                if (digit != '.') {
                    integrals = integrals * 10 + Character.getNumericValue(digit);
                }
            }

            return (long) integrals * 100 + decimals;
        }

        private String prettify(long number) {
            String decimals = null;
            int cents = (int) (number % 100);
            if (cents != 0) {
                decimals = Integer.toString(cents);
                if (decimals.length() == 1)
                    decimals = "0" + decimals;
            }
            number /= 100;
            String integrals = Integer.toString((int) number);
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for (int i = integrals.length() - 1; i >= 0; --i) {
                sb.append(integrals.charAt(i));
                count++;
                if (count % 3 == 0 && count != integrals.length())
                    sb.append('.');
            }

            if (decimals == null) {
                return sb.reverse().toString();
            } else {
                return sb.reverse().toString() + "." + decimals;
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

    }
}
