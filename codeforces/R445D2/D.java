import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.util.Collections;
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
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            List<String> words = new ArrayList<>();

            for (int i = 0; i < n; ++i) {
                String word = in.next();
                if (!checkDuplicates(word)) {
                    out.println("NO");
                    return;
                }

                words.add(word);
            }

            for (char c = 'a'; c <= 'z'; ++c) {
                String current = "" + c;
                int focus = 0;
                boolean seen = false;
                List<String> frontier = new ArrayList<>();
                for (String word : words) {
                    int index = word.indexOf(c);
                    if (index != -1) {
                        seen = true;
                        if (!match(word, 0, index - 1, current, 0, focus - 1) ||
                                !match(word, index + 1, word.length() - 1, current, focus + 1, current.length() - 1)) {
                            out.println("NO");
                            return;
                        }

                        String prefix = focus > index ? current.substring(0, focus) : word.substring(0, index);
                        String suffix = (current.length() - focus) > (word.length() - index) ? current.substring(focus + 1) : word.substring(index + 1);
                        current = prefix + c + suffix;
                        focus = Math.max(focus, index);

                        if (!checkDuplicates(current)) {
                            out.println("NO");
                            return;
                        }
                    } else {
                        frontier.add(word);
                    }
                }

                if (seen) {
                    frontier.add(current);
                    words = frontier;
                }
            }

            Collections.sort(words);
            for (String word : words)
                out.print(word);
            out.println();
        }

        private boolean match(String a, int as, int ae, String b, int bs, int be) {
            String first = a.substring(as, ae + 1);
            String second = b.substring(bs, be + 1);
            return first.contains(second) || second.contains(first);
        }

        private boolean checkDuplicates(String s) {
            boolean[] seen = new boolean[26];
            for (int i = 0; i < s.length(); ++i) {
                int index = s.charAt(i) - 'a';
                if (seen[index]) {
                    return false;
                }
                seen[index] = true;
            }

            return true;
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

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
