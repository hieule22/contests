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
        public static final String PREFIX = "ogo";
        public static final String SUFFIX = "go";
        public static final String FILLER = "***";

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            in.nextInt();
            char[] interview = in.next().toCharArray();
            int index = 0;

            StringBuilder sb = new StringBuilder();
            while (index < interview.length) {
                if (startsWith(interview, index, PREFIX)) {
                    for (int i = 0; i < PREFIX.length(); ++i) {
                        ++index;
                    }
                    while (startsWith(interview, index, SUFFIX)) {
                        for (int i = 0; i < SUFFIX.length(); ++i) {
                            ++index;
                        }
                    }
                    sb.append(FILLER);
                } else {
                    sb.append(interview[index]);
                    ++index;
                }
            }

            out.println(sb.toString());
        }

        private static boolean startsWith(char[] s, int start, String pattern) {
            for (int i = 0; i < pattern.length(); ++i) {
                if (start >= s.length || s[start] != pattern.charAt(i))
                    return false;
                ++start;
            }
            return true;
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
