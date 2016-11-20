import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.InputMismatchException;
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
        private static final int MAX_LENGTH = 18;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int t = in.nextInt();

            TrieNode root = new TrieNode();
            for (int i = 0; i < t; ++i) {
                char command = in.nextChar();
                String data = in.next();
                if (command == '+') {
                    insert(root, data);
                } else if (command == '-') {
                    remove(root, data);
                } else {
                    out.println(query(root, data));
                }
            }
        }

        private void insert(TrieNode root, String data) {
            TrieNode current = root;
            for (int i = data.length() - 1; i >= 0; --i) {
                int digit = Character.getNumericValue(data.charAt(i));
                if (digit % 2 == 0) {
                    if (current.even == null)
                        current.even = new TrieNode();
                    ++current.even.frequency;
                    current = current.even;
                } else {
                    if (current.odd == null)
                        current.odd = new TrieNode();
                    ++current.odd.frequency;
                    current = current.odd;
                }
            }

            // Patch zeros at the beginning.
            for (int i = data.length(); i < MAX_LENGTH; ++i) {
                if (current.even == null)
                    current.even = new TrieNode();
                ++current.even.frequency;
                current = current.even;
            }
        }

        private void remove(TrieNode root, String data) {
            TrieNode current = root;
            for (int i = data.length() - 1; i >= 0; --i) {
                int digit = Character.getNumericValue(data.charAt(i));
                if (digit % 2 == 0) {
                    --current.even.frequency;
                    current = current.even;
                } else {
                    --current.odd.frequency;
                    current = current.odd;
                }
            }

            // Remove zeros at the beginning.
            for (int i = data.length(); i < MAX_LENGTH; ++i) {
                --current.even.frequency;
                current = current.even;
            }
        }

        private int query(TrieNode root, String pattern) {
            TrieNode current = root;
            for (int i = pattern.length() - 1; i >= 0; --i) {
                int digit = Character.getNumericValue(pattern.charAt(i));
                if (digit % 2 == 0) {
                    if (current.even == null || current.even.frequency == 0)
                        return 0;
                    current = current.even;
                } else {
                    if (current.odd == null || current.odd.frequency == 0)
                        return 0;
                    current = current.odd;
                }
            }

            // Patch zeros at the beginning.
            for (int i = pattern.length(); i < MAX_LENGTH; ++i) {
                if (current.even == null || current.even.frequency == 0)
                    return 0;
                current = current.even;
            }

            return current.frequency;
        }

        private class TrieNode {
            private int frequency;
            private TrieNode odd;
            private TrieNode even;

            public TrieNode() {
                frequency = 0;
                even = odd = null;
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

        public char nextChar() {
            String s = next();
            if (s.length() != 1) throw new InputMismatchException();
            return s.charAt(0);
        }

    }
}
