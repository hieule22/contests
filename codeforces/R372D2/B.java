import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Set;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * B. Complete the Word http://codeforces.com/contest/716/problem/B
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Hieu Le
 */
public class Main {
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
        public static final int ALPHABET_SIZE = 26;

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            char[] word = in.next().toCharArray();
            if (word.length < ALPHABET_SIZE) {
                out.println(-1);
                return;
            }

            // Preprocess first 26 characters
            int[] charFrequency = new int[ALPHABET_SIZE];
            for (int i = 0; i < ALPHABET_SIZE; ++i) {
                if (Character.isAlphabetic(word[i]))
                    charFrequency[word[i] - 'A']++;
            }

            if (isCompleteSubstring(charFrequency)) {
                completeWord(word, 0);
                out.println(new String(word));
                return;
            }

            for (int i = ALPHABET_SIZE; i < word.length; ++i) {
                if (Character.isAlphabetic(word[i]))
                    charFrequency[word[i] - 'A']++;
                if (Character.isAlphabetic(word[i - ALPHABET_SIZE]))
                    charFrequency[word[i - ALPHABET_SIZE] - 'A']--;
                if (isCompleteSubstring(charFrequency)) {
                    completeWord(word, i - ALPHABET_SIZE + 1);
                    out.println(new String(word));
                    return;
                }
            }

            out.println(-1);
        }

        private boolean isCompleteSubstring(int[] frequency) {
            for (int element : frequency) {
                if (element > 1)
                    return false;
            }
            return true;
        }

        private void completeWord(char[] s, int index) {
            Set<Character> alphabet = new HashSet<>();
            for (int i = 0; i < ALPHABET_SIZE; ++i)
                alphabet.add((char) ('A' + i));
            for (int i = index; i < index + ALPHABET_SIZE; ++i) {
                if (Character.isAlphabetic(s[i])) {
                    alphabet.remove(s[i]);
                }
            }
            List<Character> residue = new ArrayList<>(alphabet);
            int position = 0;
            for (int i = index; i < index + ALPHABET_SIZE; ++i) {
                if (!Character.isAlphabetic(s[i])) {
                    s[i] = residue.get(position);
                    position++;
                }
            }

            // Fill remaining section
            for (int i = 0; i < index; ++i) {
                if (!Character.isAlphabetic(s[i])) {
                    s[i] = 'A';
                }
            }

            for (int i = index + ALPHABET_SIZE; i < s.length; ++i) {
                if (!Character.isAlphabetic(s[i])) {
                    s[i] = 'A';
                }
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
