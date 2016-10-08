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
public class A {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        private static final int[] MONTH_LENGTHS = {28, 30, 31};
        private static final String[] days = {"monday", "tuesday", "wednesday", "thursday", "friday",
                "saturday", "sunday"};

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String first = in.next();
            String second = in.next();
            int firstIndex = indexOf(days, first);
            int secondIndex = indexOf(days, second);

            for (int length : MONTH_LENGTHS) {
                if ((firstIndex + length) % days.length == secondIndex) {
                    out.println("YES");
                    return;
                }
            }

            out.println("NO");
        }

        private static int indexOf(String[] values, String target) {
            for (int i = 0; i < values.length; ++i) {
                if (values[i].equals(target))
                    return i;
            }
            return -1;
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

