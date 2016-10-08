import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.io.BufferedReader;
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
            int m = in.nextInt();
            char[] s = in.next().toCharArray();
            Map<Character, List<Integer>> alphabet = new HashMap<>();
            for (int i = 0; i < s.length; ++i) {
                char c = s[i];
                if (!alphabet.containsKey(c))
                    alphabet.put(c, new ArrayList<>());
                alphabet.get(c).add(i);
            }

            int[] covered = new int[s.length - m + 1];
            char[] frequency = new char[26];
            List<Interval> intervals = new ArrayList<>();
            intervals.add(new Interval(0, s.length - m));
            for (int i = 0; i < 26; ++i) {
                char c = (char) ('a' + i);
                int[] frontier = new int[covered.length];
                if (!alphabet.containsKey(c)) continue;
                for (int pos : alphabet.get(c)) {
                    int left = Math.max(0, pos - m + 1);
                    frontier[left] = 1;
                    if (pos + 1 < frontier.length)
                        frontier[pos + 1] = -1;
                    frequency[i]++;
                }

                if (check(frontier, covered)) {
                    frequency[i] = 0;
                    int iterator = 0;
                    List<Integer> positions = alphabet.get(c);
                    for (Interval interval : intervals) {
                        int begin = interval.begin;
                        while (begin <= interval.end) {
                            while (iterator < positions.size() &&
                                    positions.get(iterator) - m + 1 <= begin)
                                iterator++;
                            iterator--;
                            frequency[i]++;
                            begin = positions.get(iterator) + m;
                        }
                    }
                    break;
                } else {
                    List<Interval> newIntervals = new ArrayList<>();
                    Interval last = new Interval(Integer.MAX_VALUE, Integer.MIN_VALUE);
                    for (int j = 0; j < frontier.length; ++j) {
                        if (frontier[j] == 0 && covered[j] == 0) {
                            if (last.begin > j) last.begin = j;
                            if (last.end < j) last.end = j;
                        } else {
                            if (last.begin <= last.end) {
                                newIntervals.add(new Interval(last.begin, last.end));
                                last = new Interval(Integer.MAX_VALUE, Integer.MIN_VALUE);
                            }
                            covered[j] = 1;
                        }
                    }

                    if (last.begin <= last.end)
                        newIntervals.add(new Interval(last.begin, last.end));

                    intervals = newIntervals;
                }

            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < frequency.length; ++i) {
                char c = (char) ('a' + i);
                for (int j = 0; j < frequency[i]; ++j)
                    sb.append(c);
            }
            out.println(sb.toString());
        }

        private static boolean check(int[] frontier, int[] covered) {
            // Lazy update
            boolean result = (frontier[0] == 1 || covered[0] == 1);
            for (int i = 1; i < frontier.length; ++i) {
                frontier[i] += frontier[i - 1];
                if (frontier[i] == 0 && covered[i] == 0)
                    result = false;
            }
            return result;

        }

        private class Interval {
            int begin;
            int end;

            public Interval(int begin, int end) {
                this.begin = begin;
                this.end = end;
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

    }
}

