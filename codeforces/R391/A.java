import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Map.Entry;
import java.io.BufferedReader;
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
        private static final String TARGET = "Bulbasaur";

        public void solve(int testNumber, InputReader in, PrintWriter out) {
            String text = in.next();
            Map<Character, Integer> frequencies = new HashMap<>();
            for (char c : text.toCharArray()) {
                if (!frequencies.containsKey(c))
                    frequencies.put(c, 1);
                else
                    frequencies.put(c, frequencies.get(c) + 1);
            }

            Map<Character, Integer> targetFrequencies = new HashMap<>();
            for (char c : TARGET.toCharArray()) {
                if (!targetFrequencies.containsKey(c))
                    targetFrequencies.put(c, 1);
                else
                    targetFrequencies.put(c, targetFrequencies.get(c) + 1);
            }

            int result = Integer.MAX_VALUE;
            for (Map.Entry<Character, Integer> entry : targetFrequencies.entrySet()) {
                char key = entry.getKey();
                int value = entry.getValue();
                if (!frequencies.containsKey(key))
                    result = 0;
                else
                    result = Math.min(result, frequencies.get(key) / value);
            }

            out.println(result);
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
