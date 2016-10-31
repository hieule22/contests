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
        private static final char[] VOWELS = {'A', 'E', 'I', 'O', 'U', 'Y'};

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            char[] paper = in.next().toCharArray();
            int maxGap = Integer.MIN_VALUE;
            int last = -1;
            for (int i = 0; i < paper.length; ++i) {
                if (isVowel(paper[i])) {
                    maxGap = Math.max(maxGap, i - last);
                    last = i;
                }
            }
            maxGap = Math.max(maxGap, paper.length - last);
            out.println(maxGap);
        }

        private static boolean isVowel(char c) {
            for (char vowel : VOWELS) {
                if (c == vowel)
                    return true;
            }
            return false;
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
